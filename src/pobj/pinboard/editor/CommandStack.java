package pobj.pinboard.editor;

import java.util.Stack;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	
	private Stack<Command> undo;
	private Stack<Command> redo;
	
	public CommandStack() {
		undo = new Stack<Command>(); 
		redo = new Stack<Command>(); 
	}
	
	public void addCommand(Command cmd) {
		undo.push(cmd);
		redo.clear();
	}
	
	public void undo() {
		Command cmd = undo.pop();
		cmd.undo();
		redo.push(cmd);
	}
	
	public void redo() {
		Command cmd = redo.pop();
		cmd.execute();
		undo.push(cmd);
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}
}
