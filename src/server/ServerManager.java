/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Napster
 */
public class ServerManager extends javax.swing.JFrame {

    //Model for the List
    DefaultListModel lstModel = new DefaultListModel();
    
    //Model for the ComboBox
    DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
    
    //Count of active Clients
    int clientCount=0;
    
    //Index of deleted Client
    int deletedIndex=-1;
    
    //Server Details
    ServerSocket server;
    int port;
    
    /**
     * Creates new form ServerManager
     */
    public ServerManager() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblClientCount = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstActiveClients = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        cmbClients = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Y-NOT Editor Server Manager");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3), "Active Clients", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 204, 51))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Number of Active Clients :");

        lblClientCount.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblClientCount.setForeground(new java.awt.Color(0, 0, 204));
        lblClientCount.setText("0");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lstActiveClients.setBackground(new java.awt.Color(255, 255, 204));
        lstActiveClients.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lstActiveClients.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lstActiveClients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstActiveClients.setToolTipText("List of active clients");
        lstActiveClients.setEnabled(false);
        jScrollPane1.setViewportView(lstActiveClients);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClientCount, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClientCount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3), "Remove Client", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14)))); // NOI18N

        cmbClients.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbClients.setForeground(new java.awt.Color(0, 153, 51));
        cmbClients.setMaximumRowCount(100);
        cmbClients.setToolTipText("");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("REMOVE CLIENT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(cmbClients, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbClients, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3), "Stop", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("STOP SERVER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(48, 48, 48))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dialogResponse = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to stop the server ?","Y-NOT Editor Server Manager",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(dialogResponse==JOptionPane.YES_OPTION)
        {
        
            //Send a message to every active client about the shutting down of server
            ArrayList<DataOutputStream> outputStreams = new ArrayList<DataOutputStream>();
            ActiveClientsInfo info = new ActiveClientsInfo();
            outputStreams = info.getWritingStreams();

            try
            {
                for(DataOutputStream dos: outputStreams)
                {
                    dos.writeUTF("!SERVER SHUT DOWN!");
                }

                server.close();           
                JOptionPane.showMessageDialog(rootPane, "Server Shut Down successful","Y-NOT Editor Server Manager",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Some Error occurred while shutting down the server", "Y-NOT Editor Server Manager", JOptionPane.ERROR_MESSAGE);            
            }
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int dialogResponse = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete this client?","Y-NOT Editor Server Manager",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(dialogResponse==JOptionPane.YES_OPTION)
        {

            String deleteHost = (String) cmbClients.getSelectedItem();
            System.out.println(deleteHost);
            ActiveClientsInfo info = new ActiveClientsInfo();
            ArrayList<Socket> sockets = info.getClientConnections();
            ArrayList<DataOutputStream> writingStreams = info.getWritingStreams();
            ArrayList<Thread> activeThreads = info.getActiveClientThreads();
            DataOutputStream dos=null;
            if(deleteHost.equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Please select a Client which has to be removed","Y-NOT Editor Server Manager",JOptionPane.ERROR_MESSAGE);            
            }
            else
            {            
                //Find the socket connection in the list of active clients        
                for(Socket s: sockets)
                {
                    if(s.getInetAddress().getHostAddress().equals(deleteHost))
                    {
                        System.out.println();
                        try
                        {       

                            //Remove the Thread corresponding to the socket connection
                            for(Thread thread:activeThreads)
                            {
                                if(thread.equals(activeThreads.get(sockets.indexOf(s))))
                                {
                                    System.out.println("Found the thread");
                                    thread.interrupt();
                                }                                                                                    
                            }                                                                       

                            //Remove the DataOutputStream corresponding to the socket
                            for(DataOutputStream d: writingStreams)
                            {                            
                                if(d.equals(writingStreams.get(sockets.indexOf(s))))
                                {
                                    dos = d;
                                }
                            }
                            //Send a goodbye message to the client
                            dos.writeUTF("!REMOVED BY SERVER!");

                            //Close and Remove the DataOutputStream from the main Array List corresponding to this socket
                            dos.close();
                            writingStreams.remove(dos);                    

                            //For Log
                            System.out.println("Successfully removed client " + s.getInetAddress().getHostAddress() + " -> " + s.getInetAddress().getHostName());

                            //Deleted Index
                            deletedIndex = sockets.indexOf(s);

                            //Now close and remove the Socket from the main Array List
                            s.close();                    
                            sockets.remove(s);

                            //Decrement the value of client count
                            --clientCount;

                            //Update the List
                            updateClientList(deletedIndex);

                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(rootPane, "Some error occurrred while removing the client","Y-NOT Editor Server Manager",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public void initServerDetails(int port, ServerSocket server)
    {
        this.port = port;
        this.server = server;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerManager().setVisible(true);
            }
        });
    }

    //Method to update the details of the connected client on the form
    public void displayClient(Socket socket)
    {
        String hostName = socket.getInetAddress().getHostName();
        String hostAddress = socket.getInetAddress().getHostAddress();        
        
        //Increase the client count by one
        ++clientCount;
        lblClientCount.setText(String.valueOf(clientCount));
        
        //Add the details of the client on the list
        lstModel.addElement("Connected to " + hostAddress + " (" + hostName + ")" + " on " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a").format(new Date()));
        lstActiveClients.setModel(lstModel);

        //Add the details of the client on the combo box
        cmbModel.addElement(hostAddress);
        cmbClients.setModel(cmbModel);
        
    }
    
    public void updateClientList(int deletedIndex)
    {
        //Update the number of active clients
        lblClientCount.setText(String.valueOf(clientCount));
        
        //Update the list and the combo box
        lstModel.remove(deletedIndex);
        cmbModel.removeElementAt(deletedIndex);        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbClients;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClientCount;
    private javax.swing.JList<String> lstActiveClients;
    // End of variables declaration//GEN-END:variables
}
