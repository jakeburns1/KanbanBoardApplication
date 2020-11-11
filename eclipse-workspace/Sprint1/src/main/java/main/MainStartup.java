package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mainPack.RmiClient;
import view.LoginModel;


public class MainStartup extends Application
{

	//RmiClient client;
	@Override
	public void start(Stage stage) throws Exception
	{
		
		
		LoginModel model2 =  new LoginModel();
		
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(MainStartup.class.getResource("./loginView.fxml"));
		
		
		AnchorPane pane = loader.load();
		
		InitialController contt = loader.getController(); 
		
		contt.setModel(model2);
		
		Scene s = new Scene(pane);
		
		stage.setScene(s);
		stage.show();
	}

	
	public static void main(String [] args) {	
		launch(args);
	}
	
}
