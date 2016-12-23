/**
 * This class represents a train and it's functions.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Dec 4, 2013
 * @project CMSC 202 - Fall 2013 - Project # 5
 * @section 06
 */
package proj5;

import java.util.ArrayList;

public class Train {

	private int currentSpeed, boxes;
	private final int MAX_BOX , MIN_S, MAX_S;
	private String currentPosition, currentStation, destination;
	private ArrayList<BoxCar> train ;

	/**
	 * Constructor of the train class.
	 * @param station
	 * @param min
	 * @param max
	 * @param total
	 */
	public Train (String station, int min, int max ,int total) {

		MAX_S = max; 
		MIN_S = min;
		MAX_BOX = total;
		train = new ArrayList<BoxCar>();
		currentStation = station;
		currentPosition = "Stopped in " + currentStation;
		destination = "New York";
	}
	/**
	 * This method returns the ArrayList of Box cars called train.
	 * @return train
	 */
	public ArrayList<BoxCar> getTrain () {
		
		return  new ArrayList<BoxCar>(train);
	}
	
	/**
	 *This method returns current speed of the train. 
	 * @return currentSpeed
	 */
	public int getSpeed () {
		
		return currentSpeed;
	}
	
	/**
	 * This method returns the destination of the train.
	 * @return destination
	 */
	public String getDestination () {
		
		return destination;
	}
	
	/**
	 * This method adds Box cars to the train.
	 * @param type
	 * @param elements
	 */
	public void addCar (String type, int elements) {
		
		if (currentSpeed == 0)
			
		if (type.equalsIgnoreCase("Person") ) {
			
			train.add(new BoxCar<Person>("Person", elements));	
			boxes++;
		}
		else {
			
			train.add(new BoxCar<Cargo>("Cargo", elements));
			boxes++;
		}		
	}	

	/**
	 * This methods loads/adds Cargo in a box car of the train. 
	 * @param id
	 * @param cargoId
	 * @param weight
	 * @param height
	 * @param width
	 * @param length
	 */
	public void load (int id, String cargoId, int weight, int height, int width,int length ) {
		
		if (currentSpeed == 0)
			
		//To check the box car user is loading is the box car to contain the same type of object.
		if(train.get(id).getType().equalsIgnoreCase("Cargo")) {

		Cargo c = new Cargo (cargoId, weight, height, width, length); 
		
		train.get(id).load(c); 
		}
	}

	/**
	 * This method adds/loads people in a box car of the train.
	 * @param id
	 * @param personId
	 * @param name
	 * @param age
	 */
	public void load (int id, String personId, String name, int age) {
		
		if (currentSpeed == 0)
			
		if(train.get(id).getType().equalsIgnoreCase("Person")) {

			Person p = new Person (personId, name, age);

			train.get(id).load(p);
		}
		
	}
	
	/**
	 * This method removes a box car from the train.
	 * @param id
	 */
	public void removeCar (int id) {
		
		if (id < train.size())
		train.remove(id);
		boxes--;
	}
	
	/**
	 * This method removes an item from a box car of the train.
	 * @param id
	 * @param itemId
	 */
	public void unload (int id, String itemId) {	
		
		if (currentSpeed == 0)
		train.get(id).unload(itemId);		
	}
	
	/**
	 * Invoking this method will start the train to move towards it's destination at it's minimum speed.
	 * @param name
	 */
	public void depart (String name) {
		
		destination = name;
		currentPosition = "Travaling from " + currentStation + " to " + destination;
		currentSpeed = MIN_S;
	}

	/**
	 * This method tells that train has reached it's destination. It sets speed to zero. 
	 */
	public void arrive () {

		currentPosition = "Stopped in " + destination;
		currentSpeed = 0;
	}
	
	/**
	 * This method increases the speed of the train by the amount passed as parameter. As
	 * long as adding that much speed will not exceed train's maximum speed limit. 
	 * @param s
	 */
	public void speedUp (int s) {

		if (currentSpeed + s <= MAX_S) 
			currentSpeed += s;
	}

	/**
	 * This method reduces the speed of the train by the amount passed as parameter. As
	 * long as decreasing that much speed will not reduce below train's minimum speed limit. 
	 * @param s
	 */
	public void slowDown (int s)  {

		if (currentSpeed - s >= MIN_S) {
			currentSpeed -= s;
		}		 
					
	}
	/**
	 * This method returns all the statistics of the train and the information of items inside the
	 * box cars in the form of string.
	 */
	public String toString () {
		
		String str = "";
		str = "Train Status\n------------\nCurrent Speed: " + currentSpeed + "\n";
		str += "Minimum Speed: " + MIN_S + "\nMaximum Speed: " + MAX_S + "\n";
		str += "Current Position:\t" + currentPosition + "\nCurrent Number of Boxcars: " + boxes;
		str += "\nMaximum Number of Boxcars: " + MAX_BOX + "\n";
		
		for (int i = 0; i < train.size(); i++) {
			
			str += "BoxCar: " + i + "\n-----------\n";
			str += "Contents:\n";
			str += train.get(i).toString();
		}
		return str;
	}

	/*
	 * Main method for unit testing.
	 */
	public static void main (String [] args) {

		Train t = new Train("Ciargo", 100, 10, 5);
		
		System.out.println (t);
		Person p = new Person ("4433-44f", "Aaiz Ahmed", 20);
		Person p1 = new Person ("3533-44f", "jemmy fallon", 5);
		Cargo c = new Cargo ("44Df", 600, 20, 12, 14);
		Cargo c1 = new Cargo ("55Df", 1200, 20, 12, 14);		
		
		t.addCar("Cargo", 4); 
		t.addCar("Person", 3);
		
		t.load(1, "Maryland82", "Elana", 40);
		t.getTrain().get(0).load(c);
		t.getTrain().get(0).load(c1);
		
	//	int speed = t.currentSpeed;
		t.getTrain().get(1).load(p);
		t.getTrain().get(1).load(p1);
		System.out.println (t.getTrain().get(1).toString());
		t.depart("Boston");
		t.arrive();
//		t.removeCar(0);
		t.getTrain().remove(1);
		System.out.println (t);
	}
}
