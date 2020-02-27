/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taint.model.cart.CartDTO;
import taint.model.itemsInCart.ItemsInCartDAO;
import taint.model.itemsInCart.ItemsInCartDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {

    private final String SEARCH_SUCCESS = "HistoryShopping.jsp";
    private final String NOT_SEARCH = "HistoryShoppingServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if getNewName servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String searchVal = request.getParameter("searchHistoryVal");
        String fromDateShopping = request.getParameter("fromDateShopping");
        String toDateShopping = request.getParameter("toDateShopping");

        boolean isSearchWithDate = !fromDateShopping.equals("") && !toDateShopping.equals("");
        
        String url = NOT_SEARCH;
        try {
            if (!searchVal.equals("") || isSearchWithDate) {
//                String searchValMatch = "[\\w]*" + searchVal + "[\\w]*";
                HttpSession session = request.getSession();

                ArrayList<CartDTO> listCartResultSearch = new ArrayList<>();

                ArrayList<CartDTO> listCartHistory
                        = (ArrayList<CartDTO>) session.getAttribute("LIST_HISTORY_CART");

//                Hashtable<Integer, ArrayList<ItemsInCartDTO>> listItemInCart
//                        = (Hashtable<Integer, ArrayList<ItemsInCartDTO>>) 
//                        session.getAttribute("LIST_ITEM_IN_CART");
                ItemsInCartDAO dao = new ItemsInCartDAO();

                ArrayList<Integer> listCartID;
                //not choose date
                if (!isSearchWithDate) {
//                    for (int i = 0; i < listCartHistory.size(); i++) {
//                        ArrayList<ItemsInCartDTO> listItem
//                                = listItemInCart.get(listCartHistory.get(i).getCartID());
//
//                        for (ItemsInCartDTO itemsInCartDTO : listItem) {
//                            if (itemsInCartDTO.getFoodName().matches(searchValMatch)) {
//                                listCartResultSearch.add(listCartHistory.get(i));
//                            }
//                        }
//                    }
                    //

                    listCartID = dao.searchItemsOfCart(searchVal);

                } else {//choose day
                    Date fromDateUtil = new Date(fromDateShopping);
                    Date toDateUtil = new Date(toDateShopping);

                    String fromDate = Utils.formatDateToString(fromDateUtil);
                    String toDate = Utils.formatDateToString(toDateUtil);

                    listCartID
                            = dao.searchItemsOfCartWithDate(searchVal, fromDate, toDate);
                }

//                for (int i = 0; i < listCartID.size(); i++) {
//                    int cartID = listCartID.get(i);
//
//                    for (CartDTO cartDTO : listCartHistory) {
//                        if (cartDTO.getCartID() == cartID) {
//                            listCartResultSearch.add(cartDTO);
//                        }
//                    }
//                }
                
                for (int i = 0; i < listCartHistory.size(); i++) {
                    int cartID = listCartHistory.get(i).getCartID();
                    
                    if(listCartID.contains(cartID)){
                        listCartResultSearch.add(listCartHistory.get(i));
                    }
                }

                request.setAttribute("LIST_SEARCH_HISTORY_CART", listCartResultSearch);
                request.setAttribute("searchHistoryVal", searchVal);
                request.setAttribute("SEARCH", "true");
                url = SEARCH_SUCCESS;
            }
        } catch (NamingException ex) {
            Logger.getLogger(SearchHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
     * @throws ServletException if getNewName servlet-specific error occurs
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
     * @throws ServletException if getNewName servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns getNewName short description of the servlet.
     *
     * @return getNewName String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
