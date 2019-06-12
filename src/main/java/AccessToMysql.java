
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class AccessToMysql {
    Connection con;  
    ResultSet rs;
    Statement stmt;
    Date date;
    Date loppupvm;
  
    
    public static String database = "kirpparikj";
    public static String username = "root";
    public static String password = "";
    private TableModel model;
  
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
     Date theDate;
  Calendar myCal = Calendar.getInstance();
    
    public void connectah() {
           
        try{  
Class.forName("com.mysql.jdbc.Driver");  
con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/" + database ,username,password);  
stmt=con.createStatement();  

            System.out.println("Connection toimii!");
            
            
            
        

}catch(Exception e){ System.out.println(e);}  
    }
    
    public void closeConnect() throws SQLException {
        con.close();
        System.out.println("Connection suljettu");
    }
    
        public void addmyynti(float summa, int myyjaid, String seuranta, int kuitattu, int poytaid) throws SQLException {
          
        
         String query = "insert into myynti (summa, seuranta, aika, pvm, kuitattu, myyjaid, maksettu, pankkikortti, kateinen, poytaid) values (?, ?, now(),now(), ?, ?, 0, 0, 0, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setFloat (1, summa);
      preparedStmt.setString (2, seuranta);
      preparedStmt.setInt (3, kuitattu);
      preparedStmt.setInt (4, myyjaid);
      preparedStmt.setInt (5, poytaid);
    
      preparedStmt.execute();
    }
        public int getHighestkuittiid() throws SQLException {
            int highestkuittiid = 1;
            
            rs=stmt.executeQuery("select max(kuittiid) from kuitti;");  
        
        while (rs.next()) {
              highestkuittiid = rs.getInt("max(kuittiid)");
        }
        return highestkuittiid;
            
        }
        public void addkuitti(int id, int myyntiid) throws SQLException {
         
                 String query = "insert into kuitti (kuittiid, myyntiid) values (?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, id);
      preparedStmt.setInt (2, myyntiid);

    
      preparedStmt.execute();
            
        }
        public int getHighestmyyntiid() throws SQLException {
            int highestmyyntiid = 1;
            
             rs=stmt.executeQuery("select max(id) from myynti;");  
        
        while (rs.next()) {
               if (rs.getInt("max(id)") != 0) {
                   highestmyyntiid = rs.getInt("max(id)");
               }
        }
        return highestmyyntiid;
            
        }
        public void pankkikorttimaksu(int myyntiid) throws SQLException {
            String query = "update myynti set kuitattu = 1, pankkikortti = 1 where id = (?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, myyntiid);

    
      preparedStmt.execute();
        }
        public void kateismaksu(int myyntiid) throws SQLException {
                  String query = "update myynti set kuitattu = 1, kateinen = 1 where id = (?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, myyntiid);

    
      preparedStmt.execute();
        }
        
        public DefaultTableModel getallmyyntifromkuitti(DefaultTableModel model, int kuittiid) throws SQLException {
            String adding[] = new String[4];
            this.model = model;
        
         
       
            
            rs=stmt.executeQuery("select * from myynti, kuitti where myynti.id = kuitti.myyntiid and kuitti.kuittiid =" + kuittiid);  
        
        while (rs.next()) {
              adding[0] = "" + rs.getInt("myyntiid");
              adding[1] = "" + rs.getInt("poytaid");
              adding[2] = "" + rs.getString("seuranta");
              adding[3] = "" + rs.getDouble("summa");
              model.addRow(adding);
        }
        return model;
      

    
    
        }
      public void poistamyynti(int myyntiid) throws SQLException {
       String query = "delete from myynti where id = ?";
        
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, myyntiid);

    
      preparedStmt.execute();
        }
      public void poistamyyntikuitsta(int myyntiid) throws SQLException {
              String query = "delete from kuitti where myyntiid = ?";
        
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, myyntiid);
   

      preparedStmt.execute();
      }
      
              public ArrayList getallmyyntiid() throws SQLException {
            ArrayList<Integer> list = new ArrayList<Integer>();
            
             rs=stmt.executeQuery("select id from myynti");  
        
        while (rs.next()) {
               list.add(rs.getInt("id"));
        }
        
        
        return list;
       
}
              public int getpankkikortti(int kuittiid) throws SQLException {
                  int summatakaisin = 0;
         
                 rs=stmt.executeQuery("select pankkikortti from myynti, kuitti where kuitti.myyntiid = myynti.id and kuitti.kuittiid = " + kuittiid); 
                  while (rs.next()) {
               summatakaisin = (rs.getInt("pankkikortti"));
        }
                  
                  return summatakaisin;
              }
              
              public void setmyyjatotable(int vuokrausid, int poytaid) throws SQLException {
          String query = "update poyta set vuokrausid = ? where poytaid = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, vuokrausid);
       preparedStmt.setInt (2, poytaid);
        preparedStmt.execute();
              }
              
              public int getHighestvuokrausid() throws SQLException {
                  int palautus = 0;
                       rs=stmt.executeQuery("select max(id) from vuokraus"); 
                  while (rs.next()) {
               palautus = (rs.getInt("max(id)"));
                  
                 
              }
                  return palautus;
              }
              
              public int getmyyjafromtable(int poytaid) throws SQLException {
                     int summatakaisin = 0;
         
                 rs=stmt.executeQuery("select * from vuokraus where voimassa = 1 and alkupvm <= now() and vuokraus.poytaid = " + poytaid); 
                  while (rs.next()) {
               summatakaisin = (rs.getInt("customerid"));
        }
                  
                  return summatakaisin;
              }
              public DefaultTableModel getallcustomers(DefaultTableModel model) throws SQLException, ParseException {
            String adding[] = new String[7];
            this.model = model;
        
            String lisayspv = "";
       
            
            rs=stmt.executeQuery("select * from customers");  
        
        while (rs.next()) {
              adding[0] = "" + rs.getString("id");
              adding[1] = "" + rs.getString("firstname");
              adding[2] = "" + rs.getString("lastname");
              adding[3] = "" + rs.getString("email");
              adding[4] = "" + rs.getString("phone");
              adding[5] = "" + rs.getString("memos");
              lisayspv = "" + rs.getString("lisayspvm");
              
              if (lisayspv.contains("null")) {
              lisayspv = "";
              }
              else {
              date = format.parse(rs.getString("lisayspvm"));
              lisayspv = df.format(date);
              }
              adding[6] = lisayspv;
              model.addRow(adding);
        }
        return model;
        
        
        
      

    
    
        }

              
              public void addCustomer(int id, String firstname, String lastname, String sposti, String puhnum, String muistiinpanot) throws SQLException {
                                 String query = "insert into customers (id, firstname, lastname, email, phone, memos, lisayspvm) values (?, ?, ?, ?, ?, ?, now())";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, id);
      preparedStmt.setString(2, firstname);
      preparedStmt.setString(3, lastname);
      preparedStmt.setString(4, sposti);
      preparedStmt.setString(5, puhnum);
      preparedStmt.setString(6, muistiinpanot);
    
      preparedStmt.execute();
              }
                            public void updateCustomer(int id, String firstname, String lastname, String sposti, String puhnum, String muistiinpanot) throws SQLException {
                                 String query = "update customers set firstname = ?, lastname = ?, email = ?, phone = ?, memos = ? where id = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
 
      preparedStmt.setString(1, firstname);
      preparedStmt.setString(2, lastname);
      preparedStmt.setString(3, sposti);
      preparedStmt.setString(4, puhnum);
      preparedStmt.setString(5, muistiinpanot);
      preparedStmt.setInt(6, id);
    
      preparedStmt.execute();
}
            public DefaultTableModel getalltables(DefaultTableModel model) throws SQLException, ParseException {
            String adding[] = new String[8];
            this.model = model;
           String result = "";
           String resultyks = "";
           String resultkaks = "";
            String alkpv;
            String loppupv;
            String alennus;
            
            rs=stmt.executeQuery("select * from poyta left join vuokraus as vuokraus on vuokraus.id inner join customers on vuokraus.customerid where vuokraus.poytaid = poyta.poytaid and vuokraus.customerid = customers.id and vuokraus.voimassa = 1 order by poyta.poytaid");  
        
        while (rs.next()) {
              adding[0] = "" + rs.getString("poytaid");
              adding[1] = "" + rs.getString("hinta");
                       
              adding[2] = "" + rs.getString("customerid") + " " + rs.getString("firstname") + " " + rs.getString("lastname");
              alkpv = "" + rs.getDate("alkupvm");
              if (alkpv.contains("null")) {
              result = "";
              }
              else {
              date = format.parse(rs.getString("alkupvm"));
              result = df.format(date);
              }
              loppupv = "" + rs.getDate("loppupvm");
              if (alkpv.contains("null")) {
              resultyks = "";
              }
              else {
              date = format.parse(rs.getString("loppupvm"));
              resultyks = df.format(date);
              }
              
              alennus = "" + rs.getDate("alepvm");
              if (alkpv.contains("null")) {
              resultkaks = "";
              }
              else {
              date = format.parse(rs.getString("alepvm"));
              resultkaks = df.format(date);
              }
              
              adding[3] = "" + result;
              adding[4] = "" + resultyks;
              adding[5] = "" + rs.getString("vuokrasta");
              adding[6] = "" + resultkaks;
              adding[7] = "" + rs.getString("poydastatietoa");
              model.addRow(adding);
            
        }
        return model;
      

    
        
        }
            
            public int getCustomerscount() throws SQLException {
                int customers = 0;
                
                   rs=stmt.executeQuery("select count(id) from customers");  
                   
                   while (rs.next()) {
                       customers = rs.getInt("count(id)");
                   }
                
                return customers;
            }
                                          
        public void addTable(int id, float summa, String muistiinpanot) throws SQLException {
                                             String query = "insert into poyta (poytaid, hinta, poydastatietoa) values (?, ?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
 
      preparedStmt.setInt(1, id);
      preparedStmt.setFloat(2, summa);
      preparedStmt.setString(3, muistiinpanot);

    
      preparedStmt.execute();
}
        
        public String getcustomerName(int id) throws SQLException {
            String name = null;
             rs=stmt.executeQuery("select * from customers where id =" + id);  
            
            while (rs.next()) {
                name = rs.getString("firstname") + " " + rs.getString("lastname");
            }
            
            return name;
        }
        public Float getTableHinta(int id) throws SQLException {
            float hinta = 0;
             rs=stmt.executeQuery("select * from poyta where poytaid =" + id);  
             
             while(rs.next()) {
                 hinta = (float) rs.getDouble("hinta");
             }
        
        return hinta;
        } 
        
        public void makevuokraus(String alkupvm, String loppupvm, String alepvm, int customerid, int poytaid, float vuokrahinta, String vuokrasta, int voimassa) throws SQLException {
                                                         String query = "insert into vuokraus (alkupvm, loppupvm, alepvm, customerid, vuokrahinta, vuokrasta, voimassa, poytaid) values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setString(1, alkupvm);
      preparedStmt.setString(2, loppupvm);
      preparedStmt.setString(3, alepvm);
      preparedStmt.setInt(4, customerid);
  
      preparedStmt.setFloat(5, vuokrahinta);
      preparedStmt.setString(6, vuokrasta);
       preparedStmt.setInt(7, voimassa);
        preparedStmt.setInt(8, poytaid);

    
      preparedStmt.execute();
        }
        
        
        public int selecthighestpoytaid() throws SQLException {
           
            
             int takaisin = 0;
            
             rs=stmt.executeQuery("select max(poytaid) from poyta");  
             
             while(rs.next()) {
                 takaisin = rs.getInt("max(poytaid)");
             }
            
            return takaisin;
            
        }
        public String getpoytamuistiinpano(int id) throws SQLException {
            String takaisin = null;
            
             rs=stmt.executeQuery("select * from poyta where poytaid =" + id);  
             
             while(rs.next()) {
                 takaisin = rs.getString("poydastatietoa");
             }
            
            return takaisin;
        }
        
        public Integer[] getpoytavoimassaAndcustomerid(int poytaid) throws SQLException {
            Integer palautus[] = new Integer[2];
            
              rs=stmt.executeQuery("select * from vuokraus inner join customers on vuokraus.customerid inner join poyta on vuokraus.poytaid where vuokraus.customerid = customers.id and vuokraus.poytaid = poyta.poytaid and voimassa = 1 and poyta.poytaid =" + poytaid);  
             
             while(rs.next()) {
                 palautus[0] = rs.getInt("voimassa");
                 palautus[1] = rs.getInt("customerid");
             }
            
            return palautus;
                    
        }
        
        public int getminjonoid(int poytaid) throws SQLException {
            int palautus = 0;
            
             rs=stmt.executeQuery("select min(jonoid) from jono inner join vuokraus on jono.vuokrausid where vuokraus.poytaid = " + poytaid);  
             
             while(rs.next()) {
                 palautus = rs.getInt("min(jonoid)");
                 
             }
            
            
            return palautus;
        }
        
         public float getpoytahinta(int id) throws SQLException {
            float takaisin = 0;
            
             rs=stmt.executeQuery("select * from poyta where poytaid =" + id);  
             
             while(rs.next()) {
                 takaisin = rs.getFloat("hinta");
             }
            
            return takaisin;
        }
                 public void updateTable(int id, float summa, String muistiinpanot) throws SQLException {
                                             String query = "update poyta set poydastatietoa = ?, hinta = ? where poytaid = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
 
         preparedStmt.setString(1, muistiinpanot);
         preparedStmt.setFloat(2, summa);
           preparedStmt.setInt(3, id);
     
    
      preparedStmt.execute();
}
                 
          public int getpoytavuokraaja(int id) throws SQLException {
            int takaisin = 0;
            
             rs=stmt.executeQuery("select * from poyta inner join vuokraus on poyta.vuokrausid where vuokraus.id = poyta.vuokrausid and poyta.poytaid =" + id);  
             
             while(rs.next()) {
                 takaisin = rs.getInt("customerid");
             }
            
            return takaisin;
        }
                  public String getvuokrastainfoa(int id) throws SQLException {
            String takaisin = null;
            
             rs=stmt.executeQuery("select * from poyta inner join vuokraus on poyta.vuokrausid where vuokraus.id = poyta.vuokrausid and poyta.poytaid =" + id);  
             
             while(rs.next()) {
                 takaisin = rs.getString("vuokrasta");
             }
            
            return takaisin;
        }
                    public String getalennuspvm(int id) throws SQLException {
            String takaisin = null;
            
             rs=stmt.executeQuery("select * from poyta inner join vuokraus on poyta.vuokrausid where vuokraus.id = poyta.vuokrausid and poyta.poytaid =" + id);  
             
             while(rs.next()) {
                 takaisin = rs.getString("alepvm");
             }
            
            return takaisin;
        }
                            public String getpoytaalkuvuokra(int id) throws SQLException {
            String takaisin = null;
            
             rs=stmt.executeQuery("select * from poyta inner join vuokraus on poyta.vuokrausid where vuokraus.id = poyta.vuokrausid and poyta.poytaid =" + id);  
             
             while(rs.next()) {
                 takaisin = rs.getString("alkupvm");
             }
            
            return takaisin;
        }
                                    public String getpoytaloppu(int id) throws SQLException {
            String takaisin = null;
            
             rs=stmt.executeQuery("select * from poyta inner join vuokraus on poyta.vuokrausid where vuokraus.id = poyta.vuokrausid and poyta.poytaid =" + id);  
             
             while(rs.next()) {
                 takaisin = rs.getString("loppupvm");
             }
            
            return takaisin;
        }
        public void updatevuokraus(String alkupvm, String loppupvm, String alepvm, int customerid, int poytaid, float vuokrahinta, String vuokrasta, int voimassa, int vuokraid) throws SQLException {
                                                         String query = "update vuokraus set alkupvm = ?, loppupvm = ?, alepvm = ?, customerid = ?, vuokrahinta = ?, vuokrasta = ?, voimassa = ?, poytaid = ? where id = ?";
                                                            
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setString(1, alkupvm);
      preparedStmt.setString(2, loppupvm);
      preparedStmt.setString(3, alepvm);
      preparedStmt.setInt(4, customerid);
  
      preparedStmt.setFloat(5, vuokrahinta);
      preparedStmt.setString(6, vuokrasta);
       preparedStmt.setInt(7, voimassa);
        preparedStmt.setInt(8, poytaid);
        preparedStmt.setInt(9, vuokraid);

    
      preparedStmt.execute();
        }
        
        public void updatevuokrausvoimassa(int vuokraid) throws SQLException {
            String query = "update vuokraus set voimassa = 0 where id = ?";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt(1, vuokraid);
       preparedStmt.execute();
        }
                public void updatevuokrausvoimassaanddate(int vuokraid) throws SQLException {
            String query = "update vuokraus set voimassa = 0, loppupvm = now() where id = ?";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt(1, vuokraid);
       preparedStmt.execute();
        }
        
        public float getPoytaHinta(int poytaid) throws SQLException {
             rs=stmt.executeQuery("select hinta from poyta where poytaid = " + poytaid); 
             float poyta = 0;
             while (rs.next()) {
                 poyta = rs.getFloat("hinta");
             }
             
             return poyta;
        }
        
        public void updateVoimassa(int poytaid) throws SQLException {
           
                    
           String query = "update vuokraus set voimassa = 0 where poytaid = ?";
        
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt(1, poytaid);
      
        preparedStmt.execute();
        }
        
       public String[] getallfromvuokraus(int poytaid) throws SQLException {
          String kaikki[] = new String[9];
          
              rs=stmt.executeQuery("select * from vuokraus where voimassa = 1 and vuokraus.poytaid =" + poytaid);  
             
             while(rs.next()) {
                 kaikki[0] = rs.getString("id");
                 kaikki[1] = rs.getString("alkupvm");
                 kaikki[2] = rs.getString("loppupvm");
                 kaikki[3] = rs.getString("alepvm");
                 kaikki[4] = rs.getString("customerid");
                 kaikki[5] = rs.getString("vuokrahinta");
                 kaikki[6] = rs.getString("vuokrasta");
                 kaikki[7] = rs.getString("voimassa");
                 kaikki[8] = rs.getString("poytaid");
             }
          
          return kaikki;
}
       
       
       public int getKaikkiloppupvmcount() throws SQLException {
           int luku = 0;
            rs=stmt.executeQuery("select * from vuokraus v where TIMESTAMP(v.loppupvm) <= NOW() and voimassa = 1");  
            
            while(rs.next()) {
                luku ++;
            }
            
           
           return luku;
       }
       public int[] getKaikkiloppupvm() throws SQLException {
          
           int kaikki[] = new int[3];
            rs=stmt.executeQuery("select poytaid, id from vuokraus v where TIMESTAMP(v.loppupvm) <= NOW() and voimassa = 1");  
           
            
           
          while(rs.next()) {
             
              kaikki[0] = rs.getInt("id");
              kaikki[1] = rs.getInt("poytaid");
               
          }
             
          
          return kaikki;
       }
       
      public DefaultTableModel paivanmyynnit(DefaultTableModel model) throws SQLException {
        this.model = model;
        String adding[] = new String[3];
  
          rs=stmt.executeQuery("select myyjaid, firstname, lastname, email, sum(summa) from myynti inner join customers on myynti.myyjaid where kuitattu = 1 and customers.id = myynti.myyjaid and pvm = curdate() group by myyjaid;");
                  

        
        while (rs.next()) {
              adding[0] = "" + rs.getString("myyjaid") + " " + rs.getString("firstname") + " " + rs.getString("lastname");
              adding[1] = "" + rs.getString("email");
              adding[2] = "" + rs.getFloat("sum(summa)");
              model.addRow(adding);
        }
        return model;
      }
      
      public float paivanmyynnityhteensa() throws SQLException {
          float sales = 0;
            rs=stmt.executeQuery("select sum(summa) from myynti where kuitattu = 1 and pvm = curdate();");
            while (rs.next()) {
                sales = rs.getFloat("sum(summa)");
            }
            
          return sales;
      }
      
  public DefaultTableModel getAlldaysmyynnit(DefaultTableModel model) throws SQLException {
         this.model = model;
        String adding[] = new String[3];
    rs=stmt.executeQuery("select myyjaid, firstname, lastname, email, sum(summa) from myynti inner join customers on myynti.myyjaid where kuitattu = 1 and customers.id = myynti.myyjaid group by myyjaid;");
    
       while (rs.next()) {
              adding[0] = "" + rs.getString("myyjaid") + " " + rs.getString("firstname") + " " + rs.getString("lastname");
              adding[1] = "" + rs.getString("email");
              adding[2] = "" + rs.getFloat("sum(summa)");
              model.addRow(adding);
        }
        return model;
  }  
        public float kaikkisummamyynnityhteensa() throws SQLException {
          float sales = 0;
            rs=stmt.executeQuery("select sum(summa) from myynti where kuitattu = 1;");
            while (rs.next()) {
                sales = rs.getFloat("sum(summa)");
            }
            
          return sales;
      }
        
          public DefaultTableModel cardrescondraall(DefaultTableModel model, String eka, String toka) throws SQLException, ParseException {
         this.model = model;
          
        String adding[] = new String[6];
    rs=stmt.executeQuery("select * from myynti left join kuitti on myynti.id where kuitti.myyntiid = myynti.id and pvm <=" + eka + " and pvm >=" + toka + " and kuitattu = 1;");
    
       while (rs.next()) {
             adding[0] = rs.getString("kuittiid");
             adding[1] = rs.getString("poytaid");
             
             if (rs.getInt("pankkikortti") == 0) {
                 adding[2] = "Käteinen";
             }
             else if (rs.getInt("pankkikortti") == 1) {
                 adding[2] = "Pankkikortti";
             }
            
             
              date = format.parse(rs.getString("pvm"));
              String pvm = df.format(date);
              
              adding[3] = pvm;
             adding[4] = rs.getString("aika");
             adding[5] = rs.getString("summa");
             
             model.addRow(adding);
            
        }
        return model;
  }  
          public float rescondraallsumma(String eka, String toka) throws SQLException {
              float palautus = 0;
                  rs=stmt.executeQuery("select sum(summa) from myynti left join kuitti on myynti.id where kuitti.myyntiid = myynti.id and pvm <=" + eka + " and pvm >=" + toka + " and kuitattu = 1;");
                  
                  while (rs.next()) {
                      palautus = rs.getFloat("sum(summa)");
                  }
              return palautus;
          }
                    public float rescondkateinensumma(String eka, String toka) throws SQLException {
              float palautus = 0;
                  rs=stmt.executeQuery("select sum(summa) from myynti left join kuitti on myynti.id where kuitti.myyntiid = myynti.id and pvm <=" + eka + " and pvm >=" + toka + " and kuitattu = 1 and kateinen = 1;");
                  
                  while (rs.next()) {
                      palautus = rs.getFloat("sum(summa)");
                  }
              return palautus;
          }
                                        public float rescondkorttisumma(String eka, String toka) throws SQLException {
              float palautus = 0;
                  rs=stmt.executeQuery("select sum(summa) from myynti left join kuitti on myynti.id where kuitti.myyntiid = myynti.id and pvm <=" + eka + " and pvm >=" + toka + " and kuitattu = 1 and pankkikortti = 1;");
                  
                  while (rs.next()) {
                      palautus = rs.getFloat("sum(summa)");
                  }
              return palautus;
          }
                    public DefaultTableModel cardresconrakateinen(DefaultTableModel model, String eka, String toka) throws SQLException, ParseException {
         this.model = model;
          
        String adding[] = new String[6];
    rs=stmt.executeQuery("select * from myynti left join kuitti on myynti.id where kuitti.myyntiid = myynti.id and pvm <=" + eka + " and pvm >=" + toka + " and kuitattu = 1 and kateinen = 1;");
    
       while (rs.next()) {
             adding[0] = rs.getString("kuittiid");
             adding[1] = rs.getString("poytaid");
             
             if (rs.getInt("pankkikortti") == 0) {
                 adding[2] = "Käteinen";
             }
             else if (rs.getInt("pankkikortti") == 1) {
                 adding[2] = "Pankkikortti";
             }
            
             
              date = format.parse(rs.getString("pvm"));
              String pvm = df.format(date);
              
              adding[3] = pvm;
             adding[4] = rs.getString("aika");
             adding[5] = rs.getString("summa");
             
             model.addRow(adding);
            
        }
        return model;
  }  
                                        public DefaultTableModel cardresconrapankki(DefaultTableModel model, String eka, String toka) throws SQLException, ParseException {
         this.model = model;
          
        String adding[] = new String[6];
    rs=stmt.executeQuery("select * from myynti left join kuitti on myynti.id where kuitti.myyntiid = myynti.id and pvm <=" + eka + " and pvm >=" + toka + " and kuitattu = 1 and pankkikortti = 1;");
    
       while (rs.next()) {
             adding[0] = rs.getString("kuittiid");
             adding[1] = rs.getString("poytaid");
             
             if (rs.getInt("pankkikortti") == 0) {
                 adding[2] = "Käteinen";
             }
             else if (rs.getInt("pankkikortti") == 1) {
                 adding[2] = "Pankkikortti";
             }
            
             
              date = format.parse(rs.getString("pvm"));
              String pvm = df.format(date);
              
              adding[3] = pvm;
             adding[4] = rs.getString("aika");
             adding[5] = rs.getString("summa");
             
             model.addRow(adding);
            
        }
        return model;
  }  
         public DefaultTableModel maksamattomat(DefaultTableModel model) throws SQLException, ParseException {
         this.model = model;
          String formataan;
        String adding[] = new String[4];
    rs=stmt.executeQuery("select *, sum(summa) from myynti left join customers on myynti.myyjaid where customers.id = myynti.myyjaid and kuitattu = 1 and maksettu = 0 and myynti.myyjaid > 1 group by myynti.myyjaid;");
    
       while (rs.next()) {
           adding[0] = rs.getString("myyjaid"); 
             adding[1] =  rs.getString("firstname") + " " + rs.getString("lastname");
            
             
             adding[2] = "" + rs.getFloat("sum(summa)") + "e";
             adding[3] = "Paina tarkastellaksesi";
             
            
             
             model.addRow(adding);
            
        }
        return model;
  }  
         public DefaultTableModel maksamattomatyksihenkilo(DefaultTableModel model, int myyjaid) throws SQLException, ParseException {
         this.model = model;
          
        String adding[] = new String[7];
    rs=stmt.executeQuery("select * from myynti left join customers on myynti.myyjaid left join kuitti on myynti.id where customers.id = myynti.myyjaid and kuitattu = 1 and kuitti.myyntiid = myynti.id and maksettu = 0 and myynti.myyjaid = " + myyjaid);
    
       while (rs.next()) {
             adding[0] = rs.getString("kuittiid");
             adding[1] = rs.getString("poytaid");
             
             if (rs.getInt("pankkikortti") == 0) {
                 adding[2] = "Käteinen";
             }
             else if (rs.getInt("pankkikortti") == 1) {
                 adding[2] = "Pankkikortti";
             }
            
             
              date = format.parse(rs.getString("pvm"));
              String pvm = df.format(date);
              
              adding[3] = pvm;
             adding[4] = rs.getString("aika");
             adding[5] = rs.getString("seuranta");
             adding[6] = "" + rs.getFloat("summa") + "e";
             
             model.addRow(adding);
            
        }
        return model;
        
        
  }  
                 public void asetamaksettu(int myyjaid) throws SQLException {
                  String query = "update myynti set maksettu = 1 where myyjaid = (?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, myyjaid);

    
      preparedStmt.execute();
        }
                          public DefaultTableModel maksetut(DefaultTableModel model) throws SQLException, ParseException {
         this.model = model;
          String formataan;
        String adding[] = new String[4];
    rs=stmt.executeQuery("select *, sum(summa) from myynti left join customers on myynti.myyjaid where customers.id = myynti.myyjaid and kuitattu = 1 and maksettu = 1 group by myynti.myyjaid;");
    
       while (rs.next()) {
           adding[0] = rs.getString("myyjaid"); 
             adding[1] =  rs.getString("firstname") + " " + rs.getString("lastname");
            
             
             adding[2] = "" + rs.getFloat("sum(summa)") + "e";
             adding[3] = "Paina tarkastellaksesi";
             
            
             
             model.addRow(adding);
            
        }
        return model;
  }  
                                   public DefaultTableModel maksettutyksihenkilo(DefaultTableModel model, int myyjaid) throws SQLException, ParseException {
         this.model = model;
          
        String adding[] = new String[7];
    rs=stmt.executeQuery("select * from myynti left join customers on myynti.myyjaid left join kuitti on myynti.id where customers.id = myynti.myyjaid and kuitattu = 1 and kuitti.myyntiid = myynti.id and maksettu = 1 and myynti.myyjaid = " + myyjaid);
    
       while (rs.next()) {
             adding[0] = rs.getString("kuittiid");
             adding[1] = rs.getString("poytaid");
             
             if (rs.getInt("pankkikortti") == 0) {
                 adding[2] = "Käteinen";
             }
             else if (rs.getInt("pankkikortti") == 1) {
                 adding[2] = "Pankkikortti";
             }
            
             
              date = format.parse(rs.getString("pvm"));
              String pvm = df.format(date);
              
              adding[3] = pvm;
             adding[4] = rs.getString("aika");
             adding[5] = rs.getString("seuranta");
             adding[6] = "" + rs.getFloat("summa") + "e";
             
             model.addRow(adding);
            
        }
        return model;
        
        
  }  
                                                      public DefaultTableModel falsepays(DefaultTableModel model) throws SQLException, ParseException {
         this.model = model;
          
        String adding[] = new String[7];
    rs=stmt.executeQuery("select * from myynti left join kuitti on kuitti.myyntiid where myynti.id = kuitti.myyntiid and myyjaid = 1 and myynti.maksettu = 0;");
    
       while (rs.next()) {
             adding[0] = rs.getString("kuittiid");
             adding[1] = rs.getString("poytaid");
             
             if (rs.getInt("pankkikortti") == 0) {
                 adding[2] = "Käteinen";
             }
             else if (rs.getInt("pankkikortti") == 1) {
                 adding[2] = "Pankkikortti";
             }
            
             
              date = format.parse(rs.getString("pvm"));
              String pvm = df.format(date);
              
              adding[3] = pvm;
             adding[4] = rs.getString("aika");
             adding[5] = rs.getString("summa") + "e";
             adding[6] = rs.getString("myyntiid");
             
             model.addRow(adding);
            
        }
        return model;
  }  
              public void changefalsepay(int myyjaid,int myyntiid) throws SQLException {
                  String query = "update myynti set myyjaid = ?, maksettu = 0 where id = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, myyjaid);
       preparedStmt.setInt (2, myyntiid);

    
      preparedStmt.execute();
        }
              
              public float getfalsemyynnit() throws SQLException {
              float summa = 0;
                    rs=stmt.executeQuery("select sum(summa) from myynti where myyjaid = 1 and kuitattu = 1;");
                    
                    while (rs.next()) {
                        summa = rs.getFloat("sum(summa)");
                    }
                    
                    return summa;
              }
                            public DefaultTableModel getsinglerents(DefaultTableModel model, String customerid) throws SQLException, ParseException {
            String adding[] = new String[5];
            this.model = model;
        
         
       
            String alkpv;
            String result;
            String loppupv;
            String resultyks;
        
            rs=stmt.executeQuery("select * from vuokraus where customerid = " + customerid);  
        
        while (rs.next()) {
              adding[0] = "" + rs.getString("poytaid");
              adding[1] = "" + rs.getString("vuokrahinta");
             
              alkpv = "" + rs.getDate("alkupvm");
              if (alkpv.contains("null")) {
              result = "";
              }
              else {
              date = format.parse(rs.getString("alkupvm"));
              result = df.format(date);
              }
              loppupv = "" + rs.getDate("loppupvm");
              if (alkpv.contains("null")) {
              resultyks = "";
              }
              else {
              date = format.parse(rs.getString("loppupvm"));
              resultyks = df.format(date);
              }
              
              adding[2] = "" + result;
              adding[3] = "" + resultyks;
              adding[4] = "" + rs.getString("vuokrasta");    
              model.addRow(adding);
        }
        return model;
        
        
      

    
    
        }
          public int checkiftableexist(int poytaid) throws SQLException {
            int exists = 0;
            
                 rs=stmt.executeQuery("select * from poyta where poytaid =" + poytaid);  
                 
                 while (rs.next()) {
                     exists = rs.getInt("poytaid");
                 }
            
            return exists;
            
        }
                    public int checkifcustomerexist(int customerid) throws SQLException {
            int exists = 0;
            
                 rs=stmt.executeQuery("select * from customers where id =" + customerid);  
                 
                 while (rs.next()) {
                     exists = rs.getInt("id");
                 }
            
            return exists;
            
        }
          
          public int getsaleday(int poytaid) throws SQLException {
             int saleday = 0;
             
             rs=stmt.executeQuery("select * from vuokraus where voimassa = 1 and alepvm <= now() and poytaid =" + poytaid);  
             
             while (rs.next()) {
                 
                 if (rs.getString("alepvm").length() > 8 ) {
                 saleday = 1;
                 }
             }
             
             return saleday;

          }
          
     
          
          public String[] salaus() throws SQLException {
             String palautus[] = new String[2];
             rs=stmt.executeQuery("select * from secret where id = 1");  
               while (rs.next()) {
                 
                
                 palautus[0] = rs.getString("krypt");
                 palautus[1] = rs.getString("krypto");
                 
             }
             
             return palautus;
          }
          
          public DefaultTableModel getcalendarmodel(DefaultTableModel model, int month, int vuos) throws SQLException, ParseException {
              String palautus[] = new String[32];
              this.model = model;
              String pvm = "";
              String pvm1 = "";
              String alkpv = "";
              int monthz = month;
             rs=stmt.executeQuery("select * from poyta left join vuokraus on vuokraus.poytaid where vuokraus.poytaid = poyta.poytaid and vuokraus.voimassa = 1 ORDER BY poyta.poytaid asc"); 
             
             while (rs.next()) {
                 palautus[0] = rs.getString("poytaid");
                 
                 
              
               
              
                 myCal.set(Calendar.YEAR, vuos);
                 myCal.set(Calendar.MONTH, month);
                 myCal.set(Calendar.DAY_OF_MONTH, 31);
                theDate = myCal.getTime();
                 
                
                 
                 alkpv = "" + rs.getString("alkupvm");
                 
              if (alkpv.contains("null")) {
                 
                  for (int i = 1; i < 32; i++) {
                      
                       palautus[i] = "     0";
                  }
              }
              else {
                 date = format.parse(rs.getString("alkupvm"));
                 loppupvm = format.parse(rs.getString("loppupvm"));
                  
                 pvm = df.format(date);
             
                 pvm1 = df.format(loppupvm);
                 
                 
                 
                 
                 
                 int alkukuu = Integer.parseInt(pvm.substring(3, 5));
                 int loppukuu = Integer.parseInt(pvm1.substring(3, 5));
                 int pevm = Integer.parseInt(pvm.substring(0, 2));
                 int pevm2 = Integer.parseInt(pvm1.substring(0, 2));
                 int alkuvuos = Integer.parseInt(pvm.substring(6, 10));
                 int loppuvuos = Integer.parseInt(pvm1.substring(6, 10));
                 
                 
                  
                  loppupvm.setHours(23);
                  date.setHours(0);
               for (int i = 1; i < 32; i ++) {
                    myCal.set(Calendar.YEAR, vuos);
                 myCal.set(Calendar.MONTH, month - 1);
                 myCal.set(Calendar.DAY_OF_MONTH, i);
                 myCal.set(Calendar.HOUR, 1);
                theDate = myCal.getTime();
                   
                      if (theDate.after(date) && (theDate.before(loppupvm)) ) {
                          palautus[i] = "     X";
                      }  
                      else {
                          palautus[i] = "     0";
                      }
                      
                
                       
                   
               }
              }
               
        
                model.addRow(palautus);
                 
             }
             
             return model;
          }
          
          public Date getalkusaleday(int poytaid) {
              Date palautusdate = null;
              
              
              
              return palautusdate;
          }

    DefaultTableModel getcustomersbysearch(DefaultTableModel model, String haku) throws SQLException, ParseException {
       
                    String adding[] = new String[7];
            this.model = model;
        
            String lisayspv = "";
       
            
            rs=stmt.executeQuery("select * from customers where concat_ws(id, firstname, lastname, email, phone, memos, lisayspvm) like " + haku);  
        
        while (rs.next()) {
              adding[0] = "" + rs.getString("id");
              adding[1] = "" + rs.getString("firstname");
              adding[2] = "" + rs.getString("lastname");
              adding[3] = "" + rs.getString("email");
              adding[4] = "" + rs.getString("phone");
              adding[5] = "" + rs.getString("memos");
              lisayspv = "" + rs.getString("lisayspvm");
              
              if (lisayspv.contains("null")) {
              lisayspv = "";
              }
              else {
              date = format.parse(rs.getString("lisayspvm"));
              lisayspv = df.format(date);
              }
              adding[6] = lisayspv;
              model.addRow(adding);
        }
        return model;
        
        
        
      
    }
    
    public int checkifneedstobepaidfirst(int id) throws SQLException {
        int palautus = 0;
        
         rs=stmt.executeQuery("select count(id) from myynti where maksettu = 0 and myyjaid ="  + id);  
        
         while (rs.next()) {
             palautus = rs.getInt("count(id)");
             
         }
         
         
        return palautus;
    }
        public int checkifneedstobedropdfromtable(int id) throws SQLException {
        int palautus = 0;
        
         rs=stmt.executeQuery("select count(customerid) from vuokraus where voimassa = 1 and customerid ="  + id);  
        
         while (rs.next()) {
             palautus = rs.getInt("count(customerid)");
             
         }
         
         
        return palautus;
    }
    
    public void updatetotyhjapoytaonremovingcustomer(int id) throws SQLException {
     String query = "update myynti set myyjaid = 1 where myyjaid = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, id);
  

    
      preparedStmt.execute();
    }
        public void deletecustomer(int id) throws SQLException {
     String query = "delete from customers where id = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, id);
  

    
      preparedStmt.execute();
    }
                public void updatevuokrauksetondelete(int id) throws SQLException {
     String query = "update vuokraus set customerid = 1 where customerid = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt (1, id);
  

    
      preparedStmt.execute();
    }
                                        
}

