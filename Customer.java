package bookstore.app;

public class Customer extends User{
    private int points;
    private State state;
    
    public Customer(String username, String password){
        super(username, password);
        this.points = 0;
        this.state = new Silver();
    }
    
    public int getPoints(){
        return points;
    }
    
    public String getStatus(){
        return points >= 1000 ? "Gold" : "Silver";
    }
    
    public void viewAvailableBooks(){
        
    }
    
    public void buyBook(){
        
    }
    
    public void redeemPointsandBuy(){
        
    }
    
    public void displayCustomerScreen(){
        System.out.println("Welcome " + getUsername() + ".You have " + points + " points. Your status is " + getStatus() + ".");
    }
    
    public void setState(State state){
        this.state=state;
    }
    
    public void addPoints(double amount){
        this.points += amount;
    }
    public void removePoints(double amount){
       this.points -= amount;
    }
    
    public void earnPoints(double amount){
        state.earnPoints(this, amount);
    }
    
    public boolean redeemPoints(double amount){
        return state.redeemPoints(this, amount);
    }
}
