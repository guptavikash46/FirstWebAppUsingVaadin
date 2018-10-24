package com.vikas.utils.enums;

public enum NotificationEnums {

	NOTIFICATION_ERROR_TITLE("Error"),
	NOTIFICATION_ERROR_DESCRIPTION("Please check that all the fields are filled with appropriate values."),
	NOTIFICATION_SUCCESS("Student added successfully."), NOTIFICATION_DELETEDATA_TITLE("DELETE MESSAGE"),
	NOTIFICATION_DELETEDATA_DESC("SELECTED DATA HAS BEEN SUCCESSFULLY DELETED"), 
	NOTIFICATION_DELETE_BUTTON("DELETE"),
	NOTIFICATION_ADD_UNIV_FAIL_TITLE("ERROR"), 
	NOTIFICATION_ADD_UNIV_FAIL_DESC("Please make sure to provide values in the fields"), 
	NOTIFICATION_ADD_UNIV_SUCCESS_MSG("University information saved successfully!"), 
	NOTIFICATION_UNIV_FOUND_TITLE("ERROR"),
	NOTIFICATION_UNIV_FOUND_DESCRIPTION("Sorry! you cannot add a student without a university.\nPlease consider entering"
			+ " a university info in the university operations section."),
	
	;
	
	private String string;
	
	private NotificationEnums(String string) {
        this.string = string;
	}
	public String getString() {
		return string;
	}
}
