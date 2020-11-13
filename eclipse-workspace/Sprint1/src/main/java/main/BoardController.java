package main;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import mainPack.BoardConcrete;
import mainPack.ListN;
import mainPack.RmiClient;

public class BoardController
{
	BoardConcrete model;
	RmiClient client;
	   @FXML
	    private VBox field2;
	
	 public void setModel(RmiClient client, BoardConcrete selectedBoard, BorderPane pane)
		{
	
			this.client = client;
			this.model = selectedBoard;
			
			
			
			ArrayList<ListN> lists = model.getLists();
		if (lists != null) {
			for (ListN l: lists) {
				System.out.println(l.toString());
			}
		}
		else {
			System.out.println("No lists");
		}
		}
}
