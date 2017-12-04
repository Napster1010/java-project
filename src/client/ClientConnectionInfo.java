package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientConnectionInfo {
    static Socket socket;
    static DataOutputStream outputStream;
    static DataInputStream inputStream;
}
