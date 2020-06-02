import java.util.List;

public class Manager extends Employee{
    private int monthlySalary;
    private DatabaseConnector connector;
    //Constructor for manager
    public Manager(String firstName, String middleName, String lastName, String address, String password, int SSN, int employeeID, int branchID, String[] phoneNumbers, int monthlySalary) {
        super(firstName, middleName, lastName, address, password, SSN, employeeID, branchID, phoneNumbers);
        this.monthlySalary = monthlySalary;
        connector = new DatabaseConnector();
    }


    //Creates temporary manager object to access login system
    public Manager(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }

    //Methods for modifying employees
    public List<Object[]> getEmployeeTable(){
        return connector.getDatabaseTable("employee");
    }
    public List<Object> getEmployee(int EmployeeID){
        return connector.getDatabaseItem("employee","Employee_ID = " + EmployeeID);
    }
    public boolean hireEmployee(Employee employee,String[] phoneNumbers){
        return connector.insertDataToDatabase("employee",new Object[]{employee.getEmployeeID(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),employee.getAddress(),
        employee.getPassword(),employee.getBranchID(),employee.getSSN()} )
                &&connector.insertDataToDatabase("ephone", phoneNumbers);
    }
    public boolean rearrangeEmployee(String address, int employeeID){

        return connector.setDataToDatabase("employee", "Address",address,"Employee_ID = " + employeeID );
    }
    public boolean dismissEmployee(int employeeID){
        return connector.removeDataFromDatabase("employee", "Employee_ID = " + employeeID);
    }

    public boolean hireManager(Employee employee, int monthlySalary, String[] phoneNumbers){
        return  hireEmployee(employee, phoneNumbers)&&connector.insertDataToDatabase("manager", new Object[]{monthlySalary});
    }
    public boolean rearrangeManager(int monthlySalary, int employeeID){
        return connector.setDataToDatabase("manager", "Monthly_Salary",monthlySalary + "" , " Employee_ID = " + employeeID );
    }


    public boolean hireReceptionist(Employee employee, int monthlySalary, String[] phoneNumbers){
        return hireEmployee(employee, phoneNumbers)&&connector.insertDataToDatabase("receptionist", new Object[]{monthlySalary});
    }
    public boolean rearrangeReceptionist(int weeklySalary, int employeeID){
        return  connector.setDataToDatabase("receptionist","Weekly_Salary" ,weeklySalary + "" , " Employee_ID = " + employeeID );
    }


    public boolean hireTrainer(Employee employee, int hourlySalary, String[] phoneNumbers){
        return hireEmployee(employee,phoneNumbers)&&connector.insertDataToDatabase("trainer", new Object[]{hourlySalary});
    }
    public boolean rearrangeTrainer(int hourlySalary, int employeeID){
        return connector.setDataToDatabase("trainer","Hourly_Salary",hourlySalary + "",  " Employee_ID = " + employeeID );
    }


    public boolean hireCleaner(Employee employee, int dailySalary, String[] phoneNumbers){
        return hireEmployee(employee, phoneNumbers)&&connector.insertDataToDatabase("Cleaner", new Object[]{dailySalary});
    }
    public boolean rearrangeCleaner(int dailySalary, int employeeID){
        return connector.setDataToDatabase("cleaner","Daily_Salary" ,dailySalary + ""," Employee_ID = " + employeeID );
    }


    //getter and setter method for Manager
    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    @Override
    public boolean employeeLogin(int employeeID, String password) {
        return false;
    }
}
