/**
 * Just a tester class for the expression tree class.
 * @author Rahul Khanna
 *
 */
public class program2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			
				ExpressionTree tree= new ExpressionTree(args[0]);
				System.out.println("Prefix: " + tree.printPrefix());
				System.out.println("Infix: " + tree.printInfix());
				System.out.println("Postfix: " + tree.printPostfix());
				System.out.println("Result: " + tree.eval());
				
			}
	
		catch (ArrayIndexOutOfBoundsException e) // tell the user that he hasn't given enough information
		{
			System.out.println("You must give me the expression, and NOTHING else.Try again.");
		}
		}
	}


