package pobj.pinboard.document;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect implements Clip{
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public ClipRect(double left, double top, double right, double bottom, Color color){
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}
	
	public void draw(GraphicsContext ctx) {
		ctx.setFill(color);
		ctx.fillRect(left,top,right - left,bottom - top);
	}
	
	// Geometry	
	public double getTop() {
		return top;
	}
	public double getLeft() {
		return left;
	}
	public double getBottom() {
		return bottom;
	}
	public double getRight() {
		return right;
	}
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	public void move(double x, double y) {
		 setGeometry( left + x,  top + y,  right + x,  bottom + y);
	}
	public boolean isSelected(double x, double y) {
		 return (x >= left && x <= right && y <= bottom && y >= top );
	}

	// Colors
	public void setColor(Color c) {
		this.color = c;
	}
	public Color getColor() {
		return color;
	}

	// Cloning
	public Clip copy() {
		return new ClipRect( this.getLeft(),  this.getTop(),  this.getRight(),  this.getBottom(),  this.getColor());
	}
}
