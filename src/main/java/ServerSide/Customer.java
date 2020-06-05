package ServerSide;

import java.sql.Date;
import java.time.LocalDate;

public class Customer {
    private String firstName, middleName, lastName, address;
    private int SSN;
    private String[] phoneNumber;
    private LocalDate birthDate;
    private Sex sex;

    //Constructor for customer

    public Customer(String firstName, String middleName, String lastName, Sex sex, String address, int SSN, String[] phoneNumber, LocalDate birthDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.address = address;
        this.SSN = SSN;

        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;

    }

    //Getter and setter methods for customer
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

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }


    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
