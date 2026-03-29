package finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OwnerBookScreen {
    private Scene ownerBookScene;
    private TableView<Book> table = new TableView<>();
    private BookStore bookStore;
    private ObservableList<Book> data = FXCollections.observableArrayList();

    public OwnerBookScreen(Stage primaryStage, Scene mainpage) {
        StackPane stackPane = new StackPane();
        ownerBookScene = new Scene(stackPane, 500, 400);
        bookStore = new BookStore();

        Label title = new Label("Book Edit Screen");
        title.setFont(new Font("Arial", 20));

        TableColumn<Book, String> nameColumn = new TableColumn<>("Book Name");
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        nameColumn.setStyle("-fx-alignment: CENTER;");
        priceColumn.setStyle("-fx-alignment: CENTER;");

        nameColumn.setMinWidth(250);
        priceColumn.setMinWidth(250);

        table.getColumns().addAll(nameColumn, priceColumn);
        loadBooks();

        Label bookNameLabel = new Label("Book Name");
        Label bookPriceLabel = new Label("Price");

        TextField bookNameField = new TextField();
        TextField bookPriceField = new TextField();

        HBox bookInputFields = new HBox(15);
        bookInputFields.setSpacing(20);
        bookInputFields.setAlignment(Pos.CENTER);
        bookInputFields.getChildren().addAll(bookNameLabel, bookNameField, bookPriceLabel, bookPriceField);

        Button addButton = new Button("Add Book");
        addButton.setPrefSize(100, 50);
        addButton.setOnAction(e -> addBook(bookNameField.getText(), bookPriceField.getText()));

        Button removeButton = new Button("Remove Book");
        removeButton.setPrefSize(100, 50);
        removeButton.setOnAction(e -> removeBook());

        Button backButton = new Button("Back");
        backButton.setPrefSize(100, 50);
        backButton.setOnAction(e -> primaryStage.setScene(mainpage));

        HBox buttons = new HBox();
        buttons.setSpacing(80);
        buttons.getChildren().addAll(addButton, removeButton, backButton);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, table, bookInputFields, buttons);

        backButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        OwnerScreen os = new OwnerScreen(primaryStage, mainpage);
                        primaryStage.setScene(os.getScene());
                    }
                });

        stackPane.getChildren().addAll(layout);
        primaryStage.setScene(ownerBookScene);
    }

    private void loadBooks() {
        data.clear();
        bookStore.loadBooks(); // Load books from the BookStore
        data.setAll(bookStore.getBooks()); // Update the table with the loaded books
        table.setItems(data);
    }

    private void addBook(String name, String priceText) {
        if (name.isEmpty() || priceText.isEmpty())
            return;
        try {
            double price = Double.parseDouble(priceText);
            Book newBook = new Book(name, price);
            bookStore.addBook(newBook); // Add book through BookStore
            loadBooks(); // Reload the table after adding
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format.");
        }
    }

    private void removeBook() {
        Book selectedBook = table.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookStore.removeBook(selectedBook); // Remove book through BookStore
            loadBooks(); // Reload the table after deleting
        }
    }
}
