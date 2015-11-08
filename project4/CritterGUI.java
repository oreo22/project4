	package project4;   
	import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
	import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
	import javafx.scene.text.Font;
	import javafx.scene.text.FontWeight;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;
	import javafx.scene.shape.*;
public class CritterGUI extends Application{
	public static Canvas canvas;
	 public class ResizableCanvas extends Canvas {

	        public ResizableCanvas() {
	            // Redraw canvas when size changes.
	            widthProperty().addListener(evt -> draw());
	            heightProperty().addListener(evt -> draw());
	        }

	        private void draw() {
	            double width = getWidth();
	            double height = getHeight();

	            GraphicsContext gc = getGraphicsContext2D();
	            gc.clearRect(0, 0, width, height);
	            
	            Critter.displayWorld();
	            
	           
	    				
	    				
	    			}
	    		
	        

	        @Override
	        public boolean isResizable() {
	            return true;
	        }

	        @Override
	        public double prefWidth(double height) {
	            return getWidth();
	        }

	        @Override
	        public double prefHeight(double width) {
	            return getHeight();
	        }
	    }
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Critter Simulation");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		GridPane stackPane = new GridPane();
		stackPane.getChildren().add(canvas);
		Scene s = new Scene(stackPane, width/2, height/2, Color.WHITE);
		
		canvas.setWidth(width/2);
		canvas.setHeight(height/2);
		
		Critter.displayWorld();
		
	
   

        stackPane.setPadding(new Insets(10,10,10,10));
        StackPane.setAlignment(canvas, Pos.CENTER);
        
		primaryStage.setScene(s);
		
		primaryStage.setWidth(width/2);
		primaryStage.setHeight(height/2);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
	
/* private void clear(Canvas canvas) {
	GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.setFill(Color.WHITE);
	gc.fillRect(0,  0,  canvas.getWidth(), canvas.getHeight());
}*/

}

