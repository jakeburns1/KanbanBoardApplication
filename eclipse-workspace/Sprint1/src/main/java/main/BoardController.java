package main;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import mainPack.BoardConcrete;
import mainPack.RmiClient;

public class BoardController
{
	BoardConcrete model;
	RmiClient client;
	   @FXML
	    private VBox field2;
	
	 public void setModel(RmiClient client, BoardConcrete selectedBoard)
		{
	
			this.client = client;
			this.model = selectedBoard;
		
		}
}
