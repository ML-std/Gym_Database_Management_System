package GUI.Trainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class trainer implements Initializable {

    @FXML
    private Button attendsButton;

    @FXML
    private Button batchesButton;

    @FXML
    private Button backButton;

    @FXML
    private Label username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("username");
    }

    @FXML
    private void handleAttendsButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/GUI/Trainer/trainerAttendsController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Attends");
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBatchesButtonAction(final ActionEvent event ){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/GUI/Trainer/trainerBatchesController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Batches");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        try{
            Stage stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("src/main/GUI/loginController.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Gym Database Management System");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
