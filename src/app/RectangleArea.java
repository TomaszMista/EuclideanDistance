package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 * @author tomasz.mista
 * 
 * GraphArea represents 2D plane for setting up points 
 * and calculating Euclidean Distance between them
 *
 */

public class RectangleArea extends JPanel {
	
	private ApplicationGUI project = null;
	private Dimension preferredSize = new Dimension(300,250);
	private ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
	
	public RectangleArea(ApplicationGUI project)
	{
		this.project = project;
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
        setBorder(compound);
	}
	
	public Dimension getPreferredSize() {
        return preferredSize;
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		PointSet pointSet = this.project.getPointSet();
		boolean showMax = this.project.isShowMax();
		JButton drawRandom = this.project.isDrawRandom();
		
		if(pointSet.setSize()<1)
		{
			return;
		}
		Point[] points = new Point[pointSet.setSize()];
		points = pointSet.toArray();
		for(int i=0;i<points.length;i++)
		{
			g.setColor(Color.BLACK);
			g.drawRoundRect((int)points[i].getX()-1,(int)points[i].getY()-1,3,3,3,3);
	    	g.fillRoundRect((int)points[i].getX()-1,(int)points[i].getY()-1,3,3,3,3);
		}
		
		
		if(points.length>1 && showMax==true)
		{
			Point pTemp = this.project.getPTemp();
			DoubleNormal dn = Utility.Algorithm(pTemp, pointSet);
			g.setColor(Color.RED);
			g.drawLine((int)dn.getX().getX(), (int)dn.getX().getY(), (int)dn.getY().getX(), (int)dn.getY().getY());
			//Calculate the Euclidean Distance between two greatest points
			double minusLeft = (dn.getY().getX() - dn.getX().getX());
			double powerLeft = minusLeft * minusLeft;
			double minusRight = dn.getY().getY() - dn.getX().getY();
			double powerRight = minusRight * minusRight;
			double sumEquation = powerLeft + powerRight;
			double squareEquation = (int)Math.sqrt(sumEquation);
		    System.out.println("The Euclidean Distance is equals to: " + squareEquation);
		    
			showMax = false;
			this.project.setShowMax(showMax);
		}
		
	}
	
	public void drawRandomCircles(int x, int y) {
		//rects.add(new Rectangle(x, y, 50, 50));
		repaint();
	}
}
