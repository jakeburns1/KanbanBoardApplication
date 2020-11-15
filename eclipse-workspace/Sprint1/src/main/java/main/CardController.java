package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainPack.BoardConcrete;
import mainPack.Card;
import mainPack.Component;
import mainPack.DescriptionComponent;
import mainPack.ListConcrete;
import mainPack.ListN;
import mainPack.RmiClient;
import mainPack.RmiServer;

public class CardController
{

	
	BoardConcrete model;
	ListConcrete list;
	RmiClient client;
	RmiServer server;
	Stage s;
	Scene scene;
	Card card;
	
    @FXML
    private Label descriptionLabel;
    
    @FXML
    private ListView<String> listView;

    ObservableList<String> data =  FXCollections.observableArrayList();
    
    @FXML
    private VBox cardLabelBox;
    
    @FXML
    private Label nameLabel;

    @FXML
    void doneClicked(ActionEvent event) {
    	model.showBoardScreen(s, scene, model, client);
    }

    @FXML
    void editCheckListClicked(ActionEvent event) {

    }

    @FXML
    void editDescriptionClicked(ActionEvent event) {
    	descriptionLabel.setText("changed");
    	Stage dialog = new Stage();
        VBox dialogVbox = new VBox(20);
        TextField textfield = new TextField();
        Button doneButton = new Button("Done");
        dialogVbox.getChildren().add(textfield);
        dialogVbox.getChildren().add(doneButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
 		       //System.out.println(textfield.getText());
		    	for (ListN l: model.getLists()) {
					for(Card c: l.getCards()) {
						if(c.getCardName().equals(card.getCardName())) {
							card = c;
						}
						//vbox.getChildren().add(b);			
					}
		    	}
 		      // descriptionLabel.setText(textfield.getText());
 		       Component description = new DescriptionComponent(textfield.getText());
 		       card.addComponent(description);
 		       dialog.close();
 		       card.storeToDisk();
 		       client.updateBoard(model);
 		    }
 		});
     
    }

    @FXML
    void editLabelsClicked(ActionEvent event) {
    	//descriptionLabel.setText("changed");
    	Stage dialog = new Stage();
        VBox dialogVbox = new VBox(20);
        TextField textfield = new TextField();
        Button addButton = new Button("Add Label");
        Label label = new Label(textfield.getText());
        dialogVbox.getChildren().add(textfield);
        dialogVbox.getChildren().add(addButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	data.add(textfield.getText());
		    	listView.setItems(data);
		    	card.createLabel(textfield.getText());
		    	card.storeToDisk();
		    }
	 		});
        
    }

    @FXML
    void editNameClicked(ActionEvent event) {
    	Stage dialog = new Stage();
        VBox dialogVbox = new VBox(20);
        TextField textfield = new TextField();
        Button doneButton = new Button("Done");
        Label label = new Label(textfield.getText());
        dialogVbox.getChildren().add(textfield);
        dialogVbox.getChildren().add(doneButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	nameLabel.setText(textfield.getText());
		    	card.setCardName(textfield.getText());
		    	dialog.close();
		    }
	 		});
    }
    
    public void setModel(Stage s, Scene scene, Card card, RmiClient c, BoardConcrete model) {
    	this.s = s;
    	this.scene = scene;
    	this.card = card;
    	this.client = c;
    	this.model = model;
    	
    	//DescriptionComponent description = card.getComponents().toArray();
    	if(card.getDesComponent()!=null) {
    		
    		descriptionLabel.setText(this.card.getDesComponent().getText());
    	}
    	else {
    		descriptionLabel.setText("Description");
    	}
    	
    	nameLabel.setText(card.getCardName());
    	ObservableList<String> l = FXCollections.observableArrayList();
    	l.addAll(card.getLabels());
    	listView.setItems(l);
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}