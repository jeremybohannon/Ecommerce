/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/order"})
public class OrderController
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> products = ProductDB.getAllProducts();
        OrderDB orderDB = new OrderDB();
        OrderItemDB orderItemDB = new OrderItemDB();
        
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("theShoppingCart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("theShoppingCart", (Object)cart);
        }
        ArrayList<OrderItem> items = cart.getItems();
        double subTotal = 0.0;
        String actionValue = request.getParameter("action");
        if (actionValue != null) {
            switch (actionValue) {
                case "updateCart": {
                    String[] productList = request.getParameterValues("productList[]");
                    if (productList == null) {
                        productList = new String[]{};
                    }
                    for (String currentProduct : productList) {
                        if (currentProduct == null || !currentProduct.matches("^[1-9]\\d*$")) continue;
                        OrderItem currentItem = cart.getOrderItemByCode(currentProduct);
                        if (currentItem == null) {
                            Product tmp = ProductDB.getProduct((String)currentProduct);
                            cart.addItem(ProductDB.getProduct((String)currentProduct), 0);
                            currentItem = cart.getOrderItemByCode(currentProduct);
                        }
                        int quantity = 1;
                        try {
                            String tempQuantity = request.getParameter(currentProduct);
                            if (tempQuantity == null || tempQuantity.isEmpty()) {
                                quantity = currentItem.getQuantity() + 1;
                            } else if (Integer.parseInt(tempQuantity) <= 0) {
                                cart.removeItem(currentItem.getProduct());
                            } else if (quantity >= 0) {
                                quantity = Integer.parseInt(tempQuantity);
                            }
                            currentItem.setQuantity(quantity);
                        }
                        catch (NumberFormatException e) {
                            System.out.println(e);
                        }
                    }
                    session.setAttribute("theShoppingCart", (Object)cart);
                    subTotal = items.stream().map(item -> item.product.price * (double)item.quantity).reduce(subTotal, (accumulator, _item) -> accumulator + _item);
                    session.setAttribute("subTotal", (Object)subTotal);
                    request.getRequestDispatcher("/cart.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    break;
                }
                case "checkout": {
                    User user = (User)UserDB.getUser("1");
                    session.setAttribute("theUser", (Object)user);
                    subTotal = items.stream().map(item -> item.product.price * (double)item.quantity).reduce(subTotal, (accumulator, _item) -> accumulator + _item);
                    Order order = new Order();
                    
                    Random rand = new Random();
                    
                    order.setOrderNumber(rand.nextInt(299999) + 200000);
                    order.setUser(user);
                    order.setItems(items);
                    order.setTaxRate(0.075);
                    order.setTotalCost(subTotal + subTotal * order.getTaxRate());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate localDate = LocalDate.now();
                    order.setDate(localDate.toString());
                    
                    //Setting order in DB
                    orderDB.addOrder(order);
                    
                    ArrayList<OrderItem> currentItems = order.getItems();
                    
                    for(OrderItem item: currentItems){
                        orderItemDB.addOrderItem(item, order.getOrderNumber());
                    }
                    
                    session.setAttribute("currentOrder", (Object)order);
                    request.getRequestDispatcher("/order.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    break;
                }
                case "confirmOrder": {
                    String card = request.getParameter("cards");
                    int cardNum = Integer.parseInt(request.getParameter("cardNum"));
                    int month = Integer.parseInt(request.getParameter("month"));
                    int year = Integer.parseInt(request.getParameter("year"));
                    int cvv = Integer.parseInt(request.getParameter("cvv"));
                    
                    //Save order details to Order and OrderItem tables
                    //Add a new order in the Order table
                    
                    //Add a new OrderItem for each item in the order with the correspondin number.
                    
                    
                    //Dispatch to the invoice page with “Paid In Full” message and no “Back To Cart”
                    //or “Purchase” links.
                    
                    request.getRequestDispatcher("/order.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    break;
                }
                case "viewOrders": {
                    User user = (User) session.getAttribute("theUser");
                    System.out.println("view order");
                    
                    if(user != null){
                        //Retrieve list of orders from DB
                        ArrayList<Order> orders = orderDB.getAllOrders();
                        
                        //Add list to session as 'theOrders'
                        session.setAttribute("theOrders", orders);
                        
                        //Dispatch to orderlist
                        request.getRequestDispatcher("/orderlist.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    } else {
                        request.getRequestDispatcher("/catalog.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    }
                    break;
                }
                default: {
                    //TODO Change to admin page
                    request.getRequestDispatcher("/cart.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    break;
                }
            }
        } else {
            request.getRequestDispatcher("/cart.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
