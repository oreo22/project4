package project4;

public class Jeho extends Critter{
	String degree;
	
	public Jeho(){
		degree = "masters";
	}
	
	public String toString() { return "J"; }

	@Override
	public void doTimeStep() {
		if(getEnergy()<=50){
			this.degree = "bachelors";
		}
		else if(getEnergy()<=75){
			this.degree = "masters";
		}
		else if(getEnergy() >= 120){
			this.degree = "phd";
		}
		int action=Critter.getRandomInt(4);
		int direction=Critter.getRandomInt(8);
		if( action==0){
			run(direction);
		}
		else if (action==1){
			walk(direction);
		}
		else{
				reproduce(new Jeho(), Critter.getRandomInt(8));
			
		}
		
	}

	@Override
	public boolean fight(String oponent) {;
		int roll= Critter.getRandomInt(5);
		if(oponent.equals("project4.Craig") && degree.equals("phd")){
			return true;
		}
		else if((oponent.equals("project4.Cassidy") || oponent.equals("project4.Margret")) && degree.equals("masters")){
			return true;
		}
		else if(oponent.equals("project4.Student")){
			if(degree.equals("phd") || degree.equals("masters")){
				return true;
			}
			else if(roll>=3){
				return true;
			}
				reproduce(new Jeho(), Critter.getRandomInt(8));
				walk(0);
				return false;
			}
		else if(oponent.equals("project4.Algae")){
			return true;
		}
		else{
			return false; 
		}
	}

}
