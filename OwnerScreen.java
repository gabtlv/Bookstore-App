package finalproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OwnerScreen {
  private Scene scene;

  public OwnerScreen(Stage primaryStage, Scene mainpage) {
    StackPane adPane = new StackPane();

    scene = new Scene(adPane, 400, 250);

    // Create the buttons
    Button books = new Button("Books");
    books.setPrefSize(150, 50);
    books.setStyle("-fx-font-size: 18px;");

    Button customer = new Button("Customer");
    customer.setPrefSize(150, 50);
    customer.setStyle("-fx-font-size: 18px;");

    Button logout = new Button("Logout");
    logout.setPrefSize(150, 50);
    logout.setStyle("-fx-font-size: 18px;");

    VBox admin_align = new VBox(30);
    admin_align.setAlignment(Pos.CENTER);
    admin_align.getChildren().addAll(books, customer, logout);

    adPane.getChildren().addAll(admin_align);

    // If the owner presses the books
    books.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            OwnerBookScreen obs = new OwnerBookScreen(primaryStage, mainpage);
          }
        });
    // If the owner presses the customer
    customer.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            System.out.println("You print");
            OwnerCustomerScreen ocs = new OwnerCustomerScreen(primaryStage, mainpage);
            primaryStage.setScene(ocs.getScene());
          }
        });
    // If the owner presses the logout move back to the startscreen
    logout.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            primaryStage.setScene(mainpage);
          }
        });
  }

  public Scene getScene() {
    return scene;
  }

}
