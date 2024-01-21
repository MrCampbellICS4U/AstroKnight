package culminating;

import java.awt.event.*;

import javax.swing.Timer;

public class Boss extends Enemy implements ActionListener {

	Timer timer;
	int timeSpeed = 1000;
	int time;

	ScrollingPlatformer platformer;

	Boss(int x, int y, int w, int h, Player p, Level c, ScrollingPlatformer platformer) {
		super(x, y, w, h, p, c);

		this.globalX = this.x;
		this.ogGlobalX = this.globalX;
		this.ogX = this.x;
		this.ogY = this.y;
		this.player = p;

		this.platformer = platformer;
		this.currentLevel = c;
		timer = new Timer(timeSpeed, this);

		timer.start();

	}

	void initializeTimer() {
		if (platformer.levelCounter == 4) {
			timer = new Timer(timeSpeed, this);
			timer.start();
		}
	}

	public void actionPerformed(ActionEvent e) { // every frame

		if (platformer.levelCounter == 4) {
			if (player.globalX > 1400 || player.globalX > -1650 && player.globalX < -400) {
				if (timer == null) {
					initializeTimer();
				}

				time++;
			}

		}
	}
	void move() {

		if (player.checkDeath()) {
			player.respawn();
			this.respawn();
			player.globalX = 0;
			removeAttack();
			health = 10;
			timer.restart();
			time = 0;
		}

		if (this.checkDeath()) {
			platformer.level5.removeBoss(this);
			player.scrolling = true;
		}

		// change this to one level before the final level
		if (platformer.levelCounter == 4) {
			if (player.globalX == 1450) {
				player.globalX = -1000;
			}


			if (player.globalX > -1650 && player.globalX < -400) {
				System.out.println(time);
				switch(time) {
				case 1: 
					time = 17;
					break;
				case 2: break;
				case 3: 
					Attack1Warning();
					break;
				case 4: 
					Attack1();
					break;
				case 5: 
					removeAttack();
					break;
				case 6:
					break;
				case 7: 
					Attack2Warning(); 
					break;
				case 8: 
					Attack2();
					break;
				case 9: 
					removeAttack(); 
					break;
				case 10: 
					break;
				case 11: 
					Attack3Warning();
					break;
				case 12: 
					Attack3();
					break;
				case 13: 
					removeAttack();
					break;
				case 14:
					Attack4Warning();
					break;
				case 15:
					Attack4();
					break;
				case 16:
					removeAttack();
					break;
				case 17:
					break;
				case 18:
					Attack5Warning();
					break;
				case 19: 
					Attack5();
					break;
				case 20:
					break;
				case 21:
					removeAttack();
					break;
				case 22:
					break;
				case 23:
					Attack6Warning();
					break;
				case 24:
					Attack6();
					break;
				case 25:
					removeAttack();
				default: 
					time = 0;
					break;
				}
			}
		}
	}
	void Attack1() {
		platformer.level5.addAttack(0, 300, 1280, 100);
		platformer.level5.addAttack(0, 600, 1280, 100);
	}
	void Attack1Warning() {
		platformer.level5.addAttackWarning(0, 300, 1280, 100);
		platformer.level5.addAttackWarning(0, 600, 1280, 100);
	}

	void Attack2() {
		platformer.level5.addAttack(0, 0, 100, 720);
		platformer.level5.addAttack(400, 0, 100, 720);
		platformer.level5.addAttack(800, 0, 100, 720);
		platformer.level5.addAttack(1200, 0, 100, 720);
	}
	void Attack2Warning() {
		platformer.level5.addAttackWarning(0, 0, 100, 720);
		platformer.level5.addAttackWarning(400, 0, 100, 720);
		platformer.level5.addAttackWarning(800, 0, 100, 720);
		platformer.level5.addAttackWarning(1200, 0, 100, 720);
	}

	void Attack3() {
		platformer.level5.addAttack(50, 0, 20, 1280);
		platformer.level5.addAttack(250, 0, 20, 1280);
		platformer.level5.addAttack(450, 0, 20, 1280);
		platformer.level5.addAttack(650, 0, 20, 1280);
		platformer.level5.addAttack(850, 0, 20, 1280);
		platformer.level5.addAttack(1050, 0, 20, 1280);
		platformer.level5.addAttack(1250, 0, 20, 1280);
	}
	void Attack3Warning() {
		platformer.level5.addAttackWarning(50, 0, 20, 1280);
		platformer.level5.addAttackWarning(250, 0, 20, 1280);
		platformer.level5.addAttackWarning(450, 0, 20, 1280);
		platformer.level5.addAttackWarning(650, 0, 20, 1280);
		platformer.level5.addAttackWarning(850, 0, 20, 1280);
		platformer.level5.addAttackWarning(1050, 0, 20, 1280);
		platformer.level5.addAttackWarning(1250, 0, 20, 1280);
	}

	void Attack4() {
		platformer.level5.addAttack(0, 100, 1280, 20);
		platformer.level5.addAttack(0, 300, 1280, 20);
		platformer.level5.addAttack(0, 500, 1280, 20);
		platformer.level5.addAttack(0, 700, 1280, 20);
		platformer.level5.addAttack(0, 900, 1280, 20);
	}
	void Attack4Warning() {
		platformer.level5.addAttackWarning(0, 100, 1280, 20);
		platformer.level5.addAttackWarning(0, 300, 1280, 20);
		platformer.level5.addAttackWarning(0, 500, 1280, 20);
		platformer.level5.addAttackWarning(0, 700, 1280, 20);
		platformer.level5.addAttackWarning(0, 900, 1280, 20);
	}

	void Attack5() {
		platformer.level5.addAttack(0, 100, 50, 50);
		platformer.level5.addAttack(50, 150, 50, 50);
		platformer.level5.addAttack(100, 200, 50, 50);
		platformer.level5.addAttack(150, 250, 50, 50);
		platformer.level5.addAttack(200, 300, 50, 50);
		platformer.level5.addAttack(250, 350, 50, 50);
		platformer.level5.addAttack(300, 400, 50, 50);
		platformer.level5.addAttack(350, 450, 50, 50);
		platformer.level5.addAttack(400, 500, 50, 50);
		platformer.level5.addAttack(450, 550, 50, 50);
		platformer.level5.addAttack(500, 600, 50, 50);
		platformer.level5.addAttack(550, 650, 50, 50);
		platformer.level5.addAttack(600, 700, 50, 50);
		platformer.level5.addAttack(650, 750, 50, 50);
		
		platformer.level5.addAttack(150, 0, 50, 50);
		platformer.level5.addAttack(200, 50, 50, 50);
		platformer.level5.addAttack(250, 100, 50, 50);
		platformer.level5.addAttack(300, 150, 50, 50);
		platformer.level5.addAttack(350, 200, 50, 50);
		platformer.level5.addAttack(400, 250, 50, 50);
		platformer.level5.addAttack(450, 300, 50, 50);
		platformer.level5.addAttack(500, 350, 50, 50);
		platformer.level5.addAttack(550, 400, 50, 50);
		platformer.level5.addAttack(600, 450, 50, 50);
		platformer.level5.addAttack(650, 500, 50, 50);
		platformer.level5.addAttack(700, 550, 50, 50);
		platformer.level5.addAttack(750, 600, 50, 50);
		platformer.level5.addAttack(800, 650, 50, 50);
		platformer.level5.addAttack(850, 700, 50, 50);
		platformer.level5.addAttack(900, 750, 50, 50);
		
		platformer.level5.addAttack(400, 0, 50, 50);
		platformer.level5.addAttack(450, 50, 50, 50);
		platformer.level5.addAttack(500, 100, 50, 50);
		platformer.level5.addAttack(550, 150, 50, 50);
		platformer.level5.addAttack(600, 200, 50, 50);
		platformer.level5.addAttack(650, 250, 50, 50);
		platformer.level5.addAttack(700, 300, 50, 50);
		platformer.level5.addAttack(750, 350, 50, 50);
		platformer.level5.addAttack(800, 400, 50, 50);
		platformer.level5.addAttack(850, 450, 50, 50);
		platformer.level5.addAttack(900, 500, 50, 50);
		platformer.level5.addAttack(950, 550, 50, 50);
		platformer.level5.addAttack(1000, 600, 50, 50);
		platformer.level5.addAttack(1050, 650, 50, 50);
		platformer.level5.addAttack(1100, 700, 50, 50);
		platformer.level5.addAttack(1150, 750, 50, 50);
		
		platformer.level5.addAttack(650, 0, 50, 50);
		platformer.level5.addAttack(600, 50, 50, 50);
		platformer.level5.addAttack(750, 100, 50, 50);
		platformer.level5.addAttack(700, 150, 50, 50);
		platformer.level5.addAttack(850, 200, 50, 50);
		platformer.level5.addAttack(800, 250, 50, 50);
		platformer.level5.addAttack(950, 300, 50, 50);
		platformer.level5.addAttack(900, 350, 50, 50);
		platformer.level5.addAttack(1050, 400, 50, 50);
		platformer.level5.addAttack(1000, 450, 50, 50);
		platformer.level5.addAttack(1150, 500, 50, 50);
		platformer.level5.addAttack(1100, 550, 50, 50);
		platformer.level5.addAttack(1250, 600, 50, 50);
		platformer.level5.addAttack(1200, 650, 50, 50);
		platformer.level5.addAttack(1350, 700, 50, 50);
		platformer.level5.addAttack(1300, 750, 50, 50);
	}
	void Attack5Warning() {
		platformer.level5.addAttackWarning(0, 100, 50, 50);
		platformer.level5.addAttackWarning(50, 150, 50, 50);
		platformer.level5.addAttackWarning(100, 200, 50, 50);
		platformer.level5.addAttackWarning(150, 250, 50, 50);
		platformer.level5.addAttackWarning(200, 300, 50, 50);
		platformer.level5.addAttackWarning(250, 350, 50, 50);
		platformer.level5.addAttackWarning(300, 400, 50, 50);
		platformer.level5.addAttackWarning(350, 450, 50, 50);
		platformer.level5.addAttackWarning(400, 500, 50, 50);
		platformer.level5.addAttackWarning(450, 550, 50, 50);
		platformer.level5.addAttackWarning(500, 600, 50, 50);
		platformer.level5.addAttackWarning(550, 650, 50, 50);
		platformer.level5.addAttackWarning(600, 700, 50, 50);
		platformer.level5.addAttackWarning(650, 750, 50, 50);
		
		platformer.level5.addAttackWarning(150, 0, 50, 50);
		platformer.level5.addAttackWarning(200, 50, 50, 50);
		platformer.level5.addAttackWarning(250, 100, 50, 50);
		platformer.level5.addAttackWarning(300, 150, 50, 50);
		platformer.level5.addAttackWarning(350, 200, 50, 50);
		platformer.level5.addAttackWarning(400, 250, 50, 50);
		platformer.level5.addAttackWarning(450, 300, 50, 50);
		platformer.level5.addAttackWarning(500, 350, 50, 50);
		platformer.level5.addAttackWarning(550, 400, 50, 50);
		platformer.level5.addAttackWarning(600, 450, 50, 50);
		platformer.level5.addAttackWarning(650, 500, 50, 50);
		platformer.level5.addAttackWarning(700, 550, 50, 50);
		platformer.level5.addAttackWarning(750, 600, 50, 50);
		platformer.level5.addAttackWarning(800, 650, 50, 50);
		platformer.level5.addAttackWarning(850, 700, 50, 50);
		platformer.level5.addAttackWarning(900, 750, 50, 50);
		
		platformer.level5.addAttackWarning(400, 0, 50, 50);
		platformer.level5.addAttackWarning(450, 50, 50, 50);
		platformer.level5.addAttackWarning(500, 100, 50, 50);
		platformer.level5.addAttackWarning(550, 150, 50, 50);
		platformer.level5.addAttackWarning(600, 200, 50, 50);
		platformer.level5.addAttackWarning(650, 250, 50, 50);
		platformer.level5.addAttackWarning(700, 300, 50, 50);
		platformer.level5.addAttackWarning(750, 350, 50, 50);
		platformer.level5.addAttackWarning(800, 400, 50, 50);
		platformer.level5.addAttackWarning(850, 450, 50, 50);
		platformer.level5.addAttackWarning(900, 500, 50, 50);
		platformer.level5.addAttackWarning(950, 550, 50, 50);
		platformer.level5.addAttackWarning(1000, 600, 50, 50);
		platformer.level5.addAttackWarning(1050, 650, 50, 50);
		platformer.level5.addAttackWarning(1100, 700, 50, 50);
		platformer.level5.addAttackWarning(1150, 750, 50, 50);
		
		platformer.level5.addAttackWarning(650, 0, 50, 50);
		platformer.level5.addAttackWarning(700, 50, 50, 50);
		platformer.level5.addAttackWarning(750, 100, 50, 50);
		platformer.level5.addAttackWarning(800, 150, 50, 50);
		platformer.level5.addAttackWarning(850, 200, 50, 50);
		platformer.level5.addAttackWarning(900, 250, 50, 50);
		platformer.level5.addAttackWarning(950, 300, 50, 50);
		platformer.level5.addAttackWarning(1000, 350, 50, 50);
		platformer.level5.addAttackWarning(1050, 400, 50, 50);
		platformer.level5.addAttackWarning(1100, 450, 50, 50);
		platformer.level5.addAttackWarning(1150, 500, 50, 50);
		platformer.level5.addAttackWarning(1200, 550, 50, 50);
		platformer.level5.addAttackWarning(1250, 600, 50, 50);
		platformer.level5.addAttackWarning(1300, 650, 50, 50);
		platformer.level5.addAttackWarning(1350, 700, 50, 50);
		platformer.level5.addAttackWarning(1400, 750, 50, 50);
	}
	
	void Attack6(){
		platformer.level5.addAttack(1230, 250, 50, 50);
		platformer.level5.addAttack(1180, 300, 50, 50);
		platformer.level5.addAttack(1130, 350, 50, 50);
		platformer.level5.addAttack(1080, 400, 50, 50);
		platformer.level5.addAttack(1030, 450, 50, 50);
		platformer.level5.addAttack(980, 500, 50, 50);
		platformer.level5.addAttack(930, 550, 50, 50);
		platformer.level5.addAttack(880, 600, 50, 50);
		platformer.level5.addAttack(830, 650, 50, 50);
		platformer.level5.addAttack(780, 700, 50, 50);
		platformer.level5.addAttack(730, 750, 50, 50);
		
		platformer.level5.addAttack(1180, 0, 50, 50);
		platformer.level5.addAttack(1130, 50, 50, 50);
		platformer.level5.addAttack(1080, 100, 50, 50);
		platformer.level5.addAttack(1030, 150, 50, 50);
		platformer.level5.addAttack(980, 200, 50, 50);
		platformer.level5.addAttack(930, 250, 50, 50);
		platformer.level5.addAttack(880, 300, 50, 50);
		platformer.level5.addAttack(830, 350, 50, 50);
		platformer.level5.addAttack(780, 400, 50, 50);
		platformer.level5.addAttack(730, 450, 50, 50);
		platformer.level5.addAttack(680, 500, 50, 50);
		platformer.level5.addAttack(630, 550, 50, 50);
		platformer.level5.addAttack(580, 600, 50, 50);
		platformer.level5.addAttack(530, 650, 50, 50);
		platformer.level5.addAttack(480, 700, 50, 50);
		platformer.level5.addAttack(430, 750, 50, 50);
		
		platformer.level5.addAttack(880, 0, 50, 50);
		platformer.level5.addAttack(830, 50, 50, 50);
		platformer.level5.addAttack(780, 100, 50, 50);
		platformer.level5.addAttack(730, 150, 50, 50);
		platformer.level5.addAttack(680, 200, 50, 50);
		platformer.level5.addAttack(630, 250, 50, 50);
		platformer.level5.addAttack(580, 300, 50, 50);
		platformer.level5.addAttack(530, 350, 50, 50);
		platformer.level5.addAttack(480, 400, 50, 50);
		platformer.level5.addAttack(430, 450, 50, 50);
		platformer.level5.addAttack(380, 500, 50, 50);
		platformer.level5.addAttack(330, 550, 50, 50);
		platformer.level5.addAttack(280, 600, 50, 50);
		platformer.level5.addAttack(230, 650, 50, 50);
		platformer.level5.addAttack(180, 700, 50, 50);
		platformer.level5.addAttack(130, 750, 50, 50);
		
		platformer.level5.addAttackWarning(580, 0, 50, 50);
		platformer.level5.addAttackWarning(530, 50, 50, 50);
		platformer.level5.addAttackWarning(480, 100, 50, 50);
		platformer.level5.addAttackWarning(430, 150, 50, 50);
		platformer.level5.addAttackWarning(380, 200, 50, 50);
		platformer.level5.addAttackWarning(330, 250, 50, 50);
		platformer.level5.addAttackWarning(280, 300, 50, 50);
		platformer.level5.addAttackWarning(230, 350, 50, 50);
		platformer.level5.addAttackWarning(180, 400, 50, 50);
		platformer.level5.addAttackWarning(130, 450, 50, 50);
		platformer.level5.addAttackWarning(80, 500, 50, 50);
		platformer.level5.addAttackWarning(30, 550, 50, 50);
	}
	
	void Attack6Warning() {
		platformer.level5.addAttackWarning(1230, 250, 50, 50);
		platformer.level5.addAttackWarning(1180, 300, 50, 50);
		platformer.level5.addAttackWarning(1130, 350, 50, 50);
		platformer.level5.addAttackWarning(1080, 400, 50, 50);
		platformer.level5.addAttackWarning(1030, 450, 50, 50);
		platformer.level5.addAttackWarning(980, 500, 50, 50);
		platformer.level5.addAttackWarning(930, 550, 50, 50);
		platformer.level5.addAttackWarning(880, 600, 50, 50);
		platformer.level5.addAttackWarning(830, 650, 50, 50);
		platformer.level5.addAttackWarning(780, 700, 50, 50);
		platformer.level5.addAttackWarning(730, 750, 50, 50);
		
		platformer.level5.addAttackWarning(1180, 0, 50, 50);
		platformer.level5.addAttackWarning(1130, 50, 50, 50);
		platformer.level5.addAttackWarning(1080, 100, 50, 50);
		platformer.level5.addAttackWarning(1030, 150, 50, 50);
		platformer.level5.addAttackWarning(980, 200, 50, 50);
		platformer.level5.addAttackWarning(930, 250, 50, 50);
		platformer.level5.addAttackWarning(880, 300, 50, 50);
		platformer.level5.addAttackWarning(830, 350, 50, 50);
		platformer.level5.addAttackWarning(780, 400, 50, 50);
		platformer.level5.addAttackWarning(730, 450, 50, 50);
		platformer.level5.addAttackWarning(680, 500, 50, 50);
		platformer.level5.addAttackWarning(630, 550, 50, 50);
		platformer.level5.addAttackWarning(580, 600, 50, 50);
		platformer.level5.addAttackWarning(530, 650, 50, 50);
		platformer.level5.addAttackWarning(480, 700, 50, 50);
		platformer.level5.addAttackWarning(430, 750, 50, 50);
		
		platformer.level5.addAttackWarning(880, 0, 50, 50);
		platformer.level5.addAttackWarning(830, 50, 50, 50);
		platformer.level5.addAttackWarning(780, 100, 50, 50);
		platformer.level5.addAttackWarning(730, 150, 50, 50);
		platformer.level5.addAttackWarning(680, 200, 50, 50);
		platformer.level5.addAttackWarning(630, 250, 50, 50);
		platformer.level5.addAttackWarning(580, 300, 50, 50);
		platformer.level5.addAttackWarning(530, 350, 50, 50);
		platformer.level5.addAttackWarning(480, 400, 50, 50);
		platformer.level5.addAttackWarning(430, 450, 50, 50);
		platformer.level5.addAttackWarning(380, 500, 50, 50);
		platformer.level5.addAttackWarning(330, 550, 50, 50);
		platformer.level5.addAttackWarning(280, 600, 50, 50);
		platformer.level5.addAttackWarning(230, 650, 50, 50);
		platformer.level5.addAttackWarning(180, 700, 50, 50);
		platformer.level5.addAttackWarning(130, 750, 50, 50);
		
		platformer.level5.addAttackWarning(580, 0, 50, 50);
		platformer.level5.addAttackWarning(530, 50, 50, 50);
		platformer.level5.addAttackWarning(480, 100, 50, 50);
		platformer.level5.addAttackWarning(430, 150, 50, 50);
		platformer.level5.addAttackWarning(380, 200, 50, 50);
		platformer.level5.addAttackWarning(330, 250, 50, 50);
		platformer.level5.addAttackWarning(280, 300, 50, 50);
		platformer.level5.addAttackWarning(230, 350, 50, 50);
		platformer.level5.addAttackWarning(180, 400, 50, 50);
		platformer.level5.addAttackWarning(130, 450, 50, 50);
		platformer.level5.addAttackWarning(80, 500, 50, 50);
		platformer.level5.addAttackWarning(30, 550, 50, 50);
	}

	void removeAttack() {
		platformer.level5.removeAttack();
	}

	void respawn() {
		this.x = this.ogX;
		this.globalX = this.ogGlobalX;
		this.y = this.ogY;
		this.health = this.ogHealth;
	}

}
