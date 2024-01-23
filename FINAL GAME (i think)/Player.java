package culminating;

import java.awt.Rectangle;

public class Player extends Rectangle {
	
	double yy;
	double vy;
	double g = 0.2; //change for different gravity
	int reverseStep = 1;
	
	int moveSpeed = 3;
	
	int globalX;
	
	boolean left, right, up, space;
	boolean scrolling = false;
	
	boolean canJump = true;
	boolean facingRight = true;
	
	boolean attacking = false, hasAttacked = false;
	
	boolean jumping;
	
	boolean knockbackLeft, knockbackRight;
	int knockbackTimer;
	int knockbackSpeed = 3;
	
	int cooldownTimer = 0;
	int attackTimer = 0;
	
	Rectangle swordHitbox;
	
	Level currentLevel;
	
	
	/**
	 * Constructor for the player class
	 * @param x the x postion of the player
	 * @param y the y position of the player
	 * @param width the width of the player
	 * @param height the height of the player
	 */
	Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		globalX = this.x;
		vy = 0.0;
		yy = (int)y;
		
		swordHitbox = new Rectangle(this.x, this.y, 70, 50);
		
		
	}
	
	/**
	 * this method contains a knockback feature upon hitting an enemy and also scrolls the screen 
	 * when the player reaches the middle of the screen and stops scrolling when reaching the end of the level
	 */
	void move() { 
		if (knockbackLeft) { //move left if being knocked back
			knockbackTimer++;
			moveLeft(knockbackSpeed);
			if (knockbackTimer > 5) knockbackLeft = false;
		}
		else if (knockbackRight) { //move right if being knocked back
			knockbackTimer++;
			moveRight(knockbackSpeed);
			if (knockbackTimer > 5) knockbackRight = false;
		}
		
		else if (left) {
			facingRight = false;
			moveLeft(moveSpeed);
		}
		else if (right) {
			facingRight = true;
			moveRight(moveSpeed);
		}
		
		//calculate position of sword hitbox
		swordHitbox.y = (int)this.yy;
		if (facingRight) swordHitbox.x = this.x + this.width;
		else swordHitbox.x = this.x - swordHitbox.width;
		
		//scroll if global x is more than half the screen but stop if the level has ended
		if (globalX > 640 && globalX + 640 < currentLevel.scrollStop) scrolling = true;
		else scrolling = false;
		
		//special case for boss
		if (globalX == -1000) scrolling = false;
		
	}
	
	/**
	 * this method uses velocity to move all the platforms and enemies in order to show the illusion of a scrolling screen
	 * @param moveSpeed how fast the platforms move to the left
	 */
	private void moveLeft(int moveSpeed) {
		
		globalX -= moveSpeed;
		if (scrolling) {
			for (Rectangle r: currentLevel.getPlatforms()) r.x += moveSpeed;
			for (Rectangle r: currentLevel.getHazards()) r.x += moveSpeed;
			for (Enemy e: currentLevel.getEnemies()) e.x += moveSpeed;
			for (Boss b: currentLevel.getBoss()) b.x += moveSpeed;
			for (Rectangle r: currentLevel.getAttacks()) r.x += moveSpeed;
		}
		else this.x -= moveSpeed;
		
		if (checkCollision()) {
			globalX += moveSpeed;
			if (scrolling) {
				for (Rectangle r: currentLevel.getPlatforms()) r.x -= moveSpeed;
				for (Rectangle r: currentLevel.getHazards()) r.x -= moveSpeed;
				for (Enemy e: currentLevel.getEnemies()) e.x -= moveSpeed;
				for (Boss b: currentLevel.getBoss()) b.x -= moveSpeed;
				for (Rectangle r: currentLevel.getAttacks()) r.x -= moveSpeed;
			}
			else this.x += moveSpeed;
		}
	}
	
	/**
	 * this method uses velocity to move all the platforms and enemies in order to show the illusion of a scrolling screen
	 * @param moveSpeed how fast the platforms move to the right
	 */
	private void moveRight(int moveSpeed) {
		
		globalX += moveSpeed;
		if (scrolling) {
			for (Rectangle r: currentLevel.getPlatforms()) r.x -= moveSpeed;
			for (Rectangle r: currentLevel.getHazards()) r.x -= moveSpeed;
			for (Enemy e: currentLevel.getEnemies()) e.x -= moveSpeed;
			for (Boss b: currentLevel.getBoss()) b.x -= moveSpeed;
			for (Rectangle r: currentLevel.getAttacks()) r.x -= moveSpeed;
		}
		else this.x += moveSpeed;
		
		if (checkCollision()) {
			globalX -= moveSpeed;
			if (scrolling) {
				for (Rectangle r: currentLevel.getPlatforms()) r.x += moveSpeed;
				for (Rectangle r: currentLevel.getHazards()) r.x += moveSpeed;
				for (Enemy e: currentLevel.getEnemies()) e.x += moveSpeed;
				for (Boss b: currentLevel.getBoss()) b.x += moveSpeed;
				for (Rectangle r: currentLevel.getAttacks()) r.x += moveSpeed;
			}
			else this.x -= moveSpeed;
		}
	}
	
	/**
	 * this method checks if the player collides with any platform
	 * @return true if it collides and vice versa
	 */
	boolean checkCollision() { //check collision 
		for (Rectangle r: currentLevel.getPlatforms()) {
			if (this.intersects(r)) return true;
		}
		return false;
	}
	
	/**
	 * this method allows the player to jump once after landing on a platform and controls the gravity as well
	 */
	void gravity() {
		
		this.y++; //move down one step to check for collision
		
		if (checkCollision()) {
			//if player is falling down, they move up from the platform, and vice versa
			if (vy < 0.0) reverseStep = 1;
			else reverseStep = -1;
			
			
			while (checkCollision()) { //move out of the block
				this.y += reverseStep;
				
				//this.y--;
				yy = (double)this.y;
			}
			
			//using doubles for smoother gravity effect
			yy = (double)this.y;
			vy = 0.0;
			canJump = true;
		}
		
		else { //move player down by gravity
			vy += g;
			canJump = false;
		}
		
		this.y--; //compensate for moving down at the beginning 
		yy += (vy); //move y by vy
		this.y = (int)yy;
		
	}
	
	/**
	 * allows player to jump by pressing up
	 */
	void jump() {
		this.y++; //move down to check for collision
		if (up && checkCollision() && canJump) {
			this.y--;
			vy = -8; //change the vy so the player is travelling up (change this value for different jump heights)
			
		}
		this.y--; //compensate for moving down
		
	}
	
	/**
	 * this method allows the player to attack using a timer cooldown 
	 */
	void attack() {
		if (hasAttacked && !space) hasAttacked = false; //if space not pressed the player can attack
		
		else if (attacking) {
			if (attackTimer < 5) attackTimer++; //how long the attack hitbox lasts
			else {
				//end the attack and start the cooldown timer
				attacking = false;
				attackTimer = 0;
				cooldownTimer = 0;
			}
		}
		else {
			if (cooldownTimer < 20) cooldownTimer++; //increase the cooldown timer
			//if player can attack and is pressing space attack
			if (space && cooldownTimer >= 20 && !hasAttacked) {
				attacking = true;
				hasAttacked = true;
			}
		}
	}
	
	/**
	 * this method checks if the player has fallen off the platforms, hit a spike, collided with an enemy, etc.
	 * @return true if the player died
	 */
	boolean checkDeath() {
		for (Rectangle r: currentLevel.getHazards()) { //spikes
			if (this.intersects(r)) return true;
		}
		for (Enemy e: currentLevel.getEnemies()) { //enemies
			if (this.intersects(e)) return true;
		}
		
		for (Rectangle r: currentLevel.getAttacks()) { //boss attacks
			if (this.intersects(r)) return true;
		}
		
		return false;
		
		
	}
	
	/**
	 * this method checks if the player has reached the end of the last platform
	 * @return true if the player makes it to the end
	 */
	boolean checkWin() {
		if (globalX > currentLevel.scrollStop) return true;
		else return false;
	}
	
	/**
	 * this method resets every platform to its normal state as well as enemies
	 */
	void respawn() {
		
		//move all platforms and spikes to their original locations using Og Lists in the Level class
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
		
		//move all enemies back to original positions and reset their healths
		currentLevel.respawnEnemies();
	
		//move player to spawn point
		this.x = 50;
		this.globalX = 50;
		this.yy = 650.0;
		this.y = 650;
		this.vy = 0.0;
	}
	
}
