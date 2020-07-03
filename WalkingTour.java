package assignment2b;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WalkingTour {
	
	public WalkingTour() {
		
	}
	public void initializeSingleArray(String[] array,int length) {//initialize single array
		for(int i = 0; i < length;i++) {
			array[i] = null;
		}
	}//end of initializeSingleArray
	public void initializeMultiArray(int[][] graph,int length) {//initialize multi array
		for(int i = 0; i < length;i++) {
			for(int j = 0; j < length;j++) {
				graph[i][j] = 0;
			}
		}
	}//end of initializeMultiArray
	public void readFromFile(String [] locations,int[][] graph) {//reads from file
		File file = new File("C:\\Users\\Danny\\Desktop\\DSA\\Dublin.txt");
		String location = "",sor = "",des = "",weg = "";
		int x = 0,y = 0,weight = 0;
		Scanner in;
		int nodeIndex = 0;
		try {
			in = new Scanner(file);
			while(in.hasNext()) {
				
				location = in.next();
				if(!location.equals("?")) {
					locations[nodeIndex] = location;
					nodeIndex++;
				}
				
				 sor = in.next();
				 x = (Integer.parseInt(sor));
				 
				 des = in.next();
				 y = (Integer.parseInt(des));
				 
				 weg = in.next();
				 weight = (Integer.parseInt(weg));
				 
				 graph[x][y] = weight;
				}	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}//end of read file method
	public boolean searchLocation(String [] array,String searchNode) {//checks if location is valid
		for(int i = 0;i < array.length;i++) {
			if(array[i].equalsIgnoreCase(searchNode)) {
				return true;//returns index position
			}
		}
		return false;
	}//end of search for location
	public int locationPosition(String [] array,String searchNode) {//returns position of location 
		for(int i = 0;i < array.length;i++) {
			if(array[i].equalsIgnoreCase(searchNode)) {
				return i;//returns index position
			}
		}
		return -1;
	}//end of location position
	public void insertEdge(int[][]graph,String[] locations,int nodes) {//allows edge to be inserted between locations
		Scanner input = new Scanner(System.in);
		Scanner in = new Scanner(System.in);
		int count = 0,row = -1,col = -1;
		String place = "";
		String word = "";
		while(count != 2){
			if(count == 0) {
				try {
					printLocations(locations, nodes);
					System.out.print("\nChoose One Of These Locations ");
					place = input.nextLine();
				}
				catch(Exception e) {
					input.next();//get next input
					continue;
				}
				if(searchLocation(locations,place) == true && count == 0) {
					row = locationPosition(locations, place);
					count++;
				}
				else {
					System.out.print("You Didn't Choose One Of These Locations\n");
				}
			}
			else {
				try {
					printLocations(locations, nodes);
					System.out.print("\nChoose Another One Of These Locations ");
					place = input.nextLine();
				}
				catch(Exception e) {
					input.next();//get next input
					continue;
				}
				if(searchLocation(locations,place) == true && count == 1) {
					col = locationPosition(locations, place);
					count++;
				}
				else {
					System.out.print("You Did Not Choose One Of The Locations>>>\n");
				}
			}
		}
		int weight = 0;
		if(graph[row][col] != 0) {
			System.out.print("The Location " + locations[row] + " And "+ locations[col]+ " Are Connected" + "\n");
		}
		else {
				System.out.print("Would You Like To Connect These Two Locations? Yes OR No");
				word = in.nextLine();
				word=word.toLowerCase(); 
				
				if(word.equalsIgnoreCase("yes")) {
					while(weight == 0){
						try {
								System.out.print("These Two Locations Are Not Connected \nPlease Add A Weight To Connect Them ");
								weight = input.nextInt();
								graph[row][col] = weight;
								graph[col][row] = weight;
							}
						catch(Exception e) {
								weight = 0;
								input.next();//get next input
								System.out.print("You Did Not Enter A Number\n");
								continue;
							}
					}//end of while
				}//end of inner if
				else {
					System.out.print("You Chose Not To Make A Connection\n");
				}//end of inner else
		}//end of else
	}//end of node to node method	
	public void menu() {//displays walking tour menu
		System.out.println("---------- MENU ----------");
		
		System.out.println("1. Check For A Location ");
		System.out.println("2. Search For And Insert An Edge (Site-Site)");
		System.out.println("3. Display All Locations Connected To A Location");
		System.out.println("4. Display Closest Location");
		System.out.println("\n--- Press 0 To Exit ---");
		System.out.print("\nOption: ");
	}//end of menu
	public void displayConnectingLocations(int[][] graph,String[]locations,String place,int length) {//displays all connecting locations
		int row = locationPosition(locations,place);
		System.out.print("The Locations Connected To "+ place + " Are ");
		for(int i = 0; i < length;i++) {
			if(graph[row][i] != 0) {
				System.out.print(locations[i] + " ");
			}
		}
	}//end of display connecting locations
	public void displayClosestLocation(int[][] graph,String[]locations,String place,int length) {//displays nearest location
		int row = 0,count = 0,i = 0,index = -1,holdWeight = 0;
		row = locationPosition(locations,place);
		for(i = 0; i < length;i++) {
			if(graph[row][i] != 0) {
				if(count == 0) {
					holdWeight = graph[row][i];
					index = i;
					count++;
				}
				else {
					if(holdWeight > graph[row][i]) {
						holdWeight = graph[row][i];
						index = i;
					}
				}
			}//end of outer if
		}//end of for
	System.out.print("The Closest Location To "+ place + " Is " + locations[index]);
	}//end of display closest location
	
	public void printLocations(String[] locations,int length) {//prints string array
		for(int i = 0; i < length;i++) {
			System.out.print(locations[i] + " ");
		}
	}//end of printLocations
}//end of walking tour class
