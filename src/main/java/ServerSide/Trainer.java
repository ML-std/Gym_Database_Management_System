package ServerSide;

import java.util.Arrays;
import java.util.List;


public class Trainer extends Employee{
    private int hourlySalary;
    private DatabaseConnector connector;

    //Constructor for trainer
    public Trainer(int employeeID,String firstName, String middleName, String lastName, String address, String  password ,int branchID,int SSN , String[] phoneNumber, int hourlySalary) {
        super(employeeID, firstName, middleName, lastName, address, password, branchID, SSN, phoneNumber);
        this.hourlySalary = hourlySalary;
        connector = new DatabaseConnector();
    }

    //Creates temporary trainer object to access login system
    public Trainer(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }


    //Methods for batches [Batch_ID, Start_Time, End_Time, Batch_type, Branch_ID, Employee_ID]
    public boolean createBatch( String startTime, String endTime, String batchType){
        Object[] batchObject = new Object[]{"0",startTime,endTime,batchType,this.getBranchID(),this.getEmployeeID()};
        return connector.insertDataToDatabase("batches", batchObject);
    }
    // Method for rearranging time for a specified batch
    public boolean rearrangeBatch(int batchID, String startTime, String endTime){
        boolean startTimeIsUpdated,endTimeIsUpdated;
        startTimeIsUpdated = connector.setDataToDatabase("batches","Start_Time",startTime,"Batch_ID = " + batchID);
        endTimeIsUpdated = connector.setDataToDatabase("batches","End_Time",endTime,"Batch_ID = " + batchID);
        return startTimeIsUpdated&&endTimeIsUpdated;
    }
    public boolean removeBatch(int batchID){
        return connector.removeDataFromDatabase("batches" , "Batch_ID = " + batchID );
    }
    public List<Object>  getBatch(int batchID){
        return connector.getDatabaseItem("batches", "Batch_ID = " + batchID) ;
    }
    public List<Object[]>  getBatchesTable(){
       return connector.getDatabaseTable("batches");
    }
    //methods for attendance of customers for batches
    public boolean includeCustomer(int customerID,int batchID){
        return connector.insertDataToDatabase("attends",new Object[]{customerID,batchID});
    }
    public boolean excludeCustomer(int customerID,int batchID){
       // System.out.println("CID = " + customerID + " AND" + " Batch_ID = " + batchID );
        return connector.removeDataFromDatabase("attends","CID = " + customerID + " AND " + "Batch_ID = " + batchID );
    }
    public List<Object[]> getAttendanceTable(){
        return connector.getDatabaseTable("attends");
    }
    public List<Object> getAttendance(int customerOrBatchID,boolean isCustomer){
        String selectedRow;
        if (isCustomer){
          selectedRow  = "Batch_ID";
            return connector.getDatabaseRowList(selectedRow,"attends", "CID = " + customerOrBatchID);
        }
        else {
            selectedRow = "CID";
            return connector.getDatabaseRowList(selectedRow,"attends", "Batch_ID = " + customerOrBatchID);
        }
    }

    //Getter and setter method for trainer
    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

  }
