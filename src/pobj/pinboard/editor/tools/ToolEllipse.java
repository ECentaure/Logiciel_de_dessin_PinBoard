package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.document.*;
public class ToolEllipse implements Tool{
	
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void drag(EditorInterface i, MouseEvent e) {
		strokeOval(left,top,width,height) ;
		
	}

	
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
	}

	
	public String getName(EditorInterface editor) {
		return "Filled Rectangle Tool";
	}

}
