package project4;

	import java.awt.Dimension;
	import java.awt.Toolkit;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.geometry.Orientation;
	import javafx.geometry.Pos;
	import javafx.scene.Group;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.canvas.Canvas;
	import javafx.scene.canvas.GraphicsContext;
	import javafx.scene.shape.*;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.Slider;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.Pane;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.StackPane;
	import javafx.scene.layout.FlowPane;
	import javafx.scene.paint.Color;
	import javafx.scene.text.Font;
	import javafx.scene.text.FontWeight;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;  

/*
 * RunStats Constantly
 * Make needs numbers
 * Set Seed
 * Animation Speed
 * Reset?
 * Show?*/
public class CritterGUI extends Application{
	public static Canvas canvas;
	
	
/*	
        stackPane.setPadding(new Insets(10,10,10,10));
        StackPane.setAlignment(canvas, Pos.CENTER);
        
		primaryStage.setScene(s);
		
		primaryStage.setWidth(width/2);
		primaryStage.setHeight(height/2);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();*/
	public void start(Stage primaryStage) {
		System.setProperty("glass.accessible.force", "false");
		primaryStage.setTitle("Critter Simulation");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
	/*	double width = 1000;
		double height = 1000;*/
		Group root = new Group();

		//------Grid of Critters-------
		StackPane stackPane = new StackPane();
		
		Scene s = new Scene(root, width/2, height/2, Color.WHITE);
		canvas.setWidth(width/2);
		canvas.setHeight((height-height/64)/2);
	
		

//--------------Main Control Panel--------	
		FlowPane controls= new FlowPane(Orientation.HORIZONTAL,width/86,0);
		//---------Lists for the Dropdown Boxes------
        ObservableList<String> crittersOptions = 
        	    FXCollections.observableArrayList(
        	        "Craig", "Algae","Student","Margret","Cassidy", "Jeho" );
        ObservableList<String> makeOpts = //custom is from the editable field
        	    FXCollections.observableArrayList(
        	        "1", "2", "5", "25", "50", "100" );
        ObservableList<String> stepOpts = //custom is from the editable field
        		//are we allowed to put 10 here?
        	    FXCollections.observableArrayList(
        	        "1", "10", "100", "1000" );
		//-------DropDown Boxes--------
        final ComboBox<String> critterBox = new ComboBox<String>(crittersOptions);
        critterBox.setPromptText("Critter Type");
      
        
        final ComboBox<String> numberBox = new ComboBox<String>();
		numberBox.setPromptText("Number Selection");
		numberBox.setEditable(true);
		
		
		//-------Buttons-------
        //------Make Button---------\
		double size = (width/100);
		Button makebtn = new Button("Make"); //gotta change this to make it scalable
		makebtn.setStyle("-fx-font: " + size + " arial;");
		controls.getChildren().add(makebtn);
		//------Step Button---------
        Button stepbtn = new Button("Step");
        stepbtn.setStyle("-fx-font: " + size + " arial;");
        controls.getChildren().add(stepbtn);
		//------Seed Button---------
	    Button seedbtn = new Button("Seed");
	    
	    seedbtn.setStyle("-fx-font: " + size + " arial;");
	    controls.getChildren().add(seedbtn);
		//------Stats Button--------
	    Button statsbtn = new Button("Stats");
	    
	    statsbtn.setStyle("-fx-font: " + size + " arial;");
	    controls.getChildren().add(statsbtn);
       
		//------Quit Button---------
       Button quitbtn=new Button("X");
       quitbtn.setTextFill(Color.RED);
     //  quitbtn.setAlignment(Pos.BOTTOM_RIGHT);
       quitbtn.relocate((width-(width/16))/2, 0);
       
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
       speed.setLabelFormatter(new StringConverter <Double> () {   //slider settings: 2, 5, 10, 20, 50, 100 
           @Override
           public String toString(Double n) {
               if (n < 16) return "2";
               else if (n <= 32 && n > 16 ) return "5";
               else if (n <= 48 && n > 32) return "10";
               else if (n <= 64 && n > 48) return "20";
               else if (n <= 80 && n > 64) return "50";
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

       animeCluster.getChildren().add(playbtn);
       animeCluster.getChildren().add(stopbtn);
       animeCluster.getChildren().add(speed);    
       
     //-------Setting up the display----
       //-------Layout of the buttons----- /Diego!/MAKE THIS SCALABLE
       controls.relocate(width * 9/32,height/5);
       animeCluster.relocate(width/5,height/3);
       critterBox.relocate(width * 17/64,height/4);
       //textSubmit.relocate(critterBox.getWidth()+800,300);
       numberBox.relocate(width*117/320,height/4);
       //customValues.relocate(numberBox.getLayoutX(), numberBox.getLayoutY());
       
       //----adding the controls
       root.getChildren().add(stackPane);
       root.getChildren().add(controls);
       root.getChildren().add(animeCluster);
       root.getChildren().add(quitbtn);
       root.getChildren().add(critterBox);
       root.getChildren().add(numberBox);
       //----Adding the grid
       stackPane.setPadding(new Insets(0,0,0,width/(width*2)));
       StackPane.setAlignment(canvas, Pos.CENTER_LEFT);
       stackPane.getChildren().add(canvas);
       
       //----Disable unnecessary elements
       critterBox.setDisable(true);
       numberBox.setDisable(true);
       
       //----ShowTime--------
		primaryStage.setScene(s);
		primaryStage.setWidth(width/2);
		primaryStage.setHeight(height/2);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		Critter.displayWorld();
		primaryStage.show();
       
       
	   //----Make Action
	   	makebtn.setOnAction(new EventHandler<ActionEvent>() {
	       @Override
	       public void handle(ActionEvent box) {
	    	   	controls.setDisable(true);
	    	   	animeCluster.setDisable(true);
	       		critterBox.setDisable(false);
	       		critterBox.setItems(crittersOptions);
	       		critterBox.getSelectionModel().clearSelection();
	       		critterBox.setOnAction(new EventHandler<ActionEvent>() {
	       			@Override
	       			public void handle(ActionEvent number) {
	       				String critterChoosen="project4." + critterBox.getSelectionModel().getSelectedItem();
	    	       		numberBox.setDisable(false);
	    	       		numberBox.setItems(makeOpts);
	    	    		numberBox.setPromptText("Choose/Enter a Number");
	    	    		numberBox.getSelectionModel().clearSelection();
	    	       		numberBox.setOnAction(new EventHandler<ActionEvent>() {
	    	       			@Override
	    	       			public void handle(ActionEvent number) {
	    	       				String numberChosen = null;
	    	       				 numberChosen= numberBox.getSelectionModel().getSelectedItem();
	    	       				if(numberChosen != null){
	    	       					Integer stepnum=Integer.parseInt(numberChosen);	
		    	       				for(int x=0; x<stepnum; x++){
			    	       				try {
			    							Critter.makeCritter(critterChoosen);
			    						} catch (Throwable t) {
			    						}
		    	       				}
		    	       				Critter.displayWorld();
		    	       				numberBox.setDisable(true);
		    	       				critterBox.setDisable(true);
		    	       				animeCluster.setDisable(false);
		    	       				controls.setDisable(false);
		    	       			}
	    	       			}
	    	       		});	
	       			}
	       		});	
	       }
	   });
	   //----Step Action---
	   stepbtn.setOnAction(new EventHandler<ActionEvent>() {
	       @Override
	       public void handle(ActionEvent e) {
	    	   	controls.setDisable(true);
	    	   	animeCluster.setDisable(true);
	       		numberBox.setDisable(false);
	       		numberBox.setItems(stepOpts);
	       		//numberBox.getSelectionModel().clearSelection();
	    		numberBox.setPromptText("Choose/Enter a Number");
	    		numberBox.getSelectionModel().clearSelection();
	       		numberBox.setOnAction(new EventHandler<ActionEvent>() {
	       			@Override
	       			public void handle(ActionEvent number) {
	       				String numberChosen = null;
	       				
	       				 numberChosen= numberBox.getSelectionModel().getSelectedItem();
	       				if(numberChosen != null){
	       					Integer stepnum=Integer.parseInt(numberChosen);
	       					CritterWorld.runWorld(stepnum);
	       					Critter.displayWorld();
	       					animeCluster.setDisable(false);
	       					controls.setDisable(false);
	       				}
	       			}
	       		});	       		
	       }
	       });

	   //----Seed Action---
	   seedbtn.setOnAction(new EventHandler<ActionEvent>() {
	       @Override
	       public void handle(ActionEvent e) {
	    	   	controls.setDisable(true);
	    	   	animeCluster.setDisable(true);
	       		numberBox.setDisable(false);
	      		//numberBox.getSelectionModel().clearSelection();
	       		
	    		numberBox.setItems(stepOpts);
	    		numberBox.setPromptText("Choose/Enter a Number");
	    		numberBox.getSelectionModel().clearSelection();
	       		numberBox.setOnAction(new EventHandler<ActionEvent>() {
	       			@Override
	       			public void handle(ActionEvent number) {
	       				String numberChosen = null;
	       				
	       				 numberChosen= numberBox.getSelectionModel().getSelectedItem();
	       				if(numberChosen != null){
	       					Integer stepnum=Integer.parseInt(numberChosen);
	       					Critter.setSeed(stepnum);
	       					numberBox.setDisable(true);
	       					animeCluster.setDisable(false);
	       					controls.setDisable(false);
	       				}
	       			}
	       		});	       		
	       }
	       });
	   
	   //----Stats Action---
	   /*Object obj = Class.forName(commands[1]).newInstance();
	   Class.forName(commands[1]).getMethod("runStats", List.class).invoke(obj, Critter.getInstances(commands[1]));
	*/
	   
	   //-----AnimeClusterAction------
	   
	   playbtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	
	            	
	            	controls.setDisable(true);
	            	int animationTime = (int)speedValue(speed.getValue());
	            		
	            	//Keep running until stop button is pressed//
	            		
	            	Timeline timeline = new Timeline(
	                        new KeyFrame(Duration.millis(250), ae -> CritterWorld.runWorld(animationTime)),
	                        new KeyFrame(Duration.millis(250), ae -> Critter.displayWorld())
	                    );
	                    timeline.setAutoReverse(true);
	                    timeline.setCycleCount(Timeline.INDEFINITE);

	                  
	                    timeline.play();
	                	playbtn.setDisable(true);
	                	speed.setDisable(true);
	                    
	                    
	                    stopbtn.setOnAction(new EventHandler<ActionEvent>() {
	        	            @Override
	        	            public void handle(ActionEvent e) {
	        	            	controls.setDisable(false);
	        	            	speed.setDisable(false);
	        	            	playbtn.setDisable(false);
	        	            	timeline.stop();

	        	            }
	        	   });
	            	
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
	public double speedValue(double n){
        if (n < 16) return 2.0;
        else if (n <= 32 && n > 16 ) return 5.0;
        else if (n <= 48 && n > 32) return 10.0;
        else if (n <= 64 && n > 48) return 20.0;
        else if (n <= 80 && n > 64) return 50.0;
        else { return 100.0;}
	}
}

		/*critterBox.setDisable(false);
		critterBox.setValue("Craig");
		critterBox.setOnAction(new EventHandler<ActionEvent>() {
	    	
			@Override
			public void handle(ActionEvent number) {
				String critterChosen=critterBox.getValue();
				String critterFullName="project4."+critterChosen;
				//how do i get this value out of the method?
			}
	    	
	    });   
		critterBox.setDisable(true);*/
