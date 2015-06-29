package app;

import java.util.Vector;


/**
 * 
 * @author tomasz.mista
 *
 * PointSet class
 */

public class PointSet {
	
	private Vector<Point> set = null;
	
	public PointSet()
	{
		set = new Vector<Point>();
	}
	
	public boolean addPoint(Point p)
	{
		if(set.contains(p))
		{
			return false;
		}
		set.add(p);
		return true;
	}
	
	public boolean containPoint(Point p)
	{
		if(!set.contains(p))
		{
			return false;
		}
		return true;
	}
	
	public boolean removePoint(Point p)
	{
		if(set.contains(p))
		{
			set.remove(p);
			return true;
		}
		return false;
	}
	
	public int setSize()
	{
		return set.size();
	}
	
	public Point[] toArray()
	{
		Point[] points = new Point[set.size()];
		set.toArray(points);
		return points;
	}
	
	public void clear()
	{
		set.clear();
	}
}
