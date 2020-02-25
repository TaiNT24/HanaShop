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
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taint.model.cart.CartDAO;
import taint.model.cart.CartDTO;
import taint.model.itemsInCart.ItemsInCartDAO;
import taint.model.itemsInCart.ItemsInCartDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "HistoryShoppingServlet", urlPatterns = {"/HistoryShoppingServlet"})
public class HistoryShoppingServlet extends HttpServlet {

    private final String REDIRECT_HISTORY_PAGE = "HistoryPage";
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
        
        String userID = request.getParameter("userID");
        
        try {
            CartDAO cartDAO = new CartDAO();
            ArrayList<CartDTO> listCartHistory = cartDAO.getHistoryShopping(userID);
            
            ItemsInCartDAO itemICDAO = new ItemsInCartDAO();
            ArrayList<ItemsInCartDTO> listItems;
            
            Hashtable<Integer, ArrayList<ItemsInCartDTO>> listItemInCart 
                    = new Hashtable<Integer, ArrayList<ItemsInCartDTO>>();
            
            HttpSession session = request.getSession();
            session.setAttribute("LIST_HISTORY_CART", listCartHistory);
            
            for (int i = 0; i < listCartHistory.size(); i++) {
                listItems = 
                        itemICDAO.loadAllItemsOfCart(listCartHistory.get(i).getCartID());
                
                listItems = itemICDAO.setDetailItemsInCart(listItems);
                
                listItemInCart.put(listCartHistory.get(i).getCartID(), listItems);
                
            }
            
            session.setAttribute("LIST_ITEM_IN_CART", listItemInCart);
            
            
        } catch (NamingException ex) {
            log("NamingException_HistoryShopping",ex.getCause());
        } catch (SQLException ex) {
            log("SQLException_HistoryShopping",ex.getCause());
            System.out.println(ex);
        }finally{
            response.sendRedirect(REDIRECT_HISTORY_PAGE);
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
