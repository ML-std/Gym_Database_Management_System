import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Trainer extends Employee{
    private int hourlySalary;
    private DatabaseConnector connector;

    //Constructor for trainer
    public Trainer(String firstName, String middleName, String lastName, String address, String  password,  int SSN, int employeeID,int branchID,  String[] phoneNumber, int hourlySalary) {
        super(firstName, middleName, lastName, address, password, SSN, employeeID, branchID, phoneNumber);
        this.hourlySalary = hourlySalary;
        connector = new DatabaseConnector();
    }

    //Creates temporary trainer object to access login system
    public Trainer(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }


    //Methods for batches [Batch_ID, Start_Time, End_Time, Batch_type, Branch_ID, Employee_ID]
    public boolean createBatch(int batchID, String startTime, String endTime, String batchType, int branchID, int employeeID){
        Object[] batchObject = new Object[]{batchID,startTime,endTime,batchType,branchID, employeeID};
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
    //Test cases for trainer methods
    public static void main(String[] args) {
        Trainer trainer = new Trainer(220001);
        System.out.println("<<Creating batch>>");
        trainer.createBatch(500007,"02:50:00","03:30:00","Strength",100001,220001);
        System.out.println(trainer.getBatch(500007));
        System.out.println();
        System.out.println("<<Rearranging batch>>");
        trainer.rearrangeBatch(500007,"02:20:00","03:20:00");
        System.out.println(trainer.getBatch(500007));
        System.out.println();
        System.out.println("<<Deleting batch>>");
        trainer.removeBatch(500007);
        System.out.println(trainer.getBatch(500007));
        System.out.println();
        List<Object[]> batches = trainer.getBatchesTable();
        for (Object[] o : batches) {
            System.out.println(Arrays.toString(o));
        }
        System.out.println();
        System.out.println();
        System.out.println("<<Including customer to batch>>");
        trainer.includeCustomer(300002,500003);
        System.out.println(trainer.getAttendance(300002,true));
        System.out.println();
        System.out.println("<<Excluding customer from batch>>");
        trainer.excludeCustomer(300002,500003);
        System.out.println(trainer.getAttendance(300002,true));
        List<Object[]> attendance = trainer.getAttendanceTable();
        for ( Object[] o: attendance ) {
            System.out.println(Arrays.toString(o));
        }
    }


  }
