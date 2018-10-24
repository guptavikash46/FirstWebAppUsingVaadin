package com.vikas.ui.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vikas.navigator.UniversNavigator;
import com.vikas.ui.students.RemoveStudentLayoutFactory;
import com.vikas.ui.students.StudentLayoutFactory;

@SpringUI(path=mainUI.path)
@Theme("valo")
@Title("University Home")
public class mainUI extends UI{
	
	public static final String path = "/ui";
    
	private Panel changeTab = new Panel();
	
	@Autowired
	UniversLogoLayoutFactory universLogoLayoutFactory;
	
	@Autowired
	UniversMenuLayoutFactory universMenuLayoutFactory; 
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	SpringViewProvider viewProvider;
	
	
	@Override
	protected void init(VaadinRequest request) {
		
		changeTab.setHeight("100%");
	VerticalLayout rootLayout = new  VerticalLayout();
	  rootLayout.setSizeFull();
	  rootLayout.setMargin(true);
	  
	  Panel contentPanel = new Panel();
	  contentPanel.setHeight("100%");
	  contentPanel.setWidth("75%");
	  
	  Panel logoPanel = new Panel();
	  logoPanel.setSizeUndefined();
	  logoPanel.setWidth("75%");
	  
	  HorizontalLayout uiLayout = new HorizontalLayout();
	  uiLayout.setMargin(true);
	  uiLayout.setSizeFull();
	  
	  Component menu = universMenuLayoutFactory.createComponent();
	  Component logo = universLogoLayoutFactory.createComponent();
	  uiLayout.addComponent(menu);
	  uiLayout.addComponent(changeTab);
	  
	  uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
	  uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
	  
	  uiLayout.setExpandRatio(menu, 1);
	  uiLayout.setExpandRatio(changeTab, 2);
	  
	  logoPanel.setContent(logo);
	  contentPanel.setContent(uiLayout);
	  
	  rootLayout.addComponent(logoPanel);
	  rootLayout.addComponent(contentPanel);
	  rootLayout.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
	  rootLayout.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
	  rootLayout.setExpandRatio(contentPanel, 1);
	  
	initNavigator();
		setContent(rootLayout); 
	}

	private void initNavigator() {
	UniversNavigator navig = new UniversNavigator(this, changeTab);
	appContext.getAutowireCapableBeanFactory().autowireBean(navig);
	navig.addProvider(viewProvider);
	navig.navigateTo(StudentLayoutFactory.NAME);
}
}
