package finalproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.layout.HBox;

public class CustomerScreen {
    private Scene scene;
    private int points;
    private StatusState status;
    private Label statusLabel;
    private String customerName;
    private String username;
    private TableView<Book> bookTable;

    public CustomerScreen(Stage primaryStage, Scene mainPage, String username) {
        this.username = username;
        StackPane custPane = new StackPane();
        scene = new Scene(custPane, 501, 400);
        loadCustomerData();

        VBox layout = new VBox(20);
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        statusLabel = new Label(getStatusMessage());

        bookTable = new TableView<>();
        setupBookTable();
        loadBooks();

        // Buttons
        Button buyBookBtn = new Button("Buy Selected Books");
        buyBookBtn.setOnAction(e -> buyBooks());

        Button redeemPointsBtn = new Button("Redeem Points");
        redeemPointsBtn.setOnAction(e -> redeemPoints());

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> primaryStage.setScene(mainPage));

        // HBox
        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(buyBookBtn, redeemPointsBtn, logoutBtn);

        layout.getChildren().addAll(statusLabel, bookTable, buttons);
        custPane.getChildren().add(layout);
    }

    public Scene getScene() {
        return scene;
    }

    private void loadCustomerData() {
        try (Scanner scanner = new Scanner(new FileReader("customer.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length >= 3 && data[0].equals(username)) {
                    customerName = data[0];
                    points = Integer.parseInt(data[2]);
                    status = (points >= 1000) ? new GoldState() : new SilverState();
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file.");
        }
        customerName = "Customer";
        points = 0;
        status = new SilverState();
    }

    private void setupBookTable() {
        TableColumn<Book, String> nameCol = new TableColumn<>("Book Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book, Integer> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Book, CheckBox> selectCol = new TableColumn<>("Select");
        selectCol.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        nameCol.setPrefWidth(167);
        priceCol.setPrefWidth(167);
        selectCol.setPrefWidth(167);

        nameCol.setStyle("-fx-alignment: CENTER;");
        priceCol.setStyle("-fx-alignment: CENTER;");
        selectCol.setStyle("-fx-alignment: CENTER;");

        bookTable.getColumns().addAll(nameCol, priceCol, selectCol);
    }

    private void loadBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        try (Scanner scanner = new Scanner(new FileReader("books.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 2) { // Valid book line
                    books.add(new Book(data[0], Double.parseDouble(data[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file.");
        }
        bookTable.setItems(books);
    }

    private void buyBooks() {
        List<Book> selectedBooks = new ArrayList<>();
        for (Book book : bookTable.getItems()) {
            if (book.getCheckBox().isSelected()) {
                selectedBooks.add(book);
            }
        }
        if (selectedBooks.isEmpty())
            return;

        for (Book book : selectedBooks) {
            points += book.getPrice() * 10; // Earn points
        }
        status = (points >= 1000) ? new GoldState() : new SilverState();
        updateCustomerPoints();
        loadBooks(); // Refresh table
        statusLabel.setText(getStatusMessage());
    }

    private void redeemPoints() {
        List<Book> selectedBooks = new ArrayList<>();
        for (Book book : bookTable.getItems()) {
            if (book.getCheckBox().isSelected()) {
                selectedBooks.add(book);
            }
        }
        if (selectedBooks.isEmpty())
            return;

        for (Book book : selectedBooks) {
            if (points - book.getPrice() * 100 < 0)
                return;
            else
                points -= book.getPrice() * 100; // Deduct points
        }
        status = (points >= 1000) ? new GoldState() : new SilverState();
        updateCustomerPoints();
        loadBooks(); // Refresh table
        statusLabel.setText(getStatusMessage());
    }

    private void updatePoints(int delta) {
        if (points + delta < 0) {
            System.out.println("Not enough points to redeem.");
            return;
        }
        points += delta;
        status = (points >= 1000) ? new GoldState() : new SilverState();
        updateCustomerPoints();
        statusLabel.setText(getStatusMessage());
    }

    private void updateCustomerPoints() {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader("customer.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length >= 3 && data[0].equals(username)) {
                    line = data[0] + "," + data[1] + "," + points; // Update points
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error updating customer points.");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter("customer.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error writing to customer file.");
        }
    }

    private String getStatusMessage() {
        return "Welcome, " + customerName + ". You have " + points + " points, your status is "
                + status.displayStatus();
    }

    interface StatusState {
        String displayStatus();
    }

    class SilverState implements StatusState {
        @Override
        public String displayStatus() {
            return "Silver";
        }
    }

    class GoldState implements StatusState {
        @Override
        public String displayStatus() {
            return "Gold";
        }
    }
}
