/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taint.model.foodAndDrink;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class FoodAndDrinkDTO implements Serializable {

    private int id;
    private String foodName;
    private String img;
    private String description;
    private float price;
    private int quantity;
    private String category;
    private String createDate;
    private String status = "Active";

    public FoodAndDrinkDTO() {
    }

    public FoodAndDrinkDTO(int id, String foodName, String img,
            String description, float price, int quantity, String category) {
        this.id = id;
        this.foodName = foodName;
        this.img = img;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return foodName + ", " + img + ", " + description + ", " + price 
                + ", " + quantity + ", " + category + ", " + status;
    }

}
