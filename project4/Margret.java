package project4;
/* Diego Guerra (dag3222) 
 * Oriana Wong (oyw58)
 */

/*Description: Margret is Margret.
 *doTimeStep: Margret runs in random directions if the dice roll is 0. Otherwise, they do nothing
 * Fight: Margret fights Craig if the dice roll is 0 or 1. 
 * Otherwise, they fight everyone.
 * Stats: The stats tell you how many Margrets there are in the world. 
 */
public class Margret extends Critter{
	public String toString() { return "M"; }

	@Override
	public void doTimeStep() {
		int action=Critter.getRandomInt(2);
		int direction=Critter.getRandomInt(8);
		if( action==0){
			run(direction);
		}
	}

	@Override
	public boolean fight(String oponent) {
		int roll= Critter.getRandomInt(5);
		if(oponent== "project4.Craig" && roll>=2){
			return false;
		}
		return true; //always fights
	}

	public static void runStats(java.util.List<Critter> Margrets) {
		
		System.out.println("There are " + Margrets.size() + " Margret(s) hunting down students");
		
	}

}
