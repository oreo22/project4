package project4;

public class Margret extends Critter{
	Margret m_child= new Margret();
	
	public String toString() { return "M"; }

	@Override
	public void doTimeStep() {
		int action=Critter.getRandomInt(2);
		int direction=Critter.getRandomInt(8);
		if( action>=1){
			run(direction);
		}
	}

	@Override
	public boolean fight(String oponent) {
		int direction=Critter.getRandomInt(8);
		int roll= Critter.getRandomInt(5);
		if(oponent== "project4.Craig"){
			return false;
		}
		if(oponent=="project4.Student"){
			return true;
		}
		else{
			if (roll>=3){
				return false;
			}
			else{
				return true;
			}
		}
	}


}
