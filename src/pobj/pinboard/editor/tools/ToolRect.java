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
	
	public void press(EditorInterface i, MouseEvent e) {
		x0 = e.getX();
		y0 = e.getY();
		ClipRect rectangle = new ClipRect( x0 ,y0 , e.getX(), e.getY(), Color.BLACK);
	}

	
	public void drag(EditorInterface i, MouseEvent e) {
		i.getBoard();
		rectangle.setGeometry( left + e.getX(),  top + e.getY(),  right+e.getX(),   + e.getY());
		
	}

	
	public void release(EditorInterface i, MouseEvent e) {
		ClipRect rectangle = new ClipRect( x0 ,y0 , e.getX(), e.getY(), Color.BLACK);
		i.getBoard().addClip(rectangle);
		
	}

	
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(Color.BLUE);
		i.getBoard().draw(gc);
		
	}

	
	public String getName(EditorInterface editor) {
		return "Filled Rectangle Tool";
	}

}
