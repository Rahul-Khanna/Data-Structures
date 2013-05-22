import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class that is responsible for creating/updating a graph from the formatted text file.
 * @author R_K
 *
 */
public class Grapher {

	private Graph graph;
	private Scanner in;
	private int initialGraphSize;
	public Grapher(Graph g)
	{
		graph=g;
		initialGraphSize=g.getSize();
	}
	/**
	 * Runs all the other functions in the class.
	 * @param file
	 */
	public void graph(String file)
	{
		try{
			File input=new File(file);
			in= new Scanner(input);
			createGraph();
			addEdges();
			sweep();
			
			}
		catch(FileNotFoundException e) 
		{
			System.out.println("File is not valid, please try again with a correct file.");
		}
	}
	
	public Graph getGraph()
	{
		return graph;
	}
	/**
	 * Parses through the text file creating Nodes and adding them to the graph. Has a run time of O(|V|).
	 */
	private void createGraph()
	{
		int size=Integer.parseInt(in.nextLine());
		for(int i=1;i<=size;i++)
		{
			String name= in.nextLine();
			String [] nameArray =name.split(",");
			String cityName;
			String stateName;
			if(nameArray.length==3)// Washington DC
			{
				cityName=nameArray[0]+" " +nameArray[1];
				stateName=nameArray[2];
			}
			
			if(nameArray.length==2)
			{
				cityName=nameArray[0];
				stateName=nameArray[1];
			}
			
			else
			{
				cityName=nameArray[0];
				stateName=nameArray[0];
			}
			double latitude=Double.parseDouble(in.nextLine());
			double longitude=Double.parseDouble(in.nextLine());
			Node city= new Node(cityName,stateName,initialGraphSize+i,latitude,longitude);
			graph.addCity(city);
		}
	}
	/**
	 * Randomly attaches edges to nodes to connect the graph.The run time turns out to be O(|E|) as it only touches an edge once, while creating the edge.
	 */
	private void addEdges()
	{
		for(int i=initialGraphSize;i<graph.getSize();i++)
		{
			int numberOfEdges=(int) (2+Math.random()*6);
			int [] adjacent = new int[numberOfEdges];
			
			for(int j=0; j<numberOfEdges; j++)
			{
				int id= (int) ((Math.random()*(graph.getSize()))+1);
				for(int k=0;k<j;k++)// ensures all edges are distinct
				{
					while(id== adjacent[k])
					{
						id= (int) ((Math.random()*(graph.getSize()))+1);
						k=0;
					}
				}
				adjacent[j]=id;
			}
			
			int [] weights = new int[numberOfEdges];
			for(int l=0; l<numberOfEdges; l++)
			{
				weights[l]= (int) (100+Math.random()*1900);
			}
				
			Node city=graph.getCity(i);
			for(int m=0; m<numberOfEdges; m++)
			{
				city.addOutgoingCity(graph.getCity(adjacent[m]-1), weights[m]);
				graph.getCity(adjacent[m]-1).addIncomingCount();
			}
		}	
	}
	/**
	 * Ensures that every node has an incoming edge into it. Has a run time of O(|V|).
	 */
	private void sweep()
	{
		for(int i=initialGraphSize; i<graph.getSize();i++)
		{
			if(graph.getCity(i).getIncomingCount()==0)
			{
				int id= (int) ((Math.random()*(graph.getSize()))+1);
				int weight= (int) (100+Math.random()*1900);
				graph.getCity(id-1).addOutgoingCity(graph.getCity(i), weight);
				graph.getCity(i).addIncomingCount();
			}
		}
	}
}
