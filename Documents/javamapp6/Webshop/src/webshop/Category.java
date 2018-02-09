package webshop;


public class Category {
 private int ID;
 private String name;
 
 public Category(int id, String name){
     this.ID = id;
     this.name = name;
 }
 public Category(){} 
 
 public int getCategoryID(){
     return ID;
 }
 public String getCategoryName(){
     return name;
 }
  public void setID(int id){
        this.ID = id;
    }
  public void setCategoryName(String name){
        this.name = name;
    }
 
}
