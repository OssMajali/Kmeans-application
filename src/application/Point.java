package application;

public class Point {
     private String label;
     private double X;
     private double Y;
     private int cluster;
     
     
	public Point(String label, double x, double y) {
		this.label = label;
		X = x;
		Y = y;
	}
	
	

	public Point(String label, double x, double y, int cluster) {
		super();
		this.label = label;
		X = x;
		Y = y;
		this.cluster = cluster;
	}



	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public int getCluster() {
		return cluster;
	}
	public void setCluster(int cluster) {
		this.cluster = cluster;
	}
     
}
