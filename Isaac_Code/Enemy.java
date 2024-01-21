package culminating;

import java.awt.*;

public abstract class Enemy extends Rectangle {

    int health, ogHealth;
   
    Player player;
    
    int hitTimer;
    boolean canBeHit;
    
    int globalX, ogGlobalX, ogX, ogY;

    /**
     * Constructor
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
   
   public abstract void action();
    
    
}