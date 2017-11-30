/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataInputStream;
import java.util.StringTokenizer;
import javax.swing.JTextArea;

/**
 *
 * @author Napster
 */
public class ClientListener implements Runnable{
    DataInputStream inputStream;
    JTextArea txtEditor;
    String read,deleteString;
    int start,end;
    int pos;
    
    public ClientListener(DataInputStream inputStream,JTextArea txtEditor)
    {
        this.inputStream = inputStream;
        this.txtEditor = txtEditor;
    }
    
    public void run()
    {
        int prev=0;
        while(true)
        {
            try
            {
                read = inputStream.readUTF();
                
                if(!(read.startsWith("!DELETE!")))
                    pos = inputStream.readInt();
                
                if(read.equals("~"))
                    break;
                else
                {
                    if(read.startsWith("!DELETE!"))
                    {
                        System.out.println(read);
                        deleteString = read.substring(8);
                        StringTokenizer split = new StringTokenizer(deleteString, "!");
                        start = Integer.parseInt(split.nextToken());
                        end = Integer.parseInt(split.nextToken());
                        
                        txtEditor.replaceRange("", start, end);
                    }
                    else
                    {
                        System.out.print(pos);
                        prev = txtEditor.getCaretPosition();
                        System.out.print("  " + txtEditor.getCaretPosition() + "  ");
                        txtEditor.insert(read, pos);                        
                        txtEditor.setCaretPosition(prev);
                        System.out.print(txtEditor.getCaretPosition() + "  ");
                        System.out.println();
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }    
}
