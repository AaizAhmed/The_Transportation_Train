/**
 * This class describes a person and contain data like name, id and age.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Dec 4, 2013
 * @project CMSC 202 - Fall 2013 - Project # 5
 * @section 06
 */
package proj5;

public class Person implements Comparable<Person> {

	private String name, id;
	private int age;

	/**
	 * Constructor for the Person class. It takes 3 parameters Id, name and age.
	 * @param id
	 * @param name
	 * @param age
	 */
	public Person (String id, String name, int age) {

		this.id = id;
		this. name = name;		
		this.age = age;		
	}
	
	/**
	 * Comparing to people by their id
	 * @return result
	 */
	public int compareTo(Person per) {
		
		int result = id.compareTo(per.getId());
		return result;
	}
	
	/**
	 * To assess the name of the person
	 * @return name
	 */
	public String getName () {

		return name;
	}
	
	/**
	 * This method return id of the person	 * 
	 * @return id
	 */
	public String getId () {

		return id;
	}
	
	/**
	 * The methods returns age
	 * @return age
	 */
	public int getAge() {

		return age;
	}
	
/**
 * This method returns a string including id, name and the age of the person.
 */
	public String toString () {

		return id + ":  Name: " + name + "\t\tAge:  " + age;
	}

	//For testing
	public static void main (String [] args) {
		
		Person p = new Person ("4433-44f", "Aaiz Ahmed", 20);
		Person p1 = new Person ("3533-44f", "jemmy fallon", 5);
		
		System.out.println (p);
		System.out.println (p1);
	}

	
}
