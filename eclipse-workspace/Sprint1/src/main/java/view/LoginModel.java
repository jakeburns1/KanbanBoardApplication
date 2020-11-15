package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.SelectionController;
import mainPack.RmiClient;
import mainPack.User;

public class LoginModel
{
		Stage stage;
		Scene s;
		SelectionController controller;
	/**
	 * 
	 */
	public LoginModel(Stage stage, Scene s)
	{
		super();
		this.stage = stage;
		this.s = s;
	}
	
	
	
	public void showSelectionScreen(User u, RmiClient client, Stage stage, Scene scene, LoginModel modelg) {
		
		
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginModel.class.getResource("../main/boardSelection.fxml"));
			BorderPane pane = loader.load();
			
			SelectionController cont = loader.getController();
			cont.setModel(u, client, stage, scene, modelg);
			s = new Scene(pane);
			stage.setScene(s);
			
			stage.show();
			
			
//			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
