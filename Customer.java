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
    
    public void viewAvailableBooks(BookStore store){
        store.displayBooks();
    }
    
    public void buyBook(BookStore store, String bookTitle){
        boolean isBuyable = store.buyBook(this, bookTitle);
        if (isBuyable) {
        	earnPoints(1);
        	System.out.println("You Have Succesfully Purchased This Book: " + bookTitle);
        }
        else {
        	System.out.println("Error: Book Purchase Could Not Be Processed.");
        }
        
    }
    
    public void redeemPointsandBuy(BookStore store, String bookTitle, double amount){
        int bookPriceInPoints = (int)(amount * 100);
    	boolean isRedeemable = store.redeemPointsAndBuy(this, bookTitle, bookPriceInPoints);
        if (isRedeemable) {
        	System.out.println("You Have Succesfully Redeemed Points For This Book: " + bookTitle);
        	System.out.println("Your Updated Points Balance Is: " + getPoints());
        }
        else if (amount * 100 > getPoints()){				// Calculates how many points are needed left
        	System.out.println("You Do Not Have Enough Points (Missing " + (bookPriceInPoints - getPoints()) + " Points.");
        }
        else {
        	System.out.println("Error: Points Redemption Could Not Be Processed.");
        }
        
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
