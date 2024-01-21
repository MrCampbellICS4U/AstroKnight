package culminating;

public class Alien extends Enemy {
	
	int leftBound, rightBound, ogLeftBound, ogRightBound;
	int moveSpeed;
	
	boolean movingLeft, movingRight;
	
	Alien(int x, int y, int w, int h, int l, int r, Player p) {
		super(x, y, w, h, p);
		
		this.health = this.ogHealth = 2;
		
		this.leftBound = this.ogLeftBound = l;
		this.rightBound = this.ogRightBound = r;
		
		this.moveSpeed = 2;
		
		this.movingLeft = true;
	}

	@Override
	public void action() {
		
		if (this.movingLeft) {
			this.x -= this.moveSpeed;
			this.globalX -= this.moveSpeed;
			if (this.globalX <= this.leftBound) {
				this.movingLeft = false;
				this.movingRight = true;
			}
		}
		if (this.movingRight) {
			this.x += this.moveSpeed;
			this.globalX += this.moveSpeed;
			if (this.globalX + this.width >= this.rightBound) {
				this.movingRight = false;
				this.movingLeft = true;
			}
		}
		//System.out.println(this.x);
		
	}
	
	public void respawn() {
		   this.x = this.ogX;
		   this.globalX = this.ogGlobalX;
		   this.y = this.ogY;
		   this.health = this.ogHealth;
		   
		   this.leftBound = this.ogLeftBound;
		   this.rightBound = this.ogRightBound;
		   
		   this.movingLeft = true;
		   this.movingRight = false;
		   
	   }

}