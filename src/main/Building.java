package main;

/**
 * Class building represents the buildings
 * on the TAMUCC map: name, info, and image
 * path.
 * 
 * @author Rafay Shaikh
 * @author Henry Lee Barton III
 * 
 * @since 10/30/2019
 */
public class Building {

	/// ///////////////////
	// PRIVATE VARIABLES
	/// ///////////////////
	
	private String name; // Name of building
	private String desc; // Description of the building
	private String numRooms; // Number of rooms in the building
	private String path; // Path to the image of the building
	
	/// ///////////////////
	// CONSTRUCTOR
	/// ///////////////////
	
	/**
	 * Default constructor
	 */
	public Building() {
		this.name = "";
		this.desc = "";
		this.numRooms = "";
		this.path = "";
	}
	
	/**
	 * Constructor function set name, desc, rooms
	 * @param name
	 * @param desc
	 * @param numRooms
	 * @param path
	 */
	public Building(String name, String desc, String numRooms, String path) {
		this.name = name;
		this.desc = desc;
		this.numRooms = numRooms;
		this.path = path;
	}
	
	/// ///////////////////
	// GET METHODS
	/// ///////////////////
	
	/**
	 * Get building name
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get building description
	 * @return String
	 */
	public String getDesc() {
		return this.desc;
	}
	
	/**
	 * Get number of rooms
	 * @return String
	 */
	public String getNumRooms() {
		return this.numRooms;
	}
	
	/**
	 * Get image path of building
	 * @return
	 */
	public String getImagePath() {
		return this.path;
	}
	
	/// ///////////////////
	// SET METHODS
	/// ///////////////////
	
	/**
	 * Set name of building
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set description of building
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * Set number of rooms in building
	 * @param numRooms
	 */
	public void setNumRooms(String numRooms) {
		this.numRooms = numRooms;
	}
	
	/**
	 * Set the path of the image of the building
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/// ///////////////////
	// FUNCTIONS
	/// ///////////////////
	
	@Override
	public String toString() {
		return "<html>" + this.name + "<br>"
				+ "<br>"
				+ this.desc
				+ "<br><br>"
				+ "Total Rooms: " + this.numRooms
				+ "<br></html>";
	}
}
