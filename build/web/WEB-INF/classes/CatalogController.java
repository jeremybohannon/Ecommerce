

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/catalog"})
public class CatalogController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    //DEPRECATED
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get all products
        List<Product> products = ProductDB.getAllProducts();
        //Get product code and category if requested
        String productCode = request.getParameter("productCode");
        String category = request.getParameter("catalogCategory");
        if (productCode != null && productCode.matches("^[1-9]\\d*$")) {
            Product product = null;
            //Finds product that matches TODO update this is legacy
            for (Product productInList : products) {
                if (!productInList.getProductCode().equals(productCode)) continue;
                product = productInList;
            }
            if (product != null) {
                //Set product to found product and redirect to item
                request.setAttribute("product", (Object) product);
                request.getRequestDispatcher("/item.jsp").forward((ServletRequest) request, (ServletResponse) response);
                return;
            }
        }
        //Redirect to catalog with requested information
        request.setAttribute("products", products);
        request.setAttribute("category", (Object) category);
        request.getRequestDispatcher("/catalog.jsp").forward((ServletRequest) request, (ServletResponse) response);
    }

    //DEPRECATED
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception reader) {
            // empty catch block
        }
        String[] updates = sb.toString().split("&");
        Boolean isUpdate = false;
        //Checks if being updated
        for (String update : updates) {
            System.out.println("Update: " + update);
            if (!update.equals("action=Update+Cart")) continue;
            isUpdate = true;
        }
        if (!isUpdate.booleanValue()) {
            request.getRequestDispatcher("/order").forward((ServletRequest) request, (ServletResponse) response);
        } else {
            request.getRequestDispatcher("/cart").forward((ServletRequest) request, (ServletResponse) response);
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}