package culminating;

import java.awt.*;

/**
 * The enemy abstract class
 * Enemies are rectangles that has attributes like health, and positions
 */
public abstract class Enemy extends Rectangle {

    int health, ogHealth;
   
    Player player;
    
    int hitTimer;
    boolean canBeHit;
    
    int globalX, ogGlobalX, ogX, ogY;

    /**
     * Constructor for the Enemy class.
     *
     * @param x      The initial X-coordinate of the enemy.
     * @param y      The initial Y-coordinate of the enemy.
     * @param w      The width of the enemy's bounding box.
     * @param h      The height of the enemy's bounding box.
     * @param player Reference to the player object in the game.
     */
    Enemy(int x, int y, int w, int h, Player p) {  
    	super(x, y, w, h);
        
    	this.globalX = this.x;
    	this.ogGlobalX = this.globalX;
    	this.ogX = this.x;
    	this.ogY = this.y;
        this.player = p;
        hitTimer = 0;
    }
    
    /**
     * Checks if the enemy has been hit by the player's attack and updates knockback and health
     */
    public void checkHit() {
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
    
   public void respawn() {
	   this.x = this.ogX;
	   this.globalX = this.ogGlobalX;
	   this.y = this.ogY;
	   this.health = this.ogHealth;
   }
   
   /**
    * Abstract method that represents the action or behavior of the enemy.
    */
   public abstract void action();
    
    
}
