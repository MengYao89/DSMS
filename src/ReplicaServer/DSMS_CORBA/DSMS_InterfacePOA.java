package ReplicaServer.DSMS_CORBA;

/**
 * Interface definition: DSMS_Interface.
 * 
 * @author OpenORB Compiler
 */
public abstract class DSMS_InterfacePOA extends org.omg.PortableServer.Servant
        implements DSMS_InterfaceOperations, org.omg.CORBA.portable.InvokeHandler
{
    public DSMS_Interface _this()
    {
        return DSMS_InterfaceHelper.narrow(_this_object());
    }

    public DSMS_Interface _this(org.omg.CORBA.ORB orb)
    {
        return DSMS_InterfaceHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:DSMS_CORBA/DSMS_Interface:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("createDRecord")) {
                return _invoke_createDRecord(_is, handler);
        } else if (opName.equals("createNRecord")) {
                return _invoke_createNRecord(_is, handler);
        } else if (opName.equals("editRecord")) {
                return _invoke_editRecord(_is, handler);
        } else if (opName.equals("getRecordCounts")) {
                return _invoke_getRecordCounts(_is, handler);
        } else if (opName.equals("transferRecord")) {
                return _invoke_transferRecord(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_createDRecord(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();
        String arg4_in = _is.read_string();
        String arg5_in = _is.read_string();
        String arg6_in = _is.read_string();

        String _arg_result = createDRecord(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in, arg5_in, arg6_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_createNRecord(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();
        String arg4_in = _is.read_string();
        String arg5_in = _is.read_string();

        String _arg_result = createNRecord(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in, arg5_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_getRecordCounts(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();

        String _arg_result = getRecordCounts(arg0_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_editRecord(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();
        String arg3_in = _is.read_string();

        String _arg_result = editRecord(arg0_in, arg1_in, arg2_in, arg3_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_transferRecord(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = _is.read_string();
        String arg1_in = _is.read_string();
        String arg2_in = _is.read_string();

        String _arg_result = transferRecord(arg0_in, arg1_in, arg2_in);

        _output = handler.createReply();
        _output.write_string(_arg_result);

        return _output;
    }

}
