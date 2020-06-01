import javax.swing.*;
import java.util.ArrayList;

public abstract class Employee  {
    private String firstName, middleName, lastName, address, password;
    private int SSN, employeeID;
    private String[] phoneNumber;
    Object employeeThatExists;
    DatabaseConnector connector = new DatabaseConnector();

    //Constructor of Employee
    public Employee(String firstName, String middleName, String lastName, String address,String password, int SSN, int employeeID, String[] phoneNumber ) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.SSN = SSN;
        this.employeeID = employeeID;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public Employee(Object employeeThatExists ) {
       this.employeeThatExists = employeeThatExists;
    }
    public Employee(){
    }
    //Method that logs in the user and specifies their role in the app
    public  boolean employeeLogin(int employeeID,String password){
        Object[] o = connector.getDatabaseList("Password", "employee","Employee_ID = " + employeeID);
        if (password.equals(o[0].toString())){
            System.out.println("it works!");
            ArrayList<Object> managerIDs = connector.getDatabaseRowList("Employee_ID","manager");
            ArrayList<Object> receptionistIDs = connector.getDatabaseRowList("Employee_ID","receptionist");
            ArrayList<Object> trainerIDs = connector.getDatabaseRowList("Employee_ID","trainer");
            for (Object managerID : managerIDs) {
                System.out.println(managerID);
                int tmp = Integer.parseInt(managerID.toString());
                System.out.println(tmp + "   " + employeeID);
                if (employeeID == tmp) {
                    //some codes here...
                    System.out.println("dırındırırıın");
                    return true;
                }
            }
            for (Object receptionistID : receptionistIDs) {
                System.out.println(receptionistID);
                int tmp = Integer.parseInt(receptionistID.toString());
                System.out.println(tmp + "   " + employeeID);
                if (employeeID == tmp) {
                    //some codes here...
                    System.out.println("dırındırırıın2");
                    return true;
                }
            }
            for (Object trainerID : trainerIDs) {
                System.out.println(trainerID);
                int tmp = Integer.parseInt(trainerID.toString());
                System.out.println(tmp + "   " + employeeID);
                if (employeeID == tmp) {
                    //some codes here...
                    System.out.println("dırındırırıın3");
                    return true;
                }
            }


        }
        else System.out.println("invalid password");
        return false;
    }


    //Getter and setter methods for Employee
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

