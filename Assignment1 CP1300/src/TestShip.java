

public class TestShip
{
	public static void main (String[]args){
	//adjust the ship position to (1,1)
	Ship Ship = new Ship() ;
	Ship.position.x = 1; Ship.position.y = 1 ; 

	 int Boundary_Width = 10 ; 
	 int Boundary_Height = 6 ;
	 int Left = 0 ;
	 int Right = Boundary_Width ;
	 int Top = 0 ;
	 int Bottom = Boundary_Height ;
	//move and bounce within the field
	System.out.println("(Test Ship) :" ) ;
	boolean hitTop = false , hitLeft=false , hitBottom = false , hitRight=false ;
	System.out.println(Ship);
	do {
		Ship.move();
		Ship.shipbounce(Boundary_Width , Boundary_Height) ;
		System.out.println(Ship); 
		if(Ship.getPosition().y==Bottom){
			hitBottom =true;        /*when ship hits bottom*/
			System.out.println("Hit bottom!");
		}
		else if(Ship.getPosition().x==Right){
			hitRight = true;     /*when ship hits right*/
			System.out.println("Hit right!");
		}
		else if(Ship.getPosition().y==Top){
			hitTop = true;   /*when ship hits top*/
			System.out.println("Hit top!");
		}
		else if(Ship.getPosition().x==Left){
			hitLeft = true;  /*when ship hits left*/
			System.out.println("Hit left");
		}
		
	/*if all sides are hit,the test will stop*/
	} while (!hitTop || !hitLeft || !hitBottom || !hitRight);
		System.out.println("All sides hit! Test it successful");
	}
	} 
