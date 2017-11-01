/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Arrays;
import java.util.List;

public class ProductDB {
    static Product[] products = new Product[]{new Product("1", "Strut Tower Brace", "externals", "Finally, a well-designed shock tower brace for the Miata. Now fits all NA and NB models!", 189.99, "./resources/strut.jpg"), new Product("2", "Windshield Cowl Panel", "externals", "Windshield Cowl Panel fits '90-'05. These often come with a small crack in the middle section, as shown in pictures, but this defect is purely cosmetic and does not impede function. It actually looks like a factory seem. ", 49.99, "./resources/cowl.jpg"), new Product("3", "Brake Lights", "externals", "3rd brake light located on trunk lid.", 15.99, "./resources/brake.jpg"), new Product("4", "Front Bumper Assembly", "externals", "Slightly worn original 1992 Mazda Miata body panel. Still looks new but with a few small dents and scratches", 13.0, "./resources/bumper.jpg"), new Product("5", "Soft Top", "internals", "NA Black Convertible Boot Cover fits '90-'97 Miata. In good condition. Sample Picture, actual pictures will be sent on request", 90.99, "./resources/top.jpg"), new Product("6", "Dash Trim Panel", "internals", "Set of A Pillar Trim pieces. Includes both left and right side.", 15.0, "./resources/dash.jpg"), new Product("7", "AC Blower", "internals", "Set of A Pillar Trim pieces. Includes both left and right side.", 23.0, "./resources/ac.jpg")};

    public static List<Product> getProducts() {
        return Arrays.asList(products);
    }

    public static Product getProduct(String productCode) {
        for (Product product : products) {
            if (!product.productCode.equals(productCode)) continue;
            return product;
        }
        return null;
    }
}