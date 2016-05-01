package me.daniel.rubber;

public class Node
{
	private boolean selected = false;
	public int x, y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
}