package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class loginStage implements Initializable {

    @FXML
    private Button submitButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField id;


    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
    }

    @FXML
    private void handleSubmitButtonAction(final ActionEvent event){

        if (id.getText().equals("receptionist")){

            try {
                Stage stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Receptionist/receptionistController.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Receptionist");
                stage.setScene(scene);
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if(id.getText().equals("trainer")){
            try {
                Stage stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Trainer/trainerController.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Trainer");
                stage.setScene(scene);
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if(id.getText().equals("manager")){
            try {
                Stage stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Manager/managerController.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Manager");
                stage.setScene(scene);
                stage.show();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else System.out.println("yanlış girdin");

    }

}
