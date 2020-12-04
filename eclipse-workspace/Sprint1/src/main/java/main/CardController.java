package main;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainPack.BoardConcrete;
import mainPack.Card;
import mainPack.ChecklistComponent;
import mainPack.Component;
import mainPack.DescriptionComponent;
import mainPack.ListConcrete;
import mainPack.ListN;
import mainPack.RmiClient;
import mainPack.RmiServer;
import mainPack.User;
import view.LoginModel;

public class CardController {

	BoardConcrete model;
	ListConcrete list;
	LoginModel modelg;
	RmiClient client;
	RmiServer server;
	Stage s;
	Scene scene;
	Card card;
	User u;

	ArrayList<String> items = new ArrayList<String>();
	ArrayList<String> checked = new ArrayList<String>();
	ArrayList<CheckBox> boxesArray = new ArrayList<CheckBox>();
	Component checklist = new ChecklistComponent();

	@FXML
	private Label descriptionLabel;

	@FXML
	private ListView<String> listView;

	ObservableList<String> data = FXCollections.observableArrayList();

	@FXML
	private VBox cardLabelBox;

	@FXML
	private Label nameLabel;

	@FXML
	private VBox checkListBox;

	@FXML
	void doneClicked(ActionEvent event) {

		model.showBoardScreen(s, scene, model, client, modelg, u);
		client.updateBoard(model);
	}

	@FXML
	void editCheckListClicked(ActionEvent event) {
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		TextField textfield = new TextField();
		textfield.setId("textfieldChecklist");
		Button addButton = new Button("Add Item");
		addButton.setId("addCheck");
		Button deleteButton = new Button("Delete Item");
		deleteButton.setId("deleteCheck");
		// Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(textfield);
		dialogVbox.getChildren().add(addButton);
		dialogVbox.getChildren().add(deleteButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);

		checklist.setChecked(checked);
		dialog.setScene(dialogScene);
		dialog.show();
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				CheckBox cb = new CheckBox(textfield.getText());

				cb.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (cb.isSelected() == true) {
							checked.add(cb.getText());
							System.out.println("REACHED CHECKED");

						} else {
							checked.remove(cb.getText());
						}
					}
				});

				items.add(textfield.getText());

				// checkListBox.getChildren().add(cb);
				checkListBox.getChildren().add(1, cb);

				boxesArray.add(cb);

				card.addComponent(checklist);
				client.updateBoard(model);
			}
		});
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// CheckBox cb = new CheckBox(textfield.getText());
				try {
					if (items != null) {
						for (String s : items) {
							if (s.equalsIgnoreCase(textfield.getText())) {
								// checklist.setItems(items);
								try {
									checkListBox.getChildren().remove(items.indexOf(s) + 1);
								} catch (Exception g) {

								}
								items.remove(textfield.getText());
								for (Component check : card.getCheckListComponent()) {
									for (String a : check.getItems()) {
										if (textfield.getText().equals(a)) {
											card.deleteComponent(check);
										}
									}

								}
								card.deleteComponent(checklist);
								client.updateBoard(model);
//						for (CheckBox c : boxesArray)
//						{
//							if (s.equals(textfield.getText()))
//								checkListBox.getChildren().remove(s);
//							boxesArray.remove(s);
//							break;
//						}
							} else {

							}
						}
					}
				} catch (Exception f) {

				}
			}

		});

	}

	@FXML
	void editDescriptionClicked(ActionEvent event) {
		descriptionLabel.setText("changed");
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		TextField textfield = new TextField();
		textfield.setId("textDescription");
		Button doneButton = new Button("Done");
		doneButton.setId("doneDes");
		dialogVbox.getChildren().add(textfield);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();

		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// System.out.println(textfield.getText());
				for (ListN l : model.getLists()) {
					for (Card c : l.getCards()) {
						if (c.getCardName().equals(card.getCardName())) {
							card = c;
						}
						// vbox.getChildren().add(b);
					}
				}
				// descriptionLabel.setText(textfield.getText());
				Component description = new DescriptionComponent(textfield.getText());
				card.addComponent(description);
				dialog.close();
				client.updateBoard(model);
			}
		});

	}

	@FXML
	void editLabelsClicked(ActionEvent event) {
		// descriptionLabel.setText("changed");
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		TextField textfield = new TextField();
		textfield.setId("labelTextField");
		Button addButton = new Button("Add Label");
		addButton.setId("addLabelBtnn");
		Button deleteButton = new Button("Delete Label");
		deleteButton.setId("deleteButtonLabel");
		Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(textfield);
		dialogVbox.getChildren().add(addButton);
		dialogVbox.getChildren().add(deleteButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();

		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(textfield.getText());
				card.createLabel(textfield.getText());
				client.updateBoard(model);
			}
		});
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					for (String s : data) {
						if (s.equalsIgnoreCase(textfield.getText())) {
							data.remove(textfield.getText());
							// listView.setItems(data);
							card.deleteLabel(textfield.getText());
							client.updateBoard(model);
						} else {

						}

					}
				} catch (Exception y) {

				}
			}
		});
	}

	@FXML
	void editNameClicked(ActionEvent event) {
		Stage dialog = new Stage();
		VBox dialogVbox = new VBox(20);
		TextField textfield = new TextField();
		textfield.setId("textfieldEditName");
		Button doneButton = new Button("Done");
		doneButton.setId("doneBtnName");
		Label label = new Label(textfield.getText());
		dialogVbox.getChildren().add(textfield);
		dialogVbox.getChildren().add(doneButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				nameLabel.setText(textfield.getText());
				card.setCardName(textfield.getText());
				client.updateBoard(model);
				dialog.close();
			}
		});
	}

	public void setModel(Stage s, Scene scene, Card card, RmiClient c, BoardConcrete model, User u, LoginModel modelg) {
		this.s = s;
		this.scene = scene;
		this.card = card;
		this.client = c;
		this.model = model;
		this.modelg = modelg;
		this.u = u;

		// DescriptionComponent description = card.getComponents().toArray();
		if (card.getDesComponent() != null) {

			descriptionLabel.setText(this.card.getDesComponent().getText());
		} else {
			descriptionLabel.setText("Description");
		}

		nameLabel.setText(card.getCardName());
//		ObservableList<String> l = FXCollections.observableArrayList();
		data.addAll(card.getLabels());
		listView.setItems(data);
		checklist.setItems(items);
		if (card.getCheckListComponent() != null) {
			for (Component check : card.getCheckListComponent()) {
				items.addAll(check.getItems());

				for (String a : check.getItems()) {
					CheckBox cb = new CheckBox(a);
					checkListBox.getChildren().add(1, cb);
					for (String checkers : check.getChecked()) {
						if (checkers.equalsIgnoreCase(a)) {
							cb.setSelected(true);
						}
					}
				}
			}

		} else {
			System.out.println("no checklist");
		}

	}

}
