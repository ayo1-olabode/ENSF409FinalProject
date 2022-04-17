/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.9
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

public class errFrame extends JFrame {
	//intializing variable
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					errFrame frame = new errFrame();
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
	public errFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 257, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Food Bank System");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(21, 0, 182, 37);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Restart");
		btnNewButton.addActionListener(new ActionListener() {
			//waits for restart button press in which user returns back to home page
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(37, 137, 78, 29);
		contentPane.add(btnNewButton);

		//exits program when button clicked
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(114, 137, 78, 29);
		contentPane.add(btnNewButton_1);
		
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

		// creates/displays error text pane to user in cases where hamper is invalid
		JTextPane txtpnFailedToCreate = new JTextPane();
		txtpnFailedToCreate.setEditable(false);
		txtpnFailedToCreate.setText("Failed to create a hamper, could be caused by not enough items or invalid client information");
		txtpnFailedToCreate.setBounds(5, 32, 226, 103);
		contentPane.add(txtpnFailedToCreate);
	}
}
