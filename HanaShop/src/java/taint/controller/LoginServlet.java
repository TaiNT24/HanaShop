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
import taint.model.account.AccountDAO;
import taint.model.account.AccountDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String USER_PAGE = "StartupServlet";
    private final String ADMIN_PAGE = "AdminStartupServlet";
    private final String INVALID = "Login.jsp";

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

        String userID = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");

        String name = request.getParameter("name");

        String url = INVALID;
        try {
            AccountDAO dao = new AccountDAO();
            HttpSession session = request.getSession(true);

            if (userID != null && password != null) {
                int roleAccount = dao.checkLogin(userID, password);

                if (roleAccount != -1) {

                    if (roleAccount == 0) {
                        url = USER_PAGE;

                    } else {
                        url = ADMIN_PAGE;
                    }

                    String userName = dao.getUserName(userID);

                    session.setAttribute("USER_ID", userID);
                    session.setAttribute("USERNAME", userName);
                    session.setAttribute("ROLE", roleAccount);

                } else {
                    if (name != null) {
                        AccountDTO dto = new AccountDTO(userID, name, password);

                        dao.insertNewAccount(dto);
                        session.setAttribute("USER_ID", userID);
                        session.setAttribute("USERNAME", name);
                        session.setAttribute("ROLE", 0);
                        url = USER_PAGE;
                    } else {
                        session.setAttribute("ERROR_LOGIN", "error");
                    }
                }
            }

        } catch (SQLException ex) {
            log("SQLException_Login", ex.getCause());
        } catch (NamingException ex) {
            log("NamingException_Login", ex.getCause());
        } finally {
            RequestDispatcher rd =request.getRequestDispatcher(url);
            rd.forward(request, response);
//            response.sendRedirect(url);
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
