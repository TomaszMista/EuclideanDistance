package app;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * 
 * @author tomasz.mista
 * 
 * MouseListener class for listening mouse clicks
 *
 */

public class MouseListener extends MouseInputAdapter {
	
	private ApplicationGUI project = null;
	
	public MouseListener(ApplicationGUI project)
	{
		this.project = project;
	}

	public void mousePressed(MouseEvent e) {
		
		Point p = new Point();
		p.setX(e.getX());
		p.setY(e.getY());
		project.addPoint(p);
		project.setPTemp(p);
		this.project.getRectangleArea().repaint();
	}

}
