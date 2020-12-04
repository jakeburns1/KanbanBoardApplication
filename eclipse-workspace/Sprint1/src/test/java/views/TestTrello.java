package views;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.BoardController;
import main.InitialController;
import mainPack.Board;
import mainPack.CardConcrete;
import mainPack.Component;
import mainPack.ListConcrete;
import mainPack.ListN;
import mainPack.RmiClient;
import mainPack.RmiServer;
import mainPack.User;
import mainPack.UserConcrete;
import view.LoginModel;

@ExtendWith(ApplicationExtension.class)

public class TestTrello {

	static RmiServer server;
	static RmiClient client;
	AnchorPane pane;
	Scene s;

	User jake;
	User bob;
	User newUser;
	Board board;
	BoardController controller;

	ListConcrete list;
	ListConcrete list2;
	CardConcrete testCard;
	CardConcrete testCard2;
	CardConcrete testCard3;
	Set<String> labels;
	Set<String> labels2;
	Set<String> labels3;
	Set<User> members;
	Set<Component> components;
	Set<Component> components2;
	Set<Component> components3;
	ArrayList<ListN> lists;

	@BeforeEach
	void set() {
		lists = new ArrayList<ListN>();
		list = new ListConcrete("Day 1");
		list2 = new ListConcrete("Day 2");
		lists.add(list);
		lists.add(list2);
		members = new HashSet<User>();
		components = new HashSet<Component>();
		components2 = new HashSet<Component>();
		components3 = new HashSet<Component>();
		labels = new HashSet<String>();
		labels2 = new HashSet<String>();
		labels3 = new HashSet<String>();

		jake = new UserConcrete();
		bob = new UserConcrete();
		members.add(jake);
		members.add(bob);
		jake.createBoard("test board 1", jake, members, lists);
		// jake.createBoard("test board 2", jake, members, null);
		bob.createBoard("test board 3", bob, members, null);
		jake.setPassword("centre1234");
		jake.setUsername("jakeburns");
		bob.setPassword("centre1234");
		bob.setUsername("bob");
		board = jake.getBoards().get(0);

		labels.add("jake");
		labels.add("bob");
		labels3.add("jake");

		testCard = new CardConcrete("Test card", labels, members, components);
		testCard2 = new CardConcrete("Second card", labels2, members, components2);
		testCard3 = new CardConcrete("thiiird card", labels3, members, components3);

		list.addCards(testCard);
		list.addCards(testCard2);

		list2.addCards(testCard3);
		server.save(board);
		server.createBoard("Testing please", jake);

	}

	@BeforeAll
	static void setUp() throws Exception {
		int port = 4347;

		server = new RmiServer(port);
		// server.startServer(port);

		client = new RmiClient(port);

	}

	@AfterAll
	static void stop() {
		server.shutdown();
	}

	@Test
	void test() throws RemoteException {

	}

	@Start
	public void start(Stage stage) throws Exception {
		// server.startServer(port);
		// server = new RmiServer(port);
		// client = new RmiClient(port);

		LoginModel model2 = new LoginModel(stage, s);
		// UserConcrete model = new UserConcrete();

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(TestTrello.class.getResource("../main/loginView.fxml"));

		pane = loader.load();

		InitialController contt = loader.getController();
		s = new Scene(pane);

		contt.setModel(model2, client, stage, s);

		stage.setScene(s);
		stage.show();
	}

	@Test
	public void testLogin(FxRobot robot) throws Exception {

		// server.loadBoardFromDisk();

		client.createBoard("banana", jake);
		client.createBoard("a", bob);
		bob.storeToDisk();
		jake.storeToDisk();
		client.updateBoard(board);
		server.loadBoardFromDisk();

		client.updateBoard(board);
		try {
			Thread.sleep(1000);
			robot.clickOn("#usernameField");
			robot.write("jakeburns");
			robot.clickOn("#passwordField");
			robot.write("centre1234");
			robot.clickOn("#loginButton");
			testLoadBoard(robot);
			testListFunctions(robot);
			testCardFuncs(robot);
			testIndvidualCards(robot);
			testMemberDropDown(robot);
			testSaveAndExit(robot);
			testCreateNewBoard(robot);
			testRenameBoard(robot);
			testDeleteBoard(robot);
			testAddFilter(robot);
			// Thread.sleep(100000);
			// server.shutdown();

			// Thread.sleep(1000);
			// Thread.sleep(1000000000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Label test = robot.lookup("#label").queryAs(Label.class);
		// Assertions.assertThat(robot.lookup("#label").queryAs(Label.class)).hasText("1");

	}

	public void testLoadBoard(FxRobot robot) {

		robot.clickOn("#dropdown").type(KeyCode.DOWN).type(KeyCode.ENTER);
		robot.clickOn("#loadButton");
	}

	public void testListFunctions(FxRobot robot) {
		try {
			robot.clickOn("#listDropDown");
			Thread.sleep(1000);
			robot.clickOn("#addList");
			robot.clickOn("#listDropDown");
			robot.clickOn("#moveList");
			robot.clickOn("#selectionBox").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#done");
			robot.clickOn("#selection2").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#done2");
			robot.clickOn("#listDropDown");
			robot.clickOn("#reorderCard");
			robot.clickOn("#reorderSelect").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			Thread.sleep(2000);
			robot.clickOn("#doneReorder");
			Thread.sleep(2000);
			robot.clickOn("#cardSelect").type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#doneSelecting");
			Thread.sleep(1000);
			robot.clickOn("#selectCombo").type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#moveWithCard");
			Thread.sleep(1000);
			robot.clickOn("#listDropDown");
			Thread.sleep(1000);
			robot.clickOn("#deleteList");
			Thread.sleep(1000);
			robot.clickOn("#listDeleteCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			Thread.sleep(1000);
			robot.clickOn("#listDeleteDone");
			robot.clickOn("#listDropDown");
			Thread.sleep(1000);
			robot.clickOn("#listRename");
			Thread.sleep(1000);
			robot.clickOn("#renameCombo").type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#renameDone");
			robot.clickOn("#renameText");
			robot.write("Day 0");
			Thread.sleep(1000);
			robot.clickOn("#changeRename");
		} catch (Exception e) {

		}
	}

	public void testCardFuncs(FxRobot robot) {
		try {
			Thread.sleep(1000);
			robot.clickOn("#cardDropDown");
			Thread.sleep(1000);
			robot.clickOn("#addCard");
			robot.clickOn("#addCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#addCardDone");
			robot.clickOn("#cardDropDown");
			robot.clickOn("#deleteCard");
			robot.clickOn("#deleteCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#deleteCardDone");
			robot.clickOn("#deleteAnotherCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#selectThat");
			robot.clickOn("#cardDropDown");
			robot.clickOn("#renameCard");
			robot.clickOn("#renameCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#renameDone");
			robot.clickOn("#nextSelectRename").type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#doneRenaming");
			robot.write("Third Card");
			robot.clickOn("#finallyDone");
			robot.clickOn("#cardDropDown");
			robot.clickOn("#moveNewList");
			robot.clickOn("#moveCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#moveCardButton");
			robot.clickOn("#comboFinal").type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#doneBtnCard");
			robot.clickOn("#selectLister").type(KeyCode.DOWN).type(KeyCode.ENTER);
		} catch (Exception e) {

		}
	}

	public void testIndvidualCards(FxRobot robot) {
		try {
			robot.clickOn("#btnChangeList");
			robot.clickOn("#buttonReal");
			robot.clickOn("#editLabelButton");
			robot.clickOn("#labelTextField");
			robot.write("Jake");
			robot.clickOn("#addLabelBtnn");
			robot.clickOn("#labelTextField");
			robot.eraseText(4);
			robot.write("Burns");
			robot.clickOn("#addLabelBtnn");
			robot.clickOn("#deleteButtonLabel");
			robot.clickOn("#labelTextField");
			robot.eraseText(5);
			robot.write("Jake");
			robot.clickOn("#deleteButtonLabel");
			robot.clickOn("#doneMain");
			robot.clickOn("#buttonReal");
			robot.clickOn("#editDescription");
			robot.clickOn("#textDescription");
			robot.write("This is the description!");
			robot.clickOn("#doneDes");
			robot.clickOn("#doneMain");
			robot.clickOn("#buttonReal");
			robot.clickOn("#editName");
			robot.clickOn("#textfieldEditName");
			robot.write("Finish it!");
			robot.clickOn("#doneBtnName");
			robot.clickOn("#editCheck");
			robot.clickOn("#textfieldChecklist");
			robot.write("Jake");
			robot.clickOn("#addCheck");
			robot.clickOn("#deleteCheck");
			robot.clickOn("#textfieldChecklist");
			robot.eraseText(4);
			robot.write("Burns");
			robot.clickOn("#addCheck");
			robot.clickOn("#deleteCheck");
			robot.clickOn("#doneMain");
		} catch (Exception e) {

		}
	}

	public void testMemberDropDown(FxRobot robot) {
		try {
			robot.clickOn("#members");
			robot.clickOn("#addMember");
			robot.clickOn("#addMemberText");
			robot.write("banana");
			robot.clickOn("#addUsername");
			robot.clickOn("#addMemberText");
			robot.eraseText(13);
			robot.write("jakeburns");
			robot.clickOn("#addUsername");
			robot.clickOn("#members");
			robot.clickOn("#removeMember");
			robot.clickOn("#removeText");
			robot.write("banana");
			robot.clickOn("#removeMember1");
			robot.clickOn("#removeText");
			robot.eraseText(13);
			robot.clickOn("#removeText");
			robot.write("jakeburns");
			robot.clickOn("#removeMember1");
		} catch (Exception e) {

		}
	}

	public void testSaveAndExit(FxRobot robot) {
		try {
			robot.clickOn("#fileDropDown");
			robot.clickOn("#saveBoard");
			robot.clickOn("#fileDropDown");
			robot.clickOn("#exitBoard");

		} catch (Exception e) {

		}
	}

	public void testCreateNewBoard(FxRobot robot) {
		try {
			robot.clickOn("#createBoard");
			robot.clickOn("#fileDropDown");
			robot.clickOn("#saveBoard");
			robot.clickOn("#fileDropDown");
			robot.clickOn("#exitBoard");
		} catch (Exception e) {

		}
	}

	public void testRenameBoard(FxRobot robot) {
		try {
			robot.clickOn("#dropdown").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#loadButton");
			robot.clickOn("#fileDropDown");
			robot.clickOn("#renameBoard");
			robot.clickOn("#renameTextBoard");
			robot.write("Bradshaw's Board");
			robot.clickOn("#changeRenameBoard");
			robot.clickOn("#fileDropDown");
			robot.clickOn("#exitBoard");

		} catch (Exception e) {

		}
	}

	public void testDeleteBoard(FxRobot robot) {
		try {
			robot.clickOn("#dropdown").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#loadButton");
			robot.clickOn("#fileDropDown");
			robot.clickOn("#deleteBoard");
			robot.clickOn("#dropdown");
		} catch (Exception e) {

		}
	}

	public void testAddFilter(FxRobot robot) {
		testLoadBoard(robot);
		robot.clickOn("#filterDropDown");
		robot.clickOn("#addFilter");
		robot.clickOn("#filterText");
		robot.write("jake");
		robot.clickOn("#addThatFilter");
		robot.clickOn("#filterDropDown");
		robot.clickOn("#addFilter");
		robot.clickOn("#filterText");
		robot.write("bob");
		robot.clickOn("#addThatFilter");
		robot.clickOn("#filterDropDown");
		robot.clickOn("#addFilter");
		robot.clickOn("#filterText");
		robot.write("bradshaw");
		robot.clickOn("#addThatFilter");
		robot.clickOn("#filterDropDown");
		robot.clickOn("#removeFilter");
		robot.clickOn("#selectDeleteFilter").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.DOWN)
				.type(KeyCode.ENTER);
		robot.clickOn("#deleteFilter");
		robot.clickOn("#filterDropDown");
		robot.clickOn("#removeFilter");
		robot.clickOn("#selectDeleteFilter").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
		robot.clickOn("#deleteFilter");
		robot.clickOn("#filterDropDown");
		robot.clickOn("#removeFilter");
		robot.clickOn("#selectDeleteFilter").type(KeyCode.DOWN).type(KeyCode.ENTER);
		robot.clickOn("#deleteFilter");

	}
}
