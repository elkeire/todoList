package datamodel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;

public class TodoData {
	private static TodoData instance =  new TodoData();

	private static String filename = "TodoListItems.txt";

	private List<TodoItem> todosBucket;

	private DateTimeFormatter formatter;

	public static TodoData getInstance()
	{
		return instance;
	}

	//PRIVATE CONSTRUCTOR for SINGLETON
	private TodoData()
	{
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	}

	/**
	 * @return the todosBucket
	 */
	public List<TodoItem> getTodosBucket() {
		return todosBucket;
	}
	
	public void addTodoItem(TodoItem item)
	{
		todosBucket.add(item);
	}

	//	/**
	//	 * @param todosBucket the todosBucket to set
	//	 */
	//	public void setTodosBucket(List<TodoItem> todosBucket) {
	//		this.todosBucket = todosBucket;
	//	}


	public void loadTodoItems() throws IOException
	{
		todosBucket = FXCollections.observableArrayList();
		Path path = Paths.get(filename);
		BufferedReader br = Files.newBufferedReader(path);
		//Dig in
		String input;
		while((input = br.readLine()) != null)
		{
			String[] itemPieces = input.split("\t");
			// indiviualise the date
			String shortDescription = itemPieces[0];
			String details = itemPieces[1];
			String dateString =itemPieces[2];

			//Sort out the date format
			LocalDate date = LocalDate.parse(dateString, formatter);
			TodoItem item = new TodoItem(shortDescription, details, date);
			todosBucket.add(item);

		}
	}

	public void storeTodoItems() throws IOException
	{
		Path path = Paths.get(filename);
		BufferedWriter bw = Files.newBufferedWriter(path);
		
		try
		{
			Iterator<TodoItem> iter = todosBucket.iterator();
			while(iter.hasNext())
			{
				TodoItem item = iter.next();
				bw.write(String.format("%s\t%s\t%s",
						item.getShortDescription(),
						item.getDetails(),
						item.getDeadline().format(formatter)
						));
				bw.newLine();
			}
		} finally
		{
			if(bw != null)
			{
				bw.close();
			}
		}
	}
	
	
	

}
