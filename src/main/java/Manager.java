import java.util.List;

public class Manager extends Employee{
    private int monthlySalary;
    //Constructor for manager
    public Manager(String firstName, String middleName, String lastName, String address, String password, int SSN, int employeeID, int branchID, String[] phoneNumber, int monthlySalary) {
        super(firstName, middleName, lastName, address, password, SSN, employeeID, branchID, phoneNumber);
        this.monthlySalary = monthlySalary;
    }


    //Creates temporary manager object to access login system
    public Manager(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }

    //Methods for modifying employees
    protected List<Object[]> getEmployeeTable(){
        return connector.getDatabaseTable("employee");
    }
    protected List<Object> getEmployee(int EmployeeID){
        return connector.getDatabaseItem("employee","Employee_ID = " + EmployeeID);
    }
    protected boolean hireEmployee(Employee employee){
        return connector.insertDataToDatabase("employee",new Object[]{employee.getEmployeeID(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),employee.getAddress(),
        employee.getPassword(),employee.getBranchID(),employee.getSSN()} );
    }
    protected boolean rearrangeEmployee(String address, int employeeID){

        return connector.setDataToDatabase("employee", "Address",address,"Employee_ID = " + employeeID );
    }
    protected boolean dismissEmployee(int employeeID){
        return connector.removeDataFromDatabase("employee", "Employee_ID = " + employeeID);
    }

    protected boolean hireManager(Employee employee, int monthlySalary){
        return  hireEmployee(employee)&&connector.insertDataToDatabase("manager", new Object[]{monthlySalary});
    }
    protected boolean rearrangeManager(int monthlySalary, int employeeID){
        return connector.setDataToDatabase("manager", "Monthly_Salary",monthlySalary + "" , " Employee_ID = " + employeeID );
    }


    protected boolean hireReceptionist(Employee employee, int monthlySalary){
        return hireEmployee(employee)&&connector.insertDataToDatabase("receptionist", new Object[]{monthlySalary});
    }
    protected boolean rearrangeReceptionist(int weeklySalary, int employeeID){
        return  connector.setDataToDatabase("receptionist","Weekly_Salary" ,weeklySalary + "" , " Employee_ID = " + employeeID );
    }


    protected boolean hireTrainer(Employee employee, int hourlySalary){
        return hireEmployee(employee)&&connector.insertDataToDatabase("trainer", new Object[]{hourlySalary});
    }
    protected boolean rearrangeTrainer(int hourlySalary, int employeeID){
        return connector.setDataToDatabase("trainer","Hourly_Salary",hourlySalary + "",  " Employee_ID = " + employeeID );
    }


    protected boolean hireCleaner(Employee employee, int dailySalary){
        return hireEmployee(employee)&&connector.insertDataToDatabase("Cleaner", new Object[]{dailySalary});
    }
    protected boolean rearrangeCleaner(int dailySalary, int employeeID){
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
