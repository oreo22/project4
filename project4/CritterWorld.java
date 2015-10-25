package project4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CritterWorld {

static ArrayList<Critter> population=new ArrayList<Critter>();
//static Map<int[],ArrayList<Integer> > grid=new HashMap<int[], ArrayList<Integer>>();
//static Map<Integer, ArrayList<Integer> > xKeys=new HashMap<Integer, ArrayList<Integer>>(Params.world_width);
static Map<Integer, Map<Integer, ArrayList<Integer> >> grid1=new HashMap<Integer, Map<Integer, ArrayList<Integer> >>(Params.world_height);
//static Map<Integer, ArrayList<Integer> > grid2=new HashMap<Integer, ArrayList<Integer> >();

public static boolean fightPhase = false;
private static String[][] critterWorld = new String[Params.world_width+2][Params.world_height+2];
		
/*static void updateGrid(){

		for(int y=0; y<Params.world_height; y++){
			
			for(int x=0; x<Params.world_width; x++){ 
				ArrayList<Integer> occupants=new ArrayList<Integer>();
				for(int c=0; c<population.size(); c++){
					if(population.get(c).getXCoord()==x && population.get(c).getYCoord()==y){
						occupants.add(c); //add this index
						/*System.out.println(population.get(c).getXCoord() + " " + population.get(c).getYCoord()  );
						System.out.println("x: " +x+ " y: " + y);
					}
				}
				grid.put(y,occupants);
			}
		}
	} */

//--------------Generating the World--------------	
	static void makeWorld() throws InvalidCritterException {
		for(int x=0; x<3; x++){
			Critter.makeCritter("project4.Craig");
		}
		for(int x=0; x<10; x++){
			Critter.makeCritter("project4.Algae");
		}
		//updateGrid();
	}

//-------------Running the World---------------
	static void runWorld(int steps) {
		for(int x=0; x<steps;x++){
			Critter.TestCritter.worldTimeStep();//run all the time steps
			//updateGrid();
		}
	}
	
//--------------Showing the world: CritterWorld----------
	static void displayWorld() { //where is 0,0? top left or bottom left?
		printBorder(); 
		Critter.displayWorld();
		printBorder();
		System.out.println();
	}
	private static void printBorder(){
		System.out.print("+");
		//Top
		for(int b=0; b<Params.world_width; b++){
			System.out.print("-");
		}
		System.out.print("+");
	}
	
	
	private static void createWorld(){
		//Top
		critterWorld[0][0] = "+";
		critterWorld[0][Params.world_width+1] = "+";
		for(int x=1; x<Params.world_width; x++){
			critterWorld[0][x] = "-";
		}
		for(int row=1; row<Params.world_height+1; row++){
			critterWorld[row][0] = "|";
			for(int col =1; col<Params.world_width; col++){
				critterWorld[row][col] = " ";
			}
			critterWorld[row][Params.world_height+1] = "|";
		}
		
		critterWorld[Params.world_height+1][0] = "+";
		critterWorld[Params.world_height+1][Params.world_width+1] = "+";
		for(int x=1; x<Params.world_width; x++){
			critterWorld[Params.world_height+1][x] = "-";
		}
	}

}
