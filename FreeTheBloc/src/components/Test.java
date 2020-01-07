package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame implements MouseMotionListener{
	Point point;
	
	public Test() {
		init();
	}
	
	public static void main(String args[]) {
		Test t = new Test();
		
	}
	
	public void init() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,500);
		f.addMouseMotionListener(this);
		f.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(point.x, point.y, 15,15);	
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("k");
		point.setLocation(e.getX(),e.getY());
		repaint();
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
