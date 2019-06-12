
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miko ja Nastia
 */
public class addTableDialog extends javax.swing.JDialog {

   int id;
   String muistiinpanot;
   float summa;
   int poydanmuokkausmode = 0;
   
    public addTableDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public void setLabels() {
        price.setText("" + summa);
        memos.setText(muistiinpanot);
        tableid.setText("" + id);
        
        tableid.setEditable(false);
    }
    
    public void sethighesttableid() throws SQLException {
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        int gethighest = (mysql.selecthighestpoytaid() + 1);
        
        tableid.setText("" + gethighest);
        
        mysql.closeConnect();
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tableid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        memos = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pöytä Nro:");

        tableid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableidActionPerformed(evt);
            }
        });

        jLabel2.setText("Hinta:");

        jLabel3.setText("Muistiinpanot:");

        jButton1.setText("Tallenna Pöytä");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(memos, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                    .addComponent(tableid)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jButton1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tableid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(memos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        if (poydanmuokkausmode == 0) {
        id = Integer.parseInt(tableid.getText());
       muistiinpanot = memos.getText();
       summa = Float.parseFloat(price.getText());
       AccessToMysql mysql = new AccessToMysql();
       mysql.connectah();
       
       int ifexists = 0;
       
            try {
                ifexists = mysql.checkiftableexist(id);
            } catch (SQLException ex) {
                Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (ifexists == 0) {
       try {
           mysql.addTable(id, summa, muistiinpanot);
       } catch (SQLException ex) {
           Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
       }
            try {
                mysql.makevuokraus(null, null, null, 1, id, summa, null, 1);
              
            } catch (SQLException ex) {
                Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
       try {
           mysql.closeConnect();
       } catch (SQLException ex) {
           Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           Firstpage.tablesdialog.setTable();
       } catch (SQLException ex) {
           Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ParseException ex) {
           Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
       }
       dispose();
        }
            else {
                JOptionPane.showMessageDialog(this, "Et voi luoda toista pöytää samalle numerolle!");
            }
        }
        
        if (poydanmuokkausmode == 1) {
       id = Integer.parseInt(tableid.getText());
       muistiinpanot = memos.getText();
       summa = Float.parseFloat(price.getText());
       AccessToMysql mysql = new AccessToMysql();
       mysql.connectah();
            try {
                mysql.updateTable(id, summa, muistiinpanot);
            } catch (SQLException ex) {
                Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
                 try {
           mysql.closeConnect();
       } catch (SQLException ex) {
           Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           Firstpage.tablesdialog.setTable();
       } catch (SQLException ex) {
           Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ParseException ex) {
           Logger.getLogger(addTableDialog.class.getName()).log(Level.SEVERE, null, ex);
       }
       dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableidActionPerformed

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
            java.util.logging.Logger.getLogger(addTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addTableDialog dialog = new addTableDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField memos;
    private javax.swing.JTextField price;
    private javax.swing.JTextField tableid;
    // End of variables declaration//GEN-END:variables
}
