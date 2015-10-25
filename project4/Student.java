package project4;

public class Student extends Critter{

	@Override
	public void doTimeStep() {
		walk(this.getEnergy()%8);
		if(this.getEnergy() >=200){
			this.reproduce(new Student(), (this.getEnergy()+1)%8);
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		if(oponent.equals("project4.Craig")){ //Chase is supreme ruler
			return false;
		}
		//Fighting other Critters(TAs)//
		int roll = Critter.getRandomInt(100);
		if(this.getEnergy() >= 150){
			return true;
		}
		else if(this.getEnergy() >=100){
			
			if(roll >= 50){
				return true;
			}
			return false;
		}
		else if(this.getEnergy() >= 50){
			if(roll >= 30){
				return true;
			}
			return false;
		}
		run(this.getEnergy()%8);
		return false;
	}
	public String toString(){
		return "S";
	}

}
