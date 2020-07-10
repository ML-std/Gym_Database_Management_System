package GUI;

import ServerSide.Employee;
import ServerSide.Manager;
import ServerSide.Receptionist;
import ServerSide.Trainer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public static String welcome = "";
    public static Employee loggedEmployee;
    @FXML
    private Button submitButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField id;


    @Override
    public void initialize(final URL url, final ResourceBundle rb) {

        System.out.println(id.getText().equals(""));
    }


    @FXML
    private void handleSubmitButtonAction(final ActionEvent event){

        Employee tmpEmployee = new Employee(Integer.parseInt(id.getText()));
        Employee.stringBooleanClass tmpObject = tmpEmployee.employeeLogin(tmpEmployee.getEmployeeID(),password.getText());
         String welcomeString =tmpObject.getString();
         boolean isLogged = tmpObject.isaBoolean();
         Employee loggedInEmployee = tmpObject.getAnEmployee();

        if (isLogged){
            welcome = welcomeString;
            loggedEmployee = loggedInEmployee;
            if (loggedInEmployee.getClass().equals(Receptionist.class)){
            try {
                Stage stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/receptionistController.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Receptionist");
                stage.setScene(scene);
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if(loggedInEmployee.getClass().equals(Trainer.class)){
            try {
                Stage stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/trainerController.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Trainer");
                stage.setScene(scene);
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if(loggedInEmployee.getClass().equals(Manager.class)){
            try {
                Stage stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/managerController.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Manager");
                stage.setScene(scene);
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else System.out.println("Some problems occurred");

    }
    else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Login Failed");
        alert.setHeaderText(welcomeString);
        alert.setContentText("Please check your ID/Password");

        alert.showAndWait();
    }}

    public void isNumber(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            keyEvent.consume();
        }}


    }

