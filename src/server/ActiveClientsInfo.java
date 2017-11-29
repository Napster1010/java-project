/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Napster
 */
public class ActiveClientsInfo {
    //Made static because the list of all active connections need to be shared amongst all the objects of this class. Data privacy is maintained as the information is shared at server level.
    private static ArrayList<Socket> activeSockets = new ArrayList<Socket>();
           
    private static ArrayList<DataOutputStream> activeWritingStreams = new ArrayList<DataOutputStream>();
    
    
    public void addClient(Socket socket, DataOutputStream dos)
    {
        activeSockets.add(socket);
        activeWritingStreams.add(dos);       
        
        System.out.println();
        System.out.println("New Client successfully added!!");
        System.out.println("Client Count: " + activeSockets.size());
                
    }
    
    public ArrayList<Socket> getClientConnections()
    {
       return activeSockets; 
    }
    
    public ArrayList<DataOutputStream> getWritingStreams()
    {
        return activeWritingStreams;
    }    
}
