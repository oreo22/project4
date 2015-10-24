            case "show":  CritterWorld.displayWorld(); break;
            case "step":  if(commands.length==1){ CritterWorld.runWorld(1);
            			  }
            			  else if(commands.length==2){
            				  //try{
            				  CritterWorld.runWorld(Integer.parseInt(commands[1]));
            				  //}catch (!StringUtils.isNumeric(commands[1])){System.out.print("Incorrect Command "); }
            			   }
            case "show":  Critter.displayWorld(); break;
            case "step":  if(commands.length==1){ CritterWorld.runWorld(1);}
            			  else if(commands.length==2){CritterWorld.runWorld(Integer.parseInt(commands[1]));}
