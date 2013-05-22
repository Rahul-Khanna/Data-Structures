import java.text.DecimalFormat;
/**
 * A linked list class that works well with Strings, but can be adapted to any data
 * @author Rahul Khanna
 * UNI:rk2658
 * The framework of the code is from this website http://users.cis.fiu.edu/~weiss/dsaajava3/code/MyLinkedList.java
 * The code comes from the book Data Structures and Algorithm Analysis in Java (Third Edition), by Mark Allen Weiss
 * @param <AnyType>
 */
public class MyCountingLinkedLists<AnyType> {
	private int theSize;
	private int totalCount = 0;
	private int modCount = 0;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;

	/**
	 * Construct an empty LinkedList.
	 */
	public MyCountingLinkedLists() {
		clear();
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void clear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);
		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	/**
	 * Says whether the list is empty or not
	 * @return whether the list is empty or not
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *            any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param x
	 *            any object.
	 * @param idx
	 *            position to add at.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at specified position p. Items at or
	 * after that position are slid one position higher.
	 * 
	 * @param p
	 *            Node to add before.
	 * @param x
	 *            any object
	 */
	
	
	private Node<AnyType> addBefore(Node<AnyType> p, AnyType x) {
		if (!(checkString((String) x))) {
			Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
			newNode.prev.next = newNode;
			p.prev = newNode;
			theSize++;
			totalCount++;
			modCount++;
			return newNode;
		}

		else
			return null;

	}
	/**
	 * Just adds a Node, without checking if the data trying to be added is already in the list or not
	 * @param p Node to add before.
	 * @param x any object.
	 */
	private Node<AnyType> addBeforeNoCheck(Node<AnyType> p, AnyType x)
	{
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		totalCount++;
		modCount++;
		return newNode;
	}
	/**
	 * Method that checks if a node with the same String as data already exists
	 * @param s the data in the form of a String
	 * @return whether their is already a node or not containing the data
	 */

	public boolean checkString(String s) {
		int indicator = 0;
		Node<AnyType> current = beginMarker.next;
		while (current != endMarker) {
			String data = (String) current.data;
			if (data.equals(s)) {
				indicator = 1;
				current.count = current.count + 1;
				totalCount++;
				break;
			}
			current = current.next;
		}

		if (indicator == 1)
			return true;
		else
			return false;
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *            the index to search in.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *            the index to change.
	 * @param newVal
	 *            the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *            index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *            index to search at.
	 * @param lower
	 *            lowest valid index.
	 * @param upper
	 *            highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between lower and upper, inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx
					+ "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *            the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *            the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		if (p == null) {
			MYLLException e = new MYLLException("That element doesn't exist you can't delete it.");
			return null;
		}

		else {
			p.next.prev = p.prev;
			p.prev.next = p.next;
			theSize--;
			modCount++;
			return p.data;
		}
	}
	/**
	 * Method for printing out the whole list
	 * @param indicator the indicator that dictates the format of count
	 */
	public void printList(int indicator) {
		printListTemplate(indicator, theSize);
	}

	/**
	 * Method to print out first n items
	 * @param indicator the indicator that dictates the format of count
	 * @param n the number of entries to print
	 * @return just to differentiate betwwen the two methods
	 */
	public boolean printList(int indicator, int n) {
		printListTemplate(indicator, n);
		return true;
	}

	/**
	 * Prints the list according to the users desires
	 * 
	 * @param indicator
	 *            the indicator that dictates the format of count
	 * @param n
	 *            the number of entries to print
	 */
	private void printListTemplate(int indicator, int n) {
		DecimalFormat df = new DecimalFormat("#########0.00");
		Node<AnyType> current = beginMarker.next;
		int i = 1;
		if (indicator == 1) {
			while (current != endMarker) {
				if (i > n)
					break;
				System.out.println(current.data + "   " + current.count);
				current = current.next;
				i++;
			}
		}

		else {
			while (current != endMarker) {
				if (i > n)
					break;
				Double percentage = (double) (current.count * 100) / totalCount;
				percentage = (double) Math.round(10000 * percentage) / 10000;
				String formattedPercentage = df.format(percentage);
				System.out.println(current.data + "   " + formattedPercentage
						+ "%");
				i++;
				current = current.next;
			}
		}

	}

	/**
	 * Method that reverses the order of the list
	 */
	public void reverseList() {
		Node<AnyType> current = endMarker;
		Node<AnyType> temp;
		while (current != beginMarker) {
			temp = current.next;
			current.next = current.prev;
			current.prev = temp;
			current = current.next;
		}
		temp = endMarker;
		endMarker = beginMarker;
		beginMarker = temp;
	}

	/**
	 * Method that implements the Bubble Sort technique to sort through the linked list
	 * EXTRA CREDIT
	 */
	public void sort() {
		boolean notChanged = false;
		Node<AnyType> current;
		Node<AnyType> nextNode;
		AnyType tempData;
		int tempCount;
		while (!notChanged) {
			notChanged = true;
			current = beginMarker.next;
			while (current != endMarker) {
				nextNode = current.next;
				if (current.count < nextNode.count) {
					tempData = current.data;
					tempCount = current.count;
					remove(current);
					current = addBeforeNoCheck(nextNode.next, tempData);
					current.count = tempCount;
					notChanged = false;
				}

				else
					current = current.next;

			}
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType> {
		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
		public int count;

		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			data = d;
			prev = p;
			next = n;
			count = 1;
		}

	}
}
