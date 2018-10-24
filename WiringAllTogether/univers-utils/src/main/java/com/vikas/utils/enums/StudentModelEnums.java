package com.vikas.utils.enums;

public enum StudentModelEnums {

	MAIN_MENU("Main Menu"),
	SHOW_ALL_STUDENTS("Show all students"),
	FIRSTNAME("First Name"),
	LASTNAME("Last Name"),
	AGE("Age"),
	GENDER("Gender"),
	EMAIL("Email"),
	MALE("Male"),
	FEMALE("Female"),
	TRANSGENDER("LGBT"),
	SAVEBUTTON("Save"),
	CLEARBUTTON("Clear"),
	DONOTWANTTODISCLOSE("DON'T WANT TO DISCLOSE"),
	REMOVE_ALL_STUDENTS("REMOVE STUDENTS"), UNIV_LIST("Select University");
	
	private final String string;
	
	private StudentModelEnums(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
}
