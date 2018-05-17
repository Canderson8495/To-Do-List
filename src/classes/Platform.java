package classes;

import java.util.Date;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Platform {
	public final int HIGH = 1;
	public final int MEDIUM = 2;
	public final int LOW = 3;
	
	private String name = "";
	private Date dateCreated = new Date();
	private int priority = 0;
	private String description = "";
	
	
	
	public String toFile() {
		return name + "|"+ dateCreated.getTime() + "|" + priority + "|" + description;
	}
	
	public static Platform read(String input) {
		return new Platform();
	}
	
	public Pane toFXVisual() {
		Pane pane = new Pane();
		
		Label name = new Label(this.name);
		pane.getChildren().add(name);
		
		return pane;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	public Platform() {
		super();
	}
	public Platform(String name, int priority) {
		super();
		this.name = name;
		this.priority = priority;
	}
	public Platform(String name, int priority, String description) {
		super();
		this.name = name;
		this.priority = priority;
		this.description = description;
	}
	public Platform(String name, Date dateCreated, int priority, String description) {
		super();
		this.name = name;
		this.dateCreated = dateCreated;
		this.priority = priority;
		this.description = description;
	}
	
}
