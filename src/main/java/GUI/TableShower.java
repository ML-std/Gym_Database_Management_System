package GUI;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import ServerSide.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Narayan
 */

public class TableShower {

    //TABLE VIEW AND DATA
    private static ObservableList<ObservableList> data;
    private static TableView tableview;



    //CONNECTION DATABASE
    public static void buildData(List<Object[]> objects){

        data = FXCollections.observableArrayList();
        try{


            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
           for(int i=0 ; i < objects.get(0).length; i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(objects.get(0)[i].toString());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            for (int i = 0; i < objects.size()-1; i++) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int j = 0 ; j < objects.get(i).length; j++){
                    //Iterate Column
                    row.add(objects.get(i+1)[j].toString());
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    public static TableView buildData( ArrayList<Object[]> objects,TableView tableView){

        try{
            ObservableList<ObservableList> data = FXCollections.observableArrayList();

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i < objects.get(0).length; i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(objects.get(0)[i].toString());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableView.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            for (int i = 0; i < objects.size()-1; i++) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int j = 0 ; j < objects.get(i).length; j++){
                    //Iterate Column
                    row.add(objects.get(i+1)[j].toString());
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableView.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return tableView;
    }

    public static void showTable(List<Object[]> objects){
        Stage stage = new Stage();
         tableview = new TableView();
        buildData((ArrayList)objects);

        //Main Scene
        Scene scene = new Scene(tableview);
        stage.setScene(scene);
        stage.setWidth(900);
        stage.setHeight(600);
        stage.setTitle("Table");
        stage.show();
    }
    public static void showTable(ArrayList<Object> objects, String tableName){
        Stage stage = new Stage();
        tableview = new TableView();
        //buildData(objects, tableName);

        //Main Scene
        Scene scene = new Scene(tableview);
        stage.setScene(scene);
        stage.show();
    }
}
