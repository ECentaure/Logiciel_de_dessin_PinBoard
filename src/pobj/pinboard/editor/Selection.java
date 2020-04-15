package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private List<Clip> contents = new ArrayList<Clip>();
	
	public Selection() {
		
	}
	
	public void select( Board board , double x , double y) {
		contents.clear();
		
		for( Clip element : board.getContents()) {
			if(element.isSelected(x, y)) {
				contents.add(element);
				return ;
			}
		}
	}
	
	public void toogleSelect(Board board, double x, double y) {
		
		for( Clip element : board.getContents()) {
			if(element.isSelected(x, y) && !contents.contains(element)) {
				contents.add(element);
			} else if (element.isSelected(x, y) && contents.contains(element)) {
				contents.remove(element);
			}	
	
		}
	}
	
	public void clear() {
		contents.clear();
	}
	
	 public List<Clip> getContents(){
		 return contents;
	 }
	 public void drawFeedback(GraphicsContext gc) {
		 double min_x0 = 0;
		 double min_y0 = 0;
		 double max_xn = 0;
		 double max_yn = 0;
		
		 
		 gc.strokeRect(min_x0, min_y0, max_xn - min_x0, max_yn - min_x0);
		 
	 }
}
