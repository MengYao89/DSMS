# DSMS
Highly available distributed staff management system 

The primary goal of the distributed stuff management system is to tolerate process failure. The following techniques are used to achieve this:
    
		- replicated servers
     - a failure detection subsystem
     - leader election to restore system integrity after a server failure
     
The client and front end communicate using CORBA and the front end communicate with leader server using UDP protocol.


Subsystems explanation:  

  This comprises of the following mechanisms:
	
    • Replicated Servers
    •	Reliable FIFO broadcast subsystem.
    •	Leader election subsystem.
    •	Failure detection subsystem.
		
  These subsystems will be a part of each replica server process. The failure detection subsystem residing in each host will periodically check with each other for the host availability and remove the failed server process. It will trigger the leader election subsystem in case of group leader process failure. 
  The FIFO broadcast mechanism (IP multicast) residing in the leader process will forward the request to other server processes in the group whenever it will receive the request from the Front End.

