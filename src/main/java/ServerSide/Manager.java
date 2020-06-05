package ServerSide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager extends Employee{
    private int monthlySalary;
    private DatabaseConnector connector;
    //Constructor for manager
    public Manager(int employeeID,String firstName, String middleName, String lastName, String address, String password, int branchID, int SSN,  String[] phoneNumbers, int monthlySalary) {
        super(employeeID, firstName, middleName, lastName, address, password, branchID, SSN, phoneNumbers);
        this.monthlySalary = monthlySalary;
        connector = new DatabaseConnector();
    }


    //Creates temporary manager object to access login system
    public Manager(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }
    public List<Object[]> getTable(String tableName){
        return connector.getDatabaseTable(tableName);
    }
    public List<Object> getDataItem(String tableName, String condition){
        return connector.getDatabaseItem(tableName,condition);
    }

    //Methods for modifying employees
    public List<Object[]> getEmployeeTable(){
        return connector.getDatabaseTable("employee");
    }
    public List<Object> getEmployee(int EmployeeID){
        return connector.getDatabaseItem("employee","Employee_ID = " + EmployeeID);
    }
    private boolean hireEmployee(Employee employee,String[] phoneNumbers){
        boolean isEmployeeAdded;
        boolean arePhonesAdded = false;
        isEmployeeAdded = connector.insertDataToDatabase("employee",new Object[]{"0", employee.getFirstName(),
                employee.getMiddleName(), employee.getLastName(),employee.getAddress(),
                employee.getPassword(),employee.getBranchID(),employee.getSSN()} );
        int tmpEmployeeID = 0;
        ArrayList<Object> IDList =  connector.getDatabaseRowList("Employee_ID","employee");
        for (Object o:IDList) {
            if (tmpEmployeeID < Integer.parseInt(o.toString())){
            tmpEmployeeID = Integer.parseInt(o.toString());
            }
        }

        for (String s:phoneNumbers) {
          arePhonesAdded =   connector.insertDataToDatabase("ephone", new Object[]{s,tmpEmployeeID});
        }
                return isEmployeeAdded&&arePhonesAdded;
    }
    public boolean rearrangeEmployee(String address, int employeeID){

        return connector.setDataToDatabase("employee", "Address",address,"Employee_ID = " + employeeID );
    }

    public boolean addPhoneNumber(String phoneNumber, int employeeID){

        return connector.setDataToDatabase("ephone", "Phone",phoneNumber,"Employee_ID = " + employeeID );
    }
    public boolean dismissEmployee(int employeeID){
        return connector.removeDataFromDatabase("employee", "Employee_ID = " + employeeID);
    }

    public boolean hireManager(Employee employee, int monthlySalary, String[] phoneNumbers){
        hireEmployee(employee, phoneNumbers);

        return  connector.insertDataToDatabase("manager", new Object[]{monthlySalary,employee.getEmployeeID()});
    }
    public boolean rearrangeManager(int monthlySalary, int employeeID){
        return connector.setDataToDatabase("manager", "Monthly_Salary",monthlySalary + "" , " Employee_ID = " + employeeID );
    }
    public List<Object[]> getEmployeeInfo(int employeeID){
        return connector.getDatabaseList("employee", "Employee_ID = " + employeeID);
    }
    public List<Object[]> getEmployeeInfo(String firstName, String surname ){
        return connector.getDatabaseList("employee", "First_Name = " + firstName + " AND " + "Last_Name = " + surname);
    }


    public boolean hireReceptionist(Employee employee, int weeklySalary, String[] phoneNumbers){
        hireEmployee(employee, phoneNumbers);
        int newEmployeeID = getNewEmployeeID();
        return connector.insertDataToDatabase("receptionist", new Object[]{weeklySalary,newEmployeeID});
    }
    public boolean rearrangeReceptionist(int weeklySalary, int employeeID){
        return  connector.setDataToDatabase("receptionist","Weekly_Salary" ,weeklySalary + "" , " Employee_ID = " + employeeID );
    }


    public boolean hireTrainer(Employee employee, int hourlySalary, String[] phoneNumbers){
        hireEmployee(employee,phoneNumbers);
        int newEmployeeID = getNewEmployeeID();
        return connector.insertDataToDatabase("trainer", new Object[]{hourlySalary,newEmployeeID});
    }
    public boolean rearrangeTrainer(int hourlySalary, int employeeID){
        return connector.setDataToDatabase("trainer","Hourly_Salary",hourlySalary + "",  " Employee_ID = " + employeeID );
    }


    public boolean hireCleaner(Employee employee, int dailySalary, String[] phoneNumbers,int facilityID){
        hireEmployee(employee, phoneNumbers);
       int newEmployeeID = getNewEmployeeID();
        return connector.insertDataToDatabase("Cleaner", new Object[]{dailySalary,newEmployeeID})
                &&connector.insertDataToDatabase("cleans", new Object[]{newEmployeeID, facilityID});
    }
    public boolean rearrangeCleaner(int dailySalary, int employeeID){
        return connector.setDataToDatabase("cleaner","Daily_Salary" ,dailySalary + ""," Employee_ID = " + employeeID );
    }
    public boolean isManager(int employeeID){
       ArrayList<Object> objects =  connector.getDatabaseRowList("Monthly_Salary","manager","Employee_ID =" + employeeID);
        return !objects.isEmpty();
    }
    public int getNewEmployeeID(){
        int tmpEmployeeID = 0;
        ArrayList<Object> IDList =  connector.getDatabaseRowList("Employee_ID","employee");
        for (Object o:IDList) {
            if (tmpEmployeeID < Integer.parseInt(o.toString())){
                tmpEmployeeID = Integer.parseInt(o.toString());
            }
        }
        return tmpEmployeeID;
    }


    //getter and setter method for Manager
    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    //Test cases for manager methods
    /*public static void main(String[] args) {
        Manager manager = new Manager(200001);
        System.out.println( "<<Hire manager method>>" );
        manager.hireManager(new Employee("Kyle","R","Semn","address",
                "kyle123",1919889898,200006,100001,new String[]{"240-864-1276", "240-999-6787" }),5000,new String[]{"240-864-1276", "240-999-6787" });
        System.out.println("Employee : " + manager.getEmployee(200006));
        System.out.println("Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Employee Salary : " + manager.connector.getDatabaseRowList("Monthly_Salary","manager", "Employee_ID = 200006"));
        System.out.println();
        System.out.println("<<Rearrange manager Method>>");
        manager.rearrangeManager(6000,200006);
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Monthly_Salary","manager", "Employee_ID = 200006"));
        manager.rearrangeEmployee("adalar caddesi",200006);
        System.out.println("Updated Employee : " + manager.getEmployee(200006));
        System.out.println();
        System.out.println("<<Dismiss manager method>>");
        manager.dismissEmployee(200006);
        System.out.println(("Updated Employee : " + manager.connector.getDatabaseItem( "employee","Employee_ID = 200006")));
        System.out.println("Updated Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Monthly_Salary","manager", "Employee_ID = 200006"));
        System.out.println();
        System.out.println();
        System.out.println("<<Hire receptionist Method>>");
        manager.hireReceptionist(new Employee("Kyle","R","Semn","address",
                "kyle123",1919889898,200006,100001,new String[]{"240-864-1276", "240-999-6787" }),5000,new String[]{"240-864-1276", "240-999-6787" });
        System.out.println("Employee : " + manager.getEmployee(200006));
        System.out.println("Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Employee Salary : " + manager.connector.getDatabaseRowList("Weekly_Salary","receptionist", "Employee_ID = 200006"));
        System.out.println();
        System.out.println("<<Rearrange receptionist Method>>");
        manager.rearrangeReceptionist(6000,200006);
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Weekly_Salary","receptionist", "Employee_ID = 200006"));
        manager.rearrangeEmployee("adalar caddesi",200006);
        System.out.println("Updated Employee : " + manager.getEmployee(200006));
        System.out.println();
        System.out.println("<<Dismiss receptionist method>>");
        manager.dismissEmployee(200006);
        System.out.println(("Updated Employee : " + manager.connector.getDatabaseItem( "employee","Employee_ID = 200006")));
        System.out.println("Updated Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Weekly_Salary","receptionist", "Employee_ID = 200006"));
        System.out.println();
        System.out.println();
        System.out.println("<<Hire trainer Method>>");
        manager.hireTrainer(new Employee("Kyle","R","Semn","address",
                "kyle123",1919889898,200006,100001,new String[]{"240-864-1276", "240-999-6787" }),5000,new String[]{"240-864-1276", "240-999-6787" });
        System.out.println("Employee : " + manager.getEmployee(200006));
        System.out.println("Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Employee Salary : " + manager.connector.getDatabaseRowList("Hourly_Salary","trainer", "Employee_ID = 200006"));
        System.out.println();
        System.out.println("<<Rearrange trainer Method>>");
        manager.rearrangeTrainer(6000,200006);
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Hourly_Salary","trainer", "Employee_ID = 200006"));
        manager.rearrangeEmployee("adalar caddesi",200006);
        System.out.println("Updated Employee : " + manager.getEmployee(200006));
        System.out.println();
        System.out.println("<<Dismiss trainer method>>");
        manager.dismissEmployee(200006);
        System.out.println(("Updated Employee : " + manager.connector.getDatabaseItem( "employee","Employee_ID = 200006")));
        System.out.println("Updated Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Hourly_Salary","trainer", "Employee_ID = 200006"));
        System.out.println();
        System.out.println();
        System.out.println("<<Hire cleaner Method>>");
        manager.hireCleaner(new Employee("Kyle","R","Semn","address",
                "kyle123",1919889898,200006,100001,new String[]{"240-864-1276", "240-999-6787" }),5000,new String[]{"240-864-1276", "240-999-6787" },110001);
        System.out.println("Employee : " + manager.getEmployee(200006));
        System.out.println("Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Employee Salary : " + manager.connector.getDatabaseRowList("Daily_Salary","cleaner", "Employee_ID = 200006"));
        System.out.println();
        System.out.println("<<Rearrange cleaner Method>>");
        manager.rearrangeCleaner(6000,200006);
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Daily_Salary","cleaner", "Employee_ID = 200006"));
        manager.rearrangeEmployee("adalar caddesi",200006);
        System.out.println("Updated Employee : " + manager.connector.getDatabaseItem( "employee","Employee_ID = 200006"));
        System.out.println();
        System.out.println("<<Dismiss cleaner method>>");
        manager.dismissEmployee(200006);
        System.out.println(("Updated Employee : " + manager.connector.getDatabaseItem( "employee","Employee_ID = 200006")));
        System.out.println("Updated Employee Phones : " + manager.connector.getDatabaseRowList("Phone","ephone", "Employee_ID = 200006"));
        System.out.println("Updated Employee Salary : " + manager.connector.getDatabaseRowList("Daily_Salary","cleaner", "Employee_ID = 200006"));
        System.out.println();
        System.out.println();
        List<Object[]> employeeTable = manager.getEmployeeTable();
        for ( Object[] objects: employeeTable) {
            System.out.println(Arrays.toString(objects));
        }
        List<Object[]> randomTable = manager.getTable("equipment");
        for ( Object[] objects: randomTable) {
            System.out.println(Arrays.toString(objects));
        }
        System.out.println(manager.getDataItem("equipment", "EID = " + 600011));
    }*/
}
