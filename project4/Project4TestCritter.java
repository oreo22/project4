package project4;

import project4.Critter.TestCritter;

public class Project4TestCritter extends TestCritter{

	@Override
	public void doTimeStep() {
		walk(4);
		this.setEnergy(1000);
		
	}
	
	@Override
	public boolean fight(String oponent) {
		return true;
	}
	public String toString(){
		return "Y";
	}

}
