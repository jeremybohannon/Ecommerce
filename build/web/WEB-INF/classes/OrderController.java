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

@WebServlet(urlPatterns = {"/order"})
public class OrderController
        extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get products from DB
        ArrayList<Product> products = ProductDB.getAllProducts();
        OrderDB orderDB = new OrderDB();
        OrderItemDB orderItemDB = new OrderItemDB();

        //Get session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("theShoppingCart");
        
        //If no cart is found, create a new cart
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("theShoppingCart", (Object) cart);
            session.setAttribute("subTotal", null);
        }
        ArrayList<OrderItem> items = cart.getItems();
        double subTotal = 0.0;
        String actionValue = request.getParameter("action");
        if (actionValue != null) {
            switch (actionValue) {
                case "updateCart": {
                    //Get values to update cart
                    String[] productList = request.getParameterValues("productList[]");
                    if (productList == null) {
                        productList = new String[]{};
                    }
                    //For all products in list
                    for (String currentProduct : productList) {
                        //Validation
                        if (currentProduct == null || !currentProduct.matches("^[1-9]\\d*$")) continue;
                        OrderItem currentItem = cart.getOrderItemByCode(currentProduct);
                        //If item doesn't exist find item in DB 
                        if (currentItem == null) {
                            Product tmp = ProductDB.getProduct((String) currentProduct);
                            cart.addItem(ProductDB.getProduct((String) currentProduct), 0);
                            currentItem = cart.getOrderItemByCode(currentProduct);
                        }
                        //Set initial quantity to 1
                        int quantity = 1;
                        try {
                            String tempQuantity = request.getParameter(currentProduct);
                            //Default increase quantity by 1
                            //If the quantity is 0, delete item
                            //Other wise if specified set quantity to requested
                            if (tempQuantity == null || tempQuantity.isEmpty()) {
                                quantity = currentItem.getQuantity() + 1;
                            } else if (Integer.parseInt(tempQuantity) <= 0) {
                                cart.removeItem(currentItem.getProduct());
                            } else if (quantity >= 0) {
                                quantity = Integer.parseInt(tempQuantity);
                            }
                            currentItem.setQuantity(quantity);
                        } catch (NumberFormatException e) {
                            System.out.println(e);
                        }
                    }
                    //Update data and redirect to cart
                    session.setAttribute("theShoppingCart", (Object) cart);
                    subTotal = items.stream().map(item -> item.product.price * (double) item.quantity).reduce(subTotal, (accumulator, _item) -> accumulator + _item);
                    session.setAttribute("subTotal", (Object) subTotal);
                    request.getRequestDispatcher("/cart.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "checkout": {
                    //Get user
                    User user;
                    user = (User) session.getAttribute("theUser");

                    //If user isn't logged in, get guest account
                    if (user == null) {
                        user = (User) UserDB.getUser("-1");
                        session.setAttribute("theUser", (Object) user);
                    }
                    
                    //Get all data from order
                    subTotal = items.stream().map(item -> item.product.price * (double) item.quantity).reduce(subTotal, (accumulator, _item) -> accumulator + _item);
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

                    //Set current order to the order made from the cart
                    session.setAttribute("currentOrder", (Object) order);
                    request.getRequestDispatcher("/order.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "confirmOrder": {
                    //Save order details to Order and OrderItem tables
                    //Add a new order in the Order table
                    //Setting order in DB
                    Order order = (Order) session.getAttribute("currentOrder");
                    order.setPaid(true);
                    orderDB.addOrder(order);

                    ArrayList<OrderItem> currentItems = order.getItems();

                    for (OrderItem item : currentItems) {
                        orderItemDB.addOrderItem(item, order.getOrderNumber());
                    }

                    //Dispatch to the invoice page with “Paid In Full” message and no “Back To Cart”
                    //or “Purchase” links.
                    session.setAttribute("theShoppingCart", null);
                    request.getRequestDispatcher("/order.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "viewOrders": {
                    User user = (User) session.getAttribute("theUser");
                    System.out.println("view order");

                    if (user != null) {
                        //Retrieve list of orders from DB
                        ArrayList<Order> orders = orderDB.getAllOrders(user.getUserID());

                        //Add list to session as 'theOrders'
                        session.setAttribute("theOrders", orders);

                        //Dispatch to orderlist
                        request.getRequestDispatcher("/orderlist.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    } else {
                        request.getRequestDispatcher("/catalog.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    }
                    break;
                }
                default: {
                    //TODO Change to admin page
                    request.getRequestDispatcher("/admin.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
            }
        } else {
            request.getRequestDispatcher("/cart.jsp").forward((ServletRequest) request, (ServletResponse) response);
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
