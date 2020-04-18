package pobj.pinboard.document;

import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.editor.Clipboard;
import pobj.pinboard.editor.ClipboardListener;
import pobj.pinboard.editor.CommandStack;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;


public class EditorWindow implements EditorInterface ,ClipboardListener{
	
	private Board board;
	private MenuBar menubar;
	private VBox vbox;
	private Canvas canvas;
	private ToolBar toolbar;
	private Separator separator;
	private Selection selection;
	private Stage stage;
	
	private MenuItem copyMI;
	private MenuItem pasteMI;
	private MenuItem deleteMI;
	
	private Tool outil_courant;

	
	public EditorWindow(Stage stage) {
		this.stage = stage;
		Clipboard.getInstance().addListener(this);
		
		ToolEllipse tool_ellipse = new ToolEllipse();
		ToolRect tool_rectangle = new ToolRect();
		ToolSelection tool_selection = new ToolSelection();
		
		Label label = new Label("Aucun outil selectionné");
		separator = new Separator();
		
		stage.setTitle("Titre");
		
		selection = new Selection();
		
		vbox = new VBox();
		
		canvas = new javafx.scene.canvas.Canvas(800, 600);

		canvas.setOnMousePressed((e) -> {press(e);});
		
		canvas.setOnMouseDragged((e) -> drag(e));
		canvas.setOnMouseReleased((e) -> release(e));
		
		MenuItem newMI = new MenuItem("New");
		MenuItem closeMI = new MenuItem("Close");
		
		MenuItem copyMI = new MenuItem("Copy");
		 pasteMI = new MenuItem("Paste");
		MenuItem deleteMI = new MenuItem("Delete");
		
		
		newMI.setOnAction( (e)-> new EditorWindow(new Stage()));
		closeMI.setOnAction( (e)-> 	{Clipboard.getInstance().removeListener(this); stage.close() ;});
		
		copyMI.setOnAction((e)-> {Clipboard.getInstance().copyToClipboard(selection.getContents());});
		pasteMI.setOnAction((e)->{ System.out.print("clipboard vide"); board.addClip(Clipboard.getInstance().copyFromClipboard() ) ; draw() ; } );
		deleteMI.setOnAction((e)->{ Clipboard.getInstance().clear();});
		
		Menu file = new Menu("File");
		Menu edit  = new Menu("Edit");
		Menu tools = new Menu("Tools");
		
		file.getItems().addAll(newMI,closeMI);
		edit.getItems().addAll(copyMI,pasteMI,deleteMI);
	
		menubar= new MenuBar(file,edit,tools);

		vbox.getChildren().add(menubar);
		
		
		Button box = new Button("Box");
		Button ellipse = new Button("Ellipse");
		Button image = new Button("Image");
		Button select = new Button("Selection");
		
		
		toolbar = new ToolBar();
		
		box.setOnAction((e) -> {outil_courant = tool_rectangle; label.textProperty().set(outil_courant.getName(this));});
		ellipse.setOnAction((e) -> {outil_courant = tool_ellipse; label.textProperty().set(outil_courant.getName(this));});
		select.setOnAction((e) -> {outil_courant = tool_selection; label.textProperty().set(outil_courant.getName(this));});
		
		toolbar.getItems().addAll(box,ellipse,image,select);
	
		
		board = new Board();

		
		
		vbox.getChildren().add(toolbar);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(separator);
		vbox.getChildren().add(label);
		
		pasteMI.setDisable(true);

		
		stage.setScene(new javafx.scene.Scene(vbox));
		stage.show();
		
	}

	public void press( MouseEvent e) {
		outil_courant.press(this, e );
		//this.draw();
	}
	public void drag (MouseEvent e) {
		
		outil_courant.drag(this, e);
		this.draw();
	}
	public void release( MouseEvent e) {
		outil_courant.release(this, e);
		this.draw();
		board.draw(canvas.getGraphicsContext2D());
	}

	public void draw() {
		outil_courant.drawFeedback(this, canvas.getGraphicsContext2D());
		
		
	}
	
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return selection ;
	
	}

	@Override
	public CommandStack getUndoStack() {
		
		return null;
	}


	public void clipboardChanged() {
		

		if(Clipboard.getInstance().isEmpty()) {	
			pasteMI.setDisable(true); //Paste est grisé
			//System.out.print("clipboard vide");
		}else {
			//System.out.print("clipboard non vide");
			pasteMI.setDisable(false);
		}
		
	}
}
