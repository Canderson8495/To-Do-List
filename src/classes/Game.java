package classes;

import java.util.ArrayList;
import java.util.Date;

import javafx.scene.layout.Pane;

public class Game extends Platform{
	private String genre = "";
	private int length = 0;
	
	@Override
	public String toFile() {
		return super.toFile() + "|" + genre + "|" + length;
	}
	
	public static Game read(String input) {
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
		Game Game = new Game(values.get(0), new Date(Long.parseLong(values.get(1))), Integer.parseInt(values.get(2)),values.get(3),values.get(4), Integer.parseInt(values.get(5)));
		return Game;
	}
	

	@Override
	public Pane toFXVisual() {
		return new Pane();
	}

	public Game(String name, Date dateCreated, int priority, String description, String genre, int length) {
		super(name, dateCreated, priority, description);
		this.genre = genre;
		this.length = length;
		// TODO Auto-generated constructor stub
	}

	public Game(String name, int priority) {
		super(name, priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.getName();
	}

	public Game() {
		// TODO Auto-generated constructor stub
	}
}
