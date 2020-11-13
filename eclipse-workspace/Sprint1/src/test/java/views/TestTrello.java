package views;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	int port = 3147;
	
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
			Thread.sleep(1000);
			robot.clickOn("#usernameField");
			robot.write("jakeburns");
			robot.clickOn("#passwordField");
			robot.write("centre1234");
			robot.clickOn("#loginButton");
			//Thread.sleep(1000);
			robot.clickOn("#dropdown");
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
