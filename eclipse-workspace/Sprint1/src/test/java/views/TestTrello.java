package views;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.InitialController;
import main.MainStartup;
import mainPack.RmiClient;
import mainPack.RmiServer;
import view.LoginModel;

@ExtendWith(ApplicationExtension.class)

public class TestTrello
{
	
	RmiClient client;
	RmiServer server;
	AnchorPane pane;
	Scene s;
	int port = 3196;
	
	@Start
	public void start(Stage stage) throws Exception
	{
		//server.startServer(port);
		server = new RmiServer(port);
		client = new RmiClient(port);
		
		
		LoginModel model2 =  new LoginModel(stage, s);
		//UserConcrete model = new UserConcrete();
		
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
	public void testButton(FxRobot robot) {
		
		try
		{
			//Thread.sleep(1000);
			robot.clickOn("#usernameField");
			robot.write("jakeburns");
			robot.clickOn("#passwordField");
			robot.write("centre1234");
			robot.clickOn("#loginButton");
			//Thread.sleep(1000);
			robot.clickOn("#dropdown").type(KeyCode.DOWN).type(KeyCode.ENTER);
			robot.clickOn("#loadButton");
//			Thread.sleep(1000);
//			robot.clickOn("#listDropDown");
//			Thread.sleep(1000);
//			robot.clickOn("#addList");
//			Thread.sleep(1000);
//			robot.clickOn("#listDropDown");
//			robot.clickOn("#moveList");
//			robot.clickOn("#selectionBox").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			Thread.sleep(1000);
//			robot.clickOn("#done");
//			robot.clickOn("#selection2").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#done2");
//			Thread.sleep(1000);
//			robot.clickOn("#listDropDown");
//			Thread.sleep(1000);
//			robot.clickOn("#reorderCard");
//			Thread.sleep(1000);
//			robot.clickOn("#reorderSelect").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			Thread.sleep(2000);
//			robot.clickOn("#doneReorder");
//			Thread.sleep(2000);
//			robot.clickOn("#cardSelect").type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#doneSelecting");
//			Thread.sleep(1000);
//			robot.clickOn("#selectCombo").type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#moveWithCard");
//			Thread.sleep(1000);
//			robot.clickOn("#listDropDown");
//			Thread.sleep(1000);
//			robot.clickOn("#deleteList");
//			Thread.sleep(1000);
//			robot.clickOn("#listDeleteCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			Thread.sleep(1000);
//			robot.clickOn("#listDeleteDone");
//			robot.clickOn("#listDropDown");
//			Thread.sleep(1000);
//			robot.clickOn("#listRename");
//			Thread.sleep(1000);
//			robot.clickOn("#renameCombo").type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#renameDone");
//			robot.clickOn("#renameText");
//			robot.write("Day 0");
//			Thread.sleep(1000);
//			robot.clickOn("#changeRename");
//			Thread.sleep(1000);
//			robot.clickOn("#cardDropDown");
//			Thread.sleep(1000);
//			robot.clickOn("#addCard");
//			robot.clickOn("#addCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#addCardDone");
//			robot.clickOn("#cardDropDown");
//			robot.clickOn("#deleteCard");
//			robot.clickOn("#deleteCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#deleteCardDone");
//			robot.clickOn("#deleteAnotherCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#selectThat");
//			robot.clickOn("#cardDropDown");
//			robot.clickOn("#renameCard");
//			robot.clickOn("#renameCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#renameDone");
//			robot.clickOn("#nextSelectRename").type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#doneRenaming");
//			robot.write("Third Card");
//			robot.clickOn("#finallyDone");
//			robot.clickOn("#cardDropDown");
//			robot.clickOn("#moveNewList");
//			robot.clickOn("#moveCardCombo").type(KeyCode.DOWN).type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#moveCardButton");
//			robot.clickOn("#comboFinal").type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#doneBtnCard");
//			robot.clickOn("#selectLister").type(KeyCode.DOWN).type(KeyCode.ENTER);
//			robot.clickOn("#btnChangeList");
			robot.clickOn("#buttonReal");
			//robot.clickOn(buttons)
			//robot.clickOn(SelectionModel<String>.)
			//robot.clickOn();
			Thread.sleep(1000000000);
			
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //Label test = robot.lookup("#label").queryAs(Label.class);
		//Assertions.assertThat(robot.lookup("#label").queryAs(Label.class)).hasText("1");

	}
	
}
