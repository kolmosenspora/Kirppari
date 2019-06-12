
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miko ja Nastia
 */
public class falsepaysingle extends javax.swing.JDialog {
    
    
    String hinta;
    int asiakas;
    int poyta;
    String paiva;
    String kello;
    String kuitti;
    String maksutapani;
    String myynti;

    /**
     * Creates new form falsepaysingle
     */
    public falsepaysingle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void setLabels(int asiakas, int poyta, String hinta, String paiva, String kello, String maksutapani, String kuitti, String myynti) {
        this.asiakas = asiakas;
        this.poyta = poyta;
        this.hinta = hinta;
        this.paiva = paiva;
        this.kello = kello;
        this.kuitti = kuitti;
        this.maksutapani = maksutapani;
        this.myynti = myynti;
        
        
        
        this.customernro.setText("" + asiakas);
        this.hintanro.setText("" + hinta);
        this.pvm.setText(paiva);
        this.aika.setText(kello);
        this.kuittinro.setText(kuitti);
        this.tablenro.setText("" + poyta);
        this.maksutapa.setText(maksutapani);
        this.myyntiid.setText(myynti);
        
        
        
        
        
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        customernro = new javax.swing.JTextField();
        kuittinro = new javax.swing.JLabel();
        hintanro = new javax.swing.JLabel();
        pvm = new javax.swing.JLabel();
        aika = new javax.swing.JLabel();
        maksutapa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tablenro = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        myyntiid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pöytä NRO:");

        jLabel2.setText("Asiakasnumero:");

        jLabel3.setText("Kuitti NRO:");

        jLabel4.setText("Hinta:");

        jLabel5.setText("PVM:");

        jLabel6.setText("Aika:");

        jLabel7.setText("Maksutapa:");

        customernro.setText("1");

        kuittinro.setText("jLabel8");

        hintanro.setText("jLabel8");

        pvm.setText("pvm");

        aika.setText("jLabel8");

        maksutapa.setText("jLabel8");

        jButton1.setText("Tallenna");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablenro.setText("jLabel8");

        jLabel8.setText("Myynti id:");

        myyntiid.setText("jLabel9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myyntiid)
                            .addComponent(tablenro)
                            .addComponent(maksutapa)
                            .addComponent(aika)
                            .addComponent(pvm)
                            .addComponent(hintanro)
                            .addComponent(kuittinro)
                            .addComponent(customernro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tablenro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(customernro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(kuittinro))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(hintanro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pvm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(aika))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(maksutapa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(myyntiid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       AccessToMysql mysql = new AccessToMysql();
       mysql.connectah();
       String myyja = customernro.getText();
       int myyjaint = Integer.parseInt(myyja);
       
       int myyntiidint = Integer.parseInt(myynti);
       
        try {
            mysql.changefalsepay(myyjaint, myyntiidint);
        } catch (SQLException ex) {
            Logger.getLogger(falsepaysingle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mysql.closeConnect();
        } catch (SQLException ex) {
            Logger.getLogger(falsepaysingle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Firstpage.falsepays.setTable();
        } catch (SQLException ex) {
            Logger.getLogger(falsepaysingle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(falsepaysingle.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(falsepaysingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(falsepaysingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(falsepaysingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(falsepaysingle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                falsepaysingle dialog = new falsepaysingle(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel aika;
    private javax.swing.JTextField customernro;
    private javax.swing.JLabel hintanro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel kuittinro;
    private javax.swing.JLabel maksutapa;
    private javax.swing.JLabel myyntiid;
    private javax.swing.JLabel pvm;
    private javax.swing.JLabel tablenro;
    // End of variables declaration//GEN-END:variables
}
