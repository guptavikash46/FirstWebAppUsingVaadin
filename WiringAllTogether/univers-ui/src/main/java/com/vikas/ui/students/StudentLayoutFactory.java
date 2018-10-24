package com.vikas.ui.students;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vikas.ui.common.mainUI;
import com.vikas.utils.enums.StudentModelEnums;

@SpringView(name=StudentLayoutFactory.NAME, ui = mainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View, StudentSavedListener{

	private TabSheet tabSheet;
	
	@Autowired
	private AddStudentTabLayoutFactory addStudentTabLayoutFactory;
	
	@Autowired
	private ShowStudentsTabLayoutFactory showStudentsTabLayoutFactory;
	
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}

	private void addLayout() {
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addStutabLayout = addStudentTabLayoutFactory.createComponent(this);
		Component showStutab =   showStudentsTabLayoutFactory.createComponent();
		
		tabSheet.addTab(addStutabLayout, StudentModelEnums.MAIN_MENU.getString());
		tabSheet.addTab(showStutab, StudentModelEnums.SHOW_ALL_STUDENTS.getString());
		
		addComponent(tabSheet);
	}

	public static final String NAME = "addstudent";

	public void studentSaved() {
		showStudentsTabLayoutFactory.refreshTable();
		
	}
}
