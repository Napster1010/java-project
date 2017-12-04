package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ActiveClientsInfo {
    //Made static because the list of all active connections need to be shared amongst all the objects of this class. Data privacy is maintained as the information is shared at server level.
    //Socket connections
    private static ArrayList<Socket> activeSockets = new ArrayList<Socket>();

    //DataOutputStreams
    private static ArrayList<DataOutputStream> activeWritingStreams = new ArrayList<DataOutputStream>();
    
    //DataInputStreams
    private static ArrayList<DataInputStream> activeInputStreams = new ArrayList<DataInputStream>();

    //Active Threads
    private static ArrayList<Thread> activeClientThreads = new ArrayList<Thread>();
    
    //Names of Active Clients
    private static ArrayList<String> activeClientNames = new ArrayList<String>();
    
    public void addClient(Socket socket, DataOutputStream dos, DataInputStream dis, String clientName)
    {
        activeSockets.add(socket);
        activeWritingStreams.add(dos);       
        activeInputStreams.add(dis);
        activeClientNames.add(clientName);
        
        System.out.println();
        System.out.println("New Client successfully added!!");
        System.out.println("Client Count: " + activeSockets.size());
                
    }
    
    public void addThread(Thread thread)
    {
        activeClientThreads.add(thread);        
    }
    
    public ArrayList<Thread> getActiveClientThreads()
    {
        return activeClientThreads;
    }
    
    public ArrayList<Socket> getClientConnections()
    {
       return activeSockets; 
    }
    
    public ArrayList<DataOutputStream> getWritingStreams()
    {
        return activeWritingStreams;
    }    
    
    public ArrayList<String> getClientNames()
    {
        return activeClientNames;
    }
    
    public ArrayList<DataInputStream> getInputStreams()
    {
        return activeInputStreams;
    }
}
