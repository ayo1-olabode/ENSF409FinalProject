/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 2.3
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class ProjectTestDesign {
	/*Client nutrition values from sql
	('Adult Male',	16,	28,	26,	30,	2500),
	('Adult Female', 16, 28, 26, 30, 2000),
	('Child over 8', 21, 33, 31, 15, 2200),
	('Child under 8', 21, 33, 31, 15, 1400);*/
	ClientValues nutritionValues = new ClientValues(16, 28, 26, 30, 2500, 16, 28, 26, 30, 2000, 21, 33, 31, 15, 2200, 21, 33, 31, 15, 2200);
    Client family1;
    ArrayList<Food> foods;

    @Test
    //Test the total values for a hamper from client
    public void testTotalNeedsClient() throws HamperCreationException {
    	int expectedAdultMale = 1;
        int expectedAdultFemale = 1;
        int expectedChildOver8 = 2;
        int expectedChildUnder8 = 1;
        family1 = new Client(expectedAdultMale, expectedAdultFemale, expectedChildUnder8, expectedChildOver8, nutritionValues);

        int actualMales = family1.getMaleAdults();
        int actualFemales = family1.getFemaleAdults();
        int actualUnder8s = family1.getChildUnder8();
        int actualOver8s = family1.getChildOver8();

        assertEquals("Incorrect values in amount of males", expectedAdultMale, actualMales);
        assertEquals("Incorrect values in amount of females", expectedAdultFemale, actualFemales);
        assertEquals("Incorrect values in amount of under 8 children", expectedChildUnder8, actualUnder8s);
        assertEquals("Incorrect values in amount of over 8 children", expectedChildOver8, actualOver8s);


        int actualWG = family1.getTotalWholeGrains();
        int actualFV = family1.getTotalFruitsVeggies();
        int actualP = family1.getTotalProtein();
        int actualO = family1.getTotalOther();
        int actualC = family1.getTotalCalories();

        assertEquals("Incorrect information in total grains", 95, actualWG);
        assertEquals("Incorrect information in total fruits and veggies", 155, actualFV);
        assertEquals("Incorrect information in total protein", 145, actualP);
        assertEquals("Incorrect information in total other", 105, actualO);
        assertEquals("Incorrect information in total calories", 11100, actualC);
    }

    @Test
    //test food class constructs values for a food with the proper values
    public void testFood() {
        //test if the grains are correct
       String expectedName = "Tomato Sauce, jar";
       Food food = new Food(1, expectedName, 0, 80, 10, 10, 120, 3);

       int actualItemID = food.getFoodID();
       String actualName = food.getFoodName();
       int actualWholeGrains = food.getGrainContent();
       int actualFruitVeggies = food.getFvContent();
       int actualProtein = food.getProContent();
       int actualOther = food.getOther();
       int atucalCalories = food.getCalories();
       int actualStock = food.getStock();

       assertEquals("Incorrect item ID", 1, actualItemID);
       assertEquals("Incorrect information in food name", expectedName, actualName);
       assertEquals("Incorrect information in whole grains", 0, actualWholeGrains);
       assertEquals("Incorrect information in fruit and veggies", 80, actualFruitVeggies);
       assertEquals("Incorrect information in protein", 10, actualProtein);
       assertEquals("Incorrect information in other", 10, actualOther);
       assertEquals("Incorrect information in calories", 120, atucalCalories);
       assertEquals("Incorrect information in stock", 3, actualStock);
    }

    @Test
    //Test calculate food returns a hamper to fit needs
    public void testCalculateFood() throws HamperCreationException {
    	int male = 1;
    	int female = 0;
    	int under8 = 0;
    	int over8 = 0;

    	Client family2 = new Client(male, female, under8, over8, nutritionValues);

    	foods = new ArrayList<Food>();
    	foods.add(new Food(1, "Tomato Sauce, jar", 0, 80, 10, 10, 120, 3));
    	foods.add(new Food(4, "Apple, dozen", 0, 100, 0, 0, 624, 2));
    	foods.add(new Food(6, "Granola Bar, box", 71, 0, 6, 23, 936, 1));
    	foods.add(new Food(7, "Chicken broth, can", 0, 0, 14, 86, 95, 1));
    	foods.add(new Food(8, "Baby carrots, pound", 0, 100, 0, 0, 159, 1));
    	foods.add(new Food(9, "Broccoli, 3 bunches", 0, 92, 8, 0, 621, 2));
    	//foods.add(new Food(11, "Wheat bread, loaf", 96, 0, 4, 0, 2192, 3));

    	calculateFood calc = new calculateFood(family2);

    	String order = calc.getFoodString();
    	String finalOrder = "1    Tomato Sauce, jar\n"
    			+ "4    Apple, dozen\n"
    			+ "6    Granola Bar, box\n"
    			+ "7    Chicken broth, can\n"
    			+ "8    Baby carrots, pound\n"
    			+ "9    Broccoli, 3 bunches\n";
    	assertEquals("hamper order is wrong", finalOrder , order);
    }

    @Test(expected = HamperCreationException.class)
    public void testMainDriver_InvalidClientException() throws HamperCreationException {
        InventoryDB db = new InventoryDB("jdbc:mysql://localhost/food_inventory", "root", "ayomi2002");
        ClientValues clientValues = db.selectFoodValues();
        Client client = new Client(0, 0, 0, 0, clientValues);
    }

    @Test(expected = HamperCreationException.class)
    public void testMainDriver_EmptyFoodItemsException() throws HamperCreationException {
        InventoryDB db = new InventoryDB("jdbc:mysql://localhost/food_inventory", "root", "ayomi2002");
        ClientValues clientValues = db.selectFoodValues();
        Client client = new Client(1, 1, 1, 1, clientValues);
        Client client1 = new Client(1, 1, 1, 1, clientValues);
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(client);
        clients.add(client1);
        foods = new ArrayList<Food>();
        mainDriver mainDriver = new mainDriver(clients, db, foods, "Bob");
        mainDriver.run();
    }
}