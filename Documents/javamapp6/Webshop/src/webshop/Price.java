
package webshop;


public class Price {
    private int ID;
    private double value;
    
    public Price(int id, double value){
        this.ID = id;
        this.value = value;
    }
    public Price(){};
    
    public int getID(){
        return ID;
    }
    
    public double getValue(){
        return value;
    }
    
    public void setID(int id){
        this.ID = id;
    }
    
    public void setValue(double value){
        this.value = value;
    }
    
}
