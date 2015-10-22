package project4;

import java.util.ArrayList;


public class CritterWorld {
static ArrayList<Critter> population=new ArrayList<Critter>();
private static String[][] critterWorld = new String[Params.world_width+2][Params.world_height+2];
	
//--------------Generating the World--------------	
	static void makeWorld() throws InvalidCritterException {
		for(int x=0; x<3; x++){
			Critter.makeCritter("project4.Craig");
		}
		for(int x=0; x<10; x++){
			Critter.makeCritter("project4.Algae");
		}
		// TODO Auto-generated method stub
		
	}
//-------------Running the World---------------
	static void runWorld(int steps) {
		for(int x=0; x<steps;x++){
			Critter.TestCritter.worldTimeStep();//run all the time steps
		}
		// TODO Auto-generated method stub
		
	}
	
//-------------Running the World---------------
		static void populatebabies() {
			for(int x=0; x<Critter.babies.size();x++){
				population.add(Critter.babies.get(x));
			}
			
			Critter.babies = new ArrayList<Critter>();
			
		}
	
	static void handleEncounters(){
		for(int y=0; y<population.size(); y++){
				for(int x=y+1; x<population.size(); x++){
					if(population.get(y).getEnergy() > 0 && population.get(x).getEnergy() > 0 && population.get(x).getXCoord() == population.get(y).getXCoord() && population.get(x).getYCoord()== population.get(y).getYCoord()){
						int xStrength = 0;
						int yStrength = 0;
						boolean xFight = population.get(x).fight(population.get(y).getClass().toString());
						boolean yFight = population.get(y).fight(population.get(x).getClass().toString());
						if(xFight){ xStrength = Critter.getRandomInt(population.get(x).getEnergy());}
						if(yFight){ yStrength = Critter.getRandomInt(population.get(y).getEnergy());}
						//if it's the same position, neither of them ran away successfully.
						if(population.get(x).getXCoord() == population.get(y).getXCoord() && population.get(x).getYCoord()== population.get(y).getYCoord()){
							if(xStrength>=yStrength){
							population.get(x).setEnergy(population.get(x).getEnergy() + population.get(y).getEnergy()/2);
							population.get(y).setEnergy(0);
							}
							else if(yStrength > xStrength){
								population.get(y).setEnergy(population.get(y).getEnergy() + population.get(x).getEnergy()/2);
								population.get(x).setEnergy(0);
							}
						}
					//solve for unsuccessful run away 
					
						
						
	
					}
				}
		}
	}
	//--------------Showing the world: CritterWorld----------
	static void killCritters(){
		for(int x=0; x<population.size(); x++){
			if(population.get(x).getEnergy() <= 0){
				population.remove(x--);
			}
		}
	}
//--------------Showing the world: CritterWorld----------
	static void displayWorld() { //where is 0,0? top left or bottom left?
		printBorder(); 
		System.out.println();
		
		for(int y=0; y<Params.world_height; y++){
			System.out.print("|");
			for(int x=0; x<Params.world_width; x++){ 
				String output = " ";
				for(int c=0; c<population.size(); c++){
					if(population.get(c).getXCoord()==x && population.get(c).getYCoord()==y){
						output = population.get(c).toString();
						break;
					}
				}
				System.out.print(output);
			}
			System.out.print("|");
			System.out.println();
		}
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
