package com.lson.todo;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class Controller {
	private List<TodoItem> todoItems;
	@FXML
	private ListView<TodoItem> todoListView;
	@FXML
	private TextArea itemDetailsTextArea;
	@FXML
	private Label deadLineLabel;
	@FXML
	private BorderPane mainBorderPane;
	
	public void initialize()
	{
//		TodoItem item1 = new TodoItem("Drum Class", "First Class at 4pm. Get Timbale from Timone",
//				LocalDate.of(2016, Month.SEPTEMBER, 5));
//		TodoItem item2 = new TodoItem("Car", "Go and get new car",
//				LocalDate.of(2016, Month.SEPTEMBER, 7));
//		TodoItem item3 = new TodoItem("Cleaners", "Lift the Dry Cleaning at 12",
//				LocalDate.of(2016, Month.SEPTEMBER, 12));
//		TodoItem item4 = new TodoItem("Song", "Add the final edit to the sequence to move forward in Logic",
//				LocalDate.of(2016, Month.OCTOBER, 5));
//		TodoItem item5 = new TodoItem("Raymond", "Record the Podcast for Java on Azure",
//				LocalDate.of(2016, Month.AUGUST, 5));
//		
//		todoItems = new ArrayList<TodoItem>();
//		todoItems.add(item1);
//		todoItems.add(item2);
//		todoItems.add(item3);
//		todoItems.add(item4);
//		todoItems.add(item5);
//		
//		TodoData.getInstance().setTodosBucket(todoItems);
		
		
		todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
			@Override
			public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
				if(newValue != null){
					TodoItem item = todoListView.getSelectionModel().getSelectedItem();
					itemDetailsTextArea.setText(item.getDetails());
					DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
					deadLineLabel.setText(df.format(item.getDeadline())); //removed a toString here
				}
				
			}
		});
		
		todoListView.getItems().setAll(TodoData.getInstance().getTodosBucket());
		todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		todoListView.getSelectionModel().selectFirst();
		
	}
	
	@FXML
	public void showNewItemDialog()
	{
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add A New Todo Item");
		dialog.setHeaderText("Use this dialog to add to the todos list...... Set in code");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
		try
		{
			
			dialog.getDialogPane().setContent(fxmlLoader.load());
			//Before defining the FXMLLoader above
//			Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
//			dialog.getDialogPane().setContent(root);
			
		}catch(IOException ioe)
		{
			System.out.println("Coudn't load dialog");
			ioe.printStackTrace();
			return;
		}
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> result = dialog.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK)
		{
			DialogController controller = fxmlLoader.getController();
			TodoItem newItem = controller.processResults();
			todoListView.getItems().setAll(TodoData.getInstance().getTodosBucket());
			todoListView.getSelectionModel().select(newItem);
			System.out.println("OK pressed");
		} else {
			System.out.println("Cancel pressed");
		}
			
		
	}
	
	@FXML
	public void handleClickListView()
	{
		TodoItem item = todoListView.getSelectionModel().getSelectedItem();
		itemDetailsTextArea.setText(item.getDetails());
		deadLineLabel.setText(item.getDeadline().toString());
//		System.out.println("The selected item is: " + item);
//		StringBuilder sb = new StringBuilder(item.getDetails());
//		sb.append("\n\n\n\n");
//		sb.append("Due: ");
//		sb.append(item.getDeadline().toString());
//		itemDetailsTextArea.setText(sb.toString());
	}
	
	
}
