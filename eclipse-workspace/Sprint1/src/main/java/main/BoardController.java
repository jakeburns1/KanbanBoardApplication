package main;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mainPack.BoardConcrete;
import mainPack.Card;
import mainPack.ListN;
import mainPack.RmiClient;

public class BoardController
{
	BoardConcrete model;
	RmiClient client;
	    @FXML
	    private HBox mainHBox;
	    

	    @FXML
	    void addCardClicked(ActionEvent event) {
	    		 	Stage dialog = new Stage();
	                VBox dialogVbox = new VBox(20);
	                dialogVbox.getChildren().add(new Text("This is a Dialog"));
	                Scene dialogScene = new Scene(dialogVbox, 300, 200);
	                dialog.setScene(dialogScene);
	                dialog.show();
	            
	    }
	
	 public void setModel(RmiClient client, BoardConcrete selectedBoard, BorderPane pane)
		{
	
			this.client = client;
			this.model = selectedBoard;
			
			
			
			ArrayList<ListN> lists = model.getLists();
			
		if (lists != null) {
			for (ListN l: lists) {
				VBox vbox = new VBox();
				mainHBox.getChildren().add(vbox);
				Label label = new Label(l.getListName());
				vbox.getChildren().add(label);
				System.out.println("reached + " + l.getCards());
				for(Card c: l.getCards()) {
					Button b = new Button(c.getCardName());
					vbox.getChildren().add(b);
					
					//.getChildren().add(b);
					
					System.out.println("Button created with name:" + c.getCardName());
				}
				System.out.println(l.toString());
				
				
			}
		}
		else {
			System.out.println("No lists");
		}
		}
}
