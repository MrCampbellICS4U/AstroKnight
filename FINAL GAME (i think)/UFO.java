package culminating;

import java.lang.Math;
import java.awt.*;

public class UFO extends Enemy {
	
	int upSpeed = 2, downSpeed = 8;
	
	
	boolean movingUp, movingDown;
	
	/**
     * Constructs a new UFO object with the specified position, dimensions, and player reference.
     * Initializes the UFO's health and movement behavior.
     *
     * @param x The x-coordinate of the UFO's position.
     * @param y The y-coordinate of the UFO's position.
     * @param w The width of the UFO.
     * @param h The height of the UFO.
     * @param p The player object for reference.
     */
	UFO(int x, int y, int w, int h, Player p) {
		super(x, y, w, h, p);
		
		this.health = this.ogHealth = 2;
		
		
	}
	
	 /**
     * Overrides the action method in the Enemy class. Implemeting vertical movement of the UFO.
     * UFOs move up and down, changing direction when reaching the upper or lower bounds.
     * They move downward when the player is within a certain distance.
     */
	@Override
	public void action() {
		if (this.movingDown) {
			this.y += downSpeed;
			for (Rectangle r: this.player.currentLevel.getPlatforms()) {//start moving up when touching platform
				if (this.intersects(r)) {
					this.movingDown = false;
					this.movingUp = true;
					break;
				}
			}
		}
		
		if (this.movingUp) {
			this.y -= upSpeed;
			if (this.y <= this.ogY) this.movingUp = false; //stop moving when reached original position
		}
		
		if (Math.abs(this.player.globalX + (this.player.width/2) - (this.globalX + (this.width/2))) < 100
				&& (!(this.movingDown || this.movingUp))) { //move down if player is in range
			movingDown = true;
		}
	}
	
	/**
     * When player dies, rest the UFO's location
     */
	public void respawn() {
		this.movingDown = this.movingUp = false;
		
		this.x = this.ogX;
		this.globalX = this.ogGlobalX;
		this.y = this.ogY;
		this.health = this.ogHealth;
		
		

	}

}
