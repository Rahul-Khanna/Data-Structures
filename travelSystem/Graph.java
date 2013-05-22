import java.io.Serializable;
import java.util.ArrayList;

/**
 * The graph class used to store all nodes and edges.
 * @author R_K
 *
 */
@SuppressWarnings("serial")
public class Graph implements Serializable {

	private ArrayList<Node> nodes;
	private int size;
	private Node currentCity;
	
	public Graph()
	{
		size=0;
		nodes= new ArrayList<Node>();
	}
	
	public void addCity(Node city)
	{
		nodes.add(city);
		size++;
	}
	
	public Node getCity(int index)
	{
		return nodes.get(index);
	}
	
	public ArrayList<Node> getCities()
	{
		return nodes;
	}
	
	public void setCurrentCity(int ID) // runs in O(1) time
	{
		currentCity=nodes.get(ID);
	}
	
	public Node getCurrentCity() // runs in O(1) time.
	{
		return currentCity;
	}
	
	public void resetGraph()
	{
		 nodes.clear();
		 size=0;
	}
	
	public int getSize()
	{
		return size;
	}
	/**
	 * Finds all cities within a state. Has a run time of O(|V|).
	 * @param stateName
	 * @return
	 */
	public ArrayList<Node> findCitiesInState(String stateName)
	{
		ArrayList<Node> cities= new ArrayList<Node>();
		for(int i=0; i<size; i++)
		{
			String name= nodes.get(i).getStateName();
			if(name.matches("(?i).*"+stateName+".*"))
			{
				cities.add(nodes.get(i));
				
			}
				
			
		}
		
		return cities;
	}
	/**
	 * Finds all cities with a certain name. Has a run time of O(|V|).
	 * @param cityName
	 * @return
	 */
	public ArrayList<Node> findCity(String cityName)
	{
		ArrayList<Node> cities= new ArrayList<Node>();
		for(int i=0;i<size;i++)
		{
			String name= nodes.get(i).getCityName();
			if(name.matches("(?i).*"+cityName+".*"))
				cities.add(nodes.get(i));
		}
		
		return cities;
	}
	
}
