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
	
	public ClipGroup() {
		
		super(0.0,0.0,0.0,0.0, Color.ALICEBLUE);
		
		left = 0.0;
		top = 0.0;
		right =0.0;
		bottom = 0.0 ;
		
	}

	
	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip e : liste_clip) {
			e.draw(ctx);
		}
		
		
	}


	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		
		move(0, 0 );
		
	}

	@Override
	public void move(double x, double y) {
		
		for(Clip elem : liste_clip) {
			elem.move(x, y);	
		}
		maj_coord();
		
		
	}

	public void maj_coord() {
		if(!liste_clip.isEmpty()) {
			
			double maxR = liste_clip.get(0).getRight();
			double maxL = liste_clip.get(0).getLeft();
			double maxT = liste_clip.get(0).getTop();
			double maxB = liste_clip.get(0).getBottom();
			
			for(Clip elem : liste_clip) {
				
				if(elem.getRight() > maxR) 
				{
					maxR = elem.getRight();
					
				} if(elem.getLeft() < maxL) 
				{
					maxL = elem.getLeft();
							
				}
				 if(elem.getTop() < maxT) 
				{
					maxT = elem.getTop();
				}
				 if(elem.getBottom() > maxB) 
				{
					maxB = elem.getBottom();
				}
						
			}	
		
			super.setGeometry(maxL, maxT, maxR, maxB);
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
		this.liste_clip.add(toAdd);
		//System.out.println(liste_clip.size());
		maj_coord();
		
	}

	@Override
	public void removeClip(Clip toRemove) {
		
		liste_clip.remove(toRemove);
		this.maj_coord();
		
	}
	


}
