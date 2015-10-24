package project4;
import java.util.Scanner;

public class Main {

	
public static void invalidCommand(String commandLine){
	System.out.println("invalid command: " + commandLine);
}

public static void main(String[] args) throws InvalidCritterException {
		//System.out.println("GLHF");
		Scanner game=new Scanner(System.in); //do we need to read from file or keyboard
		//CritterWorld.makeWorld();
//---------Controller------------------------	
		outerloop:
			while(true){
			
//---------Receiving input-------------------
			System.out.print("critters>");
			String commandLine=game.nextLine();
			String[] commands = commandLine.split(" ");
//---------Available Commands----------------
			try{
				
				if(commands.length == 1 && commands[0].equals("quit")){
					 break outerloop;
				}
				else if(commands.length == 1 && commands[0].equals("show")){
					CritterWorld.displayWorld();
				}
				else if(commands.length == 2 && commands[0].equals("stats")){
					Critter.getInstances(commands[1]);
				}
				else if(commands.length == 2 && commands[0].equals("seed")){
					Critter.setSeed(Long.parseLong(commands[1]));
				}
				else if(commands.length <=2 && commands[0].equals("step")){
					if(commands.length==1){ CritterWorld.runWorld(1);}
      			  	else if(commands.length==2){CritterWorld.runWorld(Integer.parseInt(commands[1]));}
				}    
				else if(commands.length <=3 && commands[0].equals("make")){
						int count = 0; 
						if(commands.length == 2){count=1;}
						else{count=Integer.parseInt(commands[2]);}
						for(int x=0; x<count; x++){Critter.makeCritter(commands[1]);}         
				}
				else{ invalidCommand(commandLine);}
			}catch(Throwable x){;
			System.out.println("error processing: " + commandLine);
			}
			
	}
}


}
