import org.omg.PortableInterceptor.ObjectReferenceFactory;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class DatabaseConnector {
    Connection conn;
    //Database Connector constructor that connects the database
    public DatabaseConnector(){
        try{
      String url = "jdbc:mysql://127.0.0.1:3306/gym_management_database_system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
         conn = DriverManager.getConnection(url, "root", null);}
        catch (SQLException e){
            e.fillInStackTrace();
        }


 }
    //Method for getting entire table
    protected List<Object[]> getDatabaseTable(String tableName){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM " +  tableName);
            fillList(rs,arrayList,true, true);}
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return arrayList;
    }
    // gets selected rows items with condition
    protected Object[] getDatabaseList(String selectedRow, String tableName,String condition){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        String conditionName, conditionValue;
        StringTokenizer stringTokenizer = new StringTokenizer(condition, " =");
        conditionName = stringTokenizer.nextToken();
        conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";

        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT " + selectedRow+ " FROM " + tableName+ " where " + conditionName + conditionValue ;
            ResultSet rs=stmt.executeQuery(query);
            fillList(rs,arrayList,true,false);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        //Object[] o = new Object[arrayList.size()];
        for (int i = 0; i<arrayList.size(); i++){
          //  o[i] = arrayList.get(0);
        }


        return arrayList.get(0);

    }


    //it gets selected rows items without condition
    protected ArrayList<Object> getDatabaseRowList(String selectedRow, String tableName){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();


        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT " + selectedRow+ " FROM " + tableName ;
            ResultSet rs=stmt.executeQuery(query);
            fillList(rs,arrayList,true,false);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        ArrayList<Object> objectList =  new ArrayList<>();
       // Object[] o =  new Object[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++){
           // o[i] = arrayList.get(i)[0];
            objectList.add(arrayList.get(i)[0]);
        }

        return objectList;

    }


    //Method for getting list of objects in given condition
    protected List<Object[]> getDatabaseList(String tableName,String condition){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        String conditionName, conditionValue;
        StringTokenizer stringTokenizer = new StringTokenizer(condition, " =");
        conditionName = stringTokenizer.nextToken();
        conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";

        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT * FROM " + tableName+ " where " + conditionName + conditionValue ;
            ResultSet rs=stmt.executeQuery(query);
            fillList(rs,arrayList,true,false);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


        return arrayList;

    }

    //Method for getting data from database where condition is like "CID = 300001"
    //This method gets the first item that is in the condition.
    protected Object[] getDatabaseItem(String tableName,String condition){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        String conditionName, conditionValue;
        StringTokenizer stringTokenizer = new StringTokenizer(condition, " =");
        conditionName = stringTokenizer.nextToken();
        conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";

        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT * FROM " + tableName+ " where " + conditionName + conditionValue ;
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            fillList(rs,arrayList,false,false);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return arrayList.get(0);

    }


    //Method for setting data to database where condition is like "CID = 300001"
    protected boolean setDataToDatabase(String tableName, String columnName, String newValue, String condition){
        String conditionName, conditionValue;
        StringTokenizer stringTokenizer = new StringTokenizer(condition, " =");
        conditionName = stringTokenizer.nextToken();
        conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";
        try {
            String query = "update " + tableName + " set "+ columnName + " = ? where " + conditionName + conditionValue ;
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, newValue);
        preparedStmt.executeUpdate();

    }
    catch (Exception e){
        System.out.println(e.getMessage());
        return false;
    }
        return true;
    }
    //Method for inserting data to database where valueObjects are values like
    //new Object[]{300022,"Falcon","M","Rodriguez","FEMALE","address", 1010101010,"2000-09-10","null"}
    protected boolean insertDataToDatabase(String tableName,Object[] valueObjects){
        try{
            Statement stmt=conn.createStatement();
            StringBuilder tableColumnNames = new StringBuilder();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " +  tableName);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount =  metaData.getColumnCount();
            for (int i =1;i<=columnCount;i++){
                if (i < columnCount )
                tableColumnNames.append(metaData.getColumnName(i)).append(", ");
                else  tableColumnNames.append(metaData.getColumnName(i)).append(") ");
            }
            StringBuilder valueString = new StringBuilder(" values (");
            int objectCount = valueObjects.length;
            for (int i = 0;i < objectCount; i++) {
                if (i < objectCount -1){
                    valueString.append("\"");
                valueString.append(valueObjects[i].toString());
                valueString.append("\"");
                valueString.append(",");
                }
                else
                    valueString.append(valueObjects[i].toString()).append(")");            }
            String query = " insert into " + tableName + " (" +tableColumnNames +" " + valueString + "" + ";";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
    //Method that deletes data from database
    protected boolean removeDataFromDatabase(String tableName, String condition){
        String conditionName, conditionValue;
        StringTokenizer stringTokenizer = new StringTokenizer(condition, " =");
        conditionName = stringTokenizer.nextToken();
        conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";
        boolean isDeleted;
        try {
            String query = "DELETE FROM " + tableName + " WHERE " + conditionName + conditionValue;
            Statement stmt = conn.createStatement();
            isDeleted = stmt.execute(query);

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return isDeleted;
    }
    //Fills the list for the getter methods
    private void fillList(ResultSet rs, List<Object[]> arrayList , boolean isAList, boolean hasMetaData ){
      try{
          ResultSetMetaData metaData=  rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Object[] tempArray = new Object[columnCount];
        if (isAList&&hasMetaData){
        for (int i =1;i<=columnCount;i++){
            tempArray[i-1] = metaData.getColumnName(i);
        }
        arrayList.add(tempArray);}
          while (rs.next()) {

            Object[] tmpArray = new Object[columnCount];
            for (int j =1;j<=columnCount;j++){
                    switch (metaData.getColumnTypeName(j)) {
                        case "INT":
                            tmpArray[j-1] = rs.getInt(j);
                            break;
                        //Although these are same with default, default is meant to be as ENUM type
                        //Unfortunately, there are no SQL enum types in JAVA.
                        case "VARCHAR":
                            tmpArray[j-1] = rs.getString(j);
                            break;
                        case "DATE":
                            tmpArray[j-1] = rs.getDate(j);
                            break;
                        default:
                            tmpArray[j-1] = rs.getString(j);
                            break;
                    }

            }

            arrayList.add(tmpArray);
            if (!isAList){
                break;
            }
        }

      }
      catch (SQLException e){
          System.out.println(e.getMessage());
      }
    }
    //Examples for if these works
    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        List<Object[]> o =   databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }
        ArrayList<Object> o2 = databaseConnector.getDatabaseRowList("CID", "customer");
        for (Object objects : o2) {
            System.out.println(objects.toString());
        }
        System.out.println();
        Object[] o3 = databaseConnector.getDatabaseItem("customer", " Middle_Name = M ");
        for (Object value : o3) System.out.print(value.toString() + " ");
        }/*
       databaseConnector.removeDataFromDatabase("customer", "First_Name = davai");
        o = databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }/*
        databaseConnector.insertDataToDatabase("customer", new Object[]{300022,"davai","M","lmao","FEMALE","adres", 1010101010,"2000-09-10","null"});
        o = databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }/*
      databaseConnector.setDataToDatabase("customer","SEX","MALE"," First_Name = davai");
        o = databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }*/
    }


