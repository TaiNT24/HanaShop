/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.cart;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class CartDTO implements Serializable{
    private int id;
    private String userID;
    private float total;
    private String payment;
    private String buyDate;

    public CartDTO() {
    }

    public CartDTO(String userID, float total, String payment, String buyDate) {
        this.userID = userID;
        this.total = total;
        this.payment = payment;
        this.buyDate = buyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }
    
    
}
