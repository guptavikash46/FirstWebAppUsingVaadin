package com.vikas.utils.enums;

public enum UniversityEnums {

	UNIVERSITY_NAME("University Name"),
	UNIVERSITY_COUNTRY("University Country"),
	UNIVERSITY_CITY("University City"),
	UNIV_SAVE_BUTTON("SAVE");
	
	private String string;
	
	UniversityEnums(String str) {
		this.string =str;
	}
	public String getString() {
		return string;
	}
}
