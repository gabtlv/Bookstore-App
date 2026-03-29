package finalproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FinalProject extends Application {
    private Pane root;
    private Scene startScreen;

    @Override
    public void start(Stage primaryStage) {

        root = new Pane();

        // Creating the labels
        Label welcome = new Label("Welcome to the bookstore app");
        Label username = new Label("Username: ");
        Label password = new Label("Password: ");

        // Create the fields
        TextField user = new TextField();
        PasswordField pass = new PasswordField();

        // Button for the login
        Button btlog = new Button("Login");

        // Vertical Alignment
        VBox align = new VBox(10);
        align.setLayoutX(80);
        align.setLayoutY(40);

        // Row 1
        HBox row1 = new HBox(20);
        row1.getChildren().addAll(username, user);

        // Row 2
        HBox row2 = new HBox(20);
        row2.getChildren().addAll(password, pass);

        align.getChildren().addAll(welcome, row1, row2, btlog);

        root.getChildren().addAll(align);

        // Store the orginal screen
        startScreen = new Scene(root, 400, 200);

        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(startScreen);
        primaryStage.show();

        // When the login button is pressed
        btlog.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        String usercheck = user.getText();
                        String passcheck = pass.getText();
                        Login log = new Login(usercheck, passcheck);

                        int checkvaild = log.checkUserandPass(usercheck, passcheck);

                        if (checkvaild == 1) {
                            System.out.println("Move to admin screen");
                            // Clearing the textfields in case the user goes back
                            user.clear();
                            pass.clear();
                            // Move to the admin method for the admin scene
                            OwnerScreen ow = new OwnerScreen(primaryStage, startScreen);
                            primaryStage.setScene(ow.getScene());

                        } else if (checkvaild == 2) {
                            System.out.println("Move to customer screen");
                            // Clearing the textfields in case the user goes back
                            user.clear();
                            pass.clear();
                            // Move to the admin method for the admin scene
                            CustomerScreen cw = new CustomerScreen(primaryStage, startScreen, usercheck);
                            primaryStage.setScene(cw.getScene());
                        } else {
                            user.clear();
                            pass.clear();
                            System.out.println("BOI");
                        }
                    }
                });

        // When the user closes the application needs to write to files
        primaryStage.setOnCloseRequest(
                new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent e) {
                        System.out.println("closing");
                    }
                });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
