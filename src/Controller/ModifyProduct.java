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
import java.util.ResourceBundle;

public class ModifyProduct implements Initializable {

    Stage stage;
    Parent scene;
    int productIndex = MainPageController.productModIndex();
    private int productID;
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();
    private ObservableList<Part> assocPartsFinal;
    Product product;

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

    /** adds parts to the associated parts table */

    @FXML
    void onActionAddPart(ActionEvent event) {

        Part p = modProductAddTableView.getSelectionModel().getSelectedItem();
        assocParts.add(p);
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {


        modProductRemoveTableView.setItems(assocPartsFinal);

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionRemovePart(ActionEvent event) {

        Part delPart = modProductRemoveTableView.getSelectionModel().getSelectedItem();
        assocParts.remove(delPart);
    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        try {
            String name = modifyProductNameTxt.getText();
            int inv = Inventory.getInv(modifyProductInvTxt.getText());
            double price = Inventory.getPrice(modifyProductPriceTxt.getText());
            int min = Inventory.getMin(modifyProductMinTxt.getText());
            int max = Inventory.getMax(modifyProductMaxTxt.getText());
            Inventory.isMinMaxCorrect(min, max);
            Inventory.isInvCorrect(inv, min, max);

            Product p = new Product(product.getId(),name,price,inv,min,max);
            p.setAssociatedParts(assocParts);
            Inventory.updateProduct(productIndex, p);

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

    @FXML
    void onSearchParts(ActionEvent event) {

        String str = modifyProductSearchTxt.getText();
        if (!str.isBlank()) {
            try {
                int i = Integer.parseInt(modifyProductSearchTxt.getText());
                Part part = Inventory.lookupPart(i);
                if(part == null) {
                    throw new NumberFormatException();
                }
                modProductAddTableView.getSelectionModel().select(part);

            } catch (NumberFormatException e) {
                ObservableList<Part> list = Inventory.lookupPart(str);
                if(list.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Part Search");
                    alert.setHeaderText(null);
                    alert.setContentText("Can't find part with that info");

                    alert.showAndWait();
                    modProductAddTableView.setItems(Inventory.getAllParts());
                } else {
                    modProductAddTableView.setItems(list);
                }
            }
        } else {
            modProductAddTableView.setItems(Inventory.getAllParts());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        product = Inventory.getAllProducts().get(productIndex);
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

        modProductRemoveTableView.setItems(product.getAllAssociatedParts());
        assocParts.setAll(product.getAllAssociatedParts());

        modifyProductRemovePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductRemovePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductRemoveInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductRemovePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        updateModProductRemoveTableView();

    }

    public void updateModProductRemoveTableView() {
        modProductRemoveTableView.setItems(assocParts);
    }
}
