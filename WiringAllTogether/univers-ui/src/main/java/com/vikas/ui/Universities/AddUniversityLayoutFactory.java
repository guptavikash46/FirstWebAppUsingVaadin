package com.vikas.ui.Universities;


import org.aspectj.weaver.patterns.BindingAnnotationFieldTypePattern;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vikas.model.University.University;
import com.vikas.service.AddUniversity.AddUniversityService;
import com.vikas.utils.enums.NotificationEnums;
import com.vikas.utils.enums.UniversityEnums;

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory {

	private TextField UniversityName;
	private TextField UniversityCountry;
	private TextField UniversityCity;
	private Button saveButton;
	private BeanFieldGroup<University> beanFieldGroup;
	private University university;
	
	@Autowired
	private AddUniversityService addUniversityService;
	
	private class AddLayoutUniversityTab extends VerticalLayout implements Button.ClickListener{
		private UniversitySavedListener univ;
		public AddLayoutUniversityTab(UniversitySavedListener uni) {
			this.univ = uni;
		}
	public AddLayoutUniversityTab init() {
		university = new University();
		UniversityName = new TextField(UniversityEnums.UNIVERSITY_NAME.getString());
		UniversityCity = new TextField(UniversityEnums.UNIVERSITY_CITY.getString());
		UniversityCountry = new TextField(UniversityEnums.UNIVERSITY_COUNTRY.getString());
		saveButton = new Button(UniversityEnums.UNIV_SAVE_BUTTON.getString(), this);
		saveButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		
		
		UniversityName.setNullRepresentation("");
		UniversityCity.setNullRepresentation("");
		UniversityCountry.setNullRepresentation("");
		return this;
	}
	
	
	public AddLayoutUniversityTab bind() {
		beanFieldGroup = new BeanFieldGroup<University>(University.class);
		beanFieldGroup.bind(UniversityName, "UniversityName");
		beanFieldGroup.bind(UniversityCity, "UniversityCity");
		beanFieldGroup.bind(UniversityCountry, "UniversityCountry");
		beanFieldGroup.setItemDataSource(university);
		return this;
	}
	public Component layout() {
		VerticalLayout vrComponents = new VerticalLayout();
		vrComponents.setSpacing(true);
		vrComponents.setHeightUndefined();
		vrComponents.addComponent(UniversityName);
		vrComponents.addComponent(UniversityCity);
		vrComponents.addComponent(UniversityCountry);
		vrComponents.addComponent(saveButton);
		
		return vrComponents;
	}
		
		public void buttonClick(ClickEvent event) {
			try {
				beanFieldGroup.commit();
			}
			catch(CommitException e) {
				Notification.show(NotificationEnums.NOTIFICATION_ADD_UNIV_FAIL_TITLE.getString(), 
						NotificationEnums.NOTIFICATION_ADD_UNIV_FAIL_DESC.getString(), Type.ERROR_MESSAGE);
				return;
			}
			clearFields();
			addUniversityService.saveUniversity(university);
			Notification.show(
					NotificationEnums.NOTIFICATION_ADD_UNIV_SUCCESS_MSG.getString(), Type.WARNING_MESSAGE);
			univ.UniversitySaved();
		}

		private void clearFields() {
            UniversityName.setValue(null);	
            UniversityCity.setValue(null);
            UniversityCountry.setValue(null);
		}
		
	}
	
	
	public com.vaadin.ui.Component createComponent(UniversitySavedListener univ){
		return new AddLayoutUniversityTab(univ).init().bind().layout();
	}
}
