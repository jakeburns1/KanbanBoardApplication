package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainPack.RmiClient;
import mainPack.User;
import view.LoginModel;

public class InitialController
{
	LoginModel modelg;
	RmiClient client;
	SelectionController selection;
	User user;
	Stage s;
	Scene scene;
	
	 	@FXML
	    private TextField usernameField;

	    @FXML
	    private PasswordField passwordField;

	    @FXML
	    void loginPressed(ActionEvent event) {
	    	if(client.checkUsernamePassword(usernameField.getText(), passwordField.getText())!=null) {
	    		
	    		 user = client.checkUsernamePassword(usernameField.getText(), passwordField.getText());
	    		
	    		System.out.println("successfully logged in");
	    		// load board selection screen
	    		
	    		modelg.showSelectionScreen(user, client, s, scene, modelg);
	   
	    		
	    	}
	    	else {
	    		System.out.println("login failed");
	    	}
	    	
	    }
	    
	    public void setModel(LoginModel newModel1, RmiClient client, Stage s, Scene scene)
		{
			modelg = newModel1;
			this.client = client;
			this.s = s;
			this.scene = scene;
			
			//StringConverter<Number> fmt = new NumberStringConverter();
			
			//Bindings.bindBidirectional(totalLabel.textProperty(), model.getCount(), fmt);		
		}

}
