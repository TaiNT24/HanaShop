package taint.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taint.model.category.CategoryDAO;
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.foodAndDrink.FoodAndDrinkDTO;

/**
 *
 * @author nguye
 */
@WebServlet(urlPatterns = {"/StartupServlet"})
public class StartupServlet extends HttpServlet {

    private final String HOME_PAGE = "Home.jsp";

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
        response.setContentType("text/html;charset=UTF-8");

        String url = HOME_PAGE;
        try {
            FoodAndDrinkDAO dao = new FoodAndDrinkDAO();
            CategoryDAO cateDAO = new CategoryDAO();
//trang 1
            ArrayList<FoodAndDrinkDTO> listArticle = dao.loadActiveProduct(1);

            request.setAttribute("LIST_PRODUCT", listArticle);

            ArrayList<String> listCate = cateDAO.loadCategory();
            //set page
            ServletContext sc = request.getServletContext();
            sc.setAttribute("LIST_CATEGORY", listCate);
            
//            ArrayList<Integer> pageList = dao.getSizeOfActiveArticle();

//            sc.setAttribute("PAGES_COUNT", pageList);

        } catch (SQLException ex) {
            log("SQLException_StartupServlet", ex.getCause());
        } catch (NamingException ex) {
            log("NamingException_StartupServlet", ex.getCause());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
