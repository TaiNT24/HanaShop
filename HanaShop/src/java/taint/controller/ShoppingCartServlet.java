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
import taint.model.cart.CartDAO;
import taint.model.itemsInCart.ItemsInCartDAO;
import taint.model.itemsInCart.ItemsInCartDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/ShoppingCartServlet"})
public class ShoppingCartServlet extends HttpServlet {

    private final String CHECKOUT_PAGE = "CheckoutCart.jsp";

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
        String foodIDStr = request.getParameter("foodID");
        String quantityItem = request.getParameter("quantityItem");
        

        String url = CHECKOUT_PAGE;
        try {

            CartDAO cartDAO = new CartDAO();
            int cartID = cartDAO.getCurrentCartIDOfUser(userID);
            boolean isNewCart = false;
            if (cartID == -1) {
                isNewCart = cartDAO.createNewCartForUser(userID);
            }
            if (isNewCart) {
                cartID = cartDAO.getCurrentCartIDOfUser(userID);
            }
            ItemsInCartDAO itemsInCartDAO = new ItemsInCartDAO();
            if (foodIDStr != null) {
                int foodID = Integer.parseInt(foodIDStr);

                String btnAction = request.getParameter("btnAction");

                int quantity = itemsInCartDAO.checkExistedItem(foodID, cartID);
                int newQuantity = Integer.parseInt(quantityItem);
                
                if(newQuantity==quantity){
                    if (btnAction.equals("decreItem")) {
                        if (quantity != -1) {
                            itemsInCartDAO.updateQuantity(foodID, cartID, quantity, true);
                        }
                    } else {
                        if (quantity != -1) {
                            itemsInCartDAO.updateQuantity(foodID, cartID, quantity);
                        }
                    }
                }else{
                    itemsInCartDAO.updateNewQuantity(foodID, cartID, newQuantity);
                }
                

            }

            ArrayList<ItemsInCartDTO> listItems
                    = itemsInCartDAO.loadAllItemsOfCart(cartID);
            //set img and name for food
            listItems = itemsInCartDAO.setDetailItemsInCart(listItems);

            HttpSession session = request.getSession();
            
            session.setAttribute("LIST_ITEMS_IN_CART", listItems);

        } catch (NamingException ex) {
            log("NamingException_ShoppingCartServlet", ex.getCause());
        } catch (SQLException ex) {
            log("SQLException_ShoppingCartServlet", ex.getCause());
            System.out.println(ex);
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
