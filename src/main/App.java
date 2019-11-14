/**
 * @author Rafay Shaikh
 * @author Henry Lee Barton III
 * 
 * @since 10/30/2019
 */

package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class App {

	/// ///////////////////
	// PRIVATE VARIABLES
	/// ///////////////////
	
	private JFrame frame;
	private String mapPath = "./src/assets/map.png";
	private ArrayList<Building> buildings = new ArrayList<Building>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Start GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		createBuildings();
		initialize();
	}
	
	/// ///////////////////
	// PRIVATE METHODS
	/// ///////////////////
	
	/**
	 * Create buildings to show on the map.
	 */
	private void createBuildings() {
		// Create buildings from data
		try {
			// Open data file
			File mapDataFile = new File("./src/assets/data.txt");
			Scanner mapData = new Scanner(mapDataFile);
			// Parse data
			int index = 0;
			while (mapData.hasNext()) {
				this.buildings.add(new Building(
						mapData.nextLine(), // Name
						mapData.nextLine(), // Description
						mapData.nextLine(),	// Number of rooms
						"./src/assets/" + (index + 1) + ".png"
				));
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create string array of building names.
	 */
	private String[] getBuildingOptions() {
		String[] buildingNames = new String[this.buildings.size()];
		for (int i = 0; i < buildingNames.length; i++) {
			buildingNames[i] = this.buildings.get(i).getName();
		}
		return buildingNames;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Create JFrame
		frame = new JFrame("Texas A&M University Corpus Christi Map");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1500, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Combo box label
		JLabel lblComboBox = new JLabel("Select a building:");
		lblComboBox.setBounds(1090, 10, 394, 14);
		frame.getContentPane().add(lblComboBox);
		
		// Combo box
		JComboBox comboBox = new JComboBox(this.getBuildingOptions());
		comboBox.setBounds(1090, 30, 394, 30);
		frame.getContentPane().add(comboBox);
		
		// Overlay icon on map
		JLabel buildingOverlay = new JLabel();
		buildingOverlay.setBounds(0, 0, 1080, 720);
		frame.getContentPane().add(buildingOverlay);
		
		// Display TAMUCC Map
		JLabel map = new JLabel();
		map.setBounds(0, 0, 1080, 720);
		map.setIcon(new ImageIcon(mapPath));
		frame.getContentPane().add(map);
		
		JLabel lblDescription = new JLabel("Building Description:");
		lblDescription.setBounds(1090, 71, 394, 14);
		frame.getContentPane().add(lblDescription);
		
		JLabel buildingDescrption = new JLabel("");
		buildingDescrption.setVerticalAlignment(SwingConstants.TOP);
		buildingDescrption.setBounds(1090, 96, 394, 584);
		frame.getContentPane().add(buildingDescrption);
		
		// Create combo box listener
		comboBox.addActionListener(new ActionListener() {
			// On item selected
			@Override
			public void actionPerformed(ActionEvent ae) {
				// Get selected building
				Building building = buildings.get(comboBox.getSelectedIndex());
				buildingOverlay.setIcon(new ImageIcon(building.getImagePath()));
				buildingDescrption.setText(building.toString());
			}
		});
	}
}
