package app;

/**
 * 
 * @author tomasz.mista
 *
 */

public class Point {
	
	private String a;
	private double x;
	private double y;
	
	public Point(String a, double x,double y)
	{
		this.a= a;
		this.x = x;
		this.y = y;
	}
	
	public Point()
	{	
	}
	
	public String getA(){
		return a;
	}
	
	public void setA(String a){
		this.a = a;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
