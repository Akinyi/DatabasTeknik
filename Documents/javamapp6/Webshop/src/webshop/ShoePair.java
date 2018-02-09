
package webshop;

import java.sql.Date;


public class ShoePair {
    private int ID;
    private int NrInWarehouse;
    private int LabelID;
    private int ColorID;
    private int SizeID;
    private int PriceID;
    
    public ShoePair(int id, int amount, int labelid, int colorid, int sizeid, int priceid){
        this.ID = id;
        this.NrInWarehouse = amount;
        this.LabelID = labelid;
        this.ColorID = colorid;
        this.SizeID = sizeid;
        this.PriceID = priceid;
        
    }
    
    public ShoePair(){}
    
    public int getID(){
        return ID;
    }
    public int getNrInWarehouse(){
        return NrInWarehouse;
    }
    public int getLabelID(){
        return LabelID;
    }
    public int getPriceID(){
        return PriceID;
    }
    public int getColorID(){
        return ColorID;
    }
    
    public int getSizeID(){
        return SizeID;
    }
    public void setID(int id){
        this.ID = id;
    }
    public void setNrInWarehouse(int amount){
        this.NrInWarehouse = amount;
    }
    public void setLabelID(int labelid){
        this.LabelID = labelid;
    }
    public void setPrice(int priceid){
        this.PriceID  = priceid;
    }
     public void setColor(int colorid){
        this.ColorID  = colorid;
    }
     public void setSize(int sizeid){
        this.SizeID  = sizeid;
    }
   
}
