package classes;

import java.util.ArrayList;
import java.util.Date;

import javafx.scene.layout.Pane;

public class Series extends Platform {
	int episodesAvailable = 0;
	int episodesFinished = 0;
	
	@Override
	public String toFile() {
		return super.toFile() + "|" + episodesAvailable + "|" + episodesFinished;
	}
	
	public static Series read(String input) {
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
		return new Series(values.get(0), new Date(Long.parseLong(values.get(1))), Integer.parseInt(values.get(2)), values.get(3), Integer.parseInt(values.get(4)), Integer.parseInt(values.get(5)));
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
	
	public Series(String name, Date dateCreated, int priority, String description, int episodesAvailable, int episodesFinished) {
		super(name, dateCreated, priority, description);
		this.episodesAvailable = episodesAvailable;
		this.episodesFinished = episodesFinished;
	}
	public Series() {
		super();
	}
	
	

}
