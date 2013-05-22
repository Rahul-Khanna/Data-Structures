/**
 * A simple class that allows a distance from a fixed point to a variable city to be attached to the variable city. Useful for keeping track of what distance goes with
 * what city.
 * @author R_K
 *
 */
public class GPSDistance implements Comparable<GPSDistance> {


		private double distance;
		private Node attachedCity;

		public GPSDistance(double distance, Node city) {
			this.distance = distance;
			attachedCity = city;
		}

		public Node getAttachedCity() {
			return attachedCity;
		}

		public double getDistance() {
			return distance;
		}

		public int compareTo(GPSDistance other) {
			if (distance > other.getDistance())
				return 1;
			if (distance < other.getDistance())
				return -1;
			else
				return 0;
		}

	}

