package com.vikas.ui.Universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vikas.model.University.University;
import com.vikas.service.ShowUnivStats.UniversityStatsService;
import com.vikas.service.ShowUniversities.ShowUniversitiesService;
import com.vikas.ui.common.UIComponentBuilder;

@org.springframework.stereotype.Component
public class StatisticsUniversityLayoutFactory implements UIComponentBuilder{

	@Autowired
	private ShowUniversitiesService showUniversitiesService;
	
	@Autowired
	private UniversityStatsService universityStatsService;
	
	private List<University> universitiesList;
	
	private class StatisticsUniversityLayoutTab extends VerticalLayout{

		public StatisticsUniversityLayoutTab load() {
			universitiesList = showUniversitiesService.getAllUnivNames();
			return this;
		}
	
		
		public StatisticsUniversityLayoutTab layout() {
		setMargin(true);
			
			for(University universities : universitiesList) {
				int numOfStudents = universityStatsService.getNumOfStudentsForUnivesity(universities.getID());
				Label showNumStudentsLabel = new Label("<p><b>In "+universities.getUniversityName()+" University</b>"+
				"  -  "+numOfStudents+" are Enrolled.</p>", ContentMode.HTML);
				addComponent(showNumStudentsLabel);
			}
			
			return this;
		}
		
	}
	
	public Component createComponent() {
		return new StatisticsUniversityLayoutTab().load().layout();
	}


}
