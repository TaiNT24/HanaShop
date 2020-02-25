/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import javax.naming.NamingException;
import taint.utils.DBUtils;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
public class CartDAO implements Serializable {

    public int getCurrentCartIDOfUser(String userID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select CartID "
                + "from Cart "
                + "where UserID=? and BuyDate is null";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, userID);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("CartID");
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

    public boolean createNewCartForUser(String userID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "insert into Cart values(?,null,null,null)";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, userID);

                int row = preStm.executeUpdate();
                if (row > 0) {
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

    public boolean paymentByCash(int cartID, Date datePayment)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "update Cart "
                + "set Payment = ?, BuyDate=? "
                + "where CartID = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);
                
                String dateInsertDB = Utils.formatDateToString(datePayment);

                preStm.setString(1, "Cash on delivery");
                preStm.setString(2, dateInsertDB);                
                preStm.setInt(3, cartID);

                int row = preStm.executeUpdate();
                if (row > 0) {
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

    public ArrayList<CartDTO> getHistoryShopping(String userID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<CartDTO> listCart = new ArrayList<>();
        
        String sqlQuery = "select CartID, Total, Payment, BuyDate "
                + "from Cart "
                + "where UserID=? and BuyDate is not null "
                + "order by BuyDate DESC";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, userID);

                rs = preStm.executeQuery();
                while (rs.next()) {
                    int cartID = rs.getInt("CartID");
                    float total = rs.getFloat("Total");
                    String payment = rs.getString("Payment");
                    String buyDate = rs.getString("BuyDate");
                    
                    StringTokenizer stk = new StringTokenizer(buyDate, " ");
                    String date = stk.nextToken();
                    String time = stk.nextToken();
                    
                    String buyDateStrFormat = Utils.formatStringDate(date);
                    
                    String buyDateFormat = buyDateStrFormat + " "+ time;
                    
                    CartDTO dto = new CartDTO(userID, total, payment, buyDateFormat);
                    dto.setCartID(cartID);
                    
                    listCart.add(dto);
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
        return listCart;
    }
    
    public boolean getHistoryCartIDWithDate(int cartID, 
            String fromDate, String toDate)
            
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<CartDTO> listCart = new ArrayList<>();
        
        String sqlQuery = "select CartID "
                + "from Cart "
                + "where CartID = ? and (BuyDate >= ? and BuyDate <= ?) ";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, cartID);
                preStm.setString(2, fromDate);
                preStm.setString(3, toDate);

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
    
}
