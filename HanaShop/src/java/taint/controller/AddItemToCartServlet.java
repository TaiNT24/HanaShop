/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.controller;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    private final String LOGIN_PAGE = "Login.jsp";
    private final String BACK_HOME = "StartupServlet";
    private final String BACK_SEARCH = "SearchServlet";

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

        String foodIDStr = request.getParameter("foodID");


        HttpSession session = request.getSession();

        String searchVal = (String) session.getAttribute("searchVal");
        String searchByFilter = (String) session.getAttribute("SearchByFilter");
        String searchByCategory = (String) session.getAttribute("SearchByCategory");

        String url = LOGIN_PAGE;
        try {

            String userID = (String) session.getAttribute("USER_ID");

            if (userID != null) {
                int foodID = Integer.parseInt(foodIDStr);

                CartDAO dao = new CartDAO();

                int cartID = dao.getCurrentCartIDOfUser(userID);
                boolean isNewCart = false;
                if (cartID == -1) {
                    isNewCart = dao.createNewCartForUser(userID);
                }
                if (isNewCart) {
                    cartID = dao.getCurrentCartIDOfUser(userID);
                }

                FoodAndDrinkDAO foodAndDrinkDAO = new FoodAndDrinkDAO();
                float price = foodAndDrinkDAO.getPriceFood(foodID);

                ItemsInCartDAO itemsInCartDAO = new ItemsInCartDAO();

                int quantity = itemsInCartDAO.checkExistedItem(foodID, cartID);
                if (quantity != -1) {
                    itemsInCartDAO.updateQuantity(foodID, cartID, quantity);
                }else{
                    ItemsInCartDTO itemsDTO
                            = new ItemsInCartDTO(foodID, price, 1, price, cartID);

                    itemsInCartDAO.addAItem(itemsDTO);
                }

                url = BACK_HOME;

                if (!searchVal.equals("") || searchByFilter != null
                        || !searchByCategory.equals("Category")) {
                    url = BACK_SEARCH;
                }
                
            }

        } catch (NamingException ex) {
            log("NamingException_AddItemToCartServlet", ex.getCause());
        } catch (SQLException ex) {
            log("SQLException_AddItemToCartServlet", ex.getCause());
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
