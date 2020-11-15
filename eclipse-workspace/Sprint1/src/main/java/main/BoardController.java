package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import view.LoginModel;

public class BoardController
{
	BoardConcrete model;
	ListConcrete list;
	RmiClient client;
	Stage s;
	User u;
	Scene scene;
	ArrayList<ListN> lists;
	ListN selectedList;
	ListN selectedList2;
	Card selectedCard;
	Label selectedListLabel;
	StringProperty sp = new SimpleStringProperty();
	LoginModel modelg;
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
	    	
	    	Stage dialog = new Stage();
	        VBox dialogVbox = new VBox(20);
	        ComboBox<String> selection = new ComboBox<String>();
	        Button doneButton = new Button("Done");
	        //Label label = new Label(textfield.getText());
	        dialogVbox.getChildren().add(selection);
	        dialogVbox.getChildren().add(doneButton);
	        Scene dialogScene = new Scene(dialogVbox, 300, 200);
	        ObservableList<String> data = FXCollections.observableArrayList();
	        selection.setItems(data);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        for(ListN l: model.getLists()) {
	        		data.add(l.getListName());
	  
	        }
	        doneButton.setOnAction(new EventHandler<ActionEvent>() {
	        	
    		    @Override public void handle(ActionEvent e) {
    		    	selectedList = (ListN)model.getLists().get(data.indexOf(selection.getValue()));
    		    
    		    	Set<String> labels = new HashSet<String>();
    	    		Set<User> members = new HashSet<User>();
    	    		Set<Component> components = new HashSet<Component>();
    	    		Card newCard = new CardConcrete("test",labels, members, components);
    	    		members.add(model.getOwner());
    	    		newCard.setCardName("Click to set!");
    	    		selectedList.addCards(newCard);
    	    		Button b = new Button(newCard.getCardName());
    	    		b.setOnAction(new EventHandler<ActionEvent>() {
    	    		    @Override public void handle(ActionEvent e) {
    	    		       model.showCardView(s, scene, newCard, client, model, u, modelg);
    	    		    }
    	    		});
    	    		dialog.close();
	    		    }
    		    
	    		});
	       
	    	
//	    	if (mainHBox.getChildren() != null) {
//	    		VBox vbox = new VBox();
//	    		mainHBox.getChildren().add(vbox);
//	    		
//	    		Set<String> labels = new HashSet<String>();
//	    		Set<User> members = new HashSet<User>();
//	    		Set<Component> components = new HashSet<Component>();
//	    		ListN list = new ListConcrete("new list");
//	    		//ArrayList<ListN> lists = new ArrayList<ListN>();
//	    		Card newCard = new CardConcrete("test",labels, members, components);
//	    		members.add(model.getOwner());
//	    		newCard.setCardName("Click to set!");
//	    		lists.add(list);
//	    		list.addCards(newCard);
//	    		Button b = new Button(newCard.getCardName());
//	    		vbox.getChildren().add(b);
//	    		
//	    		b.setOnAction(new EventHandler<ActionEvent>() {
//	    		    @Override public void handle(ActionEvent e) {
//	    		       model.showCardView(s, scene, newCard, client, model, u, modelg);
//	    		    }
//	    		});
//	    		
//	    		
//	    	}
//	    	
//	    	else {
//	    		
//	    	}
	            
	    }
	    @FXML
	    void renameList(ActionEvent event) {
	    	
	    	
	    	Stage dialog = new Stage();
	        VBox dialogVbox = new VBox(20);
	        ComboBox<String> selection = new ComboBox<String>();
	        Button doneButton = new Button("Done");
	        //Label label = new Label(textfield.getText());
	        dialogVbox.getChildren().add(selection);
	        dialogVbox.getChildren().add(doneButton);
	        Scene dialogScene = new Scene(dialogVbox, 300, 200);
	        ObservableList<String> data = FXCollections.observableArrayList();
	        selection.setItems(data);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        for(ListN l: model.getLists()) {
	        		data.add(l.getListName());
	  
	        }
	        doneButton.setOnAction(new EventHandler<ActionEvent>() {
	        	
    		    @Override public void handle(ActionEvent e) {
    		    	selectedList = (ListN)model.getLists().get(data.indexOf(selection.getValue()));
    		    		System.out.println(selectedList.getListName());
    		    		 Stage dialog2 = new Stage();
    		 	        VBox dialogVbox2 = new VBox(20);
    		 	        TextField textfield = new TextField();
    		 	        Button changeButton = new Button("Change");
    		 	        Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
    		 	        //Label label = new Label(textfield.getText());
    		 	        dialogVbox2.getChildren().add(textfield);
    		 	        dialogVbox2.getChildren().add(changeButton);
    		 	        dialog2.setScene(dialogScene2);
    		 	        dialog2.show();
    		 	        
    		 	        changeButton.setOnAction(new EventHandler<ActionEvent>() {
    		     		    @Override public void handle(ActionEvent e) {
    		     		    	selectedList.updateName(textfield.getText());
    		     		    	sp.set(textfield.getText());
    		     		    	
    		     		    	dialog2.close();
    		 	    		    }
    		     		  
    		 	    		});
    		 	        dialog.close();
	    		    }
    		    
	    		});
	        
	       
	        
	      
	    }
	    
	    @FXML
	    void saveBoard(ActionEvent event) {
	    	client.updateBoard(model);
	    	
	    }
	    

	    @FXML
	    void exitBoard(ActionEvent event) {
	    	System.out.println(model.getOwner().getUsername());
	    	modelg.showSelectionScreen(u, client, s, scene, modelg);
	    }
	    
	    
	    @FXML
	    void addListClicked(ActionEvent event) {
	    	ListN list = new ListConcrete("new list");
	    	model.addList(list);
	    	
	    	
	    }
	    
	    @FXML
	    void deleteListClicked(ActionEvent event) {
	    	Stage dialog = new Stage();
	        VBox dialogVbox = new VBox(20);
	        ComboBox<String> selection = new ComboBox<String>();
	        Button doneButton = new Button("Done");
	        //Label label = new Label(textfield.getText());
	        dialogVbox.getChildren().add(selection);
	        dialogVbox.getChildren().add(doneButton);
	        Scene dialogScene = new Scene(dialogVbox, 300, 200);
	        ObservableList<String> data = FXCollections.observableArrayList();
	        selection.setItems(data);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        for(ListN l: model.getLists()) {
	        		data.add(l.getListName());
	  
	        }
	        
	        doneButton.setOnAction(new EventHandler<ActionEvent>() {
	        	
    		    @Override public void handle(ActionEvent e) {
    		    	selectedList = (ListN)model.getLists().get(data.indexOf(selection.getValue()));
    		    	model.deleteList(selectedList);
    	    	
    	    		dialog.close();
	    		    }
    		    
	    		});
	    }
	    
	    @FXML
	    void deleteCardClicked(ActionEvent event) {
	    	Stage dialog = new Stage();
	        VBox dialogVbox = new VBox(20);
	        ComboBox<String> selection = new ComboBox<String>();
	        Button doneButton = new Button("Select list");
	        //Label label = new Label(textfield.getText());
	        dialogVbox.getChildren().add(selection);
	        dialogVbox.getChildren().add(doneButton);
	        Scene dialogScene = new Scene(dialogVbox, 300, 200);
	        ObservableList<String> data = FXCollections.observableArrayList();
	        selection.setItems(data);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        for(ListN l: model.getLists()) {
	        	if(l.getCards().size()>= 1) {
	        		data.add(l.getListName());
	        	}
	        	else {
	        		System.out.println("No cards in list " + l.getListName());
	        	}
  
	        }
	        
	        doneButton.setOnAction(new EventHandler<ActionEvent>() {
	        	
    		    @Override public void handle(ActionEvent e) {
    		    	selectedList = (ListN)model.getLists().get(data.indexOf(selection.getValue()));    	
    	    		dialog.close();
    	    		
    	    	  	Stage dialog2 = new Stage();
    		        VBox dialogVbox2 = new VBox(20);
    		        ComboBox<String> selection2 = new ComboBox<String>();
    		        Button doneButton2 = new Button("Select card");
    		        //Label label = new Label(textfield.getText());
    		        dialogVbox2.getChildren().add(selection2);
    		        dialogVbox2.getChildren().add(doneButton2);
    		        Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
    		        ObservableList<String> data2 = FXCollections.observableArrayList();
    		        selection2.setItems(data2);
    		        dialog2.setScene(dialogScene2);
    		        dialog2.show();
    		        for(Card c: selectedList.getCards()) {
    	        		data2.add(c.getCardName());
    	  
    		        }
    		        doneButton2.setOnAction(new EventHandler<ActionEvent>() {
    		        	
    	    		    @Override public void handle(ActionEvent e) {
    	    		    	
    	    		    
    	    		    	selectedCard = selectedList.getCards().get(data2.indexOf(selection2.getValue()));
    	    		    	
    	    		
    	    		    	
    	    		    	selectedList.removeCard(selectedCard);
  
    	    		    	dialog2.close();
    		    		    }
    	    		   
    		    		});
    		        
	    		    }
    		    
    		    
	    		});
	      

	    }
	    @FXML
	    void renameCardClicked(ActionEvent event) {
	    	Stage dialog = new Stage();
	        VBox dialogVbox = new VBox(20);
	        ComboBox<String> selection = new ComboBox<String>();
	        Button doneButton = new Button("Select list");
	        //Label label = new Label(textfield.getText());
	        dialogVbox.getChildren().add(selection);
	        dialogVbox.getChildren().add(doneButton);
	        Scene dialogScene = new Scene(dialogVbox, 300, 200);
	        ObservableList<String> data = FXCollections.observableArrayList();
	        selection.setItems(data);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        for(ListN l: model.getLists()) {
	        	if(l.getCards().size()>= 1) {
	        		data.add(l.getListName());
	        	}
	        	else {
	        		System.out.println("No cards in list " + l.getListName());
	        	}
  
	        }
	        
	        doneButton.setOnAction(new EventHandler<ActionEvent>() {
	        	
    		    @Override public void handle(ActionEvent e) {
    		    	selectedList = (ListN)model.getLists().get(data.indexOf(selection.getValue()));    	
    	    		dialog.close();
    	    		
    	    	  	Stage dialog2 = new Stage();
    		        VBox dialogVbox2 = new VBox(20);
    		        ComboBox<String> selection2 = new ComboBox<String>();
    		        Button doneButton2 = new Button("Select card");
    		        //Label label = new Label(textfield.getText());
    		        dialogVbox2.getChildren().add(selection2);
    		        dialogVbox2.getChildren().add(doneButton2);
    		        Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
    		        ObservableList<String> data2 = FXCollections.observableArrayList();
    		        selection2.setItems(data2);
    		        dialog2.setScene(dialogScene2);
    		        dialog2.show();
    		        for(Card c: selectedList.getCards()) {
    	        		data2.add(c.getCardName());
    	  
    		        }
    		        doneButton2.setOnAction(new EventHandler<ActionEvent>() {
    		        	
    	    		    @Override public void handle(ActionEvent e) {
    	    		    	
    	    		    
    	    		    	selectedCard = selectedList.getCards().get(data2.indexOf(selection2.getValue()));
    	    		    	
    	    		
    	    	    		//dialog.close();
    	    	    		
    	    	    	  	Stage dialog3 = new Stage();
    	    		        VBox dialogVbox3 = new VBox(20);
    	    		        TextField textfield = new TextField();
    	    		        Button doneButton3 = new Button("Change name");
    	    		        //Label label = new Label(textfield.getText());
    	    		        dialogVbox3.getChildren().add(textfield);
    	    		        dialogVbox3.getChildren().add(doneButton3);
    	    		        Scene dialogScene3 = new Scene(dialogVbox3, 300, 200);
    	    		     
    	    		        dialog2.setScene(dialogScene3);
    	    		        dialog2.show();
    	    		        
    	    		        doneButton3.setOnAction(new EventHandler<ActionEvent>() {
    	    		        	
    	    	    		    @Override public void handle(ActionEvent e) {
    	    	    		    	
    	    	    		    	selectedCard.setCardName(textfield.getText());
    	    	    		    	dialog2.close();
    	    	    		
    	    	    	    	
    	    		    		    }
    	    	    		   
    	    		    		});
    		    		    }
    	    		   
    		    		});
    		        
	    		    }
    		    
    		    
	    		});
	    }
	    
	    @FXML
	    void moveListClicked(ActionEvent event) {
	     	Stage dialog = new Stage();
	        VBox dialogVbox = new VBox(20);
	        ComboBox<String> selection = new ComboBox<String>();
	        Button doneButton = new Button("Select list to move");
	        //Label label = new Label(textfield.getText());
	        dialogVbox.getChildren().add(selection);
	        dialogVbox.getChildren().add(doneButton);
	        Scene dialogScene = new Scene(dialogVbox, 300, 200);
	        ObservableList<String> data = FXCollections.observableArrayList();
	        selection.setItems(data);
	        dialog.setScene(dialogScene);
	        dialog.show();
	        
	        for(ListN l: model.getLists()) {

	        		data.add(l.getListName());
	        
	        }
	        doneButton.setOnAction(new EventHandler<ActionEvent>() {
	        	
    		    @Override public void handle(ActionEvent e) {
    		    	dialog.close();
    		    	selectedList = (ListN)model.getLists().get(data.indexOf(selection.getValue()));    	

    		    	Stage dialog2 = new Stage();
    		        VBox dialogVbox2 = new VBox(20);
    		        ComboBox<String> selection2 = new ComboBox<String>();
    		        Button doneButton2 = new Button("Move after list");
    		        //Label label = new Label(textfield.getText());
    		        dialogVbox2.getChildren().add(selection2);
    		        dialogVbox2.getChildren().add(doneButton2);
    		        Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
    		        ObservableList<String> data2 = FXCollections.observableArrayList();
    		        selection2.setItems(data2);
    		        dialog2.setScene(dialogScene2);
    		        dialog2.show();
    		        
    		        for(ListN l: model.getLists()) {
    		        	if(!l.equals(selectedList)) {
    		        		data2.add(l.getListName());
    		        	}
    		        	else {
    		        		
    		        	}
    		        
    		        }
    		        doneButton2.setOnAction(new EventHandler<ActionEvent>() {

    	    		    @Override public void handle(ActionEvent e) {
    	    		    	selectedList2 = (ListN)model.getLists().get(data.indexOf(selection2.getValue())); 
    	    		    	dialog2.close();
    	    		    	
    	    		    	
    	    		    	model.reorderList(selectedList,data.indexOf(selection2.getValue()));
    	    		    	//model.reorderList(selectedList2, data.indexOf(selection.getValue()));
    	    	    	
    		    		    }
    	    		   
    		    		});
    	    	
	    		    }
    		    
    		   
	    		});
	    }
	 public void setModel(Stage s, Scene scene, RmiClient client, BoardConcrete selectedBoard, BorderPane pane, LoginModel modelg, User u)
		{
	
			this.client = client;
			this.model = selectedBoard;
			this.s = s;
			this.scene = scene;
			this.modelg = modelg;
			this.u = u;
			
			
			lists = model.getLists();
			
		if (lists != null) {
			for (ListN l: lists) {
				VBox vbox = new VBox();
				mainHBox.getChildren().add(vbox);
				Label label = new Label(l.getListName());
				//StringProperty sp = new SimpleStringProperty();
				//sp.set(l.getListName());
				//label.textProperty().bindBidirectional(sp);
				vbox.getChildren().add(label);
				System.out.println("reached + " + l.getCards());
				for(Card c: l.getCards()) {
					Button b = new Button(c.getCardName());
					vbox.getChildren().add(b);			
					b.setOnAction(new EventHandler<ActionEvent>() {
		    		    @Override public void handle(ActionEvent e) {
		    		       model.showCardView(s, scene, c, client, model, u, modelg);
		    		       
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
