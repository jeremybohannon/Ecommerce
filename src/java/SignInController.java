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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeremybohannon
 */
@WebServlet(urlPatterns={"/signin"})
public class SignInController extends HttpServlet {

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
        
        System.out.println("\n\n\n\nAction Value: " + actionValue);
        if(actionValue != null){
            UserDB userDB = new UserDB();
            HttpSession session = request.getSession();
            
            switch (actionValue) {
                case "signIn":
                    {
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        User user = UserDB.getUserByEmail(email);
                        if(password.equals(user.getPassword())){
                            session.setAttribute("theUser", (Object)user);
                            request.getRequestDispatcher("/index.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        } else {
                            request.setAttribute("error", true);
                            request.getRequestDispatcher("/signin.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        }       break;
                    }
                case "signUp":
                    {
                        String email, password, firstName, lastName, address1, address2, city, state, zip, country = "";
                        email = request.getParameter("email");
                        password = request.getParameter("password");
                        firstName = request.getParameter("firstName");
                        lastName = request.getParameter("lastName");
                        address1 = request.getParameter("address1");
                        address2 = request.getParameter("address2");
                        city = request.getParameter("city");
                        state = request.getParameter("state");
                        zip = request.getParameter("zip");
                        country = request.getParameter("country");
                        Random rand = new Random();
                        User user = new User(rand.nextInt(199999) + 100000 +"", firstName, lastName, email, address1, address2, city, state, zip, country, password);
                        userDB.addUser(user);
                        session.setAttribute("theUser", (Object)user);
                        request.getRequestDispatcher("/index.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                case "signOut":
                    session.setAttribute("theUser", null);
                    request.getRequestDispatcher("/index.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    break;
                case "update":
                    {
                        String email, password, firstName, lastName, address1, address2, city, state, zip, country = "";
                        email = request.getParameter("email");
                        password = request.getParameter("password");
                        firstName = request.getParameter("firstName");
                        lastName = request.getParameter("lastName");
                        address1 = request.getParameter("address1");
                        address2 = request.getParameter("address2");
                        city = request.getParameter("city");
                        state = request.getParameter("state");
                        zip = request.getParameter("zip");
                        country = request.getParameter("country");
                        User currentUser = (User) session.getAttribute("theUser");
                        User user = new User(currentUser.getUserID(), firstName, lastName, email, address1, address2, city, state, zip, country, password);
                        userDB.updateUser(user);
                        session.setAttribute("theUser", user);
                        //update stuff
                        request.getRequestDispatcher("/index.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                case "recover":
                    {
                        String email = request.getParameter("email");
                        User user = (User) UserDB.getUserByEmail(email);
                        String address = request.getParameter("address1");
                        if(address.equals(user.getAddress1())){
                            request.setAttribute("password", user.getPassword());
                        } else {
                            request.setAttribute("error", true);
                        }       request.getRequestDispatcher("/forgotPass.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        break;
                    }
                default:
                    break;
            }
        } else {
            request.getRequestDispatcher("/index.jsp").forward((ServletRequest)request, (ServletResponse)response);
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
