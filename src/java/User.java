

import java.io.Serializable;

public class User
implements Serializable {
    String firstName;
    String lastName;
    String email;
    String address1;
    String address2;
    String city;
    String state;
    String postalCode;
    String country;
    String userID;
    String password;
    
    public User() {
        this.userID = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.postalCode = "";
        this.country = "";
    }

    public User(String userID, String firstName, String lastName, String emailAddress, String address1, String address2, String city, String state, String postalCode, String country) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailAddress;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}