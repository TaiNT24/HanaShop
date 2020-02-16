/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import taint.utils.DBUtils;

/**
 * @author nguye Check user login
 */
public class AccountDAO implements Serializable {

    public int checkLogin(String email, String password)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select UserID, Role "
                + "from Account "
                + "where UserID = ? and Password = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                /*
 *SHA-256
                 */
//                String sha256hex = Utils.encodePassword(password);
//                
                preStm.setString(1, email);
                preStm.setString(2, password);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    boolean role = rs.getBoolean("Role");
                    
                    if(role == true){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public String getUserName(String userID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String url = "select FullName "
                + "from Account "
                + "where UserID = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(url);

                preStm.setString(1, userID);

                rs = preStm.executeQuery();

                if (rs.next()) {
                    return rs.getString("FullName");
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean checkDupUserID(String userID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String url = "select FullName "
                + "from Account "
                + "where UserID = ?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(url);

                preStm.setString(1, userID);

                rs = preStm.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertNewAccount(AccountDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;

        String url = "insert into Account "
                + "values(?,?,?,?)";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(url);

                preStm.setString(1, dto.getUserID());
                preStm.setString(2, dto.getPassword());
                preStm.setString(3, dto.getFullName());
                preStm.setBoolean(4, dto.isRole());

                /*
 * SHA-256
                 */
//                String sha256hex = Utils.encodePassword(dto.getPassword());
                
                

                int count = preStm.executeUpdate();
                if (count > 0) {
                    return true;
                }
            }
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    
}
