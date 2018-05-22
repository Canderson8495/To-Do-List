package classes;

import java.util.ArrayList;
import java.util.Date;

import javafx.scene.layout.Pane;

public class Task extends Platform {
	private String SchoolClass = "";
	private Date dueDate = new Date();
	
	@Override
	public String toFile() {
		return super.toFile() + "|" + SchoolClass + "|" + dueDate.getTime();
	}
	
	public static Task read(String input) {
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
		return new Task(values.get(0), new Date(Long.parseLong(values.get(1))), Integer.parseInt(values.get(2)), values.get(3), values.get(4), new Date(Long.parseLong(values.get(5))));
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
	
	public Task() {
		super();
	}

	public Task(String name, Date dateCreated, int priority, String description, String schoolClass,
			Date dueDate) {
		super(name, dateCreated, priority, description);
		SchoolClass = schoolClass;
		this.dueDate = dueDate;
	}
	
}
