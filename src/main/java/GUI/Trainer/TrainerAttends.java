package GUI.Trainer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static GUI.Login.loggedEmployee;
import static GUI.Rules.alertHandler;
import static GUI.Rules.hasAnEmptyInput;

public class TrainerAttends implements Initializable {

    public TableView attendanceView;
    public TableColumn batchIDColumn;
    public TableColumn customerIDColumn;
    @FXML
    private Button backButton,includeButton,excludeButton,getAttendanceButton;

    @FXML
    private TextField customerID,batchID,customerOrBatchID;

    @FXML
    private CheckBox isCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        attendanceView.setVisible(false);
        batchIDColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("batchIDColumn"));
        batchIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("customerIDColumn"));
        customerIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });
    }

    @FXML
    private void handleExcludeButtonAction(final ActionEvent event){
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(customerID);
        textFields.add(batchID);
        if (!hasAnEmptyInput(textFields)){
        boolean isRemoved = ((ServerSide.Trainer) loggedEmployee).excludeCustomer(Integer.parseInt(customerID.getText()),Integer.parseInt(batchID.getText()));
        alertHandler(isRemoved, "Excluding attendance operation has failed, please check your inputs" );
    }}

    @FXML
    private void handleIncludeButtonAction(final ActionEvent event){
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(customerID);
        textFields.add(batchID);
        if (!hasAnEmptyInput(textFields)){
        boolean isIncluded = ((ServerSide.Trainer) loggedEmployee).includeCustomer(Integer.parseInt(customerID.getText()),Integer.parseInt(batchID.getText()));
        alertHandler(isIncluded,"Including attendance operation has failed, please check your inputs" );

    }}

    @FXML
    private void handleGetAttendanceButtonAction(final ActionEvent event) {
        ArrayList<Object> o;
        ObservableList<ObservableList> data2;
        data2 = FXCollections.observableArrayList();
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(customerOrBatchID);
        if (!hasAnEmptyInput(textFields)) {
            o = (ArrayList<Object>) ((ServerSide.Trainer) loggedEmployee).getAttendance(Integer.parseInt(customerOrBatchID.getText()), isCustomer.isSelected());

            for (Object object : o) {
                ObservableList<String> row = FXCollections.observableArrayList();
                if (!isCustomer.isSelected()) {
                    row.add(customerOrBatchID.getText());
                    row.add(object.toString());
                } else {
                    row.add(object.toString());
                    row.add(customerOrBatchID.getText());
                }
                data2.add(row);
                System.out.println(object.toString());
            }

            for (Object obj : data2) {
                System.out.print(obj.toString() + "  ");
            }

            attendanceView.setItems(data2);
            attendanceView.setVisible(true);
        }
    }
    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }


}
