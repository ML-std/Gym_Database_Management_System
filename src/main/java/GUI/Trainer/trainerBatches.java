package GUI.Trainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class trainerBatches {

    @FXML
    private Button backButton,addButton,rearrangeBatchButton,getBatchButton,removeBatchButton;

    @FXML
    private TextField batchID,startTime,endTime,batchType,branchID,employeeID,batchIDRearrange,batchIDRemove,batchIDGet,startTimeRearrange,endTimeRearrange;

    @FXML
    private void handleRearrangeBatchButtonAction(final ActionEvent event){

    }

    @FXML
    private void handleGetBatchButtonAction(final ActionEvent event){

    }

    @FXML
    private void handleRemoveBatchButtonAction(final ActionEvent event){

    }



    @FXML
    private void handleAddButtonAction(final ActionEvent event){
        batchID.getText();
        startTime.getText();
        endTime.getText();
        batchType.getText();
        branchID.getText();
        employeeID.getText();
    }

    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }


}
