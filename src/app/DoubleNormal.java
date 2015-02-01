package app;

/**
 * 
 * @author tomasz.mista
 *
 */

public class DoubleNormal {
	
	private Point a;
	private Point x;
	private Point y;
	
	public DoubleNormal(Point x, Point y)
	{
		this.x = x;
		this.y = y;
	}
	
	public DoubleNormal()
	{
		
	}

	public Point getX() {
		return x;
	}

	public void setX(Point x) {
		this.x = x;
	}

	public Point getY() {
		return y;
	}

	public void setY(Point y) {
		this.y = y;
	}
	
	public Point getA(){
		return a;
	}
	
	public void setA(Point a) {
		this.a = a;
	}
	
	
}
