package ServerSide;

import java.sql.Connection;
import java.util.ArrayList;

public  class Employee {
    private String firstName, middleName, lastName, address, password;
    private int SSN, employeeID, branchID;
    private String[] phoneNumber;
    private DatabaseConnector connector;

    public static class stringBooleanClass {
        String aString;
        boolean aBoolean;
        Employee anEmployee;

        public stringBooleanClass(String string, boolean aBoolean, Employee anEmployee) {
            this.aString = string;
            this.aBoolean = aBoolean;
            this.anEmployee = anEmployee;
        }

        public String getString() {
            return aString;
        }

        public void setString(String string) {
            this.aString = string;
        }

        public boolean isaBoolean() {
            return aBoolean;
        }

        public void setABoolean(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public Employee getAnEmployee() {
            return anEmployee;
        }

        public void setAnEmployee(Employee anEmployee) {
            this.anEmployee = anEmployee;
        }
    }

    //Constructor of Employee
    public Employee(int employeeID, String firstName, String middleName, String lastName, String address, String password, int branchID, int SSN, String[] phoneNumber) {
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

    //Since this is an auto-incremented employee ID, it is not required in constructor
    public Employee(String firstName, String middleName, String lastName, String address, String password, int branchID, int SSN) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.SSN = SSN;
        this.password = password;
        this.branchID = branchID;
        connector = new DatabaseConnector();
    }

    //Creates Temporary employee object to access login system
    public Employee(int employeeID) {
        this.employeeID = employeeID;
        connector = new DatabaseConnector();
    }

    //Method that logs in the user and specifies their role in the app
    public stringBooleanClass employeeLogin(int employeeID, String password) {
        //If employeeID is correct continue, else return false
        boolean isEmployeeID = false;
        ArrayList<Object> employeeList = connector.getDatabaseRowList("Employee_ID", "employee");
        for (Object o : employeeList) {
            if (Integer.parseInt(o.toString()) == employeeID) {
                isEmployeeID = true;
                break;
            }
        }

        //If password is correct continue, else return false
        if (isEmployeeID) {
            ArrayList<Object> passwordItem = connector.getDatabaseRowList("Password", "employee", "Employee_ID = " + employeeID);
            if (password.equals(passwordItem.get(0).toString())) {
                Employee employeeThatLoggedIn = null;
                String employeeDataShow = "";
                ArrayList<Object> employeeData = connector.getDatabaseItem("employee", "Employee_ID = " + employeeID);
                ArrayList<Object> managerIDs = connector.getDatabaseRowList("Employee_ID", "manager");
                boolean hasPlace = false;

                // specify the users role in the application

                for (Object managerID : managerIDs) {
                    int tmp = Integer.parseInt(managerID.toString());
                    if (employeeID == tmp) {
                        employeeThatLoggedIn = new Manager(Integer.parseInt(employeeData.get(0).toString()), employeeData.get(1).toString(), employeeData.get(2).toString(),
                                employeeData.get(3).toString(), employeeData.get(4).toString(), employeeData.get(5).toString(),
                                Integer.parseInt(employeeData.get(6).toString()), Integer.parseInt(employeeData.get(7).toString()), new String[]{""}, 0);
                        hasPlace = true;
                        employeeDataShow = "Logged as Manager, ";
                        break;
                    }
                }

                ArrayList<Object> receptionistIDs = connector.getDatabaseRowList("Employee_ID", "receptionist");
                if (!hasPlace) {
                    for (Object receptionistID : receptionistIDs) {
                        int tmp = Integer.parseInt(receptionistID.toString());
                        if (employeeID == tmp) {
                            employeeThatLoggedIn = new Receptionist(Integer.parseInt(employeeData.get(0).toString()), employeeData.get(1).toString(), employeeData.get(2).toString(),
                                    employeeData.get(3).toString(), employeeData.get(4).toString(), employeeData.get(5).toString(),
                                    Integer.parseInt(employeeData.get(6).toString()), Integer.parseInt(employeeData.get(7).toString()), new String[]{""}, 0);
                            employeeDataShow = "Logged as Receptionist, ";
                            hasPlace = true;

                            break;
                        }
                    }
                }
                if (!hasPlace) {
                    ArrayList<Object> trainerIDs = connector.getDatabaseRowList("Employee_ID", "trainer");
                    for (Object trainerID : trainerIDs) {
                        int tmp = Integer.parseInt(trainerID.toString());
                        if (employeeID == tmp) {
                            employeeThatLoggedIn = new Trainer(Integer.parseInt(employeeData.get(0).toString()), employeeData.get(1).toString(), employeeData.get(2).toString(),
                                    employeeData.get(3).toString(), employeeData.get(4).toString(), employeeData.get(5).toString(),
                                    Integer.parseInt(employeeData.get(6).toString()), Integer.parseInt(employeeData.get(7).toString()), new String[]{""}, 0);
                            employeeDataShow = "Logged as Trainer, ";
                            hasPlace = true;
                            break;
                        }
                    }
                }
                employeeDataShow = employeeDataShow + "Welcome " + employeeData.get(1).toString() + " " +
                        employeeData.get(2).toString() + " " + employeeData.get(3).toString();
                return new stringBooleanClass(employeeDataShow, hasPlace, employeeThatLoggedIn);
            } else
                return new stringBooleanClass("Invalid Password", false, null);
        } else
            return new stringBooleanClass("Invalid Employee ID", false, null);
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

    public Connection getConnector() {
        return connector.conn;
    }

}