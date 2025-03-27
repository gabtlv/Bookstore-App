package bookstore.app;

public interface State {
    void earnPoints(Customer customer, double amount);
    void redeemPoints(Customer customer, double amount);
}
