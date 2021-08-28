package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {

    Stage stage;
    Parent scene;
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();
    Product product = new Product(0, null, 0,0,0,100);


    @FXML
    private TextField addProductIDTxt;

    @FXML
    private TextField addProductNameTxt;

    @FXML
    private TextField addProductInvTxt;

    @FXML
    private TextField addProductPriceTxt;

    @FXML
    private TextField addProductMaxTxt;

    @FXML
    private TextField addProductMinTxt;

    @FXML
    private TextField addProductSearchTxt;

    @FXML
    private TableView<Part> addProductAddTableView;

    @FXML
    private TableColumn<Part, Integer> addPartIDCol;

    @FXML
    private TableColumn<Part, String> addPartNameCol;

    @FXML
    private TableColumn<Part, Integer> addInvLvlCol;

    @FXML
    private TableColumn<Part, Double> addPriceCol;

    @FXML
    private TableView<Part> addProductRemoveTableView;

    @FXML
    private TableColumn<Part, Integer> removePartIDCol;

    @FXML
    private TableColumn<Part, String> removePartNameCol;

    @FXML
    private TableColumn<Part, Integer> removeInvLvlCol;

    @FXML
    private TableColumn<Part, Double> removePriceCol;

    @FXML
    private Button addProductSearchParts;

    /** searches top table for parts */

    @FXML
    void onSearchParts(ActionEvent event) {

        String str = addProductSearchTxt.getText();
        if (!str.isBlank()) {
            try {
                int i = Integer.parseInt(addProductSearchTxt.getText());
                Part part = Inventory.lookupPart(i);
                if(part == null) {
                    throw new NumberFormatException();
                }
                addProductAddTableView.getSelectionModel().select(part);

            } catch (NumberFormatException e) {
                ObservableList<Part> list = Inventory.lookupPart(str);
                if(list.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Part Search");
                    alert.setHeaderText(null);
                    alert.setContentText("Can't find part with that info");

                    alert.showAndWait();
                    addProductAddTableView.setItems(Inventory.getAllParts());
                } else {
                    addProductAddTableView.setItems(list);
                }
            }
        } else {
            addProductAddTableView.setItems(Inventory.getAllParts());
        }

    }

    /** adds parts to the associated parts table */

    @FXML
    void onActionAddPart(ActionEvent event) {

        Part p = addProductAddTableView.getSelectionModel().getSelectedItem();
        assocParts.add(p);

    }

    /** cancels and goes back to the main menu */

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** removes parts from the associated parts table */

    @FXML
    void onActionRemovePart(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove associated part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Part delPart = addProductRemoveTableView.getSelectionModel().getSelectedItem();
            assocParts.remove(delPart);
        }
    }

    /** saves product and returns to the main menu */

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        try {
            int id = Product.getProductID();
            String name = addProductNameTxt.getText();
            int inv = Inventory.getInv(addProductInvTxt.getText());
            double price = Inventory.getPrice(addProductPriceTxt.getText());
            int min = Inventory.getMin(addProductMinTxt.getText());
            int max = Inventory.getMax(addProductMaxTxt.getText());
            Inventory.isMinMaxCorrect(min, max);
            Inventory.isInvCorrect(inv, min, max);

            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setStock(inv);
            product.setMin(min);
            product.setMax(max);
            Inventory.addProduct(product);
            for(Part p : assocParts) {
                product.addAssociatedPart(p);
            }

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

    }

    /** Sets the screen up with initial data */

    public void initialize(URL url, ResourceBundle rb) {
        /** RUNTIME ERROR non-static method Product.getAllAssociatedParts() cannot be referenced from a static context,
         * changed to Inventory.getAllParts() */


        addProductAddTableView.setItems(Inventory.getAllParts());

        addPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        addProductRemoveTableView.setItems(assocParts);

        removePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        removePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        removeInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        removePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
