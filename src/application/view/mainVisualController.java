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
	private Main main;
	
	@FXML 
	private void initialize() {
	}
	@FXML
	private void goAddVisual() throws IOException {
		System.out.println("We're here");
		main.showAddVisual();
	}
	@FXML
	private void goNewDatabaseVisual() throws IOException {
		System.out.println("We're here");
		main.showNewDatabaseVisual();
	}
	@FXML
	private void goSave() throws IOException {
		Main.db.write();
	}
	
	@FXML
	private void goOpen() throws IOException{
		Main.showOpenFileVisual();
		//Open a file picker that will then allow the user to select the database
	}
	@FXML
	private void goDelete() throws IOException{
		Main.deleteEntry();
	}
}
