/**
 * It is an Exception class, it will be thrown in case an object is added in an already full box car.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Dec 4, 2013
 * @project CMSC 202 - Fall 2013 - Project # 5
 * @section 06
 */
package proj5;

public class BoxFullException extends Exception{
	
	BoxFullException ( String message) {
		
		super(message);
	}

}
