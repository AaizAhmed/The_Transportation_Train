/**
 * This class represents cargo and contain data like id and dimensions.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Dec 4, 2013
 * @project CMSC 202 - Fall 2013 - Project # 5
 * @section 06
 */
package proj5;

public class Cargo implements Comparable <Cargo> {

	private String id;
	private int weight, h, w, l;

	/**
	 * Constructor for this class. It takes id, weight and dimensions as parameter.
	 * @param id
	 * @param weight
	 * @param h
	 * @param w
	 * @param l
	 */
	public Cargo (String id, int weight, int h, int w, int l) {

		this.id = id;
		this.weight = weight;
		this.h = h;
		this.w = w;
		this.l = l;
	}

	/**
	 * Comparable method compares to cargo objects based on their id
	 * @return
	 */
	public int compareTo(Cargo car) {
		
		int result = id.compareTo(car.getId());
		return result;
	}
	
	/**
	 * This method returns id of the cargo object
	 * @return
	 */
	public String getId() {

		return id;
	}	

	/**
	 * This method returns a string representation of a cargo object. 
	 * It includes id, name and the dimensions.
	 */
	public String toString () {

		return id + ":  Weight: " + weight + "\tDimensions: " + h + " X " + w + " X " + l;
	}
	
/*
 * For unit testing
 */
	public static void main (String [] args) {

		Cargo c = new Cargo ("44Df", 600, 20, 12, 14);
		Cargo c1 = new Cargo ("55Df", 1200, 20, 12, 14);

		System.out.println (c);
		System.out.println (c1);
	}


}
