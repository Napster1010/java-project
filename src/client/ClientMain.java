package client;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ClientMain extends javax.swing.JFrame {

    public ClientMain() {
        initComponents();
        setTitle("Y-NOT Text Editor and Chat Messenger");
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mnuNavigator = new javax.swing.JMenu();
        itmNew = new javax.swing.JMenuItem();
        itmOpen = new javax.swing.JMenuItem();
        itmChat = new javax.swing.JMenuItem();
        itmChat1 = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mnuAbout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mnuNavigator.setBorder(null);
        mnuNavigator.setMnemonic('n');
        mnuNavigator.setText("Navigator");

        itmNew.setMnemonic('c');
        itmNew.setText("Current File");
        itmNew.setToolTipText("Open the Current file on which the group is working");
        itmNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNewActionPerformed(evt);
            }
        });
        mnuNavigator.add(itmNew);

        itmOpen.setMnemonic('o');
        itmOpen.setText("Open New File");
        itmOpen.setToolTipText("Start working on a different file");
        itmOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmOpenActionPerformed(evt);
            }
        });
        mnuNavigator.add(itmOpen);

        itmChat.setMnemonic('c');
        itmChat.setText("Chat");
        itmChat.setToolTipText("Chat with your group");
        itmChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmChatActionPerformed(evt);
            }
        });
        mnuNavigator.add(itmChat);

        itmChat1.setMnemonic('e');
        itmChat1.setText("Exit");
        itmChat1.setToolTipText("Exit the Editor");
        itmChat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmChat1ActionPerformed(evt);
            }
        });
        mnuNavigator.add(itmChat1);

        menuBar.add(mnuNavigator);

        mnuHelp.setMnemonic('h');
        mnuHelp.setText("Help");
        mnuHelp.setToolTipText("Help regarding the editor");
        menuBar.add(mnuHelp);

        mnuAbout.setMnemonic('a');
        mnuAbout.setText("About");
        mnuAbout.setToolTipText("About the Y-NOT Editor");
        menuBar.add(mnuAbout);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmOpenActionPerformed

        JFileChooser opener = new JFileChooser();
        int response = opener.showOpenDialog(rootPane);
        if(response==JFileChooser.OPEN_DIALOG)
        {
            System.out.println("Opening file! From MDI");
            ClientEditor editor = new ClientEditor();
            editor.setVisible(true);
            editor.openFile(opener.getSelectedFile());
        }
        
    }//GEN-LAST:event_itmOpenActionPerformed

    private void itmChat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmChat1ActionPerformed
        System.exit(0);
    
    }//GEN-LAST:event_itmChat1ActionPerformed

    private void itmNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNewActionPerformed

            new ClientEditor().setVisible(true);
            this.setVisible(false);        
        
    }//GEN-LAST:event_itmNewActionPerformed

    private void itmChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmChatActionPerformed

        new ChatMessenger().setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_itmChatActionPerformed

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
            java.util.logging.Logger.getLogger(ClientMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itmChat;
    private javax.swing.JMenuItem itmChat1;
    private javax.swing.JMenuItem itmNew;
    private javax.swing.JMenuItem itmOpen;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuAbout;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenu mnuNavigator;
    // End of variables declaration//GEN-END:variables

}
