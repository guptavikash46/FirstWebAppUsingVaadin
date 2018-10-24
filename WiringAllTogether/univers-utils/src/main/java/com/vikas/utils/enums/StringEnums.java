package com.vikas.utils.enums;

public enum StringEnums {
	
	MENU_STUDENT("STUDENTS"),
	MENU_UNIVERSITY("UNIVERSITY"),
	MENU_ADDSTD("ADD STUDENT"),
	MENU_RMVSTD("REMOVE STUDENT"),
	MENU_OPT("OPERATIONS"),
	;

	private final String names;

	
	private StringEnums(String names) {
		this.names = names;
		}
	
	
public String getString() {
	return names;
}
	
}
