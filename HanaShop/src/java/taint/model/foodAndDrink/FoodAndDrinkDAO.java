/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.foodAndDrink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.naming.NamingException;
import taint.utils.DBUtils;
import taint.utils.Utils;

/**
 *
 * @author nguye
 */
public class FoodAndDrinkDAO {
    
    private final int RECORDS_IN_PAGE = 2;

    //for normal user and guest
    public ArrayList<FoodAndDrinkDTO> loadActiveProduct(int page)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        ArrayList<FoodAndDrinkDTO> listActiveArticle = new ArrayList<>();
        
        String sqlQuery = "SELECT FoodID, FoodName, Img, Description, Price,"
                + "Quantity, Category "
                + "FROM FoodAndDrink "
                + "WHERE Status = 'Active' "
                + "ORDER BY CreateDate DESC "
                + "OFFSET ? ROWS "
                + "FETCH NEXT ? ROWS ONLY";
        try {
            con = DBUtils.connectDB();
            
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);
                
                int dismissRecord = (page - 1) * RECORDS_IN_PAGE;
                
                preStm.setInt(1, dismissRecord);
                preStm.setInt(2, RECORDS_IN_PAGE);
                
                rs = preStm.executeQuery();
                
                int id;
                String foodName;
                String img;
                String description;
                float price;
                int quantity;
                String category;
                
                while (rs.next()) {
                    id = rs.getInt("FoodID");
                    foodName = rs.getString("FoodName");
                    img = rs.getString("Img");
                    description = rs.getString("Description");
                    price = rs.getFloat("Price");
                    quantity = rs.getInt("Quantity");
                    category = rs.getString("Category");

                    FoodAndDrinkDTO dto = new FoodAndDrinkDTO(id, foodName, img,
                            description, price, quantity, category);
                    
                    listActiveArticle.add(dto);
                    
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
        return listActiveArticle;
    }
}
