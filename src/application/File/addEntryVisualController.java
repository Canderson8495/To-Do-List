package application.File;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import application.Main;
import classes.Book;
import classes.Game;
import classes.Movie;
import classes.Task;
import classes.Series;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class addEntryVisualController {
	String value = "NULL";
	
	@FXML private AnchorPane platformField;
	@FXML private ComboBox<String> platformBox;
	
	String usedValue;
	@FXML private ComboBox<String> priorityBox;
	ObservableList<String> priorityList = FXCollections.observableArrayList("High", "Medium", "Low");
	
	@FXML private TextField genreField;
	@FXML private TextField	classField;
	@FXML private DatePicker	dateDueField;
	@FXML private TextField	episodesField;
	@FXML private TextField	episodesFinishedField;
	@FXML private TextField nameField;
	@FXML private TextField descriptionField;
	@FXML private TextField lengthField;
	@FXML private TextField authorField;
	@FXML private TextField pageNumField;
	@FXML private DatePicker dateReleasedField;
	
	@FXML
	private void initialize() {
		ObservableList<String> platformList = FXCollections.observableArrayList();
		ArrayList<String> tmp = Main.db.getPlatformsInUse();
		for(int x = 0; x < tmp.size(); x++) {
			platformList.add(tmp.get(x));
		}
		if(usedValue == null) {
			platformBox.setValue(platformList.get(0));
		}else {
			platformBox.setValue(usedValue);
		}
		platformBox.setItems(platformList);
		priorityBox.setItems(priorityList);
		priorityBox.setValue(priorityList.get(1));
	}
	
	@FXML private void platformBoxChange() {
		usedValue = (String)platformBox.getValue();
	}
	
	@FXML private void renderPlatformInfo() throws IOException {
		//This condition below will prevent a reset of inputed fields when switching between tabs.
		if(value != platformBox.getValue()) {
			//Rendering platform information
			System.out.println("Rendering platform information");
			if(platformBox.getValue().equals("Book")) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/NewBook.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
			}else if(platformBox.getValue().equals("Movie")){
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/newMovie.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
			}else if(platformBox.getValue().equals("Task")){
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/NewTask.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
			}else if(platformBox.getValue().equals("Series")) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/NewSeries.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
			}else if(platformBox.getValue().equals("Game")) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/NewGame.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
			}
			value = (String)platformBox.getValue();
		}
	}
	
	@FXML
	private void OkBtn() throws IOException {
		int priority = 0;
		if((String)priorityBox.getValue() == "Low") {
			priority = 3;
		}else if((String)priorityBox.getValue() == "Medium") {
			priority = 2;
		}else {
			priority = 1;
		}
		//Just pass in the entry manually, like a book object, and make everything the function with platform.
		if(platformBox.getValue().equals("Book")) {
			Book book = new Book(nameField.getText(), new Date(), priority, descriptionField.getText(), Integer.parseInt(pageNumField.getText()), authorField.getText() );
			Main.db.addEntry(book);
		}else if(platformBox.getValue().equals("Movie")){
			Date date = Date.from(dateReleasedField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Main.db.addEntry(new Movie(nameField.getText(), new Date(), priority, descriptionField.getText(), Integer.parseInt(lengthField.getText()),date));
		}else if(platformBox.getValue().equals("Task")){
			Date date = Date.from(dateDueField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Main.db.addEntry(new Task(nameField.getText(), new Date(), priority, descriptionField.getText(), classField.getText(),date));
		}else if(platformBox.getValue().equals("Series")) {
			Main.db.addEntry(new Series(nameField.getText(), new Date(), priority, descriptionField.getText(), Integer.parseInt(episodesField.getText()), Integer.parseInt(episodesFinishedField.getText())));
		}else if(platformBox.getValue().equals("Game")) {
			Main.db.addEntry(new Game(nameField.getText(), new Date(), priority, descriptionField.getText(), genreField.getText(), Integer.parseInt(lengthField.getText())));
		}
		Main.closeSecondaryStage();
	}
}
