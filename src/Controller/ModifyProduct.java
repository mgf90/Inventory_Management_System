package Controller;

import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProduct {

    Stage stage;
    Parent scene;

    @FXML
    private TextField modifyProductIDTxt;

    @FXML
    private TextField modifyProductNameTxt;

    @FXML
    private TextField modifyProductInvTxt;

    @FXML
    private TextField modifyProductPriceTxt;

    @FXML
    private Label modifyProductMaxTxt;

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

}