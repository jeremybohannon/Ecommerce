//ITIS 4166 Assignment 3

import java.sql.*;
import java.util.*;
import javax.servlet.*;

public class OrderItemDB {

    public boolean addOrderItem(OrderItem orderItem, int orderNumber) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO miata.OrderItem VALUES ( ?, ?, ?)");
            
            ps.setInt(1, orderNumber);
            ps.setString(2, orderItem.getProduct().getProductCode());
            ps.setString(3, orderItem.getQuantity()+"");
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key: " + orderItem.getProduct().getProductCode() + "order num " + orderNumber);
            } else {
                System.out.println("ERROR: Could not add row to Order table: " + orderItem.getProduct().getProductCode() + " " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table: " + orderItem.getProduct().getProductCode());
            System.out.println(e);
            return false;
        }
        System.out.println("Added order to ORDER table: " + orderItem.getProduct().getProductCode());

        return true;
    }

    public static OrderItem getOrderItem(int orderID) {

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;
        
        int orderNumber = 0;
        String productCode = "";
        String quantity = "";
        String query = "";
        try {
            // Find the speciic row in the table
            query = "SELECT orderNumber, productCode, quantity FROM miata.OrderItem WHERE orderNumber ='" + orderID + "' ORDER BY orderNumber";

            resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + orderID);
                return null;
            } else {
                orderNumber = resultSet.getInt("orderNumber");
                productCode = resultSet.getString("productCode");
                quantity = resultSet.getString("quantity");

                System.out.println("Found user in user table: " + orderID);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement: " + query);
            System.out.println("SQL error: " + se);
            return null;
        }
        
        return new OrderItem(ProductDB.getProduct(productCode), Integer.parseInt(quantity));
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
//                Order temp = getOrder(orderNumber);
//                orders.add(temp);
//                System.out.println("Found user in USER table: " + temp.getOrderNumber());
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "UserDB.getAllUsers()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }
        return orders;
    }

}
