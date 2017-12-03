/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Napster
 */
//This class is used to keep the connection information of the client
public class ClientConnectionInfo {
    static Socket socket;
    static DataOutputStream outputStream;
    static DataInputStream inputStream;
}
