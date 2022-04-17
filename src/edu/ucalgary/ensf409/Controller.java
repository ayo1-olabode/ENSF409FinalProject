/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 1.7
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;




public class Controller extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Integer SpinnerValue;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller frame = new Controller();
					frame.setResizable(false);
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
	public Controller() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Food Bank System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel.setBounds(62, 0, 237, 33);
		contentPane.add(lblNewLabel);
		
		JSpinner mainSpinner = new JSpinner();
		mainSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		mainSpinner.setBounds(128, 71, 77, 33);
		contentPane.add(mainSpinner);

		
		JLabel lblNewLabel_1 = new JLabel("Number of Hampers");
		lblNewLabel_1.setBounds(10, 71, 116, 33);
		contentPane.add(lblNewLabel_1);
		
		//set spinner value to mainSpinner value
		
		JButton btnNewButton = new JButton("Submit");
		
		//detects if button has been pressed, where if pressed, it moves to next screen
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mainPage = new MainPage(Integer.parseInt(mainSpinner.getValue().toString()),textField.getText());
				mainPage.setResizable(false);
				setSpinnerValue(Integer.parseInt(mainSpinner.getValue().toString()));
				mainPage.setNumofHamper(SpinnerValue);
				mainPage.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(128, 115, 77, 23);
		contentPane.add(btnNewButton);
		
		//create text field to hold name on the order form
		textField = new JTextField();
		textField.setBounds(128, 44, 140, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter name: ");
		lblNewLabel_2.setBounds(10, 44, 77, 23);
		contentPane.add(lblNewLabel_2);

		//if closing program, ask user if they are sure they want to close it
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

	public int getSpinnerValue() {
		return SpinnerValue;
	}

	public void setSpinnerValue(Integer spinnerValue) {
		SpinnerValue = spinnerValue;
	}
}
