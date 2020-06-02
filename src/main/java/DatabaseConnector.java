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
            System.out.println(e.getMessage());
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
    protected ArrayList<Object> getDatabaseRowList(String selectedRow, String tableName, String condition){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        String queryString = conditionRegulator(condition);

        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT " + selectedRow+ " FROM " + tableName+ " where " + queryString ;
            ResultSet rs=stmt.executeQuery(query);
            fillList(rs,arrayList,true,false);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        ArrayList<Object> tmp = new ArrayList<>();
        for (Object[] objects : arrayList) {
            tmp.add(objects[0]);
        }


        return tmp;

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
        for (Object[] objects : arrayList) {
            // o[i] = arrayList.get(i)[0];
            objectList.add(objects[0]);
        }

        return objectList;

    }


    //Method for getting list of objects in given condition
    protected List<Object[]> getDatabaseList(String tableName,String condition){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        String queryString = conditionRegulator(condition);

        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT * FROM " + tableName+ " where " + queryString ;
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
    protected ArrayList<Object> getDatabaseItem(String tableName,String condition){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        String queryString = conditionRegulator(condition);

        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT * FROM " + tableName+ " where " + queryString;
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            fillList(rs,arrayList,false,false);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return new ArrayList<>(Arrays.asList(arrayList.get(0)));

    }


    //Method for setting data to database where condition is like "CID = 300001"
    protected boolean setDataToDatabase(String tableName, String columnName, String newValue, String condition){
        String queryString = conditionRegulator(condition);
        try {
            String query = "update " + tableName + " set "+ columnName + " = ? where " +queryString ;
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
       String queryString = conditionRegulator(condition);

        try {
            String normalString = queryString + "";
            System.out.println(normalString);
            Object o = getDatabaseList(tableName,normalString,true);

            if (!(o.toString().equals("[]"))){
            String query = "DELETE FROM " + tableName + " WHERE " + queryString;
                System.out.println(1111);
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        }
            else
                System.out.println("data is not valid ");
                return false;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

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
        boolean hasMoreElement=false;
         do {
            if (isAList){
               hasMoreElement =  rs.next();
            }
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
      } while (hasMoreElement&&!rs.isLast());

      }
      catch (SQLException e){
          System.out.println(e.getMessage());
      }
    }
    private String conditionRegulator(String condition){
        String conditionName = "", conditionValue = "";
        StringBuilder queryString = new StringBuilder();
        if (condition.contains("AND")){
            String[] strings = condition.split("AND");
            for (int i = 0; i < strings.length;i++) {
                String s = strings[i];
                StringTokenizer stringTokenizer = new StringTokenizer(s, " =");
                conditionName =  stringTokenizer.nextToken();
                conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";
                if (i==strings.length-1)
                    queryString.append(conditionName).append(conditionValue);
                else{
                    queryString.append(conditionName).append(conditionValue).append(" AND ");
                }
            }

            System.out.println(queryString);
        }
        else if (condition.contains("OR")){
            String[] strings = condition.split("OR");
            for (int i = 0; i < strings.length;i++) {
                String s = strings[i];
                StringTokenizer stringTokenizer = new StringTokenizer(s, " =");
                conditionName =  stringTokenizer.nextToken();
                conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";
                if (i==strings.length-1)
                    queryString.append(conditionName).append(conditionValue);
                else{
                    queryString.append(conditionName).append(conditionValue).append(" OR ");
                }
            }

            System.out.println(queryString);
        }
        else {

            StringTokenizer stringTokenizer = new StringTokenizer(condition, " =");
            conditionName = stringTokenizer.nextToken();
            conditionValue =" = " + "\"" + stringTokenizer.nextToken() + "\"";
            queryString.append(conditionName).append(conditionValue);
        }
        return (queryString + "");
    }
    private List<Object[]> getDatabaseList(String tableName,String condition,boolean isRegularCondition){
        // List that stores String arrays as a List
        List<Object[]> arrayList = new ArrayList<>();
        try {
            Statement stmt=conn.createStatement();
            String query ="SELECT * FROM " + tableName+ " where " + condition ;
            ResultSet rs=stmt.executeQuery(query);
            fillList(rs,arrayList,true,false);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


        return arrayList;

    }

    //Examples for if these works
    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        System.out.println("<<Full Of Table Writing>>");
        List<Object[]> o =   databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }
        System.out.println();
        System.out.println("<<Getting Row List With Condition>>");
        System.out.println();
        ArrayList<Object> o2 = databaseConnector.getDatabaseRowList("Middle_Name", "customer","CID = 300001");
        for (Object objects : o2) {
            System.out.print(objects.toString() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("<<Getting Row List Without Condition>>");
        System.out.println();
        ArrayList<Object> o3 = databaseConnector.getDatabaseRowList("CID", "customer");
        for (Object objects : o3) {
            System.out.print(objects.toString() + "  ");
        }
        System.out.println();
        System.out.println();
        System.out.println("<<Getting Database item With Condition>>");
        System.out.println();
        ArrayList<Object> o4 = databaseConnector.getDatabaseItem("customer", " CID = 300001 ");
        for (Object value : o4) System.out.print(value.toString() + " ");
        System.out.println();
        System.out.println();
        System.out.println("<<Inserting a data from database>>");
        System.out.println();
        databaseConnector.insertDataToDatabase("customer", new Object[]{300022,"davai","M","lmao","FEMALE","adres", 1010101010,"2000-09-10","null"});
        o = databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }
        System.out.println();
        System.out.println("<<Setting a data from database>>");
        System.out.println();
        databaseConnector.setDataToDatabase("customer","SEX","FEMALE"," Middle_Name = M");
        o = databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }
        System.out.println();
        System.out.println("<<Removing a data from database>>");
        System.out.println();
       databaseConnector.removeDataFromDatabase("customer", "Middle_Name = O");
        o = databaseConnector.getDatabaseTable("customer");
        for (Object[] objects : o) {
            System.out.println(Arrays.toString(objects));
        }


    }}


