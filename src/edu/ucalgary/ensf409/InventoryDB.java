/*
Copyright Ann Barcomb and Emily Marasco, 2022
Licensed under GPL v3
See LICENSE.txt for more information.
*/

/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 2.3
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class to access database and retrieve info from the database
 */
public class InventoryDB {

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private ArrayList<Food> arraylist = new ArrayList<Food>();
    private ClientValues foodValues;

    private Connection dbConnect;
    private ResultSet results;

    public InventoryDB(String url, String user, String pw) {

        // Database URL
        this.DBURL = url;

        // Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }


    //Must create a connection to the database, no arguments, no return value
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String getDburl() {
        return this.DBURL;
    }

    String getUsername() {
        return this.USERNAME;
    }

    String getPassword() {
        return this.PASSWORD;
    }


    //selects all the items in the database and returns a ArrayList of Food objects with values from database
    public ArrayList<Food> selectAllNames(){

        try {
            String query = "SELECT ItemID, Name, GrainContent, FVContent, ProContent, Other, Calories,\r\n"
            		+ "COUNT(*)\r\n"
            		+ "FROM available_food\r\n"
            		+ "GROUP BY Name, GrainContent, FVContent, ProContent, Other, Calories \r\n"
            		+ "HAVING COUNT(*) > 0;\r\n"
            		+ "";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            results = myStmt.executeQuery();

            //parses all food items' data and puts them into variables to be made into Food objects
            //which are stored in an ArrayList
            while (results.next()){
            	int foodid = Integer.parseInt(results.getString("ItemID"));
            	String name = results.getString("Name");
            	int grain = Integer.parseInt(results.getString("GrainContent"));
            	int fruit = Integer.parseInt(results.getString("FVContent"));
            	int protein = Integer.parseInt(results.getString("ProContent"));
            	int other = Integer.parseInt(results.getString("Other"));
            	int calories = Integer.parseInt(results.getString("Calories"));
            	int count = Integer.parseInt(results.getString("COUNT(*)"));
            	arraylist.add(new Food(foodid,name, grain, fruit, protein, other, calories, count));
            	
            }

            results.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
               
        return arraylist;
    }

    //select food values per client from database --> daily_client_needs
    public ClientValues selectFoodValues(){
     
    	int AMgrain = 0;
    	int AMfruit = 0;
    	int AMprotein= 0;
    	int AMother= 0;
    	int AMcalories= 0;
    	
    	int AFgrain= 0;
    	int AFfruit= 0;
    	int AFprotein= 0;
    	int AFother= 0;
    	int AFcalories= 0;
    	
    	int Cgrain= 0;
    	int Cfruit= 0;
    	int Cprotein= 0;
    	int Cother= 0;
    	int Ccalories= 0;
    	
    	int C8grain= 0;
    	int C8fruit= 0;
    	int C8protein= 0;
    	int C8other= 0;
    	int C8calories= 0;
    	
        try {
            String query = "SELECT * FROM daily_client_needs;";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            results = myStmt.executeQuery();
            
            int counter = 0;
            while (results.next()){
                //parses all daily_client_needs' columns and puts them into variables to be made into ClientValue objects
                //returns a ClientValue object
            	String Client = results.getString("Client");
            	if (counter == 0) {
            		AMgrain = Integer.parseInt(results.getString("WholeGrains"));
                    AMfruit = Integer.parseInt(results.getString("FruitVeggies"));
                	AMprotein = Integer.parseInt(results.getString("Protein"));
                	AMother = Integer.parseInt(results.getString("Other"));
                	AMcalories = Integer.parseInt(results.getString("Calories"));
            	}

            	if (counter == 1) {
            		AFgrain = Integer.parseInt(results.getString("WholeGrains"));
                	AFfruit = Integer.parseInt(results.getString("FruitVeggies"));
                	AFprotein = Integer.parseInt(results.getString("Protein"));
                	AFother = Integer.parseInt(results.getString("Other"));
                	AFcalories = Integer.parseInt(results.getString("Calories"));
            	}
            	
            	if (counter == 2) {
            		Cgrain = Integer.parseInt(results.getString("WholeGrains"));
                	Cfruit = Integer.parseInt(results.getString("FruitVeggies"));
                	Cprotein = Integer.parseInt(results.getString("Protein"));
                	Cother = Integer.parseInt(results.getString("Other"));
                	Ccalories = Integer.parseInt(results.getString("Calories"));
                	
            	}
            	
            	if (counter == 3) {
            		C8grain = Integer.parseInt(results.getString("WholeGrains"));
                	C8fruit = Integer.parseInt(results.getString("FruitVeggies"));
                	C8protein = Integer.parseInt(results.getString("Protein"));
                	C8other = Integer.parseInt(results.getString("Other"));
                	C8calories = Integer.parseInt(results.getString("Calories"));	
                	
            	}
            	counter++;
            }
            
            foodValues = new ClientValues(AMgrain,AMfruit,AMprotein,AMother,AMcalories,AFgrain,AFfruit,AFprotein,AFother,AFcalories,
            		Cgrain,Cfruit,Cprotein,Cother,Ccalories,C8grain,C8fruit,C8protein,C8other,C8calories);

            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodValues;
    }

    //simple delete function, where we delete from database using the Food name as the key
    public void deleteFood(String foodName){
        try {
        	String query = "DELETE FROM AVAILABLE_FOOD\r\n"
        			+ "WHERE Name= ?\r\n"
        			+ "LIMIT 1;";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, foodName);
            myStmt.executeUpdate();
            myStmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //close function
    public void close() {
        try {
            results.close();
            dbConnect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
