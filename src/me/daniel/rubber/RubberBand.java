package me.daniel.rubber;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RubberBand extends JPanel implements MouseListener, MouseMotionListener 
{
	private int lastX = -1, lastY = -1, curX = -1, curY = -1;
	private Color border = new Color(125,125,125);
	private Color inside = new Color(80,80,80,100);
	private boolean haveMouse = false;
	
	private Map<Node, Rectangle> nodes = new HashMap<>();
	private BufferedImage icon;
	private boolean useIcon = true;
	
	private Rectangle theBox;
	
	public RubberBand(int w, int h) {
		setSize(w, h); //ensure proper size
		//try to load the folder icon, otherwise flag useIcon as false.
		try {
			icon = ImageIO.read(getClass().getResource("folder.png"));
		} catch(IOException e) {
			useIcon = false;
		}
		//create all the nodes
		Random r = new Random();
		int rand = r.nextInt(255)+10;
		for(int i = 0; i < rand; i++) {
			int x = r.nextInt(getWidth()-20) + 10;
			int y = r.nextInt(getHeight()-20) + 10;
			getCoords(x, y);
			nodes.put(new Node(x, y), new Rectangle(x-4, y-4, x+16, y+16));
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		//clear the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(Node n : nodes.keySet()) {
			//draw the node, if icon failed to load, we'll just draw a circle instead.
			if(useIcon) {
				g.drawImage(icon, n.x, n.y, 16, 16, null);
			} else {
				g.drawOval(n.x, n.y, n.x+16, n.y+16);
			}
			//the node is selected, draw the special stuff.
			if(n.isSelected()) {
				g.setColor(Color.BLUE);
				g.drawRect(n.x-4, n.y-4, 20, 20);
				g.setColor(new Color(0, 0, 125, 80));
				g.fillRect(n.x-3, n.y-3, 19, 19);
			}
		}
		if(haveMouse) {
			//draw the rubberband
			g.setColor(border);
			g.drawRect(lastX, lastY, curX, curY);
			g.setColor(inside);
			g.fillRect(lastX+1, lastY+1, curX-1, curY-1);
		}
		try {
			Thread.sleep(10); //don't eat the cpu
		} catch(InterruptedException e) {}
		repaint();
	}
	
	private String getCoords(int x, int y) {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(haveMouse) {
			curX = e.getX()-lastX-8; //subtraction because wonky getX() and getY() :(
			curY = e.getY()-lastY-30;
//			System.out.println(getCoords(curX, curY));
			theBox = new Rectangle(lastX, lastY, curX, curY); //create the selection rectangle to use in checking interestions
			for(Node n : nodes.keySet()) {
				if(theBox.intersects(nodes.get(n))) n.setSelected(true);
				else n.setSelected(false); //clear selected
			}
		} else {
			haveMouse = true;
			lastX = e.getX();
			lastY = e.getY();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(Node n : nodes.keySet()) n.setSelected(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		haveMouse = false;
		lastX = lastY = curX = curY = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
}
