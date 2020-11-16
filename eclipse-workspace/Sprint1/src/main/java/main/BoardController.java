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
import mainPack.Board;
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
	Card selectedCard2;
	Label selectedListLabel;
	StringProperty sp = new SimpleStringProperty();
	LoginModel modelg;
	@FXML
	public HBox mainHBox;

	@FXML
	void addCardClicked(ActionEvent event)
	{
//	    		 	Stage dialog = new Stage();
//	                VBox dialogVbox = new VBox(20);
//	                dialogVbox.getChildren().add(new Text("This is a Dialog"));
//	                Scene dialogScene = new Scene(dialogVbox, 300, 200);
//	                dialog.setScene(dialogScene);
//	                dialog.show();

		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("addCardCombo");
		Button doneButton = new Button("Done");
		doneButton.setId("addCardDone");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{
			data.add(l.getListName());

		}
		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));

				Set<String> labels = new HashSet<String>();
				Set<User> members = new HashSet<User>();
				Set<Component> components = new HashSet<Component>();
				Card newCard = new CardConcrete("test", labels, members, components);
				members.add(model.getOwner());
				newCard.setCardName("Click to set!");
				selectedList.addCards(newCard);
				client.updateBoard(model);
				model.showBoardScreen(s, scene, model, client, modelg, u);

				Button b = new Button(newCard.getCardName());
				b.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{
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
	void renameList(ActionEvent event)
	{

		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("renameCombo");
		Button doneButton = new Button("Done");
		doneButton.setId("renameDone");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{
			data.add(l.getListName());

		}
		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));
				System.out.println(selectedList.getListName());
				Stage dialog2 = new Stage();
				VBox dialogVbox2 = new VBox(20);
				TextField textfield = new TextField();
				textfield.setId("renameText");
				Button changeButton = new Button("Change");
				changeButton.setId("changeRename");
				Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
				// Label label = new Label(textfield.getText());
				dialogVbox2.getChildren().add(textfield);
				dialogVbox2.getChildren().add(changeButton);
				dialog2.setScene(dialogScene2);
				dialog2.show();

				changeButton.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{
						selectedList.updateName(textfield.getText());
						client.updateBoard(model);
						model.showBoardScreen(s, scene, model, client, modelg, u);
						// sp.set(textfield.getText());

						dialog2.close();
					}

				});
				dialog.close();
			}

		});

	}

	@FXML
	void saveBoard(ActionEvent event)
	{
		
		client.createBoard("New Board", u);
		
	}

	@FXML
	void exitBoard(ActionEvent event)
	{
		//System.out.println(model.getOwner().getUsername());
		modelg.showSelectionScreen(u, client, s, scene, modelg);
	}

	@FXML
	void addListClicked(ActionEvent event)
	{
		ListN list = new ListConcrete("new list");
		model.addList(list);
		client.updateBoard(model);
		model.showBoardScreen(s, scene, model, client, modelg, u);

	}

	@FXML
	void deleteListClicked(ActionEvent event)
	{
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("listDeleteCombo");
		Button doneButton = new Button("Done");
		doneButton.setId("listDeleteDone");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{
			data.add(l.getListName());

		}

		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));
				model.deleteList(selectedList);
				client.updateBoard(model);
				model.showBoardScreen(s, scene, model, client, modelg, u);

				dialog.close();
			}

		});
	}

	@FXML
	void deleteCardClicked(ActionEvent event)
	{
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("deleteCardCombo");
		Button doneButton = new Button("Select list");
		doneButton.setId("deleteCardDone");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{
			if (l.getCards().size() >= 1)
			{
				data.add(l.getListName());
			} else
			{
				System.out.println("No cards in list " + l.getListName());
			}

		}

		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));
				dialog.close();

				Stage dialog2 = new Stage();
				VBox dialogVbox2 = new VBox(20);
				ComboBox<String> selection2 = new ComboBox<String>();
				selection2.setId("deleteAnotherCombo");
				Button doneButton2 = new Button("Select card");
				doneButton2.setId("selectThat");
				// Label label = new Label(textfield.getText());
				dialogVbox2.getChildren().add(selection2);
				dialogVbox2.getChildren().add(doneButton2);
				Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
				ObservableList<String> data2 = FXCollections.observableArrayList();
				selection2.setItems(data2);
				dialog2.setScene(dialogScene2);
				dialog2.show();
				for (Card c : selectedList.getCards())
				{
					data2.add(c.getCardName());

				}
				doneButton2.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent e)
					{

						selectedCard = selectedList.getCards().get(data2.indexOf(selection2.getValue()));

						selectedList.removeCard(selectedCard);
						client.updateBoard(model);
						model.showBoardScreen(s, scene, model, client, modelg, u);

						dialog2.close();
					}

				});

			}

		});

	}

	@FXML
	void renameCardClicked(ActionEvent event)
	{
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("renameCardCombo");
		Button doneButton = new Button("Select list");
		doneButton.setId("renameDone");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{
			if (l.getCards().size() >= 1)
			{
				data.add(l.getListName());
			} else
			{
				System.out.println("No cards in list " + l.getListName());
			}

		}

		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));
				dialog.close();

				Stage dialog2 = new Stage();
				VBox dialogVbox2 = new VBox(20);
				ComboBox<String> selection2 = new ComboBox<String>();
				selection2.setId("nextSelectRename");
				Button doneButton2 = new Button("Select card");
				doneButton2.setId("doneRenaming");
				// Label label = new Label(textfield.getText());
				dialogVbox2.getChildren().add(selection2);
				dialogVbox2.getChildren().add(doneButton2);
				Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
				ObservableList<String> data2 = FXCollections.observableArrayList();
				selection2.setItems(data2);
				dialog2.setScene(dialogScene2);
				dialog2.show();
				for (Card c : selectedList.getCards())
				{
					data2.add(c.getCardName());

				}
				doneButton2.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent e)
					{

						selectedCard = selectedList.getCards().get(data2.indexOf(selection2.getValue()));

						// dialog.close();

						Stage dialog3 = new Stage();
						VBox dialogVbox3 = new VBox(20);
						TextField textfield = new TextField();
						Button doneButton3 = new Button("Change name");
						doneButton3.setId("finallyDone");
						// Label label = new Label(textfield.getText());
						dialogVbox3.getChildren().add(textfield);
						dialogVbox3.getChildren().add(doneButton3);
						Scene dialogScene3 = new Scene(dialogVbox3, 300, 200);

						dialog2.setScene(dialogScene3);
						dialog2.show();

						doneButton3.setOnAction(new EventHandler<ActionEvent>()
						{

							@Override
							public void handle(ActionEvent e)
							{

								selectedCard.setCardName(textfield.getText());
								client.updateBoard(model);
								model.showBoardScreen(s, scene, model, client, modelg, u);
								dialog2.close();

							}

						});
					}

				});

			}

		});
	}

	@FXML
	void moveListClicked(ActionEvent event)
	{
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("selectionBox");
		Button doneButton = new Button("Select list to move");
		doneButton.setId("done");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{

			data.add(l.getListName());

		}
		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				dialog.close();
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));

				Stage dialog2 = new Stage();
				VBox dialogVbox2 = new VBox(20);
				ComboBox<String> selection2 = new ComboBox<String>();
				selection2.setId("selection2");
				Button doneButton2 = new Button("Move after list");
				doneButton2.setId("done2");
				// Label label = new Label(textfield.getText());
				dialogVbox2.getChildren().add(selection2);
				dialogVbox2.getChildren().add(doneButton2);
				Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
				ObservableList<String> data2 = FXCollections.observableArrayList();
				selection2.setItems(data2);
				dialog2.setScene(dialogScene2);
				dialog2.show();

				for (ListN l : model.getLists())
				{
					if (!l.equals(selectedList))
					{
						data2.add(l.getListName());
					} else
					{

					}

				}
				doneButton2.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent e)
					{
						selectedList2 = (ListN) model.getLists().get(data.indexOf(selection2.getValue()));
						dialog2.close();

						model.reorderList(selectedList, data.indexOf(selection2.getValue()));
						client.updateBoard(model);
						model.showBoardScreen(s, scene, model, client, modelg, u);

						// model.reorderList(selectedList2, data.indexOf(selection.getValue()));

					}

				});

			}

		});
	}

	@FXML
	void reorderCardClicked(ActionEvent event)
	{

		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("reorderSelect");
		Button doneButton = new Button("Select list");
		doneButton.setId("doneReorder");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{
			if (l.getCards().size() >= 2)
			{
				data.add(l.getListName());
			} else
			{
				System.out.println("No cards in list " + l.getListName());
			}

		}

		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));
				dialog.close();

				Stage dialog2 = new Stage();
				VBox dialogVbox2 = new VBox(20);
				ComboBox<String> selection2 = new ComboBox<String>();
				selection2.setId("cardSelect");
				Button doneButton2 = new Button("Select card");
				doneButton2.setId("doneSelecting");
				// Label label = new Label(textfield.getText());
				dialogVbox2.getChildren().add(selection2);
				dialogVbox2.getChildren().add(doneButton2);
				Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
				ObservableList<String> data2 = FXCollections.observableArrayList();
				selection2.setItems(data2);
				dialog2.setScene(dialogScene2);
				dialog2.show();
				for (Card c : selectedList.getCards())
				{
					data2.add(c.getCardName());

				}
				doneButton2.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent e)
					{
						dialog2.close();

						selectedCard = selectedList.getCards().get(data2.indexOf(selection2.getValue()));

						// dialog.close();

						Stage dialog3 = new Stage();
						VBox dialogVbox3 = new VBox(20);
						ComboBox<String> selection3 = new ComboBox<String>();
						selection3.setId("selectCombo");
						Button doneButton3 = new Button("Move with card");
						doneButton3.setId("moveWithCard");
						// Label label = new Label(textfield.getText());
						dialogVbox3.getChildren().add(selection3);
						dialogVbox3.getChildren().add(doneButton3);
						Scene dialogScene3 = new Scene(dialogVbox3, 300, 200);
						ObservableList<String> data3 = FXCollections.observableArrayList();
						selection3.setItems(data3);
						dialog3.setScene(dialogScene3);
						dialog3.show();
						for (Card c : selectedList.getCards())
						{
							if (!c.equals(selectedCard))
							{
								data3.add(c.getCardName());
							} else
							{

							}
						}

						doneButton3.setOnAction(new EventHandler<ActionEvent>()
						{

							@Override
							public void handle(ActionEvent e)
							{
								dialog3.close();
								selectedCard2 = selectedList.getCards().get(data3.indexOf(selection3.getValue()));

								selectedList.reorderCard(selectedCard, data2.indexOf(selection3.getValue()));
								client.updateBoard(model);
								model.showBoardScreen(s, scene, model, client, modelg, u);

							}

						});
					}

				});

			}

		});

	}

	@FXML
	void moveCardClicked(ActionEvent event)
	{
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		ComboBox<String> selection = new ComboBox<String>();
		selection.setId("moveCardCombo");
		Button doneButton = new Button("Select list");
		doneButton.setId("moveCardButton");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(selection);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		ObservableList<String> data = FXCollections.observableArrayList();
		selection.setItems(data);
		dialog.setScene(dialogScene);
		dialog.show();

		for (ListN l : model.getLists())
		{
			if (l.getCards().size() >= 1)
			{
				data.add(l.getListName());
			} else
			{
				System.out.println("No cards in list " + l.getListName());
			}

		}

		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				selectedList = (ListN) model.getLists().get(data.indexOf(selection.getValue()));
				dialog.close();

				Stage dialog2 = new Stage();
				VBox dialogVbox2 = new VBox(20);
				ComboBox<String> selection2 = new ComboBox<String>();
				selection2.setId("comboFinal");
				Button doneButton2 = new Button("Select card");
				doneButton2.setId("doneBtnCard");
				// Label label = new Label(textfield.getText());
				dialogVbox2.getChildren().add(selection2);
				dialogVbox2.getChildren().add(doneButton2);
				Scene dialogScene2 = new Scene(dialogVbox2, 300, 200);
				ObservableList<String> data2 = FXCollections.observableArrayList();
				selection2.setItems(data2);
				dialog2.setScene(dialogScene2);
				dialog2.show();
				for (Card c : selectedList.getCards())
				{
					data2.add(c.getCardName());

				}
				doneButton2.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent e)
					{
						dialog2.close();

						selectedCard = selectedList.getCards().get(data2.indexOf(selection2.getValue()));

						// dialog.close();
						Stage dialog3 = new Stage();
						VBox dialogVbox3 = new VBox(20);
						ComboBox<String> selection3 = new ComboBox<String>();
						selection3.setId("selectLister");
						Button doneButton3 = new Button("Select list");
						doneButton3.setId("btnChangeList");
						// Label label = new Label(textfield.getText());
						dialogVbox3.getChildren().add(selection3);
						dialogVbox3.getChildren().add(doneButton3);
						Scene dialogScene3 = new Scene(dialogVbox3, 300, 200);
						ObservableList<String> data3 = FXCollections.observableArrayList();
						selection3.setItems(data3);
						dialog3.setScene(dialogScene3);
						dialog3.show();

						for (ListN l : model.getLists())
						{
							data3.add(l.getListName());

						}

						doneButton3.setOnAction(new EventHandler<ActionEvent>()
						{

							@Override
							public void handle(ActionEvent e)
							{
								dialog3.close();
								selectedList2 = model.getLists().get(data3.indexOf(selection3.getValue()));

								selectedList.moveCard(selectedCard, selectedList2, 0);
								client.updateBoard(model);
								model.showBoardScreen(s, scene, model, client, modelg, u);
							}

						});
					}

				});

			}

		});
	}
	
    @FXML
    void addMember(ActionEvent event) {
    	Stage dialog3 = new Stage();
		VBox dialogVbox3 = new VBox(20);
		TextField textfield = new TextField();
		textfield.setId("addMemberText");
		Button doneButton3 = new Button("add member by username");
		doneButton3.setId("addUsername");
		// Label label = new Label(textfield.getText());
		dialogVbox3.getChildren().add(textfield);
		dialogVbox3.getChildren().add(doneButton3);
		Scene dialogScene3 = new Scene(dialogVbox3, 300, 200);
	
		dialog3.setScene(dialogScene3);
		dialog3.show();
		
		doneButton3.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				
				
				if(client.checkUsernamePassword(textfield.getText(), "centre1234")!=null){
					
					model.addMember(client.checkUsernamePassword(textfield.getText(), "centre1234"));
					client.updateBoard(model);
					textfield.setText("User added!");
		
				}
				else {
					textfield.setText("No user found");
				}
				
			}

		});
    }
    

    @FXML
    void removeMember(ActionEvent event) {
    	Stage dialog3 = new Stage();
		VBox dialogVbox3 = new VBox(20);
		TextField textfield = new TextField();
		textfield.setId("removeText");
		Button doneButton3 = new Button("delete member by username");
		doneButton3.setId("removeMember1");
		// Label label = new Label(textfield.getText());
		dialogVbox3.getChildren().add(textfield);
		dialogVbox3.getChildren().add(doneButton3);
		Scene dialogScene3 = new Scene(dialogVbox3, 300, 200);
	
		dialog3.setScene(dialogScene3);
		dialog3.show();
		
		doneButton3.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent e)
			{
				
				
				if(client.checkUsernamePassword(textfield.getText(), "centre1234")!=null){
					
					model.removeMember(client.checkUsernamePassword(textfield.getText(), "centre1234"));
					client.updateBoard(model);
					textfield.setText("User removed!");
		
				}
				else {
					textfield.setText("No user found");
				}
				
			}

		});
    }

	public void setModel(Stage s, Scene scene, RmiClient client, BoardConcrete selectedBoard, BorderPane pane,
			LoginModel modelg, User u)
	{

		this.client = client;
		this.model = selectedBoard;
		this.s = s;
		this.scene = scene;
		this.modelg = modelg;
		this.u = u;

		lists = model.getLists();

		if (lists != null)
		{
			for (ListN l : lists)
			{
				VBox vbox = new VBox();
				mainHBox.getChildren().add(vbox);
				Label label = new Label(l.getListName());
				vbox.getChildren().add(label);
				System.out.println("reached + " + l.getCards());
				for (Card c : l.getCards())
				{
					Button b = new Button(c.getCardName());
					b.setId("buttonReal");
					vbox.getChildren().add(b);
					b.setOnAction(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent e)
						{
							model.showCardView(s, scene, c, client, model, u, modelg);
							client.updateBoard(model);

						}
					});
					// .getChildren().add(b);

					// System.out.println("Button created with name:" + c.getCardName());
				}
				System.out.println(l.toString());

			}
		} else
		{
			System.out.println("No lists");
		}
	}
}
