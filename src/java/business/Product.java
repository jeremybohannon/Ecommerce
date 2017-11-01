/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

public class Product
implements Serializable {
    String productCode;
    String productName;
    String category;
    String description;
    double price;
    String imageURL;

    public Product() {
        this.productCode = "";
        this.productName = "";
        this.category = "";
        this.description = "";
        this.price = 0.0;
        this.imageURL = "";
    }

    public Product(String productCode, String productName, String category, String description, double price, String imageURL) {
        this.productCode = productCode;
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
