package com.nkxgen.spring.orm.model;

public class SprintTasksInput {
	private int sprintId;
	private int taskId;
	private int userId;

	public int getSprintId() {
		return sprintId;
	}

	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public SprintTasks toEntity() {
		SprintTasks st = new SprintTasks();
		SprintTasksId sti = new SprintTasksId();
		sti.setTaskId(this.taskId);
		sti.setSprnId(this.sprintId);
		st.setId(sti);
		User u = new User();
		u.setUserId(this.userId);
		st.setUser(u);

		return null;
	}
	// Additional properties, constructors, and other methods
}
