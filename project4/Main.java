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
//---------Available Commands----------------
			switch (commands[0]) {
            case "quit": break outerloop;
            case "show":  CritterWorld.displayWorld(); break;
            case "step":  if(commands.length==1){ CritterWorld.runWorld(1);
            			  }
            			  else if(commands.length==2){
            				  //try{
            				  CritterWorld.runWorld(Integer.parseInt(commands[1]));
            				  //}catch (!StringUtils.isNumeric(commands[1])){System.out.print("Incorrect Command "); }
            			   }
            				break;//put a try catch to see if commands[1] is a number
            case "seed": System.out.print("Work in Progress for seed"); break;
            case "make": System.out.print("Work in Progress for make"); break;
            case "class_name": System.out.print("Work in Progress for class_name"); break;
            default: System.out.println("Invalid command. Try again."); break;
			}
	}
}


}
