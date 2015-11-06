	package project4;
	import javafx.application.Application;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.HBox;
	import javafx.scene.paint.Color;
	import javafx.scene.text.Font;
	import javafx.scene.text.FontWeight;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;
public class CritterGUI {

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Critter Simulation");
			
		GridPane screen = new GridPane(); //makes the user-interactive part
		        //smaller part of the scene
		        //Other panes exist
		screen.setAlignment(Pos.CENTER_LEFT);
		screen.setHgap(Params.world_height); //sets the height
		screen.setVgap(Params.world_width); //sets the width? 
		screen.setPadding(new Insets(25, 25, 25, 25)); 
		     
		
		      //Scene scene = new Scene(screen, 500, 500); //the whole window
		primaryStage.setWidth(Params.world_width*2);
		primaryStage.setHeight(Params.world_height*2);
		primaryStage.show();
	}
/* private void clear(Canvas canvas) {
	GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.setFill(Color.WHITE);
	gc.fillRect(0,  0,  canvas.getWidth(), canvas.getHeight());
}*/


}
