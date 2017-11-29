/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Napster
 */
public class ClientConnectionListener implements Runnable {   

    ServerSocket server;
    String password;
    
    public ClientConnectionListener(ServerSocket server,String password)
    {
        this.server = server;
        this.password = password;
    }
    
    public void run()
    {
        try
        {
            System.out.println();
            System.out.println("******************************");
            System.out.println("CURRENT THREAD: " + Thread.currentThread().getName());
            System.out.println("******************************");
            
            
            System.out.println();
            
            System.out.println("Waiting for a client ....");
            
            //Accept client's connection request after authentication
            Socket socket = server.accept();                       
            System.out.println("Got connection request from client " + socket.getInetAddress().getHostAddress() + " , Name: " + socket.getInetAddress().getHostName() );
            
            DataInputStream dis = new DataInputStream(socket.getInputStream()); //Input Stream            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); //Output Stream
            
            String userPass = dis.readUTF();
            
            if(password.equals(userPass))
            {
                dos.writeUTF("Login successful");
            }
            else
            {
                dos.writeUTF("Login Failed");
            }
            
                                              
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
        
    }
}
