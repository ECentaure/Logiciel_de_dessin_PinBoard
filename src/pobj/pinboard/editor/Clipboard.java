package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard implements ClipboardListener {
	
	private List<Clip> contents ;
	private List<ClipboardListener> liste_cibles;
	
	private Clipboard() {
		contents = new ArrayList<Clip>();
		liste_cibles = new ArrayList<ClipboardListener>();
	}
	
	private static Clipboard cb = new Clipboard();
	
	public static Clipboard getInstance() {
		return cb;
	}
	
	public void addListener(ClipboardListener listener) {
		liste_cibles.add(listener);
	}
	public void removeListener(ClipboardListener listener) {
		liste_cibles.remove(listener);
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

	@Override
	public void clipboardChanged() {
		// TODO Auto-generated method stub
		
	}
	

}
