
package webshop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class AddToCart {
    Webshop w = new Webshop();
    Customer customer = new Customer();
    List<Customer> allcustomers = new LinkedList<>();
    private Login login = new Login();
      int counter = 1;
    
      public AddToCart(){
        
    }
    
    public void addToCart(){
        
        ResultSet rs = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement thecustomerstmt = null;
        PreparedStatement allshoesstmt = null;
        Connection con = null;
        String customer = "";
        Scanner sc = new Scanner(System.in);
        ShoeOrder shoeorder = new ShoeOrder();  
         String shoenr = "", date = "", tempshoeid = "";boolean delivered = false;
         int shoeid =0;
         
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
             stmt = con.prepareStatement("select customer.Firstname from customer");
             rs = stmt.executeQuery();
             while(rs.next())
             {
                customer = rs.getString("customer.Firstname");
                System.out.println(counter + " " +  customer); 
                counter++;
             } 
            System.out.println("Select a number corresponding to your name: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            int orderid = id;
            
            w.shoesPerCategory();
            System.out.println("Select a ShoeIdentification Nr of the shoes you wish to purchase: ");
            int shoecombinationid = Integer.parseInt(sc.nextLine().trim());
            
            
            stmt = con.prepareStatement("select shoepair.ID as ShoePairID, "
            + "category.name as Category, color.name as Color from belongingto\n" +
            "inner join shoepair on shoepair.ID = belongingto.ShoePairID\n" +
            "inner join category on category.ID = belongingto.CategoryID\n" +
            "inner join color on color.id = shoepair.ColorID\n" +
            "where belongingto.ID = ?");
            stmt.setInt(1,shoecombinationid);
            rs = stmt.executeQuery();
            while(rs.next())
             {
                tempshoeid = rs.getString("ShoePairID");
                shoeid = Integer.parseInt(tempshoeid);
                
             } 
            shoeid = shoeid;
            // lägg till stmt som visar upp alla beställningarna här innan du gör beställningen
             // shows all orders made by customer 
             stmt = con.prepareStatement(" select composition.ts as Date, "
                     + "composition.NrOfShoePairs as Amount, "
                     + "belongingto.ID as ShoeCombiNr, "
                     + "category.Name as CategoryName, "
                     + "shoeorder.Ready as Ready from composition\n" +
"inner join shoeorder on composition.ShoeOrderID = shoeorder.ID\n" +
"inner join belongingto on belongingto.ShoePairID = composition.ShoeID \n" +
"inner join shoepair on shoepair.ID = composition.ShoeID \n" +
"inner join category on category.ID = belongingto.CategoryID\n" +
"inner join customer on shoeorder.CustomerID = customer.CustomerID\n" +
"where customer.CustomerID = ?\n" +
"group by Date;");
             
            stmt.setInt(1,id);
            System.out.println("Your previous orders are: ");
           rs = stmt.executeQuery();
           while(rs.next())
           {
               date = rs.getString("Date");
               int amount  = rs.getInt("Amount");
               shoecombinationid = rs.getInt("ShoeCombiNr");
               String shoe = rs.getString("CategoryName");
               delivered = rs.getBoolean("Ready");
               System.out.println("Datum: " +  date + " Amount: " + amount +
                       " ShoeIdentification Nr: " + shoecombinationid  + 
                       " Shoe: " + shoe + " Delivered: " + delivered); 
           }
           //System.out.println("");
           
           System.out.println("Confirm one of these by  selecting one ShoeIdentification Nr "
                   + " Or select number from the larger shoe list above: ");
            shoecombinationid = Integer.parseInt(sc.nextLine().trim());
                       
            stmt = con.prepareStatement("select shoepair.ID as ShoePairID, "
            + "category.name as Category, color.name as Color from belongingto\n" +
            "inner join shoepair on shoepair.ID = belongingto.ShoePairID\n" +
            "inner join category on category.ID = belongingto.CategoryID\n" +
            "inner join color on color.id = shoepair.ColorID\n" +
            "where belongingto.ID = ?");
            stmt.setInt(1,shoecombinationid);
            rs = stmt.executeQuery();
            while(rs.next())
             {
                tempshoeid = rs.getString("ShoePairID");
                shoeid = Integer.parseInt(tempshoeid);
                
             }
           stmt = con.prepareStatement("call AddToCart(?,?,?)");
            
            stmt.setInt(1,orderid) ; // orderID
            stmt.setInt(2,shoeid) ; // shoepairID should be belongingtoID
            stmt.setInt(3,id) ; // customerID
            stmt.executeUpdate();
            rs = stmt.executeQuery();
            
          
//            Map<Integer, String> shoes = new HashMap<>();
//            for(int i = 0; i<w.shoesPerCategory().size; i++)
            
            stmt = con.prepareStatement("select shoepair.ID as ShoeOrder, "
                    + "composition.ts as DateOrdered, Ready, customer.Firstname, "
                    + "customer.Surname from composition\n" +
        "inner join shoepair on shoepair.ID = composition.ShoeID \n" +
        "inner join belongingto on belongingto.shoepairID = shoepair.ID\n" +
        "inner join category on belongingto.categoryID = category.ID\n" +
        "inner join shoeorder on composition.ShoeOrderID = shoeorder.ID \n" +
        "inner join customer on customer.CustomerID = shoeorder.CustomerID\n" +
        "where customer.CustomerID = ?");
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            while(rs.next())
             {
                shoenr = rs.getString("ShoeOrder");
                date = rs.getString("DateOrdered");
               // delivered = rs.getBoolean("Ready");
              
             } 
            
           
            System.out.println(" Your order : " + shoecombinationid  + 
            ", ordered " + date + " " + "Ready " +   
            "  has been received at the warehouse.");
  
       
             
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
    
    
    public static void main (String[] args){
        
        AddToCart toCart = new AddToCart();
      
          toCart.addToCart();  
        
      }
    }
