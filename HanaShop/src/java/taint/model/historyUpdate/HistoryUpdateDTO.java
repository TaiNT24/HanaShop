/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.historyUpdate;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class HistoryUpdateDTO implements Serializable{
    private int historyID;
    private int foodID;
    private String userID;
    private String dateUpdate;
    private String detail;

    public HistoryUpdateDTO() {
    }

    public HistoryUpdateDTO(int foodID, String userID, String dateUpdate, String detail) {
        this.foodID = foodID;
        this.userID = userID;
        this.dateUpdate = dateUpdate;
        this.detail = detail;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
}
