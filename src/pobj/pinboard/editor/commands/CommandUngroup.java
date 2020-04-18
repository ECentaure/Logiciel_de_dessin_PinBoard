package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command {
	private EditorInterface editor;

	private ClipGroup cg;
	
	public CommandUngroup(EditorInterface editor, ClipGroup cg) {
		this.editor = editor; 
		this.cg = cg;

	}
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	
		editor.getBoard().addClip(cg.getClips());
		
		editor.getBoard().removeClip(cg);
	}

	@Override
	public void undo() {
		
		
		editor.getBoard().removeClip(cg.getClips());
		
		editor.getBoard().addClip(cg);
		
	}

}
