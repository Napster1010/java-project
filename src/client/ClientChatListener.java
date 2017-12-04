
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
    JTextPane txtChatBox;
    
    public ClientChatListener(Socket socket, DataInputStream inputStream, JTextPane txtChatBox)
    {
        this.socket = socket;
        this.inputStream = inputStream;
        this.txtChatBox = txtChatBox;
    }
    
    public void run()
    {
        String message;
        String recipient;
        try
        {
            StyledDocument document = txtChatBox.getStyledDocument();                                       
            Style style = txtChatBox.addStyle("style", null);            
            
            while(true)
            {
                message = inputStream.readUTF();
                if(message.startsWith("!MESSENGER!"))
                {
                    recipient = inputStream.readUTF();

                    //Display the recipient
                    StyleConstants.setForeground(style, Color.red);
                    document.insertString(txtChatBox.getCaretPosition(), recipient + "\n", style);

                    //Display the message
                    StyleConstants.setForeground(style, Color.blue);
                    document.insertString(txtChatBox.getCaretPosition(), message + "\n", style);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
