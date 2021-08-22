package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    /** RUNTIME ERROR added "implements Initializable" to class declaration to enable app to run */

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> PartIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLvlCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLvlCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField searchPartTxt;

    @FXML
    private TextField searchProductTxt;

    @FXML
    private Button searchParts;

    @FXML
    private Button searchProducts;

    private static Part modPart;
    private static int modPartIndex;
    private static Product modProduct;
    private static int modProductIndex;

    public static int partModIndex() {
        return modPartIndex;
    }

    public static int productModIndex() {
        return modProductIndex;
    }

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addpart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addproduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        if(Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem())) {
            ObservableList<Part> allParts = Inventory.getAllParts();
            allParts.remove(partsTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        if(Inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem())) {
            ObservableList<Product> allProducts = Inventory.getAllProducts();
            allProducts.remove(productsTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        modPart = partsTableView.getSelectionModel().getSelectedItem();
        modPartIndex = Inventory.getAllParts().indexOf(modPart);

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifypart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/modifyproduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onSearchParts(ActionEvent event) {

            String s = searchPartTxt.getText();

            ObservableList<Part> parts = Inventory.lookupPart(s);

            if(parts.size() == 0) {
                int id = Integer.parseInt(s);
                Part p = Inventory.lookupPart(id);
                if(p != null) {
                    parts.add(p);
                }
            }

            partsTableView.setItems(parts);
    }

    @FXML
    void onSearchProducts(ActionEvent event) {

        String s = searchProductTxt.getText();

        ObservableList<Product> products = Inventory.lookupProduct(s);

        if(products.size() == 0) {
            int id = Integer.parseInt(s);
            Product p = Inventory.lookupProduct(id);
            if(p != null) {
                products.add(p);
            }
        }

        productsTableView.setItems(products);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableView.setItems(Inventory.getAllParts());

        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems(Inventory.getAllProducts());

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}