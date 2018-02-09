package webshop;


public class Composition {
    private int ID;
   // public timestamp ts;
    private int NrOfShoePairs;
    private int ShoeID;
    private int ShoeOrderID;
   
    public Composition(int id, int nrpairs, int shoeid, int shoeorderID){
        this.ID = id;
        this.NrOfShoePairs = nrpairs;
        this.ShoeID = shoeid;
        this.ShoeOrderID = shoeorderID;
        
    }
    public Composition(){}
    
    public int getID(){
        return ID;
    }
    
    public void setID(int id){
        this.ID = id;
    }
    
    public int getNrOfShoePairs(){
        return NrOfShoePairs;
    }
    public int getShoeID(){
        return ShoeID;
    }
    
    public void setShoeID(int shoeid){
        this.ShoeID = shoeid;
    }
    public int getShoeOrderID(){
        return ShoeOrderID;
    }
    public void setShoeOrderID(int shoeorderID){
        this.ShoeOrderID = shoeorderID;
    }
    
    
}
