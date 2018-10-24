package com.vikas.model.student;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vikas.model.University.University;


@Entity
public class student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ID;
	
	@NotNull(message="Please specify the First Name")
	private String FirstName;
	
	@NotNull(message="Please specify the Last Name")
	private String LastName;
	
	@NotNull(message="Please specify the Age")
	@Min(value=14, message="Minimum age is 14")
	@Max(value=70, message="Maximum age is 70")
	private Integer Age;
	
	@NotNull(message="Please enter a valid email")
	private String Email;
	
	@NotNull(message="Select the gender")
	private String Gender;
	
	@NotNull(message="Please select a university from the list")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "University_id")
	private University university;
	
	public student() {
		
	}

	public Integer getID() {
		return ID;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		this.Age = age;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}
	@Override
	public String toString() {
		
		return FirstName+" "+LastName+" "+Age+ " "+Email+" "+Gender;
	}
}
