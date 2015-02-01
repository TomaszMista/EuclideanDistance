package app;

import java.util.Vector;

/**
 * 
 * @author tomasz.mista
 *
 */

public class Utility {
	
	private static double distance(Point a, Point b)
	{
		if(a == null || b == null) {
			return 0;
		}
		
		double result = Math.pow(a.getX()-b.getX(), 2)+Math.pow(a.getY()-b.getY(), 2);
		
		result = Math.sqrt(result);
		
		return result;
	}
	
	private static Vector FP(Point p, PointSet s)
	{
		if(p == null || s == null)
		{
			System.out.println("Null Occur");
			return null;
		}

		Point[] points = s.toArray();
		if(points == null || points.length==0)
		{
			System.out.println("Points is NULL");
			return null;
		}
		Vector<Point> fpSet = new Vector<Point>();
		double maxD = -1;
		for(int i=0;i<points.length;i++)
		{
			double tempD = distance(p, points[i]);
			if(maxD<tempD)
			{
				fpSet.clear();
				fpSet.add(points[i]);
				maxD = tempD;
			}
			else if(maxD == tempD)
			{
				fpSet.add(points[i]);
			}
			else
			{
				//do nothing
			}
		}
		return fpSet;
	}
	
	public static DoubleNormal	Algorithm(Point p, PointSet s)
	{
		System.out.println("The greates distance is between:");
		double d1 = 0;//start distance
		double d2 = -1;
		PointSet P = s; 
		Point q = null;
		DoubleNormal DN = new DoubleNormal();

		while(d1!=d2)
		{
			
			System.out.println("x:"+p.getX()+", "+": y:"+p.getY());
			d2=d1;
			P.removePoint(p);
			Vector tempV = FP(p, P);
			if(tempV == null)
			{
				return DN;
			}
			q = (Point) tempV.get(0);
			if(distance(p, q)>d2)
			{
				d1=distance(p, q);
				DN.setX(p);
				DN.setY(q);
				p=q;
			}
		}
		return DN;
	}
	

	public static PointSet SetCopy(PointSet a, PointSet b)
	{
		Point[] points = null;
		if(a==null || b==null || b.setSize()==0)
		{
			return null;
		}
		points = b.toArray();
		for(int i=0;i<points.length;i++)
		{
			a.addPoint(points[i]);
		}
		return a;
	}
}
