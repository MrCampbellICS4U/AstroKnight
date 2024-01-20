package culminating;

import java.awt.*;

public class Enemy extends Rectangle {

    int health, ogHealth;
   
    Player player;
    
    Level currentLevel;
    
    int hitTimer;
    boolean canBeHit;
    
    int globalX, ogGlobalX, ogX, ogY;
    
	double yy;
	double vy;
	double g = 0.2; //change for different gravity
	int reverseStep = 1;

    /**
     * Constructor
     */
    Enemy(int x, int y, int w, int h, Player p, Level c) {  
    	super(x, y, w, h);
        
    	this.globalX = this.x;
    	this.ogGlobalX = this.globalX;
    	this.ogX = this.x;
    	this.ogY = this.y;
        this.player = p;
        health = 10;
        hitTimer = 0;
        
        currentLevel = c;

    }
    
    void checkHit() {
    	
    	if (hitTimer < 10) {
    		hitTimer++;
    		canBeHit = false;
    	}
    	else {
    		canBeHit = true;
    	}
    	
    	if (this.intersects(this.player.swordHitbox) && this.player.attacking && this.canBeHit) {
    		System.out.println("hit");
    		this.health--;
    		this.hitTimer = 0;
    		System.out.println(this.health);
    	}
    	    	
    }
    
    void takeDamage(int damage) {
        if (canBeHit) {
            health -= damage;
            hitTimer = 0;
            System.out.println("Boss hit! Health: " + health);
        }
    }
    
    boolean checkDeath() {
    	if (health <= 0) {
    		return true;
    	}
    	return false;
    }
    
   void respawn() {
	   this.x = this.ogX;
	   this.globalX = this.ogGlobalX;
	   this.y = this.ogY;
	   this.health = this.ogHealth;
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
		}
		
		else { //move player down by gravity
			vy += g;
		}
		this.y--;
		yy += (vy);
		this.y = (int)yy;
		
	}
    
    
}