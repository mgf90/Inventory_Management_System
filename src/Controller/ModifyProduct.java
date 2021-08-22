package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
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
    void onActionSavePart(ActionEvent event) {

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

    }
}
