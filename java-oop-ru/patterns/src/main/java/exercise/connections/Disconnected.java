package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    @Override
    public void connect(TcpConnection tcpConnection) {
        tcpConnection.setState(tcpConnection.getConnected());
    }

    @Override
    public void disconnect(TcpConnection tcpConnection) {
        System.out.println("Error: no connection");
    }

    @Override
    public void write(String msg) {
        System.out.println("Error: no connection");
    }
}
// END
