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

public class AddProduct implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField addProductIDTxt;

    @FXML
    private TextField addProductNameTxt;

    @FXML
    private TextField addProductInvTxt;

    @FXML
    private TextField addProductPriceTxt;

    @FXML
    private Label addProductMaxTxt;

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
    void onActionSavePart(ActionEvent event) {

    }

    public void initialize(URL url, ResourceBundle rb) {
        /** RUNTIME ERROR non-static method Product.getAllAssociatedParts() cannot be referenced from a static context,
         * changed to Inventory.getAllParts() */


        addProductAddTableView.setItems(Inventory.getAllParts());

        addPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
