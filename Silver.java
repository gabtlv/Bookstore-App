package bookstore.app;

public class Silver implements State{
    
    public void earnPoints(bookstore.app.Customer customer, double amount){
        customer.addPoints((int)(amount*10));
        if(customer.getPoints() >= 1000){
            customer.setState(new Gold());
        }
    }
    
    
    public boolean redeemPoints(Customer customer, double amount){
        int pointsToRedeem = (int)amount*100;
        if (customer.getPoints() >= pointsToRedeem){
            customer.removePoints(pointsToRedeem);
            return true;
        }
        return false;
    }
}
