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
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import taint.filterController.MappingText;
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.model.foodAndDrink.FoodAndDrinkDTO;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
@WebServlet(name = "CreateFoodServlet", urlPatterns = {"/CreateFoodServlet"})
@MultipartConfig
public class CreateFoodServlet extends HttpServlet {

    private final String CREATE_SUCCESS = "AdminStartupServlet";
    private final String CREATE_FAIL = "CreateNewFood.jsp";

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
        Date now = new Date();
        String dateNow = Utils.formatDateToString(now);

        String foodName = request.getParameter("foodName");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String category = request.getParameter("category");

        boolean error = false;

        String url = CREATE_FAIL;
        Part filePart = request.getPart("file");
        String img = filePart.getSubmittedFileName();

        try {
            
            int quantity = Integer.parseInt(quantityStr);

            FoodAndDrinkDAO dao = new FoodAndDrinkDAO();

            boolean isDupName = dao.checkDupName(foodName);
            if (isDupName) {
                request.setAttribute("ERROR_NAME", "This name has existed !");
                error = true;
            }
            
            if (quantity <= 0) {
                request.setAttribute("ERROR_QUANTITY", "Quantity is more than 0 !");
                error = true;
            }

            float price = Float.parseFloat(priceStr);
            
            if (price <= 0) {
                request.setAttribute("ERROR_PRICE", "Price is more than 0 !");
                error = true;
            }

            if (!error) {

                int lastIndex = img.lastIndexOf(".");

                String ext = "." + img.substring(lastIndex + 1);// duoi

                String oldImg = Utils.getNewName() + ext;

                img = "food/" + Utils.getNewName() + ext;

                FoodAndDrinkDTO dto = new FoodAndDrinkDTO(0, foodName, img,
                        description, price, quantity, category);
                dto.setCreateDate(dateNow);

                boolean isCreate = dao.createNewFood(dto);

                //if insert into DB success
                if (isCreate) {
                    ServletContext sc = request.getServletContext();
                    //mapping file
                    String fileName = (String) sc.getAttribute("FILE_NAME_MAPPING");

                    String realPath = request.getServletContext().getRealPath("/");
                    File mappingFile = new File(realPath + fileName);

                    Utils.writeIntoMappingFile(mappingFile, oldImg);
                    Utils.copyFileMappingToStatic(realPath, fileName);
//done write mapping file

//reload mapping file
                    MappingText mappingText = new MappingText();
                    HashMap<String, String> hashMap = mappingText.mapping(mappingFile);

                    sc.setAttribute("HASH_MAP", hashMap);

//                    start copy Img
                    File fileCopy = new File(realPath + "food/" + oldImg);

                    Utils.copyImg(filePart, fileCopy, realPath);

                    url = CREATE_SUCCESS;
                }
            }

        } catch (NamingException ex) {
            log("NamingException_CreateFoodServlet", ex.getCause());
        } catch (SQLException ex) {
            log("SQLException_CreateFoodServlet", ex.getCause());
        } catch (NumberFormatException ex) {
            log("NumberFormatException_CreateFoodServlet", ex.getCause());
            request.setAttribute("ERROR_PRICE", "Price must is number !");
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
