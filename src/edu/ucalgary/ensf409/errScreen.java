/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.5
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

public class errScreen {
	//opens frame in gui that showcases error, in which it prompts the user
	//if they want to continue using the program or not
	public static void getErrorScreen() {
		errFrame error = new errFrame();
		error.setVisible(true);
		error.setResizable(false);
	}
}
