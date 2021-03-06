
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class payToCustomer extends javax.swing.JDialog {

    int asiakasnumero;
    String asiakasnimi;
    String summani;
    int maksettu = 0;
    /**
     * Creates new form payToCustomer
     */
    public payToCustomer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public void setlabels(int asiakasnumero, String asiakasnimi, String summani) throws SQLException, ParseException {
        this.asiakasnumero = asiakasnumero;
        this.asiakasnimi = asiakasnimi;
        this.summani = summani;
        
        
        vuokraaja.setText(asiakasnumero + " " + asiakasnimi);
        summa.setText(summani);
        
          String col[] = {"Kuitti NRO", "Pöytä NRO","Maksutapa","PVM", "Klo", "Seuranta", "Hinta"};     
           DefaultTableModel model = new DefaultTableModel(col, 0);
           
           
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        
        if (maksettu == 0) {
        model = mysql.maksamattomatyksihenkilo(model, asiakasnumero);
        }
        
        else if (maksettu == 1) {
            model = mysql.maksettutyksihenkilo(model, asiakasnumero);
        }
        this.tabletti.setModel(model);
        
          mysql.closeConnect();
        
          this.tabletti.setDefaultEditor(Object.class, null);
        
        
        
    }
           public void tulostakuitti() {
           
           
   

   
     PrinterService printerService = new PrinterService();
     
    SimpleDateFormat time_formatterpvm = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat time_formatteraika = new SimpleDateFormat("HH:mm:ss");
    String current_time_straika = time_formatteraika.format(System.currentTimeMillis());
    String current_time_strpvm = time_formatterpvm.format(System.currentTimeMillis());
    String tulostetaan = "";
    
   for (int i = 0; i < tabletti.getRowCount(); i ++) {
       tulostetaan = tulostetaan + tabletti.getValueAt(i, 1) + "    " + tabletti.getValueAt(i, 3) + "  " +  tabletti.getValueAt(i, 4) + " " + tabletti.getValueAt(i, 5) + "   " + tabletti.getValueAt(i, 6) + "\n";
       
   }
    
    String aika = current_time_straika;
    String pvm = current_time_strpvm;
    

    String kuitti = new String();
    
            kuitti = "\n"
                    +"Pöydän vuokraajan tiedot: " + vuokraaja.getText() + "\n" 
                    + "Pöytä    Pvm     Klo 	Tuote 	Hinta \n"    
                    +"------------------------------------------ "
                    + "\n" 
                   
                    
                    + tulostetaan
                    +"\n"
                    +"------------------------------------------\n"
                    +"Myynnit yhteensä: " + summa.getText() + " \n"
                    + "\n"
                    + "Aukioloajat: ma-pe 11-18, la-su 11-16\n" 
                    + "Westfold Oy, Y-tunnus: 2637776-2\n" 
                    + "www.kampinkirpputori.fi 	\n" 
                    +  "\n"             
                    ;
            
           
   
               printerService.printString(Mainclass.printerName, kuitti);
               
            
            System.out.println(kuitti);
            
            

        // cut that paper!
        byte[] empty = new byte[] {27,100, 3};
        byte[] cutP = new byte[] {27,109, 3};
        
        printerService.printBytes(Mainclass.printerName, empty);
        printerService.printBytes(Mainclass.printerName, cutP);
    
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
        maksalabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        vuokraaja = new javax.swing.JLabel();
        maksabtn = new javax.swing.JToggleButton();
        summa = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(tabletti);

        maksalabel.setText("Maksa pöytävuokraajalle");

        vuokraaja.setText("jLabel3");

        maksabtn.setText("Tulosta tästä kuitti ja maksa seuraava summa asiakkaalle:");
        maksabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maksabtnActionPerformed(evt);
            }
        });

        summa.setText("0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(maksabtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(summa))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(maksalabel)
                                .addGap(31, 31, 31)
                                .addComponent(vuokraaja)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maksalabel)
                    .addComponent(jLabel2)
                    .addComponent(vuokraaja))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maksabtn)
                    .addComponent(summa))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maksabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maksabtnActionPerformed
    if (maksettu == 0) {
        tulostakuitti();
      AccessToMysql mysql = new AccessToMysql();
      mysql.connectah();
        try {
            mysql.asetamaksettu(asiakasnumero);
        } catch (SQLException ex) {
            Logger.getLogger(payToCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mysql.closeConnect();
        } catch (SQLException ex) {
            Logger.getLogger(payToCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
        try {
            Firstpage.unpaid.setTable();
        } catch (SQLException ex) {
            Logger.getLogger(payToCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(payToCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    else if (maksettu == 1) {
        tulostakuitti();
        
    }
    }//GEN-LAST:event_maksabtnActionPerformed

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
            java.util.logging.Logger.getLogger(payToCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payToCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payToCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payToCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                payToCustomer dialog = new payToCustomer(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JToggleButton maksabtn;
    public static javax.swing.JLabel maksalabel;
    private javax.swing.JLabel summa;
    private javax.swing.JTable tabletti;
    private javax.swing.JLabel vuokraaja;
    // End of variables declaration//GEN-END:variables
}
