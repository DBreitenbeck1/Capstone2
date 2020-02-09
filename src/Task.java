
public class Task {
	private String TMName;
	private String taskDescription;
	private Boolean complete = false;
	String dueDate;
	
	String dateRegex = "\\d{2}/\\d{2}/\\d{4}";
	
	public Task(String name, String td, String due) {
		this.TMName = name;
		this.taskDescription = td;
		this.dueDate = due;
	}

	public String getTMName() {
		return TMName;
	}

	public void setTMName(String tMName) {
		TMName = tMName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Task [TMName=" + TMName + ", taskDescription=" + taskDescription + ", complete=" + complete
				+ ", dueDate=" + dueDate + "]";
	}
	
	
	
}
