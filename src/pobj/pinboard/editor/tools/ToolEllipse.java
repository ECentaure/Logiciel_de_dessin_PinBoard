package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.document.*;
public class ToolEllipse implements Tool{
	private double x0;
	private double y0;
	private ClipEllipse contour_ellipse;
	private ClipEllipse ellipse;
	
	public void press(EditorInterface i, MouseEvent e) {
		x0 = e.getX();
		y0 = e.getY();
		contour_ellipse = new ClipEllipse( x0 ,y0 , e.getX(), e.getY(), Color.BLACK);
	}

	
	public void drag(EditorInterface i, MouseEvent e) {
		i.getBoard();
	
		contour_ellipse.setGeometry( e.getX() + x0,  y0 ,  x0 +e.getX(),  y0 + e.getY());
		
	}

	
	public void release(EditorInterface i, MouseEvent e) {
		 ellipse = new ClipEllipse( Math.min(x0,e.getX()) ,Math.min(y0,e.getY()) , Math.max(x0,e.getX()), Math.max(y0,e.getY()), Color.BLACK);
		i.getBoard().addClip(ellipse);
		
	}

	
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(Color.BLUE);
		i.getBoard().draw(gc);
		
	}

	
	public String getName(EditorInterface editor) {
		return "Filled Rectangle Tool";
	}

}
