package com.vikas.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vikas.model.University.University;
import com.vikas.model.student.student;
import com.vikas.service.AddStudent.AddStudentService;
import com.vikas.service.ShowUniversities.ShowUniversitiesService;
import com.vikas.utils.enums.NotificationEnums;
import com.vikas.utils.enums.StudentModelEnums;

@org.springframework.stereotype.Component
public class AddStudentTabLayoutFactory {
	

	private class AddStudentTabLayout extends VerticalLayout implements Button.ClickListener {

		private StudentSavedListener studentSavedListener;
		public AddStudentTabLayout(StudentSavedListener studentSavedListener) {
			this.studentSavedListener = studentSavedListener;
		}
		
		private TextField FirstName;
	    private TextField LastName;
		private TextField Age;
		private TextField Email;
		private ComboBox Gender;
		private ComboBox UnivListBox;
		private Button saveButton;
		private Button clearButton;

		private student stu;
		private BeanFieldGroup<student> fieldGroup;

		public AddStudentTabLayout init() {
			stu = new student();
			fieldGroup = new BeanFieldGroup<student>(student.class);
			
	
			FirstName = new TextField(StudentModelEnums.FIRSTNAME.getString());
			LastName = new TextField(StudentModelEnums.LASTNAME.getString());
			Age = new TextField(StudentModelEnums.AGE.getString());
			Email = new TextField(StudentModelEnums.EMAIL.getString());
			Gender = new ComboBox(StudentModelEnums.GENDER.getString());
			UnivListBox = new ComboBox(StudentModelEnums.UNIV_LIST.getString());
			
			FirstName.setNullRepresentation("");
			LastName.setNullRepresentation("");
			Age.setNullRepresentation("");
			Email.setNullRepresentation("");
		
			Gender.addItem(StudentModelEnums.MALE.getString());
			Gender.addItem(StudentModelEnums.FEMALE.getString());
			Gender.addItem(StudentModelEnums.TRANSGENDER.getString());
			Gender.addItem(StudentModelEnums.DONOTWANTTODISCLOSE.getString());

			saveButton = new Button(StudentModelEnums.SAVEBUTTON.getString());
			clearButton = new Button(StudentModelEnums.CLEARBUTTON.getString());
			saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
			clearButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            
			//add click listeners
			saveButton.addClickListener(this);
			clearButton.addClickListener(this);
			return this;
		}

		public AddStudentTabLayout bind() {
			fieldGroup.bind(FirstName, "FirstName");
			fieldGroup.bind(LastName, "LastName");
			fieldGroup.bind(Age, "Age");
			fieldGroup.bind(Email, "Email");
			fieldGroup.bind(Gender, "Gender");
			fieldGroup.bind(UnivListBox, "university");
			fieldGroup.setItemDataSource(stu);
			return this;
		}

		public Component layout() {
			setMargin(true);
			GridLayout gridLayout = new GridLayout(2, 5);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);
			UnivListBox.setWidth("100%");

			gridLayout.addComponent(FirstName, 0, 0);
			gridLayout.addComponent(LastName, 1, 0);
			gridLayout.addComponent(Age, 0, 1);
			gridLayout.addComponent(Email, 1, 1);
			gridLayout.addComponent(Gender, 0, 2);
            gridLayout.addComponent(UnivListBox,0,3,1,3);
			
			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 4);
			return gridLayout;
		}

		public void buttonClick(ClickEvent event) {
			if(event.getSource() == this.saveButton) {
				if(isUnivListAvailable()) {
					Notification.show(NotificationEnums.NOTIFICATION_UNIV_FOUND_TITLE.getString(),
							NotificationEnums.NOTIFICATION_UNIV_FOUND_DESCRIPTION.getString(), 
							Type.ERROR_MESSAGE);
				}
				else if(isUnivListAvailable()==false) {
				save();
				}
				}
			else {
				clearField();
			}
			clearField();
		}

		private void clearField() {
		FirstName.setValue(null);
		LastName.setValue(null);
		Age.setValue(null);
		Email.setValue(null);
			
		}
		private boolean isUnivListAvailable() {
			return showUniversitiesService.getAllUnivNames().size() ==0;
		}

		private void save() {
			try {
				fieldGroup.commit();
				addStudentService.saveStudent(stu);
				studentSavedListener.studentSaved();
				Notification.show(NotificationEnums.NOTIFICATION_SUCCESS.getString(), Type.WARNING_MESSAGE);
			}
			catch(CommitException e) {
				Notification.show(NotificationEnums.NOTIFICATION_ERROR_TITLE.getString(),
						NotificationEnums.NOTIFICATION_ERROR_DESCRIPTION.getString(), 
						Type.ERROR_MESSAGE);
			}
			
		}

		public AddStudentTabLayout load() {
			List<University> UniversityLists = showUniversitiesService.getAllUnivNames();
			UnivListBox.addItems(UniversityLists);
			return this;
		}

	}
	@Autowired
	private ShowUniversitiesService showUniversitiesService;
	@Autowired
	private AddStudentService addStudentService;

	public Component createComponent(StudentSavedListener studentSavedListener) {
		return new AddStudentTabLayout(studentSavedListener).init().load().bind().layout();
	}
}
