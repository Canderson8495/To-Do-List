package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Database {
	//A list of a list of our platforms
	//Assign values of the platforms to an arraylist?
	private ArrayList<String> platformsAvailable = new ArrayList<String>();
	private ArrayList<String> platformsInUse = new ArrayList<String>();
	private ArrayList<ArrayList<Platform>> platforms = new ArrayList<ArrayList<Platform>>();
	String name;
	public Database(String name) {
		super();
		this.name = name;
	}
	public Database(ArrayList<String> platformsToBeUsed, String name) {
		this();
		this.platformsInUse = platformsToBeUsed;
		for(int x = 0; x < platformsInUse.size(); x++) {
			platforms.add(new ArrayList<Platform>());
		}
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void addEntry(Platform entry) {
		if(entry instanceof Book) {
			platforms.get(platformsInUse.indexOf("Book")).add((Book)entry);
			System.out.println(entry);
		}else if(entry instanceof Movie) {
			platforms.get(platformsInUse.indexOf("Movie")).add((Movie)entry);
		}else if(entry instanceof Task) {
			platforms.get(platformsInUse.indexOf("Task")).add((Task)entry);
		}else if(entry instanceof Series) {
			platforms.get(platformsInUse.indexOf("Series")).add((Series)entry);
		}else if(entry instanceof Game) {
			platforms.get(platformsInUse.indexOf("Game")).add((Game)entry);
		}
	}
	public void deleteEntry(Platform entry) {
		if(entry instanceof Book) {
			platforms.get(platformsInUse.indexOf("Book")).remove((Book)entry);
		}else if(entry instanceof Movie) {
			platforms.get(platformsInUse.indexOf("Movie")).remove((Movie)entry);
		}else if(entry instanceof Task) {
			platforms.get(platformsInUse.indexOf("Task")).remove((Task)entry);
		}else if(entry instanceof Series) {
			platforms.get(platformsInUse.indexOf("Series")).remove((Series)entry);
		}else if(entry instanceof Game) {
			platforms.get(platformsInUse.indexOf("Game")).remove((Game)entry);
		}
	}
	public void deleteEntry(int index, int secondindex) {
		platforms.get(index).remove(secondindex);
	}
	public ArrayList<Platform> getPlatformList(String platformName) {
		return platforms.get(platformsInUse.indexOf(platformName));
	}
	public ArrayList<String> getPlatformsInUse() {
		return platformsInUse;
	}
	public void setPlatformsInUse(ArrayList<String> platformsInUse) {
		this.platformsInUse = platformsInUse;
	}
	public void addPlatform(String platform) {
		
	}
	public Platform getEntry(String platform, String entryName) {
		Platform entry = new Platform();
		ArrayList<Platform> tmp= platforms.get(platformsInUse.indexOf(platform));
		for(int x = 0; x < tmp.size(); x++) {
			if(tmp.get(x).getName() == entryName) {
				entry = tmp.get(x);
			}
		}
		if(entry instanceof Book) {
			return (Book)entry;
		}else if(entry instanceof Movie) {
			return (Movie)entry;
		}else if(entry instanceof Task) {
			return (Task)entry;
		}else if(entry instanceof Series) {
			return (Series)entry;
		}else if(entry instanceof Game) {
			return (Game)entry;
		}
		return entry;
	}
	public ArrayList<String> getPlatformsAvailable(){
		return this.platformsAvailable;
	}
	
	public TableView<Platform> getTableVisual(String platformName) throws IOException{
		//Chaning our internal database to an observable list
		ArrayList<Platform> arr = platforms.get(platformsInUse.indexOf(platformName));
		ObservableList<Platform> obvList = FXCollections.observableArrayList();
			for(int x = 0; x < arr.size(); x++) {
				obvList.add(arr.get(x));
				
			}
			//tmp.setItems(obvList);
		
		TableView<Platform> tmp = new TableView<Platform>();
		//Creating the columns and their cell factories for their common platform variables
		
		//name column
		TableColumn<Platform, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		//Date Created column
		TableColumn<Platform, Date> dateCreatedColumn = new TableColumn<>("Date Created");
		dateCreatedColumn.setMinWidth(175);
		dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

		//name column
		TableColumn<Platform, Integer> priorityColumn = new TableColumn<>("Priority");
		priorityColumn.setMinWidth(50);
		priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));

		//name column
		TableColumn<Platform, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(300);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		tmp.setItems(obvList);
		//I need to add the logic for adding more tables based on the platform, that's not difficult in itself, but I have to create more space for the categories.
		tmp.getColumns().addAll(nameColumn,dateCreatedColumn, priorityColumn, descriptionColumn);
		return tmp;
	}
	//Add the another else if statement if another a new platform is added.
	public void write() throws IOException {
		try {
		FileWriter writer = new FileWriter(name+".txt");
		PrintWriter printWriter = new PrintWriter(writer);	
		printWriter.println(name);
		for(int c = 0; c  < platforms.size(); c++) {
				printWriter.println(platformsInUse.get(c));
				ArrayList<Platform> arr = platforms.get(c);
				for(int x = 0; x < arr.size(); x++) {
					if(arr.get(x) instanceof Book) {
						printWriter.println(((Book)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Movie) {
						printWriter.println(((Movie)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Task) {
						printWriter.println(((Task)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Series) {
						printWriter.println(((Series)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Game) {
						printWriter.println(((Game)arr.get(x)).toFile());
					}
				}
		}
			writer.close();
		}catch(IOException error) {
			System.out.println("ERROR" + error);
		}
	}
	public void write(File file) throws IOException {
		try {
			System.out.println("Entered overloaded write function");
		FileWriter writer = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(writer);	
		printWriter.println(name);
		for(int c = 0; c  < platforms.size(); c++) {
				printWriter.println(platformsInUse.get(c));
				ArrayList<Platform> arr = platforms.get(c);
				for(int x = 0; x < arr.size(); x++) {
					if(arr.get(x) instanceof Book) {
						printWriter.println(((Book)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Movie) {
						printWriter.println(((Movie)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Task) {
						printWriter.println(((Task)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Series) {
						printWriter.println(((Series)arr.get(x)).toFile());
					}else if(arr.get(x) instanceof Game) {
						printWriter.println(((Game)arr.get(x)).toFile());
					}
				}
		}
			writer.close();
		}catch(IOException error) {
			System.out.println("ERROR" + error);
		}
	}
	public void parse(java.io.File file) {
		String line;
		//The values of the array will be initialized as the pattern for parsing state above
		String platform = "";
		try(Scanner scan = new Scanner(file)){
			name = scan.nextLine();
			while(scan.hasNextLine()) {
				boolean pass = false;
				line = scan.nextLine();
				for(int x = 0; x < platformsAvailable.size();x++) {
					if(line.equals(platformsAvailable.get(x))) {
						platformsInUse.add(line);
						System.out.println(line);
						platforms.add(new ArrayList<Platform>());
						pass = true;
						platform = line;
						break;
					}
				}
				if(pass) {
					continue;
				}
				try {
					System.out.println("PLATFORM" +line);
					if(platform.equals("Book")) {
						platforms.get(platformsInUse.indexOf("Book")).add((Book.read(line)));
					}else if(platform.equals("Movie")) {
						platforms.get(platformsInUse.indexOf("Movie")).add((Movie.read(line)));
					}else if(platform.equals("Task")) {
						platforms.get(platformsInUse.indexOf("Task")).add((Task.read(line)));
					}else if(platform.equals("Series")) {
						platforms.get(platformsInUse.indexOf("Series")).add((Series.read(line)));
					}else if(platform.equals("Game")) {
						platforms.get(platformsInUse.indexOf("Game")).add((Game.read(line)));
					}
				}catch(Exception e){
					System.out.println("ERROR" + e);
				}
				//Loading a Person object and throwing it into a arrayList that is to be returned.
			}
		}catch(FileNotFoundException error) {
			System.out.println("The file doesn't exist|"+error);
		}catch(NumberFormatException error) {
			System.out.println("There is an issue w/ the file setup |" + error);
		}
	}

	
	public Database() {
		super();
		platformsAvailable.add("Book");
		platformsAvailable.add("Movie");
		platformsAvailable.add("Task");
		platformsAvailable.add("Series");
		platformsAvailable.add("Game");
	}
	public Database(String args[]) {
		for(String e : args) {
			platformsAvailable.add(e);
			platforms.add(new ArrayList<Platform>());
		}
	}
}
