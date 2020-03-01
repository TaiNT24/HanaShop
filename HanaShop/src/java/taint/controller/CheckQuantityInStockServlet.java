/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.itemsInCart.ItemsInCartDAO;
import taint.model.itemsInCart.ItemsInCartDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "CheckQuantityInStockServlet", urlPatterns = {"/CheckQuantityInStockServlet"})
public class CheckQuantityInStockServlet extends HttpServlet {

    private final String PAYMENT_WITH_PAYPAL = "PaymentWithPayPal.jsp";
    private final String PAYMENT_BY_CAST = "PaymentByCastServlet";
    private final String ITEM_OUT_OF_STOCK = "CheckoutCart.jsp";

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

        String cartIDStr = request.getParameter("cartID");
        String totalPayment = request.getParameter("totalPayment");
        String paymentMethod = request.getParameter("paymentMethod");

        String url = "";

        try {
            int cartID = Integer.parseInt(cartIDStr);

            ItemsInCartDAO itemsDAO = new ItemsInCartDAO();
            FoodAndDrinkDAO foodDAO = new FoodAndDrinkDAO();

            ArrayList<ItemsInCartDTO> listItem = itemsDAO.loadAllItemsOfCart(cartID);
            Hashtable<Integer, Integer> listItemOutOfStock = new Hashtable<>();

            for (int i = 0; i < listItem.size(); i++) {
                int quantityUserBuy = listItem.get(i).getQuantity();
                int foodID = listItem.get(i).getFoodID();

                int quantityInStock = foodDAO.getQuantityFood(foodID);

                if (quantityUserBuy > quantityInStock) {
                    listItemOutOfStock.put(foodID, quantityInStock);
                }
            }
            if (listItemOutOfStock.isEmpty()) {
                request.setAttribute("cartID", cartID);
                
                if (paymentMethod.equalsIgnoreCase("PaymentByCast")) {
                    url = PAYMENT_BY_CAST;
                } else {
                    url = PAYMENT_WITH_PAYPAL;
                    request.setAttribute("totalPayment", totalPayment);
                }

            } else {
                url = ITEM_OUT_OF_STOCK;
                request.setAttribute("ITEM_OUT_OF_STOCK", listItemOutOfStock);
            }
        } catch (NamingException ex) {
            Logger.getLogger(CheckQuantityInStockServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckQuantityInStockServlet.class.getName()).log(Level.SEVERE, null, ex);
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
