package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {
	private EditorInterface editor;  
	private  List<Clip> lc;
	private double x;
	private double y;
	
	public CommandMove(EditorInterface editor, Clip toMove, double x , double y) {
		this.editor = editor;
		lc = new ArrayList<Clip>();
		lc.add(toMove);
		this.x = x;
		this.y = y;
	}
	
	public CommandMove(EditorInterface editor, List<Clip> toMove, double x , double y) {
		this.editor = editor;
		lc = new ArrayList<Clip>();
		for(Clip e : toMove) {lc.add(e);}
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public void execute() {
		
		for ( Clip e : lc) {e.move(x, y);}
	}

	@Override
	public void undo() {
		for ( Clip e : lc) {e.move(-x, -y);}
	}

}
