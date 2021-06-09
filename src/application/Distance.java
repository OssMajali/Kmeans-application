package application;

public class Distance {
	public static double eucledianDistance(double[] point1, double[] point2) {
        double distance = 0;
        for(int i = 0; i < point1.length; i++) {
        	distance +=  Math.pow((point1[i] - point2[i]), 2) ;
        }
        return Math.sqrt(distance);
    }
	 public static double manhattanDistance(double point1[], double point2[]){
	    	double distance = 0;
	        for(int i = 0; i < point1.length; i++) {
	        	distance += (Math.abs(point1[i] - point2[i]));
	        }
	        return distance;
	 }
}
