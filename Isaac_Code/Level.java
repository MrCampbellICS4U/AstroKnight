package culminating;

import java.util.ArrayList;
import java.awt.*;

public class Level {

	private ArrayList<Rectangle> platformList;
	private ArrayList<Rectangle> hazardList;
	private ArrayList<Rectangle> platformListOg;
	private ArrayList<Rectangle> hazardListOg;

	private ArrayList<Boss> bossList;
	private ArrayList<Boss> bossListOg;
	
	private ArrayList<Rectangle> attackList;
	private ArrayList<Rectangle> warningList;

	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> enemiesOg;

	Enemy toRemove;

	int scrollStop;

	Level() {
		platformList = new ArrayList<Rectangle>();
		hazardList = new ArrayList<Rectangle>();
		platformListOg = new ArrayList<Rectangle>();
		hazardListOg = new ArrayList<Rectangle>();

		bossList = new ArrayList<Boss>();
		bossListOg = new ArrayList<Boss>();
		attackList = new ArrayList<Rectangle>();
		warningList = new ArrayList<Rectangle>();

		enemies = new ArrayList<Enemy>();
		enemiesOg = new ArrayList<Enemy>();
	}

	public void addPlatform(int x, int y, int w, int h) {
		this.platformList.add(new Rectangle(x, y, w, h));
		this.platformListOg.add(new Rectangle(x, y, w, h));
	}

	public void addHazard(int x, int y, int w, int h) {
		this.hazardList.add(new Rectangle(x, y, w, h));
		this.hazardListOg.add(new Rectangle(x, y, w, h));
	}
	
	public void addEnemy(Enemy e) {
		this.enemies.add(e);
		this.enemiesOg.add(e);
	}

	public ArrayList<Rectangle> getPlatforms() {
		return this.platformList;
	}

	public ArrayList<Rectangle> getHazards() {
		return this.hazardList;
	}

	public void setScrollStop() {
		this.scrollStop = 0;
		for (Rectangle r: this.platformList) {
			if (r.x + r.width > this.scrollStop) this.scrollStop = r.x + r.width;
		}
	}

	public ArrayList<Rectangle> getOgPlatforms() {
		return this.platformListOg;
	}

	public ArrayList<Rectangle> getOgHazards() {
		return this.hazardListOg;
	}

	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}

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


	}

	public void respawnEnemies() {
		this.enemies.clear();
		for (Enemy e: this.enemiesOg) this.enemies.add(e);
		for (Enemy e: this.enemies) e.respawn();
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
	
	public void respawnBoss() {
		this.enemies.clear();
		for (Boss b: this.bossListOg) this.bossList.add(b);
		for (Boss b: this.bossList) b.respawn();
	}

}