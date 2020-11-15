package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainPack.BoardConcrete;
import mainPack.Card;
import mainPack.CardConcrete;
import mainPack.Component;
import mainPack.ListConcrete;
import mainPack.ListN;
import mainPack.RmiClient;
import mainPack.User;

public class BoardController
{
	BoardConcrete model;
	ListConcrete list;
	RmiClient client;
	Stage s;
	Scene scene;
	ArrayList<ListN> lists;
	    @FXML
	    public HBox mainHBox;
	    

	    @FXML
	    void addCardClicked(ActionEvent event) {
//	    		 	Stage dialog = new Stage();
//	                VBox dialogVbox = new VBox(20);
//	                dialogVbox.getChildren().add(new Text("This is a Dialog"));
//	                Scene dialogScene = new Scene(dialogVbox, 300, 200);
//	                dialog.setScene(dialogScene);
//	                dialog.show();
	    	
	    	if (mainHBox.getChildren() != null) {
	    		VBox vbox = new VBox();
	    		mainHBox.getChildren().add(vbox);
	    		
	    		Set<String> labels = new HashSet<String>();
	    		Set<User> members = new HashSet<User>();
	    		Set<Component> components = new HashSet<Component>();
	    		ListN list = new ListConcrete("new list");
	    		//ArrayList<ListN> lists = new ArrayList<ListN>();
	    		Card newCard = new CardConcrete("test",labels, members, components);
	    		members.add(model.getOwner());
	    		newCard.setCardName("Click to set!");
	    		lists.add(list);
	    		list.addCards(newCard);
	    		Button b = new Button(newCard.getCardName());
	    		vbox.getChildren().add(b);
	    		
	    		b.setOnAction(new EventHandler<ActionEvent>() {
	    		    @Override public void handle(ActionEvent e) {
	    		       model.showCardView(s, scene, newCard, client, model);
	    		    }
	    		});
	    		
	    		
	    	}
	    	
	    	else {
	    		
	    	}
	            
	    }
	
	 public void setModel(Stage s, Scene scene, RmiClient client, BoardConcrete selectedBoard, BorderPane pane)
		{
	
			this.client = client;
			this.model = selectedBoard;
			this.s = s;
			this.scene = scene;
			
			
			lists = model.getLists();
			
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
					b.setOnAction(new EventHandler<ActionEvent>() {
		    		    @Override public void handle(ActionEvent e) {
		    		       model.showCardView(s, scene, c, client, model);
		    		       
		    		    }
		    		});
					//.getChildren().add(b);
					
					//System.out.println("Button created with name:" + c.getCardName());
				}
				System.out.println(l.toString());
				
				
			}
		}
		else {
			System.out.println("No lists");
		}
		}
}
