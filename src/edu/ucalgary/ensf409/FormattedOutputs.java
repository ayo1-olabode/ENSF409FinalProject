/**
 * @author Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version 1.1
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.io.FileNotFoundException;

public interface FormattedOutputs {
	public String buildReport(String report); // interface method
	public void printReport(String report)  throws FileNotFoundException; // interface method
}
	
