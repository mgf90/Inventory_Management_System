package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPart {

    Stage stage;
    Parent scene;

    @FXML
    private TextField addPartIDTxt;

    @FXML
    private TextField addPartNameTxt;

    @FXML
    private TextField addPartInvTxt;

    @FXML
    private TextField addPartPriceTxt;

    @FXML
    private TextField addPartMaxTxt;

    @FXML
    private TextField AddPartMachineIDTxt;

    @FXML
    private TextField addPartMinTxt;

    @FXML
    private RadioButton addPartInHouseRBtn;

    @FXML
    private RadioButton addPartOutsourcedRBtn;

    @FXML
    private Label changeText;

    private boolean isInHouse = true;

    /** returns to main menu when Cancel button is selected */
    @FXML
    void OnActionCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** saves Part info that you enter into the fields */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        try {

            if (isInHouse) {

                int id = InHouse.getPartID();
                String name = addPartNameTxt.getText();
                int inv = Inventory.getInv(addPartInvTxt.getText());
                double price = Inventory.getPrice(addPartPriceTxt.getText());
                int min = Inventory.getMin(addPartMinTxt.getText());
                int max = Inventory.getMax(addPartMaxTxt.getText());
                int machineID = Integer.parseInt(AddPartMachineIDTxt.getText());

                Inventory.addPart(new InHouse(id, name, price, inv, min, max, machineID));

                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }

            if (!isInHouse) {
                int id = InHouse.getPartID();
                String name = addPartNameTxt.getText();
                int inv = Inventory.getInv(addPartInvTxt.getText());
                double price = Inventory.getPrice(addPartPriceTxt.getText());
                int min = Inventory.getMin(addPartMinTxt.getText());
                int max = Inventory.getMax(addPartMaxTxt.getText());
                String companyName = AddPartMachineIDTxt.getText();

                Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName));

                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect data entered");

            alert.showAndWait();
        }



        }

        /** sets last field to Machine ID when In House radio button is selected */
    @FXML
    void onInHouse(ActionEvent event) {
        changeText.setText("Machine ID");
        isInHouse = true;
    }

    /** sets last field to Company Name when Outsourced radio button is selected */
    @FXML
    void onOutsourced(ActionEvent event) {
        changeText.setText("Company Name");
        isInHouse = false;
    }

}
