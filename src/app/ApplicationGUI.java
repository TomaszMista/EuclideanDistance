package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintStream;

import javax.swing.*;

/*
 * @author tomasz.mista
 * 
 * Applet for calculating Euclidean Distance.
 * Distance calculated between two points in 2D plane (2 points with greatest distance).
 */

public class ApplicationGUI extends JApplet {
	
	private GridBagConstraints g = new GridBagConstraints(); 
	private JPanel jPanelContentPanel;
	private JPanel jPanelButtonPanel;
	private JPanel jPanelGraphPanel;
	private JButton jButtonCalculate;
	private JCheckBox jCheckBoxGreatestDistance;
	private JCheckBox jCheckBoxSmallestDistance;
	private JButton jButtonLoad;
	private JButton jButtonRandom;
	private JButton jButtonReset;
	private GridBagLayout gridbag;
	private RectangleArea rectangleArea;
	private PointSet pointSet = new PointSet();
	private Point pTemp = null;
	private boolean showMax = false;
	private boolean showMin = false;
	private boolean drawRandom = false;
	private PrintStream standardOut;
	
	/**
	 * ActionListener added to JButtonCalculate for calculating greatest distance between 2 point in 2D plane
	 * By clicking on Calculate button 2 greatest point are connected by line
	 */
	private ActionListener calculateButtonListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == jButtonCalculate && pointSet.setSize()>1) {

				showMax = true;
				rectangleArea.repaint();	
			}
			getRectangleArea().repaint();
		}
	};

    /**
     * ActionListener added to jButtonLoad for displaying window with local user directory structure.
     * From them user can select file with set of points between which the greatest Euclidean Distance can be calculated.
     */
	private ActionListener loadButtonListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser fc = new JFileChooser();  
            fc.setCurrentDirectory( new File( "C:\\" ) );  
			int returnVal = fc.showSaveDialog( ApplicationGUI.this );   
			String fileName = "C:\\" ;  
			if ( returnVal == JFileChooser.APPROVE_OPTION )     
			{    
				File aFile = fc.getSelectedFile();    
				fileName = aFile.getName();
            }  
           System.out.println( fileName );  
		}
	};
	
	/**
	 * ActionListener added to jButtonRandom for randomly setting up set of points in 2D plane
	 */
	private ActionListener randomButtonListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			//RectangleArea area = new RectangleArea(null);
			//area.drawRandomCircles(100,100);
			getRectangleArea().drawRandomCircles(100, 100);
			getRectangleArea().repaint();
		}

	};
	
	/**
	 * Action Listener added to jButtonReset removing points from rectangle area.
	 */
	private ActionListener resetButtonListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			 getPointSet().clear();
			 getRectangleArea().removeAll();
			 getRectangleArea().updateUI();
		}
	};
	
	/**
	 * MouseAdapter controlling Calculate button (enabled or disabled). 
	 * Set Calculate button as enabled if Greatest or Shortest checkbox is selected otherwise leave as disabled
	 */
	private MouseAdapter inputListener = new MouseAdapter()
	{
		public void mouseClicked(MouseEvent e)
		{
			if(InputVerifier())
			{
				jButtonCalculate.setEnabled(true);
			}
			else 
			{
				jButtonCalculate.setEnabled(false);
			}
		}
	};
	
	/**
	 * 
	 * @return
	 */
	private boolean InputVerifier() {
		
		getPointSet();
		Point[] points = new Point[pointSet.setSize()];
		points = pointSet.toArray();
		boolean isValid = false;
		
		if(!jCheckBoxGreatestDistance.isSelected() && !jCheckBoxSmallestDistance.isSelected()) {
		    isValid = false;
		}else if ((jCheckBoxGreatestDistance.isSelected() || jCheckBoxSmallestDistance.isSelected()) && points.length >= 2) {
			isValid = true;
		}
		
		return isValid;
	}

 
   	public void init() {

		setSize(600,350);
		
		jPanelContentPanel = (JPanel) this.getContentPane();

		gridbag = new GridBagLayout();

		//jPanel for placing buttons: Calculate, Load, Random, Reset
		jPanelButtonPanel = new JPanel(gridbag);
		jPanelButtonPanel.setBorder(BorderFactory.createTitledBorder(" "));
		g.insets = new Insets(2,2,2,2);
		
		//jCheckBox for calculating greatest Euclidean Distance
		jCheckBoxGreatestDistance = new JCheckBox("Greatest");
		g.gridx = 0;
		g.gridy = 0;
		jCheckBoxGreatestDistance.addMouseListener(inputListener);
		jPanelButtonPanel.add(jCheckBoxGreatestDistance,g);
		
		///jcheckBox for calculating smallest Euclidean Distance
		jCheckBoxSmallestDistance = new JCheckBox("Smallest");
		g.gridx = 1;
		g.gridy = 0;
	    jCheckBoxSmallestDistance.addMouseListener(inputListener);
		jPanelButtonPanel.add(jCheckBoxSmallestDistance,g);
		
		//set Calculate button
		jButtonCalculate  = new JButton("Calculate");
		jButtonCalculate.setPreferredSize(new Dimension(90, 30));
		jButtonCalculate.setEnabled(InputVerifier());
		jButtonCalculate.addActionListener(calculateButtonListener);
		g.gridx = 0;
		g.gridy = 1;
		jPanelButtonPanel.add(jButtonCalculate,g);
		
		//set Load button
		jButtonLoad = new JButton("Load");
		jButtonLoad.setPreferredSize(new Dimension(90, 30));
		g.gridx = 1;
		g.gridy = 1;
	    jButtonLoad.addActionListener(loadButtonListener);
		jPanelButtonPanel.add(jButtonLoad,g);
		
		//set Random button
		jButtonRandom = new JButton("Random");
		jButtonRandom.setPreferredSize(new Dimension(90, 30));
		jButtonRandom.addActionListener(randomButtonListener);
		g.gridx = 2;
		g.gridy = 1;
		jPanelButtonPanel.add(jButtonRandom,g);
		
		//set Reset button
		jButtonReset = new JButton("Reset");
		jButtonReset.setPreferredSize(new Dimension(90, 30));
		g.gridx = 3;
		g.gridy = 1;
		jButtonReset.addActionListener(resetButtonListener);
		jPanelButtonPanel.add(jButtonReset,g);
		
		jPanelContentPanel.add(jPanelButtonPanel,BorderLayout.PAGE_END);
        
		//jPanel for placing rectangleArea component
		jPanelGraphPanel = new JPanel();
		jPanelGraphPanel.setBorder(BorderFactory.createTitledBorder(""));
		rectangleArea = new RectangleArea(this);
		MouseListener mouseClicklistenerListener = new MouseListener(this);
		rectangleArea.addMouseListener(mouseClicklistenerListener);
		rectangleArea.addMouseListener(inputListener);

		jPanelGraphPanel.add(rectangleArea);
		
		jPanelContentPanel.add(jPanelGraphPanel,BorderLayout.WEST);
	    
		JTextArea jTextAreaReport = new JTextArea("Logs:\n");
		jTextAreaReport.setFont(new Font("Serif", Font.PLAIN, 11));
		jTextAreaReport.setLineWrap(true);
		jTextAreaReport.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(jTextAreaReport));
		standardOut = System.out;
        
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
        
		JScrollPane scroll = new JScrollPane(jTextAreaReport);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(270, 260));
		scroll.setBorder(
		            BorderFactory.createCompoundBorder(
		                BorderFactory.createCompoundBorder(
		                                BorderFactory.createTitledBorder(""),
		                                BorderFactory.createEmptyBorder(5,5,5,5)),
		                scroll.getBorder()));
		
		jPanelContentPanel.add(scroll,BorderLayout.EAST);
		
		jPanelContentPanel.repaint();
		
	}

   	/**
   	 * 
   	public void actionPerformed(ActionEvent e){
		 if (e.getSource() == random) {
				//isOK=true;
				pointSet.clear();
	            try {
	            	ArrayList<String> column0 = new ArrayList<String>();// Defining an character Array List
	                ArrayList<Double> column1 = new ArrayList<Double>();// Defining an integer Array List
	                ArrayList<Double> column2 = new ArrayList<Double>();// Defining an integer Array List

	                Scanner myfile = new Scanner(new FileReader("E:\\app\\euclidean.txt")); // Reading file using Scanner

	                while (myfile.hasNext()) {// Read file content using a while loop
	                	column0.add(myfile.next());
	                    column1.add(myfile.nextDouble());// Store the first integer into the first array list
	                    column2.add(myfile.nextDouble());// Store the next integer into the second array list
	                    
	                }
	                System.out.println("The file loaded file consist the following points:");
	                
	                for(int i=0;i<column1.size();i++){//column are equals
	                	Point p = new Point();
	                	p.setA((String)column0.get(i));
	                	p.setX((double)column1.get(i));
	                	p.setY((double)column2.get(i));
	                
	                	System.out.println(p.getA()+":" + " x:"+p.getX()+", y:"+p.getY());
	 					pointSet.addPoint(p);
	 					this.addPoint(p);
	 					this.setPTemp(p);
	 					}
	                myfile.close(); // close the file
	                }catch (Exception ei) {// we defined it just in the case of error
	                	ei.printStackTrace(); 
	                	}
	                } else if (e.getSource() == maxD ) {
	                	
	                	if (pointSet.setSize() > 1) {
	                		//getRectangleArea().setBackground(Color.blue);
	                		showMax = true;
	                		rectangleArea.repaint();
	                		} else {
	                			// do nothing
	                			}	
	                	}
		 getRectangleArea().repaint();
		 }
   	*/
   	
   	public void addPoint(Point p) {
		this.pointSet.addPoint(p);
	}

	public PointSet getPointSet() {
		return pointSet;
	}

	public void setPointSet(PointSet pointSet) {
		this.pointSet = pointSet;
	}

	public JButton getMaxD() {
		return jButtonCalculate;
	}

	public void setMaxD(JButton jButtonCalculate) {
		this.jButtonCalculate = jButtonCalculate;
	}

	public boolean isShowMax() {
		return showMax;
	}

	public void setShowMax(boolean showMax) {
		this.showMax = showMax;
	}
	
	public JButton isDrawRandom() {
		return jButtonRandom;
	}
	
	public void setDrawRandom (JButton jButtonRandom) {
		this.jButtonRandom = jButtonRandom;
	}
	
	public boolean isShowMin() {
		return showMin;
	}
	
	public void setShowMin(boolean showMin) {
		this.showMin = showMin;
	}

	public Point getPTemp() {
		return pTemp;
	}

	public void setPTemp(Point temp) {
		pTemp = temp;
	}

	public RectangleArea getRectangleArea() {
		return rectangleArea;
	}

	public void setRectangleArea(RectangleArea rectangleArea) {
		this.rectangleArea = rectangleArea;
	}
	
}
	
	