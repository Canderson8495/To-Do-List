package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import classes.Book;
import classes.Database;
import classes.Game;
import classes.Movie;
import classes.Platform;
import classes.Series;
import classes.Task;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	//A database will be loaded whenever the program is loaded.
	public static Database db = new Database();
	//The main window will show our database.
	private static Stage primaryStage = new Stage();
	//This is the pane that occupies the primaryStage
	public static BorderPane mainVisual = new BorderPane();
	//this holds the table of all of our entries
	public static TabPane entryPane = new TabPane();
	//THis window is used for the windows with the add and shit.
	public static Stage secondaryStage = new Stage();

	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("To Do List");
		//Reads from config file, and inputs the settings to the program.
		//Loads the last used database.
		loadConfig();
		showMainVisual();
		primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

	        public void handle(KeyEvent ke) {
	            if (ke.getCode() == KeyCode.DELETE) {
	               try {
					deleteEntry();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            }
	        }
	    });
	}
	
	//Loads Menu and tab Panes
	private void showMainVisual() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/mainVisual.fxml"));
		mainVisual = loader.load();
		Scene scene = new Scene(mainVisual);
		primaryStage.setScene(scene);
		primaryStage.show();
		showListVisual();
	}

	//Might just make this into a table view, each tab pane representing a different table which represents a different platorm
	public static void showListVisual() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/EntryList.fxml"));
		//When loading the EntryList FXML file, the controller is called. When the controller is called, the initializer loads the database.
		entryPane = loader.load();
		mainVisual.setCenter(entryPane);
	}

	public static void showAddVisual() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("File/addEntryVisual.fxml"));
		BorderPane addVisual = loader.load();
		secondaryStage = new Stage();
		secondaryStage.setTitle("Add new Entry");
		secondaryStage.initModality(Modality.WINDOW_MODAL);
		secondaryStage.initOwner(primaryStage);
		Scene scene = new Scene(addVisual);
		secondaryStage.setScene(scene);
		secondaryStage.show();
	}

	public static void showNewDatabaseVisual() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("File/newDatabaseVisual.fxml"));
		BorderPane newVisual = loader.load();
		secondaryStage = new Stage();
		secondaryStage.setTitle("New Database");
		secondaryStage.initModality(Modality.WINDOW_MODAL);
		secondaryStage.initOwner(primaryStage);
		Scene scene = new Scene(newVisual);
		secondaryStage.setScene(scene);
		secondaryStage.show();
	}
	
	//Terminates the next stage, and reloads the list visual
	public static void closeSecondaryStage() throws IOException {
		secondaryStage.close();
		showListVisual();
	}


	public static void showOpenFileVisual() throws IOException {
		FileChooser FC = new FileChooser();
		File file = FC.showOpenDialog(secondaryStage);
        if (file != null) {
        	//Writes to the config with the last loaded file.
        	FileWriter configWriter = new FileWriter("src\\application\\config.txt");
    		PrintWriter configPrintWriter = new PrintWriter(configWriter);
    		configPrintWriter.println(file.toString());
    		configPrintWriter.close();
    		
    		//POSSIBLY NOT NEEDED
    		showListVisual();
    		db = new Database();
            db.parse(file);
            showListVisual();
        }
	}
	
	public static void loadConfig() throws IOException {
		//The values of the array will be initialized as the pattern for parsing state above
		File file = new File("tmp");
		java.io.File configFile = new java.io.File("src\\application\\config.txt");
		try(Scanner scan = new Scanner(configFile)){
			file = new File(scan.nextLine());
		}catch(Exception error) {
			System.out.println("Preloaded File not valid");
		}
		if (file != null) {
    		db = new Database();
            db.parse(file);
            showListVisual();
        }
	} 
	
	public static void deleteEntry() throws IOException{
		TableView table = (TableView)entryPane.getSelectionModel().getSelectedItem().getContent();
		for(int x = 0; x < table.getSelectionModel().getSelectedIndices().size(); x++) {
			db.deleteEntry(entryPane.getSelectionModel().getSelectedIndex(), (int)table.getSelectionModel().getSelectedIndices().get(x));
		}
		showListVisual();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
