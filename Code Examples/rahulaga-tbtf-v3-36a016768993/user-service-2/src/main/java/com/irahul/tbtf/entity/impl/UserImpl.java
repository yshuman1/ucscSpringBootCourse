package com.irahul.tbtf.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.irahul.tbtf.entity.User;

@Entity
@Table(name = "users")
public class UserImpl implements User {
	@Id
	@Column(name = "idusers")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "pin")
	private String pin;

	public UserImpl() {
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "UserImpl [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pin=" + pin + "]";
	}
}
