package bookstore.app;

public class Gold implements State{
    
    public void earnPoints(bookstore.app.Customer customer, double amount){
        customer.addPoints((int)(amount*10));
    }
    
    public boolean redeemPoints(Customer customer, double amount) {
        int pointsToRedeem = (int) (amount * 100);
        
        if (customer.getPoints() >= pointsToRedeem) {
            customer.removePoints(pointsToRedeem);

            // Change state if points drop below 1000
            if (customer.getPoints() < 1000) {
                customer.setState(new Silver());
            }
            
            return true;
        }
        return false; 
    }
}
