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
	import javafx.scene.shape.*;
public class CritterGUI extends Application{

	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Critter Simulation");
			
		GridPane grid = new GridPane(); //makes the user-interactive part
		        //smaller part of the scene
		        //Other panes exist
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPrefSize(20, 20);
		primaryStage.setWidth(Params.world_width*5);
		primaryStage.setHeight(Params.world_height*5);
		
		grid.setGridLinesVisible(true);
		//screen.getChildren().get(0).setStyle("-fx-background-color: cornsilk; -fx-alignment: center;");
		Scene scene = new Scene(grid, 300, 275);
		grid.add(new Circle(5), 0, 0);
		grid.add(new Circle(5), 5, 0);
		grid.add(new Circle(5), 15, 2);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
/* private void clear(Canvas canvas) {
	GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.setFill(Color.WHITE);
	gc.fillRect(0,  0,  canvas.getWidth(), canvas.getHeight());
}*/


}
