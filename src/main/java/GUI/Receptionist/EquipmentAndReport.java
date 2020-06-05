package GUI.Receptionist;

import GUI.Rules;
import ServerSide.Condition;
import ServerSide.Receptionist;
import com.sun.javafx.css.Rule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static GUI.Login.loggedEmployee;

public class EquipmentAndReport implements Initializable {

    public RadioButton fairRadioButton;
    public RadioButton badRadioButton;
    public RadioButton goodRadioButton;
    public ToggleGroup conditionGroup;
    public Button addCustomerReport;
    public TextField manufacturer;
    public TextField equipmentName;
    public TextField type;
    public TextField count;
    public Button addEquipmentButton;
    public TextField customerID;
    public TextField fat;
    public TextField height;
    public Button removeButton;
    public TextField weight;
    public Button setEquipmentButton;
    public TextField removeID;
    public RadioButton badSetRadioButton;
    public ToggleGroup conditionSetGroup;
    public RadioButton fairSetRadioButton;
    public RadioButton customerReportRadioButton;
    public ToggleGroup removeGroup;
    public RadioButton equipmentRadioButton;
    public RadioButton goodSetRadioButton;
    public TextField equipmentID;
    @FXML
    private Button backButton;

    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        removeGroup = new ToggleGroup();
        conditionGroup = new ToggleGroup();
        conditionSetGroup = new ToggleGroup();

        equipmentRadioButton.setToggleGroup(removeGroup);
        customerReportRadioButton.setToggleGroup(removeGroup);

        goodRadioButton.setToggleGroup(conditionGroup);
        fairRadioButton.setToggleGroup(conditionGroup);
        badRadioButton.setToggleGroup(conditionGroup);

        goodSetRadioButton.setToggleGroup(conditionSetGroup);
        fairSetRadioButton.setToggleGroup(conditionSetGroup);
        badRadioButton.setToggleGroup(conditionSetGroup);

    }
    public void handleAddCustomerReportAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(customerID);
        textFields.add(fat);
        textFields.add(weight);
        textFields.add(height);
        if (!Rules.hasAnEmptyInput(textFields)){
         boolean isAdded = ((Receptionist) loggedEmployee).addCustomerReport(Integer.parseInt(customerID.getText()),Integer.parseInt(fat.getText()),
                    Integer.parseInt(weight.getText()),Integer.parseInt(height.getText()), LocalDate.now().toString());
         Rules.alertHandler(isAdded,"Please check your inputs");
        }
    }

    public void handleRemoveButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(removeID);
        if(!Rules.hasAnEmptyInput(textFields)){
            boolean isRemoved = false;
        if (equipmentRadioButton.isSelected()){
            isRemoved = ((Receptionist) loggedEmployee).removeEquipment(Integer.parseInt(removeID.getText()));
        }
        else if (customerReportRadioButton.isSelected()){
            isRemoved = ((Receptionist) loggedEmployee).removeCustomerReport(Integer.parseInt(removeID.getText()));
        }
        else{
            Rules.alertHandler(false,"Please Select a type");
            return;
        }
        Rules.alertHandler(isRemoved,"Please check your inputs");

        }
    }

    public void handleAddEquipmentAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(equipmentName);
        textFields.add(manufacturer);
        textFields.add(type);
        textFields.add(count);
        if (!Rules.hasAnEmptyInput(textFields)){
            boolean isAdded = false;
            if (goodRadioButton.isSelected()){
                isAdded = ((Receptionist) loggedEmployee).addEquipment(equipmentName.getText(),manufacturer.getText(), Condition.GOOD,
                        type.getText(),Integer.parseInt(count.getText()));
            }
            else if (fairRadioButton.isSelected()){
                isAdded = ((Receptionist) loggedEmployee).addEquipment(equipmentName.getText(),manufacturer.getText(), Condition.FAIR,
                        type.getText(),Integer.parseInt(count.getText()));
            }
            else if(badRadioButton.isSelected()){
                isAdded = ((Receptionist) loggedEmployee).addEquipment(equipmentName.getText(),manufacturer.getText(), Condition.BAD,
                        type.getText(),Integer.parseInt(count.getText()));
            }
            else{
                Rules.alertHandler(false,"Select a condition");
                return;
            }
            Rules.alertHandler(isAdded,"Please check your inputs");
        }
    }

    public void handleSetEquipmentAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(equipmentID);
        if (!Rules.hasAnEmptyInput(textFields)){
            boolean isAdded = false;
            if (goodSetRadioButton.isSelected()){
                isAdded = ((Receptionist) loggedEmployee).setEquipment(
                       Integer.parseInt(equipmentID.getText()),Condition.GOOD);
            }
            else if (fairSetRadioButton.isSelected()){
                isAdded = ((Receptionist) loggedEmployee).setEquipment(Integer.parseInt(equipmentID.getText()),Condition.FAIR);
            }
            else if(badSetRadioButton.isSelected()){
                isAdded = ((Receptionist) loggedEmployee).setEquipment(Integer.parseInt(equipmentID.getText()),Condition.BAD);
            }
            else{
                Rules.alertHandler(false,"Select a condition");
                return;
            }
            Rules.alertHandler(isAdded,"Please check your inputs");
        }
    }


}
