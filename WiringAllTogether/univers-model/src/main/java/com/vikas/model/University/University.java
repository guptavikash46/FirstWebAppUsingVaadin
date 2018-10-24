package com.vikas.model.University;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class University {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ID;
	
	@NotNull(message="Please Specify the University Name")
	private String UniversityName;
	
	@NotNull(message="Please Specify the University City")
	private String UniversityCity;
	
	@NotNull(message="Please Specify the University Country")
	private String UniversityCountry;
	
	
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	
	public String getUniversityName() {
		return UniversityName;
	}

	public void setUniversityName(String universityName) {
		UniversityName = universityName;
	}

	

	public String getUniversityCountry() {
		return UniversityCountry;
	}

	public void setUniversityCountry(String universityCountry) {
		UniversityCountry = universityCountry;
	}

	public String getUniversityCity() {
		return UniversityCity;
	}

	public void setUniversityCity(String universityCity) {
		UniversityCity = universityCity;
	}
	public University() {
		
	}
	public String toString() {
		return UniversityName+" "+UniversityCity+" "+UniversityCountry;
	}

}
