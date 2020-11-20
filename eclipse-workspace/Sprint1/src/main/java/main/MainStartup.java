package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainPack.RmiClient;
import mainPack.RmiServer;
import view.LoginModel;



public class MainStartup extends Application
{

	RmiClient client;
	RmiServer server;
	AnchorPane pane;
	Scene s;
	int port = 3420;
	@Override
	public void start(Stage stage) throws Exception
	{
		//server.startServer(port);
		server = new RmiServer(port);
		client = new RmiClient(port);
		
		
		LoginModel model2 =  new LoginModel(stage, s);
		//UserConcrete model = new UserConcrete();
		
		FXMLLoader loader = new FXMLLoader();
		
		
		loader.setLocation(MainStartup.class.getResource("loginView.fxml"));
		
		 pane = loader.load();
		
		InitialController contt = loader.getController(); 
		s = new Scene(pane);
		
		contt.setModel(model2, client, stage, s);
		
		 
		
	
		
		
	
		
		stage.setScene(s);
		stage.show();
	}

	
	public static void main(String [] args) {	
		launch(args);
	}
	
	
	
}
