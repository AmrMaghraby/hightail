/*
* MainJFrame.java
*
* Created on 2011-02-17, 20:55:27
*/

package org.hightail.ui;

//import java.awt.Insets;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.hightail.Config;
import org.hightail.Problem;

public class MainJFrame extends javax.swing.JFrame {

    /** Creates new form MainJFrame */
    @SuppressWarnings("LeakingThisInConstructor")
    public MainJFrame() {
        initComponents();

        // We load the configuration
        boolean ok = Config.load();
        if (!ok) { // couldn't load
            JOptionPane.showMessageDialog(this,
                    "If you're a new user, welcome!\n"
                    +"The settings file could not be loaded.\n"
                    +"A new one will be created now, in the same directory as the program.\n"
                    +"Make sure to configure the program before usage.",
                    "Hightail",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                Config.save();
            } catch (IOException e2) {
                JOptionPane.showMessageDialog(this,
                        "The configuration file could not be created. Make sure the program has write rights to its directory.",
                        "Output error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    /** This method is called from within the constructor to
* initialize the form.
* WARNING: Do NOT modify this code. The content of this method is
* always regenerated by the Form Editor.
*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newFromURL = new javax.swing.JMenuItem();
        newContest = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        openConfig = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Hightail");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedPane.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        fileMenu.setText("File");

        newFromURL.setText("New problem...");
        newFromURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFromURLActionPerformed(evt);
            }
        });
        fileMenu.add(newFromURL);

        newContest.setText("New contest...");
        newContest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newContestActionPerformed(evt);
            }
        });
        fileMenu.add(newContest);
        fileMenu.add(jSeparator1);

        openConfig.setText("Settings...");
        openConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openConfigActionPerformed(evt);
            }
        });
        fileMenu.add(openConfig);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        fileMenu.add(exit);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");

        about.setText("About...");
        helpMenu.add(about);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void addTabForProblem(Problem problem) {
        ProblemJPanel panel = new ProblemJPanel(problem, tabbedPane, this);
        // as recommended here: http://stackoverflow.com/questions/476678/tabs-with-equal-constant-width-in-jtabbedpane
        tabbedPane.addTab("<html><body><table width='150'>"+problem.getName()+"</table></body></html>",panel);
        tabbedPane.setSelectedComponent(panel);
    }

    private void confirmAndClose () {
        // Display confirm dialog
        int confirmed = JOptionPane.showConfirmDialog(this,
                "Are you sure?",
                "Confirm quit",
                JOptionPane.YES_NO_OPTION);

        // Close iff user confirmed
        if (confirmed == JOptionPane.YES_OPTION) {
            this.dispose();
            System.exit(0);
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmAndClose();
    }//GEN-LAST:event_formWindowClosing

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        confirmAndClose();
    }//GEN-LAST:event_exitActionPerformed

    private void openConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openConfigActionPerformed
        new ConfigJDialog(this).setVisible(true);
    }//GEN-LAST:event_openConfigActionPerformed

    private void newContestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newContestActionPerformed
        NewContestJDialog dialog = new NewContestJDialog(this);
        dialog.setVisible(true); // this is modal; it will block until window is closed
        for (Problem problem : dialog.getProblemList()) { // possibly none, if parsing failed or user clicked Cancel
            addTabForProblem(problem);
        }
    }//GEN-LAST:event_newContestActionPerformed


    private void newFromURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFromURLActionPerformed
        // show user a dialog to type the name, and the URL
        NewProblemJDialog dialog = new NewProblemJDialog(this);
        dialog.setVisible(true); // this is modal; it will block until window is closed
        if (dialog.getProblem() != null) { // a problem has been created
            addTabForProblem(dialog.getProblem());
        }
    }//GEN-LAST:event_newFromURLActionPerformed

    /**
* @param args the command line arguments
*/
    public static void main(String args[]) {
        // We set the look and feel for Swing
        try {
            /*for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
if ("Nimbus".equals(info.getName())) {
UIManager.setLookAndFeel(info.getClassName());
break;
}
}*/
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.put("TabbedPane.tabInsets", new Insets(5,20,6,20));
        } catch (Exception e) {
            // We fall back to Metal
        }

        // And we let the application run
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newContest;
    private javax.swing.JMenuItem newFromURL;
    private javax.swing.JMenuItem openConfig;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}