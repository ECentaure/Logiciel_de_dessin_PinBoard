package pobj.pinboard.editor.tools;

import com.sun.glass.events.KeyEvent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class ToolSelection implements Tool {

	private double click_x0;
	private double click_y0;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		click_x0 = e.getX();
		click_y0 = e.getY();

		
		if(e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(), e.getX(),e.getY());
		}else {
			i.getSelection().clear();
			i.getSelection().select(i.getBoard(), e.getX(),e.getY());
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		double deplacement_x =  e.getX() - click_x0 ;
		double deplacement_y = e.getY() - click_y0 ;
		click_x0 = e.getX();
		click_y0 = e.getY();
		
		for(Clip element : i.getSelection().getContents()) {
			
			element.setGeometry(element.getLeft() + deplacement_x ,element.getTop() + deplacement_y,element.getRight() + deplacement_x,element.getBottom() + deplacement_y);
			
			i.getBoard().addClip(element);
		}
	}

	
	public void release(EditorInterface i, MouseEvent e) {

	}

	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		
		return "Selection";
	}
	
}

	