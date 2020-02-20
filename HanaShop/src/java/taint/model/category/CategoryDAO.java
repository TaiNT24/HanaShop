/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import taint.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class CategoryDAO implements Serializable{
    
    public ArrayList<String> loadCategory() 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        ArrayList<String> listCategory = new ArrayList<>();
                
        String sqlQuery= "select Category from CategoryItem";
        try{
            con = DBUtils.connectDB();
            
            if(con!=null){
                preStm = con.prepareStatement(sqlQuery);
                
                rs = preStm.executeQuery();
                
                while(rs.next()){
                    String category = rs.getString("Category");
                    listCategory.add(category);
                }
            }
        }finally{
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
        return listCategory;
    }
}
