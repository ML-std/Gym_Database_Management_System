package GUI.Manager;

import GUI.Login;
import ServerSide.Employee;
import ServerSide.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static GUI.Login.loggedEmployee;
import static GUI.Rules.alertHandler;
import static GUI.Rules.hasAnEmptyInput;

public class EmployeeEmployer implements Initializable {
    @FXML
    public RadioButton receptionistRadioButton;
    @FXML
    public ToggleGroup employeeGroup;
    @FXML
    public RadioButton trainerRadioButton;
    @FXML
    public RadioButton cleanerRadioButton;
    @FXML
    public Button hireEmployeeButton;
    @FXML
    public TextArea address;
    @FXML
    public TextField SSN;
    @FXML
    public TextField middleName;
    @FXML
    public TextField password;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public Label username;
    @FXML
    public TextField salary;
    @FXML
    public TextField phone;
    @FXML
    public TextField facilityID;
    @FXML
    public Label facilityIDLabel;
    @FXML
    public Button removeEmployeeButton;
    @FXML
    public TextField removeEmployeeID;
    public ToggleGroup rearrangeGroup;
    public RadioButton addressRadioButton;
    public RadioButton salaryRadioButton;
    public Button rearrangeEmployee;
    public TextField rearrangeSalary;
    public TextArea rearrangeAddress;
    public TextField rearrangeEmployeeID;
    public RadioButton receptionistSetRadioButton;
    public ToggleGroup employeeSetGroup;
    public RadioButton trainerSetRadioButton;
    public RadioButton cleanerSetRadioButton;
    public RadioButton managerSetRadioButton;
    public Label setTypeLabel;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rearrangeAddress.setVisible(false);
        rearrangeSalary.setVisible(false);
        facilityID.setVisible(false);
        facilityIDLabel.setVisible(false);
        trainerSetRadioButton.setVisible(false);
        receptionistSetRadioButton.setVisible(false);
        cleanerSetRadioButton.setVisible(false);
        managerSetRadioButton.setVisible(false);
        setTypeLabel.setVisible(false);
        employeeSetGroup = new ToggleGroup();
        receptionistSetRadioButton.setToggleGroup(employeeSetGroup);
        trainerSetRadioButton.setToggleGroup(employeeSetGroup);
        cleanerSetRadioButton.setToggleGroup(employeeSetGroup);
        managerSetRadioButton.setToggleGroup(employeeSetGroup);

        employeeGroup = new ToggleGroup();
        receptionistRadioButton.setToggleGroup(employeeGroup);
        trainerRadioButton.setToggleGroup(employeeGroup);
        cleanerRadioButton.setToggleGroup(employeeGroup);

        username.setText(Login.welcome);

        rearrangeGroup = new ToggleGroup();

        addressRadioButton.setToggleGroup(rearrangeGroup);
        salaryRadioButton.setToggleGroup(rearrangeGroup);

    }

    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void handleHireEmployeeButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(firstName);
        textFields.add(lastName);
        textFields.add(password);
        textFields.add(SSN);
        textFields.add(salary);
        textFields.add(phone);
        if (!(hasAnEmptyInput(textFields) )) {
            if (!address.getText().equals("")){
            if (receptionistRadioButton.isSelected() || trainerRadioButton.isSelected() || cleanerRadioButton.isSelected()) {
                boolean isHired = false;
                if (receptionistRadioButton.isSelected()) {
                    isHired = ((ServerSide.Manager) loggedEmployee).hireReceptionist(new Employee(firstName.getText(),
                            middleName.getText(), lastName.getText(), address.getText(), password.getText(),
                            loggedEmployee.getBranchID(), Integer.parseInt(SSN.getText())), Integer.parseInt(salary.getText()), new String[]{phone.getText()});

                } else if (trainerRadioButton.isSelected()) {
                    isHired = ((ServerSide.Manager) loggedEmployee).hireTrainer(new Employee(firstName.getText(),
                            middleName.getText(), lastName.getText(), address.getText(), password.getText(),
                            loggedEmployee.getBranchID(), Integer.parseInt(SSN.getText())), Integer.parseInt(salary.getText()), new String[]{phone.getText()});
                } else if (cleanerRadioButton.isSelected()) {
                    ArrayList<TextField> tmpArrayList = new ArrayList<>();
                    tmpArrayList.add(facilityID);
                    if (!hasAnEmptyInput(tmpArrayList)) {
                        isHired = ((ServerSide.Manager) loggedEmployee).hireCleaner(new Employee(firstName.getText(),
                                        middleName.getText(), lastName.getText(), address.getText(), password.getText(),
                                        loggedEmployee.getBranchID(), Integer.parseInt(SSN.getText())), Integer.parseInt(salary.getText()), new String[]{phone.getText()},
                                Integer.parseInt(facilityID.getText()));

                    }
                }
                if (isHired) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hiring succeed");
                    alert.setHeaderText("Please note these information below");
                    alert.setContentText("Hired Employee ID : " + ((Manager) loggedEmployee).getNewEmployeeID() + " Hired Employee password : "
                            + password.getText());
                    alert.showAndWait();

                } else alertHandler(false, "Hiring employee operation has failed, please check your inputs");
            } else
                alertHandler(false, "Hire operation has failed,please select employee type");}
            else alertHandler(false,"please put an address.");
        }
    }


    public void getFacilityIDAction(ActionEvent actionEvent) {
        facilityIDLabel.setVisible(true);
        facilityID.setVisible(true);

    }

    public void handleRemoveEmployeeButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(removeEmployeeID);
        if (!hasAnEmptyInput(textFields)){
        List<Object> employeeToRemove = ((ServerSide.Manager) loggedEmployee).getEmployee(Integer.parseInt(removeEmployeeID.getText()));
        if (employeeToRemove.isEmpty()){
            alertHandler(false,"No such employee ID is found");
        }
        else if (((Manager) loggedEmployee).isManager(Integer.parseInt(removeEmployeeID.getText()))){
                alertHandler(false,"You are not allowed to dismiss a manager");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation To Remove");
            alert.setHeaderText("Are you sure to dismiss the employee?");
            alert.setContentText("Employee : " + employeeToRemove.get(1) +" " + employeeToRemove.get(2)
                    +" "+ employeeToRemove.get(3));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK){
               boolean isRemoved = ((ServerSide.Manager) loggedEmployee).dismissEmployee(Integer.parseInt(removeEmployeeID.getText()));
                alertHandler(isRemoved,"Some problem is occurred. Please Check your inputs.");
            }
        }
    }}

    public void removeFacilityIDTextAction(ActionEvent actionEvent) {
        facilityID.setVisible(false);
        facilityIDLabel.setVisible(false);
    }

    public void getAddressTextAction(ActionEvent actionEvent) {
        rearrangeAddress.setVisible(true);
        setTypeLabel.setVisible(false);
        receptionistSetRadioButton.setVisible(false);
        trainerSetRadioButton.setVisible(false);
        cleanerSetRadioButton.setVisible(false);
        managerSetRadioButton.setVisible(false);
        rearrangeSalary.setVisible(false);

    }

    public void getSalaryTextAction(ActionEvent actionEvent) {
        rearrangeAddress.setVisible(false);
        setTypeLabel.setVisible(true);
        receptionistSetRadioButton.setVisible(true);
        trainerSetRadioButton.setVisible(true);
        cleanerSetRadioButton.setVisible(true);
        managerSetRadioButton.setVisible(true);
        rearrangeSalary.setVisible(true);
    }

    public void handleRearrangeEmployeeAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(rearrangeEmployeeID);
        if (!hasAnEmptyInput(textFields)){
        if (addressRadioButton.isSelected()){
            if (rearrangeAddress.getText().equals("")){
                alertHandler(false,"Put address");
            }
            else {
              boolean isSet =  ((ServerSide.Manager)loggedEmployee).rearrangeEmployee(rearrangeAddress.getText(),Integer.parseInt(rearrangeEmployeeID.getText()));
              alertHandler(isSet,"Please check your inputs");
            }
        }
        else if (salaryRadioButton.isSelected()){
                textFields.add(rearrangeSalary);
                if (!hasAnEmptyInput(textFields)){
                    boolean isSet;
                    if (trainerSetRadioButton.isSelected()){
                        isSet = ((ServerSide.Manager) loggedEmployee).rearrangeTrainer(Integer.parseInt(rearrangeSalary.getText()),
                                Integer.parseInt(rearrangeEmployeeID.getText()));
                    }
                    else if (managerSetRadioButton.isSelected()){
                        isSet = ((ServerSide.Manager) loggedEmployee).rearrangeManager(Integer.parseInt(rearrangeSalary.getText()),
                                Integer.parseInt(rearrangeEmployeeID.getText()));

                    }
                    else if (cleanerSetRadioButton.isSelected()){
                        isSet = ((ServerSide.Manager) loggedEmployee).rearrangeCleaner(Integer.parseInt(rearrangeSalary.getText()),
                                Integer.parseInt(rearrangeEmployeeID.getText()));

                    }
                    else if (receptionistSetRadioButton.isSelected()){
                        isSet = ((ServerSide.Manager) loggedEmployee).rearrangeReceptionist(Integer.parseInt(rearrangeSalary.getText()),
                                Integer.parseInt(rearrangeEmployeeID.getText()));

                    }
                    else {alertHandler(false,"please select employee type");
                    return;}
                    alertHandler(isSet,"please check your inputs");
                }

        }
        else alertHandler(false,"select type of rearrange first");
    }}

    public void setPromptText(ActionEvent actionEvent) {
        String promptText = "";
        if (trainerSetRadioButton.isSelected()){
            promptText = "Hourly salary";
        }
        else if (managerSetRadioButton.isSelected()){
            promptText = "Monthly salary";

        }
        else if (cleanerSetRadioButton.isSelected()){
            promptText = "Daily salary";

        }
        else if (receptionistSetRadioButton.isSelected()){
            promptText = "Weekly salary";

        }
        rearrangeSalary.setPromptText(promptText);
    }
}
