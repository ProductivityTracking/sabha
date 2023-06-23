package com.nkxgen.spring.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T4_SprintResources")
public class SprintResource {

	@Id
	@Column(name = "sprn_id")
	private int sprintId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User masterId;

	private String role;

	public int getSprintId() {
		return sprintId;
	}

	public void setSprintId(int sprintId) {
		this.sprintId = sprintId;
	}

	public User getMasterId() {
		return masterId;
	}

	public void setMasterId(User masterId) {
		this.masterId = masterId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Constructors

}
