package webshop;


public class Size {
    private int ID;
   private int number;
   
   public Size(int id, int number){
       this.ID = id;
       this.number = number;
   }
   public Size(){}
   
   public int getSizeID(){
       return ID;
   }
   
   public int getSizeNumber(){
       return number;
   }
   
   public void setSizeID(int id){
       this.ID = id;
       
   }
   public void setSizeNumber(int number){
       this.number = number;
   }
}
