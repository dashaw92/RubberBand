package me.daniel.rubber;

import java.awt.image.BufferedImage;

public class Node
{
	private boolean selected = false;
	public int x, y;
//	private Color color;
	private BufferedImage icon;

	public Node(int x, int y, BufferedImage icon) {
		this.x = x;
		this.y = y;
		this.icon = icon;
	}
	
	public BufferedImage getIcon() {
		return icon;
	}
	
//	public Color getColor() {
//		return color;
//	}
//	
//	public void setColor(Color c) {
//		this.color = c;
//	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
}