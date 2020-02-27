/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.historyUpdate;

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
public class HistoryUpdateDAO implements Serializable{
    
    public boolean recordUpdate(HistoryUpdateDTO dto) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement preStm = null;
        
        String sqlQuery = "insert into HistoryUpdate values(?,?,?,?)";
        try{
            con = DBUtils.connectDB();
            if(con!=null){
                preStm = con.prepareStatement(sqlQuery);
                
                preStm.setInt(1, dto.getFoodID());
                preStm.setString(2, dto.getUserID());
                preStm.setString(3, dto.getDateUpdate());
                preStm.setString(4, dto.getDetail());
                
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
