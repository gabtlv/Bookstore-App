package bookstore.app;

public class Customer extends User{
    private int points;
    private State state;
    
    public Customer(String username, String password){
        super(username, password);
        this.points = 0;
        //this.state = new Silver();
    }
    
    public int getPoints(){
        return points;
    }
    
    public void viewAvailableBooks(){
        
    }
    
    public void buyBook(){
        
    }
    
    public void redeemPointsandBuy(){
        
    }
    
    public void setState(State state){
        this.state=state;
    }
    
    public void addPoints(double amount){
        this.points += points;
    }
    public void removePoints(double amount){
       this.points -= points;
    }
    
    public void earnPoints(double amount){
        state.earnPoints(this, amount);
    }
    
    public void redeemPoints(double amount){
        state.redeemPoints(this, amount);
    }
}
