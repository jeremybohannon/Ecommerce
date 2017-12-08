//ITIS 4166 Assignment 3

import java.sql.*;
import java.util.*;
import javax.servlet.*;

public class UserDB {

    // This class handles the USER table.
    public void createUserTable() {

        Statement statement = DbConnection.getNewStatement();

        try {
            statement.execute("CREATE TABLE users("
                    + "userID VARCHAR(50),firstName VARCHAR(50),"
                    + "lastName VARCHAR(50), emailAddr VARCHAR(50),"
                    + "address1 VARCHAR(50), address2 VARCHAR(50),"
                    + "city VARCHAR(50),state VARCHAR(50),zipcode VARCHAR(50),"
                    + "country VARCHAR(50),"
                    + "PRIMARY KEY (userID))");

            System.out.println("Created a new table: " + "USER");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState())) {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                System.out.println("ERROR: Could not create USER table: " + se);
            }
        }
    }

    public static boolean addUser(String firstName, String lastName, String email,
                           String address1, String address2, String city, String state,
                           String zipcode, String country, String password, String security) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
            insertRow = connection.prepareStatement("INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            insertRow.setString(2, firstName);
            insertRow.setString(1, lastName);
            insertRow.setString(3, email);
            insertRow.setString(4, address1);
            insertRow.setString(5, address2);
            insertRow.setString(6, city);
            insertRow.setString(7, state);
            insertRow.setString(8, zipcode);
            insertRow.setString(9, country);
            insertRow.setString(10, password);
            insertRow.setString(11, security);
            insertRow.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key");
            } else {
                System.out.println("ERROR: Could not add row to USER table: " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table");
            return false;
        }
        System.out.println("Added user to USER table");

        // user added successfully 
        return true;
    }

    public static boolean addUser(User user) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO User VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, user.getUserID());
            ps.setString(3, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAddress1());
            ps.setString(6, user.getAddress2());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getPostalCode());
            ps.setString(10, user.getCountry());
            ps.setString(11, user.getPassword());
            ps.setString(12, user.getSecurity());
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key: " + user.getUserID());
            } else {
                System.out.println("ERROR: Could not add row to USER table: " + user.getUserID() + " " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table: " + user.getUserID());
            return false;
        }
        System.out.println("Added user to USER table: " + user.getUserID());

        // return the  User object
        return true;
    }

    public static User getUser(String userID) {

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        String firstName = "";
        String lastName = "";
        String email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";
        String password = "";
        String security = "";

        String query = "";
        try {
            // Find the speciic row in the table
            query = "SELECT UserID, FirstName, LastName, Email, Address_1, Address_2, City, State, Postal_Code, Country, Password, Security FROM User WHERE UserID =" + userID + " ORDER BY UserID";

            resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + userID);
                return null;
            } else {
                firstName = resultSet.getString("FirstName");
                lastName = resultSet.getString("LastName");
                email = resultSet.getString("Email");
                address1 = resultSet.getString("Address_1");
                address2 = resultSet.getString("Address_2");
                city = resultSet.getString("City");
                state = resultSet.getString("State");
                zipcode = resultSet.getString("Postal_Code");
                country = resultSet.getString("Country");
                password = resultSet.getString("Password");
                security = resultSet.getString("Security");

                System.out.println("Found user in user table: " + userID);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement: " + query);
            System.out.println("SQL error: " + se);
            return null;
        }

        return new User(userID, firstName, lastName, email, address1, address2, city, state, zipcode, country, password, security);
    }

    public static User getUserByEmail(String _email) {

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        String userID = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";
        String password = "";
        String security = "";

        String query = "";
        try {
            // Find the speciic row in the table
            query = "SELECT UserID, FirstName, LastName, Email, Address_1, Address_2, City, State, Postal_Code, Country, Password, Security FROM User WHERE Email ='" + _email + "' ORDER BY UserID";

            resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + _email);
                return null;
            } else {
                userID = resultSet.getString("UserID");
                firstName = resultSet.getString("FirstName");
                lastName = resultSet.getString("LastName");
                email = resultSet.getString("Email");
                address1 = resultSet.getString("Address_1");
                address2 = resultSet.getString("Address_2");
                city = resultSet.getString("City");
                state = resultSet.getString("State");
                zipcode = resultSet.getString("Postal_Code");
                country = resultSet.getString("Country");
                password = resultSet.getString("Password");
                security = resultSet.getString("Security");

                System.out.println("Found user in user table: " + _email);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement: " + query);
            System.out.println("SQL error: " + se);
            return null;
        }

        return new User(userID, firstName, lastName, email, address1, address2, city, state, zipcode, country, password, security);
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        String userID = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";
        String password = "";
        String security = "";
        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT UserID, FirstName, LastName, Email, Address_1, Address_2, City, State, Postal_Code, Country, Password, Security FROM User ORDER BY UserID");
            while (resultSet.next()) {
                userID = resultSet.getString("userID");
                firstName = resultSet.getString("FirstName");
                lastName = resultSet.getString("LastName");
                email = resultSet.getString("Email");
                address1 = resultSet.getString("Address_1");
                address2 = resultSet.getString("Address_2");
                city = resultSet.getString("City");
                state = resultSet.getString("State");
                zipcode = resultSet.getString("Postal_Code");
                country = resultSet.getString("Country");
                password = resultSet.getString("Password");
                security = resultSet.getString("Security");
                User user = new User(userID, firstName, lastName, email, address1, address2, city, state, zipcode, country, password, security);
                users.add(user);
                System.out.println("Found user in USER table: " + userID);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "UserDB.getAllUsers()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return users;
    }

    public static boolean updateUser(User user) {
        System.out.println("\n\n[WARN]\n\n UPDATING USER: " + user.getUserID());
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("UPDATE User SET LastName=?, FirstName=?, Email=?, Address_1=?,"
                    + " Address_2=?, City=?, State=?, Postal_Code=?, Country=?, Password=?, Security=? WHERE UserID=?");

            ps.setString(1, user.getLastName());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress1());
            ps.setString(5, user.getAddress2());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getState());
            ps.setString(8, user.getPostalCode());
            ps.setString(9, user.getCountry());
            ps.setString(10, user.getPassword());
            ps.setString(11, user.getSecurity());
            ps.setString(12, user.getUserID());

            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key: " + user.getUserID());
            } else {
                System.out.println("ERROR: Could not update row to USER table: " + user.getUserID() + " " + se);
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table: " + user.getUserID());
            return false;
        }
        System.out.println("Added user to USER table: " + user.getUserID());

        // return the  User object
        return true;
    }

}
