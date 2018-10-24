package com.vikas.ui.Universities;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vikas.ui.common.mainUI;

@SpringView(name=UniversityLayoutFactory.NAME, ui=mainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, UniversitySavedListener {

	public static final String NAME = "operations";
	
	@Autowired
	private AddUniversityLayoutFactory addUniversityLayoutFactory;
	
	@Autowired
	private ShowAllUniversitiesTabLayoutFactory showAllUniversitiesTabLayoutFactory;
	
	@Autowired
	private StatisticsUniversityLayoutFactory statisticsUniversityLayoutFactory;
	
     private TabSheet tabSheet;
     
	private void addLayout() {
		setMargin(true);
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addUniversities = addUniversityLayoutFactory.createComponent(this);
		Component showUniversities = showAllUniversitiesTabLayoutFactory.createComponent();
		Component universityStats = statisticsUniversityLayoutFactory.createComponent();
		
		tabSheet.addTab(addUniversities,"ADD UNIVERSITIES");
		tabSheet.addTab(showUniversities,"SHOW ALL UNIVERSITIES");
		tabSheet.addTab(universityStats,"UNIVERSITY STATS");
		
		addComponent(tabSheet);
	}
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}
	public void UniversitySaved() {
        		showAllUniversitiesTabLayoutFactory.updateUnivList();
	}
	
	
}
