package project4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */
public abstract class Critter{
	private int energy = 0;	
	private int x_coord;
	private int y_coord;
	private boolean move_flag;
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}

	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	protected int getEnergy() { return energy; }

	protected int[] findNewLocation(int direction, int step){
		int[] newCoord=new int[2];
		newCoord[0] = x_coord;
		newCoord[1] = y_coord;
		switch (direction){
		case 0: if(y_coord == 0){ newCoord[1] = Params.world_height-step;}else{newCoord[1] = (y_coord-step)%Params.world_height;}			  break;
        case 1: newCoord[0]=(x_coord+step)%Params.world_width; if(y_coord == 0){ newCoord[1] = (Params.world_height-1);}else{newCoord[1] = (y_coord-step)%Params.world_height;} break;
        case 2: newCoord[0]=(x_coord+step)%Params.world_width; 			  break;
        case 3: newCoord[0]=(x_coord+step)%Params.world_width; newCoord[1]=(y_coord+step)%Params.world_height; break;
        case 4: newCoord[1]=(y_coord+step)%Params.world_height; 			  break;
        case 5: if(x_coord == 0){newCoord[0] = Params.world_width-step;}else{newCoord[0] = (x_coord-step)%Params.world_width;} 
        		newCoord[1]=(y_coord+step)%Params.world_height; break; //what if it is at 0
        case 6: if(x_coord == 0){newCoord[0] = Params.world_width-step;}else{newCoord[0] = (x_coord-step)%Params.world_width;} break;
        case 7: newCoord[0]=(x_coord+step)%Params.world_width; if(y_coord == 0){ newCoord[1] = (Params.world_height-1);}else{newCoord[1] = (y_coord-step)%Params.world_height;} break;
        default: System.out.println("Invalid direction. Try again."); break;
        
		}
		return newCoord;
	}
	protected final void walk(int direction) {
		if(this.move_flag == false){
			int[] newCoord=findNewLocation(direction,1);
			boolean newLocationIsOccupied = false; //different for fightstage and normal stages
			//only set newLocationIsOccupied if it's fightPhase
			/*if(this location is occupied && CritterWorld.fightPhase==true ){
			 * newLocationIsOccupied==true;
				}*/
			
			if(newLocationIsOccupied){
				//do nothing
			}
			else{
				x_coord=newCoord[0];
				y_coord=newCoord[1];
			}
			energy-=Params.walk_energy_cost;
			move_flag=true; //it walked!
		}
		else{
			energy-=Params.walk_energy_cost;
		}
	}
	protected final void run(int direction) {
		if(this.move_flag == false){
			int[] newCoord=findNewLocation(direction,2);
			boolean newLocationIsOccupied = false; //different for fightstage and normal stages
			/*if(this location is occupied){
			 * newLocationIsOccupied==true;
				}*/
			if(newLocationIsOccupied){
				//do nothing
				
			}
			else{
				x_coord=newCoord[0];
				y_coord=newCoord[1];
			}
			energy-=Params.run_energy_cost;
			move_flag=true; //it ran!
		}
		else{
			energy-=Params.run_energy_cost;
		}
	}
	protected final void reproduce(Critter offspring, int direction){
		if(this.energy <= Params.min_reproduce_energy){
			return;
		}
			this.energy /= 2;
			offspring.energy = this.energy + Params.walk_energy_cost;
			offspring.x_coord = offspring.x_coord;
			offspring.y_coord = offspring.y_coord;
			offspring.walk(direction);
			babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
        try { 
            Class<Critter> class_type  = (Class<Critter>) Class.forName(critter_class_name);
    		Critter newSpawn;
				newSpawn = class_type.newInstance(); //calling the basic constructor for whatever Critter sub-type was given
				newSpawn.energy=Params.start_energy;
	    		newSpawn.x_coord=newSpawn.getRandomInt(Params.world_width);
	    		newSpawn.y_coord=newSpawn.getRandomInt(Params.world_height);
	    		if(critter_class_name == "project4.Project4TestCritter"){
	    			newSpawn.x_coord =0;
	    			newSpawn.y_coord =  3;
	    		}
	    		newSpawn.move_flag = false;
	    		CritterWorld.population.add(newSpawn);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				Throwable x=new InvalidCritterException(critter_class_name);
				System.out.println(x.toString());
			}
        
		/*If the random location selected for the critter is already 
		 * occupied, the critter should be placed into that position anyway. 
		 * The encounter between the two critters now located in the same position will be resolved 
		 * in the next time step (provided both critters are still in the same position at the end of that 
		 * time step, see below).*/
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for(int x=0; x<CritterWorld.population.size(); x++){
			try {
				if(Class.forName(critter_class_name).isInstance(CritterWorld.population.get(x))){
					result.add(CritterWorld.population.get(x));
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		runStats(result);
		return result;
	}
	
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setXCoord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setYCoord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
	}
	
	public static List<Critter> babies = new java.util.ArrayList<Critter>();

	public static void worldTimeStep() {
		for(int x=0; x<CritterWorld.population.size(); x++){
			CritterWorld.population.get(x).move_flag = false;
			CritterWorld.population.get(x).energy -= Params.rest_energy_cost;
			CritterWorld.population.get(x).doTimeStep(); 
		}
		updateGrid();
		handleEncounters();
		killCritters();
		populatebabies();
		for(int x=0; x<Params.refresh_algae_count;x++){
			Critter newAlgae = new Algae();
			newAlgae.energy = Params.start_energy;
			newAlgae.x_coord=newAlgae.getRandomInt(Params.world_width);
    		newAlgae.y_coord=newAlgae.getRandomInt(Params.world_height);
    		CritterWorld.population.add(newAlgae);
		}
	}
//------------Critter World Time Step Methods----------	
	//-------Fighting-------
	static void handleEncounters(){
		CritterWorld.fightPhase = true;
		for(int y=0; y<CritterWorld.population.size(); y++){
				for(int x=y+1; x<CritterWorld.population.size(); x++){
					if(CritterWorld.population.get(y).energy> 0 && CritterWorld.population.get(x).energy > 0 && CritterWorld.population.get(x).x_coord == CritterWorld.population.get(y).x_coord && CritterWorld.population.get(x).y_coord== CritterWorld.population.get(y).y_coord){
						int xStrength = 0;
						int yStrength = 0;
						boolean xFight = CritterWorld.population.get(x).fight(CritterWorld.population.get(y).getClass().toString());
						boolean yFight = CritterWorld.population.get(y).fight(CritterWorld.population.get(x).getClass().toString());
						if(xFight){ xStrength = Critter.getRandomInt(CritterWorld.population.get(x).energy);}
						if(yFight){ yStrength = Critter.getRandomInt(CritterWorld.population.get(y).energy);}
						//if it's the same position, neither of them ran away successfully.
						if(CritterWorld.population.get(x).x_coord == CritterWorld.population.get(y).x_coord && CritterWorld.population.get(x).y_coord== CritterWorld.population.get(y).y_coord){
							if(xStrength>=yStrength){
								CritterWorld.population.get(x).energy=(CritterWorld.population.get(x).energy + CritterWorld.population.get(y).energy/2);
								CritterWorld.population.get(y).energy=0;
							}
							else if(yStrength > xStrength){
								CritterWorld.population.get(y).energy=(CritterWorld.population.get(y).energy + CritterWorld.population.get(x).energy/2);
								CritterWorld.population.get(x).energy=0;
							}
						}
					//solve for unsuccessful run away 
					}
				}
		}
		CritterWorld.fightPhase = false;
	}
	//--------------Killing Critters----------
	static void killCritters(){
		for(int x=0; x<CritterWorld.population.size(); x++){
			if(CritterWorld.population.get(x).energy <= 0){
				CritterWorld.population.remove(x--);
			}
		}
	}

	
	//----Reproduction-----
	static void populatebabies() {
		for(int x=0; x<Critter.babies.size();x++){
			CritterWorld.population.add(Critter.babies.get(x));
		}
		
		Critter.babies = new ArrayList<Critter>();	
	}
	
//----------Showing the Grid of the World-------
	public static void displayWorld() {
		System.out.println();
		for(int y=0; y<Params.world_height; y++){
			System.out.print("|");
			for(int x=0; x<Params.world_width; x++){ 
				String output = " ";
				/*if(grid.get(y).get(x)!=0){
					int index=grid.get(y).get(x);
					output=population.get(index).toString();
				}*/
				
				for(int c=0; c<CritterWorld.population.size(); c++){
					if(CritterWorld.population.get(c).x_coord==x && CritterWorld.population.get(c).y_coord==y){
						output = CritterWorld.population.get(c).toString();
						break;
					}
				}
				System.out.print(output);
			}
			System.out.print("|");
			System.out.println();
		}
	}

	static void updateGrid(){
		for(int y=0; y<Params.world_height; y++){
			Map<Integer, ArrayList<Integer> > xKeys=new HashMap<Integer, ArrayList<Integer>>(Params.world_width);
			for(int x=0; x<Params.world_width; x++){ 
				ArrayList<Integer> occupants=new ArrayList<Integer>();
				for(int c=0; c<CritterWorld.population.size(); c++){
					if(CritterWorld.population.get(c).x_coord==x && CritterWorld.population.get(c).y_coord==y){
						occupants.add(c); //add this index
					}
				}
				xKeys.put(x,occupants); //make a bunch of x keys to arrays of indexes
			}
			CritterWorld.grid1.put( y, xKeys); //put the xkeys at that y position			
		} 
	}
}


//----Unused code-----
/*switch (direction){
case 0: if(y_coord == 0){ y_coord = Params.world_height-1;}else{y_coord = (y_coord-1)%Params.world_height;}			  break;
case 1: x_coord=(x_coord+1)%Params.world_width; if(y_coord == 0){ y_coord = Params.world_height;}else{y_coord = (y_coord-1)%Params.world_height;} break;
case 2: x_coord=(x_coord+1)%Params.world_width;		  break;
case 3: x_coord=(x_coord+1)%Params.world_width; y_coord=(y_coord+1)%Params.world_height; break;
case 4: y_coord=(y_coord+1)%Params.world_height; 			  break;
case 5: if(x_coord == 0){x_coord = Params.world_width-1;}else{x_coord = (x_coord-1)%Params.world_width;} 
		y_coord=(y_coord+1)%Params.world_height; break; //what if it is at 0
case 6: if(x_coord == 0){x_coord = Params.world_width-1;}else{x_coord = (x_coord-1)%Params.world_width;} break;
case 7: x_coord=(x_coord+1)%Params.world_width; if(y_coord == 0){ y_coord = Params.world_height;}else{y_coord = (y_coord-1)%Params.world_height;} break;
default: System.out.println("Invalid direction. Try again."); break;
}*/