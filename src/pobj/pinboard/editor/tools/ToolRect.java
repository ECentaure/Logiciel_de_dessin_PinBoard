package pobj.pinboard.editor.tools;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.*;
import pobj.pinboard.editor.EditorInterface;
import javafx.scene.paint.Color;

public class ToolRect implements Tool{
	private double x0;
	private double y0;
	private ClipRect contour_rectangle;
	private ClipRect rectangle;
	private boolean contour;
	
	public void press(EditorInterface i, MouseEvent e) {
		contour = false;
		x0 = e.getX();
		y0 = e.getY();
		contour_rectangle = new ClipRect( x0 ,y0 , e.getX(), e.getY(), Color.BLACK);
		System.out.print("creation rectangle");
	}

	
	public void drag(EditorInterface i, MouseEvent e) {
		
		
		contour = true;
		
		contour_rectangle.setGeometry(Math.min(x0,e.getX()) ,Math.min(y0,e.getY()) , Math.max(x0,e.getX()), Math.max(y0,e.getY()));
		
		
	}

	
	public void release(EditorInterface i, MouseEvent e) {
		contour = false;
	
		ClipRect rectangle = new ClipRect( Math.min(x0,e.getX()) ,Math.min(y0,e.getY()) , Math.max(x0,e.getX()), Math.max(y0,e.getY()), Color.BLACK);
		i.getBoard().addClip(rectangle);
	    
		
	}

	
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
			i.getBoard().draw(gc.getCanvas().getGraphicsContext2D());
			gc.setStroke(Color.BLACK);   //en travaux 
	        gc.setLineWidth(5);

			gc.strokeRect(this.contour_rectangle.getLeft(),this.contour_rectangle.getTop(),this.contour_rectangle.getRight() - this.contour_rectangle.getLeft(),this.contour_rectangle.getBottom() - this.contour_rectangle.getTop());

		
	}

	
	public String getName(EditorInterface editor) {
		return "Filled Rectangle Tool";
	}

}
