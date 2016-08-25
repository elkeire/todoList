package datamodel;

import java.time.LocalDate;

public class TodoItem {
	
	private String shortDescription;
	private String details;
	private LocalDate deadline;
	/**
	 * @param shortDescription
	 * @param details
	 * @param deadline
	 */
	public TodoItem(String shortDescription, String details, LocalDate deadline) {
		super();
		this.shortDescription = shortDescription;
		this.details = details;
		this.deadline = deadline;
	}
	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}
	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * @return the deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}
	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return shortDescription;
	}
	
	
	
	
	
	
	

}
