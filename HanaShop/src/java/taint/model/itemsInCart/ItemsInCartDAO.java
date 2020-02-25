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
import taint.model.cart.CartDAO;
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
                    totalPrice = Math.round(totalPrice*10)/10.0F;

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

    public int checkExistedItem(int foodID, int cartID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select Quantity "
                + "from ItemsInCart "
                + "where FoodID = ? and CartID = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, foodID);
                preStm.setInt(2, cartID);

                rs = preStm.executeQuery();

                if (rs.next()) {
                    return rs.getInt("Quantity");
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

    public boolean updateQuantity(int foodID, int cartID, int quantity)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "update ItemsInCart "
                + "set Quantity = ? "
                + "where FoodID = ? and CartID =?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, quantity + 1);
                preStm.setInt(2, foodID);
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

    public boolean updateQuantity(int foodID, int cartID, int quantity, boolean decreItem)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "update ItemsInCart "
                + "set Quantity = ? "
                + "where FoodID = ? and CartID =?";
        try {
            if (decreItem) {
                con = DBUtils.connectDB();
                if (con != null) {
                    preStm = con.prepareStatement(sqlQuery);

                    preStm.setInt(1, quantity - 1);
                    preStm.setInt(2, foodID);
                    preStm.setInt(3, cartID);

                    int row = preStm.executeUpdate();

                    if (row > 0) {

                        return true;
                    }
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

    public boolean updateNewQuantity(int foodID, int cartID, int newQuantity)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "update ItemsInCart "
                + "set Quantity = ? "
                + "where FoodID = ? and CartID =?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, newQuantity);
                preStm.setInt(2, foodID);
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
    
    public boolean deleteAItem(int itemID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "delete from ItemsInCart where ItemID = ?";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, itemID);

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

    public ArrayList<Integer> searchItemsOfCart(String searchVal)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<Integer> listCartID = new ArrayList<>();

        String sqlQuery = "select CartID "
                + "from ItemsInCart "
                + "where FoodID IN (SELECT FoodID "
                                + "FROM FoodAndDrink "
                                + "WHERE FoodName LIKE ? ) ";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, "%"+searchVal+"%");

                rs = preStm.executeQuery();

                int cartID;
                
                while (rs.next()) {
                    cartID = rs.getInt("CartID");

                    listCartID.add(cartID);
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
        return listCartID;
    }
    
    public ArrayList<Integer> searchItemsOfCartWithDate(String searchVal, 
            String fromDate, String toDate)
            
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<Integer> listCartID = new ArrayList<>();

        String sqlQuery = "select CartID "
                + "from ItemsInCart "
                + "where FoodID IN (SELECT FoodID "
                                + "FROM FoodAndDrink "
                                + "WHERE FoodName LIKE ?) ";
        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, "%"+searchVal+"%");

                rs = preStm.executeQuery();

                int cartID;
                
                CartDAO dao = new CartDAO();
                
                while (rs.next()) {
                    cartID = rs.getInt("CartID");

                    boolean isInRangeDate = 
                            dao.getHistoryCartIDWithDate(cartID, fromDate, toDate);
                    
                    if(isInRangeDate){
                        listCartID.add(cartID);
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
        return listCartID;
    }
    
    
}
