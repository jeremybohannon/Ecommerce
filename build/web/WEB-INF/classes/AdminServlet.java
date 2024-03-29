/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jeremybohannon
 */
public class AdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionValue = request.getParameter("action");

        if (actionValue != null) {
            switch (actionValue) {
                case "viewOrders": {
                    //Get session
                    HttpSession session = request.getSession();
                    //Retrieve list of orders from DB
                    ArrayList<Order> orders = OrderDB.getAllOrders();
                    //Add list to session as 'theOrders'
                    session.setAttribute("theOrders", orders);
                    //Inform view this is an admin
                    request.setAttribute("admin", true);
                    request.getRequestDispatcher("/orderlist.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "viewProducts": {
                    //Get session
                    HttpSession session = request.getSession();
                    //Get list of products
                    ArrayList<Product> products = ProductDB.getAllProducts();
                    //Set list to session
                    session.setAttribute("products", products);
                    request.getRequestDispatcher("/productlist.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "viewProduct": {
                    //Get product code
                    String productCode = request.getParameter("productCode");
                    //Get respective product
                    Product product = ProductDB.getProduct(productCode);
                    //Set product to session
                    request.setAttribute("product", (Object) product);
                    request.getRequestDispatcher("/product.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "updateProduct": {
                    //Get values
                    String productCode = request.getParameter("productCode");
                    String name = request.getParameter("productName");
                    String catagory = request.getParameter("catagory");
                    String description = request.getParameter("description");
                    double price = Double.parseDouble(request.getParameter("price"));
                    String imageURL = request.getParameter("imageURL");

                    //Make new product with values
                    Product product = new Product(productCode, name, catagory, description, price, imageURL);
                    //Update product
                    ProductDB.updateProduct(product);

                    request.getRequestDispatcher("/admin?action=viewProducts").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "deleteProduct": {
                    //Get product code
                    String productCode = request.getParameter("productCode");
                    //Delete product
                    ProductDB.deleteProduct(Integer.parseInt(productCode));

                    request.getRequestDispatcher("/admin?action=viewProducts").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "addProduct": {
                    String blank = request.getParameter("blank");
                    if (blank != null && blank.equals("true")) {
                        request.setAttribute("blank", true);
                        request.getRequestDispatcher("./product.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    } else {
                        //Set random product code for new product
                        Random rand = new Random();
                        int productCode = rand.nextInt(599999) + 500000;
                        //Get values
                        String name = request.getParameter("productName");
                        String catagory = request.getParameter("catagory");
                        String description = request.getParameter("description");
                        double price = Double.parseDouble(request.getParameter("price"));
                        String imageURL = request.getParameter("imageURL");
                        
                        //Create new product
                        Product product = new Product(productCode + "", name, catagory, description, price, imageURL);
                        //Update database
                        ProductDB.addProduct(product);

                        request.getRequestDispatcher("/admin?action=viewProducts").forward((ServletRequest) request, (ServletResponse) response);
                    }
                    break;
                }
                case "viewUsers": {
                    //Get session
                    HttpSession session = request.getSession();
                    //New userDB
                    UserDB userDB = new UserDB();
                    //Get users list
                    ArrayList<User> users = userDB.getAllUsers();
                    //Set users to session
                    session.setAttribute("users", users);
                    request.getRequestDispatcher("/userlist.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "viewProfile": {
                    //Get session
                    HttpSession session = request.getSession();
                    //Get email
                    String email = request.getParameter("email");
                    //Get user by email
                    User user = UserDB.getUserByEmail(email);
                    //Set user to session
                    session.setAttribute("theUser", user);
                    request.getRequestDispatcher("/profile.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "orderNum": {
                    //Get values
                    String orderNum = request.getParameter("orderNum");
                    String userID = request.getParameter("userID");
                    //Find user
                    User user = (User) UserDB.getUser(userID);
                    //Get list of orders
                    ArrayList<OrderItem> orders = (ArrayList<OrderItem>) OrderItemDB.getAllOrders(Integer.parseInt(orderNum));
                    Order order = (Order) OrderDB.getOrder(Integer.parseInt(orderNum));
                    //Set items and attributes
                    order.setItems(orders);
                    request.setAttribute("theUser", user);
                    request.setAttribute("currentOrder", order);
                    request.setAttribute("admin", true);
                    request.getRequestDispatcher("/order.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                case "updateOrder": {
                    //Get values
                    String[] productList = request.getParameterValues("productList[]");
                    if (productList == null) {
                        productList = new String[]{};
                    }
                    double totalCost = 0.0;
                    String orderNum = request.getParameter("orderNum");
                    //For products
                    for (String currentProduct : productList) {
                        if (currentProduct == null || !currentProduct.matches("^[1-9]\\d*$")) continue;
                        
                        String tempQuantity = request.getParameter(currentProduct);
                        //Update database
                        OrderItemDB.updateOrderItem(Integer.parseInt(orderNum), currentProduct, tempQuantity);

                        Product product = ProductDB.getProduct(currentProduct);

                        totalCost += product.getPrice() * Integer.parseInt(tempQuantity);
                    }
                    //Update total cost
                    OrderDB.updateOrderTotal(Integer.parseInt(orderNum), totalCost);
                    request.getRequestDispatcher("/admin?action=viewOrders").forward((ServletRequest) request, (ServletResponse) response);
                    break;
                }
                default:
                    request.getRequestDispatcher("/admin/admin.jsp").forward((ServletRequest) request, (ServletResponse) response);
                    break;
            }
        } else {
            request.getRequestDispatcher("/admin/admin.jsp").forward((ServletRequest) request, (ServletResponse) response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
