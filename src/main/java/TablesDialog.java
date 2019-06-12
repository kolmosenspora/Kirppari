
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miko ja Nastia
 */
public class TablesDialog extends javax.swing.JDialog {
  String col[] = {"Pöytänumero", "Hinta / vko","Vuokraaja", "Alkaa", "Päättyy", "Vuokrasta", "ale 50% alkaen", "Pöydästä"};     
  DefaultTableModel tableModel = new DefaultTableModel(col, 0);
 public int poydanvuokrausmode = 0;
 public int myyjaid = 0;
 public static  tableRentingDialog renting;
 
  
    public TablesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void setTable() throws SQLException, ParseException {
        
        DefaultTableModel model = (DefaultTableModel) tabletti.getModel();
        model.setRowCount(0);
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        tableModel = mysql.getalltables(tableModel);
        tabletti.setModel(tableModel);
        mysql.closeConnect();
        
          this.tabletti.setDefaultEditor(Object.class, null);
          
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabletti = new javax.swing.JTable();
        addTable = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabletti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabletti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablettiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabletti);

        addTable.setText("Lisää pöytä");
        addTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTableActionPerformed(evt);
            }
        });

        jButton1.setText("Muokkaa pöytää");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Varauskalenteri");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addTable, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(296, 296, 296)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(addTable, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTableActionPerformed
      addTableDialog addtabledialog = new addTableDialog(Mainclass.firstpage, true);
      try {
          addtabledialog.sethighesttableid();
      } catch (SQLException ex) {
          Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
      }
      addtabledialog.setVisible(true);
    }//GEN-LAST:event_addTableActionPerformed

    private void tablettiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablettiMouseClicked
       
        if (evt.getClickCount() == 2) {
            
         int indexi = tabletti.rowAtPoint(evt.getPoint());
            
         
        String poytaidstring = "" + tabletti.getValueAt(indexi, 0);
             int poytaid = Integer.parseInt(poytaidstring);
         
         if (poydanvuokrausmode == 1 ) {
             
             if (tabletti.getValueAt(indexi, 2).equals("1 Tyhjä Pöytä")) {
                 
                 
               
             tableRentingDialog renting = new tableRentingDialog(Mainclass.firstpage, true);
             try {
                 renting.setAsiakas(myyjaid, poytaid);
             } catch (SQLException ex) {
                 Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             
             try {
                 renting.setHinta(poytaid);
             } catch (SQLException ex) {
                 Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 renting.setlabels();
             } catch (SQLException ex) {
                 Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
             }
             renting.setVisible(true);
             }
             else {
             JOptionPane.showMessageDialog(this, "Voit vuokrata vain tyhjään pöytään");
             }
         }
         
        
        if (poydanvuokrausmode == 0) {
            AccessToMysql mysql = new AccessToMysql();
            mysql.connectah();
            String kaikki[] = new String[9];
            
             try {
                 kaikki = mysql.getallfromvuokraus(poytaid);
             } catch (SQLException ex) {
                 Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
             }
             
           
             int vuokranumero = Integer.parseInt(kaikki[0]);
             
             if (vuokranumero > 0) {
             int asiakasid = Integer.parseInt(kaikki[4]);
             if (asiakasid > 1) {
                          String alkupvm = kaikki[1];
                          String loppupvm = kaikki[2];
                          String alepvm = kaikki[3];
                         
                          float vuokrahinta = Float.parseFloat(kaikki[5]);
                          String vuokrastainfoa = kaikki[6];
                          int voimassako = Integer.parseInt(kaikki[7]);
                          int tableid = Integer.parseInt(kaikki[8]);
                          
                         renting = new tableRentingDialog(Mainclass.firstpage, true);
             try {
                 renting.setValues(asiakasid, vuokrahinta, tableid, alkupvm, loppupvm, alepvm, vuokrastainfoa, vuokranumero);
             } catch (SQLException ex) {
                 Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             
             renting.myynninmuokkaus = 1;
             renting.setVisible(true);
             }
             try {
                 mysql.closeConnect();
             } catch (SQLException ex) {
                 Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
        
        }
        }
    }//GEN-LAST:event_tablettiMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       AccessToMysql mysql = new AccessToMysql();
       mysql.connectah();
       
        
        String poytaidstr = JOptionPane.showInputDialog("Anna pöydän numero:");
        int poytaid = Integer.parseInt(poytaidstr);
        addTableDialog atd = new addTableDialog(Mainclass.firstpage, true);
     
        atd.id = poytaid;
      try {
          atd.muistiinpanot = mysql.getpoytamuistiinpano(poytaid);
      } catch (SQLException ex) {
          Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
          atd.summa = mysql.getpoytahinta(poytaid);
      } catch (SQLException ex) {
          Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
      }
      atd.setLabels();
      atd.poydanmuokkausmode = 1;
      atd.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try {
          Mainclass.firstpage.setcalendar();
      } catch (SQLException ex) {
          Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ParseException ex) {
          Logger.getLogger(TablesDialog.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      Mainclass.firstpage.calendar.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TablesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TablesDialog dialog = new TablesDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabletti;
    // End of variables declaration//GEN-END:variables
}