package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
                String clientName = inputStream.readUTF();
                
                
                //Add the client to the list of Active Clients
                ActiveClientsInfo info = new ActiveClientsInfo();
                info.addClient(socket, outputStream, inputStream, clientName);
                
                //Call the method of ServerManager which displays the infomration of the connected client on the form
                serverManager.displayClient(socket);
                
                //Start another thread which listens for a client connection
                ClientConnectionListener connection = new ClientConnectionListener(server, password, serverManager);
                Thread thread = new Thread(connection);   
                //Add new thread to the Thread ArrayList in ActiveClientsInfo 
                info.addThread(thread);
                thread.start();
                
                
                //Server side dispatching logic
                String input,recipient;
                int pos=0;                                
                
                while(true)
                {
                    input = inputStream.readUTF();
                    if(input.equals("!LOGGING OUT!"))
                    {
                        System.out.println("Got logout request from client");
                        
                        //Remove the particular socket connection   
                        info.getClientConnections().remove(socket);
                        info.getWritingStreams().remove(outputStream);
                        info.getClientNames().remove(clientName);
                        info.getActiveClientThreads().remove(thread);
                        
                        //Update the details on server manager
                        serverManager.updateClientList(info.getClientConnections().indexOf(socket));
                        outputStream.close();
                        inputStream.close();
                        thread.interrupt();                        
                        socket.close();
                        break;
                    }
                    else if(input.startsWith("!MESSENGER!"))
                    {
                        //Take out the message which was sent
                        //Take out the recipient name
                        recipient = inputStream.readUTF();

                        //Give the message to every client
                        for(DataOutputStream d: info.getWritingStreams())
                        {
                            if(d!=outputStream)
                            {
                                d.writeUTF(input);
                                d.writeUTF(recipient);       
                                System.out.println("Sent message (Chat messenger)");
                            }
                        }                                                
                        
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
