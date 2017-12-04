
package client;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class ClientChatListener implements Runnable{

    Socket socket;
    DataInputStream inputStream;
    ChatMessenger messenger;
    
    public ClientChatListener(Socket socket, DataInputStream inputStream, ChatMessenger messenger)
    {
        this.socket = socket;
        this.inputStream = inputStream;
        this.messenger = messenger;
    }
    
    public void run()
    {
        String message;
        String recipient;
        try
        {            
            while(true)
            {
                message = inputStream.readUTF();
                if(message.startsWith("!MESSENGER!"))
                {
                    recipient = inputStream.readUTF();

                    //Write the contents to the TextPane
                    messenger.writeText(recipient, message.substring(11));                    
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
