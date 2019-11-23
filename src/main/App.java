package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

/**
 * Class App is the main class that controls
 * the application.
 * 
 * @author Rafay Shaikh
 * @author Henry Lee Barton III
 * 
 * @since 10/30/2019
 */
public class App {

	/// ///////////////////
	// PRIVATE VARIABLES
	/// ///////////////////
	
	private JFrame frame;
	private String mapPath = "./src/assets/map.png";
	private ArrayList<Building> buildings = new ArrayList<Building>(); 		//holds all buildings objects

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Start GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					//timer to delay the the map until welcome screen completes the animation
					new Timer().schedule(new TimerTask() {
						public void run() {
							window.frame.setVisible(true);
						}
					}, 4500);
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
		createBuildings();	// this will generate buildings objects with the data from text file.
		welcomeFrame();		// this will generate a welcome scenario before the actual program begins.
		initialize();		// this will initialize the map program
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
						"./src/assets/" + (index + 1) + ".png" //Map pictures
				));
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create string array of building names.
	 * @return buildingNames that will fill-up the ComboBox
	 */
	private String[] getBuildingOptions() {
		String[] buildingNames = new String[this.buildings.size()];
		for (int i = 0; i < buildingNames.length; i++) {
			buildingNames[i] = this.buildings.get(i).getName();
		}
		return buildingNames;
	}

	/**
	 * Welcome Frame is a implementation of proxy design pattern
	 */
	void welcomeFrame()  {
		JFrame welcomeFrame = new JFrame();	//new frame
		welcomeFrame.setUndecorated(true); // Remove title bar
		ImageIcon imgThisImg = new ImageIcon("./src/assets/welcome.gif");//to display picture
		JLabel welcomeData = new JLabel();
		welcomeData.setIcon(imgThisImg);	//label filled with gif image
		welcomeFrame.add(welcomeData);
		welcomeFrame.setSize(482, 311);
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setVisible(true);
		/*
		* Timer will wait for animation to display for specific amount of time
		*then the map program will begin execution
		*/
		new Timer().schedule(new TimerTask() {
			public void run() {
				// closes the welcome display frame when the actual program begins
				welcomeFrame.dispose();
			}
		}, 4500);
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

		//Written 'Building Description' to display
		JLabel lblDescription = new JLabel("Building Description:");
		lblDescription.setBounds(1090, 71, 394, 14);
		frame.getContentPane().add(lblDescription);
		
		JLabel buildingDescrption = new JLabel("");
		buildingDescrption.setVerticalAlignment(SwingConstants.TOP);
		buildingDescrption.setBounds(1090, 96, 394, 584);
		frame.getContentPane().add(buildingDescrption);

		//Written prompt to display
		JLabel lblUrlClick = new JLabel("Click On The Logo For More Information");
		lblUrlClick.setBounds(1190, 420, 394, 14);
		frame.getContentPane().add(lblUrlClick);

		//TAMUCC logo where user will click to open university's website
		ImageIcon logoPic = new ImageIcon("./src/assets/logo.jpg");//to display url logo
		JLabel lblLogo = new JLabel();
        lblLogo.setIcon(logoPic);
		lblLogo.setBounds(1250, 450, logoPic.getIconWidth(), logoPic.getIconHeight());
		//Mouselistner activates when user click on the logo image.
		lblLogo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent m)
			{
				try {
					Desktop desktop = java.awt.Desktop.getDesktop(); //this will open the browser
					URI oURL = new URI("http://www.tamucc.edu");// with this URL
					desktop.browse(oURL);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(lblLogo);
		
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
