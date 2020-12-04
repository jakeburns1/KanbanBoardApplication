package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

public class SelectionController {
	RmiClient client;
	Stage s;
	Scene scene;
	LoginModel modelg;

	User model;

	ArrayList<Board> boards;

	BoardController boardController;

	@FXML
	private Label selectBoardLabel;
	// BoardConcrete bmodel;
	// User userData;

	@FXML
	private ComboBox<String> dropDownField = new ComboBox<String>();

	ObservableList<String> data = FXCollections.observableArrayList();

	@FXML
	void createNewBoard(ActionEvent event) {
		client.createBoard("New Board", model);
		BoardConcrete boarder = new BoardConcrete();
		boarder.setBoardName("New Board");
		ListN starterList = new ListConcrete("Sample List");
		Set<String> labels = new HashSet<String>();
		labels.add("Example Label");
		Set<User> members = new HashSet<User>();
		members.add(model);
		boarder.setOwner(model);
		Set<Component> components = new HashSet<Component>();
		Card card1 = new CardConcrete("Example Card", labels, members, components);
		card1.setCardName("Demo Card");
		model.addBoardtoUser(boarder);
		boarder.addList(starterList);
		starterList.addCards(card1);
		boarder.save();
		client.updateBoard(boarder);
		// boarder.addList(listToAdd)
		boarder.showBoardScreen(s, scene, boarder, client, modelg, model);
	}

	@FXML
	void loadBoardButton(ActionEvent event) {
		BoardConcrete selectedBoard = (BoardConcrete) boards.get(data.indexOf(dropDownField.getValue()));
		// client.getBoard("Server");
		selectedBoard.showBoardScreen(s, scene, selectedBoard, client, modelg, model);
		// System.out.println(selectedBoard);
	}

	public void setModel(User user, RmiClient client, Stage s, Scene scene, LoginModel modelg) {
		this.model = user;
		this.client = client;
		this.s = s;
		this.scene = scene;
		this.modelg = modelg;
		boards = model.getBoards();

		selectBoardLabel.setText(user.getUsername() + ", select your board:");
		// Board board = boards.get(0);
		for (Board b : boards) {
			data.add(b.getBoardName().toString());

		}
		dropDownField.setItems(data);
		// System.out.println(board.getBoardName());

		// FXCollections.observableArrayList(data);

		// StringConverter<Number> fmt = new NumberStringConverter();

		// Bindings.bindBidirectional(totalLabel.textProperty(), model.getCount(), fmt);
	}

}
