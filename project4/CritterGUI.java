	package project4;
	import javafx.util.StringConverter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.canvas.Canvas;
	import javafx.scene.canvas.GraphicsContext;
	import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
	import javafx.scene.paint.Color;
	import javafx.scene.text.Font;
	import javafx.scene.text.FontWeight;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;
	import javafx.scene.Group;
	import javafx.scene.shape.*;
public class CritterGUI extends Application{


	public void start(Stage primaryStage) {
		primaryStage.setTitle("Critter Simulation");
		int height=500;   int width=500;
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		Group root = new Group();
		Scene s = new Scene(root, height, width, Color.WHITE);

		//------where the grid will be-------
		final Canvas canvas = new Canvas(250,250);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.LIGHTCYAN);
		gc.fillRect(30, 30, 500, 500);
		///------DELETE AND REPLACE WITH GRID STUFF------

		
//--------------Main Control Panel--------	
		FlowPane controls= new FlowPane(Orientation.HORIZONTAL,10,10);
		//---------Lists for the Dropdown Boxes------
        ObservableList<String> crittersOptions = 
        	    FXCollections.observableArrayList(
        	        "Craig", "Algae","Student","Margret","Cassidy", "Jeho" );
        
        ObservableList<String> stepOpts = 
        	    FXCollections.observableArrayList(
        	        "1",  "2", "5", "10", "100", "Custom" );
		//-------DropDown Boxes--------
        final ComboBox<String> critterBox = new ComboBox<String>(crittersOptions);
        critterBox.setPromptText("Critter Type");
        controls.getChildren().add(critterBox);
        
        final ComboBox<String> numberBox = new ComboBox<String>(stepOpts);
		numberBox.setPromptText("Number Selection");
		controls.getChildren().add(numberBox);
 
		//-------Buttons-------
        //------Make Button---------
		Button makebtn = new Button("Make"); //gotta change this to make it scalable
		controls.getChildren().add(makebtn);
		//------Step Button---------
        Button stepbtn = new Button("Step");
        controls.getChildren().add(stepbtn);
		//------Seed Button---------
	    Button seedbtn = new Button("Seed");
	    controls.getChildren().add(seedbtn);
		//------Stats Button--------
	    Button statsbtn = new Button("Stats");
	    controls.getChildren().add(statsbtn);
       
		//------Quit Button---------
       Button quitbtn=new Button("X");
       quitbtn.setTextFill(Color.RED);
       quitbtn.setAlignment(Pos.TOP_LEFT);
       
       //------Animation Cluster----
       FlowPane animeCluster=new FlowPane(Orientation.HORIZONTAL, 10,0);
       animeCluster.setAlignment(Pos.BOTTOM_CENTER);
       Button stopbtn = new Button("o");
       Button playbtn = new Button(">");
       Slider speed = new Slider(2,100,5);
       speed.setMax(100);
       speed.setMin(2);
       speed.setValue(5);
       speed.setMinorTickCount(0);
       speed.setMajorTickUnit(25);
       speed.setSnapToTicks(true);
       speed.setShowTickMarks(true);
       speed.setShowTickLabels(true);
       speed.setLabelFormatter(new StringConverter <Double> () {
           @Override
           public String toString(Double n) {
               if (n < 16) return "2";
               if (n <= 32 && n > 16 ) return "5";
               if (n <= 48 && n > 32) return "10";
               if (n <= 64 && n > 48) return "20";
               if (n <= 80 && n > 64) return "50";
               return "100";
           }

           @Override
           public Double fromString(String s) {
               switch (s) {
                   case "2":
                       return 2.0;
                   case "5":
                       return 5.0;
                   case "10":
                       return 10.0;
                   case "20":
                       return 20.0;
                   case "50":
                	   return 50.0;

                   default:
                       return 100.0;
               }
           }
       });
       //slider settings: 2, 5, 10, 20, 50, 100 
       animeCluster.getChildren().add(playbtn);
       animeCluster.getChildren().add(stopbtn);
       animeCluster.getChildren().add(speed);    
       
       //-------Layout of the buttons-----
       //MAKE THIS SCALABLE
       controls.setLayoutX(30);
       controls.setLayoutY(250);
       animeCluster.setLayoutX(30);
       animeCluster.setLayoutY(300);
       
       //-------Setting up the display----
       root.getChildren().add(canvas);
       root.getChildren().add(controls);
       root.getChildren().add(animeCluster);
       root.getChildren().add(quitbtn);
       root.getChildren().add(critterBox);
       root.getChildren().add(numberBox);
       critterBox.setDisable(true);
       numberBox.setDisable(true);
       primaryStage.setScene(s);
       primaryStage.show();
       
       
	   //----Make Action
	   	makebtn.setOnAction(new EventHandler<ActionEvent>() {
	       @Override
	       public void handle(ActionEvent box) {
	       	critDropAction(critterBox);
	       }});
	   //----Step Action---
	   stepbtn.setOnAction(new EventHandler<ActionEvent>() {
	       @Override
	       public void handle(ActionEvent e) {
	       		numberBox.setDisable(false);
	       		numberBox.setOnAction(new EventHandler<ActionEvent>() {
	       			@Override
	       			public void handle(ActionEvent number) {
	       				String numberChosen=numberBox.getValue();
	       				Integer stepnum=Integer.parseInt(numberChosen);
	       				CritterWorld.runWorld(stepnum);//idk if this is the best decision...
	       			}
	       		});	       		
	       }
	       });
		
	   //-----AnimeClusterAction------
	   stopbtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	controls.setDisable(true);
	            	if(playbtn.isPressed()){
	            		controls.setDisable(false);
	            	}
	            }
	   });
	   playbtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	
	            }
	   });
	  
	        //-----Quit Button Action
	  quitbtn.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent e) {
	        	System.exit(0);
	        }
	  });

        


	}
	
	
private void makeAction(Button makebtn){
	
}

private void critDropAction(ComboBox<String> critterBox){
	critterBox.setDisable(false);
	critterBox.setValue("Craig");
	critterBox.setOnAction(new EventHandler<ActionEvent>() {
    	
		@Override
		public void handle(ActionEvent number) {
			String critterChosen=critterBox.getValue();
			String critterFullName="project4."+critterChosen;
			//how do i get this value out of the method?
		}
    	
    });   
	critterBox.setDisable(true);
}

/* private void clear(Canvas canvas) {
	GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.setFill(Color.WHITE);
	gc.fillRect(0,  0,  canvas.getWidth(), canvas.getHeight());
}*/

	/*screen.setAlignment(Pos.CENTER_LEFT);
	screen.setHgap(500); //sets the height
	screen.setVgap(500); //sets the width? 
	screen.setPadding(new Insets(25, 25, 25, 25)); */
}
