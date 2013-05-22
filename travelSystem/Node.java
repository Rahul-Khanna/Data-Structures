import java.io.Serializable;
import java.util.ArrayList;

/**
 * The node class that is used to represent cities in the map. Uses the adjacency list concept to keep track of what nodes and the corresponding edge weights are attached
 * to it.
 * @author R_K
 *
 */
@SuppressWarnings("serial")
public class Node implements Serializable, Comparable<Node>  {
	
	private String cityName;
	private String stateName;
	private int ID;
	private double longitude;
	private double latitude;
	private int incomingCount;
	private ArrayList<Node> adjacencyList;
	private ArrayList<Integer> adjacencyListWeights;
	
	private Node path;
	private int distance; 
	private boolean known;
	
	public Node(String cityName, String stateName, int ID, double latitude, double longitude)
	{
		this.cityName=cityName;
		this.stateName=stateName;
		this.ID= ID;
		this.latitude=latitude;
		this.longitude=longitude;
		incomingCount=0;
		adjacencyList= new ArrayList<Node>();
		adjacencyListWeights= new ArrayList<Integer>();
	}
	
	public String getCityName()
	{
		return cityName;
	}
	
	public String getStateName()
	{
		return stateName;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public void addOutgoingCity(Node out, int weight)
	{
		adjacencyList.add(out);
		adjacencyListWeights.add((Integer) weight);
	}
	
	public ArrayList<Node> getAdjacencyList()
	{
		return adjacencyList;
	}
	
	public ArrayList<Integer> getWeights()
	{
		return adjacencyListWeights;
	}
	
	public void addIncomingCount() // useful for displaying information about the node and making sure that a node has an incoming edge.
	{
		incomingCount++;
	}
	
	public int getIncomingCount()
	{
		return incomingCount;
	}
	
	public void updateKnown(boolean known)
	{
		this.known=known;
	}
	
	public boolean getKnown()
	{
		return known;
	}
	
	public void updateDistance(int distance)
	{
		this.distance=distance;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public void updatePath(Node path)
	{
		this.path=path;
	}
	
	public Node getPath()
	{
		return path;
	}
	
	public int compareTo(Node other) {
		
		if(other.distance> distance)
			return -1;
		if(distance>other.distance)
			return 1;
		else
			return 0;
	}

}
