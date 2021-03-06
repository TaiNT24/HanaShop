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
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.foodAndDrink.FoodAndDrinkDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "GetObjUpdateDetailPageServlet",
        urlPatterns = {"/GetObjUpdateDetailPageServlet"})
public class GetObjUpdateDetailPageServlet extends HttpServlet {

    private final String GOTO_DETAIL_PAGE = "UpdateFoodDetail.jsp";

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

        FoodAndDrinkDAO dao = new FoodAndDrinkDAO();
        FoodAndDrinkDTO dto;

        String url = GOTO_DETAIL_PAGE;
        try {
            HttpSession session = request.getSession();
            Object roleObj = session.getAttribute("ROLE");
            if (roleObj == null) {
                url = "StartupServlet";
            }else if ((int) roleObj != 1) {
                url = "StartupServlet";
            } else {
                int foodID = Integer.parseInt(foodIDStr);

                dto = dao.searchFood(foodID);

                request.setAttribute("food", dto);
            }

        } catch (SQLException ex) {
            log("SQLException_UpdateFoodDetailServlet", ex.getCause());
        } catch (NamingException ex) {
            log("NamingException_UpdateFoodDetailServlet", ex.getCause());
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
