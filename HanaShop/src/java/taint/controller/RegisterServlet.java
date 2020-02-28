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
import taint.model.account.AccountDAO;
import taint.model.account.AccountDTO;
import taint.model.account.ErrorAccountDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    private final String VALID_REGISTER = "Login.jsp";
    private final String INVALID_REGISTER = "RegisterNewAccount.jsp";
    
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

        String userID = request.getParameter("txtUserID").trim();
        String name = request.getParameter("txtName").trim();
        String password = request.getParameter("txtPassword");
        String confPassword = request.getParameter("txtConfPassword");
        
        AccountDAO dao = new AccountDAO();
        ErrorAccountDTO error = null;
        
        String url = INVALID_REGISTER;
        try {
            //check input text
            if(userID.equals("")){
                if(error==null){
                    error = new ErrorAccountDTO();
                }
                error.setEmail("This field can't be empty");
            }
            //check duplicate email
            if(dao.checkDupUserID(userID)){
                if(error==null){
                    error = new ErrorAccountDTO();
                }
                error.setEmail("This User ID has been existed !"
                        + "Please choose another email");
            }
            if(name.equals("")){
                if(error==null){
                    error = new ErrorAccountDTO();
                }
                error.setName("This field can't be empty");
            }
            if(password.length()<6){
                if(error==null){
                    error = new ErrorAccountDTO();
                }
                error.setPassword("Password at least has 6 characters");
            }
            if(confPassword.equals("")){
                if(error==null){
                    error = new ErrorAccountDTO();
                }
                error.setConfPassword("This field can't be empty");
            }
            if(!confPassword.equals(password)){
                if(error==null){
                    error = new ErrorAccountDTO();
                }
                error.setConfPassword("Confirm password not match password");
            }
            
            if(error!=null){    // there is error
                request.setAttribute("ErrorRegister", error);
            }else{
                url = VALID_REGISTER;
                request.setAttribute("RegisterSuccess", "success");
                AccountDTO dto = new AccountDTO(userID, name, password);
                
                dao.insertNewAccount(dto);
            }
            
        } catch (SQLException ex) {
            log("SQLException_RegisterNewAccount", ex.getCause());
        } catch (NamingException ex) {
            log("NamingException_RegisterNewAccount", ex.getCause());
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
