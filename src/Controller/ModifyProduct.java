package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

public class ModifyProduct implements Initializable {

    Stage stage;
    Parent scene;
    int productIndex = MainPageController.productModIndex();
    private int productID;

    @FXML
    private TextField modifyProductIDTxt;

    @FXML
    private TextField modifyProductNameTxt;

    @FXML
    private TextField modifyProductInvTxt;

    @FXML
    private TextField modifyProductPriceTxt;

    @FXML
    private TextField modifyProductMaxTxt;

    @FXML
    private TextField modifyProductMinTxt;

    @FXML
    private TextField modifyProductSearchTxt;

    @FXML
    private TableView<Part> modProductAddTableView;

    @FXML
    private TableColumn<Part, Integer> modifyProductAddPartIDCol;

    @FXML
    private TableColumn<Part, String> modifyProductAddPartNameCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductAddInvLvlCol;

    @FXML
    private TableColumn<Part, Double> modifyProductAddPriceCol;

    @FXML
    private TableView<Part> modProductRemoveTableView;

    @FXML
    private TableColumn<Part, Integer> modifyProductRemovePartIDCol;

    @FXML
    private TableColumn<Part, String> modifyProductRemovePartNameCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductRemoveInvLvlCol;

    @FXML
    private TableColumn<Part, Double> modifyProductRemovePriceCol;

    @FXML
    void onActionAddPart(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        String name = modifyProductNameTxt.getText();
        int inv = Integer.parseInt(modifyProductInvTxt.getText());
        double price = Double.parseDouble(modifyProductPriceTxt.getText());
        int min = Integer.parseInt(modifyProductMinTxt.getText());
        int max = Integer.parseInt(modifyProductMaxTxt.getText());

        Product p = new Product(0,null,0,0,0,0);
        p.setId(productID);
        p.setName(name);
        p.setStock(inv);
        p.setPrice(price);
        p.setMin(min);
        p.setMax(max);
        Inventory.updateProduct(productIndex, p);

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onSearchParts(ActionEvent event) {

        String s = modifyProductSearchTxt.getText();

        ObservableList<Part> parts = Inventory.lookupPart(s);

        if(parts.size() == 0) {
            int id = Integer.parseInt(s);
            Part p = Inventory.lookupPart(id);
            if(p != null) {
                parts.add(p);
            }
        }

        modProductAddTableView.setItems(parts);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Product product = Inventory.getAllProducts().get(productIndex);
        productID = Inventory.getAllProducts().get(productIndex).getId();
        modifyProductIDTxt.setText(String.valueOf(productID));
        modifyProductNameTxt.setText(product.getName());
        modifyProductInvTxt.setText(String.valueOf(product.getStock()));
        modifyProductPriceTxt.setText(String.valueOf(product.getPrice()));
        modifyProductMinTxt.setText(String.valueOf(product.getMin()));
        modifyProductMaxTxt.setText(String.valueOf(product.getMax()));

        modProductAddTableView.setItems(Inventory.getAllParts());

        modifyProductAddPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductAddPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductAddInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductAddPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
