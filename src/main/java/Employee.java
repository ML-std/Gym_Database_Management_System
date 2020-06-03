
import java.util.ArrayList;

public  class Employee  {
    private String firstName, middleName, lastName, address, password;
    private int SSN, employeeID, branchID;
    private String[] phoneNumber;
    private DatabaseConnector connector;

    //Constructor of Employee
    public Employee(String firstName, String middleName, String lastName, String address,String password, int SSN, int employeeID, int branchID, String[] phoneNumber ) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.SSN = SSN;
        this.employeeID = employeeID;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.branchID = branchID;
        connector = new DatabaseConnector();
    }

    //Creates Temporary employee object to access login system
    public Employee(int employeeID){
        this.employeeID = employeeID;
        connector = new DatabaseConnector();
    }

    //Method that logs in the user and specifies their role in the app
    public  String employeeLogin(int employeeID,String password){
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

                    break;
                }
            }}
            employeeDataShow = employeeDataShow + "Welcome " + employeeData.get(1).toString() + " " +
                    employeeData.get(2).toString() + " " + employeeData.get(3).toString();
            return employeeDataShow;
        }
        else
        return "invalid password";
    }
        else
            return "invalid ID";
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
    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //Testing for Employee Class
    public static void main(String[] args) {
        //Test for Login Method in working cases
        Employee manager = new Employee(200001);
        System.out.println(manager.employeeLogin(manager.employeeID, 240698 +""));
        Employee receptionist = new Employee(210001);
        System.out.println(receptionist.employeeLogin(receptionist.employeeID, "" + 249987));
        Employee trainer = new Employee(220001);
        System.out.println(trainer.employeeLogin(trainer.employeeID,"" + 220011));
        //Test for Invalid ID and invalid password
        Employee invalidID = new Employee(1519894);
        System.out.println(invalidID.employeeLogin(invalidID.employeeID,"klmpokmp"));
        Employee invalidPwd = new Employee(220001);
        System.out.println(invalidPwd.employeeLogin(invalidPwd.employeeID, "5486496"));

    }
}

