/**
 * @authors: Carter Boucher, Morgan Chen, Ayomiposi Olabode, Bryant Zhang
 * @version: 2.4
 * @since: 1.0
 */

package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.util.ArrayList;
import static edu.ucalgary.ensf409.errScreen.getErrorScreen;

//frame that handles the Client selection process
public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int numOfHamper;
	private String username;
	private int count = 0;
	private JSpinner adultMaleValue;
	private JSpinner adultFemaleValue;
	private JSpinner childUnder8Value;
	private JSpinner childOver8Value;
	private InventoryDB myJDBC;
	private ClientValues clientConsts;
	private ArrayList<Client> clientlist = new ArrayList<Client>();

	//initialize instance of database
	public MainPage(int number, String name) {
		this.username = name;
		InventoryDB myJDBC = new InventoryDB("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        myJDBC.initializeConnection();

		//client nutritional value saved from database
        clientConsts = myJDBC.selectFoodValues();
		numOfHamper = number;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Food Bank System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(44, 0, 273, 47);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter people in your hamper:");
		lblNewLabel_1.setBounds(44, 33, 173, 35);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("AdultMale:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_2.setBounds(44, 98, 144, 35);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("AdultFemale:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_2_1.setBounds(44, 144, 164, 35);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("ChildUnder8:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_2_2.setBounds(44, 236, 164, 35);
		panel_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("ChildOver8:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_2_3.setBounds(44, 190, 144, 35);
		panel_1.add(lblNewLabel_2_3);

		JList list = new JList();
		list.setBounds(190, 131, 27, -17);
		panel_1.add(list);

		//spinner objects : we are using spinner to set Client information, such as amount of Adult male for example.
		adultMaleValue = new JSpinner();
		adultMaleValue.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		adultMaleValue.setBounds(198, 98, 39, 30);
		((JSpinner.DefaultEditor)adultMaleValue.getEditor()).getTextField().setEditable(false);
		panel_1.add(adultMaleValue);

		adultFemaleValue = new JSpinner();
		adultFemaleValue.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		adultFemaleValue.setBounds(198, 149, 39, 30);
		((JSpinner.DefaultEditor)adultFemaleValue.getEditor()).getTextField().setEditable(false);
		panel_1.add(adultFemaleValue);

		childUnder8Value = new JSpinner();
		childUnder8Value.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		childUnder8Value.setBounds(198, 190, 39, 30);
		((JSpinner.DefaultEditor)childUnder8Value.getEditor()).getTextField().setEditable(false);
		panel_1.add(childUnder8Value);


		childOver8Value = new JSpinner();
		childOver8Value.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		childOver8Value.setBounds(198, 236, 39, 30);
		((JSpinner.DefaultEditor)childOver8Value.getEditor()).getTextField().setEditable(false);
		panel_1.add(childOver8Value);

		//submit button, when pressed calculates hamper and writes to file if successful
		//if failed, triggers an exception and pushes user to an error screen

		JButton btnNewButton = new JButton("Submit Hamper");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				//error checking for invalid client information like an order with no people in it
				try {
					clientlist.add(new Client((Integer) adultMaleValue.getValue(), (Integer)adultFemaleValue.getValue()
							, (Integer)childOver8Value.getValue(), (Integer)childUnder8Value.getValue(), myJDBC.selectFoodValues()));
				} catch (HamperCreationException ex) {
					getErrorScreen();
					dispose();
					return;
				}

				count++;
				//only runs when the number of press equals the number of hampers
				if (count == getNumofHamper())  {
					//checks empty database if missed in calcFood function
					if (myJDBC.selectAllNames().isEmpty()) {
						getErrorScreen();
						dispose();
					}

					//main to make hamper
					else {
					mainDriver main = new mainDriver(clientlist, myJDBC, myJDBC.selectAllNames(), getUsername());
					try {
						main.run();
						OrderFrame order = new OrderFrame();
						order.setVisible(true);
						order.setResizable(false);
						dispose();
						count = 0;
					} catch (HamperCreationException e1) {
						getErrorScreen();
						dispose();
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}

				}
				resetSpinners();
			}
		});

		btnNewButton.setBounds(44, 282, 126, 23);
		panel_1.add(btnNewButton);

		//button to go back in case user makes a mistake and needs to correct it
		JButton btnGoBack = new JButton("Go Back");

		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller = new Controller();
				controller.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(44, 305, 126, 23);
		panel_1.add(btnGoBack);

		//listen for when user tries to close program in which it asks for confirmation
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
	
	public int getNumofHamper() {
		return numOfHamper;
	}

	public void setNumofHamper(int number) {
		numOfHamper = number;
	}

	//resets spinner values
	public void resetSpinners() {
		//set spinner value to 0
		adultMaleValue.setValue(0);
		adultFemaleValue.setValue(0);
		childUnder8Value.setValue(0);
		childOver8Value.setValue(0);
	}

	//get nutritional values for client  from database ---> works as a update function
	public ClientValues getClientNutValues() {
        return myJDBC.selectFoodValues();
	}

	//get food arraylist from database ---> works as a update function
	public ArrayList<Food> getFoodArray() {
        ArrayList<Food> client = myJDBC.selectAllNames();
        return client;
	}

	/**
	 * @return the clientConsts
	 */
	public ClientValues getClientConsts() {
		return clientConsts;
	}

	//return username
	public String getUsername() {
		return username;
	}

}
