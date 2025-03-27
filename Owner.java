package bookstore.app;

public class Owner extends User{
    private BookStore bookStore;
    
    public Owner(String username, String password, BookStore bookStore){
        super(username, password);
        this.bookStore = bookStore;
    }
    public void viewBooks(){
        bookStore.displayBooks();
    }
    
    public void viewCustomers(){
        bookStore.displayCustomers();
    }
}
