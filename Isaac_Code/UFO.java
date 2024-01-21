package culminating;

import java.lang.Math;
import java.awt.*;

public class UFO extends Enemy {
	
	int upSpeed = 2, downSpeed = 8;
	
	
	boolean movingUp, movingDown;
	
	UFO(int x, int y, int w, int h, Player p) {
		super(x, y, w, h, p);
		
		this.health = this.ogHealth = 2;
		
		
	}
	
	@Override
	public void action() {
		if (this.movingDown) {
			this.y += downSpeed;
			for (Rectangle r: this.player.currentLevel.getPlatforms()) {
				if (this.intersects(r)) {
					this.movingDown = false;
					this.movingUp = true;
					break;
				}
			}
		}
		
		if (this.movingUp) {
			this.y -= upSpeed;
			if (this.y <= this.ogY) this.movingUp = false;
		}
		
		if (Math.abs(this.player.globalX + (this.player.width/2) - (this.globalX + (this.width/2))) < 100
				&& (!(this.movingDown || this.movingUp))) {
			movingDown = true;
		}
	}
	
	public void respawn() {
		this.movingDown = this.movingUp = false;
		
		this.x = this.ogX;
		this.globalX = this.ogGlobalX;
		this.y = this.ogY;
		this.health = this.ogHealth;
		
		

	}

}