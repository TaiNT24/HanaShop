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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.foodAndDrink.FoodAndDrinkDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String SEARCH_FAIL = "StartupServlet";
    private final String SEARCH_SUCCESS = "ResultUserSearch.jsp";

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

        String searchVal = request.getParameter("searchVal");
        String searchByFilter = request.getParameter("SearchByFilter");
        String searchByCategory = request.getParameter("SearchByCategory");
        String priceVal = request.getParameter("priceVal");

        String url = SEARCH_FAIL;
        try {
            if (!searchVal.equals("") || searchByFilter != null) {
                FoodAndDrinkDAO dao = new FoodAndDrinkDAO();
                ArrayList<FoodAndDrinkDTO> listSearch;

                if (searchByFilter != null) {
                    if (!searchByFilter.equals("Price")) {
                        
                        if (!searchByCategory.equals("Category")) {
                            listSearch
                                    = dao.userSearchByCategory(searchVal, searchByCategory, 1);

                        } else { // choose filter, but not choose category => search by name
                            listSearch = dao.userSearchByName(searchVal, 1);
                        }
                        
                    } else {
                        int price = Integer.parseInt(priceVal);
                        int fromPrice = price - 5;
                        int toPrice = price + 5;

                        listSearch = dao.userSearchByPrice(searchVal, fromPrice, toPrice, 1);
                    }
                } else { // no filter => search by name
                    listSearch = dao.userSearchByName(searchVal, 1);
                }
                request.setAttribute("LIST_SEARCH", listSearch);
                url = SEARCH_SUCCESS;
            }

        } catch (SQLException ex) {
            log("SQLException_SearchServlet", ex.getCause());
        } catch (NamingException ex) {
            log("NamingException_SearchServlet", ex.getCause());
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
