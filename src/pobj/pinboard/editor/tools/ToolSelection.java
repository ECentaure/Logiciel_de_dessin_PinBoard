package pobj.pinboard.editor.tools;

import com.sun.glass.events.KeyEvent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.Clipboard;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool {

	private double click_x0;
	private double click_y0;
	private ClipRect contour_rectangle;
	private double x0;
	private double y0;
	private double xn;
	private double yn;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		click_x0 = e.getX();
		click_y0 = e.getY();

		
		if(e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(), e.getX(),e.getY());
			 
			
		}else {
			//i.getSelection().clear();
			i.getSelection().select(i.getBoard(), e.getX(),e.getY());
		}
		
		if(!i.getSelection().getContents().isEmpty()) {
			Clip elem = i.getSelection().getContents().get(0);
			x0 = elem.getLeft();
			y0 = elem.getTop();
			xn = elem.getRight();
			yn = elem.getBottom();
			contour_rectangle = new ClipRect( x0,y0,xn,yn, Color.BLUE);
		}

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		double deplacement_x =  e.getX() - click_x0 ;
		double deplacement_y = e.getY() - click_y0 ;
		click_x0 = e.getX();
		click_y0 = e.getY();
		
		for(Clip element : i.getSelection().getContents()) {
			
			//element.setGeometry(element.getLeft() + deplacement_x ,element.getTop() + deplacement_y,element.getRight() + deplacement_x,element.getBottom() + deplacement_y);
			element.move(deplacement_x, deplacement_y);
			contour_rectangle.setGeometry(element.getLeft(),element.getTop(),element.getRight(),element.getBottom());
			i.getBoard().addClip(element);
		}
	}

	
	public void release(EditorInterface i, MouseEvent e) {
		
		CommandMove c_move = new CommandMove(i,Clipboard.getInstance().copyFromClipboard(),x0 , y0);
		i.getUndoStack().addCommand(c_move);
		c_move.execute();
	}

	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
		i.getBoard().draw(gc.getCanvas().getGraphicsContext2D());

        gc.setLineWidth(5);
        gc.setStroke(Color.BLUE);
		gc.strokeRect(contour_rectangle.getLeft(),contour_rectangle.getTop(),contour_rectangle.getRight() - contour_rectangle.getLeft(),this.contour_rectangle.getBottom() - this.contour_rectangle.getTop());

		
	}

	@Override
	public String getName(EditorInterface editor) {
		
		return "Selection";
	}
	
}

	