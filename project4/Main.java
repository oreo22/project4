package project4;
import java.util.List;
import java.util.Scanner;

import javafx.scene.canvas.Canvas;
/* Diego Guerra (dag3222) 
 * Oriana Wong (oyw58)
 */
public class Main {

	
public static void invalidCommand(String commandLine){
	System.out.println("invalid command: " + commandLine);
}

public static void main(String[] args) throws InvalidCritterException {
		CritterGUI.canvas = new Canvas();
		CritterGUI.launch(CritterGUI.class, args);
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
					Object obj = Class.forName(commands[1]).newInstance();
					Class.forName(commands[1]).getMethod("runStats", List.class).invoke(obj, Critter.getInstances(commands[1]));
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
						
						for(int x=0; x<count; x++){
							try{
							Critter.makeCritter(commands[1]);
							}catch(InvalidCritterException e){
								System.out.println(e.toString());
								break;
							}
							
							} 
						
				}
				else{ invalidCommand(commandLine);}
			}catch(Throwable x){;
			System.out.println("error processing: " + commandLine);
			}
			
	}
}


}
