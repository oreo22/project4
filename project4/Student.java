package project4;

public class Student extends Critter{

	@Override
	public void doTimeStep() {
		walk(this.getEnergy()%8);
		if(this.getEnergy() >100){
			this.reproduce(new Student(), (this.getEnergy()+1)%8);
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		if(oponent.equals("project4.Craig") && this.getEnergy() >= 100){ //Chase is supreme ruler
			return false;
		}
		//Fighting other Critters(TAs)//
		int roll = Critter.getRandomInt(100);
		if(this.getEnergy() >= 90){
			return true;
		}
		else if(this.getEnergy() >=80){
			
			if(roll >= 50){
				return true;
			}
			return false;
		}
		else if(this.getEnergy() >= 70){
			if(roll >= 75){
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
	public static void runStats(java.util.List<Critter> Students) {
		int A =0;
		int B =0;
		int C =0;
		int failing =0;
		for(Object obj: Students){
			Student student = (Student) obj;
			if(student.getEnergy() >= 90){
				A++;
			}
			else if(student.getEnergy() >= 80){
				B++;
			}
			else if (student.getEnergy() >= 70){
				C++;
			}
			else{
				failing++;
			}
		}
		System.out.println("There are " + A + " student(s) with A's in this course");
		System.out.println("There are " + B + " student(s) with B's in this course");
		System.out.println("There are " + C + " student(s) with C's in this course");
		System.out.println("There are " + failing + " student(s) failing this course and should drop");
		
	}
}
