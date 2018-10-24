package com.vikas.ui.Universities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vikas.model.University.University;
import com.vikas.service.ShowUniversities.ShowUniversitiesService;

@org.springframework.stereotype.Component
public class ShowAllUniversitiesTabLayoutFactory {

	@Autowired
	private ShowUniversitiesService showUniversitiesService;
	private Grid gridUniversityList;
	private List<University> univList;
	private BeanItemContainer<University> beanItemContainer;
	private class ShowAllUniversitiesTabLayout extends VerticalLayout{
		
	public ShowAllUniversitiesTabLayout init() {
			setMargin(true);
			setWidthUndefined();
			beanItemContainer = new BeanItemContainer<University>(University.class, univList);
			gridUniversityList = new Grid(beanItemContainer);
			gridUniversityList.setColumnOrder("universityName","universityCity","universityCountry");
			gridUniversityList.removeColumn("ID");
			gridUniversityList.setImmediate(true);
			return this;
		}
		
		public ShowAllUniversitiesTabLayout bind() {
			univList = showUniversitiesService.getAllUnivNames();
			return this;
		}
		public ShowAllUniversitiesTabLayout layout(){
			 addComponent(gridUniversityList);
			 return this;
		}
		
	}
	public void updateUnivList() {
		beanItemContainer.removeAllItems();
		beanItemContainer.addAll(showUniversitiesService.getAllUnivNames());
	}
	
	public Component createComponent() {
		return new ShowAllUniversitiesTabLayout().bind().init().layout();
	}
}
