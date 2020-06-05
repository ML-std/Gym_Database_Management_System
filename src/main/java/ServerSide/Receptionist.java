package ServerSide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Receptionist extends Employee {
   private int weeklySalary;

   DatabaseConnector connector;
   //constructor for receptionist
    public Receptionist( int employeeID, String firstName, String middleName, String lastName, String address, String password, int branchID,int SSN, String[] phoneNumber, int weeklySalary) {
        super(employeeID, firstName, middleName, lastName, address, password, branchID, SSN, phoneNumber);
        this.weeklySalary = weeklySalary;
        connector = new DatabaseConnector();
    }

    //Creates temporary trainer object to access login system
    public Receptionist(int employeeID){
        super(employeeID);
        connector = new DatabaseConnector();
    }


    //Methods for modifying equipments

    public boolean addEquipment(String name,String manufacturer, Condition condition, String type, int count){
        connector.insertDataToDatabase("equipment", new Object[]{"0",name, manufacturer, condition, type, count, this.getBranchID()});
        int tmpEquipmentID = 0;
        List<Object> equipmentIDs = connector.getDatabaseRowList("EID","equipment");
        for (Object o: equipmentIDs) {
            if (tmpEquipmentID < (int) o){
                tmpEquipmentID = (int )o;
            }
        }
    return connector.insertDataToDatabase("equipment_maintains", new Object[]{tmpEquipmentID,this.getEmployeeID()});
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
    public List<Object> getEquipment(String equipmentName){
        return connector.getDatabaseItem("equipment", "Name = " + equipmentName);
    }
    public List<Object[]> getEquipmentTable(){
        return connector.getDatabaseTable("equipment");
    }

    //Methods for modifying customers
    public boolean addCustomer(Customer customer,String[] phoneNumbers) {
        Object[] o = new Object[]{"0", customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getSex(),
                customer.getAddress(), customer.getSSN(), customer.getBirthDate()};
        boolean isAdded = connector.insertDataToDatabase("customer", o);
        if (!isAdded) return false;
        else {
            List<Object> objectList = connector.getDatabaseRowList("CID", "customer");
            int newCustomerID = 0;
            for (Object object : objectList) {
                if ((int) object > newCustomerID) {
                    newCustomerID = (int) object;
                }
            }

            for (String phoneNumber : phoneNumbers) {
                isAdded = connector.insertDataToDatabase("cphone", new Object[]{phoneNumber, newCustomerID});
            }
            return isAdded;
        }
    }
    public boolean setCustomerTrainer(int customerID, int employeeID){
        return connector.setDataToDatabase("customer","Employee_ID", employeeID + " ", "CID = " + customerID);
    }
    public boolean setCustomerPhoneNumber(int customerID, String phoneNumber){
        return connector.setDataToDatabase("cphone", "Phone",phoneNumber, "CID = " + customerID );
    }

    public boolean removeCustomer(int customerID){
        return connector.removeDataFromDatabase("customer", "CID = " + customerID);
    }

    public List<Object[]> getCustomers(){
        String tableName = "customer";
        return connector.getDatabaseTable(tableName);
    }

    public List<Object[]> getCustomerByID(int customerID){

        if (connector.getDatabaseList("customer", "CID = " + customerID).isEmpty()){
            return null;
        }
        return connector.getDatabaseList("customer", "CID = " + customerID);
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


    public boolean removeCustomerReport(int reportID){
        return connector.removeDataFromDatabase("customer_report","Report_ID = " + reportID  );

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
        return connector.insertDataToDatabase("facility_review",new Object[]{customerID,facilityID , rating});
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



}
