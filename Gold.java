package bookstore.app;

public class Gold implements State{
    
    public void earnPoints(bookstore.app.Customer customer, double amount){
        customer.addPoints((int)(amount*10));
    }
    
    public void redeemPoints(bookstore.app.Customer customer, double amount){
        int pointsToRedeem = (int)(amount * 100);
        if (customer.getPoints() >= pointsToRedeem){
            customer.removePoints(pointsToRedeem);
        if(customer.getPoints() < 1000){
                customer.setState(new Silver());
            }
        }
    }
}
