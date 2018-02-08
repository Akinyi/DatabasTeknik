
package GymBookingSystem;

import java.util.Scanner;
import javax.swing.JOptionPane;


public class GymBookingSystem {
 Repository r = new Repository();
 Trainer myAccount;
 Scanner scanner;
    int individualsessionID =0; String comment = "", snr = "", wholeline = ""; String[] snrAndName;
 GymBookingSystem(){
     scanner = new Scanner(System.in);
//     String name = "";
//      
//      while(true){
//            name = JOptionPane.showInputDialog("Name of member: ");
//            name = name.trim();
//            
//            if (name.equalsIgnoreCase("name")){
//                System.exit(0);
//            }
//           r.getMembersTraining(name);
//          // r.getMembersNotes(name);
//            break;
//        }
      // r.getAllMembersTraining(); Kolla detta igen
      
    }
 
 public void start(){
     System.out.println("What's your name?");
     login(scanner.nextLine());
     System.out.println("Choose an activity");
     System.out.println("Get a Members Group training [1]" + 
             "\nGetMembersNotes [2]" + 
             "\nSee a member's Individuals training [3]" + 
             "\nSee all members' trainings [4]" +
             "\nWrite notes of a member [5]");
     String choice = scanner.nextLine();
     switch(choice){
         case "1":
             System.out.println("Name of member: ");
             String name = scanner.nextLine().trim();
             r.getMembersTraining(name);
             break;
         case "2":
             System.out.println("Name of member: ");
             name = scanner.nextLine().trim();
             r.getMembersNotes(name);
         case "3": 
             System.out.println("Name of member: ");
             name = scanner.nextLine().trim();
             r.getIndividualsTraining(name);
             break;
         case "4":
             r.getAllMembersTraining();
             break;
         case "5": //INCOMPLETE
             System.out.println("Name of member: ");
             name = scanner.nextLine().trim();
            individualsessionID = r.checkIfMemberHasIndividualTraining(name);
//             while(scanner.hasNext())
//             wholeline = scanner.nextLine(); // cannot read the line****
//             snrAndName = wholeline.split(","); // Splitar raden med komma till två strings
//             snr = snrAndName[0].trim(); // individualsessionID
//             individualsessionID = Integer.parseInt(snr);
//             System.out.println(individualsessionID);
             if(individualsessionID > 0 ){ 
               System.out.println("Write your comment here: ");
                 comment = scanner.nextLine();
                r.writeMembersNotes(comment, individualsessionID);
                 System.out.println("Comment successfully entered into note");
             }
             break;
     }
 }
 
 public void login(String trainerName){
     myAccount = r.logIn(trainerName);
 }
    
    
    public static void main(String[] args) throws NullPointerException {
        GymBookingSystem best = new GymBookingSystem();
       best.start();
    }
    
}
