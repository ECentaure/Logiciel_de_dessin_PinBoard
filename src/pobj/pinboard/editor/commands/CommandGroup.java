package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command{
	private EditorInterface editor;
	private  List<Clip> lc;
	private ClipGroup cg;
	public CommandGroup(EditorInterface editor, Clip c) {
		
		this.editor = editor; 
		lc = new ArrayList<Clip>();
		lc.add(c);
				
	}
	
	public CommandGroup(EditorInterface editor, List<Clip> toAdd) {
		this.editor = editor;
		lc = new ArrayList<Clip>();
		for(Clip e : toAdd) {lc.add(e);}
	}
	
	@Override
	public void execute() {
		
		editor.getBoard().removeClip(lc);
		 cg = new ClipGroup(); 
		for( Clip c : lc ) {
			cg.addClip(c);
		}
		editor.getBoard().addClip(cg);
	}

	@Override
	public void undo() {
		
		editor.getBoard().addClip(cg.getClips());
		
		editor.getBoard().removeClip(cg);
	}

}
