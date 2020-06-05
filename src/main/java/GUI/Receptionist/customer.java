package GUI.Receptionist;

import GUI.Login;
import GUI.Rules;
import ServerSide.Customer;
import ServerSide.Receptionist;
import ServerSide.Sex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static GUI.Login.loggedEmployee;
import static GUI.Rules.alertHandler;

public class customer implements Initializable {

    public ToggleGroup sexGroup;
    public RadioButton maleRadioButton;
    public RadioButton femaleRadioButton;
    public RadioButton trainerRadioButton;
    public RadioButton facilityRadioButton;
    public ToggleGroup ratingGroup;
    public Label username;
    public Button addCustomerButton;
    public TextField SSN;
    public TextArea address;
    public TextField lastName;
    public TextField middleName;
    public TextField firstName;
    public DatePicker birthDate;
    public TextField phoneNumbers;
    public Button setTrainerButton;
    public TextField trainerID;
    public TextField setCustomerID;
    public Button addRatingButton;
    public TextField rating;
    public TextField addCustomerRating;
    public Button removeCustomerButton;
    public TextField removeCustomerID;
    public TextField trainerOrFacility;
    @FXML
    private Button backButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            username.setText(Login.welcome);
            ratingGroup = new ToggleGroup();
            facilityRadioButton.setToggleGroup(ratingGroup);
            trainerRadioButton.setToggleGroup(ratingGroup);
            sexGroup = new ToggleGroup();
            maleRadioButton.setToggleGroup(sexGroup);
            femaleRadioButton.setToggleGroup(sexGroup);
    }
    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }


    public void handleAddCustomerButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(firstName);
        textFields.add(lastName);
        textFields.add(SSN);
        textFields.add(phoneNumbers);
        if (!Rules.hasAnEmptyInput(textFields)){
            if (!(maleRadioButton.isSelected()||femaleRadioButton.isSelected())||address.getText().equals("")||birthDate.getValue().toString().equals("")){
                alertHandler(false,"fill the blanks");
            }
            else {
                boolean isAdded =false;
                String[] phoneNumberArray = phoneNumbers.getText().split(" ");
                if (maleRadioButton.isSelected()) {
                  isAdded =  ((ServerSide.Receptionist) loggedEmployee).addCustomer(new Customer(firstName.getText(), middleName.getText(), lastName.getText(),
                            Sex.MALE, address.getText(), Integer.parseInt(SSN.getText()), phoneNumberArray, birthDate.getValue()), phoneNumberArray);
                }
                else {
                  isAdded =   ((ServerSide.Receptionist) loggedEmployee).addCustomer(new Customer(firstName.getText(), middleName.getText(), lastName.getText(),
                            Sex.FEMALE, address.getText(), Integer.parseInt(SSN.getText()), phoneNumberArray, birthDate.getValue()), phoneNumberArray);
                }
                alertHandler(isAdded,"Please check the values");
            }


        }
    }

    public void handleSetTrainerButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(setCustomerID);
        textFields.add(trainerID);
        boolean isSet = false;
        if (!Rules.hasAnEmptyInput(textFields)){
            isSet = ((Receptionist)loggedEmployee).setCustomerTrainer(Integer.parseInt(setCustomerID.getText()),Integer.parseInt(trainerID.getText()));
        }
        alertHandler(isSet,"Please check your inputs");
    }

    public void handleAddRatingButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(addCustomerRating);
        textFields.add(rating);
        textFields.add(trainerOrFacility);
        boolean isAdded = false;
        if (!Rules.hasAnEmptyInput(textFields)){
            if (trainerRadioButton.isSelected()){
                isAdded = ((Receptionist)loggedEmployee).addTrainerReview(Integer.parseInt(addCustomerRating.getText()),Integer.parseInt(trainerOrFacility.getText())
                ,Integer.parseInt(rating.getText()));
            }
            else if (facilityRadioButton.isSelected()){
                isAdded = ((Receptionist)loggedEmployee).addFacilityReview(Integer.parseInt(addCustomerRating.getText()),Integer.parseInt(trainerOrFacility.getText())
                        ,Integer.parseInt(rating.getText()));
            }
            else {
                alertHandler(false, "check your Type of review");
                return;
            }
            alertHandler(isAdded,"please check your inputs");
        }

    }

    public void handleRemoveCustomerButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(removeCustomerID);
        boolean isRemoved = false;
        if (!Rules.hasAnEmptyInput(textFields)){
            isRemoved = ((Receptionist)loggedEmployee).removeCustomer(Integer.parseInt(removeCustomerID.getText()));
        }
        alertHandler(isRemoved, " please write a correct  customer ID");
        }
    }
