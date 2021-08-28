package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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

public class ModifyPart implements Initializable {

    Stage stage;
    Parent scene;
    int partIndex = MainPageController.partModIndex();
    private int partID;

    @FXML
    private TextField modifyPartIDTxt;

    @FXML
    private TextField modifyPartNameTxt;

    @FXML
    private TextField modifyPartInvTxt;

    @FXML
    private TextField modifyPartPriceTxt;

    @FXML
    private TextField modifyPartMaxTxt;

    @FXML
    private TextField modifyPartMachineIDTxt;

    @FXML
    private TextField modifyPartMinTxt;

    @FXML
    private RadioButton modifyPartInHouseRBtn;

    @FXML
    private RadioButton modifyPartOutsourcedRBtn;

    @FXML
    private Label changeText;

    /** goes back to the main menu without saving */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainpage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        try {
            String name = modifyPartNameTxt.getText();
            int inv = Inventory.getInv(modifyPartInvTxt.getText());
            double price = Inventory.getPrice(modifyPartPriceTxt.getText());
            int min = Inventory.getMin(modifyPartMinTxt.getText());
            int max = Inventory.getMax(modifyPartMaxTxt.getText());
            String machineID = modifyPartMachineIDTxt.getText();
            Inventory.isMinMaxCorrect(min, max);
            Inventory.isInvCorrect(inv, min, max);

            if(modifyPartOutsourcedRBtn.isSelected() == true) {
                Outsourced op = new Outsourced(0, name, price, inv, min, max,null);
                op.setId(partID);
                op.setCompanyName(machineID);
                Inventory.updatePart(partIndex, op);
            } else {
                InHouse ih = new InHouse(0,name,price,inv,min,max,0);
                ih.setId(partID);
                ih.setMachineID(Integer.parseInt(machineID));
                Inventory.updatePart(partIndex, ih);
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

    @FXML
    void onInHouse(ActionEvent event) {
        changeText.setText("Machine ID");
    }

    @FXML
    void onOutsourced(ActionEvent event) {
        changeText.setText("Company Name");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Part part = Inventory.getAllParts().get(partIndex);
        partID = Inventory.getAllParts().get(partIndex).getId();
        modifyPartIDTxt.setText(String.valueOf(partID));
        modifyPartNameTxt.setText(part.getName());
        modifyPartInvTxt.setText(String.valueOf(part.getStock()));
        modifyPartPriceTxt.setText(String.valueOf(String.format("%.2f", part.getPrice())));
        modifyPartMinTxt.setText(String.valueOf(part.getMin()));
        modifyPartMaxTxt.setText(String.valueOf(part.getMax()));

        if(part instanceof InHouse) {
            modifyPartMachineIDTxt.setText(Integer.toString(((InHouse) Inventory.getAllParts().get(partIndex)).getMachineID()));
            modifyPartInHouseRBtn.setSelected(true);
            changeText.setText("Machine ID");
        } else if(part instanceof Outsourced) {
            modifyPartMachineIDTxt.setText(((Outsourced) Inventory.getAllParts().get(partIndex)).getCompanyName());
            modifyPartOutsourcedRBtn.setSelected(true);
            changeText.setText("Company Name");
        }
    }


}