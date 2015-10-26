package project4;

public class Cassidy extends Critter {
	private int hours;

	public Cassidy(){
		this.hours = 0;
	}
	
	@Override
	public void doTimeStep() {
		hours = (hours+1)%24;
		walk(hours%8);
		walk((hours+1)%8);
		if(hours >= 0 && hours <= 8){ //sleeping hours
			this.reproduce(new Cassidy(), (hours+2)%8);
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		if(oponent.equals("project4.Craig")){
			return false;
		}
		else if((hours >=10 && hours<=14) || (hours >=16 && hours <= 20) || oponent.equals("project4.Algae")){ //smash hours
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "Ç";
	}

}
