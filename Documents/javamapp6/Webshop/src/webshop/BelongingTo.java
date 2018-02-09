
package webshop;


public class BelongingTo {
    private int ID;
    private int ShoePairID;
    private int CategoryID;
   
    public BelongingTo(int id, int shoepairid, int categoryid){
        this.ID = id;
        this.ShoePairID = shoepairid;
        this.CategoryID = categoryid;
        
        
    }
    public BelongingTo(){}
    
    public int getID(){
        return ID;
    }
    
    public int getShoePairID(){
        return ShoePairID;
    }
    public int getCategoryID(){
        return CategoryID;
    }
     public void setID(int id){
        this.ID = id;
    }
    public void setShoePairID(int shoeid){
        this.ShoePairID = shoeid;
    }
   
    public void setCategoryID(int categoryID){
        this.CategoryID = categoryID;
    }
    
}
