package com.vikas.ui.common;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vikas.navigator.UniversNavigator;
import com.vikas.utils.enums.StringEnums;

@org.springframework.stereotype.Component
public class UniversMenuLayoutFactory implements UIComponentBuilder {

	
	
	private class MenuLayout extends VerticalLayout implements Property.ValueChangeListener{
		private Tree mainMenu;
		
		
		public MenuLayout init() {
			mainMenu = new Tree();
			mainMenu.addValueChangeListener(this);
			return this;
		}
		public MenuLayout layout() {
			
			mainMenu.addItem(StringEnums.MENU_STUDENT.getString());
			mainMenu.addItem(StringEnums.MENU_UNIVERSITY.getString());
			
			mainMenu.expandItem(StringEnums.MENU_STUDENT.getString());
			mainMenu.expandItem(StringEnums.MENU_UNIVERSITY.getString());
			
			mainMenu.addItem(StringEnums.MENU_ADDSTD.getString());
			mainMenu.addItem(StringEnums.MENU_RMVSTD.getString());
			mainMenu.setChildrenAllowed(StringEnums.MENU_ADDSTD.getString(), false);
			mainMenu.setChildrenAllowed(StringEnums.MENU_RMVSTD.getString(), false);
			mainMenu.setParent(StringEnums.MENU_ADDSTD.getString(), StringEnums.MENU_STUDENT.getString());
			mainMenu.setParent(StringEnums.MENU_RMVSTD.getString(), StringEnums.MENU_STUDENT.getString());
			
			mainMenu.addItem(StringEnums.MENU_OPT.getString());
			mainMenu.setChildrenAllowed(StringEnums.MENU_OPT.getString(), false);
			mainMenu.setParent(StringEnums.MENU_OPT.getString(), StringEnums.MENU_UNIVERSITY.getString());
			
			addComponent(mainMenu);
			
			return this;
		}
		public void valueChange(ValueChangeEvent event) {
			String selectedItem = (String)event.getProperty().getValue();
			
			if(selectedItem == null)return;
			
			String usefulItem = selectedItem.toLowerCase().replaceAll(" ", "");
			
			UniversNavigator.navigate(usefulItem);
		}
		
	}

	public Component createComponent() {
		
		return new MenuLayout().init().layout();
	}

}
