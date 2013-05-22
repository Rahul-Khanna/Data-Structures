/**
 * Class for the nodes of an expression tree, adapted from the BNode class shown in lecture
 * @author Rahul Khanna
 */

public class ENode {
	 private String value;
	 private ENode left;
	 private ENode right;
	
	public ENode(String a){
		value = a;
		left = right = null;
	}
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String n){
		value = n;
	}
	
	public ENode getLeft(){
		return left;
	}
	
	public ENode getRight(){
		return right;
	}
	
	public void setLeft(ENode a){
		left = a;
	}
	
	public void setRight(ENode a){
		right = a;
	}
}
