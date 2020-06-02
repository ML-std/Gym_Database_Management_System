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
    public static void main(String[] args) {
        Trainer tmp2 = new Trainer(220001);
        tmp2.employeeLogin(220010,"237010");
     //   ArrayList<Object> e = tmp2.connector.getDatabaseRowList("Employee_ID","manager");
      //  System.out.println(tmp2.createBatch(100344,"12:30:00","12:40:00","strength",100001, tmp2.getEmployeeID()));
      //  System.out.println(tmp2.rearrangeBatch(100341,"12:40:00","13:30:00"));
      /*  System.out.println(tmp2.removeBatch(100110));
      List<Object> arrayList = tmp2.getBatch(100111);
        for (Object objects : arrayList) {
            System.out.println(objects.toString());
        }
        List<Object[]> arrayList2 = tmp2.getBatchesTable();
        for (Object[] objects : arrayList2) {
            System.out.println(Arrays.toString(objects));
        }*//*
        System.out.println( tmp2.includeCustomer(300001,500005));
      System.out.println( tmp2.excludeCustomer(300002,500003));
        List<Object> arrayList = tmp2.getAttendance(500004,true);
        for (Object objects : arrayList) {
            System.out.println(objects.toString());
        }*/
        //String firstName, String middleName, String lastName, String address,String password, int SSN, int employeeID, int branchID, String[] phoneNumber
        Manager manager = new Manager(200001);
      //  Employee employee = new Employee("hiyar","eşek","omg","lololol","202020",15696857,19093939,100001,new String[]{"534-239-212"});
        //manager.hireEmployee(employee);
       // System.out.println(manager.dismissEmployee(19093939));
    }

  }
