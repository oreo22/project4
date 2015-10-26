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
	
	public static void runStats(java.util.List<Critter> cassidys) {
		int cassidysAsleep =0;
		int cassidysSmashing =0;
		int cassidysInSchool =0;
		for(Object obj: cassidys){
			Cassidy cas = (Cassidy) obj;
			if(cas.hours >= 0 && cas.hours<=8){
				cassidysAsleep++;
			}
			else if((cas.hours >=10 && cas.hours<=14) || (cas.hours >=16 && cas.hours <= 20)){
				cassidysSmashing++;
			}
			else{
				cassidysInSchool++;
			}
		}
		System.out.println("There are " + cassidysAsleep + " Cassidy(s) sleeping");
		System.out.println("There are " + cassidysSmashing + " Cassidy(s) playing smash");
		System.out.println("There are " + cassidysInSchool + " Cassidy(s) in school");
		
	}

}
