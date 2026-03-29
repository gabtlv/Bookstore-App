package finalproject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OwnerCustomerScreen {

    private Scene ownercust;
    private TableView<Customer> table = new TableView<>();
    private ObservableList<Customer> data = FXCollections.observableArrayList();
    private String customerFile = "customer.txt";
    private CustomerStore customerStore;

    public OwnerCustomerScreen(Stage primaryStage, Scene mainpage) {

        StackPane oc = new StackPane();
        ownercust = new Scene(oc, 501, 400);
        customerStore = new CustomerStore();

        Label title = new Label("Customer Edit Screen");
        title.setFont(new Font("Arial", 20));

        // So the user does not edit the table directly
        table.setEditable(false);

        // Setting Up Table Columns
        TableColumn<Customer, String> firstCol = new TableColumn<>("Username");
        TableColumn<Customer, String> secondCol = new TableColumn<>("Password");
        TableColumn<Customer, Integer> thirdCol = new TableColumn<>("Points");

        firstCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        secondCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        thirdCol.setCellValueFactory(new PropertyValueFactory<>("points"));

        firstCol.setStyle("-fx-alignment: CENTER;");
        secondCol.setStyle("-fx-alignment: CENTER;");
        thirdCol.setStyle("-fx-alignment: CENTER;");

        firstCol.setMinWidth(167);
        secondCol.setMinWidth(167);
        thirdCol.setMinWidth(167);

        table.getColumns().clear();
        table.getColumns().addAll(firstCol, secondCol, thirdCol);
        loadCustomers();

        // Labels
        Label user = new Label("Username");
        Label pass = new Label("Password");

        // TextField
        TextField userfield = new TextField();
        TextField passfield = new TextField();

        HBox user_pass = new HBox();
        user_pass.setSpacing(20);
        user_pass.setAlignment(Pos.CENTER);
        user_pass.getChildren().addAll(user, userfield, pass, passfield);

        // Buttons
        Button add = new Button("Add");
        add.setPrefSize(100, 100);

        Button delete = new Button("Delete");
        delete.setPrefSize(100, 100);

        Button back = new Button("Back");
        back.setPrefSize(100, 100);

        add.setOnAction(e -> addCustomer(userfield.getText(), passfield.getText()));
        delete.setOnAction(e -> deleteCustomer());

        back.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        OwnerScreen os = new OwnerScreen(primaryStage, mainpage);
                        primaryStage.setScene(os.getScene());
                    }
                });

        add.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        addCustomer(userfield.getText(), passfield.getText());
                    }
                }

        );

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(80);
        buttons.getChildren().addAll(add, delete, back);

        VBox line = new VBox(20);
        line.getChildren().addAll(title, table, user_pass, buttons);

        oc.getChildren().addAll(line);

    }

    public Scene getScene() {
        return ownercust;
    }

    private void loadCustomers() {
        data.clear();
        customerStore.loadCustomers(); // Load customers from the CustomerStore
        data.setAll(customerStore.getCustomers()); // Update the table with the loaded customers
        table.setItems(data);
    }

    private void addCustomer(String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            return;
        Customer newCustomer = new Customer(username, password, 0);
        customerStore.addCustomer(newCustomer); // Add customer through CustomerStore
        loadCustomers(); // Reload the table after adding
    }

    private void deleteCustomer() {
        Customer selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            customerStore.removeCustomer(selected); // Remove customer through CustomerStore
            loadCustomers(); // Reload the table after deleting
        }
    }

}
