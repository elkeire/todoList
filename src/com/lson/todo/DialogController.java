package com.lson.todo;


import java.time.LocalDate;

import datamodel.TodoData;
import datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogController {
	@FXML
	private TextField shortDescriptionField;
	@FXML
	private TextArea detailsArea;
	
	@FXML
	private DatePicker deadlinePicker;
	
//	Process the results from the New To do Dialog
	public TodoItem processResults()
	{
		String shortDescription = shortDescriptionField.getText().trim();
		String details = detailsArea.getText().trim();
		LocalDate deadlineValue = deadlinePicker.getValue();
		TodoItem newItem = new TodoItem(shortDescription, details, deadlineValue);
		TodoData.getInstance().addTodoItem(newItem);
		return newItem;
	}

}
