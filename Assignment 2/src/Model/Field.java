package Model;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Field {
	public static final int EASY=1;
	public static final int NORMAL=2;
	public static final int HARD=3;
	static Random random=new Random();
	public MoveableObject ship;
	public ArrayList<MoveableObject>asteroids;
	public ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	public int asteroidCount;
	public int scoreValue;
	public int difficultyFactor;
	private Dimension size;
	
	public Field(){
		ship=new MoveableObject();
		ship.height=10;
		ship.width=10;
		difficultyFactor=NORMAL;
	}
	public void setDifficultyFactor(int a){
		this.difficultyFactor=a;
	}
	public int getScore() {
		return scoreValue;
	}
	public void setup(Dimension size){
		ship.position.x=size.width/2;
		ship.position.y=size.height/2;
		this.setSize(size);
		scoreValue=0;
		asteroidCount=5;
		asteroids=new ArrayList<MoveableObject>();
		
	}
	public void createAsteroids() {
		for (int i=0; i<asteroidCount; ++i){
			MoveableObject asteroid=new MoveableObject();
			asteroid.position.x=random.nextInt(getSize().width/(difficultyFactor+1)+ship.position.x);
			asteroid.position.y=random.nextInt(getSize().height/(difficultyFactor+1)+ship.position.y);
			asteroid.width=(random.nextInt(10)+10)*difficultyFactor;
			asteroid.height=(random.nextInt(10)+10)*difficultyFactor;
			int[] pushAsteroid={-5,-4,-3,-2,-1,1,2,3,4,5};
			asteroid.xOffset=pushAsteroid[random.nextInt(pushAsteroid.length)]*difficultyFactor;
			asteroid.yOffset=pushAsteroid[random.nextInt(pushAsteroid.length)]*difficultyFactor;
			asteroids.add(asteroid);
		}
	}
	public void addBullets(){
		int test=0;
		for(int i=0;i<3;++i){
			test+=5;
			Bullet bullet=new Bullet();
			bullet.duration=random.nextInt(50/difficultyFactor);
			bullet.position.x = ship.position.x;
			bullet.position.y = ship.position.y;
			bullet.width = 5;
			bullet.height=5;
			bullet.push(ship.xOffset + test,ship.yOffset + test);
			bullets.add(bullet);			
		}
	}
	public boolean shipHitAsteroid(){
		for(MoveableObject asteroid:asteroids){
			if(ship.contains(asteroid.position) || asteroid.contains(ship.position))return true;
		}
		return false;
	}
	
	public void update(){		
		ship.move();
		ship.bounce(getSize());
		if(asteroids!=null && asteroids.size()>0){
			for(MoveableObject as:asteroids){
				as.move();
				as.bounce(getSize());
			}
		}
		for (Iterator<Bullet> iter=bullets.iterator();iter.hasNext();){
			Bullet bullet=iter.next();
			bullet.move();
			bullet.bounce(getSize());
			bullet.duration-=1;
			if (bullet.duration<=0){
				iter.remove();				
			}
		}
		if(asteroids!=null){
			if (asteroids.size()==0) createAsteroids();
		}
	}
	public void bulletHitAsteroid(){		
		for (Iterator<Bullet> iter=bullets.iterator();iter.hasNext();){
			Bullet bullet=iter.next();
			for (Iterator<MoveableObject> iter2=asteroids.iterator();iter2.hasNext();){
				MoveableObject asteroid=iter2.next();
				if (asteroid.contains(bullet.position)){
					iter.remove();
					iter2.remove();
					scoreValue+=5*difficultyFactor;
				}
			}
		}
	}
	public Dimension getSize() {
		return size;
	}
	public void setSize(Dimension size) {
		this.size = size;
	}
}
