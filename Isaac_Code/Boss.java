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
				case 1: break;
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
					break;
				case 20:
					Attack5();
					break;
				case 21:
					removeAttack();
					time = 3;
					break;
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
		platformer.level5.addAttack(0, 0, 0, 0);
	}
	void Attack5Warning() {
		platformer.level5.addAttack(0, 0, 0, 0);
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
