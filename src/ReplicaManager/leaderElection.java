package ReplicaManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import HeartBeat.PacketServer;
import HeartBeat.UDPTransporter;

public class leaderElection {

	public static int LEADER_ELECTION_INI  = 9;
	public static int LEADER_ELECTION_ACK  = 10;
	public static int LEADER_ELECTION_HOLD = 11;
	public static int LEADER_ELECTION_DONE = 12;
	public static boolean IS_LEADER = true;
	public static boolean IS_NOT_LEADER = false;
	public static int IS_ALIVE = 1;
	public static int IS_NOT_ALIVE = 0;
	
	public static boolean isLeader = false;
	int ReplicaManagerID = 1;
	Timer timer = new Timer();
	//UDPTransporter transpoter = new UDPTransporter();
	public leaderElection(){
	}
	
	HashMap<String, Integer> GroupMemberStatus = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> GroupMemberStatus() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> gm = new HashMap<String, Integer>();
		gm.put("rep1", 3);
		gm.put("rep2", 2);
		gm.put("rep3", 1);
		return gm;
	}
	
	public String initialize() throws Exception{
//		String msg = ReplicaManager.REPLICA_MANAGER_NAME + "," + Integer.toString(ReplicaManagerID) + "," +
//				Integer.toString(LEADER_ELECTION_INI);
//		for(HashMap.Entry<String, String> entry : getGroupMember().entrySet()){
//			UDPTransporter.send(entry.getValue(), ReplicaManager.LEADER_ELECTION_PORT, msg);
//		}
//		
//		DatagramSocket server = new DatagramSocket(ReplicaManager.LEADER_ELECTION_PORT);
//		Thread listener = new Thread(new Runnable(){
//			public void run(){
//				while (true) {
//					byte[] recData = new byte[1024];
//					DatagramPacket receivePacket = new DatagramPacket(recData, recData.length);
//					try {
//						server.receive(receivePacket);
//					} catch (IOException e) {
//						e.printStackTrace();
//						return;
//					}
//					parsePacket(new String(receivePacket.getData()));
//				}
//				
//			}
//		});
//		listener.start();
		
		return electMaxID(GroupMemberStatus());
	}
	
	public void electLeader() throws Exception{
		String msg = ReplicaManager.REPLICA_MANAGER_NAME + "," + Integer.toString(ReplicaManagerID) + "," +
				Integer.toString(LEADER_ELECTION_HOLD);
		for(Entry<String, Integer> entry : GroupMemberStatus.entrySet()){
			if (entry.getValue() > ReplicaManagerID){
				String host = getGroupMember().get(entry.getKey());
				DatagramSocket electSocket = new DatagramSocket();
				InetAddress IPAddress = InetAddress.getByName(host);
				byte[] sendData = msg.getBytes();
				byte[] receiveData = new byte[4096];
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, ReplicaManager.LEADER_ELECTION_PORT);
				electSocket.send(sendPacket);
				electSocket.close();
				DatagramSocket server = new DatagramSocket(ReplicaManager.LEADER_ELECTION_PORT);
				Thread listener = new Thread(new Runnable(){
				public void run(){
					while (true) {
						byte[] recData = new byte[1024];
						DatagramPacket receivePacket = new DatagramPacket(recData, recData.length);
						try {
							server.receive(receivePacket);
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}
						parsePacket(new String(receivePacket.getData()));
						String reply = parsePacket(new String(receivePacket.getData()));
						if(reply != null){
							byte[] send = reply.getBytes();
							int prt = receivePacket.getPort();
							InetAddress IPAddress = receivePacket.getAddress();
							DatagramPacket sendPacket = new DatagramPacket(send, send.length, IPAddress, prt);
							try {
								server.send(sendPacket);
							} catch (IOException e) {
								e.printStackTrace();
							}
							clock(3);
							isLeader = true;
						}
						else 
							isLeader = false;
					}
					
				}
			});
			listener.start();
			}
			isLeader = true;
		}
		
	}

	public String electMaxID(HashMap<String, Integer> GM) {
		int max = 0;
		for(Entry<String, Integer> entry : GM.entrySet()){
			if (entry.getValue() > max)
				max = entry.getValue();
		}
		for(Entry<String, Integer> entry : GM.entrySet()){
			if (entry.getValue() == max)
				return entry.getKey();
		}
		return null;
	}
	 

	public HashMap<String, String> getGroupMember() {
		// TODO Auto-generated method stub
		HashMap<String, String> gm = new HashMap<String, String>();
		gm.put("rep1", "192.168.1.2");
		gm.put("rep2", "192.168.1.3");
		gm.put("rep3", "192.168.1.4");
		return gm;
	}
	
	public String parsePacket(String in){
		String replicaManagerName = new String(in.trim().split(",")[0]);
		int replicaManagerID = Integer.parseInt(in.trim().split(",")[1]);
		int electionCMD = Integer.parseInt(in.trim().split(",")[2]);
		if (electionCMD == LEADER_ELECTION_INI){
			GroupMemberStatus.put(replicaManagerName, replicaManagerID);
			return null;
		}
		else if (electionCMD == LEADER_ELECTION_HOLD){
			if (replicaManagerID < ReplicaManagerID)
			return new String(ReplicaManager.REPLICA_MANAGER_NAME + "," + Integer.toString(ReplicaManagerID) + "," +
				Integer.toString(LEADER_ELECTION_ACK));
			else
				return null;
		}
		else if (electionCMD == LEADER_ELECTION_ACK){
			return null;
		}
		return null;
	}
	
	public void clock(long time){
		timer.schedule(new TimerTask01(), time * 1000);
	}
	
	public class TimerTask01 extends TimerTask{
		public void run() {
	    }
	}
}
