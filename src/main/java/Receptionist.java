import java.sql.Time;
import java.util.ArrayList;

public class Receptionist extends Employee {
   private int weeklySalary;

   DatabaseConnector connector;
   //constructor for receptionist
    public Receptionist(String firstName, String middleName, String lastName, String address, String password, int SSN, int employeeID,int branchID,  String[] phoneNumber, int weeklySalary) {
        super(firstName, middleName, lastName, address, password, SSN, employeeID,branchID, phoneNumber);
        this.weeklySalary = weeklySalary;
    }

    //Creates temporary trainer object to access login system
    public Receptionist(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }


    //Methods for modifying equipments
    public static boolean addEquipment(){
        return true;
    }
    protected boolean setEquipment(){
        return true;
    }

    protected boolean removeEquipment(){
        return true;
    }
    public static void getEquipment(){
    }

    //Methods for modifying customers
    protected boolean addCustomer(){
        return true;
    }

    protected boolean setCustomer(){
        return true;
    }

    protected boolean removeCustomer(){
        return true;
    }

    public static void getCustomers(){
    }
    public static void getCustomerByID(){
    }

    //Methods for modifying customer reports

    protected boolean addCustomerReport(){
        return true;
    }

    protected boolean setCustomerReport(){
        return true;
    }

    protected boolean removeCustomerReport(){
        return true;
    }

    public static void getCustomerReports(){
    }
    public static void getCustomerReportByID(){
    }
    //Methods for modifying facilities used by customers

    protected boolean addCustomerToFacility(){
        return true;
    }

    protected boolean setCustomerToFacility(){
        return true;
    }

    protected boolean removeCustomerToFacility(){
        return true;
    }
    public static void getFacilitiesUsedByCustomer(){

    }

    //Methods for trainer and facility reviews
    protected boolean addTrainerReview(){
        return true;
    }
    protected boolean setTrainerReview(){
        return true;
    }
    protected boolean removeTrainerReview(){
        return true;
    }
    public static void getTrainerReview(){
    }

    protected boolean addFacilityReview(){
        return true;
    }
    protected boolean setFacilityReview(){
        return true;
    }
    protected boolean removeFacilityReview(){
        return true;
    }
    public static void getFacilityReviews() {
    }
    public static void getFacilityReviewByID(){

    }



    //Getter and setter method for receptionist
    public int getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(int weeklySalary) {
        this.weeklySalary = weeklySalary;
    }




}
