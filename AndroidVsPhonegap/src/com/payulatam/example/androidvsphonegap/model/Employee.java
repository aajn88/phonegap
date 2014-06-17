package com.payulatam.example.androidvsphonegap.model;

public class Employee {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private int pictureResourceId;

	public Employee() {
	}

	public Employee(String id, String firstName, String lastName, String email, int pictureResourceId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pictureResourceId = pictureResourceId;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public int getPictureResourceId() {
		return pictureResourceId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPictureResourceId(int pictureResourceId) {
		this.pictureResourceId = pictureResourceId;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + "\n" + email;
	}

}
