package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
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
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Database db = new Database();
	private static Stage primaryStage = new Stage();
	public static BorderPane mainVisual = new BorderPane();
	public static TabPane entryPane = new TabPane();
	public static Stage secondaryStage = new Stage();

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("To Do List");
		loadConfig();
		showMainVisual();

	}

	private void showMainVisual() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/mainVisual.fxml"));
		mainVisual = loader.load();
		Scene scene = new Scene(mainVisual);
		primaryStage.setScene(scene);
		primaryStage.show();
		showListVisual();
	}

	
	//Deprecating
	public static void showEntryVisual(Platform entry){
		mainVisual.setLeft(entry.toFXVisual());
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

	public static void closeSecondaryStage() throws IOException {
		secondaryStage.close();
		showListVisual();
	}

	
	//Might just make this into a table view, each tab pane representing a different table which represents a different platorm
	public static void showListVisual() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/EntryList.fxml"));
		entryPane = loader.load();
		mainVisual.setCenter(entryPane);
		//Probably going to have to make this fxml with its own controller
		
	}
	
	public static void showTabVisual() {
		
	}
	
	public static void showOpenFileVisual() throws IOException {
		FileChooser FC = new FileChooser();
		File file = FC.showOpenDialog(secondaryStage);
        if (file != null) {
        	FileWriter configWriter = new FileWriter("src\\application\\config.txt");
    		PrintWriter configPrintWriter = new PrintWriter(configWriter);
    		configPrintWriter.println(file.toString());
    		configPrintWriter.close();
    		showListVisual();
    		db = new Database();
        	System.out.println("WHERE IT IS");
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
			System.out.println(file.toString() + "????");
    		db = new Database();
        	System.out.println("WHERE IT IS");
            db.parse(file);
            showListVisual();
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
