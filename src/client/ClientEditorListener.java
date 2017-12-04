package client;

import java.io.DataInputStream;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextArea;

public class ClientEditorListener implements Runnable{
    DataInputStream inputStream;
    JTextArea txtEditor;
    String read,deleteString;
    JRootPane rootPane;
    int start,end;
    int pos;
    
    public ClientEditorListener(DataInputStream inputStream,JTextArea txtEditor,JRootPane rootPane)
    {
        this.inputStream = inputStream;
        this.txtEditor = txtEditor;
        this.rootPane = rootPane;
    }
    
    public void run()
    {
        int prev=0;
        while(true)
        {
            try
            {
                read = inputStream.readUTF();
                
                if(read.equals("!REMOVED BY SERVER!"))
                {
                    JOptionPane.showMessageDialog(rootPane,"You were kicked out of the editor by server!","Y-NOT Text Editor",JOptionPane.ERROR_MESSAGE);
                    break;
                }                
                else if(read.equals("!OPENED FILE!"))
                {
                    System.out.println("Client Opening file");
                    String newText = inputStream.readUTF();
                    txtEditor.setText(newText);
                }
                else if(read.startsWith("!MESSENGER!"))
                {}
                else
                {
                    if(read.startsWith("!DELETE!"))
                    {
                        deleteString = read.substring(8);
                        StringTokenizer split = new StringTokenizer(deleteString, "!");
                        start = Integer.parseInt(split.nextToken());
                        end = Integer.parseInt(split.nextToken());                        
                        txtEditor.replaceRange("", start, end);
                    }
                    else
                    {
                        pos = inputStream.readInt();                
                        prev = txtEditor.getCaretPosition();
                        txtEditor.insert(read, pos);                        
                    }                
                }    
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
               
        System.exit(0);
    }    
}
