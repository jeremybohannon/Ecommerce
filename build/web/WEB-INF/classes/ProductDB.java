/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDB {

    public void createProductTable() {

        Statement statement = DbConnection.getNewStatement();

        try {

            statement.execute("CREATE TABLE product("
                    + "productCode INT,productName VARCHAR(50),"
                    + "categoryCode INT,catalogCategory VARCHAR(50),"
                    + "price FLOAT,description VARCHAR(100),imageUrl VARCHAR(50),"
                    + "PRIMARY KEY (productCode))");
            System.out.println("Created a new table: " + "PRODUCT");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState())) {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                System.out.println("ERROR: Could not create PRODUCT table: " + se);
            }
        }
    }

    public Product addProduct(String productCode, String productName, String categoryCode, String catalogCategory,
            float price, String description, String imageUrl) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, productCode);
            ps.setString(2, productName);
            ps.setString(3, categoryCode);
            ps.setString(4, catalogCategory);
            ps.setFloat(5, price);
            ps.setString(6, description);
            ps.setString(7, imageUrl);

            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into PRODUCT; dup primary key: " + productCode);
            } else {
                System.out.println("ERROR: Could not add row to PRODUCT table: " + productCode + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to PRODUCT table: " + productCode);
            return null;
        }
        System.out.println("Added product to PRODUCT table: " + productCode);
        return new Product(productCode, productName, categoryCode, description, price, imageUrl);
    }

    public Product addProduct(Product product) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getProductCode());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getCategory());
            ps.setDouble(5, product.getPrice());
            ps.setString(6, product.getDescription());
            ps.setString(7, product.getImageURL());

            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into PRODUCT; dup primary key: " + product.getProductCode());
            } else {
                System.out.println("ERROR: Could not add row to PRODUCT table: " + product.getProductCode() + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to PRODUCT table: " + product.getProductCode());
            return null;
        }
        System.out.println("Added product to PRODUCT table: " + product.getProductCode());

        // return the  product object
        return product;
    }

    public static Product getProduct(String pcode) {

        Product product = new Product();
        product.setProductCode(pcode);

        String query = "SELECT Name, CatelogCategory, Price, Description, ImageURL"
                + " from Product where Product.productCode =  " + pcode;
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                product.setProductName(resultSet.getString("Name"));
                product.setCategory(resultSet.getString("CatelogCategory"));
                product.setPrice(resultSet.getInt("Price"));
                product.setDescription(resultSet.getString("Description"));
                product.setImageURL(resultSet.getString("ImageURL"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<Product>();

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        String productCode = "";
        String productName = "";
        String catalogCategory = "";
        float price = 0.0f;
        String description = "";
        String imageUrl = "";

        try {
            resultSet = statement.executeQuery(
                    "SELECT ProductCode, Name, CatelogCategory, Description, Price, ImageURL FROM product ORDER BY productCode");
            while (resultSet.next()) {
                productCode = ""+resultSet.getInt("ProductCode");
                productName = resultSet.getString("Name");
                catalogCategory = resultSet.getString("CatelogCategory");
                price = resultSet.getFloat("Price");
                description = resultSet.getString("Description");
                imageUrl = resultSet.getString("ImageURL");

                Product product = new Product(productCode, productName, catalogCategory, description, price, imageUrl);
                products.add(product);
                System.out.println("Found product in PRODUCT table: " + productCode);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "ProductDB.getAllProducts()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return products;
    }

}