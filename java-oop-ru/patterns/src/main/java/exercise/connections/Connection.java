package exercise.connections;

import exercise.TcpConnection;

public interface Connection {
    // BEGIN

    default String getCurrentState() {
        return getClass().getSimpleName().toLowerCase();
    }// — возвращает текущее состояние в виде строки

    void connect(TcpConnection tcpConnection); // — устанавливает новое соединение
    void disconnect(TcpConnection tcpConnection);// — разрывает установленное соединение
    void write(String msg);// — добавляет текстовые данные в буфер
    // END
}
