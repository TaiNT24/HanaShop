/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.foodAndDrink.FoodAndDrinkDTO;
import taint.model.historyUpdate.HistoryUpdateDAO;
import taint.model.historyUpdate.HistoryUpdateDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@WebServlet(name = "UpdateFoodServlet", urlPatterns = {"/UpdateFoodServlet"})
@MultipartConfig
public class UpdateFoodServlet extends HttpServlet {

    private final String UPDATE_SUCCESS = "AdminStartupServlet";

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

        String status = request.getParameter("status");
        String category = request.getParameter("category");
        String foodIDStr = request.getParameter("foodID");

        Part filePart = request.getPart("img");

        String updateDetail = request.getParameter("updateDetail");

        FoodAndDrinkDAO dao = new FoodAndDrinkDAO();
        FoodAndDrinkDTO oldDTO;

        boolean isUpdate;
        String detailUpdate;

        String url = "StartupServlet";
        try {
            int foodID = Integer.parseInt(foodIDStr);

            oldDTO = dao.searchFood(foodID);

            String newImage = "";
            boolean isUpdateImage = false;

            if (updateDetail == null) {

                isUpdate = dao.quickUpdate(foodID, status, category);

                detailUpdate = "Quick Update: Old Status: " + oldDTO.getStatus()
                        + ", old Category: " + oldDTO.getCategory();
            } else {
                String foodName = request.getParameter("foodName");
//                String img = request.getParameter("img");
                String oldImg = request.getParameter("oldImg");
                String description = request.getParameter("description");
                String priceStr = request.getParameter("price");
                String quantityStr = request.getParameter("quantity");

                String img = filePart.getSubmittedFileName();

                float price = Float.parseFloat(priceStr);
                int quantity = Integer.parseInt(quantityStr);
                if (img.equals("")) {
                    img = oldImg;
                } else {
                    int lastIndex = img.lastIndexOf(".");
                    int lastIndexOldImg = oldImg.lastIndexOf(".");

                    String newExt = "." + img.substring(lastIndex + 1);// duoi

                    newImage = oldImg.substring(0, lastIndexOldImg) + newExt;

                    img = newImage;
                    isUpdateImage = true;
                }

                FoodAndDrinkDTO newDTO = new FoodAndDrinkDTO(foodID, foodName, img, description,
                        price, quantity, category);
                newDTO.setStatus(status);

                isUpdate = dao.updateDetail(newDTO);

                detailUpdate = "Update detail: Old DTO: "
                        + oldDTO.toString();
            }

            if (isUpdate) {
                Date now = new Date();
                String dateNow = Utils.formatDateToString(now);

                HistoryUpdateDAO historyUpdateDAO = new HistoryUpdateDAO();

                HttpSession session = request.getSession();
                String userID = (String) session.getAttribute("USER_ID");

                HistoryUpdateDTO historyUpdateDTO
                        = new HistoryUpdateDTO(foodID, userID, dateNow, detailUpdate);

                historyUpdateDAO.recordUpdate(historyUpdateDTO);

                //
                if (isUpdateImage) {
                    String realPath = request.getServletContext().getRealPath("/");
                    
                    //copy image
                    File fileCopy = new File(realPath + newImage);

                    Utils.copyImg(filePart, fileCopy, realPath);
                }

                url = UPDATE_SUCCESS;

            }
        } catch (NamingException ex) {
            log("NamingException_QuickUpdateServlet", ex.getCause());
        } catch (SQLException ex) {
            log("SQLException_QuickUpdateServlet", ex.getCause());
        } finally {
            response.sendRedirect(url);
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
