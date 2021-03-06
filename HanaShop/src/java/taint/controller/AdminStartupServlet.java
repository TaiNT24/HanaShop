/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.controller;

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
import javax.servlet.http.HttpSession;
import taint.model.category.CategoryDAO;
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.foodAndDrink.FoodAndDrinkDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "AdminStartupServlet", urlPatterns = {"/AdminStartupServlet"})
public class AdminStartupServlet extends HttpServlet {

    private final String ADMIN_HOME = "AdminHome.jsp";

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

        String pageStr = request.getParameter("page");
        int page = 1;
        if(pageStr!=null){
            page = Integer.parseInt(pageStr);
        }
        
        String url = ADMIN_HOME;
        try {

            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("searchVal");
                session.removeAttribute("SearchByFilter");
                session.removeAttribute("SearchByCategory");
                session.removeAttribute("priceVal");
            }

            FoodAndDrinkDAO dao = new FoodAndDrinkDAO();
//trang 1
            ArrayList<FoodAndDrinkDTO> listArticle = dao.loadAllProduct(page);

            request.setAttribute("LIST_PRODUCT", listArticle);

            //ServletContext obj
            ServletContext sc = request.getServletContext();
            Object listStatusObject = sc.getAttribute("LIST_STATUS");
            Object listCategory = sc.getAttribute("LIST_CATEGORY");

            if (listStatusObject == null) {
                ArrayList<String> listStatus = new ArrayList<>();

                listStatus.add("Active");
                listStatus.add("Inactive");

                sc.setAttribute("LIST_STATUS", listStatus);
            }
            if (listCategory == null) {
                CategoryDAO cateDAO = new CategoryDAO();

                ArrayList<String> listCate = cateDAO.loadCategory();
                sc.setAttribute("LIST_CATEGORY", listCate);
            }

//set page
            ArrayList<Integer> pageList = dao.getPageListForAdmin();
            session.setAttribute("PAGES_LIST_ADMIN", pageList);
            
        } catch (SQLException ex) {
            log("SQLException_AdminStartupServlet", ex.getCause());
        } catch (NamingException ex) {
            log("NamingException_AdminStartupServlet", ex.getCause());
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
