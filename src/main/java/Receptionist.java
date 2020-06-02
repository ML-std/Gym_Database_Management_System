
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    public boolean addEquipment(int equipmentID,String name,String manufacturer, Condition condition, String type, int count, int branchID){
        return connector.insertDataToDatabase("equipment", new Object[]{equipmentID, name, manufacturer, condition, type, count, branchID});
    }
    public boolean setEquipment(int equipmentID,Condition condition){
        return connector.setDataToDatabase("equipment", "Condition", condition + "", "EID = " + equipmentID);
    }
    public boolean setEquipment(int equipmentID,int count){
        return connector.setDataToDatabase("equipment", "Count", count + "", "EID = " + equipmentID);
    }


    public boolean removeEquipment(int equipmentID ){
        return connector.removeDataFromDatabase("equipment", "EID = " + equipmentID);
    }
    public List<Object> getEquipment(int equipmentID){
        return connector.getDatabaseItem("equipment", "EID = " + equipmentID);
    }
    public List<Object[]> getEquipmentTable(){
        return connector.getDatabaseTable("equipment");
    }

    //Methods for modifying customers
    public boolean addCustomer(Customer customer,String[] phoneNumbers){
        Object[] o = new Object[]{customer.getCustomerID(), customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getSex(),
                customer.getAddress(), customer.getSSN(), customer.getBirthDate(), null};
        boolean isAdded =  connector.insertDataToDatabase("customer", o);
        if (!isAdded) return false;
        else
        for (String phoneNumber : phoneNumbers) {
          isAdded =   connector.insertDataToDatabase("cphone", new Object[]{phoneNumber, customer.getCustomerID()});
        }
        return isAdded;
    }

    public boolean setCustomerTrainer(int customerID, int employeeID){
        return connector.setDataToDatabase("customer","Employee_ID", employeeID + " ", "Customer_ID = " + customerID);
    }
    public boolean setCustomerPhoneNumber(int customerID, String phoneNumber){
        return connector.setDataToDatabase("cphone", "Phone",phoneNumber, "Customer_ID = " + customerID );
    }

    public boolean removeCustomer(int customerID){
        return connector.removeDataFromDatabase("customer", "Customer_ID = " + customerID);
    }

    public List<Object[]> getCustomers(){
        return connector.getDatabaseTable("customer");
    }
    public List<Object> getCustomerByID(int customerID){
        return connector.getDatabaseItem("customer", "Customer = " + customerID);
    }

    //Methods for modifying customer reports

    public boolean addCustomerReport(int customerID, int fatPercentage, int weight, int height, String reportDate){
        //can be updated to check no reports in 1 day
        connector.insertDataToDatabase("customer_report",new Object[]{"0", customerID, fatPercentage, weight, height, reportDate});
        boolean isAdded;
        List<Object> objectList = connector.getDatabaseRowList("Report_ID","customer_report", "CID = " + customerID);
        int newReportID = 0;
        for (Object o : objectList){
          if ((int) o > newReportID){
              newReportID = (int) o;
          }
        }
        isAdded = connector.insertDataToDatabase("reports_maintains",new Object[]{newReportID, customerID, getEmployeeID()});


        return isAdded;
    }

    public boolean setCustomerReport(){
        return true;
    }

    public boolean removeCustomerReport(int customerID){
        return connector.removeDataFromDatabase("customer","CID = " + customerID  );

    }

    public List<Object[]> getCustomerReports(){
        return connector.getDatabaseTable("customer_report");
    }
    public List<Object[]> getCustomerReportByID(int customerID){
        return connector.getDatabaseList("customer_report", "CID = " + customerID);
    }
    //Methods for modifying facilities used by customers

    public boolean addCustomerToFacility(int facilityID, int customerID){
        return connector.insertDataToDatabase("uses",new Object[]{facilityID, customerID});
    }

    public boolean setCustomerToFacility(){
        return true;
    }

    public boolean removeCustomerFromFacility(int facilityID, int customerID){
        return connector.removeDataFromDatabase("uses", "Facility_ID = " +facilityID + "AND" + " CID = " + customerID );
    }
    public List<Object> getFacilitiesUsedByCustomer(int customerID){
       ArrayList<Object> facilityIDs = connector.getDatabaseRowList("Facility_ID", "uses", "CID = " + customerID);
      ArrayList<Object> facilityNames = new ArrayList<>();
        for (Object o:facilityIDs) {
            System.out.println(o.toString() + o.getClass());
            facilityNames.add(connector.getDatabaseRowList("Name","facilities","Facility_ID = " + o.toString()).get(0));
        }
        return facilityNames;
    }

    //Methods for trainer and facility reviews
    public boolean addTrainerReview(int customerID,int trainerID, int rating){
        return connector.insertDataToDatabase("trainer_review",new Object[]{trainerID, customerID, rating});
    }
    public boolean setTrainerReview(int customerID,int trainerID, int rating){
        return connector.setDataToDatabase("trainer_review", "Rating",rating + "","CID = " + customerID
                + " AND " + "Employee_ID = " + trainerID);
    }
    public boolean removeTrainerReview(int customerID, int trainerID){
        return connector.removeDataFromDatabase("trainer_review", "CID = " + customerID
                + " AND " + "Employee_ID = " + trainerID );
    }
    public List<Object[]> getTrainerReviews(int trainerID){
        return connector.getDatabaseList("trainer_review","Employee_ID = " + trainerID);
    }
    public List<Object[]> getTableOfTrainerReviews() {
        return connector.getDatabaseTable("trainer_review");
    }
    public double getAverageRatingOfTrainer(int trainerID){
        double sum = 0;
        List<Object> ratings = connector.getDatabaseRowList("Rating", "trainer_review", "Employee_ID = " + trainerID);
        for (Object o : ratings) {
            sum = sum +(int) o;
        }
        return sum/ratings.size();
    }

    public boolean addFacilityReview(int customerID,int facilityID, int rating){
        return connector.insertDataToDatabase("facility_review",new Object[]{facilityID, customerID, rating});
    }
    public boolean setFacilityReview(int customerID,int facilityID, int rating){
        return connector.setDataToDatabase("facility_review", "Rating",rating + "","CID = " + customerID
                + " AND " + "Facility_ID = " + facilityID);
    }
    public boolean removeFacilityReview(int customerID,int facilityID){
        return connector.removeDataFromDatabase("facility_Review", "CID = " + customerID
                + " AND " + "Facility_ID = " + facilityID );
    }
    public List<Object[]> getTableFacilityReviews() {
        return connector.getDatabaseTable("facility_review");
    }
    public  List<Object> getFacilityReviewByID(int facilityID){
        return connector.getDatabaseRowList("Rating", "facility_review", "facility_ID = " + facilityID);
    }

    public double getAverageRatingOFacility(int facilityID){
        double sum = 0;
        List<Object> ratings = connector.getDatabaseRowList("Rating", "facility_review", "Facility_ID = " + facilityID);
        for (Object o : ratings) {
            sum = sum +(int) o;
        }
        return sum/ratings.size();
    }



    //Getter and setter method for receptionist
    public int getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(int weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public static void main(String[] args) {
        Receptionist receptionist = new Receptionist(210001);
       // System.out.println(receptionist.addCustomerReport(300001,20,90, 200,"2020-05-09"));
        //System.out.println(receptionist.removeCustomerReport(300001));
       List<Object> o=  receptionist.getFacilitiesUsedByCustomer(300017);
       for (Object o1 : o ){
           System.out.println(o1.toString());
       }
        System.out.println();
    }




}
