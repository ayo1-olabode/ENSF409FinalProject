/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.2
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

/*
 * Exception class that handles invalid hampers for reasons such as
 * invalid client info, doesn't meet macro goals, ran out of supplies etc...
 */
public class HamperCreationException extends Exception {
	private static final long serialVersionUID = 1L;
	public HamperCreationException() {
        super("Error creating hamper");
    }
}