package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginModel
{
		Stage stage;
		Scene s;
	/**
	 * 
	 */
	public LoginModel(Stage stage, Scene s)
	{
		super();
		this.stage = stage;
		this.s = s;
	}
	
	
	
	public void showSelectionScreen() {
		
		
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginModel.class.getResource("../main/boardSelection.fxml"));
			BorderPane pane = loader.load();
			s = new Scene(pane);
			stage.setScene(s);
			stage.show();
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
