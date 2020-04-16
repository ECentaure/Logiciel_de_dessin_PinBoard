package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	
	private List<Clip> contents = new ArrayList<Clip>();
	private List<ClipboardListener> liste_cibles = new ArrayList<ClipboardListener>();
	
	private Clipboard() {
		
	}
	
	private static Clipboard cb = new Clipboard();
	
	public static Clipboard getInstance() {
		return cb;
	}
	
	public void copyToClipboard(List<Clip> clips){
		//System.out.print("copytoclip");
		
		for(Clip element: clips ) {
			contents.add(element.copy());
		}
		
		for(ClipboardListener cible : liste_cibles) {
			cible.clipboardChanged();
			
		}
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> contents_copy = new ArrayList<Clip>();
		
		for(Clip element: contents ) {
			contents_copy.add(element.copy());
		}
		
		for(ClipboardListener cible : liste_cibles) {
			cible.clipboardChanged();
			//System.out.print("copyfromclip");
		}
		return contents_copy;
	}
	
	public void clear() {
		
		contents.clear();
		
		for(ClipboardListener cible : liste_cibles) {
			cible.clipboardChanged();
		}
	}
	
	public boolean isEmpty() {
		return (contents.isEmpty());
	}
	
	public void addListener(ClipboardListener listener) {
		liste_cibles.add(listener);
	}
	public void removeListener(ClipboardListener listener) {
		liste_cibles.remove(listener);
	}
}
