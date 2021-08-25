package Controller;

import Model.InHouse;
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

        String s = addProductSearchTxt.getText();

        ObservableList<Part> parts = Inventory.lookupPart(s);

        if(parts.size() == 0) {
            int id = Integer.parseInt(s);
            Part p = Inventory.lookupPart(id);
            if(p != null) {
                parts.add(p);
            }
        }

        addProductAddTableView.setItems(parts);

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

        Part delPart = addProductRemoveTableView.getSelectionModel().getSelectedItem();
        assocParts.remove(delPart);

    }

    /** saves product and returns to the main menu */

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        int id = Product.getProductID();
        String name = addProductNameTxt.getText();
        double price = Double.parseDouble(addProductPriceTxt.getText());
        int inv = Integer.parseInt(addProductInvTxt.getText());
        int min = Integer.parseInt(addProductMinTxt.getText());
        int max = Integer.parseInt(addProductMaxTxt.getText());

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

    }


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
