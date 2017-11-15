//ITIS 4166 Assignment 3

import java.sql.*;
import java.util.*;
import javax.servlet.*;

public class OrderDB {


    public boolean addOrder(String orderNumber, String date, String userID,
            String taxRate, String totalCost, String paid) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
            insertRow = connection.prepareStatement("INSERT INTO Order VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            insertRow.setInt(1, Integer.parseInt(orderNumber));
            insertRow.setString(2, date);
            insertRow.setString(3, userID);
            insertRow.setString(4, taxRate);
            insertRow.setString(5, totalCost);
            insertRow.setString(6, paid);
            insertRow.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into Order; dup primary key");
            } else {
                System.out.println("ERROR: Could not add row to Order table: "  + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to Order table");
            return false;
        }
        System.out.println("Added order to Order table");

        // user added successfully 
        return true;
    }

    public boolean addOrder(Order order) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO miata.Order VALUES ( ?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, order.getOrderNumber());
            ps.setString(2, order.getDate());
            ps.setString(3, order.getUser().getUserID());
            ps.setString(4, order.getTaxRate()+"");
            ps.setString(5, order.getTotalCost()+"");
            ps.setString(6, order.isPaid()+"");
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key: " + order.getUser().getUserID() + "order num " + order.getOrderNumber());
            } else {
                System.out.println("ERROR: Could not add row to Order table: " + order.getUser().getUserID() + " " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table: " + order.getUser().getUserID());
            System.out.println(e);
            return false;
        }
        System.out.println("Added order to ORDER table: " + order.getUser().getUserID());

        return true;
    }

    public static Order getOrder(int orderID) {

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;
        
        int orderNumber = 0;
        String date = "";
        String userID = "";
        String taxRate = "";
        String totalCost = "";
        String isPaid = "";
        User user;

        String query = "";
        try {
            // Find the speciic row in the table
            query = "SELECT orderNumber, date, userID, taxRate, totalCost, paid FROM miata.Order WHERE orderNumber ='" + orderID + "' ORDER BY orderNumber";

            resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + orderID);
                return null;
            } else {
                orderNumber = resultSet.getInt("orderNumber");
                date = resultSet.getString("date");
                userID = resultSet.getString("userID");
                taxRate = resultSet.getString("taxRate");
                totalCost = resultSet.getString("totalCost");
                isPaid = resultSet.getString("paid");
                
                user = UserDB.getUser(userID);
                

                System.out.println("Found user in user table: " + userID);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement: " + query);
            System.out.println("SQL error: " + se);
            return null;
        }
        
        return new Order(orderNumber, date, taxRate, totalCost, isPaid, user);
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;
        int orderNumber = 0;
        
        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT orderNumber, date, userID, taxRate, totalCost, paid FROM miata.Order ORDER BY orderNumber");
            while (resultSet.next()) {
                orderNumber = resultSet.getInt("orderNumber");
                System.out.println("OrderNumber: " + orderNumber);
                Order temp = getOrder(orderNumber);
                orders.add(temp);
                System.out.println("Found order in Order table: " + temp.getOrderNumber());
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "UserDB.getAllUsers()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }
        return orders;
    }

}
