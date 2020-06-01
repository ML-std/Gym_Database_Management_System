import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trainer extends Employee{
    private int hourlySalary;
    DatabaseConnector connector;
    Trainer tmpTrainer;
    //Constructor for trainer
    public Trainer(String firstName, String middleName, String lastName, String address, String  password,  int SSN, int employeeID, String[] phoneNumber, int hourlySalary) {
        super(firstName, middleName, lastName, address, password, SSN, employeeID, phoneNumber);
        this.hourlySalary = hourlySalary;
        connector = new DatabaseConnector();
    }
    public Trainer(Object[] trainerThatExists, int hourlySalary) {
        super(trainerThatExists);
        this.hourlySalary = hourlySalary;
        connector = new DatabaseConnector();
    }
    //for temp constructor
    public Trainer(){
        connector = new DatabaseConnector();
    }


    //Methods for batches [Batch_ID, Start_Time, End_Time, Batch_type, Branch_ID, Employee_ID]
    private boolean createBatches(int batchID, Time startTime, Time endTime, String batchType, int branchID){
        Object[] o = new Object[]{batchID, startTime, endTime, batchType, branchID};
        connector.insertDataToDatabase("batches", o);

        return true;
    }
    private boolean rearrangeBatches(){
        return true;
    }
    private boolean removeBatches(){
        return true;
    }
    public static void getBatches(){
    }
    //methods for attendance of customers for batches
    private boolean includeCustomer(){
        return true;
    }
    private boolean excludeCustomer(){
        return true;
    }
    public static void getAttendance(){

    }


    //Getter and setter method for trainer
    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public static void main(String[] args) {
      Trainer tmp2 = new Trainer();
        System.out.println(tmp2.employeeLogin(220010,"237010"));
      ArrayList<Object> e = tmp2.connector.getDatabaseRowList("Employee_ID","manager");


        }}
