package project4;

import java.awt.Dimension;
import java.awt.Toolkit;

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
	import javafx.util.StringConverter;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;  

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
		primaryStage.setTitle("Critter Simulation");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		Critter.displayWorld();
		Group root = new Group();

		//------Grid of Critters-------
		StackPane stackPane = new StackPane();
		Scene s = new Scene(stackPane, width/2, height/2, Color.WHITE);
		canvas.setWidth(width/2);
		canvas.setHeight(height/2);


//--------------Main Control Panel--------	
		FlowPane controls= new FlowPane(Orientation.HORIZONTAL,10,10);
		//---------Lists for the Dropdown Boxes------
        ObservableList<String> crittersOptions = 
        	    FXCollections.observableArrayList(
        	        "Craig", "Algae","Student","Margret","Cassidy", "Jeho" );
        ObservableList<String> stepOpts = 
        	    FXCollections.observableArrayList(
        	        "1",  "2", "5", "10", "100", "Custom" );
        ObservableList<String> SeedOpts= 
        		FXCollections.observableArrayList(
    	        "1",  "10", "100", "1000", "Custom" );
		//-------DropDown Boxes--------
        final ComboBox<String> critterBox = new ComboBox<String>(crittersOptions);
        critterBox.setPromptText("Critter Type");
        controls.getChildren().add(critterBox);
        
        final ComboBox<String> numberBox = new ComboBox<String>();
		numberBox.setPromptText("Number Selection");
		final TextField subject = new TextField("");
		final TextArea text = new TextArea ("");
		numberBox.setEditable(true);
		/*	numberBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
			address = t1;
			}
			});*/
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
       speed.setLabelFormatter(new StringConverter <Double> () {   //slider settings: 2, 5, 10, 20, 50, 100 
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

       animeCluster.getChildren().add(playbtn);
       animeCluster.getChildren().add(stopbtn);
       animeCluster.getChildren().add(speed);    
       
     //-------Setting up the display----
       //-------Layout of the buttons----- /Diego!/MAKE THIS SCALABLE
       controls.relocate(30,250);
       animeCluster.relocate(0,350);
       critterBox.relocate(30,300);
       numberBox.relocate(critterBox.getWidth()+200,300);
       
       //----adding the controls
       root.getChildren().add(canvas);
       root.getChildren().add(controls);
       root.getChildren().add(animeCluster);
       root.getChildren().add(quitbtn);
       root.getChildren().add(critterBox);
       root.getChildren().add(numberBox);
       //----Adding the grid
       stackPane.setPadding(new Insets(10,10,10,10));
       StackPane.setAlignment(canvas, Pos.CENTER_LEFT);
       stackPane.getChildren().add(canvas);
       root.getChildren().add(stackPane);
       //----Disable unnecessary elements
       critterBox.setDisable(true);
       numberBox.setDisable(true);
       //----ShowTime--------
		primaryStage.setScene(s);
		primaryStage.setWidth(width/2);
		primaryStage.setHeight(height/2);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
       
       
	   //----Make Action
	   	makebtn.setOnAction(new EventHandler<ActionEvent>() {
	       @Override
	       public void handle(ActionEvent box) {
	       	//critterBox selection time
	       }});
	   //----Step Action---
	   stepbtn.setOnAction(new EventHandler<ActionEvent>() {
	       @Override
	       public void handle(ActionEvent e) {
	    	   //create textfield to see selection
	    	   //find out how they'll select it
	       		numberBox.setDisable(false);
	    		numberBox.setItems(stepOpts);
	       		numberBox.setOnAction(new EventHandler<ActionEvent>() {
	       			@Override
	       			public void handle(ActionEvent number) {
	       				String numberChosen=numberBox.getValue();
	       				System.out.print("Selection done");
	       				if(numberChosen.equals("Chosen")){
	       					
	       				}
	       				Integer stepnum=Integer.parseInt(numberChosen);
	       				System.out.print(stepnum);
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

