package culminating;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AstroKnight implements ActionListener {
	
	//get everythign
	JFrame frame, introFrame, winFrame;
	DrawingPanel panel;
	IntroPanel introPanel;
	WinPanel winPanel;
	JButton introButton;
	
	Timer timer;
	int timeSpeed = 10;
	
	Player player = new Player(50, 650, 50, 50);
	Level level2, level3, level4, bossLevel, theEnd;
	
	boolean up, left, right, space;
	int frameCounter = 0;
	
	int levelCounter;
	ArrayList<Level> levels = new ArrayList<Level>();
	
	
	BufferedImage astronaut, swordLeft, swordRight, bgImage, jump, spike, boss;
	BufferedImage introBackground, startButton, title;
	BufferedImage background;
	BufferedImage coolBackground;
	
	AudioInputStream introMusic;
	
	ImageIcon windowIcon = new ImageIcon("res/icon.png");
	
	int walkingAnimationDelay = 5;  // Adjust this value to control the animation speed
	int jumpingAnimationDelay = 5;  // Adjust this value to control the animation speed

	//The Sprite sheet has 2 rows of sprites, each consisting of 14 frames.
	//Each sprite is 16x16 pixels.
	int astroSpriteW = 16;
	int astroSpriteH = 16;
	int astroSpriteRows = 2;
	int astroSpriteCols = 14;
	int currentAstroSprite = 0;

	//The Sprite sheet has 2 rows of sprites, each consisting of 14 frames.
	//Each sprite is 16x16 pixels.
	int jumpSpriteW = 16;
	int jumpSpriteH = 16;
	int jumpSpriteRows = 1;
	int jumpSpriteCols = 6;
	int currentJumpSprite = 0;

	//The Sprite sheet has 1 rows of sprites, each consisting of 12 frames.
	//Each sprite is 64x64 pixels.
	int swingSpriteW = 64;
	int swingSpriteH = 64;
	int swingSpriteRows = 1;
	int swingSpriteCols = 12;
	int currentSwingSprite = 0;
	
	//The Sprite sheet has 1 rows of sprites, each consisting of 4 frames.
	//Each sprite is 16x16 pixels.
	int spikeSpriteW = 16;
	int spikeSpriteH = 16;
	int spikeSpriteRows = 1;
	int spikeSpriteCols = 4;
	int currentSpikeSprite = 0;
	
	Color warningColor;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AstroKnight();
			}
		});
	}
	
	AstroKnight() { //make evrything appear
		introFrame = new JFrame();
		introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		introFrame.setTitle("Welcome to Astro Knight");
		introFrame.setResizable(false);

		introPanel = new IntroPanel();
		introPanel.setPreferredSize(new Dimension(1280, 720));
		introPanel.setBackground(Color.black);
		introPanel.setLayout(null);
		introPanel.add(introButton);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new ArrowKeyListener());
		frame.setResizable(false);
		
		frame.setIconImage(windowIcon.getImage());
		
		panel = new DrawingPanel();
		panel.setPreferredSize(new Dimension(1280, 720)); 
		panel.setBackground(Color.black);
		
		winFrame = new JFrame();
		winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winFrame.setTitle("YOU WON!!!!!");
		winFrame.setResizable(false);

		winPanel = new WinPanel();
		winPanel.setPreferredSize(new Dimension(1280, 720));
		winPanel.setBackground(Color.black);
		
		level2 = new Level();
		
		level2.addPlatform(-4, 704, 196, 20);
		level2.addPlatform(388, 660, 189, 65);
		level2.addPlatform(580, 520, 188, 34);
		level2.addPlatform(399, 380, 161, 32);
		level2.addPlatform(592, 230, 219, 34);
		level2.addPlatform(938, 232, 106, 104);
		level2.addPlatform(1143, -6, 137, 247);
		level2.addPlatform(1033, 564, 122, 162);
		level2.addPlatform(1307, 564, 122, 162);
		level2.addPlatform(1600, 470, 300, 30);
		level2.addPlatform(2100, 400, 200, 880);
		level2.addPlatform(2100, 0, 200, 200);
		
		level2.addHazard(576, 680, 467, 50);
		level2.addHazard(1144, 612, 163, 113);
		
		level2.setScrollStop();
		level2.addHazard(0, 750, 1280 + level2.scrollStop, 50);
		
		level3 = new Level();
		
		level3.addPlatform(0, 700, 150, 30);
		level3.addPlatform(280, 590, 500, 30);
		level3.addPlatform(280, 570, 120, 30);
		level3.addPlatform(490, 570, 110, 30);
		level3.addPlatform(700, 570, 80, 30);
		level3.addPlatform(280, 390, 500, 80);
		level3.addPlatform(710, 0, 70, 410);
		level3.addPlatform(280, 0, 70, 400);
		level3.addPlatform(980, 530, 130, 30);
		level3.addPlatform(1260, 480, 130, 30);
		level3.addPlatform(1535, 390, 190, 340);
		level3.addPlatform(1705, 530, 100, 200);
		level3.addPlatform(1800, 440, 150, 290);
		level3.addPlatform(1970, 300, 210, 20);
		level3.addPlatform(1830, 180, 130, 20);
		level3.addPlatform(1980, 80, 490, 20);
		
		level3.addPlatform(2635, 480, 280, 250);
		level3.addPlatform(3090, 410, 390, 320);
		level3.addPlatform(3090, 0, 390, 230);
		level3.addPlatform(3680, 360, 250, 360);
		level3.addPlatform(3680, -10, 250, 240);

		level3.addHazard(390, 580, 110, 20);
		level3.addHazard(590, 580, 120, 20);
		level3.addHazard(1715, 470, 90, 70);
		level3.addHazard(3180, 390, 80, 40);
		level3.addHazard(3360, 390, 80, 30);
		
		
		level3.addEnemy(new UFO(1020, 250, 50, 50, player));
		level3.addEnemy(new UFO(1300, 230, 50, 50, player));
		
		
		level3.addEnemy(new Turret(2220, 230, 50, 50, player));
		level3.addEnemy(new Alien(2220, 40, 40, 40, 1980, 2470, player));
		
		level3.setScrollStop();
		
		level3.addHazard(0, 750, 1280 + level3.scrollStop, 50);
		
		level4 = new Level();
		level4.addPlatform(0, 700, 110, 20);
		level4.addPlatform(210, 570, 370, 30);
		level4.addPlatform(710, 540, 350, 30);
		level4.addPlatform(1030, 120, 30, 430);
		level4.addPlatform(810, 0, 30, 440);
		level4.addPlatform(830, 410, 100, 30);
		level4.addPlatform(940, 260, 100, 30);
		level4.addPlatform(830, 110, 90, 20);
		level4.addPlatform(1180, 240, 250, 30);
		level4.addPlatform(1480, 500, 560, 240);
		level4.addPlatform(2210, 440, 120, 20);
		level4.addPlatform(2450, 370, 110, 20);
		level4.addPlatform(2110, 610, 570, 30);
		level4.addPlatform(2705, 330, 190, 50);
		level4.addPlatform(2705, -10, 190, 220);
		level4.addPlatform(2745, 540, 200, 30);
		level4.addPlatform(3060, 450, 190, 30);
		level4.addPlatform(3370, 390, 220, 340);
		level4.addPlatform(3370, -10, 220, 220);

		level4.addHazard(1550, 470, 80, 30);
		level4.addHazard(1870, 470, 70, 30);
		level4.addHazard(2755, 320, 40, 10);
		
		level4.setScrollStop();
		
		level4.addHazard(0, 750, 1280 + level4.scrollStop, 50);
		
		level4.addEnemy(new Alien(280, 530, 40, 40, 210, 580, player));
		level4.addEnemy(new Alien(1200, 200, 40, 40, 1180, 1430, player));
		level4.addEnemy(new Turret(2110, 560, 50, 50, player));
		level4.addEnemy(new Turret(2630, 560, 50, 50, player));
		
		
		bossLevel = new Level();

		// walking to the stage platform
		bossLevel.addPlatform(0, 0, 1, 1280);
		bossLevel.addPlatform(0, 680, 750, 50);
		bossLevel.addPlatform(650, 0, 150, 600);

		// arena
		bossLevel.addPlatform(600, 680, 2000, 50); // base platform
		bossLevel.addPlatform(1000, 540, 300, 30);
		bossLevel.addPlatform(1600, 540, 300, 30);
		bossLevel.addPlatform(810, 400, 100, 30);
		bossLevel.addPlatform(1100, 400, 100, 30);
		bossLevel.addPlatform(1300, 400, 100, 30);
		bossLevel.addPlatform(1500, 400, 100, 30);
		bossLevel.addPlatform(1700, 400, 100, 30);
		bossLevel.addPlatform(2000, 400, 100, 30);

		// ending platform
		bossLevel.addPlatform(2150, 680, 750, 50);
		bossLevel.addPlatform(2100, 0, 150, 600);
		
		//removed after beating boss
		bossLevel.addPlatform(2050, 0, 50, 720);

		bossLevel.setScrollStop();	
		
		bossLevel.addBoss(new Boss(1000, 0, 900, 300, player, this));
		warningColor = new Color(250, 230, 0, 100);
		
		theEnd = new Level();
		
		player.currentLevel = level2;
		
		levels.add(level2);
		levels.add(level3);
		levels.add(level4);
		levels.add(bossLevel);
		levels.add(theEnd);
		
		astronaut = loadImage("/Astronaut.png");
		jump = loadImage("/Jump.png");
		swordLeft = loadImage("/SwingLeft.png");
		swordRight = loadImage("/SwingRight.png");
		bgImage = loadImage("/spaceBackground.png");
		spike = loadImage("/Spike.png");
		boss = loadImage("/Boss.png");

		introBackground = loadImage("/introBackground.png");
		startButton = loadImage("/startButton.png");
		title = loadImage("/ASTROKNIGHT.png");
		background = loadImage("/spaceBackground.png");
		coolBackground = loadImage("/coolBackground.gif");
		
		PlayMusic("/Hollow Knight OST - Enter Hallownest.wav");
		
		levelCounter = levels.indexOf(player.currentLevel);
		
		timer = new Timer(timeSpeed, this);
		
		introFrame.setContentPane(introPanel);
		introFrame.pack();
		introFrame.setLocationRelativeTo(null);
		introFrame.setVisible(true);

		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);

		winFrame.setContentPane(winPanel);
		winFrame.pack();
		winFrame.setLocationRelativeTo(null);
		winFrame.setVisible(false);
		
	}
	
	public void actionPerformed(ActionEvent e) {//every frame
		
		String eventName = e.getActionCommand();

		if (("".equals(eventName))) {
			introFrame.setVisible(false);
			frame.setVisible(true);

			timer.start();
		}
		
		player.move();
		player.gravity();
		player.jump();
		player.attack();
		
		player.currentLevel.checkEnemies();
		player.currentLevel.checkBoss();
		
		checkWinner();
		
		if (player.checkWin()) {
			levelCounter++;
			player.currentLevel = levels.get(levelCounter);
			player.respawn();
		}
		if (player.checkDeath()) player.respawn();
		panel.repaint();
	}
	
	private class IntroPanel extends JPanel {
		public void paintComponent(Graphics g) { // paint
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.drawImage(introBackground, 0, 0, 1280, 720, null);
			g2.drawImage(title, 240, 100, 800, 300, null);
			g2.drawImage(startButton, 565, 425, 150, 80, null);

			g2.setFont(new Font("Times New Romans", Font.PLAIN, 20));
			g2.setColor(Color.WHITE);
			g2.drawString("Use the left/up/right arrow keys to move, and 'space' to jump. Good Luck.", 320, 400);

		}

		IntroPanel() {
			introButton = new JButton("");
			introButton.setActionCommand("");
			introButton.addActionListener(AstroKnight.this);

			introButton.setOpaque(false);
			introButton.setContentAreaFilled(false);
			introButton.setBorderPainted(false);

			// Set the preferred size to make the button bigger
			introButton.setBounds(565, 425, 150, 80);
		}
	}
	
	private class WinPanel extends JPanel {
		public void paintComponent(Graphics g) { // paint
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.drawImage(coolBackground, 0, 0, 1280, 720, null);

			g2.setFont(new Font("Times New Romans", Font.PLAIN, 100));
			g2.setColor(Color.WHITE);
			g2.drawString("YOU WON LET GO", 100, 400);

		}

	}
	
	private class DrawingPanel extends JPanel {

		public void paintComponent(Graphics g) { //paint
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.drawImage(bgImage, 0, 0, 1280, 720, null);
			
			for (Rectangle r: player.currentLevel.getHazards()) {

			    // Calculate the position and size of the current spike sprite
			    int spikeSpriteX = (currentSpikeSprite % spikeSpriteCols) * spikeSpriteW;
			    int spikeSpriteY = (currentSpikeSprite / spikeSpriteCols) * spikeSpriteH;

			    // Draw the current spike sprite for the current hazard
			    g2.drawImage(spike, r.x, r.y, r.x + r.width, r.y + r.height,
			            spikeSpriteX, spikeSpriteY, spikeSpriteX + spikeSpriteW, spikeSpriteY + spikeSpriteH, null);
			}
			
			g2.setColor(Color.white);
			for (Rectangle r: player.currentLevel.getPlatforms()) g2.fillRect(r.x, r.y, r.width, r.height); 

		
			

			if (player.right) {
				frameCounter++;

				

				// Update the sprite index for walking animation only if the frameCounter exceeds the delay
				if (frameCounter >= walkingAnimationDelay) {
					currentAstroSprite++;
					frameCounter = 0;
				}

				if (currentAstroSprite >= 1 * astroSpriteCols) {
					currentAstroSprite = 0;
				}
			}

			// Update the sprite index for walking animation
			if (player.left) {
				frameCounter++;
				

				// Update the sprite index for walking animation only if the frameCounter exceeds the delay
				if (frameCounter >= walkingAnimationDelay) {
					currentAstroSprite++;
					frameCounter = 0;
				}

				if (currentAstroSprite >= 2 * astroSpriteCols || currentAstroSprite < astroSpriteCols) {
					currentAstroSprite = astroSpriteCols;
				}
			} 

			// Update the sprite index for walking animation
		    if (player.up && !player.jumping) {
		        player.jumping = true;
		        currentJumpSprite = 0;
		    }

		    // Update the jump animation only if the player is currently jumping
		    if (player.jumping) {
		        frameCounter++;

		        // Update the sprite index for jumping animation only if the frameCounter exceeds the delay
		        

		        if (frameCounter >= jumpingAnimationDelay) {
		            currentJumpSprite++;
		            frameCounter = 0;
		        }

		        // If the jump animation completes, stop jumping
		        if (currentJumpSprite >= 1 * jumpSpriteCols) {
		            currentJumpSprite = 0;
		            player.jumping = false;
		        }
		    }

			// idle left or right
			if (!player.left && !player.right) { 

				if (player.facingRight) currentAstroSprite = 0;
				else currentAstroSprite = 14;

			}

			// Calculate the position and size of the current sprite
			int AstrospriteX = (currentAstroSprite % astroSpriteCols) * astroSpriteW; 
			int AstrospriteY = (currentAstroSprite / astroSpriteCols) * astroSpriteH;
			// Calculate the position and size of the current sprite
			int jumpSpriteX = (currentJumpSprite % jumpSpriteCols) * jumpSpriteW; 
			int jumpSpriteY = (currentJumpSprite / jumpSpriteCols) * jumpSpriteH;

			/*
			 * player.x + player.width, player.y + player.height: bottom-right corner of player's coordinate and top-left corner to specify the size of destiantion rectangle (rectangle size)
			 * spriteX, spriteY: The top-left coordinates of the first image of the sprite sheet. Coordinates determine which part of the sprite sheet will be drawn. (which frame)
			 * spriteX + spriteW, spriteY + spriteH: The bottom-right coordinates of the source rectangle within the sprite sheet. With the top-left coordinates, this specifies the size of the source rectangle. (image size)
			 */

			if (!player.jumping) {
				g2.drawImage(astronaut, player.x, player.y, player.x + player.width, player.y + player.height,
						AstrospriteX, AstrospriteY, AstrospriteX + astroSpriteW, AstrospriteY + astroSpriteH, null);
			}
			if (player.jumping) {
				g2.drawImage(jump, player.x, player.y, player.x + player.width, player.y + player.height,
						jumpSpriteX, jumpSpriteY, jumpSpriteX + jumpSpriteW, jumpSpriteY + jumpSpriteH, null);
			}


			// Add a new variable to control the swinging animation delay
			int swingingAnimationDelay = 0; 

			if (player.attacking) {
				frameCounter++;

				if (frameCounter >= swingingAnimationDelay) {
					currentSwingSprite += 2;
					frameCounter = 0;
				}

				if (currentSwingSprite >= swingSpriteRows * swingSpriteCols) {
					currentSwingSprite = 0;
				}

				int swordSpriteX = (currentSwingSprite % swingSpriteCols) * swingSpriteW;
				int swordSpriteY = (currentSwingSprite / swingSpriteCols) * swingSpriteH;

				if (player.facingRight) {
					g2.drawImage(swordRight, player.swordHitbox.x, player.swordHitbox.y,
							player.swordHitbox.x + player.swordHitbox.width,
							player.swordHitbox.y + player.swordHitbox.height,
							swordSpriteX, swordSpriteY, swordSpriteX + swingSpriteW, swordSpriteY + swingSpriteH, null);
				} else if (!player.facingRight) {
					g2.drawImage(swordLeft, player.swordHitbox.x, player.swordHitbox.y,
							player.swordHitbox.x + player.swordHitbox.width,
							player.swordHitbox.y + player.swordHitbox.height,
							swordSpriteX, swordSpriteY, swordSpriteX + swingSpriteW, swordSpriteY + swingSpriteH, null);
				}
			} else {
				currentSwingSprite = 0;
			}


			
			
			
			g2.setColor(Color.green);
			for (Enemy e: player.currentLevel.getEnemies()) g2.fillRect(e.x, e.y, e.width, e.height);
			
			g2.setColor(Color.red);
			for (Boss b: player.currentLevel.getBoss()) {
				g2.drawImage(boss, b.x, b.y, b.width, b.height, null);
				g2.drawRect(b.x, b.y, b.width, b.height);
			}
			
			g2.setColor(warningColor);
			for (Rectangle r: player.currentLevel.getWarning()) g2.fillRect(r.x, r.y, r.width, r.height);
			g2.setColor(Color.red);
			for (Rectangle r: player.currentLevel.getAttacks()) g2.fillRect(r.x, r.y, r.width, r.height);
			
			

			g2.drawString(String.valueOf(player.y), 50, 50);
			g2.drawString(String.valueOf(player.globalX), 80, 50);
			g2.drawString(String.valueOf(player.currentLevel.getEnemies().size()), 120, 50);

			if (player.checkCollision()) g2.fillRect(100, 100, 100, 100);

		}
	}
	
	public class ArrowKeyListener extends KeyAdapter {
		
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
			if (e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
			if (e.getKeyCode() == KeyEvent.VK_SPACE) player.space = false;
		}
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
			if (e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
			if (e.getKeyCode() == KeyEvent.VK_SPACE) player.space = true;
		}
	}
	
	BufferedImage loadImage(String filename) {
		BufferedImage image = null;	
		java.net.URL imageURL = this.getClass().getResource(filename);
		if (imageURL != null) {
			try {
				image = ImageIO.read(imageURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { 
			JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}
	
	public static void PlayMusic (String location) {
        AudioInputStream audioIn;
        try {
            audioIn = AudioSystem.getAudioInputStream(AstroKnight.class.getResourceAsStream(location));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException  e) {
            e.printStackTrace();
        }
    }
	
	void checkWinner(){
		if (player.currentLevel == theEnd) {
			timer.stop();
			frame.setVisible(false);
			winFrame.setVisible(true);
		}
	}

}