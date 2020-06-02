public class Manager extends Employee{
    private int monthlySalary;
    //Constructor for manager
    public Manager(String firstName, String middleName, String lastName, String address, String password, int SSN, int employeeID, String[] phoneNumber, int monthlySalary) {
        super(firstName, middleName, lastName, address, password, SSN, employeeID, phoneNumber);
        this.monthlySalary = monthlySalary;
    }


    //Creates temporary manager object to access login system
    public Manager(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }

    //Methods for modifying employees
    protected void getEmployee(){ }
    protected boolean hireManager(){
        return true;
    }
    protected boolean rearrangeManager(){
        return true;
    }
    protected boolean dismissManager(){
        return true;
    }


    protected boolean hireReceptionist(){
        return true;
    }
    protected boolean rearrangeReceptionist(){
        return true;
    }
    protected boolean dismissReceptionist(){
        return true;
    }

    protected boolean hireTrainer(){
        return true;
    }
    protected boolean rearrangeTrainer(){
        return true;
    }
    protected boolean dismissTrainer() {
        return true;
    }

    protected boolean hireCleaner(){
        return true;
    }
    protected boolean rearrangeCleaner(){
        return true;
    }
    protected boolean dismissCleaner(){
        return true;
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
