package webshop;


import java.sql.Date;


public class ShoeOrder {
    private int ID;
    private Date ts;
    private boolean Ready;
    private int customerID;
    
    public ShoeOrder(int id, Date date, boolean ready, int customerid){
        this.ID = id;
        this.ts = date;
        this.Ready = ready;
        this.customerID = customerid;
        
    }
    
    public ShoeOrder(){}
    
    public int getID(){
        return ID;
    }
    public Date getDate(){
        return ts;
    }
    public boolean getReady(){
        return Ready;
    }
    public int getCustomerID(){
        return customerID;
    }
    public void setID(int id){
        this.ID = id;
    }
    public void setReady(boolean ready){
        this.Ready = ready;
    }
    public void setCustomerID(int customerid){
        this.customerID = customerid;
    }
    
}
