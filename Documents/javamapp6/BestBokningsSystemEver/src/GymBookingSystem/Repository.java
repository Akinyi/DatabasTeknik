
package GymBookingSystem;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Repository {
    
// private Connection con;
 private Properties p = new Properties();
 private Login login = new Login();
    
    public Repository(){
//        ResultSet rs = null;
//        PreparedStatement stmt = null;
//        con = null;
//        try{
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GymBookingSystem/Settings.properties",
//              "Akinyi",
//              "java2msql2018");
//            p.load(new FileInputStream("jdbc:mysql://localhost:3306/GymBookingSystem/Settings.properties"));
//            Class.forName("com.mysql.jdbc.Driver");
//        }
//         catch (Exception e){
//            e.printStackTrace();
//        }
//        
             
    }
    public void getMembersTraining(String memberName){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int groupsessionID = 0;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
//        try {con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GymBookingSystem",
//                "Akinyi",
//                "java2msql2018");
      PreparedStatement memberStmt = con.prepareStatement("select member.name as 'Member',"
      + " exercisetype.name as 'Exercise',\n" + "groupsession.sessionID as 'Group Session ID',"
      + " session.scheduled as Date from groupsession\n" +
            "inner join booking on booking.groupSessionID = groupsession.ID\n" +
            "inner join member on member.ID = booking.memberID \n" +
            "inner join session on groupsession.sessionID= session.ID\n" +
            "inner join exercisetype on exercisetype.ID = "
            + "groupsession.exerciseTypeID where member.name like ? ");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                exercise = rs.getString("Exercise");
                scheduled = rs.getString("Date");
                groupsessionID = rs.getInt("Group Session ID");
                System.out.println("Member: " + membername + ". " + 
                        "Exercise: " +  exercise + ". " + "Date and Time: " + 
                        scheduled + ". GroupSession ID " + groupsessionID);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
     public void getIndividualsTraining(String memberName){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int individualsessionID = 0; boolean attendance = true;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
//        try {con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GymBookingSystem",
//                "Akinyi",
//                "java2msql2018");
      PreparedStatement memberStmt = con.prepareStatement("select  "
              + "member.name as 'Member', "
              + "individualsession.sessionID as 'Individual Session ID', "
              + "individualsession.attendance as Attendance, \n" 
              + "session.scheduled as Date from individualsession\n" +
"inner join member on member.ID = individualsession.memberID\n" +
"inner join session on individualsession.sessionID= session.ID\n" +
"where member.name like ? ");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                scheduled = rs.getString("Date");
                attendance = rs.getBoolean("Attendance");
                individualsessionID = rs.getInt("Individual Session ID");
                System.out.println(membername + "   " + 
                         scheduled + "    IndividualSessionID: " + 
                        individualsessionID + "    Attendance: " + attendance);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
     public int checkIfMemberHasIndividualTraining(String memberName){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int individualsessionID = 0, sessionID = 0; boolean attendance = true;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
//        try {con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GymBookingSystem",
//                "Akinyi",
//                "java2msql2018");
      PreparedStatement memberStmt = con.prepareStatement("select Member, SessionID from(\n" +
"select member.name as 'Member', individualsession.sessionID as 'SessionID', individualsession.attendance, \n" +
"session.scheduled as Date from individualsession\n" +
"inner join member on member.ID = individualsession.memberID\n" +
"inner join session on individualsession.sessionID = session.ID\n" +
") as ccc where Member like ? ");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                sessionID = rs.getInt("SessionID");
                System.out.println(sessionID+ "," + membername);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return sessionID;
    }
    public void getAllMembersTraining(){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int groupsessionID = 0; boolean attendance = true;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
//        try {con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GymBookingSystem",
//                "Akinyi",
//                "java2msql2018");
     PreparedStatement memberStmt = con.prepareStatement("select  "
             + "member.name as 'Member', exercisetype.name as 'Exercise',\n" +
"groupsession.sessionID as 'Group Session ID', session.scheduled as Date, "
             + "booking.attendance as Attendance from groupsession\n" +
"inner join booking on booking.groupSessionID = groupsession.ID\n" +
"inner join member on member.ID = booking.memberID \n" +
"inner join session on groupsession.sessionID= session.ID\n" +
"inner join exercisetype on exercisetype.ID = groupsession.exerciseTypeID;");
            
            //memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                exercise = rs.getString("Exercise");
                attendance = rs.getBoolean("Attendance");
                scheduled = rs.getString("Date");
                groupsessionID = rs.getInt("Group Session ID");
                    System.out.println( membername + "    " + exercise 
                    + "    " + scheduled + "     Attendance " + attendance);
                
            }
        
                         
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
     
    public void getMembersNotes(String memberName){
        ResultSet rs = null;
        Connection con = null;
        String membername = "", comment = "", trainer = "";
        
        try {con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
        PreparedStatement memberStmt = con.prepareStatement("select trainer.name as 'Trainer', "
                + "member.name as 'Member' , comment as 'Comment' from note\n" +
"inner join individualsession on individualSession.ID = note.individualSessionID\n" +
"inner join session on session.ID = individualsession.sessionID\n" +
"inner join member on individualsession.memberID = member.ID\n" +
"inner join trainer on trainer.ID = session.trainerID where member.name like ? ");
//        PreparedStatement insertNoteStmt = con.prepareStatement(
//                        "insert into note (comment) values (?)");
//        PreparedStatement updateNoteStmt = con.prepareStatement(
//                        "update note set comment = ? where note.memberID = member.ID");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                trainer = rs.getString("Trainer");
                membername = rs.getString("Member");
                comment = rs.getString("Comment");
            }
            System.out.println("Trainer " + trainer + "; Member: " + membername + "; " + 
                    "Comments: " +  comment );
             
          
            
            // rs = updateNoteStmt.executeQuery();
             
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
      
    }
     public int writeMembersNotes(String comment, int individualsessionID){
        ResultSet rs = null;
        Connection con = null;
        comment = ""; String trainer = "";
        individualsessionID = 0;
        int rows = -1;
        
        try {con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
        PreparedStatement memberStmt = con.prepareStatement("insert into note "
                + "(comment,individualSessionID ) values (?,?) ");

            memberStmt.setString(1, comment);
            memberStmt.setInt(2, individualsessionID);
            rows = memberStmt.executeUpdate();
            
//            while (rs.next()) {
//                //trainer = rs.getString("Trainer");
//               // membername = rs.getString("Member");
//               individualsessionID = rs.getInt("individualsessionID");
//                comment = rs.getString("comment");
//            }
//            System.out.println("Comments: " +  comment  + "IndividualSessionID" + individualsessionID);
             
            // rs = updateNoteStmt.executeQuery();
             
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
      return rows;
    }
     
     
     public class FindID {
    
    public int findID(String customerNr, String pnr){
        int i = 1;
        if(customerNr.equals(pnr)){
            i =1;
        }
        else 
            i=2;
        
     return i;   
    }
}
    
    public Trainer logIn(String trainerName) {
        Trainer trainer = new Trainer();
        String query = "select * from trainer where name like ?";
        try(Connection con = DriverManager.getConnection(login.connectionString, login.name, login.password);
                PreparedStatement stmt = con.prepareStatement(query);
                ){
            stmt.setString(1, trainerName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                trainer.setID(rs.getInt("ID"));
                trainer.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainer;
    }
    
    
    
}
