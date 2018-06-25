package application.view;

import java.util.ArrayList;

import application.Main;
import classes.Book;
import classes.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Observable;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.*;

public class EntryListVisualController {
	@FXML private TabPane tabPane;
	@FXML private void initialize() throws IOException {
		tabPane.getTabs().clear();

		//Dynamically creates all the tab based upon whether or not they're in use.
		for(int x = 0; x < Main.db.getPlatformsInUse().size(); x++) {
			tabPane.getTabs().add(new Tab(Main.db.getPlatformsInUse().get(x)));
			tabPane.getTabs().get(x).setContent(Main.db.getTableVisual(Main.db.getPlatformsInUse().get(x)));
		}
	}
}