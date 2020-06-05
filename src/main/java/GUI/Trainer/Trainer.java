package GUI.Trainer;

        import GUI.Login;
        import GUI.TableShower;
        import com.mysql.cj.xdevapi.Table;
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
        import java.util.Objects;
        import java.util.ResourceBundle;

        import static GUI.Login.loggedEmployee;


public class Trainer implements Initializable {

    @FXML
    private Button attendsButton, batchesButton, backButton, attendanceTableButton, batchTableButton;

    @FXML
    private Label username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(Login.welcome);
    }

    @FXML
    private void handleAttendsButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("trainerAttendsController.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("trainerBatchesController.fxml"));
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

    public void handleBatchTableButtonAction(ActionEvent actionEvent) {

        TableShower showTable = new TableShower();

        showTable.showTable(((ServerSide.Trainer) loggedEmployee).getAttendanceTable());
    }

    public void handleAttendanceTableButtonAction(ActionEvent actionEvent) {
        int employeeID = loggedEmployee.getEmployeeID();
        TableShower showTable = new TableShower();
        showTable.showTable(((ServerSide.Trainer) loggedEmployee).getBatchesTable());
        }
}
