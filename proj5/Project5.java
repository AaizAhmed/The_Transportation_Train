/**
 * This class instantiates a train object and reads input from a file and writes it on another file.
 * @author Aaiz Ahmed <aaiza2@umbc.edu>
 * @version Dec 6, 2013
 * @project CMSC 202 - Fall 2013 - Project # 5
 * @section 06
 */
package proj5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Project5 {	

	public static void main (String [] args) {		

		ArrayList<String> input = new ArrayList<String>();

		//Reading file.
		try {		
			FileInputStream stream = new FileInputStream(args[4]);
			Scanner scan = new Scanner(stream);	

			int i = 0;
			while (scan.hasNext()) {

				input.add(i, scan.nextLine());					
				i++;
			} 		
		}

		catch(IOException e) {
			System.out.println ("Input problem, File not found");
		} 

		//------------------------------------------------------------
		// Instantiating a Train object.
		//------------------------------------------------------------		
		int min = Integer.parseInt(args[1]);
		int max = Integer.parseInt(args[2]);
		int total = Integer.parseInt(args[3]);

		Train train = new Train(args[0], min, max, total);	

		//Reading commands from a file and writing them to another file. 

		try {
			
			PrintWriter out = new PrintWriter(new File(args[5]));
			
			int i = 0;
			
			//Invoking different methods of a train based on the input from a file
			while (i < input.size()) {

				//Printing the status of the train.
				if(input.get(i).equalsIgnoreCase("print")) {

					out.print(input.get(i) + "\n" + train.toString() + "\n");
					System.out.println(train.toString());
				}

				//Adding box cars to the train. Train should not be moving.
				if (input.get(i).equalsIgnoreCase("addcar")) {

					if (train.getSpeed() == 0) {

						train.addCar(input.get(i+1), Integer.parseInt(input.get(i+2)));
						out.print (input.get(i) + " " + input.get(i+1) + " " + input.get(i+2) +"\n" );	
					}
					else					
						out.print ("ERROR: The train has not arriaved in " + train.getDestination() + " yet.");					
				}

				//Arriving
				if (input.get(i).equalsIgnoreCase("arrive")) {

					train.arrive();
					out.print(input.get(i) + "\n");
				}  

				//Departing
				if (input.get(i).equalsIgnoreCase("depart")) {

					train.depart(input.get(i+1));					
					out.print(input.get(i) + " " + input.get(i+1)+ "\n");
				}   


				//-----------------------------------------------------------
				//Speeding up and checking conditions.
				//-----------------------------------------------------------
				if (input.get(i).equalsIgnoreCase("speedup")) {

					int speed = Integer.parseInt(input.get(i+1));	
					boolean moving = true;

					if (train.getSpeed() == 0) {						

						out.print(input.get(i) + " "  + speed + "\n");
						out.print("   ERROR: The Train has not departed yet.\n");
						moving = false;
					}					
					try {

						if (moving == true && train.getSpeed() + speed > max)	{								
							throw new MaxSpeedException ("   ERROR: Speed can not be increased, it would exceed it's maximum speed.");
						}						

						else {								

							if (moving == true ) {  
								train.speedUp(speed);
								out.print(input.get(i) + " "  + speed + "\n");
							}			
						}

					}
					catch (MaxSpeedException e) {
						out.print(input.get(i) + " " + input.get(i+1) + "\n" + e.getMessage() + "\n");
					}

				}				

				//----------------------------------------------------
				// Slowing Down the train
				//----------------------------------------------------
				if (input.get(i).equalsIgnoreCase("slowdown")) {

					int speed = Integer.parseInt(input.get(i+1));	
					boolean moving = true;

					if (train.getSpeed() == 0) {					

						out.print(input.get(i) + " "  + speed + "\n");
						out.print("   ERROR: The Train has not departed yet.\n");	
						moving = false;
					}					
					try {

						if ( moving == true && train.getSpeed() - speed < min )
							throw new MinSpeedException ("   ERROR: Speed can not be reduced, it would reduce below it's minimum speed.");

						else {

							if (moving == true ) {  
								train.slowDown(speed);
								out.print(input.get(i) + " "  + speed + "\n");
							}	
						}

					}
					catch (MinSpeedException e) {
						out.print(input.get(i) + " " + input.get(i+1) + "\n" + e.getMessage() + "\n");
					}

				}	

				//--------------------------------------------------------------
				//Removing a box car out from train. 
				//--------------------------------------------------------------
				if (input.get(i).equalsIgnoreCase("Removecar")) {

					int boxId = Integer.parseInt(input.get(i+1));
					boolean moving = false;
					
					if (train.getSpeed() > 0) {

						out.print(input.get(i) + " "  + boxId + "\n");
						out.print("     ERROR: The train has not arrived in " + train.getDestination() + " yet.\n");
						moving = true;
					}
					if ( moving == false && boxId < train.getTrain().size()) {
						
						if (train.getTrain().get(boxId).getItems().isEmpty() == false) {
							
							out.print(input.get(i) + " "  + boxId + "\n");
							out.print("    ERROR: Boxcar " + boxId + " is not empty.\n");
						}						
					  else
						  if (train.getTrain().get(boxId).getItems().isEmpty() == true) {
							
							train.removeCar(boxId);
							out.print(input.get(i) + " "  + boxId + "\n");
						}
					}
				}
				
				//-----------------------------------------------
				//Loading the box car with appropriate items.
				//-----------------------------------------------
				if (input.get(i).equalsIgnoreCase("load")) {

					String type = input.get(i+1);
					int boxId = Integer.parseInt(input.get(i+2));
					String id = input.get(i+3);
					boolean thrown = false ;
					
					try{
						
						//checking that box car holds the same type of item as being loaded.
						if (!train.getTrain().get(boxId).getType().equalsIgnoreCase(type)) {

							thrown = true;	
							throw new WrongTypeException ("   ERROR: Wrong type of item being loaded. This box car does not contain " + type);					
						}						
					}
					//Throwing exception if type does by match.
					catch (WrongTypeException e) {
						out.print(input.get(i) + " " + input.get(i+2) + " contains " + train.getTrain().get(boxId).getType() + "\n");				 
						out.print(e.getMessage() +"\n");
					}
					
					try {
						
						//Checking if box is already full or not. If full throwing the exception.
						if (train.getTrain().get(boxId).isFull() == true) {

							thrown = true;	
							throw new BoxFullException ("   ERROR: Box car " + boxId + " is already full. Not enough room for the given item.");		
						}
					}
					catch (BoxFullException e) {

						out.print(input.get(i) + " " + input.get(i+2) + " " + input.get(i+3) + " ");
						out.print(input.get(i+4) + " " + input.get(i+5) + "\n" ); 
						out.print(e.getMessage() + "\n");
					}
					
					//Checking the type and loading if exceptions are not thrown
					if ( thrown == false && type.equalsIgnoreCase("cargo")) {

						int weight = Integer.parseInt(input.get(i+4));
						int height = Integer.parseInt(input.get(i+5));
						int width = Integer.parseInt(input.get(i+6));
						int length = Integer.parseInt(input.get(i+7));

						//Checking if item is already present in the box.
						if (train.getTrain().get(boxId).isPresent(id) == true) {

							out.print(input.get(i) + " " + input.get(i+2) + " " );
							out.print(input.get(i+4) + " " + input.get(i+5) + " " + input.get(i+6) + " ");
							out.print(input.get(i+7) + "\n");
							out.print("   ERROR: Invalid item, item with id \"" + id + "\" already exists.\n");
						}

						else {

							if (train.getTrain().get(boxId).isPresent(id) == false) {

								train.load(boxId, id, weight, height, width, length);
								out.print(input.get(i) + " " + input.get(i+2) + " " + input.get(i+3) + " ");
								out.print(input.get(i+4) + " " + input.get(i+5) + " " + input.get(i+6) + " ");
								out.print(input.get(i+7) + "\n");
							}
						}
					} 
					
					//Loading a person if exceptions are not thrown
					if ( thrown == false && type.equalsIgnoreCase("person")) {

						String name = input.get(i+4);
						int age = Integer.parseInt(input.get(i+5));
						
						if (train.getTrain().get(boxId).isPresent(id) == true) {

							out.print(input.get(i) + " " + input.get(i+2) + " " + input.get(i+3) + " ");
							out.print(input.get(i+4) + " " + input.get(i+5) + "\n" ); 
							out.print("   ERROR: Invalid item, item with id \"" + id + "\" already exists.\n");
						}
						else {

							if (train.getTrain().get(boxId).isPresent(id) == false) {
								train.load(boxId, id, name, age);

								out.print(input.get(i) + " " + input.get(i+2) + " " + input.get(i+3) + " ");
								out.print(input.get(i+4) + " " + input.get(i+5) + "\n" ); 
							}
						}							

					}

				}
				
				//-----------------------------------------------------------------------
				// Unload a box cars.
				//-----------------------------------------------------------------------
				if (input.get(i).equalsIgnoreCase("unload")) {

					int boxId = Integer.parseInt(input.get(i+1));
					String id = input.get(i+2);

					//Checking that train is not moving.
					if (train.getSpeed() != 0) {

						out.print(input.get(i) + " " + input.get(i+1) + " " + input.get(i+2) + "\n");
						out.print("     ERROR: The train has not arrived in " + train.getDestination() + " yet. ");
						out.print("Can not unload an item while train is moving.\n");
					}
					else
						try { 
							
							//Checking if box car contains that item
							if (train.getTrain().get(boxId).isPresent(id) == true ) {

								train.unload(boxId, id);
								out.print(input.get(i) + " " + input.get(i+1) + " " + input.get(i+2) + "\n");
							}
							else
							throw new NoSuchItemException ("   ERROR: Invalid item id. Id: " + id + " is not present in the Box Car " + boxId);
						}
					catch (NoSuchItemException e) {

						out.print(input.get(i) + " " + input.get(i+1) + " " + input.get(i+2) + "\n");
						out.print(e.getMessage() + "\n");
					}
				}  

				//Quitting				
				if (input.get(i).equalsIgnoreCase("quit")) {

					out.print(input.get(i) + "\nQuitting.....");
					break;
				}
				
				//Incrementing the variable of while loop.
				i++;
			}
			
			//Closing the file
			out.close();
		}

		catch (FileNotFoundException e) {
			
			System.out.println ("File does not exist."); 
			
		}

	}  

}
