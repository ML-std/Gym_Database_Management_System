import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Employee  {
    private String firstName, middleName, lastName, address, password;
    private int SSN, employeeID;
    private String[] phoneNumber;
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



    //Creates Temporary employee object to access login system
    public Employee(int employeeID){
        this.employeeID = employeeID;
    }
    //Method that logs in the user and specifies their role in the app
    public  boolean employeeLogin(int employeeID,String password){
        //If employeeID is correct continue, else return false
        boolean isEmployeeID = false;
        ArrayList<Object> employeeList = connector.getDatabaseRowList("Employee_ID","employee");
        for (Object o: employeeList) {
            if (Integer.parseInt(o.toString()) == employeeID){
                isEmployeeID = true;
                break;
            }
        }
        //If password is correct continue, else return false
        if (isEmployeeID){
       ArrayList<Object> passwordItem = connector.getDatabaseRowList("Password", "employee","Employee_ID = " + employeeID);
        if (password.equals(passwordItem.get(0).toString())){
            String employeeDataShow = "";
            ArrayList<Object> employeeData = connector.getDatabaseItem( "employee","Employee_ID = " + employeeID);
            ArrayList<Object> managerIDs = connector.getDatabaseRowList("Employee_ID","manager");
            boolean hasPlace = false;

            // specify the users role in the application

            for (Object managerID : managerIDs) {
                int tmp = Integer.parseInt(managerID.toString());
                if (employeeID == tmp) {
                    //some codes here...

                    hasPlace = true;
                    employeeDataShow = "Logged as Manager ";
                    break;
                }
            }

            ArrayList<Object> receptionistIDs = connector.getDatabaseRowList("Employee_ID","receptionist");
            if (!hasPlace){
            for (Object receptionistID : receptionistIDs) {
                int tmp = Integer.parseInt(receptionistID.toString());
                if (employeeID == tmp) {
                    //some codes here...
                    employeeDataShow = "Logged as Receptionist ";
                    hasPlace =true;

                    break;
                }
            }}
            if (!hasPlace){
            ArrayList<Object> trainerIDs = connector.getDatabaseRowList("Employee_ID","trainer");
            for (Object trainerID : trainerIDs) {
                int tmp = Integer.parseInt(trainerID.toString());
                if (employeeID == tmp) {
                    //some codes here...
                    employeeDataShow = "Logged as Trainer ";
                    hasPlace =true;
                    break;
                }
            }}
            employeeDataShow = employeeDataShow + "Welcome " + employeeData.get(1).toString() + " " +
                    employeeData.get(2).toString() + " " + employeeData.get(3).toString();
            System.out.println(employeeDataShow);
            return hasPlace;
        }
        else System.out.println("invalid password");
        return false;
    }
        else System.out.println("invalid Employee ID");
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

