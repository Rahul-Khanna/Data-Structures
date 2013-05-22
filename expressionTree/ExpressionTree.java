/**
 * Class for an Expression Tree. Builds an expression tree from a String and prints the tree out in three ways.
 * @author Rahul Khanna
 */
import java.util.StringTokenizer;


public class ExpressionTree {
	ENode root;
	public ExpressionTree(String input) {
		buildTree(input);
	}
	
	// methods to recursively print prefix notation
	public String printPrefix() {
		return printPrefix(root);
	}

	private String printPrefix(ENode n) {
		if (n.getLeft() != null && n.getRight() != null)
			return n.getValue() + " " + printPrefix(n.getLeft()) + " "
					+ printPrefix(n.getRight());
		else
			return n.getValue();
	}

	// methods to recursively print infix notation
	public String printInfix() {
		return printInfix(root);
	}

	private String printInfix(ENode n) {
		if (n.getLeft() != null && n.getRight() != null)
			return "(" + printInfix(n.getLeft()) + " " + n.getValue() + " "
					+ printInfix(n.getRight()) + ")";
		else
			return n.getValue();
	}

	// methods to recursively print postfix notation
	public String printPostfix() {
		return printPostfix(root);
	}

	private String printPostfix(ENode n) {
		if (n.getLeft() != null && n.getRight() != null)
			return printPostfix(n.getLeft()) + " " + printPostfix(n.getRight())
					+ " " + n.getValue();
		else
			return n.getValue();
	}

	// from now on this is all lecture code
	private boolean isOperation(String s) {
		char c = s.charAt(0);
		switch (c) {
   			case '+':	return true;
   			case '-':  	return true;
   			case '*':  	return true;
   			case '/':  	return true;
		}
		return false;
	}

	public void buildTree(String input) {
   		StringTokenizer stok = new StringTokenizer(input, " ");
   		Stack s = new Stack();
		while (stok.hasMoreElements()) {
   			String n = stok.nextToken();
   			if (isOperation(n)) {
   				ENode A = s.pop();
   				ENode B = s.pop();
   				ENode C = new ENode(n);
   				C.setLeft(B);
   				C.setRight(A);
   				s.push(C);
				root = C;
   			}
   			else
   				s.push(new ENode(n));
   		}
  	}

  	public int eval() {
   	return eval(root);
  	}

   	private int eval(ENode n) {
   		if (isOperation(n.getValue())) {
   			int L = eval(n.getLeft());
   			int R = eval(n.getRight());
   			return calculate(n.getValue(), L, R);
   		}
   		else
   			return Integer.parseInt(n.getValue());
   	}

   	private int calculate(String operation, int L, int R) {
   		char op = operation.charAt(0);
   		switch (op) {
   			case '+': 	return L+R;
   			case '-':	return L-R;
   			case '*':  	return L*R;
   			case '/':	return L/R;
   		}
		return 0;   
	}	
}

