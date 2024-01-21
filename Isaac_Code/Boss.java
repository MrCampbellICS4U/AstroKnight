package culminating;

import java.awt.event.*;

import javax.swing.Timer;

public class Boss extends Enemy implements ActionListener {

	Timer timer;
	int timeSpeed = 800;
	int time;

	AstroKnight platformer;

	boolean fightStarted = false;
	boolean dead = false;

	Boss(int x, int y, int w, int h, Player p, AstroKnight s) {
		super(x, y, w, h, p);

		this.globalX = this.x;
		this.ogGlobalX = this.globalX;
		this.ogX = this.x;
		this.ogY = this.y;
		this.player = p;

		this.health = this.ogHealth = 10;

		this.platformer = s;


		timer = new Timer(timeSpeed, this);

		timer.start();

	}

	void initializeTimer() {
		if (platformer.levelCounter == 3) {
			timer = new Timer(timeSpeed, this);
			timer.start();
		}
	}

	public void actionPerformed(ActionEvent e) { // every frame

		if (platformer.levelCounter == 3) {
			if (player.globalX > 1400 || player.globalX > -1650 && player.globalX < -400) {
				if (timer == null) {
					initializeTimer();

				}

				time++;
				fightStarted = true;
			}

		}
	}
	public void action() {

		if (player.checkDeath()) {
			player.respawn();
			this.respawn();
			player.globalX = player.x;
			removeAttack();
			health = ogHealth;
			fightStarted = false;
			timer.restart();
			time = 0;
		}

		if (this.checkDeath()) {
			dead = true;
			platformer.bossLevel.removeBoss(this);
			removeAttack();
			platformer.bossLevel.getPlatforms().remove(platformer.bossLevel.getPlatforms().size() - 1);
			player.scrolling = true;
			player.globalX = player.x + 800;
		}

		// change this to one level before the final level
		if (platformer.levelCounter == 3 && !dead) {
			if (player.globalX >= 1450) {
				player.globalX = -1000;

			}


			if (player.globalX > -1650 && player.globalX < -400) {

				switch(time) {
				case 1:
					break;
				case 2: 
					break;
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
					time = 2;
					break;
				}
			}
		}
	}

	public void checkHit() {
		if (fightStarted) {
			if (hitTimer < 10) {
				hitTimer++;
				canBeHit = false;
			}
			else {
				canBeHit = true;
			}

			if (this.intersects(this.player.swordHitbox) && this.player.attacking && this.canBeHit) {
				this.health--;
				this.hitTimer = 0;

				if (this.player.facingRight) this.player.knockbackLeft = true;
				else this.player.knockbackRight = true;

				this.player.knockbackTimer = 0;
			}
		}
	}

	boolean checkDeath() {
		if (this.health <= 0) return true;
		return false;
	}

	void Attack1() {
		if (platformer.bossLevel.getAttacks().size() == 0) {
			platformer.bossLevel.addAttack(0, 300, 1280, 100);
			platformer.bossLevel.addAttack(0, 600, 1280, 100);
		}
	}
	void Attack1Warning() {
		if (platformer.bossLevel.getWarning().size() == 0) {
			platformer.bossLevel.addAttackWarning(0, 300, 1280, 100);
			platformer.bossLevel.addAttackWarning(0, 600, 1280, 100);
		}
	}

	void Attack2() {
		if (platformer.bossLevel.getAttacks().size() == 0) {
			platformer.bossLevel.addAttack(0, 0, 100, 720);
			platformer.bossLevel.addAttack(400, 0, 100, 720);
			platformer.bossLevel.addAttack(800, 0, 100, 720);
			platformer.bossLevel.addAttack(1200, 0, 100, 720);
		}
	}
	void Attack2Warning() {
		if (platformer.bossLevel.getWarning().size() == 0) {
			platformer.bossLevel.addAttackWarning(0, 0, 100, 720);
			platformer.bossLevel.addAttackWarning(400, 0, 100, 720);
			platformer.bossLevel.addAttackWarning(800, 0, 100, 720);
			platformer.bossLevel.addAttackWarning(1200, 0, 100, 720);
		}
	}

	void Attack3() {
		if (platformer.bossLevel.getAttacks().size() == 0) {
			platformer.bossLevel.addAttack(50, 0, 20, 1280);
			platformer.bossLevel.addAttack(250, 0, 20, 1280);
			platformer.bossLevel.addAttack(450, 0, 20, 1280);
			platformer.bossLevel.addAttack(650, 0, 20, 1280);
			platformer.bossLevel.addAttack(850, 0, 20, 1280);
			platformer.bossLevel.addAttack(1050, 0, 20, 1280);
			platformer.bossLevel.addAttack(1250, 0, 20, 1280);
		}
	}
	void Attack3Warning() {
		if (platformer.bossLevel.getWarning().size() == 0) {
			platformer.bossLevel.addAttackWarning(50, 0, 20, 1280);
			platformer.bossLevel.addAttackWarning(250, 0, 20, 1280);
			platformer.bossLevel.addAttackWarning(450, 0, 20, 1280);
			platformer.bossLevel.addAttackWarning(650, 0, 20, 1280);
			platformer.bossLevel.addAttackWarning(850, 0, 20, 1280);
			platformer.bossLevel.addAttackWarning(1050, 0, 20, 1280);
			platformer.bossLevel.addAttackWarning(1250, 0, 20, 1280);
		}
	}

	void Attack4() {
		if (platformer.bossLevel.getAttacks().size() == 0) {
			platformer.bossLevel.addAttack(0, 100, 1280, 20);
			platformer.bossLevel.addAttack(0, 300, 1280, 20);
			platformer.bossLevel.addAttack(0, 500, 1280, 20);
			platformer.bossLevel.addAttack(0, 700, 1280, 20);
			platformer.bossLevel.addAttack(0, 900, 1280, 20);
		}
	}
	void Attack4Warning() {
		if (platformer.bossLevel.getWarning().size() == 0) {
			platformer.bossLevel.addAttackWarning(0, 100, 1280, 20);
			platformer.bossLevel.addAttackWarning(0, 300, 1280, 20);
			platformer.bossLevel.addAttackWarning(0, 500, 1280, 20);
			platformer.bossLevel.addAttackWarning(0, 700, 1280, 20);
			platformer.bossLevel.addAttackWarning(0, 900, 1280, 20);
		}
	}

	void Attack5() {
		if (platformer.bossLevel.getAttacks().size() == 0) {
			platformer.bossLevel.addAttack(0, 100, 50, 50);
			platformer.bossLevel.addAttack(50, 150, 50, 50);
			platformer.bossLevel.addAttack(100, 200, 50, 50);
			platformer.bossLevel.addAttack(150, 250, 50, 50);
			platformer.bossLevel.addAttack(200, 300, 50, 50);
			platformer.bossLevel.addAttack(250, 350, 50, 50);
			platformer.bossLevel.addAttack(300, 400, 50, 50);
			platformer.bossLevel.addAttack(350, 450, 50, 50);
			platformer.bossLevel.addAttack(400, 500, 50, 50);
			platformer.bossLevel.addAttack(450, 550, 50, 50);
			platformer.bossLevel.addAttack(500, 600, 50, 50);
			platformer.bossLevel.addAttack(550, 650, 50, 50);
			platformer.bossLevel.addAttack(600, 700, 50, 50);
			platformer.bossLevel.addAttack(650, 750, 50, 50);

			platformer.bossLevel.addAttack(150, 0, 50, 50);
			platformer.bossLevel.addAttack(200, 50, 50, 50);
			platformer.bossLevel.addAttack(250, 100, 50, 50);
			platformer.bossLevel.addAttack(300, 150, 50, 50);
			platformer.bossLevel.addAttack(350, 200, 50, 50);
			platformer.bossLevel.addAttack(400, 250, 50, 50);
			platformer.bossLevel.addAttack(450, 300, 50, 50);
			platformer.bossLevel.addAttack(500, 350, 50, 50);
			platformer.bossLevel.addAttack(550, 400, 50, 50);
			platformer.bossLevel.addAttack(600, 450, 50, 50);
			platformer.bossLevel.addAttack(650, 500, 50, 50);
			platformer.bossLevel.addAttack(700, 550, 50, 50);
			platformer.bossLevel.addAttack(750, 600, 50, 50);
			platformer.bossLevel.addAttack(800, 650, 50, 50);
			platformer.bossLevel.addAttack(850, 700, 50, 50);
			platformer.bossLevel.addAttack(900, 750, 50, 50);

			platformer.bossLevel.addAttack(400, 0, 50, 50);
			platformer.bossLevel.addAttack(450, 50, 50, 50);
			platformer.bossLevel.addAttack(500, 100, 50, 50);
			platformer.bossLevel.addAttack(550, 150, 50, 50);
			platformer.bossLevel.addAttack(600, 200, 50, 50);
			platformer.bossLevel.addAttack(650, 250, 50, 50);
			platformer.bossLevel.addAttack(700, 300, 50, 50);
			platformer.bossLevel.addAttack(750, 350, 50, 50);
			platformer.bossLevel.addAttack(800, 400, 50, 50);
			platformer.bossLevel.addAttack(850, 450, 50, 50);
			platformer.bossLevel.addAttack(900, 500, 50, 50);
			platformer.bossLevel.addAttack(950, 550, 50, 50);
			platformer.bossLevel.addAttack(1000, 600, 50, 50);
			platformer.bossLevel.addAttack(1050, 650, 50, 50);
			platformer.bossLevel.addAttack(1100, 700, 50, 50);
			platformer.bossLevel.addAttack(1150, 750, 50, 50);

			platformer.bossLevel.addAttack(650, 0, 50, 50);
			platformer.bossLevel.addAttack(700, 50, 50, 50);
			platformer.bossLevel.addAttack(750, 100, 50, 50);
			platformer.bossLevel.addAttack(800, 150, 50, 50);
			platformer.bossLevel.addAttack(850, 200, 50, 50);
			platformer.bossLevel.addAttack(900, 250, 50, 50);
			platformer.bossLevel.addAttack(950, 300, 50, 50);
			platformer.bossLevel.addAttack(1000, 350, 50, 50);
			platformer.bossLevel.addAttack(1050, 400, 50, 50);
			platformer.bossLevel.addAttack(1100, 450, 50, 50);
			platformer.bossLevel.addAttack(1150, 500, 50, 50);
			platformer.bossLevel.addAttack(1200, 550, 50, 50);
			platformer.bossLevel.addAttack(1250, 600, 50, 50);
			platformer.bossLevel.addAttack(1300, 650, 50, 50);
			platformer.bossLevel.addAttack(1350, 700, 50, 50);
			platformer.bossLevel.addAttack(1400, 750, 50, 50);
		}
	}
	void Attack5Warning() {
		if (platformer.bossLevel.getWarning().size() == 0) {
			platformer.bossLevel.addAttackWarning(0, 100, 50, 50);
			platformer.bossLevel.addAttackWarning(50, 150, 50, 50);
			platformer.bossLevel.addAttackWarning(100, 200, 50, 50);
			platformer.bossLevel.addAttackWarning(150, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(200, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(250, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(300, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(350, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(400, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(450, 550, 50, 50);
			platformer.bossLevel.addAttackWarning(500, 600, 50, 50);
			platformer.bossLevel.addAttackWarning(550, 650, 50, 50);
			platformer.bossLevel.addAttackWarning(600, 700, 50, 50);
			platformer.bossLevel.addAttackWarning(650, 750, 50, 50);

			platformer.bossLevel.addAttackWarning(150, 0, 50, 50);
			platformer.bossLevel.addAttackWarning(200, 50, 50, 50);
			platformer.bossLevel.addAttackWarning(250, 100, 50, 50);
			platformer.bossLevel.addAttackWarning(300, 150, 50, 50);
			platformer.bossLevel.addAttackWarning(350, 200, 50, 50);
			platformer.bossLevel.addAttackWarning(400, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(450, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(500, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(550, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(600, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(650, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(700, 550, 50, 50);
			platformer.bossLevel.addAttackWarning(750, 600, 50, 50);
			platformer.bossLevel.addAttackWarning(800, 650, 50, 50);
			platformer.bossLevel.addAttackWarning(850, 700, 50, 50);
			platformer.bossLevel.addAttackWarning(900, 750, 50, 50);

			platformer.bossLevel.addAttackWarning(400, 0, 50, 50);
			platformer.bossLevel.addAttackWarning(450, 50, 50, 50);
			platformer.bossLevel.addAttackWarning(500, 100, 50, 50);
			platformer.bossLevel.addAttackWarning(550, 150, 50, 50);
			platformer.bossLevel.addAttackWarning(600, 200, 50, 50);
			platformer.bossLevel.addAttackWarning(650, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(700, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(750, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(800, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(850, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(900, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(950, 550, 50, 50);
			platformer.bossLevel.addAttackWarning(1000, 600, 50, 50);
			platformer.bossLevel.addAttackWarning(1050, 650, 50, 50);
			platformer.bossLevel.addAttackWarning(1100, 700, 50, 50);
			platformer.bossLevel.addAttackWarning(1150, 750, 50, 50);

			platformer.bossLevel.addAttackWarning(650, 0, 50, 50);
			platformer.bossLevel.addAttackWarning(700, 50, 50, 50);
			platformer.bossLevel.addAttackWarning(750, 100, 50, 50);
			platformer.bossLevel.addAttackWarning(800, 150, 50, 50);
			platformer.bossLevel.addAttackWarning(850, 200, 50, 50);
			platformer.bossLevel.addAttackWarning(900, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(950, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(1000, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(1050, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(1100, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(1150, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(1200, 550, 50, 50);
			platformer.bossLevel.addAttackWarning(1250, 600, 50, 50);
			platformer.bossLevel.addAttackWarning(1300, 650, 50, 50);
			platformer.bossLevel.addAttackWarning(1350, 700, 50, 50);
			platformer.bossLevel.addAttackWarning(1400, 750, 50, 50);
		}
	}

	void Attack6(){
		if (platformer.bossLevel.getAttacks().size() == 0) {
			platformer.bossLevel.addAttack(1230, 250, 50, 50);
			platformer.bossLevel.addAttack(1180, 300, 50, 50);
			platformer.bossLevel.addAttack(1130, 350, 50, 50);
			platformer.bossLevel.addAttack(1080, 400, 50, 50);
			platformer.bossLevel.addAttack(1030, 450, 50, 50);
			platformer.bossLevel.addAttack(980, 500, 50, 50);
			platformer.bossLevel.addAttack(930, 550, 50, 50);
			platformer.bossLevel.addAttack(880, 600, 50, 50);
			platformer.bossLevel.addAttack(830, 650, 50, 50);
			platformer.bossLevel.addAttack(780, 700, 50, 50);
			platformer.bossLevel.addAttack(730, 750, 50, 50);

			platformer.bossLevel.addAttack(1180, 0, 50, 50);
			platformer.bossLevel.addAttack(1130, 50, 50, 50);
			platformer.bossLevel.addAttack(1080, 100, 50, 50);
			platformer.bossLevel.addAttack(1030, 150, 50, 50);
			platformer.bossLevel.addAttack(980, 200, 50, 50);
			platformer.bossLevel.addAttack(930, 250, 50, 50);
			platformer.bossLevel.addAttack(880, 300, 50, 50);
			platformer.bossLevel.addAttack(830, 350, 50, 50);
			platformer.bossLevel.addAttack(780, 400, 50, 50);
			platformer.bossLevel.addAttack(730, 450, 50, 50);
			platformer.bossLevel.addAttack(680, 500, 50, 50);
			platformer.bossLevel.addAttack(630, 550, 50, 50);
			platformer.bossLevel.addAttack(580, 600, 50, 50);
			platformer.bossLevel.addAttack(530, 650, 50, 50);
			platformer.bossLevel.addAttack(480, 700, 50, 50);
			platformer.bossLevel.addAttack(430, 750, 50, 50);

			platformer.bossLevel.addAttack(880, 0, 50, 50);
			platformer.bossLevel.addAttack(830, 50, 50, 50);
			platformer.bossLevel.addAttack(780, 100, 50, 50);
			platformer.bossLevel.addAttack(730, 150, 50, 50);
			platformer.bossLevel.addAttack(680, 200, 50, 50);
			platformer.bossLevel.addAttack(630, 250, 50, 50);
			platformer.bossLevel.addAttack(580, 300, 50, 50);
			platformer.bossLevel.addAttack(530, 350, 50, 50);
			platformer.bossLevel.addAttack(480, 400, 50, 50);
			platformer.bossLevel.addAttack(430, 450, 50, 50);
			platformer.bossLevel.addAttack(380, 500, 50, 50);
			platformer.bossLevel.addAttack(330, 550, 50, 50);
			platformer.bossLevel.addAttack(280, 600, 50, 50);
			platformer.bossLevel.addAttack(230, 650, 50, 50);
			platformer.bossLevel.addAttack(180, 700, 50, 50);
			platformer.bossLevel.addAttack(130, 750, 50, 50);

			platformer.bossLevel.addAttack(580, 0, 50, 50);
			platformer.bossLevel.addAttack(530, 50, 50, 50);
			platformer.bossLevel.addAttack(480, 100, 50, 50);
			platformer.bossLevel.addAttack(430, 150, 50, 50);
			platformer.bossLevel.addAttack(380, 200, 50, 50);
			platformer.bossLevel.addAttack(330, 250, 50, 50);
			platformer.bossLevel.addAttack(280, 300, 50, 50);
			platformer.bossLevel.addAttack(230, 350, 50, 50);
			platformer.bossLevel.addAttack(180, 400, 50, 50);
			platformer.bossLevel.addAttack(130, 450, 50, 50);
			platformer.bossLevel.addAttack(80, 500, 50, 50);
			platformer.bossLevel.addAttack(30, 550, 50, 50);
		}
	}

	void Attack6Warning() {
		if (platformer.bossLevel.getWarning().size() == 0) {
			platformer.bossLevel.addAttackWarning(1230, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(1180, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(1130, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(1080, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(1030, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(980, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(930, 550, 50, 50);
			platformer.bossLevel.addAttackWarning(880, 600, 50, 50);
			platformer.bossLevel.addAttackWarning(830, 650, 50, 50);
			platformer.bossLevel.addAttackWarning(780, 700, 50, 50);
			platformer.bossLevel.addAttackWarning(730, 750, 50, 50);

			platformer.bossLevel.addAttackWarning(1180, 0, 50, 50);
			platformer.bossLevel.addAttackWarning(1130, 50, 50, 50);
			platformer.bossLevel.addAttackWarning(1080, 100, 50, 50);
			platformer.bossLevel.addAttackWarning(1030, 150, 50, 50);
			platformer.bossLevel.addAttackWarning(980, 200, 50, 50);
			platformer.bossLevel.addAttackWarning(930, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(880, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(830, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(780, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(730, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(680, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(630, 550, 50, 50);
			platformer.bossLevel.addAttackWarning(580, 600, 50, 50);
			platformer.bossLevel.addAttackWarning(530, 650, 50, 50);
			platformer.bossLevel.addAttackWarning(480, 700, 50, 50);
			platformer.bossLevel.addAttackWarning(430, 750, 50, 50);

			platformer.bossLevel.addAttackWarning(880, 0, 50, 50);
			platformer.bossLevel.addAttackWarning(830, 50, 50, 50);
			platformer.bossLevel.addAttackWarning(780, 100, 50, 50);
			platformer.bossLevel.addAttackWarning(730, 150, 50, 50);
			platformer.bossLevel.addAttackWarning(680, 200, 50, 50);
			platformer.bossLevel.addAttackWarning(630, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(580, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(530, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(480, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(430, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(380, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(330, 550, 50, 50);
			platformer.bossLevel.addAttackWarning(280, 600, 50, 50);
			platformer.bossLevel.addAttackWarning(230, 650, 50, 50);
			platformer.bossLevel.addAttackWarning(180, 700, 50, 50);
			platformer.bossLevel.addAttackWarning(130, 750, 50, 50);

			platformer.bossLevel.addAttackWarning(580, 0, 50, 50);
			platformer.bossLevel.addAttackWarning(530, 50, 50, 50);
			platformer.bossLevel.addAttackWarning(480, 100, 50, 50);
			platformer.bossLevel.addAttackWarning(430, 150, 50, 50);
			platformer.bossLevel.addAttackWarning(380, 200, 50, 50);
			platformer.bossLevel.addAttackWarning(330, 250, 50, 50);
			platformer.bossLevel.addAttackWarning(280, 300, 50, 50);
			platformer.bossLevel.addAttackWarning(230, 350, 50, 50);
			platformer.bossLevel.addAttackWarning(180, 400, 50, 50);
			platformer.bossLevel.addAttackWarning(130, 450, 50, 50);
			platformer.bossLevel.addAttackWarning(80, 500, 50, 50);
			platformer.bossLevel.addAttackWarning(30, 550, 50, 50);
		}
	}

	void removeAttack() {
		platformer.bossLevel.removeAttack();
	}


}