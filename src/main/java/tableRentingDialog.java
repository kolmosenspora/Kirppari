
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
public class tableRentingDialog extends javax.swing.JDialog {

    int asiakasnumero;
    float hinta;
    String asiakasnimi;
    int poytaid;
    int myynninmuokkaus = 0;
    int vuokraid = 0;
    
    public void setValues(int asiakasnumero, float hinta, int poytaid, String alkupv, String loppupv, String alepv, String vuokrasta, int vuokraid) throws SQLException {
        
        setAsiakas(asiakasnumero, poytaid);
        
        price.setText("" + hinta);
        
        setlabels();
        
        
        
        
        String alkupvdd = alkupv.substring(8, 10);
        dd.setSelectedItem(alkupvdd);
        
        String alkupvmm = alkupv.substring(5, 7);
        
        mm.setSelectedItem(alkupvmm);   
        
        this.vuokraid = vuokraid;
        
        tablenumber.setText("Pöytä num: " + poytaid + " VuokraNum: " + vuokraid);
        
        
        
        
    }
    
    public tableRentingDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public void setAsiakas(int id, int poytaid) throws SQLException {
        asiakasnumero = id;
        
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        asiakasnimi = mysql.getcustomerName(id);
        customer.setText("" + asiakasnumero + " " + asiakasnimi);
        this.poytaid = poytaid;
        mysql.closeConnect();
    }
    public void setHinta(int id) throws SQLException {
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        hinta = mysql.getTableHinta(id);
        price.setText("" + hinta);
        mysql.closeConnect();
    }
    public void setlabels() throws SQLException {
        String days[] = new String[31];
        days[0] = "" + 0 + 1;
        for (int i = 1; i < days.length; i ++) {
            days[i] = "" + (i + 1);
            if (days[i].length() < 2) {
                days[i] = 0 + days[i];
            }
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(days);
        dd.setModel(model);
        
        String months[] = new String[12];
        months[0] = "" + 0 + 1;
               for (int i = 1; i < months.length; i ++) {
            months[i] = "" + (i + 1);
            if (months[i].length() < 2) {
                months[i] = 0 + months[i];
            }
            
        }
        DefaultComboBoxModel modelmm = new DefaultComboBoxModel(months);
        
        mm.setModel(modelmm);
        
        String years[] = new String[20]; 
        
                       for (int i = 0; i < years.length; i ++) {
            years[i] = "" + (2019 + i);
        }
        DefaultComboBoxModel modelyyyy = new DefaultComboBoxModel(years);
        
        yyyy.setModel(modelyyyy);
        
               String rentweekss[] = new String[53];
        rentweekss[0] = "" + 1;
        for (int i = 1; i < rentweekss.length; i ++) {
            rentweekss[i] = "" + (i + 1);
        }
        DefaultComboBoxModel modelww = new DefaultComboBoxModel(rentweekss);
        rentweeks.setModel(modelww);
        
                      String saledays[] = new String[15];
        
        for (int i = 0; i < saledays.length; i ++) {
            saledays[i] = "" + i;
        }
        DefaultComboBoxModel modelsaledays = new DefaultComboBoxModel(saledays);
        sale.setModel(modelsaledays);
        
        
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        String dayOfMonthStr = String.valueOf(dayOfMonth);
        
        int monthOfYear = cal.get(Calendar.MONTH);
        String monthofyear = "0" + (monthOfYear + 1);
        
        String year = "" + cal.get(Calendar.YEAR);
        
        dd.setSelectedItem(dayOfMonthStr);
        mm.setSelectedItem(monthofyear);
        yyyy.setSelectedItem(year);
       
 
        
    }
    public void makemyynti() throws SQLException, ParseException{
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        
        String pv = "" + dd.getSelectedItem();
        String kuukaus = "" + mm.getSelectedItem();
        String vuos = "" + yyyy.getSelectedItem();
        
        if (pv.length() == 1) {
            pv = "0" + pv;
        }
        if (kuukaus.length() == 1) {
            kuukaus = "0" + kuukaus;
        }
       
        String alkupvm = "" + vuos + "-" + kuukaus + "-" + pv;
        
        
        
        
        
        Date date = java.sql.Date.valueOf(alkupvm);
        int noOfDays = 7;
        String rentweekss = "" + rentweeks.getSelectedItem();
        int viikkoja = Integer.parseInt(rentweekss);
        float hintoja = mysql.getPoytaHinta(poytaid);
        float vuokrahinta = viikkoja * hintoja;
        noOfDays = (noOfDays * viikkoja);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);            
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        
        
        String loppupv = "" + calendar.get(Calendar.DAY_OF_MONTH);
        String loppukuukaus = "" + (calendar.get(Calendar.MONTH) + 1);
        String loppuvuos = "" + calendar.get(Calendar.YEAR);
        
                if (loppupv.length() == 1) {
            loppupv = "0" + loppupv;
        }
        if (loppukuukaus.length() == 1) {
            loppukuukaus = "0" + loppukuukaus;
        }
        
        String loppupvm = "" + loppuvuos + "-" + loppukuukaus + "-" + loppupv;
        
        
        
        Date dateni = java.sql.Date.valueOf(loppupvm);
        String daysalennustr = "" + sale.getSelectedItem();
        int daysalennus = (-Integer.parseInt(daysalennustr));
        
        
        Calendar calendari = Calendar.getInstance();
        calendari.setTime(dateni);            
        calendari.add(Calendar.DAY_OF_YEAR, daysalennus);
        
        String alennuspv = "" + calendari.get(Calendar.DAY_OF_MONTH);
        String alennuskuukaus = "" + (calendari.get(Calendar.MONTH) + 1);
        String alennusvuos = "" + calendari.get(Calendar.YEAR);
        
                if (alennuspv.length() == 1) {
            alennuspv = "0" + alennuspv;
        }
        if (loppukuukaus.length() == 1) {
            alennuskuukaus = "0" + alennuskuukaus;
        }
        
        String alennuspvm = alennusvuos + "-" + alennuskuukaus + "-" + alennuspv;
       
        if (myynninmuokkaus == 0) {
            
        
         
       
            
        mysql.updateVoimassa(poytaid);
            
        mysql.makevuokraus(alkupvm, loppupvm, alennuspvm, asiakasnumero, poytaid, vuokrahinta, "" + poydasta.getText(), 1);
        
        SingleCustomer.tabledialog.setTable();
        SingleCustomer.tabledialog.dispose();
        Firstpage.customersdialog.dispose();
        this.dispose();
     
        
        }
        
        else if (myynninmuokkaus == 1) {
         
            mysql.updateVoimassa(poytaid);
            mysql.updatevuokraus(alkupvm, loppupvm, alennuspvm, asiakasnumero, poytaid, vuokrahinta, "" + poydasta.getText(), 1, vuokraid);
                Firstpage.tablesdialog.setTable();
                 Firstpage.tablesdialog.dispose();
            
                 TablesDialog.renting.dispose();
        }
        mysql.closeConnect();
        
      Mainclass.removeOlds();
        dispose();
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
        customer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dd = new javax.swing.JComboBox<>();
        mm = new javax.swing.JComboBox<>();
        yyyy = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        rentweeks = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        sale = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        poydasta = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tablenumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pöydän Vuokraaminen");

        jLabel2.setText("Asiakas");

        customer.setText("jLabel3");

        jLabel3.setText("Hinta");

        price.setText("jLabel4");

        jLabel4.setText("Vuokraus alkaa:");

        dd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddActionPerformed(evt);
            }
        });

        mm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        yyyy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Vuokraus viikkoja:");

        rentweeks.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        rentweeks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentweeksActionPerformed(evt);
            }
        });

        jLabel6.setText("Ale 50%");

        sale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleActionPerformed(evt);
            }
        });

        jLabel7.setText("pv. ennen loppua");

        jLabel8.setText("Muuta vuokrasta   (ei näy asiakkaalle):");

        poydasta.setColumns(20);
        poydasta.setRows(5);
        jScrollPane1.setViewportView(poydasta);

        jButton1.setText("Vahvista vuokraus");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Poista pöytävuokra");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setText("€");

        jLabel9.setText("Pöytänumero");

        tablenumber.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tablenumber)))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(30, 30, 30)
                                .addComponent(dd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(yyyy, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(price)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(rentweeks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addComponent(jLabel7))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(customer)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tablenumber))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(price)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yyyy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rentweeks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(sale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(47, 47, 47)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddActionPerformed

    private void saleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        int onkoolemassa = 0;
        
        try {
            onkoolemassa = mysql.checkifcustomerexist(asiakasnumero);
        } catch (SQLException ex) {
            Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (onkoolemassa > 0) {
        try {
            makemyynti();
          
        } catch (SQLException ex) {
            Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else {
            JOptionPane.showMessageDialog(this, "Ei ole olemassa asiakasta numero " + asiakasnumero);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
        if (myynninmuokkaus == 1) {
        int reply = JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistaa kokonaan pöytävuokran?", "Myytyjen tuotteiden rahat menevät vuokraajalle", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            
            AccessToMysql mysql = new AccessToMysql();
            mysql.connectah();
            try {
                mysql.updatevuokrausvoimassaanddate(vuokraid);
 
            } catch (SQLException ex) {
                Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                mysql.makevuokraus(null, null, null, 1, poytaid, 0, null, 1);
            } catch (SQLException ex) {
                Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
          JOptionPane.showMessageDialog(null, "Vuokra poistettu lopullisesti");
            try {
                Firstpage.tablesdialog.setTable();
            } catch (SQLException ex) {
                Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dispose();
        }
        else {
           JOptionPane.showMessageDialog(null, "Vuokraa ei poistettu");
          
        }
        }
        else {
             JOptionPane.showMessageDialog(null, "Et voi poistaa vuokraa kesken vuokrauksen.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rentweeksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentweeksActionPerformed
        // TODO add your handling code here:
        AccessToMysql mysql = new AccessToMysql();
        mysql.connectah();
        float hintoja = 0;
        try {
            hintoja = mysql.getPoytaHinta(poytaid);
        } catch (SQLException ex) {
            Logger.getLogger(tableRentingDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        String renting = (String) rentweeks.getSelectedItem();
        float renttaus = Float.parseFloat(renting);
        float summa = (hintoja * renttaus);
        price.setText("" + summa);
        
    }//GEN-LAST:event_rentweeksActionPerformed

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
            java.util.logging.Logger.getLogger(tableRentingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tableRentingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tableRentingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tableRentingDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tableRentingDialog dialog = new tableRentingDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel customer;
    private javax.swing.JComboBox<String> dd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> mm;
    private javax.swing.JTextArea poydasta;
    private javax.swing.JLabel price;
    private javax.swing.JComboBox<String> rentweeks;
    private javax.swing.JComboBox<String> sale;
    private javax.swing.JLabel tablenumber;
    private javax.swing.JComboBox<String> yyyy;
    // End of variables declaration//GEN-END:variables
}
