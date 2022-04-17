/* Authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
* version: 1.5
* since: 1.0
*/

package edu.ucalgary.ensf409;

import java.util.ArrayList;


public class calculateFood {
	InventoryDB inventory = new InventoryDB("jdbc:mysql://localhost/food_inventory", "ensf", "student");
	private Client client;
	private ArrayList<Food> food;
	private ArrayList<Food> finalFood;
	private String finalString;
	
	//total values for the single hamper
	private int totalWG;
	private int totalFV;
	private int totalP;
	private int totalO;
	private int totalC;
	
	public calculateFood(Client client) throws HamperCreationException {
		this.finalString = "";
	    inventory.initializeConnection();
		this.client = client;
		this.food = inventory.selectAllNames();
		this.finalFood = new ArrayList<Food>();
		//continue to setting the total values
		setTotals();
	}
	
	//Set the total nutrition content needed for the hamper
	private void setTotals() throws HamperCreationException {
		totalWG = client.getTotalWholeGrains();
		totalFV = client.getTotalFruitsVeggies();
		totalP = client.getTotalProtein();
		totalO = client.getTotalOther();
		totalC = client.getTotalCalories();
		//continue to calculation of hamper
		calculateWG();
	}
	
	//fill the whole grain content while taking account for the rest
	private void calculateWG() throws HamperCreationException {
		int i;
		for(i = 0; i < food.size(); i++){
			if(totalWG < 0) {	//continue through loop until whole grain content is full
				break;
			}
			totalWG = totalWG - food.get(i).getGrainContent();
			totalFV = totalFV - food.get(i).getFvContent();
			totalP = totalP - food.get(i).getProContent();
			totalO = totalO - food.get(i).getOther();
			totalC = totalC - food.get(i).getCalories();
			finalFood.add(food.get(i));
			finalString = finalString + food.get(i).getFoodID() + "    " + food.get(i).getFoodName() + "\n";
			inventory.deleteFood(food.get(i).getFoodName());
		}
		if(totalWG < 0 && totalFV < 0 && totalP < 0 && totalO < 0 && totalC < 0) {	//if all values full return
			return;
		}
		calculateFV(i);
	}
	
	//fill fruit and veggies without adding any more to grain content
	private void calculateFV(int index) throws HamperCreationException {
		int j;
		for(j = index; j < food.size(); j++){
			if(totalFV < 0) {	//continue through loop until fruit and veggie content is full
				break;
			}
			if(food.get(j).getGrainContent() == 0) {	//try to fill baskets without adding more to nutrients from before
				totalFV = totalFV - food.get(j).getFvContent();
				totalP = totalP - food.get(j).getProContent();
				totalO = totalO - food.get(j).getOther();
				totalC = totalC - food.get(j).getCalories();
				finalFood.add(food.get(j));
				finalString = finalString + food.get(j).getFoodID() + "    " + food.get(j).getFoodName() + "\n";
				inventory.deleteFood(food.get(j).getFoodName());
			}
		}
		if(totalWG < 0 && totalFV < 0 && totalP < 0 && totalO < 0 && totalC < 0) {	//if all values full return
			return;
		}
		calculateP(j);
	}
	
	//fill protein content without adding any more grain and fruit and veggie content
	private void calculateP(int index) throws HamperCreationException {
		int k;
		for(k = index; k < food.size(); k++){
			if(totalP < 0) {	//continue through loop until protein content is full
				break;
			}
			if(food.get(k).getGrainContent() == 0 || food.get(k).getFvContent() == 0) {	//try to fill baskets without adding more to nutrients from before
				totalP = totalP - food.get(k).getProContent();
				totalO = totalO - food.get(k).getOther();
				totalC = totalC - food.get(k).getCalories();
				finalFood.add(food.get(k));
				finalString = finalString + food.get(k).getFoodID() + "    " + food.get(k).getFoodName() + "\n";
				inventory.deleteFood(food.get(k).getFoodName());
			}
		}
		if(totalWG < 0 && totalFV < 0 && totalP < 0 && totalO < 0 && totalC < 0) {	//if all values full return
			return;
		}
		calculateO(k);
	}
	
	//fill other content without adding any more to grain, fruit and veggie, and protein
	private void calculateO(int index) throws HamperCreationException {
		int l;
		for(l = index; l < food.size(); l++){
			if(totalO < 0) {	//continue through loop until other content is full
				break;
			}
			//try to fill baskets without adding more to nutrients from before
			if(food.get(l).getGrainContent() == 0 || food.get(l).getFvContent() == 0 || food.get(l).getProContent() == 0) {
				totalO = totalO - food.get(l).getOther();
				totalC = totalC - food.get(l).getCalories();
				finalFood.add(food.get(l));
				finalString = finalString + food.get(l).getFoodID() + "    " + food.get(l).getFoodName() + "\n";
				inventory.deleteFood(food.get(l).getFoodName());
			}
		}
		if(totalWG < 0 && totalFV < 0 && totalP < 0 && totalO < 0 && totalC < 0) {	//if all values full return
			return;
		}
		calculateC(l);
	}
	
	//fill calorie content without adding any more to grain, fruit and vegtable, protein, and other
	private void calculateC(int index) throws HamperCreationException {
		int p;
		for(p = index; p < food.size(); p++){
			if(totalC < 0) {
				//continue through loop until calorie content is full content is full
				break;
			}
			////try to fill baskets without adding more to nutrients from before
			if(food.get(p).getGrainContent() == 0 || food.get(p).getFvContent() == 0 || food.get(p).getProContent() == 0 || food.get(p).getOther() == 0) {
				totalC = totalC - food.get(p).getCalories();
				finalFood.add(food.get(p));
				finalString = finalString + food.get(p).getFoodID() + "    " + food.get(p).getFoodName() + "\n";
				inventory.deleteFood(food.get(p).getFoodName());
			}
		}
		
		finalCheck(p);
	}
	
	//may need this function if calculate not working properly
	private void finalCheck(int index) throws HamperCreationException {
		if(totalWG < 0 && totalFV < 0 && totalP < 0 && totalO < 0 && totalC < 0) {
			return;
		}
		else {
			for(int q = 0; q < food.size(); q++) {
				if(totalWG < 0 && totalFV < 0 && totalP < 0 && totalO < 0 && totalC < 0) {
					return;
				}
				if(!(finalFood.contains(food.get(q)))) {
					finalFood.add(food.get(q));
					finalString = finalString + food.get(q).getFoodID() + "    " + food.get(q).getFoodName() + "\n";
					inventory.deleteFood(food.get(q).getFoodName());
				}
			}
			throw new HamperCreationException();
		}
	}

	//return final string of hamper info after all calculations
	public String getFoodString() {
		return this.finalString;
	}

	//returns ArrayList of all the food items in hamper
	public ArrayList<Food> getFoodArray() {
		return this.finalFood;
	}
}