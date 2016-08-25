package com.lson.todo;

import java.io.IOException;

import datamodel.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MAINUI.fxml"));
			Scene scene = new Scene(root,700,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		try
		{
			TodoData.getInstance().storeTodoItems();
		} catch(IOException IOE)
		{
			System.out.println(IOE.getMessage());
		}
	}

	@Override
	public void init() throws Exception {
		try
		{
			TodoData.getInstance().loadTodoItems();
		} catch(IOException IOE)
		{
			System.out.println(IOE.getMessage());
		}
	}
}
