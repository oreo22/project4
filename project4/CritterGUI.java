	package project4;
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
		int height=500;
		int width=500;
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		
		Group root = new Group();
		Scene s = new Scene(root, height, width, Color.WHITE);

		final Canvas canvas = new Canvas(250,250);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.LIGHTCYAN);
		gc.fillRect(30, 30, 500, 500);

		
//--------------Main Control Panel--------	
		FlowPane controls= new FlowPane(Orientation.HORIZONTAL,10,10);
		//---------Lists for the Dropdown Boxes------
        ObservableList<String> crittersOptions = 
        	    FXCollections.observableArrayList(
        	        "Craig",
        	        "Algae",
        	        "Student",
        	        "Margret",
        	        "Cassidy",
        	        "Jeho"
        	    );
        
        ObservableList<String> stepOpts = 
        	    FXCollections.observableArrayList(
        	        "1",
        	        "2",
        	        "5",
        	        "10",
        	        "100",
        	        "Custom"
        	    );
		//------Make Button---------
		Button makebtn = new Button("Make"); //gotta change this to make it scalable
		controls.getChildren().add(makebtn);
	    makebtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent box) {
	                final ComboBox<String> critterBox = new ComboBox<String>(crittersOptions);
	                critterBox.setPromptText("Critter Type");
	                controls.getChildren().add(critterBox);
	                critterBox.setOnAction(new EventHandler<ActionEvent>() {
	                	String critterChosen=critterBox.getValue();

						@Override
						public void handle(ActionEvent number) {
							final ComboBox<String> numberBox = new ComboBox<String>(stepOpts);
							numberBox.setPromptText("Number of Critter");
			                controls.getChildren().add(numberBox);
							
						}
	                	
	                });
	            }
	    });
        
		//------Step Button---------
        Button stepbtn = new Button("Step");
        controls.getChildren().add(stepbtn);
        stepbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//Does step stuff
            }
        });
        


		//------Seed Button---------
       Button seedbtn = new Button("Seed");
       controls.getChildren().add(seedbtn);
		//------Stats Button--------
       Button statsbtn = new Button("Stats");
       controls.getChildren().add(statsbtn);
       
       
        
		//------Animation Cluster----
        FlowPane animeCluster=new FlowPane(Orientation.HORIZONTAL, 10,0);
        animeCluster.setAlignment(Pos.BOTTOM_CENTER);
        Button stop = new Button();
        Rectangle stopshape=new Rectangle(3, 3, Color.DARKRED);
        stop.setShape(stopshape);
        Rectangle playshape=new Rectangle(3,3, Color.LIMEGREEN);
       // playshape.setFill(Color.LIMEGREEN);
        //HOW DO I MAKE A SIMPLE TRIANGLE?!
        Button play = new Button();
        play.setShape(playshape);
        animeCluster.getChildren().add(stop);
        animeCluster.getChildren().add(play);
        
        Slider speed = new Slider(2,5,10);
        speed.setShowTickMarks(true);
        speed.setShowTickLabels(true);
        speed.setMajorTickUnit(5);
        speed.setMax(100);
        speed.setMin(2);
        animeCluster.getChildren().add(speed);
        //2, 5, 10, 20, 50, 100 
  
        
		//------Quit Button---------
        Button quitbtn=new Button("X");
        quitbtn.setTextFill(Color.RED);
        quitbtn.setAlignment(Pos.TOP_LEFT);
        quitbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	System.exit(0);
            }
        });
		
		root.getChildren().add(canvas);
		controls.setLayoutX(30);
		controls.setLayoutY(250);
		animeCluster.setLayoutX(30);
		animeCluster.setLayoutY(300);
		root.getChildren().add(controls);
		root.getChildren().add(animeCluster);
		root.getChildren().add(quitbtn);
		primaryStage.setScene(s);
		primaryStage.show();

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
