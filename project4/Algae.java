package project4;
/* Diego Guerra (dag3222) 
 * Oriana Wong (oyw58)
 */
import project4.Critter.TestCritter;

public class Algae extends TestCritter {
	

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
}
