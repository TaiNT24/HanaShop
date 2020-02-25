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
import javax.naming.NamingException;
import taint.utils.DBUtils;

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

        ArrayList<FoodAndDrinkDTO> listAvalableProduct = new ArrayList<>();

        String sqlQuery = "SELECT FoodID, FoodName, Img, Description, Price,"
                + "Quantity, Category "
                + "FROM FoodAndDrink "
                + "WHERE Status = 'Active' and Quantity >0 "
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

                    listAvalableProduct.add(dto);

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
        return listAvalableProduct;
    }

    public ArrayList<FoodAndDrinkDTO> loadAllProduct(int page)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<FoodAndDrinkDTO> listAllProduct = new ArrayList<>();

        String sqlQuery = "SELECT FoodID, FoodName, Img, Description, Price,"
                + "Quantity, Category, Status "
                + "FROM FoodAndDrink "
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
                String status;

                while (rs.next()) {
                    id = rs.getInt("FoodID");
                    foodName = rs.getString("FoodName");
                    img = rs.getString("Img");
                    description = rs.getString("Description");
                    price = rs.getFloat("Price");
                    quantity = rs.getInt("Quantity");
                    category = rs.getString("Category");
                    status = rs.getString("Status");

                    FoodAndDrinkDTO dto = new FoodAndDrinkDTO(id, foodName, img,
                            description, price, quantity, category);
                    dto.setStatus(status);

                    listAllProduct.add(dto);
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
        return listAllProduct;
    }

    public ArrayList<FoodAndDrinkDTO>
            userSearchByCategory(String searchVal, String category, int page)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<FoodAndDrinkDTO> listSearch = new ArrayList<>();

        String sqlQuery = "SELECT FoodID, FoodName, Img, Description, Price,"
                + "Quantity, Category "
                + "FROM FoodAndDrink "
                + "WHERE Status = 'Active' and Quantity >0 "
                + "and FoodName like ? and Category=? "
                + "ORDER BY CreateDate DESC "
                + "OFFSET ? ROWS "
                + "FETCH NEXT ? ROWS ONLY";

        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, "%" + searchVal + "%");
                preStm.setString(2, category);

                int dismissRecord = (page - 1) * RECORDS_IN_PAGE;

                preStm.setInt(3, dismissRecord);
                preStm.setInt(4, RECORDS_IN_PAGE);

                rs = preStm.executeQuery();

                int id;
                String foodName;
                String img;
                String description;
                float price;
                int quantity;

                while (rs.next()) {
                    id = rs.getInt("FoodID");
                    foodName = rs.getString("FoodName");
                    img = rs.getString("Img");
                    description = rs.getString("Description");
                    price = rs.getFloat("Price");
                    quantity = rs.getInt("Quantity");

                    FoodAndDrinkDTO dto = new FoodAndDrinkDTO(id, foodName, img,
                            description, price, quantity, category);

                    listSearch.add(dto);

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
        return listSearch;
    }

    public ArrayList<FoodAndDrinkDTO>
            userSearchByName(String searchVal, int page)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<FoodAndDrinkDTO> listSearch = new ArrayList<>();

        String sqlQuery = "SELECT FoodID, FoodName, Img, Description, Price,"
                + "Quantity, Category "
                + "FROM FoodAndDrink "
                + "WHERE Status = 'Active' and Quantity >0 "
                + "and FoodName like ? "
                + "ORDER BY CreateDate DESC "
                + "OFFSET ? ROWS "
                + "FETCH NEXT ? ROWS ONLY";

        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, "%" + searchVal + "%");

                int dismissRecord = (page - 1) * RECORDS_IN_PAGE;

                preStm.setInt(2, dismissRecord);
                preStm.setInt(3, RECORDS_IN_PAGE);

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

                    listSearch.add(dto);

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
        return listSearch;
    }

    public ArrayList<FoodAndDrinkDTO>
            userSearchByPrice(String searchVal, int fromPrice, int toPrice, int page)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        ArrayList<FoodAndDrinkDTO> listSearch = new ArrayList<>();

        String sqlQuery = "SELECT FoodID, FoodName, Img, Description, Price,"
                + "Quantity, Category "
                + "FROM FoodAndDrink "
                + "WHERE Status = 'Active' "
                + "and FoodName like ? "
                + "and Price BETWEEN ? and ? "
                + "ORDER BY CreateDate DESC "
                + "OFFSET ? ROWS "
                + "FETCH NEXT ? ROWS ONLY";

        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setString(1, "%" + searchVal + "%");
                preStm.setInt(2, fromPrice);
                preStm.setInt(3, toPrice);

                int dismissRecord = (page - 1) * RECORDS_IN_PAGE;

                preStm.setInt(4, dismissRecord);
                preStm.setInt(5, RECORDS_IN_PAGE);

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

                    listSearch.add(dto);

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
        return listSearch;
    }

    public float getPriceFood(int FoodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select Price from FoodAndDrink "
                + "where FoodID=?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, FoodID);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    return rs.getFloat("Price");
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
        return 0;
    }

    public String getImgFood(int FoodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select Img from FoodAndDrink "
                + "where FoodID=?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, FoodID);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    return rs.getString("Img");
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

    public String getNameFood(int FoodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        String sqlQuery = "select FoodName from FoodAndDrink "
                + "where FoodID=?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, FoodID);

                rs = preStm.executeQuery();
                if (rs.next()) {
                    return rs.getString("FoodName");
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

    public boolean quickUpdate(int foodID, String status, String category)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement preStm = null;

        String sqlQuery = "Update FoodAndDrink "
                + "set Category=?, Status=? "
                + "where FoodID=?";
        try {
            con = DBUtils.connectDB();

            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(3, foodID);
                preStm.setString(2, status);
                preStm.setString(1, category);

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

    public FoodAndDrinkDTO searchFood(int foodID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        FoodAndDrinkDTO dto = new FoodAndDrinkDTO();

        String sqlQuery = "SELECT FoodID, FoodName, Img, Description, Price,"
                + "Quantity, Category, Status "
                + "FROM FoodAndDrink "
                + "WHERE FoodID = ?";

        try {
            con = DBUtils.connectDB();
            if (con != null) {
                preStm = con.prepareStatement(sqlQuery);

                preStm.setInt(1, foodID);

                rs = preStm.executeQuery();

                int id;
                String foodName;
                String img;
                String description;
                float price;
                int quantity;
                String category;
                String status;

                if (rs.next()) {
                    id = rs.getInt("FoodID");
                    foodName = rs.getString("FoodName");
                    img = rs.getString("Img");
                    description = rs.getString("Description");
                    price = rs.getFloat("Price");
                    quantity = rs.getInt("Quantity");
                    category = rs.getString("Category");
                    status = rs.getString("Status");

                    dto = new FoodAndDrinkDTO(id, foodName, img,
                            description, price, quantity, category);
                    dto.setStatus(status);

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
        return dto;
    }

}
