	package project4;   
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
	   /*class ResizableCanvas extends Canvas {

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
	            
	           
	            
	            for(int n=0; n<Critter.population.size(); n++){
		    				int x = Critter.population.get(n).x_coord;
		    				int y = Critter.population.get(n).y_coord;
		    				Color color = Critter.population.get(n).viewFillColor();
		    				gc.setFill(color);
		    				if(Critter.population.get(n).viewShape().equals( Critter.CritterShape.CIRCLE)){
		    				 gc.fillOval(x*(width/(Params.world_width*2)), y*(height/(Params.world_height)), (width/(Params.world_width*5)), (height/(Params.world_height*3)));
		    				}
	    				
	    				
	    			}
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
	    }*/
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Critter Simulation");
		
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(canvas);
		Scene s = new Scene(stackPane, 500, 500, Color.WHITE);
		
		canvas.widthProperty().bind(stackPane.widthProperty());
		canvas.heightProperty().bind(stackPane.heightProperty());
		Critter.displayWorld();
		
	
      
        //NumberBinding rectsAreaSize = Bindings.min(stackPane.heightProperty(), stackPane.heightProperty());
/*        for(int y=0; y<Params.world_height; y++){
        	for(int x=0; x<Params.world_width; x++ ){
        		Rectangle rectangle = new Rectangle();
        		rectangle.setStroke(Color.BLACK);
        		rectangle.setFill(Color.WHITE);
        		
        		//binding rectangle positions to pane size
        		rectangle.xProperty().bind(rectsAreaSize.multiply(x).divide(Params.world_width));
                rectangle.yProperty().bind(rectsAreaSize.multiply(y).divide(Params.world_height));

                // here we bind rectangle size to pane size 
                rectangle.heightProperty().bind(rectsAreaSize.divide(Params.world_height));
                rectangle.widthProperty().bind(rectsAreaSize.divide(Params.world_height));

                root.getChildren().add(rectangle);
        	}*/
		//root.getChildren().add(canvas);

        stackPane.setPadding(new Insets(10,10,10,10));
        StackPane.setAlignment(canvas, Pos.CENTER_LEFT);
        
		primaryStage.setScene(s);
		
		primaryStage.setWidth(1000);
		primaryStage.setHeight(700);
		primaryStage.show();
	}
	
/* private void clear(Canvas canvas) {
	GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.setFill(Color.WHITE);
	gc.fillRect(0,  0,  canvas.getWidth(), canvas.getHeight());
}*/

}

