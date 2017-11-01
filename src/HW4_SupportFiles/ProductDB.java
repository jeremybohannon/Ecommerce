//ITIS 4166 Assignment 3

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

    public Product addProduct(int productCode, String productName, int categoryCode, String catalogCategory,
            float price, String description, String imageUrl) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, productCode);
            ps.setString(2, productName);
            ps.setInt(3, categoryCode);
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

        return new Product(productCode, productName, categoryCode, catalogCategory,
                price, description, imageUrl);
    }

    public Product addProduct(Product product) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, product.getProductCode());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getCategoryCode());
            ps.setString(4, product.getCatalogCategory());
            ps.setFloat(5, product.getPrice());
            ps.setString(6, product.getDescription());
            ps.setString(7, product.getImageUrl());

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

    public Product getProduct(int pcode) {

        Product product = new Product();
        product.setProductCode(pcode);

        String query = "SELECT productName, categoryCode, catalogCategory, price, description, imageUrl"
                + " from PRODUCTSTABLE where PRODUCTSTABLE.productCode =  " + pcode;
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                product.setProductName(resultSet.getString("productName"));
                product.setCategoryCode(resultSet.getInt("categoryCode"));
                product.setCatalogCategory(resultSet.getString("catalogCategory"));
                product.setPrice(resultSet.getInt("price"));
                product.setDescription(resultSet.getString("description"));
                product.setImageUrl(resultSet.getString("imageUrl"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;

    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<Product>();

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        int productCode = 0;
        String productName = "";
        int categoryCode = 0;
        String catalogCategory = "";
        float price = 0.0f;
        String description = "";
        String imageUrl = "";

        try {

            resultSet = statement.executeQuery(
                    "SELECT productCode, productName, categoryCode, catalogCategory, price, description, imageUrl FROM product ORDER BY productCode");
            while (resultSet.next()) {
                productCode = resultSet.getInt("productCode");
                productName = resultSet.getString("productName");
                categoryCode = resultSet.getInt("categoryCode");
                catalogCategory = resultSet.getString("catalogCategory");
                price = resultSet.getFloat("price");
                description = resultSet.getString("description");
                imageUrl = resultSet.getString("imageUrl");

                Product product = new Product(productCode, productName, categoryCode, catalogCategory, price, description, imageUrl);
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
