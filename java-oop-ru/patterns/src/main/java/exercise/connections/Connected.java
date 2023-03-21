package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection{

    @Override
    public void connect(TcpConnection tcpConnection) {
        System.out.println("Error: Already established");
    }

    @Override
    public void disconnect(TcpConnection tcpConnection) {
        tcpConnection.setState(tcpConnection.getDisconnected());
    }

    @Override
    public void write(String msg) {
        System.out.println(msg);
    }
}
// END
