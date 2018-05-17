package application.File;

import java.awt.List;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import application.Main;
import classes.Book;
import classes.Movie;
import classes.SchoolWork;
import classes.Series;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class addEntryVisualController {
	String value = "NULL";
	private Main main;
	
	@FXML private AnchorPane platformField;
	@FXML private ComboBox platformBox;
	
	String usedValue;
	@FXML private ComboBox priorityBox;
	ObservableList<String> priorityList = FXCollections.observableArrayList("High", "Medium", "Low");
	
	
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
		System.out.println("CALLED");
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
		if(value != platformBox.getValue()) {
			System.out.println("Rendering platform information");
			//Add a condition to not reset if platform's value was not changed.
			//Let's make the controller this file also
			System.out.println("We're heressfsdf");
			if(platformBox.getValue().equals("Book")) {
				System.out.println("We're here");
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/NewBook.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
				//THIS IS FUCKING IT^^^^^^^^^^^^^^^
				System.out.println("We're here");
				//render book's extra values
			}else if(platformBox.getValue().equals("Movie")){
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/newMovie.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
				//render movie's extra values
			}else if(platformBox.getValue().equals("SchoolWork")){
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/NewSchoolWork.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
				//render schoolWork's extra values
			}else if(platformBox.getValue().equals("Series")) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("File/NewSeries.fxml"));
				loader.setController(this);
				AnchorPane ex = loader.load();
				platformField.getChildren().setAll(ex.getChildren());
				//render Serie's exta values
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
		//Can i just pass in the entry manually, like a book object, and make everything the function with platform.
		if(platformBox.getValue().equals("Book")) {
			//public Book(String name, Date dateCreated, int priority, String description, int numPage, String author)
			//Look at using constants for this
			Book book = new Book(nameField.getText(), new Date(), priority, descriptionField.getText(), Integer.parseInt(pageNumField.getText()), authorField.getText() );
			Main.db.addEntry(book);
		}else if(platformBox.getValue().equals("Movie")){
			//Movie(String name, Date dateCreated, int priority, String description, int length, Date dateReleased)
			Date date = Date.from(dateReleasedField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Main.db.addEntry(new Movie(nameField.getText(), new Date(), priority, descriptionField.getText(), Integer.parseInt(lengthField.getText()),date));
		}else if(platformBox.getValue().equals("SchoolWork")){
			Date date = Date.from(dateDueField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Main.db.addEntry(new SchoolWork(nameField.getText(), new Date(), priority, descriptionField.getText(), classField.getText(),date));
		}else if(platformBox.getValue().equals("Series")) {
			Main.db.addEntry(new Series(nameField.getText(), new Date(), priority, descriptionField.getText(), Integer.parseInt(episodesField.getText()), Integer.parseInt(episodesFinishedField.getText())));
		}
		System.out.println("WTF");
		Main.closeSecondaryStage();
	}
}
