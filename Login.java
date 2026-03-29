package finalproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    private String username;
    private String password;
    ArrayList<String> user = new ArrayList<>();
    ArrayList<String> pass = new ArrayList<>();

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int checkUserandPass(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            // ADMIN
            return 1;
        } else {
            // File Reading for Customer.txt
            try {
                FileReader custread = new FileReader("customer.txt");
                Scanner scan = new Scanner(custread);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] section = line.split(",");
                    if (section.length >= 2) {
                        user.add(section[0]);
                        pass.add(section[1]);

                    }
                }
                scan.close();
                custread.close();
                for (int i = 0; i < user.size(); i++) {
                    if ((user.get(i).equals(username)) && (pass.get(i).equals(password))) {
                        return 2;
                    }
                }
            } catch (IOException e) {
                System.out.println("FILE NOT FOUND");
            }
            return 0;
        }

    }

}
