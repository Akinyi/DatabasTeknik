
package webshop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class ShoesPerCategory {
    Webshop w = new Webshop();
    Customer customer = new Customer();
    ShoeOrder shoeorder = new ShoeOrder();
    Composition composition = new Composition();
    ShoePair shoes = new ShoePair();
    BelongingTo belongs = new BelongingTo();
    Price price = new Price();
    Category category = new Category();
   
    public ShoesPerCategory(){
            
       
       
   }
    
   // SHOES PER CATEGORY 
       public void shoesPerCategory(){
        //Category category = new Category();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        int counter = 1;
                              
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshoptest2",
                "Akinyi",
                "java2msql2018");
                         
            stmt = con.prepareStatement("select  belongingto.ID as ShoeCombiNr, "
                    + "category.Name as CategoryName, size.Number as 'Size', "
                    + "color.name as 'Color', label.Name as 'Label' from category\n" +
"inner join belongingto on belongingto.CategoryID = category.ID\n" +
"inner join shoepair on shoepair.id = belongingto.ShoePairID\n" +
"inner join size on shoepair.sizeid = Size.id\n" +
"inner join color on shoepair.colorid = Color.id\n" +
"inner join label on shoepair.LabelID = label.ID\n" +
"order by CategoryID;");
            
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 int combinr = rs.getInt("ShoeCombiNr");
                 String categName = rs.getString("CategoryName");
                 int size = rs.getInt("Size");
                 String color = rs.getString("Color");
                 String label = rs.getString("Label");
                 System.out.println(combinr +" " + label + "    " + categName + 
                         "   Size:" +  size  + "   " + color);
                 
             }
             
        }catch(Exception e){
            e.printStackTrace();
            }
        finally {
            if(rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
             if(con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }     
                  
       }
       
    public static void main(String[] args) {
    ShoesPerCategory shoepercat = new ShoesPerCategory();
    shoepercat.shoesPerCategory();
    
    }
}
