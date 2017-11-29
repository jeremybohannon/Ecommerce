/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeremybohannon
 */
public class AdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionValue = request.getParameter("action");
        
        if(actionValue != null){
            switch (actionValue) {
                case "viewOrders":
                    {
                        OrderDB orderDB = new OrderDB();
                        HttpSession session = request.getSession();
                        //Retrieve list of orders from DB
                        ArrayList<Order> orders = orderDB.getAllOrders();
                        //Add list to session as 'theOrders'
                        session.setAttribute("theOrders", orders);
                        request.setAttribute("admin", true);
                        request.getRequestDispatcher("/orderlist.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                case "viewProducts":
                    break;
                case "viewUsers":
                    {
                        HttpSession session = request.getSession();
                        ArrayList<User> users = new ArrayList<>();
                        UserDB userDB = new UserDB();
                        users = userDB.getAllUsers();
                        session.setAttribute("users", users);
                        request.getRequestDispatcher("/userlist.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                case "viewProfile":
                    {
                        String email = request.getParameter("email");
                        User user = UserDB.getUserByEmail(email);
                        request.setAttribute("theUser", (Object)user);
                        request.getRequestDispatcher("/profile.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                case "orderNum":
                    {
                        String orderNum = request.getParameter("orderNum");
                        String userID = request.getParameter("userID");
                        User user = (User) UserDB.getUser(userID);
                        ArrayList<OrderItem> orders = (ArrayList<OrderItem>) OrderItemDB.getAllOrders(Integer.parseInt(orderNum));
                        Order order = (Order) OrderDB.getOrder(Integer.parseInt(orderNum));
                        order.setItems(orders);
                        request.setAttribute("theUser", user);
                        request.setAttribute("currentOrder", order);
                        request.setAttribute("admin", true);
                        request.getRequestDispatcher("/order.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                case "updateOrder":
                    {
                        String[] productList = request.getParameterValues("productList[]");
                        if (productList == null) {
                            productList = new String[]{};
                        }   double totalCost = 0.0;
                        String orderNum = request.getParameter("orderNum");
                        for (String currentProduct : productList) {
                            if (currentProduct == null || !currentProduct.matches("^[1-9]\\d*$")) continue;
                            
                            String tempQuantity = request.getParameter(currentProduct);
                            
                            OrderItemDB.updateOrderItem(Integer.parseInt(orderNum), currentProduct, tempQuantity);
                            
                            Product product = ProductDB.getProduct(currentProduct);
                            
                            totalCost += product.getPrice() * Integer.parseInt(tempQuantity);
                        }   OrderDB.updateOrderTotal(Integer.parseInt(orderNum), totalCost);
                        request.getRequestDispatcher("/admin?action=viewOrders").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                default:
                    request.getRequestDispatcher("/admin/admin.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    break;
            }
        } else {
            request.getRequestDispatcher("/admin/admin.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
