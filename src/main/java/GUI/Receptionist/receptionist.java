package GUI.Receptionist;

import GUI.Login;
import GUI.Rules;
import GUI.TableShower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static GUI.Login.loggedEmployee;
import static GUI.Rules.alertHandler;
import static GUI.Rules.hasAnEmptyInput;
import static java.lang.Double.NaN;

public class receptionist implements Initializable {

    public Button backButton;
    public RadioButton trainerRadioButton;
    public ToggleGroup averageRatingGroup;
    public RadioButton facilityRadioButton;
    public Button customerInfoButton;
    public TextField customerIDInfo;
    public TableView customerInfoTable;
    public Button getCustomerReport;
    public TableView customerReportTable;
    public TextField customerIDReport;
    public Label averageLabel;
    public Button averageRatingButton;
    public TextField trainerOrFacilityID;
    @FXML
    private Button facilityReview;

    @FXML
    private Button trainerReview;

    @FXML
    private Button equipment;

    @FXML
    private Button customer;

    @FXML
    private Button facilityUsage;

    @FXML
    private Button customerReport;

    @FXML
    private Label username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(Login.welcome);
        customerInfoTable.setVisible(false);
        customerReportTable.setVisible(false);
        averageRatingGroup = new ToggleGroup();
        trainerRadioButton.setToggleGroup(averageRatingGroup);
        facilityRadioButton.setToggleGroup(averageRatingGroup);

    }

    @FXML
    private void handleFacilityReviewButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("customerReviewFacilityController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Facility Review");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void handleTrainerReviewButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("customerReviewTrainerController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Trainer Review");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEquipmentButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("equipmentAndReportController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Equipment");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCustomerButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("customerController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customers");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFacilityUsageButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("customerUsageFacilityController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Facility Usage");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCustomerReportButtonAction(final ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("customerReportController.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer Reports");
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

    public void handleCustomerInfoButtonAction(ActionEvent actionEvent) {
         ArrayList<TextField> textFields = new ArrayList<>();
         textFields.add(customerIDInfo);
        if (!Rules.hasAnEmptyInput(textFields)){

        List<Object[]> objects = ((ServerSide.Receptionist) loggedEmployee).getCustomerByID(Integer.parseInt(customerIDInfo.getText()));
        if (objects.isEmpty()){
            alertHandler(false,"Please control customer ID");
        }
        else{

        customerInfoTable = TableShower.buildData((ArrayList<Object[]>) objects,customerInfoTable);
        customerInfoTable.setVisible(true);
        customerInfoTable.refresh();

        }}

    }

    public void handleGetCustomerReportAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        textFields.add(customerIDReport);
        if (!Rules.hasAnEmptyInput(textFields)){
            List<Object[]> objects = ((ServerSide.Receptionist) loggedEmployee).getCustomerReportByID(Integer.parseInt(customerIDReport.getText()));
            if (objects.isEmpty()){
                alertHandler(false,"Please control customer ID");
            }
            else {
                customerReportTable = TableShower.buildData((ArrayList<Object[]>) objects, customerReportTable);
                customerReportTable.setVisible(true);
                customerReportTable.refresh();
            }
        }
    }

    public void handleAverageRatingButtonAction(ActionEvent actionEvent) {
        ArrayList<TextField> textFields = new ArrayList<>();
        double average = 0;
        textFields.add(trainerOrFacilityID);
        if (!hasAnEmptyInput(textFields)){
        if (trainerRadioButton.isSelected()){
           average = ((ServerSide.Receptionist) loggedEmployee).getAverageRatingOfTrainer(Integer.parseInt(trainerOrFacilityID.getText()));
        }
        else if (facilityRadioButton.isSelected()){
            average = ((ServerSide.Receptionist) loggedEmployee).getAverageRatingOFacility(Integer.parseInt(trainerOrFacilityID.getText()));
        }
        else{
            alertHandler(false,"Please select an ID type");
            return;
        }

        }
        if (Double.isNaN(average)){
            alertHandler(false,"Wrong ID");
        }
        else {
        averageLabel.setText("Average Rating :");
        averageLabel.setText(averageLabel.getText() + " " + average);}
}}
