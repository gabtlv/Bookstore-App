
package bookstore.app;

public class User {
    protected String username;
    protected String password;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public boolean login(String enteredUsername, String enteredPassword){
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }
    
    public void logout(){
        
    }
    
}
