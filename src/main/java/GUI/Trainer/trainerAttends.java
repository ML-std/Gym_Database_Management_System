package GUI.Trainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class trainerAttends {

    @FXML
    private Button backButton,includeButton,excludeButton,getAttendanceButton;

    @FXML
    private TextField customerID,batchID,customerOrBatchID;

    @FXML
    private CheckBox isCustomer;

    @FXML
    private void handleExcludeButtonAction(final ActionEvent event){

    }

    @FXML
    private void handleIncludeButtonAction(final ActionEvent event){

    }

    @FXML
    private void handleGetAttendanceButtonAction(final ActionEvent event){

    }

    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
