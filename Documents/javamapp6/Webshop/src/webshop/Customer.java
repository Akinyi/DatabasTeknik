
package webshop;


public class Customer {
    private int customerID;
    //public timestamp ts;
    private String firstname;
    private String surname;
    private String address;
    private String addressLocation;
    
    public Customer(int customerid, String name, String surname, String address, String addresslocation){
        this.customerID = customerid;
        this.firstname = name;
        this.surname = surname;
        this.address = address;
        this.addressLocation = addresslocation;
        
    }
    public Customer(){}
    
    public int getCustomerID(){
        return customerID;
    }
    
    public void setCustomerID(int customerid){
        this.customerID = customerid;
    }
    
    public String getFirstname(){
        return firstname;
    }
    public String getSurname(){
        return surname;
    }
     public String getAddress(){
        return address;
    }
      public String getAddresslocation(){
        return addressLocation;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
     public void setSurname(String surname){
        this.surname = surname;
    }
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setAddresslocation(String addresslocation){
        this.addressLocation = addresslocation;
    }
}
