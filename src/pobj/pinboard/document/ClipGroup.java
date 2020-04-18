package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite{
	private List<Clip> liste_clip = new ArrayList<Clip>();
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	private Clip rectangle_englobant;
	
	public ClipGroup() {
		/*
		left = 0;
		top = 0;
		right = 0;
		bottom = 0 ;
		
		*/
		
		
		super(0,0,0,0, Color.ALICEBLUE);
		
	}

	
	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip e : liste_clip) {
			e.draw(ctx);
		}
		
		
	}


	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		move(right - left , bottom - top );
	}

	@Override
	public void move(double x, double y) {
		for(Clip e : liste_clip) {
			e.move(x, y);	
		}
		
	}


	@Override
	public Clip copy() {
		ClipGroup copy_group = new ClipGroup();
		for(Clip e : liste_clip) {
			copy_group.addClip(e.copy());
			
		}
		return copy_group;
	}

	@Override
	public List<Clip> getClips() {
		return liste_clip;
	}

	@Override
	public void addClip(Clip toAdd) {
		liste_clip.add(toAdd);
		maj_coord();
	}

	@Override
	public void removeClip(Clip toRemove) {
		liste_clip.remove(toRemove);
		maj_coord();
		
	}
	
	public void maj_coord() {
		for(Clip e : liste_clip) {
			
			if(e.getRight() > right) 
			{
				right = e.getRight();
			}else if(e.getLeft() > left) 
			{
				left = e.getLeft();
			}
			else if(e.getTop() > top) 
			{
				top = e.getTop();
			}
			else if(e.getBottom() > bottom) 
			{
				bottom = e.getBottom();
			}
		}
	}

}
