/**
 * A stack class, implemented with a dynamic Array.
 * @author Rahul Khanna
 */
public class Stack {

	private ENode[] stack;
	private int head;
	public Stack()
	{
		stack= new ENode[100];
		head=-1;
	}
	
	public void push(ENode a) {
		head++;
		if(head>=stack.length)
		{
			resize();
			stack[head]=a;
		}
		else
			stack[head]=a;
	}
	
	public ENode pop() {
		ENode a= stack[head];
		stack[head]=null;
		head--;
		return a;
	}
	
	public void resize()
	{
		ENode[] temp= new ENode[(stack.length)*2];
		for(int i=0;i<head;i++)
		{
			temp[i]=stack[i];
		}
		
		stack=temp;
	}

	

}
