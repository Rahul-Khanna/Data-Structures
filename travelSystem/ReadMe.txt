Rahul Khanna
rk2658
Programming Assignment 3

How to Run Code When In Directory With Files:
javac *.java
java Tester

Class Descriptions:

Node:

This class acts as cities and stores all the information that is needed for each city. Uses an adjacency list to keep track of what other vertices are near by and the cost to them. 

GPSDistance:

Allows the distance from a fixed city to a variable one to be associated with the variable city very easily. Important for when trying to find the n closest cities.

Graph:

Stores all the information about the map/graph in it. Stores the current city, the nodes, and the therefore the edges via the nodes in it. Can also find cities in state and just cities in general very easily, O(|V|) time. It sets and gets the current city in O(1) time.

Grapher:

Has the ability to read in a properly formatted file and parse through it creating nodes. It then randomly assigns edges and weights to the edges. Finally it goes through all nodes and makes sure there is at least one edge going into each one. The run time for these algorithms are O(|V|), O(|E|) and O(|V|) receptively.

TravelSystem:

Is the main class in this program. Is the UI for it and allows the user to engage the program and choose any of the 11 options at any time. It allows the user to load a file via the grapher class. It allows the user to load and save a graph to a file called mygraph.bin, and does so in O(|V|) time. It then uses the graph class to find cities in a state, a city, set and get a current city. It then finds the n closest cities to your current city in O(|V|) time, it also finds the the closest cities via cost in O(1) time,  as it only has to go through the number of edges of a single node which is a number between 2 and 8 each time. If the number of edges was more variable it would be O(|E|) time. Finally it allows the user to find the shortest path via edges from a current point to another. This algorithm is based on the one in Mark Allen Weiss' Data Structures and Algorithm Analysis in Java. Its run time as described in the book is O(|E|log|V|).

Tester:

Just runs the TravelSystem class.


