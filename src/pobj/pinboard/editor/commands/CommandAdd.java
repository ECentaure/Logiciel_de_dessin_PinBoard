package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command {
	private EditorInterface editor;  
	private  List<Clip> lc;
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.editor = editor;
		lc = new ArrayList<Clip>();
		lc.add(toAdd);
	}
	
	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		this.editor = editor;
		lc = new ArrayList<Clip>();
		for(Clip e : toAdd) {lc.add(e);}
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		editor.getBoard().addClip(lc);
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(lc);
		
	}

}
