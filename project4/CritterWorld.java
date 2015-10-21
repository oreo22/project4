package project4;

import java.util.ArrayList;


public class CritterWorld {
public static ArrayList<Critter> population=new ArrayList<Critter>();
	
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
	
	static void handleEncounters(){
		for(int y=0; y<population.size(); y++){
			for(int x=y; x<population.size(); x++){
				if(population.get(x).getXCoord() == population.get(y).getXCoord() && population.get(x).getYCoord()== population.get(y).getYCoord()){
					boolean xFight = population.get(x).fight(population.get(y).getClass().toString());
					boolean yFight = population.get(y).fight(population.get(x).getClass().toString());
					int xStrength = 0;
					int yStrength = 0;
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
	

}
