package finalproject;

public class Customer {
   private String username;
   private String password;
   private int points;
   
   public Customer(String username, String password, int points){
       this.username= username;
       this.password= password;
       this.points= points;
   }
   public String getUsername(){
       return this.username;
   }
   
   public String getPassword(){
       return this.password;
   }
   
   public int getPoints(){
       return this.points;
   }
   @Override
   public String toString(){
       return this.username +", "+ this.password+", "+ this.points;
   }

    public String getCustomerName() {
       return this.username;
    }

}
