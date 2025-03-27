package bookstore.app;

public interface State {
    void earnPoints(Customer customer, double amount);
    boolean redeemPoints(Customer customer, double amount);
}
