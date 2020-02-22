/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.itemsInCart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import taint.model.foodAndDrink.FoodAndDrinkDAO;
import taint.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class ItemsInCartDAO implements Serializable {

    public boolean addAItem(ItemsInCartDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "insert into ItemsInCart values(?,?,?,?,?)";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, dto.getFoodID());
                preStm.setFloat(2, dto.getPrice());
                preStm.setInt(3, dto.getQuantity());
                preStm.setFloat(4, dto.getTotalPrice());
                preStm.setInt(5, dto.getCartID());

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

    public ArrayList<ItemsInCartDTO> loadAllItemsOfCart(int cartID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<ItemsInCartDTO> listItems = new ArrayList<>();

        String sqlQuery = "select ItemID, FoodID, Price, Quantity, TotalPrice "
                + "from ItemsInCart "
                + "where CartID = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, cartID);

                rs = preStm.executeQuery();

                int itemID;
                int foodID;
                float price;
                int quantity;
                float totalPrice;
                ItemsInCartDTO itemDTO;

                while (rs.next()) {
                    itemID = rs.getInt("ItemID");
                    foodID = rs.getInt("FoodID");
                    price = rs.getFloat("Price");
                    quantity = rs.getInt("Quantity");
                    totalPrice = rs.getFloat("TotalPrice");

                    itemDTO = new ItemsInCartDTO(foodID, price, quantity,
                            totalPrice, cartID);
                    itemDTO.setItemID(itemID);

                    listItems.add(itemDTO);
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
        return listItems;
    }

    public ArrayList<ItemsInCartDTO>
            setDetailItemsInCart(ArrayList<ItemsInCartDTO> listItems)
            throws NamingException, SQLException {

        FoodAndDrinkDAO foodAndDrinkDAO = new FoodAndDrinkDAO();
        int foodID;
        for (int i = 0; i < listItems.size(); i++) {
            foodID = listItems.get(i).getFoodID();

            String img = foodAndDrinkDAO.getImgFood(foodID);
            String name = foodAndDrinkDAO.getNameFood(foodID);

            listItems.get(i).setImg(img);
            listItems.get(i).setFoodName(name);
        }

        return listItems;
    }

}
