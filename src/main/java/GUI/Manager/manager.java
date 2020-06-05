package GUI.Manager;

import GUI.Login;
import GUI.Rules;
import GUI.TableShower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static GUI.Login.loggedEmployee;

public class manager implements Initializable {


    public Button getInformationButton;
    public TextField employeeID;
    public TextField surname;
    public TextField name;
    public ChoiceBox<String> tableChoice;
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

    @FXML
    private TextField tableName;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableChoice.getItems().add("attends");
        tableChoice.getItems().add("batches");
        tableChoice.getItems().add("cleaner");
        tableChoice.getItems().add("cleans");
        tableChoice.getItems().add("cphone");
        tableChoice.getItems().add("customer");
        tableChoice.getItems().add("customer_report");
        tableChoice.getItems().add("employee");
        tableChoice.getItems().add("ephone");
        tableChoice.getItems().add("equipment");
        tableChoice.getItems().add("equipment_maintains");
        tableChoice.getItems().add("facilities");
        tableChoice.getItems().add("fitness_branch");
        tableChoice.getItems().add("manager");
        tableChoice.getItems().add("receptionist");
        tableChoice.getItems().add("reports_maintains");
        tableChoice.getItems().add("trainer");
        tableChoice.getItems().add("trainer_reviews");
        tableChoice.getItems().add("trains");
        tableChoice.getItems().add("uses");

        username.setText(Login.welcome);
    }

    @FXML
    private void handleEmployReceptionistButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("employReceptionistController.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("employCleanerController.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("employEmployeeController.fxml"));
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("loginController.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Gym Database Management System");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handleTableShowAction(final ActionEvent actionEvent) {
       TableShower.showTable(((ServerSide.Manager)loggedEmployee).getTable(tableChoice.getValue()));

    }

    public void handleGetInformationButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> nameTexts = new ArrayList<>();
        nameTexts.add(name);
        nameTexts.add(surname);
        List<Object[]> infoList = new ArrayList<>();
        boolean hasInfo = false;
        if (!employeeID.getText().equals("")){
            infoList = ((ServerSide.Manager)loggedEmployee).getEmployeeInfo(Integer.parseInt(employeeID.getText()));
            hasInfo = !infoList.isEmpty();
        }
        else if (!Rules.hasAnEmptyInput(nameTexts)){
            infoList = ((ServerSide.Manager)loggedEmployee).getEmployeeInfo(name.getText(),surname.getText());
            hasInfo = !infoList.isEmpty();
        }
        else return;

        if (hasInfo){
        TableShower.showTable(infoList);
    }
        else Rules.alertHandler(false,"put valid inputs");

    }
}
