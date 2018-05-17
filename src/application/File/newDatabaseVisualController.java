package application.File;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import classes.Database;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class newDatabaseVisualController {
	@FXML
	private VBox platformVBox;
	@FXML
	private TextField nameField;

	@FXML
	private void initialize() {
		System.out.println("Are we here");
		ArrayList<String> platformsAvailable = Main.db.getPlatformsAvailable();
		
		
		for(int x = 0; x < platformsAvailable.size(); x++) {
			System.out.println("we're here");
			platformVBox.getChildren().add(new RadioButton(platformsAvailable.get(x)));
		}
	}
	
	@FXML
	private void OkBtn() throws IOException {
		ArrayList<String> platformsAvailable = Main.db.getPlatformsAvailable();
		ArrayList<String> platformsChecked = new ArrayList<String>();
		for(int x = 0; x< platformVBox.getChildren().size(); x++) {
			if(((RadioButton) platformVBox.getChildren().get(x)).isSelected()) {
				platformsChecked.add(platformsAvailable.get(x));
			}
		}
		Main.db = new Database(platformsChecked, nameField.getText());
		Main.closeSecondaryStage();
		Main.showListVisual();
		//Close the window here
		
	}
}
