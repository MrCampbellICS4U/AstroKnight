package culminating;

import java.awt.*;

public class Turret extends Enemy {
	
	int shootTimer = 0;
	
	Rectangle bulletToRemove;
	
	boolean faceLeft = true;

	/**
     * Constructs a new Turret object
     * Initializes the turret's health.
     *
     * @param x The x-coordinate of the turret's position.
     * @param y The y-coordinate of the turret's position.
     * @param w The width of the turret.
     * @param h The height of the turret.
     * @param p The player object for reference.
     */
	Turret(int x, int y, int w, int h, Player p) {
		super(x, y, w, h, p);
		
		this.health = this.ogHealth = 3;
		
	}
	
	/**
     * Overrides the action method in the Enemy class. Implements the shooting behavior of the turret.
     * Turrets shoot bullets at the player with a certain frequency and direction.
     */
	@Override
	public void action() {
		this.shootTimer++;
		if (this.shootTimer > 200 && this.x <= 1280 && this.x >= 0) {
			if (this.player.x + (this.player.width/2) < this.x + (this.width/2)) this.faceLeft = true;
			else this.faceLeft = false; //detect is player is to the left or right of turret
				
			this.player.currentLevel.addTempEnemy(new Bullet(this.x, this.y + (this.height/2) - 5, 
					40, 10, this.player, this.faceLeft)); //add a bullet
			
			this.shootTimer = 0;
		}
	}
	
	/**
     * The Bullet class represents a projectile shot by the turret.
     * It extends the Enemy class and defines the behavior of the bullet.
     */
	public class Bullet extends Enemy {
		
		boolean shootLeft;

		/**
         * Constructs a new Bullet object.
         *
         * @param x      The x-coordinate of the bullet's position.
         * @param y      The y-coordinate of the bullet's position.
         * @param w      The width of the bullet.
         * @param h      The height of the bullet.
         * @param p      The player object for reference.
         * @param s      The direction of the bullet's movement (left or right).
         */

		Bullet(int x, int y, int w, int h, Player p, boolean s) {
			super(x, y, w, h, p);
			
			this.health = 1;
			this.shootLeft = s;
		}
		
		 /**
         * Overrides the action method in the Enemy class. Implements the movement and collision logic of the bullet.
         * Bullets move in the specified direction and are removed upon collision with platforms.
         */
		public void action() {
			if (this.shootLeft) this.x -= 6;
			else this.x += 6;
			
			for (Rectangle r: this.player.currentLevel.getPlatforms()) { //remove bullet when touching a platform
				if (this.intersects(r)) {
					this.player.currentLevel.getEnemies().remove(this); 
					break;
				}
			}
		}
		
		public void respawn() {} //respawn method empty to remove bullets from screen when player dies
		
	}

	
	
		
	
	}