package culminating;

import java.util.ArrayList;
import java.awt.*;

public class Level {
	
	//create all the lists
	private ArrayList<Rectangle> platformList;
	private ArrayList<Rectangle> hazardList;
	private ArrayList<Rectangle> platformListOg;
	private ArrayList<Rectangle> hazardListOg;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> enemiesOg;
	
	private ArrayList<Boss> bossList;
	private ArrayList<Boss> bossListOg;
	
	private ArrayList<Rectangle> attackList;
	private ArrayList<Rectangle> warningList;
	
	Enemy toRemove;
	
	int scrollStop; //the farthest x-coordinate of the level
	
	 /**
     * Constructs a new Level object
     */
	Level() {
		platformList = new ArrayList<Rectangle>();
		hazardList = new ArrayList<Rectangle>();
		platformListOg = new ArrayList<Rectangle>();
		hazardListOg = new ArrayList<Rectangle>();
		
		enemies = new ArrayList<Enemy>();
		enemiesOg = new ArrayList<Enemy>();
		
		bossList = new ArrayList<Boss>();
		bossListOg = new ArrayList<Boss>();
		attackList = new ArrayList<Rectangle>();
		warningList = new ArrayList<Rectangle>();
	}
	
	/**
	 * Adds a rectangle to the platform list and the original platform list.
	 * @param x x of rectangle to add
	 * @param y y of rectangle to add
	 * @param w width of rectangle to add
	 * @param h height of rectangle to add
	 */
	public void addPlatform(int x, int y, int w, int h) {
		this.platformList.add(new Rectangle(x, y, w, h));
		this.platformListOg.add(new Rectangle(x, y, w, h));
	}
	
	/**
	 * Adds a rectangle to the hazard list and the original hazard list.
	 * @param x x of rectangle to add
	 * @param y y of rectangle to add
	 * @param w width of rectangle to add
	 * @param h height of rectangle to add
	 */
	
	public void addHazard(int x, int y, int w, int h) {
		this.hazardList.add(new Rectangle(x, y, w, h));
		this.hazardListOg.add(new Rectangle(x, y, w, h));
	}
	
	/**
	 * Adds an enemy to the enemy and original enemy lists
	 * @param e the enemy to add
	 */
	
	public void addEnemy(Enemy e) {
		this.enemies.add(e);
		this.enemiesOg.add(e);
	}
	
	/**
	 * Adds an enemy to the level without adding it to the original enemy list, so that it doesn't respawn
	 * @param e the enemy to add (usually a Bullet)
	 */
	public void addTempEnemy(Enemy e) {
		this.enemies.add(e);
	}
	
	/**
	 * Returns the platform list
	 * @return the platform list
	 */
	public ArrayList<Rectangle> getPlatforms() {
		return this.platformList;
	}
	
	/**
	 * Returns the hazard list
	 * @return the hazard list
	 */
	public ArrayList<Rectangle> getHazards() {
		return this.hazardList;
	}
	
	/**
	 * Sets the scrollStop of the level by finding the farthest rectangle
	 */
	public void setScrollStop() {
		this.scrollStop = 0;
		for (Rectangle r: this.platformList) {
			if (r.x + r.width > this.scrollStop) this.scrollStop = r.x + r.width;
		}
	}
	
	/**
	 * Returns the original platform list
	 * @return the original platform list
	 */
	public ArrayList<Rectangle> getOgPlatforms() {
		return this.platformListOg;
	}
	
	/**
	 * Returns the original hazard list
	 * @return the original hazard list
	 */
	public ArrayList<Rectangle> getOgHazards() {
		return this.hazardListOg;
	}
	
	/**
	 * returns the enemy list
	 * @return the enemy list
	 */
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	/**
	 * returns the original enemy list
	 * @return the original enemy list
	 */
	public ArrayList<Enemy> getOgEnemies() {
		return this.enemiesOg;
	}
	
	/**
	 * Checks the health of all the enemies and removes them if they are dead. 
	 * Then performs the action of each enemy.
	 */
	public void checkEnemies() {
		this.toRemove = null;
		for (Enemy e: this.enemies) {
			e.checkHit();
			if (e.health <= 0) {
				this.toRemove = e;
				break;
			}
		}
		this.enemies.remove(this.toRemove);
		
		for (int i = 0; i < this.enemies.size(); i++) this.enemies.get(i).action();
		
	}
	
	/**
	 * Checks if the boss has been hit and performs its action.
	 */
	public void checkBoss() {
		for (Boss b: this.bossList) {
			b.checkHit();
		}
		
		for (int i = 0; i < this.bossList.size(); i++) this.getBoss().get(i).action();
	}
	
	/**
	 * respawns all the enemies and boss
	 */
	public void respawnEnemies() {
		this.enemies.clear();
		for (Enemy e: this.enemiesOg) this.enemies.add(e);
		
		for (Enemy e: this.enemies) e.respawn();
		
		this.bossList.clear();
		for (Boss b: this.bossListOg) this.bossList.add(b);
		
		for (Boss b: this.bossList) b.respawn();
	}
	
	// for boss
		public void addAttack(int x, int y, int w, int h) {
			this.attackList.add(new Rectangle(x, y, w, h));
		}
		public void addAttackWarning(int x, int y, int w, int h) {
			this.warningList.add(new Rectangle(x, y, w, h));
		}
		
		public void addBoss(Boss b) {
			this.bossList.add(b);
			this.bossListOg.add(b);
		}
		public void removeBoss(Boss b) {
			this.bossList.remove(b);
			this.bossListOg.remove(b);
		}

		public void removeAttack() {
			this.attackList.clear();
			this.warningList.clear();
		}
		
		public ArrayList<Rectangle> getAttacks(){
			return this.attackList;
		}
		public ArrayList<Rectangle> getWarning(){
			return this.warningList;
		}
		
		public ArrayList<Boss> getBoss(){
			return this.bossList;
		}

	
	
	

}
