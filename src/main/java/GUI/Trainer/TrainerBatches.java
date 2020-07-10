package GUI.Trainer;

import GUI.Rules;
import javafx.application.Platform;
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
import java.util.Optional;
import java.util.ResourceBundle;
import static GUI.Login.loggedEmployee;
import static GUI.Rules.alertHandler;
import static GUI.Rules.hasAnEmptyInput;

public class TrainerBatches implements Initializable {


    @FXML
    public TableView<ObservableList> batchView;
    @FXML
    private TableColumn<ObservableList, String> batchIDColumn;
    @FXML
    private TableColumn<ObservableList, String> startTimeColumn;
    @FXML
    private TableColumn<ObservableList, String> endTimeColumn;
    @FXML
    private TableColumn<ObservableList, String> batchTypeColumn;
    @FXML
    private TableColumn<ObservableList, String> branchIDColumn;
    @FXML
    private TableColumn<ObservableList, String> employeeIDColumn;

    @FXML
    private Button backButton,addButton,rearrangeBatchButton,getBatchButton,removeBatchButton;

    @FXML
    private TextField batchID,startTime,endTime,batchType,branchID,employeeID,batchIDRearrange,batchIDRemove,batchIDGet,startTimeRearrange,endTimeRearrange;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        batchView.setVisible(false);
        batchIDColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("batchIDColumn"));
        batchIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("startTimeColumn"));
        startTimeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("endTimeColumn"));
        endTimeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(2).toString());
            }
        });
        batchTypeColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("batchTypeColumn"));
        batchTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(3).toString());
            }
        });
        branchIDColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("branchIDColumn"));
        branchIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(4).toString());
            }
        });
        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<ObservableList, String>("employeeIDColumn"));
        employeeIDColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(5).toString());
            }
        });
    }


    @FXML
    private void handleRearrangeBatchButtonAction(final ActionEvent event){
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(batchIDRearrange);
        textFields.add(startTimeRearrange);
        textFields.add(endTimeRearrange);

        if (!Rules.hasAnEmptyInput(textFields)){
            String[] times = startTimeRearrange.getText().split(":");
            String[] endTimes = endTimeRearrange.getText().split(":");
            String tmpStartTime = times[0] + times[1] + times[2] ;
            String tmpEndTime = endTimes[0] + endTimes[1] + endTimes[2];
        boolean isRearranged = ((ServerSide.Trainer) loggedEmployee).rearrangeBatch(Integer.parseInt(batchIDRearrange.getText()),tmpStartTime, tmpEndTime);
        alertHandler(isRearranged,"batch rearrange operation has failed, please check your inputs");
    }}

    @FXML
    private void handleGetBatchButtonAction(final ActionEvent event){
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(batchIDGet);
        if (!hasAnEmptyInput(textFields)){
          ObservableList<ObservableList> data2;
         data2 = FXCollections.observableArrayList();
        ObservableList<String> row = FXCollections.observableArrayList();
        ArrayList<Object> o = (ArrayList<Object>) ((ServerSide.Trainer) loggedEmployee).getBatch(Integer.parseInt(batchIDGet.getText()));
        if (o.isEmpty()){
            alertHandler(false,"");
        }
        else{
        for (Object object : o ) {
            row.add(object.toString());
            System.out.println(object.toString());
        }
        for (Object obj: data2) {
            System.out.print(obj.toString() + "  ");
        }
        data2.add(row);
        batchView.setItems(data2);
        batchView.setVisible(true);
    }
    }}

    @FXML
    private void handleRemoveBatchButtonAction(final ActionEvent event){
        boolean isRemoved = ((ServerSide.Trainer) loggedEmployee).removeBatch(Integer.parseInt(batchIDRemove.getText()));
        alertHandler(isRemoved, "batch remove operation has failed, please check your inputs");
    }






    @FXML
    private void handleAddButtonAction(final ActionEvent event){
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(batchType);
        textFields.add(startTime);
        textFields.add(endTime);
        if (!hasAnEmptyInput(textFields)){
        String[] times = startTime.getText().split(":");
        String[] endTimes = endTime.getText().split(":");
        String tmpStartTime = times[0] + times[1] + times[2] ;
        String tmpEndTime = endTimes[0] + endTimes[1] + endTimes[2];

     boolean isAdded =  ((ServerSide.Trainer)loggedEmployee).createBatch(tmpStartTime,tmpEndTime,batchType.getText());
       alertHandler(isAdded,"Batch add operation has failed, please check your inputs");
    }}

    @FXML
    private void handleBackButtonAction(final ActionEvent event){
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }



}
