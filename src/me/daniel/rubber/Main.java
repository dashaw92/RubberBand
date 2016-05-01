package me.daniel.rubber;

import java.awt.Cursor;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class Main
{
	
	private RubberBand rb;
	
	public static void main(String[] args) {
		new Main();
	}
	
	@SuppressWarnings("deprecation")
	public Main() {
		JFrame frame = new JFrame("RubberBand test");
		frame.setSize(1000, 700);
		frame.setResizable(true);
		frame.setLayout(null);
		frame.setCursor(Cursor.CROSSHAIR_CURSOR);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				rb.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			}

			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentShown(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {}
			
		});
		rb = new RubberBand(frame.getWidth(), frame.getHeight());
		rb.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.addMouseListener(rb);
		frame.addMouseMotionListener(rb);
		frame.add(rb);
		frame.setVisible(true);
	}

}	