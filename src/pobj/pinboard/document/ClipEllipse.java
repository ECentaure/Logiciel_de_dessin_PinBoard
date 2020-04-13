package pobj.pinboard.document;
import java.lang.Math; 
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip  {
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public ClipEllipse(double left, double top, double right, double bottom, Color color){
		super( left,  top,  right,  bottom,  color);
	}
	
	public void draw(GraphicsContext ctx) {
		ctx.setFill(color);
		ctx.fillOval(left,top,right - left,bottom - top);
	}
	
	@Override 
	public boolean isSelected(double x, double y) {
		double cx =  ((this.getLeft()+this.getRight())/2.0) ;
		double cy =  ((this.getTop()+this.getBottom())/2.0) ;
		double rx = ((this.getLeft()-this.getRight())/2.0) ;
		double ry =  ((this.getTop()-this.getBottom())/2.0);
		double a = (x-cx)/rx;
		double b = (y-cy)/ry;
		return ( (Math.pow(a,2) + Math.pow(b,2)) <= 1 );
	}

	// Cloning
	public Clip copy() {
		return new ClipEllipse( this.getLeft(),  this.getTop(),  this.getRight(),  this.getBottom(),  this.getColor());
	}
}
