
package webshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Repository {
    
    public Repository(){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        try {
           Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshoptest2",
                "Akinyi",
                "java2msql2018");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
}
