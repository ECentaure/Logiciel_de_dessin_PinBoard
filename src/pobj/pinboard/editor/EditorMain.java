package pobj.pinboard.editor;

import javafx.application.Application;
import javafx.stage.Stage;
import pobj.pinboard.document.EditorWindow;

public class EditorMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new EditorWindow(stage);		
    
    }
	
    public static void main(String[] args) { 
    	launch(args); 
    	
    } 
}
