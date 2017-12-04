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
    ServerManager serverManager;
    
    public ClientConnectionListener(ServerSocket server,String password, ServerManager serverManager)
    {
        this.server = server;
        this.password = password;
        this.serverManager = serverManager;
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
            
            DataInputStream inputStream = new DataInputStream(socket.getInputStream()); //Input Stream            
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream()); //Output Stream
            
            String userPass = inputStream.readUTF();
            System.out.println("Server received " + userPass);
            if(password.equals(userPass))
            {
                outputStream.writeUTF("Login successful");
                
                //Add the client to the list of Active Clients
                ActiveClientsInfo info = new ActiveClientsInfo();
                info.addClient(socket, outputStream);
                
                //Call the method of ServerManager which displays the infomration of the connected client on the form
                serverManager.displayClient(socket);
                
                //Start another thread which listens for a client connection
                ClientConnectionListener connection = new ClientConnectionListener(server, password, serverManager);
                Thread thread = new Thread(connection);   
                //Add new thread to the Thread ArrayList in ActiveClientsInfo 
                info.addThread(thread);
                thread.start();
                
                
                //Server side dispatching logic
                String input;
                int pos=0;
                while(true)
                {
                    input = inputStream.readUTF();
                    if(input.equals("!LOGGING OUT!"))
                    {
                        System.out.println("Got logout request from client");
                        //Remove the particular socket connection                        
                        serverManager.updateClientList(info.getClientConnections().indexOf(socket));
                        socket.close();
                        break;
                    }
                    else if(input.equals("!OPENED FILE!"))
                    {
                        System.out.println("Server Opening File");
                        //Read the new Text
                        String newText = inputStream.readUTF();
                        System.out.println("Sending " + newText);
                        for(DataOutputStream d: info.getWritingStreams())
                        {
                            if(d!=outputStream)
                            {
                                d.writeUTF("!OPENED FILE!");
                                d.writeUTF(newText);       
                                System.out.println("Sent file text");
                            }
                        }                                                
                    }
                    else
                    {
                        if(!(input.startsWith("!DELETE!")))
                            pos = inputStream.readInt();
                        
                        for(DataOutputStream d: info.getWritingStreams())
                        {
                            if(d!=outputStream)
                            {
                                d.writeUTF(input);       
                                if(!(input.startsWith("!DELETE!")))
                                    d.writeInt(pos);
                            }
                        }                        
                    }                                        
                }                                        
            }
            else
            {
                outputStream.writeUTF("Login Failed");
            }
            
                                              
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
        
    }
}
