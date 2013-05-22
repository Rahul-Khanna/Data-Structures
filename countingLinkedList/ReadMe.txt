Rahul Khanna
rk2658

How to run and compile the code when in directory with the java files: :
javac *.java
java HWTester (file path name as the only argument in the command line)

The framework for the MyCountingLinkedLists class came from the book, Data Structures and Algorithm Analysis in Java (Third Edition), by Mark Allen Weiss

**Can Work With Anything, But Tester Class Assumes Strings**

The sort method used for this program is the Bubble Sort.

Class Description: 

HWTest:

Just a tester class for the the linked list class, that allows a user to print out the list in several ways, and allows them to control how many items are printed out.

MYLLException:

An exception class that is called on when someone tries to remove a node that doesn't actually exist.

MyCountingLinkedLists:

A linked list class for any type of data. Keeps count of the number of times it sees a particular instance of data, instead of making multiple nodes with the same data. Sorts the nodes in order of their counts, from greatest to least, but is able to reverse the list as well. Includes a nested Node class, that are the storage units for the data in the linked list.

Analysis of Methods:

clear():
This runs in constant time, O(1),as it just instantiates a new list.

size()
This runs in constant time ,O(1), as it just returns the size of the list

isEmpty():
This runs in constant time, O(1), as it just returns whether the list is empty or not

**Both add() methods use the addBefore method, described below**

addBefore(Node<AnyType> p, AnyType x):
This runs in O(n) time as in if the statement a method, checkString, is called which scans through the whole list with a while list to make sure their isn't a node with the same data already existing. As there is only one while loop, the method runs in O(n) time.

addBeforeNoCheck(Node<AnyType> p, AnyType x):
This runs in O(1) time, and is just a version of addBefore for the purpose of adding an element, when sure that the data trying to be added is not already in the list.

checkString(String s)
This runs in O(n) time as well, and it is the same reason as above, as addBefore calls checkString to see if a node already exists with the data trying to be added.

**get, set, getNode all use getNode(int idx, int lower, int upper) which is described below.**

getNode(int idx, int lower, int upper)
This runs in O(n) time as it scans through half of the list using a for loop to find the needed Node. Really this runs in O(n/2) but that is the same as O(n).

remove(int idx)
This runs in O(n) time as it calls the getNode method, which runs in O(n) time. It also calls the remove(Node<AnyType> p) which runs in O(1) time so it doesn't add to the time complexity.

remove(Node<AnyType> p) 
This runs in O(1) time as it just changes values for a node, but doesn't depend on other nodes. No for or while loops are used.

**both printList methods depend on printListTemplate which is described below**

printListTemplate(int indicator, int n)
This runs in O(n) time as it basically uses a while loop to go through the whole list and print out each element.

reverseList()
This runs in O(n) time as it uses a while loop to go through the list and reverse the order of the list.

sort() THIS IS THE BUBBLE SORT METHOD, EXTRA CREDIT
As described in the book, this method runs in O(n^2) time as it is two nested while loops. The method uses the outer loop to ensure that no more methods need to be sorted, and the inner while loop to actually go through the list and sort each element. The addBeforeNoCheck method used in it, actually runs in O(1) time, as the node is added to list, without a scan of the list to ensure that the data is not already there, but this is okay, since we just deleted the very same data from the list in the line before.

