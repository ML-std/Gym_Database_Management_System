public class Trainer extends Employee{
    private int hourlySalary;
    //Constructor for trainer
    public Trainer(String firstName, String middleName, String lastName, String address, String  password,  int SSN, int employeeID, String[] phoneNumber, int hourlySalary) {
        super(firstName, middleName, lastName, address, password, SSN, employeeID, phoneNumber);
        this.hourlySalary = hourlySalary;
    }
    //Methods for batches
    private boolean createBatches(){
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
}
