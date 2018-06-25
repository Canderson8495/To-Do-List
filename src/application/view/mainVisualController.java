package application.view;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import classes.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class mainVisualController {

	@FXML
	private static AnchorPane entryPane;
	
	@FXML 
	private void initialize() {
	}
	@FXML
	private void goAddVisual() throws IOException {
		Main.showAddVisual();
	}
	@FXML
	private void goNewDatabaseVisual() throws IOException {
		Main.showNewDatabaseVisual();
	}
	@FXML
	private void goSave() throws IOException {
		Main.showSaveFileVisual();
	}
	
	@FXML
	private void goSaveAs() throws IOException{
		Main.showSaveAsFileVisual();
	}
	
	@FXML
	private void goOpen() throws IOException{
		Main.showOpenFileVisual();
	}
	@FXML
	private void goDelete() throws IOException{
		Main.deleteEntry();
	}
}
