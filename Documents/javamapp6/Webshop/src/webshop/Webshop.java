
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


public class Webshop {
   List<Customer> allcustomers = new LinkedList<>();
    List<Price> totalprice = new LinkedList<>();
    List<Category> categories = new LinkedList<>();
    List<BelongingTo> belongings = new LinkedList<>();
    Customer customer = new Customer();
    ShoeOrder shoeorder = new ShoeOrder();
    Composition composition = new Composition();
    ShoePair shoes = new ShoePair();
    BelongingTo belongs = new BelongingTo();
    Price price = new Price();
    Category category = new Category();
    Color color = new Color();
    Size size = new Size();
    private Login login = new Login();
    
    public Webshop(){
       
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
             con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
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
       
       
       
    // Metod Customers and Expenses
    public void getCustomersExpenses(){
         String s = JOptionPane.showInputDialog(" Ange KundID: ");
        
        int ordernr = 0, shoeid = 0, priceid = 0,amount = 0;double price =0; 
        int custid =0;
        boolean again = true;
        while(again){
            
        if (s !=null) {
           int si = Integer.parseInt(s);  
           System.out.println("KundID: "  + findCustomer(si).getCustomerID() + " " 
          + findCustomer(si).getFirstname() + " " + findCustomer(si).getSurname()); 
          ordernr = findOrder(si).getID();
          shoeid = findComposition(ordernr).getShoeID();
          priceid = getPriceID(shoeid).getPriceID();
          amount = findComposition(ordernr).getNrOfShoePairs();
          price = getPriceValue(priceid).getValue();
          price = price * amount;
          //totalprice.add(price);
          System.out.println("Totala beställningsvärde: " +  price);
          break;
        }
        else if(s == null)
           try{     
        allcustomers = findAllCustomers();
        
        for(Customer c: allcustomers){
          custid =  c.getCustomerID();
          ordernr = findOrder(custid).getID();
          shoeid = findComposition(ordernr).getShoeID();
          amount = findComposition(ordernr).getNrOfShoePairs();
          priceid = getPriceID(shoeid).getPriceID();
          price = getPriceValue(priceid).getValue();
          price = price * amount;
          System.out.println("Kund: " + c.getFirstname() + " " + c.getSurname());
          System.out.println("Totala beställningsvärde: " +  price);
         } break;
        }catch(NumberFormatException e){System.out.println("Invalid input" 
                + e.getMessage());}
         continue;
       }
        
    }
    public List<Category> findAllCategories(){
        
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
                     
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM category");
            rs = stmt.executeQuery();
           
              while(rs.next())
             {
                 category =  new Category(rs.getInt("ID"), 
                         rs.getString("Name"));
                 categories.add(category);
                 
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
        return categories;
    }
    public Customer findCustomer(int cusID){
        Customer customer = new Customer();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        
                       
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM customer WHERE CustomerID = ?");
            stmt.setInt(1,cusID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 customer =  new Customer (rs.getInt("CustomerID"), 
                         rs.getString("Firstname"), rs.getString("Surname"), 
                         rs.getString("Address"), rs.getString("AddressLocation"));

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
        return customer;
    }
    
    public List<Customer> findAllCustomers(){
        Customer customer = new Customer();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
                     
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM customer");
            rs = stmt.executeQuery();
           
              while(rs.next())
             {
                 customer =  new Customer (rs.getInt("CustomerID"), 
                         rs.getString("Firstname"), rs.getString("Surname"), 
                         rs.getString("Address"), rs.getString("AddressLocation"));
                 allcustomers.add(customer);
                 
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
        return allcustomers;
    }
    
     public ShoeOrder findOrder(int cusID){
        ShoeOrder order = new ShoeOrder();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
                              
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
            stmt = con.prepareStatement("SELECT * FROM shoeorder WHERE CustomerID = ?");
            stmt.setInt(1,cusID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 order =  new ShoeOrder (rs.getInt("ID"), rs.getDate("ts"),
                         rs.getBoolean("Ready"), rs.getInt("CustomerID"));

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
        return order;
    }
    public Composition findComposition(int shoeorderID){
        Composition composition = new Composition();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        
                       
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM composition WHERE ShoeOrderID = ?");
            stmt.setInt(1,shoeorderID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 composition =  new Composition (rs.getInt("ID"), rs.getInt("NrOfShoePairs"), 
                         rs.getInt("ShoeID"), rs.getInt("ShoeOrderID"));

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
        return composition;
    }
    
     public BelongingTo getShoeAndCategory(int categoryID){
        BelongingTo belonging = new BelongingTo();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        
                       
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM belongingto WHERE CategoryID = ?");
            stmt.setInt(1,categoryID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 belonging =  new BelongingTo (rs.getInt("ID"), rs.getInt("ShoePairID"), rs.getInt("CategoryID"));
                 belongings.add(belonging);
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
        return belonging;
    }
     public ShoePair getPriceID(int shoeid){
        ShoePair shoes = new ShoePair();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        
                       
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM ShoePair WHERE ID = ?");
            stmt.setInt(1,shoeid) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 shoes =  new ShoePair (rs.getInt("ID"), rs.getInt("NrInWarehouse"), rs.getInt("LabelID"), rs.getInt("ColorID"),rs.getInt("SizeID"),rs.getInt("PriceID"));

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
        return shoes;
    }
     public Price getPriceValue(int priceID){
        Price value = new Price();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        
                       
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM Price WHERE ID = ?");
            stmt.setInt(1,priceID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 value =  new Price (rs.getInt("ID"), rs.getDouble("Value"));
                 totalprice.add(value);
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
        return value;
    }
      public Category findCategory(int ID){
        Category category = new Category();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
                              
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM category WHERE ID = ?");
            stmt.setInt(1,ID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 category =  new Category (rs.getInt("ID"), rs.getString("Name"));
                 categories.add(category);
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
        return category;
    }
      public Color findColor(int colorID){
        Color color = new Color();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
                              
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM color WHERE ID = ?");
            stmt.setInt(1,colorID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 color =  new Color (rs.getInt("ID"), rs.getString("Name"));

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
        return color;
    }
      public Size findSize(int sizeID){
        Size size = new Size();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
                              
        try{ 
          
             Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
                         
            stmt = con.prepareStatement("SELECT * FROM size WHERE ID = ?");
            stmt.setInt(1,sizeID) ;
            rs = stmt.executeQuery();
              while(rs.next())
             {
                 size =  new Size (rs.getInt("ID"), rs.getInt("Number"));

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
        return size;
    }
    public static void main(String[] args) {
        Webshop w = new Webshop();
        w.shoesPerCategory();
        //w.getCustomersExpenses();
     
        
    }
    
}
