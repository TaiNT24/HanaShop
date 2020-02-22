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
import javax.naming.NamingException;
import taint.utils.DBUtils;

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
            if(con!=null){
                preStm = con.prepareStatement(sqlQuery);
                
                preStm.setString(1, userID);
                
                rs = preStm.executeQuery();
                if(rs.next()){
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
    
    
    public boolean createNewCartForUser(String userID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement preStm = null;
        
        String sqlQuery ="insert into Cart values(?,null,null,null)";
        try{
            con = DBUtils.connectDB();
            if(con!=null){
                preStm = con.prepareStatement(sqlQuery);
                
                preStm.setString(1, userID);
                
                int row = preStm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
            
        }finally{
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
