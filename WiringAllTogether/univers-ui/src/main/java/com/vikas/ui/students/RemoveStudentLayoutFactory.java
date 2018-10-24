package com.vikas.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vikas.model.student.*;
import com.vikas.service.RemoveStudent.RemoveStudentService;
import com.vikas.service.ShowStudents.ShowStudentService;
import com.vikas.ui.common.mainUI;
import com.vikas.utils.enums.NotificationEnums;

@SpringView(name=RemoveStudentLayoutFactory.NAME, ui=mainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout  implements View, Button.ClickListener{

 public static final String NAME ="removestudent";	
	@Autowired
	private ShowStudentService showStudentService;
	
	@Autowired
	private RemoveStudentService removeStudentService;
	
	Grid studentsTable;
	List<student> studentsList;
	Button removeButton;
	
	private void addLayout() {
		removeAllComponents();
		setMargin(true);
		removeButton = new Button(NotificationEnums.NOTIFICATION_DELETE_BUTTON.getString());
		removeButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		
		BeanItemContainer<student> beanItemContainer = new BeanItemContainer<student>(student.class, studentsList);
		
		studentsTable = new Grid(beanItemContainer);
		studentsTable.setColumnOrder("firstName","lastName","age","email","gender");
		studentsTable.removeColumn("ID");
		studentsTable.removeColumn("university");
		studentsTable.setSelectionMode(SelectionMode.MULTI);
		studentsTable.setImmediate(true);
		
		removeButton.addClickListener(this);
		
		addComponent(studentsTable);
		addComponent(removeButton);
	}
	
	private void loadStudents() {
		studentsList = showStudentService.getAllStudents();
	}
	public void buttonClick(ClickEvent event) {
		MultiSelectionModel multiSelectionModel = (MultiSelectionModel)studentsTable.getSelectionModel();
		for(Object selecteditems : multiSelectionModel.getSelectedRows()) {
			student stuRows = (student)selecteditems;
			studentsTable.getContainerDataSource().removeItem(stuRows);
			removeStudentService.removeStudent(stuRows);
			}
		Notification.show(NotificationEnums.NOTIFICATION_DELETEDATA_TITLE.getString(),
				NotificationEnums.NOTIFICATION_DELETEDATA_DESC.getString(), Type.WARNING_MESSAGE);
	}

	public void enter(ViewChangeEvent event) {
              loadStudents();
              addLayout();
	}
		
}
