/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 2.1
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class mainDriver {
	private ArrayList<Client> clientlist;
	private InventoryDB inventorydb;
	private ArrayList<Food> food;
	private calculateFood calcFood;
	private StringBuilder sb = new StringBuilder();
	private String name;
	
	
	/**
	 * @param client the client
	 * @param inventorydb the inventory database
	 * @param food the food items
	 * @param name name
	 */
	public mainDriver(ArrayList<Client> client, InventoryDB inventorydb, ArrayList<Food> food, String name) {
		this.name = name;
		this.clientlist = client;
		this.inventorydb = inventorydb;
		this.food = food;
	}

	/**
	 creates the hamper as per the requirements entered by the user. Requires the number of
	 male adults, female adults, children over 8, and children under 8, and creates a hamper
	 based on each of the different requirements for nutrition values, and the available items in inventory.
	 */
	public void run() throws HamperCreationException {
		int hamper = 1;
		int hamper1 = 1;
		
		printOrder clientPrintReport = new printOrder(name);
		
		for (Client client : clientlist) {

			// throws if there are no people in a hamper at all
			if (client.getMaleAdults() == 0 && client.getFemaleAdults() == 0 && client.getChildOver8() == 0 && client.getChildUnder8() == 0 ) {
				throw new HamperCreationException();
			}

			clientPrintReport.buildReport("Hamper " + hamper1 + ": ");
			hamper1++;

			// displays the number/types of people in each hamper
			if(client.getMaleAdults() > 0) {
				clientPrintReport.buildReport(client.getMaleAdults() + " Adult Male, ");
	        }
	        if(client.getFemaleAdults() > 0) {
	        	clientPrintReport.buildReport(client.getFemaleAdults() + " Adult Female, ");
	        }
	        if(client.getChildOver8() > 0) {
	        	clientPrintReport.buildReport(client.getChildOver8() + " Child Over 8, ");
	        }
	        if(client.getChildUnder8() > 0) {
	        	clientPrintReport.buildReport(client.getChildUnder8() + " Child Under 8, ");
	        }
	        if(clientPrintReport.getSb().length() > 1) {
	        	clientPrintReport.clearString();
	        }
	        
	        clientPrintReport.buildReport("\n");

		}
		clientPrintReport.buildReport("\n");

		/*
		 calculating the food required per the amount of male adults, female adults
		 children under 8, and children over 8, for the hampers
		 */
		for (Client client : clientlist) {
			calculateFood calcFood = new calculateFood(client);
			String report = calcFood.getFoodString();

			//console print out of hampers for debugging
			//System.out.println(report);

			//displays the hamper with the food items in said hamper
			clientPrintReport.buildReport("Hamper " + hamper + " Items:" + "\n");
			clientPrintReport.buildReport(calcFood.getFoodString());
			clientPrintReport.buildReport("\n");
			hamper++;
		}
		
		try {
			clientPrintReport.printReport(clientPrintReport.getSb().toString());
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @return the inventorydb
	 */
	public InventoryDB getInventorydb() {
		return inventorydb;
	}

	/**
	 * @return the food
	 */
	public ArrayList<Food> getFood() {
		return food;
	}

	/**
	 * @return the calcFood
	 */
	public calculateFood getCalcFood() {
		return calcFood;
	}

	/**
	 * @param food the food to set
	 */
	public void setFood(ArrayList<Food> food) {
		this.food = food;
	}
}
