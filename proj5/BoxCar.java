/**
 * This class represents a BoxCar that can contain either people or cargo.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Dec 4, 2013
 * @project CMSC 202 - Fall 2013 - Project # 5
 * @section 06
 */
package proj5;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxCar <T extends Comparable <T>> {	

	private int maxNum, things = 0;	
	private ArrayList<T> items;
	private String type;

	/**
	 * Constructor for the Box Car. 
	 * @param type
	 * @param max
	 */
	public BoxCar(String type, int max) {
		
		maxNum = max;
		this.type = type;
		items =   new ArrayList<T>();		

	}
	
	/**
	 * This method takes in an Id as an argument and checks if it is already present in the box car.
	 * @param id
	 * @return true or false
	 */
	public boolean isPresent ( String id) {

		for (int i = 0; i < items.size(); i++) {

			if (type.equalsIgnoreCase("person")) {

				String holder = ((Person) items.get(i)).getId();

				if (id.equalsIgnoreCase(holder)) {
					return true;
				}
			}
			
			else {
				String holder = ((Cargo) items.get(i)).getId();
				
				if (id.equalsIgnoreCase(holder)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method check if box is full or not.
	 * @return true or false
	 */
	public boolean isFull () {
		
		if (items.size() < maxNum) {
			
			return false;
		}
		else
			if (items.size() == maxNum) {
				
				return true;
			}
		return false;
	}
	
	/**
	 * This method returns the type of object stored in the box car.
	 * @return type
	 */
	public String getType () {

		return type;
	}
	
	/**
	 *This method loads/adds items (person or cargo) to the box.
	 * @param item
	 */
	public void load (T item) {

		if (items.size() < maxNum )
			items.add(item);
		    things++;
	}
	
	/**
	 *This method returns the ArrayList containing all the items inside it. 
	 * @return items
	 */
	public ArrayList<T> getItems () {

		return new ArrayList<T>(items);
	}
	
	/**
	 * This methods sorts elements of the box car in ascending order bases on their Ids
	 */
	public void sort () {

		if (type.equalsIgnoreCase("person")) {

			Person [] people = new Person [items.size()];

			for (int i = 0; i < items.size(); i++) {				

				people[i] = (Person) items.get(i);	
				
			}

			Arrays.sort(people);

			for (int i = 0; i < items.size(); i++) {

				items.set(i, (T) people[i]);	
			}

		}	

		else {

			Cargo[] luggage = new Cargo [items.size()]; 

			for (int i = 0; i < items.size(); i++) {

				luggage [i] = (Cargo) items.get(i);				
			}		

			Arrays.sort(luggage);

			for (int i = 0; i < items.size(); i++) {

				items.set(i, (T) luggage[i]);				
			}
		}

	}	
	
	/**
	 * This method removed an item from the box car based on the id given as parameter.  
	 * @param id
	 */
	public void unload(String id) {

		String holder;
		for (int i = 0; i < items.size(); i++) {	


			if (type.equalsIgnoreCase("person")) {

				holder = ((Person) items.get(i)).getId();

				if (id.equalsIgnoreCase(holder) ) {

					items.remove(i);
				}
			} 

			else {

				holder = ((Cargo) items.get(i)).getId();

				if (id.equalsIgnoreCase(holder)) {

					items.remove(i);
				}
			}
		}
	}

	/**
	 * This method returns information about all the items inside a box car in a string.
	 */
	public String toString () {
		String str = "";

		for (int i = 0; i < items.size(); i++) {

			str += "\t" + items.get(i).toString() + "\n";
		}	
		return str;
	}

	/*
	 * Main method for unit testing.
	 */
	public static void main (String [] args) {

		Person p = new Person ("4433-44f", "Aaiz Ahmed", 20);
		Person p1 = new Person ("3533-44f", "jemmy fallon", 5);
		Cargo c = new Cargo ("44Df", 600, 20, 12, 14);
		Cargo c1 = new Cargo ("55Df", 1200, 20, 12, 14);

		BoxCar<Person> car1 = new BoxCar<Person>("Person",3);
		BoxCar<Cargo> car2 = new BoxCar<Cargo>("Cargo",2);

		car1.load(new Person ("5532-66g","jut", 33));
		car1.load(p);  
		car1.load(p1);
		car2.load(c1);   
		car2.load(c);
       
	//	car1.sort();
		System.out.println(car1.toString() + "yes " + car1.getItems().size() + "\n" + car2.toString());

		car1.sort(); 	
		System.out.println(car1.toString() );
		car2.unload("44Df");
    	car1.unload("4433-44f");  
    	car1.sort();
		car2.sort();
	//	System.out.println(car1.toString() + "yes " + car1.getItems().size() + "\n" + car2.toString());
		System.out.println(car1.isPresent("5532-66g"));
	}


}
