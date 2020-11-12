package main;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import mainPack.Board;
import mainPack.BoardConcrete;
import mainPack.RmiClient;
import mainPack.User;

public class SelectionController
{
	RmiClient client;
	Stage s;
	Scene scene;
	
	User model;
	
	ArrayList<Board> boards;
	
	BoardController boardController;
	//BoardConcrete bmodel;
	//User userData;
	

		@FXML
	    private ComboBox<String> dropDownField= new ComboBox<String>();
	 	
	 	ObservableList<String> data = FXCollections.observableArrayList();

	    @FXML
	    void createNewBoard(ActionEvent event) {
	    	
	    }

	    @FXML
	    void loadBoardButton(ActionEvent event) {
	    	BoardConcrete selectedBoard = (BoardConcrete) boards.get(data.indexOf(dropDownField.getValue()));
	    	
	    	selectedBoard.showBoardScreen(s, scene, selectedBoard, client);
	    	//System.out.println(selectedBoard);
	    }
	    
	    public void setModel(User user, RmiClient client, Stage s, Scene scene)
		{
			this.model = user;
			this.client = client;
			this.s = s;
			this.scene = scene;
			boards = model.getBoards();
			
			
			//Board board = boards.get(0);
			for (Board b:boards) {
				data.add(b.getBoardName().toString());
				
			}
			dropDownField.setItems(data);
			//System.out.println(board.getBoardName());
			
			//FXCollections.observableArrayList(data);
			
			//StringConverter<Number> fmt = new NumberStringConverter();
			
			//Bindings.bindBidirectional(totalLabel.textProperty(), model.getCount(), fmt);		
		}
	    
	   
}
