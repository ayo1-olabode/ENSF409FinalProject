/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.9
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

import java.io.File;
import java.time.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Objects;

public class printOrder implements FormattedOutputs {

    //initializing variables
    private StringBuilder sb = new StringBuilder();
    LocalDate lt = LocalDate.now();
    private String name;

    //printing name of the user
    public printOrder(String name) {
    	this.name = name; 
    }

    //build stringBuilder object in which each hamper is appended
    public String buildReport(String report)  {
    	sb.append(report);
        return sb.toString();
    }

    /**
     formatting the order form, and printing it out in the same format as "successful example"
     saves the output form in a director below, and outputs as a txt file
     in the output file, prints the first two strings, then gets the name of the user and prints it,
     as well as the local time (time of using the program).
     then prints the items in the hampers; sets output stream to a file and prints hamper
     */
    public void printReport(String report) throws FileNotFoundException {
        //System.out.println("Order Report");
        File saveFile = new File("OrderReports/OrderForm.txt");

        PrintStream outputFile = new PrintStream(saveFile);
        System.setOut(outputFile);
        
        System.out.println("Example Food Bank\r\n"
        		+ "Hamper Order Form\n");
        
        System.out.println("Name: " + getName());
        System.out.println("Date: " + lt + "\n" );
        System.out.println("Original Request");
        System.out.println(report);
    }

    //username
    public String getName() {
        // handles empty name input
        if (Objects.equals(name, "")) {
            return "N/A";
        }
    	return name;
    }

    /**
	 * @return the sb
	 */
	public StringBuilder getSb() {
		return sb;
	}

    //removes commas
	public void clearString() {
		sb.setLength(sb.length() - 2);
    }

}
