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

		System.out.println(time);
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
			player.globalX = 0;
			removeAttack();
			timer.restart();
			time = 0;

			this.x = 1000;
			this.y = 1000;
			this.width = 1000;
			this.height = 1000;
		}

		// change this to one level before the final level
		if (platformer.levelCounter == 4) {
			if (player.globalX == 1450) {
				player.globalX = -1000;
			}


			if (player.globalX > -1650 && player.globalX < -400) {

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
				default:
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
		platformer.level5.addAttack(200, 200, 800, 200);
		platformer.level5.addAttack(200, 400, 500, 500);
	}
	void Attack2Warning() {
		platformer.level5.addAttackWarning(200, 200, 800, 200);
		platformer.level5.addAttackWarning(200, 400, 500, 500);
	}

	void Attack3() {

	}

	void removeAttack() {
		platformer.level5.removeAttack();
	}

	void checkHit() {
		super.checkHit();
	}

	void respawn() {
		this.x = this.ogX;
		this.globalX = this.ogGlobalX;
		this.y = this.ogY;
		this.health = this.ogHealth;
	}

}
