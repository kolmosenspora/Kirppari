
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class Mainclass {
    
    public static Firstpage firstpage;
    public static String printerName = "Star TSP100 Cutter (TSP143) (kopio 1)";
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException  {
        
     
        
        conffaus();
  
        
      removeOlds();
        
 Thread t = new Thread() {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000 * 60 * 60 * 4);
               removeOlds();
               allPrinters.varmuuskopioi();
                 
            } catch (InterruptedException ie) {
            } catch (SQLException ex) {
                Logger.getLogger(Mainclass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
};
t.start();


       AccessToMysql mysql = new AccessToMysql();
       mysql.connectah();
       String salaus[] = new String[2];
       
        salaus = mysql.salaus();
        String username = salaus[0];
        String password = salaus[1];
       
        
      mysql.closeConnect();
        
       int i = 0;
        
        
       for (i = 0; i < 5; i++) {
           JPasswordField pwd = new JPasswordField(25);
       String usernamefrominput = JOptionPane.showInputDialog("Anna Käyttäjänimi: ");
       int action = JOptionPane.showConfirmDialog(null, pwd,"Anna Salasana:",JOptionPane.OK_CANCEL_OPTION);
       
     
       if (username.equals(usernamefrominput) && password.equals(new String(pwd.getPassword()))) {
           
         
       
        firstpage = new Firstpage();
        
        firstpage.setTitle("Kirpputori Kassajärjestelmä");
        
        firstpage.setVisible(true);
       
        i = 10;
        
        removeOlds();
        conffaus();
        allPrinters.varmuuskopioi();
         }
        
        else {
      JOptionPane.showMessageDialog(firstpage, "Väärin, kokeile uudestaan!");
       }
       }
    }
    
    public static void removeOlds() throws SQLException {
                 AccessToMysql mysql = new AccessToMysql();
                mysql.connectah();
                
              
              
                int kaikkiko[] = new int[3];
                
                 kaikkiko = mysql.getKaikkiloppupvm();
                
               while (kaikkiko[0] > 0) {
                
                kaikkiko = mysql.getKaikkiloppupvm();
                   System.out.println(kaikkiko[0] + " Kaikkiko1 " + kaikkiko[1]);
                 
                   
                 if (kaikkiko[0] > 0) {
                    
                mysql.updatevuokrausvoimassa(kaikkiko[0]);
                 mysql.makevuokraus(null, null, null, 1, kaikkiko[1], mysql.getTableHinta(kaikkiko[1]), null, 1);
                     System.out.println("Onnistui");
                
                }
               }
               
               
    }
    public static void conffaus() {
             File configFile = new File("config.properties");
 
try {
    FileReader reader = new FileReader(configFile);
    Properties props = new Properties();
    props.load(reader);
 
    String printer = props.getProperty("printer");
    String provisio = props.getProperty("provisio");
    String provisiodouble = props.getProperty("provisiodouble");
    String path = props.getProperty("path");
    String mysqlpath = props.getProperty("mysql");
    String username = props.getProperty("username");
    String password = props.getProperty("password");
    String database = props.getProperty("database");
    String mysqldump = props.getProperty("mysqldump");
    
    AccessToMysql.database = database;
    AccessToMysql.username = username;
    AccessToMysql.password = password;
    
    System.out.println(database);
    Makingbackup.mysqlpath = mysqlpath;
    Makingbackup.path = path;
    allPrinters.varmuuskopio = path;
    allPrinters.printerName = printer;
    Mainclass.printerName = printer;
    allPrinters.provisioni = provisio;
    Double Provikat = 1 - Double.parseDouble(provisiodouble);
    allPrinters.provisiodouble = Provikat;
    Makingbackup.mysqldump = mysqldump;
 
    System.out.print("Host name is: " + printer + " " + provisio);
    reader.close();
} catch (FileNotFoundException ex) {
    // file does not exist
} catch (IOException ex) {
    // I/O error
}
    }
    }
// }
    //   if (i < 8) {
      //   JOptionPane.showMessageDialog(firstpage, "5 kertaa väärin. Ohjelma sammuu.");
       // }
   // }
    

