import java.awt.*;
import java.util.List;
import java.util.*;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
//Dots.java			Amy Liu

/*
 * Dots is a program that allows you to place dots on the screen
 * and counts the number of dots on the screen.
 * Features:
 * - Each dot is a different random color.  Changes color each time you use 
 *   one of the features unless setting to a solid color.
 * - Change size of dots
 * 		- Scroll up using mouse to increase size
 * 		- Scroll down using mouse to decrease size
 * - Click and drag mouse to place a series of dots along mouse path
 * - Dot follows mouse path
 * 		- Dot is green when using random color
 * 		- Dot is the same color as the other dots when using specific color
 * - Clear screen
 * 		- Press DELETE on keyboard
 * - Erase last dot placed
 * 		- Press SPACE on keyboard
 * - Enable random color
 * 		- Press CONTROL on keyboard
 * - Change color of dots to red
 * 		- Press R on keyboard
 * - Change color of dots to green
 * 		- Press G on keyboard
 * - Change color of dots to blue
 * 		- Press B on keyboard
 * - Change color of dots to white
 * 		- Press W on keyboard
 * - Change color of dots to yellow
 * 		- Press Y on keyboard
 * - Change color of dots to magenta
 * 		- Press M on keyboard
 * - Change color of dots to orange
 * 		- Press O on keyboard
 * - Change color of dots to pink
 * 		- Press P on keyboard
 */

@SuppressWarnings("serial")
public class Dots extends JPanel{

	private final int WIDTH = 700, HEIGHT = 500;
	private int radius;

	private List<Point> pointList;
	private int count;

	private Point mousePoint = new Point();
	
	private Color color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	private boolean randomColor = true;
	
	public Dots() {
		pointList = new ArrayList<Point>();
		count = 0;
		radius = 6;
		
		addMouseListener(new DotsListener());
		addMouseWheelListener(new DotsListener());
		addMouseMotionListener(new DotsListener());
		addKeyListener(new DotsListener());

		setBackground(Color.black);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	// Necessary to get KeyboardListener to work
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(color);
		
		for (Point point : pointList)
		{
			if(randomColor)
			{
				g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
			}
			g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);
		}
		
		if(randomColor)
		{
			g.setColor(Color.green);
		}
		
		g.fillOval(mousePoint.x - radius, mousePoint.y - radius, radius * 2, radius * 2);
		
		g.setColor(Color.green);
		g.drawString("Count: " + count, 5, 15);
	}

	private class DotsListener implements MouseListener, MouseWheelListener, MouseMotionListener, KeyListener {

		//Mouse
		@Override
		public void mousePressed(MouseEvent e) {
			pointList.add(e.getPoint());
			count++;
			repaint();
		}

		public void mouseClicked(MouseEvent e) { }
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }

		//MouseWheel
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
			{
				if(radius < 0)
					radius = 0;
				else
					radius -= e.getWheelRotation();
				repaint();
			}
		}

		//MouseMotion
		@Override
		public void mouseDragged(MouseEvent e) {
			pointList.add(e.getPoint());
			count++;
			mouseMoved(e);
			repaint();
		}

		public void mouseMoved(MouseEvent e) { 
			mousePoint = e.getPoint();
			repaint();
		}

		//Key
		@Override
		public void keyPressed(KeyEvent e) {
			//Clear all dots
			if(e.getKeyCode() == KeyEvent.VK_DELETE)
			{
				pointList.clear();
				count = 0;
				repaint();
			}
			//Erase last dot
			else if(pointList.size() != 0 && e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				pointList.remove(pointList.size() - 1);
				count--;
				repaint();
			}
			//Enable random color
			else if(e.getKeyCode() == KeyEvent.VK_CONTROL)
			{
				randomColor = true;
				repaint();
			}
			//Change color to red
			else if (e.getKeyCode() == KeyEvent.VK_R)
			{
				color = Color.RED;
				randomColor = false;
				repaint();
			}
			//Change color to green
			else if (e.getKeyCode() == KeyEvent.VK_G)
			{
				color = Color.GREEN;
				randomColor = false;
				repaint();
			}
			//Change color to blue
			else if (e.getKeyCode() == KeyEvent.VK_B)
			{
				color = Color.BLUE;
				randomColor = false;
				repaint();
			}
			//Change color to white
			else if (e.getKeyCode() == KeyEvent.VK_W)
			{
				color = Color.WHITE;
				randomColor = false;
				repaint();
			}
			//Change color to yellow
			else if (e.getKeyCode() == KeyEvent.VK_Y)
			{
				color = Color.YELLOW;
				randomColor = false;
				repaint();
			}
			//Change color to magenta
			else if (e.getKeyCode() == KeyEvent.VK_M)
			{
				color = Color.MAGENTA;
				randomColor = false;
				repaint();
			}
			//Change color to orange
			else if (e.getKeyCode() == KeyEvent.VK_O)
			{
				color = Color.ORANGE;
				randomColor = false;
				repaint();
			}
			//Change color to pink
			else if (e.getKeyCode() == KeyEvent.VK_P)
			{
				color = Color.PINK;
				randomColor = false;
				repaint();
			}
		}

		public void keyReleased(KeyEvent e) { }
		public void keyTyped(KeyEvent e) { }
	}

	public static void main(String[] args) {

		JFrame dotsFrame = new JFrame("Dots");
		dotsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dotsFrame.getContentPane().add(new Dots());

		dotsFrame.pack();
		dotsFrame.setVisible(true);

	}

}