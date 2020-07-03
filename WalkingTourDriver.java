package assignment2b;

import java.util.Scanner;

public class WalkingTourDriver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		WalkingTour tour = new WalkingTour();
		String [] locations;
		int [][] graph;
		int nodes = 10,pick = -1;
		String place = "";
		
		locations = new String[nodes];
		graph = new int[nodes][nodes];
		tour.initializeSingleArray(locations, nodes);
		tour.initializeMultiArray(graph, nodes);
		tour.readFromFile(locations, graph);//enter file into arrays
	
		while(pick != 0){
			try {
					tour.menu();
					pick = input.nextInt();
				}
			catch(Exception e) {
					//pick = ;
					input.next();//get next input
					System.out.print("You Did Not Pick A Number\n");
					continue;
				}
			switch(pick){
				case 1:
					input.nextLine();
					tour.printLocations(locations, nodes);
					System.out.print("\nChoose One Of These Locations ");
					place = input.nextLine();	
					if(tour.searchLocation(locations, place) == true) {
						System.out.print(place + " Is A Location\n");
					}
					else {
						System.out.print(place + " Is Not A Location\n");
					}
					break;
				case 2:
					tour.insertEdge(graph, locations, nodes);
					System.out.print("Path Added\n");
					break;
				case 3:
					input.nextLine();
					tour.printLocations(locations, nodes);
					System.out.print("\nChoose One Of These Locations ");
					place = input.nextLine();
					if(tour.searchLocation(locations, place) == true) {
						tour.displayConnectingLocations(graph, locations, place, nodes);
						System.out.println();
					}
					else {
						System.out.print("No Location Was Found\n");
					}
					break;
				case 4:
					input.nextLine();
					tour.printLocations(locations, nodes);
					System.out.print("\nChoose One Of These Locations ");
					place = input.nextLine();
					if(tour.searchLocation(locations, place) == true) {
						tour.displayClosestLocation(graph, locations, place, nodes);;
						System.out.println();
					}
					else {
						System.out.print("No Location Was Found\n");
					}
					break;
				default:
					if(pick < 0 || pick > 4) {
						System.out.print("Only Choose 0-4\n");
					}
					break;		
			}//end of switch
		}//end of while
		System.out.print("\nEnd Of Walking Tour! Hope You Had Fun!\n");
	}//end of main
}//end of walking tour driver class
