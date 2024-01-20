package culminating;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

public class PlatformMaker implements ActionListener {

	JFrame frame;
	DrawingPanel dpanel;
	JPanel bpanel;
	JPanel main;
	
	JButton erase;
	JButton hazard;
	JButton save;
	
	Timer timer;
	int timeSpeed = 10;
	int scrollSpeed = 4;
	
	int mx, my, mxOg, myOg;
	
	int currentScroll = 0;
	
	boolean drawing = false;
	boolean erasing = false;
	boolean drawingHazard = false;
	
	boolean left, right;
	
	Placement toErase;
	
	ArrayList<Placement> level = new ArrayList<Placement>();
	ArrayList<Placement> hazards = new ArrayList<Placement>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PlatformMaker();
			}
		});
	}
	
	PlatformMaker() {
		frame = new JFrame("naeme");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dpanel = new DrawingPanel();
		dpanel.setPreferredSize(new Dimension(1280, 720));
		dpanel.setBackground(Color.black);
		
		bpanel = new JPanel();
		
		erase = new JButton("Erase");
		erase.addActionListener(this);
		hazard = new JButton("Hazard");
		hazard.addActionListener(this);
		save = new JButton("Save");
		save.addActionListener(this);
		
		bpanel.add(erase);
		bpanel.add(hazard);
		bpanel.add(save);
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
		
		main.add(dpanel);
		main.add(bpanel);
		
		
		
		frame.addMouseMotionListener(new MousePosListener());
		frame.addMouseListener(new MouseClickListener());
		frame.addKeyListener(new ArrowKeyListener());
		
		frame.setFocusable(true);
		frame.requestFocus();
		frame.setContentPane(main);
		frame.pack();
		frame.setVisible(true);
		
		timer = new Timer(timeSpeed, this);
		timer.start();
		
	}
	
	private class MousePosListener implements MouseMotionListener {

		@Override
		public void mouseDragged(java.awt.event.MouseEvent e) {
			mx = e.getX() - 18;
			my = e.getY() - 41;
		}

		@Override
		public void mouseMoved(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			mx = e.getX() - 18;
			my = e.getY() - 41;
		}
	}
	
	private class MouseClickListener extends MouseAdapter {

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			if (erasing && level.size() > 0) {
				for (Placement r: level) {
					if (r.contains(mx, my)) toErase = r;
				}
			level.remove(toErase);
			}
			else if (erasing && hazards.size() > 0) {
				for (Placement r: hazards) {
					if (r.contains(mx, my)) toErase = r;
				}
			hazards.remove(toErase);
			}
			else {
				drawing = true;
				mxOg = e.getX() - 18;
				myOg = e.getY() - 14;
			}
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
			if (drawingHazard) {
				if (drawing) {
					if (mx > mxOg) {
						if (my > myOg) hazards.add(new Placement(mxOg, myOg, mx - mxOg, my - myOg));	//down right
						else hazards.add(new Placement(mxOg, my, mx - mxOg, myOg - my)); //up right
					}
					else {
						if (my > myOg) hazards.add(new Placement(mx, myOg, mxOg - mx, my - myOg)); //down left
						else hazards.add(new Placement(mx, my, mxOg - mx, myOg - my)); //up left
					}
				}
				drawing = false;
			}
			
			else {
				if (drawing) {
					if (mx > mxOg) {
						if (my > myOg) level.add(new Placement(mxOg, myOg, mx - mxOg, my - myOg));	//down right
						else level.add(new Placement(mxOg, my, mx - mxOg, myOg - my)); //up right
					}
					else {
						if (my > myOg) level.add(new Placement(mx, myOg, mxOg - mx, my - myOg)); //down left
						else level.add(new Placement(mx, my, mxOg - mx, myOg - my)); //up left
					}
				}
				drawing = false;
			}
			
		}

		
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == erase) {
			if (erasing) {
				erasing = false;
				erase.setText("Erase");
			}
			
			else {
				erasing = true;
				erase.setText("Draw");
			}
			frame.requestFocus();
			
		}
		
		if (event.getSource() == save) {
			for (Placement r: level) {
				System.out.println("level2.addPlatform(" + r.globalX + ", " + r.y + ", " + r.width + ", "
						 + r.height + ");");
			}
			System.out.println();
			for (Placement r: hazards) {
				System.out.println("level2.addHazard(" + r.globalX + ", " + r.y + ", " + r.width + ", "
						 + r.height + ");");
			}
			frame.requestFocus();
		}
		
		if (event.getSource() == hazard) {
			if (drawingHazard) {
				drawingHazard = false;
				hazard.setText("Hazard");
			}
			
			else {
				drawingHazard = true;
				hazard.setText("Platform");
			}
			frame.requestFocus();
		}
		
		if (left && currentScroll > scrollSpeed) {
			currentScroll -= 4;
			for (Placement r: level) r.x += scrollSpeed;
			for (Placement r: hazards) r.x += scrollSpeed;
		}
		if (right) {
			currentScroll += 4;
			for (Placement r: level) r.x -= scrollSpeed;
			for (Placement r: hazards) r.x -= scrollSpeed;
		}
			
		
		dpanel.repaint();
	}
	
	private class ArrowKeyListener extends KeyAdapter {
		
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			//if (!drawing) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
				
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
			//}
		}

		
	}
	
	private class Placement extends Rectangle {
		int myScroll;
		int globalX;
		
		Placement(int x, int y, int w, int h) {
			super(x, y, w, h);
			
			this.globalX = this.x + currentScroll;
		}
	}
	
	private class DrawingPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2.setColor(Color.white);
			
			g2.drawString("mx: " + String.valueOf(mx), 20, 20);
			g2.drawString("my: " + String.valueOf(my), 20, 40);
			g2.drawString("mxOg: " + String.valueOf(mxOg), 20, 60);
			g2.drawString("myOg: " + String.valueOf(myOg), 20, 80);
			g2.drawString("current scroll: " + String.valueOf(currentScroll), 20, 100);
			
			//if (drawing) g2.drawRect(mxOg, myOg, Math.abs(mx - mxOg), Math.abs(my - myOg));
			
			if (drawing) {
				if (mx > mxOg) {
					if (my > myOg) g2.drawRect(mxOg, myOg, mx - mxOg, my - myOg);	//down right
					else g2.drawRect(mxOg, my, mx - mxOg, myOg - my); //up right
				}
				else {
					if (my > myOg) g2.drawRect(mx, myOg, mxOg - mx, my - myOg); //down left
					else g2.drawRect(mx, my, mxOg - mx, myOg - my); //up left
				}
			}
			
			for (Placement r: level) g2.fillRect(r.x, r.y, r.width, r.height);
			g2.setColor(Color.red);
			for (Placement r: hazards) g2.fillRect(r.x, r.y, r.width, r.height);
			
			
		}
	}

}