import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * The interface that allows a user to use the travel system. Has all the functionalities as per the assignment.
 * @author R_K
 *
 */
public class TravelSystem {
	
	private Graph map= new Graph();
	private boolean quit=false;
	Scanner input= new Scanner(System.in);

	public TravelSystem()
	{
	}
	
	public void travel()
	{
		menu();
	}
	private void menu()
	{
		while(!quit)
		{ 
			System.out.println("Hello and welcome to the travel system, please select from an option below to get started!");
			System.out.println("Enter 1 to load a file into the system.");
			System.out.println("Enter 2 to load a graph from mygraph.bin.");
			System.out.println("Enter 3 to save a graph to mygraph.bin.");
			System.out.println("Enter 4 to search for cities inside a state.");
			System.out.println("Enter 5 to search for a city.");
			System.out.println("Enter 6 to set your current city.");
			System.out.println("Enter 7 to see your current city details.");
			System.out.println("Enter 8 to find the closest cities to you by distance.");
			System.out.println("Enter 9 to find the closest cities to you by path.");
			System.out.println("Enter 10 to find the shortest path between your current city and another.");
			System.out.println("Enter 11 to quit.");
			
			int answer=input.nextInt();
			while(answer <1 || answer >11)
			{
				System.out.println("Sorry I think your input was invalid, please try again.");
				System.out.println("Enter 1 to load a file into the system.");
				System.out.println("Enter 2 to load a map from mygraph.bin.");
				System.out.println("Enter 3 to save a map to mygraph.bin.");
				System.out.println("Enter 4 to search for cities inside a state.");
				System.out.println("Enter 5 to search for a city.");
				System.out.println("Enter 6 to set your current city.");
				System.out.println("Enter 7 to see your current city details.");
				System.out.println("Enter 8 to find the closest cities to you by distance.");
				System.out.println("Enter 9 to find the closest cities to you by path.");
				System.out.println("Enter 10 to find the shortest path between your current city and another.");
				System.out.println("Enter 11 to quit.");
			}
			
			switch(answer){
			
				case 1:
					loadFile();
					break;
				
				case 2:
					loadMap();
					break;
					
				case 3:
					storeMap();
					break;
				
				case 4:
					searchState();
					break;
				
				case 5:
					searchForCity();
					break;
				
				case 6:
					setCurrentCity();
					break;
				
				case 7:
					showCurrentCity();
					break;
				
				case 8:
					findClosestCities();
					break;
				
				case 9:
					findClosestPaths();
					break;
				
				case 10:
					findShortestPath();
					break;
					
				case 11:
					quit= true;
					break;					
			
			}
		}
	}
	/**
	 * Allows the user to load a file in two ways. Either start with a new map, or add to the existing map. Uses the algorithms of the grapher class.
	 */
	public void loadFile()
	{
		System.out.println("Would you like to add to the current map, or start over? Enter 1 to add to the current map and 0 to start over.");
		int answer= input.nextInt();
		while(answer!=0 && answer !=1)
		{
			System.out.println("Sorry I think your input was invalid, please try again.");
			System.out.println("Enter 1 to add to the current graph and 0 to start over.");
			answer= input.nextInt();
		}
		input.nextLine();
		if(answer==0)
		{
			map.resetGraph();
			System.out.println("The map has been reset.");
		}
		
		System.out.println("Please enter the file name you would like to use.");
		String filename=input.nextLine();
		Grapher grapher= new Grapher(map);
		grapher.graph(filename);
		map=grapher.getGraph();
		if(map.getSize()>0)
			System.out.println("Your file was loaded.");
	}
	/**
	 * Allows the user to load a graph from the mygpraph.bin file. Its run time is O(|V|).
	 */
	public void loadMap() 
	{
		System.out.println("Loading mygraph.bin");
		try {
			FileInputStream file = new FileInputStream("mygraph.bin");
			ObjectInputStream in = new ObjectInputStream(file);
			ArrayList<Node> cities= new ArrayList<Node>();
			Node city = (Node) in.readObject();
			boolean load = true;
			while (load) {
				cities.add(city);
				try {
					city= (Node) in.readObject();
				} catch (EOFException e) {
					in.close();
					file.close();
					load = false;
				}
			}
			map.resetGraph();
			for(Node i: cities)
			{
				map.addCity(i);
			}
			if (map.getSize() > 0) {
				System.out.println("Your map was loaded from mygraph.bin");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Allows the user to save a graph to the mygraph.bin file. Its run time is O(|E|).
	 */
	public void storeMap() 
	{
		if (map.getSize() > 0) {
			System.out.println("Saving map to file mygraph.bin");

			try {
				FileOutputStream file = new FileOutputStream("mygraph.bin");
				ObjectOutputStream out = new ObjectOutputStream(file);
				ArrayList<Node> cities= map.getCities();
				for(Node i: cities)
				{
					out.writeObject(i);
				}
				out.close();
				file.close();
				System.out.println("Your map has been saved.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Sorry it seems there is no map to save.");
		}
	}
	/**
	 * Allows a user to search for cities in a state, uses the algorithms in the Graph class. 
	 */
	public void searchState()
	{
		System.out.println("Please enter the state's name and only the state's name.");
		input.nextLine();
		String stateName=input.nextLine();
		stateName.trim();
		ArrayList<Node> cities= map.findCitiesInState(stateName);
		if(cities.size()>0)
		{
			System.out.println("Here are the cities in the state:");
			for(int i=0; i<cities.size(); i++)
			{
				System.out.println("");
				System.out.println("Name :" + cities.get(i).getCityName());
				System.out.println("ID :" + cities.get(i).getID());
				System.out.println("Incoming Count :" +cities.get(i).getIncomingCount());
				System.out.println("Outgoing Count :" + cities.get(i).getAdjacencyList().size());
				System.out.println("");
			}
		}
		
		else
			System.out.println("Sorry there were no cities in your state.");
	}
	/**
	 * Allows a user to search for a city in the graph, uses the algorithms in the Graph class.
	 */
	public void searchForCity()
	{
		System.out.println("Please enter the city's name and only the city's name you are looking for.");
		input.nextLine();
		String cityName=input.nextLine();
		cityName.trim();
		ArrayList<Node> cities= map.findCity(cityName);
		if(cities.size()>0)
		{
			System.out.println("Here are the potential cities you are looking for:");
			for(int i=0; i<cities.size(); i++)
			{
				System.out.println("");
				System.out.println("Name :" + cities.get(i).getCityName());
				System.out.println("ID :" + cities.get(i).getID());
				System.out.println("Incoming Count :" +cities.get(i).getIncomingCount());
				System.out.println("Outgoing Count :" + cities.get(i).getAdjacencyList().size());
				System.out.println("Latitude :" +cities.get(i).getLatitude());
				System.out.println("Longitude :" +cities.get(i).getLongitude());
				System.out.println("");
			}
		}
		
		else
			System.out.println("Sorry there were no city with that name in the map.");
	}
	/**
	 * Allows the user to set his/her current city based on an ID number from 1 to N, uses algorithms of the Graph class.
	 */
	public void setCurrentCity()
	{
		System.out.println("Please enter the ID number of the city you wish to set as current.");
		input.nextLine();
		int ID=input.nextInt();
		while(ID<1 || ID>map.getSize())
		{
			System.out.println("Sorry I think your input was invalid, please try again.");
			System.out.println("Please enter the ID number of the city you wish to set as current.");
			ID=input.nextInt();
		}
		
		map.setCurrentCity(ID-1);
		System.out.println("Your current city has now been changed.");
	}
	/**
	 * Simply displays the information of the current city.
	 */
	public void showCurrentCity()
	{
		if(map.getCurrentCity()==null)
			System.out.println("Sorry no city has been set as current.");
		
		else
		{
			Node curr=map.getCurrentCity();
			System.out.println("");
			System.out.println("Name: " + curr.getCityName());
			System.out.println("ID: " + curr.getID());
			System.out.println("Incoming Count: " +curr.getIncomingCount());
			System.out.println("Outgoing Count: " + curr.getAdjacencyList().size());
			System.out.println("Latitude: " +curr.getLatitude());
			System.out.println("Longitude: " +curr.getLongitude());
			System.out.println("");
		}
	}
	/**
	 * Allows the user to find n closest cities to his/her current location. Uses the GPSDistance class and is basically two traversals so the run time is O(|V|).
	 */
	public void findClosestCities()
	{
		System.out.println("Enter the number of closest cities to the current city you would like.");
		input.nextLine();
		int n= input.nextInt();
		while(n<1||n>map.getSize()-1)
		{
			System.out.println("Sorry I think your input was invalid, please try again.");
			System.out.println("Enter the number of closest cities to the current city you would like.");
			n= input.nextInt();
		}
		
		if(map.getCurrentCity()==null)
			map.setCurrentCity((int) (Math.random()*(map.getSize()-1)));
		ArrayList<GPSDistance> distances= new ArrayList<GPSDistance>();
		for(int i=0; i< map.getSize();i++)
		{
			if(map.getCity(i)!=map.getCurrentCity()) //distance formula taken from https://sites.google.com/a/cs.usfca.edu/cs-107-03-2012-spring/tutorials/distancecalculator
			{
				double x= (69.1)*(map.getCity(i).getLatitude()-map.getCurrentCity().getLatitude());
				double y= (69.1)*(map.getCity(i).getLongitude()-map.getCurrentCity().getLongitude())*(Math.cos(map.getCurrentCity().getLatitude()/57.3));
				double distance=Math.sqrt((x*x) + (y*y));
				GPSDistance attachedDistance= new GPSDistance(distance,map.getCity(i));
				distances.add(attachedDistance);
			}
		}
		
		Collections.sort(distances);
		
		System.out.println("Here are your n closest cities by GPS distance: ");
		for(int i=0; i<n; i++)
		{
			int rank=i+1;
			System.out.println("");
			System.out.println(rank+ ". " + distances.get(i).getAttachedCity().getCityName() + ", Distance: " + distances.get(i).getDistance());
			System.out.println("");
		}
	}
	/**
	 * Allows the user to see what cities he/she can get to directly from his/her current city within a certain cost. 
	 * Its run time is O(1) as it only goes through the edges of a single node, which is 2 or 8 each time.
	 */
	public void findClosestPaths()
	{
		System.out.println("Please input the max distance a city can be away from your current city.\nPlease note that this is only taking into account cities that are directly connected to your current city.");
		input.nextLine();
		int distance=input.nextInt();
		if(distance<100)
			System.out.println("Sorry there are no cities within that distance of your current city");
		else
		{
			if(map.getCurrentCity()==null)
				map.setCurrentCity((int) (Math.random()*(map.getSize()-1)));
			
			ArrayList<Node> cities= new ArrayList<Node>();
			for(int i=0; i<map.getCurrentCity().getAdjacencyList().size(); i++)
			{
				if(map.getCurrentCity().getWeights().get(i)<=distance)
					cities.add(map.getCurrentCity().getAdjacencyList().get(i));
			}
			
			if(cities.size()>0)
			{
				System.out.println("Here are the cities that are directly connected to your current city and less than " + distance + " units by edge weight away from it.");
				for(int i=0; i<cities.size();i++)
				{
					int rank=i+1;
					System.out.println("");
					System.out.println(rank+ ". " + cities.get(i).getCityName());
					System.out.println("");
				}
			}
			
			else
				System.out.println("Sorry there are no cities within that distance of your current city");
		}
	}
	/**
	 * Algorithm to find shortest path. Based off book example on page 337 of Mark Allen Weiss' Data Structures and Algorithm Analysis in Java. The run time for this should be 
	 * O(|E|log|V|) as the idea of queuing was also used.
	 */
	public void findShortestPath() 
	{
		final int INFINITY=1000000;
		System.out.println("Please input the ID number of the city you want to get to.");
		input.nextLine();
		int ID=input.nextInt();
		while(ID<1|| ID>map.getSize())
		{
			System.out.println("Sorry I think your input was invalid, please try again.");
			System.out.println("Please input the ID number of the city you want to get to.");
			ID=input.nextInt();
		}
		
		if(map.getCurrentCity()==null)
			map.setCurrentCity((int) (Math.random()*(map.getSize()-1)));
		
		if(ID==map.getCurrentCity().getID())
			System.out.println("You are already at the city you want to get to!");
		
		else
		{
			
			ArrayList<Node> temp= new ArrayList<Node>();
			ArrayList<Node> known= new ArrayList<Node>();
			for(int i=0;i<map.getSize();i++)
			{
				Node temp1=map.getCity(i);
				temp.add(temp1);
			}
		
		for(int i=0;i<temp.size();i++)
		{
			temp.get(i).updateDistance(INFINITY);
			temp.get(i).updateKnown(false);
		}
		
		temp.get(map.getCurrentCity().getID()-1).updateDistance(0);
	
		boolean over=false;
		while(temp.size()>0)
		{

			Collections.sort(temp);
			while(temp.get(0).getKnown())
			{
				known.add(temp.get(0));
				temp.remove(0);
				if(temp.size()==0)
				{
					over=true;
					break;
				}
					
			}
			
			if(!over)
			{
			Node curr= temp.get(0);
			curr.updateKnown(true);
			for(int k=0; k<curr.getAdjacencyList().size();k++)
			{
				Node adj=curr.getAdjacencyList().get(k);
				if(!adj.getKnown())
				{
					if(adj.getDistance()>curr.getDistance()+curr.getWeights().get(k))
					{
						adj.updateDistance(curr.getDistance()+curr.getWeights().get(k));
						adj.updatePath(curr);
					}
						
				}
			}		
		}
		}
		Node destination=null;
		for(int i=0; i<known.size();i++)
		{
			if(ID==known.get(i).getID())
			{
				destination=known.get(i);
				break;
			}
		}
		
		ArrayList<Node> path= new ArrayList<Node>();
		boolean done=false;
		while(!done)
		{
			if(destination.getPath()!=null)
			{
				path.add(destination);
				destination=destination.getPath();
			}
			
			else
			{
				path.add(destination);
				done=true;
			}
			
		}
		if(path.get(path.size()-1).getID()==map.getCurrentCity().getID())
		{
			int cost=path.get(0).getDistance();
			System.out.println("Here is your shortest path: ");
			for(int i=path.size()-1; i>=0; i--)
			{
				int number=path.size()-i;
				System.out.println("");
				System.out.println(number+ ". " + path.get(i).getCityName());
				System.out.println("");
			}
			System.out.println("The total cost of it is: " + cost);
		}
		
		else
			System.out.println("Sorry it is not possible to reach that city from your current one.");
		}
		
	}
	


}
