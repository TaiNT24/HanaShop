/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taint.model.cart.CartDAO;
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.itemsInCart.ItemsInCartDAO;
import taint.model.itemsInCart.ItemsInCartDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "PaymentByCastServlet", urlPatterns = {"/PaymentByCastServlet"})
public class PaymentByCastServlet extends HttpServlet {

    private final String PAYMENT_SUCCESS = "CheckoutCart.jsp";
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

        Date now = new Date();

        String cartIDStr = request.getParameter("cartID");
        String url = PAYMENT_SUCCESS;

        try {
            int cartID = Integer.parseInt(cartIDStr);

            CartDAO cartDAO = new CartDAO();
            ItemsInCartDAO itemsDAO = new ItemsInCartDAO();
            FoodAndDrinkDAO foodDAO = new FoodAndDrinkDAO();

            ArrayList<ItemsInCartDTO> listItem = itemsDAO.loadAllItemsOfCart(cartID);
            Hashtable<Integer,Integer> listItemOutOfStock = new Hashtable<>();

            for (int i = 0; i < listItem.size(); i++) {
                int quantityUserBuy = listItem.get(i).getQuantity();
                int foodID = listItem.get(i).getFoodID();

                int quantityInStock = foodDAO.getQuantityFood(foodID);

                if (quantityUserBuy > quantityInStock) {
                    listItemOutOfStock.put(foodID, quantityInStock);
                }
            }
            if (listItemOutOfStock.isEmpty()) {
                cartDAO.paymentByCash(cartID, now);
                
                //update quantity in stock
                for (int i = 0; i < listItem.size(); i++) {
                    int foodID = listItem.get(i).getFoodID();
                    int quantity = listItem.get(i).getQuantity();
                    
                    foodDAO.updateQuantity(foodID,quantity);
                }

                HttpSession session = request.getSession();
                session.removeAttribute("LIST_ITEMS_IN_CART");
                session.removeAttribute("CART_ID");

                request.setAttribute("PAYMENT_SUCCESSFUL", "TRUE");
            } else {
                url = ITEM_OUT_OF_STOCK;
                request.setAttribute("ITEM_OUT_OF_STOCK", listItemOutOfStock);
            }

        } catch (NamingException ex) {
            log("NamingException_PaymentByCast", ex.getCause());
        } catch (SQLException ex) {
            log("SQLException_PaymentByCast", ex.getCause());
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
