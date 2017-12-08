/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    ArrayList<OrderItem> items;

    public Cart() {
        this.items = new ArrayList();
    }

    public Cart(ArrayList<OrderItem> orderList) {
        this.items = orderList;
    }

    public void setItems(ArrayList<OrderItem> orderList) {
        this.items = orderList;
    }

    public void addItem(Product product, int quantity) {
        Boolean wasFound = false;
        for (OrderItem item : this.items) {
            if (!item.getProduct().getProductCode().equals(product.productCode)) continue;
            item.setQuantity(item.getQuantity() + quantity);
            wasFound = true;
            break;
        }
        if (!wasFound.booleanValue()) {
            this.items.add(new OrderItem(product, quantity));
        }
    }

    public void addItem(OrderItem orderItem) {
        this.addItem(orderItem.product, orderItem.quantity);
    }

    public void removeItem(Product product) {
        for (OrderItem item : this.items) {
            if (!item.getProduct().getProductCode().equals(product.productCode)) continue;
            this.items.remove(item);
            break;
        }
    }

    public ArrayList<OrderItem> getItems() {
        return this.items;
    }

    public void emptyCart() {
        this.items.clear();
    }

    public OrderItem getOrderItemByCode(String productCode) {
        for (OrderItem item : this.items) {
            if (!item.getProduct().getProductCode().equals(productCode)) continue;
            return item;
        }
        return null;
    }
}
