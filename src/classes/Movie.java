package classes;

import java.util.ArrayList;
import java.util.Date;

import javafx.scene.layout.Pane;

public class Movie extends Platform {
	private int length = 0;
	private Date dateReleased = new Date();
	
	@Override
	public String toFile() {
		return super.toFile() + "|" + length + "|" + dateReleased.getTime();
	}
	
	public static Movie read(String input) {
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
		return new Movie(values.get(0), new Date(Long.parseLong(values.get(1))), Integer.parseInt(values.get(2)), values.get(3), Integer.parseInt(values.get(4)), new Date(Long.parseLong(values.get(5))));
	}
	
	@Override
	public Pane toFXVisual() {
		Pane pane = super.toFXVisual();
		return pane;
	}
	
	@Override
	public String toString() {
		return super.getName();
	}

	public Movie() {
		super();
	}

	public Movie(String name, Date dateCreated, int priority, String description, int length, Date dateReleased) {
		super(name, dateCreated, priority, description);
		this.length = length;
		this.dateReleased = dateReleased;
	}
	
}
