package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board implements Clip {
	private List<Clip> contents = new ArrayList<Clip>();
	
	public Board() {
		
	}
	
	public List<Clip> getContents(){
		return contents;
	}
	public void addClip(Clip clip)
	{
		contents.add(clip);
	}
	public void addClip(List<Clip> clip)
	{
		for(Clip element: clip ) {
			contents.add(element);
		}
	}
	public void removeClip(Clip clip)
	{
		contents.remove(clip);
	}
	public void removeClip(List<Clip> clip)
	{
		for(Clip element: clip ) {
			contents.remove(element);
		}
	}
	public void draw(GraphicsContext gc)
	{
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());
		
		for(Clip element: contents) {
			
			element.draw(gc);
		}
	}

	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clip copy() {
		Board b = new Board();
		b.addClip(contents);
		return b;
	}
	
}
