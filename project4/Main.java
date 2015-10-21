package project4;
import java.util.Scanner;

public class Main {

public static void main(String[] args) throws InvalidCritterException {
		//System.out.println("GLHF");
		Scanner game=new Scanner(System.in); //do we need to read from file or keyboard?
		String[] commands=new String[4];
		CritterWorld.makeWorld();
//---------Controller------------------------	
		outerloop:
			while(true){
			
//---------Receiving input-------------------
			System.out.print("critters>");
			String commandLine=game.nextLine();
			commands = commandLine.split(" ");
//---------Avaliable Commands----------------
			switch (commands[0]) {
            case "quit": break outerloop;
            case "show":  Critter.displayWorld(); break;
            case "step":  if(commands.length==1){ CritterWorld.runWorld(1);}
            			  else if(commands.length==2){CritterWorld.runWorld(Integer.parseInt(commands[1]));}
            				break;//put a try catch to see if commands[1] is a number
            case "seed": System.out.print("Work in Progress for seed"); break;
            case "make": System.out.print("Work in Progress for make"); break;
            case "class_name": System.out.print("Work in Progress for class_name"); break;
            default: System.out.println("Invalid command. Try again."); break;
			}
	}
}


}
