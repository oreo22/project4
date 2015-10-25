package project4;

public class Jeho extends Critter{
	Jeho child = new Jeho();

	public String toString() { return "J"; }

	@Override
	public void doTimeStep() {
		int action=Critter.getRandomInt(3);
		int direction=Critter.getRandomInt(8);
		if( action==0){
			run(direction);
		}
		else if (action==1){
			walk(direction);
		}
		else{
			if (getEnergy() > 250) {
				reproduce(child, Critter.getRandomInt(8));
			}
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		int direction=Critter.getRandomInt(8);
		int roll= Critter.getRandomInt(5);
		if(oponent== "project4.Craig"){
			return true;
		}
		else if(oponent=="project4.Student"){
			if(roll>=3){
				return true;
			}
			else{
				reproduce(child, Critter.getRandomInt(8));
				return false;
			}
		}
		else{
			return true; 
		}
		// TODO Auto-generated method stub
	}

}
