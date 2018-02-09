package webshop;


public class Color {
   private int ID;
   private String name;
   
   public Color(int id, String name){
       this.ID = id;
       this.name = name;
   }
   public Color(){}
   
   public int getColorID(){
       return ID;
   }
   
   public String getColorName(){
       return name;
   }
   
   public void setColorID(int id){
       this.ID = id;
       
   }
   public void setColorName(String name){
       this.name = name;
   }
   
}
