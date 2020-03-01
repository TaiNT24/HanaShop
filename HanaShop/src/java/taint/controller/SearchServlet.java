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
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();

        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);

            searchVal = (String) session.getAttribute("searchVal");
            searchByFilter = (String) session.getAttribute("SearchByFilter");
            searchByCategory = (String) session.getAttribute("SearchByCategory");
            priceVal = (String) session.getAttribute("priceVal");
        }

        String url = SEARCH_FAIL;
        try {

            if (!searchVal.equals("") || searchByFilter != null) {
                session.setAttribute("searchVal", searchVal);

                FoodAndDrinkDAO dao = new FoodAndDrinkDAO();
                ArrayList<FoodAndDrinkDTO> listSearch;

                if (searchByFilter != null) {
                    session.setAttribute("SearchByFilter", searchByFilter);
                    if (!searchByFilter.equals("Price")) {

                        if (!searchByCategory.equals("Category")) {
                            session.setAttribute("SearchByCategory", searchByCategory);
                            listSearch
                                    = dao.userSearchByCategory(searchVal, searchByCategory, page);

                        } else { // choose filter, but not choose category => search by name
                            listSearch = dao.userSearchByName(searchVal, page);
                            session.removeAttribute("SearchByCategory");
                            session.removeAttribute("SearchByFilter");
                        }

                    } else {
                        int price = Integer.parseInt(priceVal);
                        int fromPrice = price - 5;
                        int toPrice = price + 5;
                        session.setAttribute("priceVal", priceVal);

                        session.removeAttribute("SearchByCategory");
                        listSearch
                                = dao.userSearchByPrice(searchVal,
                                         fromPrice, toPrice, page);

                    }
                } else { // no filter => search by name
                    listSearch = dao.userSearchByName(searchVal, page);
                    session.removeAttribute("SearchByCategory");
                    session.removeAttribute("SearchByFilter");
                }
                request.setAttribute("LIST_SEARCH", listSearch);

                // set page 
                ArrayList<Integer> pageList
                        = dao.getPageListForUserSearch(searchVal,
                                 searchByCategory, searchByFilter, priceVal);

                session.setAttribute("PAGES_LIST_SEARCH", pageList);
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
