/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Miko ja Nastia
 */
public class MakingListModels {
    String adding[];
    private TableModel model;
    String[] hadding;
   
    public MakingListModels() {
       
        
        
    }
    
    public DefaultListModel getoneModel(ArrayList List) {
       
        DefaultListModel model = new DefaultListModel();
        
        for (int i = 0; i < List.size(); i++) {
           
            
            model.addElement(List.get(i));
            
    
        }
        
    return model;
    }
     public DefaultListModel getmanyModel(ArrayList List, ArrayList List1, ArrayList List2) {
       String yhdistetyt = "";
        DefaultListModel model = new DefaultListModel();
        
        for (int i = 0; i < List.size(); i++) {
           
            yhdistetyt = List.get(i).toString() + " " + List1.get(i).toString() + " " + List2.get(i).toString();
            model.addElement(yhdistetyt);
            
    
        }
        
    return model;
    }
     
       public DefaultTableModel getmanyModelli(DefaultTableModel model, ArrayList List, ArrayList List1, ArrayList List2) {
       String yhdistetyt = "";
        
  
       adding = new String[3];
       
       hadding = new String[List.size()];
       
      this.model = model;
      
      
       
       for (int i = 0; i<List.size(); i++) {
             adding[0] = List.get(i).toString();
      adding[1] = List1.get(i).toString();
      adding[2] = List2.get(i).toString();
       model.addRow(adding);
       }
        
        
    return model;
    }
       public DefaultTableModel getmanytablemodelli(DefaultTableModel model, ArrayList List, ArrayList List1, ArrayList List2, ArrayList List3, ArrayList List4) {
       String yhdistetyt = "";
        

  
       adding = new String[5];
       
       
       
      this.model = model;
      
      
       
       for (int i = 0; i<List.size(); i++) {
             adding[0] = List.get(i).toString();
      adding[1] = List1.get(i).toString();
      adding[2] = List2.get(i).toString();
      adding[3] = List3.get(i).toString();
      adding[4] = List4.get(i).toString();
       model.addRow(adding);
       }
        
        
    return model;
    }
       public DefaultTableModel getmanyModelli2(DefaultTableModel model, ArrayList List, ArrayList List1) {
      
        

  
       adding = new String[2];
       
       hadding = new String[List.size()];
       
      this.model = model;
      
      
       
       for (int i = 0; i<List.size(); i++) {
             adding[0] = List.get(i).toString();
      adding[1] = List1.get(i).toString();
     
       model.addRow(adding);
       }
        
        
    return model;
    }
         public DefaultTableModel getmanytablemodelli4(DefaultTableModel model, ArrayList List, ArrayList List1, ArrayList List2, ArrayList List3) {
       String yhdistetyt = "";
        

  
       adding = new String[4];
       
       
       
      this.model = model;
      
      
       
       for (int i = 0; i<List.size(); i++) {
             adding[0] = List.get(i).toString();
      adding[1] = List1.get(i).toString();
      adding[2] = List2.get(i).toString();
      adding[3] = List3.get(i).toString();
       model.addRow(adding);
       }
        
        
    return model;
    }
    
}
