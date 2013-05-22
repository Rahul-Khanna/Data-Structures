import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * A tester class that shows the workings of MyCountingLinkedLists
 * @author Rahul Khanna
 * UNI:rk2658
 */
public class HWTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String pathName = args[0]; // takes in the path name from the command line
			File ipAddresses = new File(pathName);
			Scanner input = new Scanner(ipAddresses);
			MyCountingLinkedLists<String> list = new MyCountingLinkedLists<String>();
			while (input.hasNextLine()) { // Reads in the input
				String ipAddress = input.nextLine();
				list.add(ipAddress);
			}

			System.out.println("How would you like the list to be printed?"); //count or percentage
			System.out.println("If you would like the count to be printed with percentages, enter 0.");
			System.out.println("If you would like the count to be printed with the actual count, enter 1");
			Scanner userInput = new Scanner(System.in);
			int indicator = userInput.nextInt();
			while (indicator != 0 && indicator != 1) {
				System.out.println("You didn't enter a valid choice.");
				System.out.println("If you would like the count to be printed with percentages, enter 0.");
				System.out.println("If you would like the count to be printed with the actual count, enter 1");
				indicator = userInput.nextInt();
			}
			System.out // certain number of elements or all elements
					.println("Would you like the whole list to be printed or just a certain number of elements?");
			System.out.println("If you would like a certain number to be printed, please enter the number. The number must be greater than zero and less then "+ list.size() + ".");
			System.out.println("If you would like the whole list to be printed, please enter "+ list.size() + ".");
			int number = userInput.nextInt();

			while (number < 1 && number > list.size()) {
				System.out.println("You didn't enter a valid number.");
				System.out.println("If you would like a certain number to be printed, please enter the number. The number must be greater than zero and less then "+ list.size() + ".");
				System.out.println("If you would like the whole list to be printed, please enter "+ list.size() + ".");
				number = userInput.nextInt();
			}

			list.sort(); // SORTS THE LIST USING THE BUBBLE SORT TECHNIQUE, EXTRA CREDIT
			list.printList(indicator, number); // prints the list
			
			list.reverseList(); //reverses the list
			System.out.println(" ");
			System.out.println("The list in revers is: "); 
			list.printList(indicator, number); // prints out the reversed list
		}

		catch (IOException e) // tells the user that file inputed is not valid
		{
			System.out.println("Please try again with a valid file path. Try again.");
		}

		catch (ArrayIndexOutOfBoundsException e) // tell the user that he hasn't
													// given enough information
		{
			System.out.println("You must give me the file path, and NOTHING else.Try again.");
		}

	}

}
