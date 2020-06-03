package GUI.Manager;

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

public class manager implements Initializable {


    @FXML
    private Button backButton;

    @FXML
    private Label username;

    @FXML
    private Button employReceptionistButton;

    @FXML
    private Button employCleanerButton;

    @FXML
    private Button employTrainerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("menejercim");
    }

    @FXML
    private void handleEmployReceptionistButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/GUI/Manager/employReceptionistController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Employ Receptionist");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void handleEmployCleanerButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/GUI/Manager/employCleanerController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Employ Cleaner");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEmployTrainerButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/GUI/Manager/employTrainerController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Employ Trainer");
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
