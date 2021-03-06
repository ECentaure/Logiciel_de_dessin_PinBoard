package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.document.*;
public class ToolEllipse implements Tool{
	private double x0;
	private double y0;
	private ClipEllipse contour_ellipse;
	private ClipEllipse ellipse;
	
	public void press(EditorInterface i, MouseEvent e) {
		x0 = e.getX();
		y0 = e.getY();
		contour_ellipse = new ClipEllipse( x0 ,y0 , e.getX(), e.getY(), Color.RED);

	}

	
	public void drag(EditorInterface i, MouseEvent e) {
		
		contour_ellipse.setGeometry(Math.min(x0,e.getX()) ,Math.min(y0,e.getY()) , Math.max(x0,e.getX()), Math.max(y0,e.getY()));
		
	}

	
	public void release(EditorInterface i, MouseEvent e) {
		 ellipse = new ClipEllipse( Math.min(x0,e.getX()) ,Math.min(y0,e.getY()) , Math.max(x0,e.getX()), Math.max(y0,e.getY()), Color.BLACK);
		 //i.getBoard().addClip(ellipse);
		 
		 CommandAdd c_add = new CommandAdd(i,ellipse);
		 i.getUndoStack().addCommand(c_add);
		 c_add.execute();
	}

	
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
	
		i.getBoard().draw(gc.getCanvas().getGraphicsContext2D());
		gc.setStroke(Color.BLACK);   //en travaux 
        gc.setLineWidth(5);

		gc.strokeOval(this.contour_ellipse.getLeft(),this.contour_ellipse.getTop(),this.contour_ellipse.getRight() - this.contour_ellipse.getLeft(),this.contour_ellipse.getBottom() - this.contour_ellipse.getTop());
		
		
		
	}

	
	public String getName(EditorInterface editor) {
		return "Filled Rectangle Tool";
	}

}
