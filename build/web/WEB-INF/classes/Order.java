

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    int orderNumber;
    String date;
    User user;
    ArrayList<OrderItem> items;
    double taxRate;
    double totalCost;
    boolean paid;

    public Order() {
        this.orderNumber = 0;
        this.user = new User();
        this.items = new ArrayList();
        this.taxRate = 0.0;
        this.totalCost = 0.0;
        this.paid = false;
    }
    
    public Order(int orderNumber, String date, String taxRate, String totalCost, String paid){
        this.orderNumber = orderNumber;
        this.date = date;
        this.taxRate = Double.parseDouble(taxRate);
        this.totalCost = Double.parseDouble(totalCost);
        this.paid = paid.toLowerCase().equals("true");
    }

    public Order(int orderNumber, String date, User user, ArrayList<OrderItem> items, double taxRate, double totalCost, boolean paid) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.user = user;
        this.items = items;
        this.taxRate = taxRate;
        this.totalCost = totalCost;
        this.paid = paid;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<OrderItem> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public double getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}