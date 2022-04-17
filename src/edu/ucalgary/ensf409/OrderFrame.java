/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.8
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();	
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hamper Completed");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(20, 10, 281, 30);
		contentPane.add(lblNewLabel);

		// Creates/waits for button press to go back to home page
		JButton btnNewButton = new JButton("Restart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(284, 10, 94, 30);
		contentPane.add(btnNewButton);
		
		JTextPane txtpnCheckFolderCalled = new JTextPane();
		txtpnCheckFolderCalled.setEditable(false);
		txtpnCheckFolderCalled.setText("Check Folder Called OrderForm for detailed report of your hamper!!");
		txtpnCheckFolderCalled.setBounds(20, 51, 256, 168);
		contentPane.add(txtpnCheckFolderCalled);

		//window listener to check if user wants to close program
		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			    int clicked = JOptionPane.showConfirmDialog(null, 
			        "Would you like to exit the program?", "Exit Program",
			        JOptionPane.YES_NO_OPTION);

			    if (clicked == JOptionPane.YES_OPTION) {
			      dispose();
			    }
			   else {
		            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        }
			  }
			});
	}
}
