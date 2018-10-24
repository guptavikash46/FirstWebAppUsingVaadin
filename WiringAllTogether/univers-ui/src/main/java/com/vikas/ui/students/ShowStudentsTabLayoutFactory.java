package com.vikas.ui.students;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vikas.model.student.student;
import com.vikas.service.ShowStudents.ShowStudentService;

@org.springframework.stereotype.Component
public class ShowStudentsTabLayoutFactory {

	private List<student> studentList;
    private BeanItemContainer<student> container;
  
	private class ShowStudentsTabLayout extends VerticalLayout{
		
		private Grid studentsTable;
		public ShowStudentsTabLayout init() {
			setMargin(true);
			container = new BeanItemContainer<student>(student.class, studentList);
			
			studentsTable = new Grid(container);
		    studentsTable.setColumnOrder("firstName","lastName","age","email","gender");
		    studentsTable.removeColumn("ID");
		    studentsTable.removeColumn("university");
			studentsTable.setImmediate(true);
			
			return this;
		}
		public ShowStudentsTabLayout load() {
		 studentList= showStudentService.getAllStudents();
			return this;
		}
		public ShowStudentsTabLayout layout() {
			addComponent(studentsTable);
			return this;
		}
	}
      public void refreshTable() {
		 container.removeAllItems();
		 container.addAll(showStudentService.getAllStudents());
		}
	@Autowired
		private ShowStudentService showStudentService;
	
	public Component createComponent() {
		return new ShowStudentsTabLayout().load().init().layout();
	}

	
}
