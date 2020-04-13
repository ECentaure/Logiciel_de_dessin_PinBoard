package pobj.pinboard.document;

import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.editor.CommandStack;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;
import pobj.pinboard.editor.tools.Tool;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;


public class EditorWindow implements EditorInterface{
	
	private Board board;
	private MenuBar menubar;
	private VBox vbox;
	private Canvas canvas;
	private ToolBar toolbar;
	private Separator separator;
	
	private Tool outil_courant;

	
	public EditorWindow(Stage stage) {
		
		toolbar = new ToolBar();
		
		stage.setTitle("Titre");
		vbox = new VBox();
		
		canvas = new javafx.scene.canvas.Canvas(800, 600);

		canvas.setOnMousePressed((e) -> press(e));
		canvas.setOnMouseDragged((e) -> drag(e));
		canvas.setOnMouseReleased((e) -> release(e));
		
		vbox.getChildren().add(menubar);
		vbox.getChildren().add(toolbar);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(separator);	
		
		MenuItem newMI = new MenuItem("New");
		MenuItem closeMI = new MenuItem("Close");
		
		newMI.setOnAction( (e)-> new EditorWindow(new Stage()));
		
		closeMI.setOnAction( (e)-> 	stage.close());
		
		Menu file = new Menu("File");
		Menu edit  = new Menu("Edit");
		Menu tools = new Menu("Tools");

		file.getItems().add(newMI);
		file.getItems().add(closeMI);
		
		menubar.getMenus().add(file);
		menubar.getMenus().add(edit);
		menubar.getMenus().add(tools);
		
		Button box = new Button("Box");
		Button ellipse = new Button("Ellipse");
		Button image = new Button("Image");
		
		toolbar.getItems().add(box);
		toolbar.getItems().add(ellipse);
		toolbar.getItems().add(image);
		
		board = new Board();

		board.draw(canvas.getGraphicsContext2D());
		
		
		stage.setScene(new javafx.scene.Scene(vbox));
		stage.show();
	}

	public void press( MouseEvent e) {
		outil_courant.press(this, e );
	}
	public void drag (MouseEvent e) {
		outil_courant.drag(this, e);
	}
	public void release( MouseEvent e) {
		outil_courant.release(this, e);
	}

	
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return null ;
	}

	@Override
	public CommandStack getUndoStack() {
		
		return null;
	}
}
