package project4;

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
