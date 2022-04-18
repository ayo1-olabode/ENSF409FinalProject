/**
 * @author Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version 2.1
 * @since 1.0
 */
package edu.ucalgary.ensf409;
import java.sql.*;

public class Food {
    private Connection dbConnect;
    private int foodID;
    private String foodName;
    private int foodStock;
    private int grainContent;
    private int fvContent;
    private int proContent;
    private int other;
    private int calories;

//    public void createConnection() throws SQLException {
//        try {
//            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/FOOD_INVENTORY", "user1", "ensf");
//        }
//        catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * constructor for our food items
	 * @param foodid is the ID number
	 * @param foodName is the name
     * @param grainContent grain content
	 * @param fvContent fruit/veggie content
	 * @param proContent protein content
	 * @param other other content
	 * @param calories caloric content
	 * @param foodStock quantity of the food item
	 */
	public Food(int foodid, String foodName, int grainContent, int fvContent, int proContent, int other, int calories,
			int foodStock) {
		this.foodID = foodid;
		this.foodName = foodName;
		this.grainContent = grainContent;
		this.fvContent = fvContent;
		this.proContent = proContent;
		this.other = other;
		this.calories = calories;
		this.foodStock = foodStock;
	}
	

	
    //getter and setters
    public int getFoodID() { return this.foodID; }
    public String getFoodName() {
        return this.foodName;
    }
    public int getGrainContent() {
        return this.grainContent;
    }
    public int getFvContent() {
        return this.fvContent;
    }
    public int getProContent() {
        return this.proContent;
    }
    public int getOther() {
        return this.other;
    }
    public int getCalories() {
        return this.calories;
    }
    public int getStock() {
    	return foodStock; 
    }
    public void setStock(int Stock) {
    	this.foodStock = Stock;
    }
    public void setFoodID(int foodId) { this.foodID = foodID; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public void setGrainContent(int grainContent) {
        this.grainContent = grainContent;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public void setFVContent(int fvContent) {
        this.fvContent = fvContent;
    }
    public void setOther(int other) {
        this.other = other;
    }
    public void setProtein(int proteinContent) {
        this.proContent = proteinContent;
    }
}
