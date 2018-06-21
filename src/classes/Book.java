package classes;

import java.util.ArrayList;
import java.util.Date;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class Book extends Platform{
	private int numPage = 0;
	private String author = "";
	
	@Override
	public String toFile() {
		return super.toFile() + "|" + numPage + "|" + author;
	}
	
	//Essentially like im subleasing the parsing functions to the classes.
	public static Book read(String input) {
		System.out.println("We're here");
		ArrayList<String> values = new ArrayList<String>();
		StringBuffer str = new StringBuffer("");
		for(int x = 0; x < input.length(); x++) {
			if(input.charAt(x) != '|') {
				str.append(input.charAt(x));
			}else {
				values.add(str.toString());
				str.delete(0, str.length());
			}
		}
		values.add(str.toString());
		Book book = new Book(values.get(0), new Date(Long.parseLong(values.get(1))), Integer.parseInt(values.get(2)), values.get(3), Integer.parseInt(values.get(4)), values.get(5));
			System.out.println(book);
		return book;
	}
	
	//Deprecated.
	@Override
	public Pane toFXVisual() {
		return new Pane();
	}

	public Book(String name, Date dateCreated, int priority, String description, int numPage, String author) {
		super(name, dateCreated, priority, description);
		this.numPage = numPage;
		this.author = author;
		// TODO Auto-generated constructor stub
	}

	public Book(String name, int priority) {
		super(name, priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.getName();
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}
	
}
