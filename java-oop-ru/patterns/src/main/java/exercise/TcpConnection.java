package exercise;

import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {

    private Connected connected = new Connected();
    private Disconnected disconnected = new Disconnected();
    private Connection connection;

    public TcpConnection(String ipAddress, int port) {
        connection = getDisconnected();
    }

    String getCurrentState() {
        return connection.getCurrentState();
    }

    void connect() {
        connection.connect(this);
    }

    void disconnect() {
        connection.disconnect(this);
    }

    void write(String msg) {
        connection.write(msg);
    }

    public void setState(Connection connection) {
        this.connection = connection;
    }

    public Connected getConnected() {
        return connected;
    }

    public Disconnected getDisconnected() {
        return disconnected;
    }
}
// END
