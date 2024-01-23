package culminating;

public class Alien extends Enemy {
	
	int leftBound, rightBound, ogLeftBound, ogRightBound;
	int moveSpeed;
	
	boolean movingLeft, movingRight;
	
	/**
     * Constructs a new Alien object with the specified position, dimensions, left and right bounds, and player reference.
     * Initializes the alien's health, movement speed, and initial direction.
     *
     * @param x         The x-coordinate of the alien's position.
     * @param y         The y-coordinate of the alien's position.
     * @param w         The width of the alien.
     * @param h         The height of the alien.
     * @param l         The left boundary for the alien's movement.
     * @param r         The right boundary for the alien's movement.
     * @param p         The player object for reference.
     */
	Alien(int x, int y, int w, int h, int l, int r, Player p) {
		super(x, y, w, h, p);
		
		this.health = this.ogHealth = 2;
		
		this.leftBound = this.ogLeftBound = l;
		this.rightBound = this.ogRightBound = r;
		
		this.moveSpeed = 2;
		
		this.movingLeft = true;
	}

	/**
     * Overrides the action method in the Enemy class. Implements the horizontal movement of the alien.
     * Aliens move back and forth within the specified range, changing direction when reaching the bounds.
     */
	@Override
	public void action() {
		
		if (this.movingLeft) { //start moving right if touching left bound
			this.x -= this.moveSpeed;
			this.globalX -= this.moveSpeed;
			if (this.globalX <= this.leftBound) {
				this.movingLeft = false;
				this.movingRight = true;
			}
		}
		if (this.movingRight) { //start moving left if touching right bound
			this.x += this.moveSpeed;
			this.globalX += this.moveSpeed;
			if (this.globalX + this.width >= this.rightBound) {
				this.movingRight = false;
				this.movingLeft = true;
			}
		}
		//System.out.println(this.x);
		
	}
	
	/**
     * Resets the alien's position, health, bounds, and movement direction to their original values after player dies.
     */
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
