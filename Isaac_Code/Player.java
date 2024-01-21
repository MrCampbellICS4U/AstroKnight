package culminating;

import java.awt.Rectangle;

public class Player extends Rectangle {
	
	double yy;
	double vy;
	double g = 0.2; //change for different gravity
	int reverseStep = 1;
	
	int moveSpeed = 10;
	
	int globalX;
	
	boolean left, right, up, space;
	boolean scrolling = false;
	
	boolean canJump = true;
	boolean facingRight = true;
	boolean jumping;
	
	boolean attacking = false;
	
	int cooldownTimer = 0;
	int attackTimer = 0;
	
	Rectangle swordHitbox;
	
	Level currentLevel;
	
	
	
	Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		globalX = this.x;
		vy = 0.0;
		yy = (int)y;
		
		swordHitbox = new Rectangle(this.x, this.y, 70, 50);
		
	}
	Player(int x, int y, int width, int height, Level c) {
		super(x, y, width, height);
		globalX = this.x;
		vy = 0.0;
		yy = (int)y;
		
		currentLevel = c;
		
		swordHitbox = new Rectangle(this.x, this.y, 70, 50);
		
	}
	
	void move() { //move the level if scrolling but move the player if not scrolling
		if (left) {
			facingRight = false;
			globalX -= moveSpeed;
			if (scrolling) {
				for (Rectangle r: currentLevel.getPlatforms()) r.x += moveSpeed;
				for (Boss b: currentLevel.getBoss()) b.x += moveSpeed;
				for (Rectangle r: currentLevel.getHazards()) r.x += moveSpeed;
				for (Rectangle r: currentLevel.getAttacks()) r.x += moveSpeed;
				for (Enemy e: currentLevel.getEnemies()) e.x += moveSpeed;
			}
			else this.x -= moveSpeed;
			
			if (checkCollision()) {
				globalX += moveSpeed;
				if (scrolling) {
					for (Rectangle r: currentLevel.getPlatforms()) r.x -= moveSpeed;
					for (Boss b: currentLevel.getBoss()) b.x -= moveSpeed;
					for (Rectangle r: currentLevel.getHazards()) r.x -= moveSpeed;
					for (Rectangle r: currentLevel.getAttacks()) r.x -= moveSpeed;
					for (Enemy e: currentLevel.getEnemies()) e.x -= moveSpeed;
				}
				else this.x += moveSpeed;
			}
		}
		if (right) {
			facingRight = true;
			globalX += moveSpeed;
			if (scrolling && globalX + this.width < currentLevel.scrollStop) {
				for (Rectangle r: currentLevel.getPlatforms()) r.x -= moveSpeed;
				for (Boss b: currentLevel.getBoss()) b.x -= moveSpeed;
				for (Rectangle r: currentLevel.getHazards()) r.x -= moveSpeed;
				for (Rectangle r: currentLevel.getAttacks()) r.x -= moveSpeed;
				for (Enemy e: currentLevel.getEnemies()) e.x -= moveSpeed;
			}
			else this.x += moveSpeed;

			if (checkCollision()) {
				globalX -= moveSpeed;
				if (scrolling) {
					for (Rectangle r: currentLevel.getPlatforms()) r.x += moveSpeed;
					for (Rectangle r: currentLevel.getBoss()) r.x += moveSpeed;
					for (Rectangle r: currentLevel.getHazards()) r.x += moveSpeed;
					for (Rectangle r: currentLevel.getAttacks()) r.x += moveSpeed;
					for (Enemy e: currentLevel.getEnemies()) e.x += moveSpeed;
				}
				else this.x -= moveSpeed;
			}
		}
		
		swordHitbox.y = (int)this.yy;
		if (facingRight) swordHitbox.x = this.x + this.width;
		else swordHitbox.x = this.x - swordHitbox.width;
		
		if (globalX > 640 && globalX + 640 < currentLevel.scrollStop) scrolling = true;
		else scrolling = false;
		
		if (globalX == -1000) scrolling = false;
		
	}
	
	boolean checkCollision() { //check collision 
		for (Rectangle r: currentLevel.getPlatforms()) {
			if (this.intersects(r)) return true;
		}
		return false;
	}
	
	void gravity() {
		
		this.y++;
		
		if (checkCollision()) {
			if (vy < 0.0) reverseStep = 1;
			else reverseStep = -1;
			
			
			while (checkCollision()) { //move out of the block
				this.y += reverseStep;
				
				//this.y--;
				yy = (double)this.y;
			}
			
			yy = (double)this.y;
			vy = 0.0;
			canJump = true;
		}
		
		else { //move player down by gravity
			vy += g;
			canJump = false;
		}
		this.y--;
		yy += (vy);
		this.y = (int)yy;
		
	}
	
	void jump() {
		this.y++;	
		if (up && checkCollision() && canJump) {
			this.y--;
			vy = -8; //change to change jump height
			
		}
		this.y--;
		
	}
	
	void attack() {
		if (attacking) {
			if (attackTimer < 10) attackTimer++;
			else {
				attacking = false;
				attackTimer = 0;
				cooldownTimer = 0;
			}
		}
		else {
			if (cooldownTimer < 20 && !space) cooldownTimer++;
			if (space && cooldownTimer >= 20) {
				attacking = true;
			}
		}
	}
	
	boolean checkDeath() {
		for (Rectangle r: currentLevel.getHazards()) {
			if (this.intersects(r)) return true;
		}
		for (Enemy e: currentLevel.getEnemies()) {
			if (this.intersects(e)) return true;
		}
		
		for (Rectangle r: currentLevel.getAttacks()) {
			if (this.intersects(r)) return true;
		}
		return false;
		
		
	}
	
	boolean checkWin() {
		if (globalX > currentLevel.scrollStop) return true;
		else return false;
	}
	
	Level checkLevel() {
		return currentLevel;
	}
	
	void respawn() {
		
		int index = 0;
		for (Rectangle r: currentLevel.getPlatforms()) {
			r.x = currentLevel.getOgPlatforms().get(index).x;
			r.y = currentLevel.getOgPlatforms().get(index).y;
			index++;
		}
		index = 0;
		for (Rectangle r: currentLevel.getHazards()) {
			r.x = currentLevel.getOgHazards().get(index).x;
			r.y = currentLevel.getOgHazards().get(index).y;
			index++;
		}
		index = 0;
		
		currentLevel.respawnEnemies(); 
		
		this.x = 50;
		this.globalX = 50;
		this.yy = 650.0;
		this.y = 650;
		this.vy = 0.0;
	}
	
}