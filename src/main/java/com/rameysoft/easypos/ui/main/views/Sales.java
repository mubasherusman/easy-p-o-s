package com.rameysoft.easypos.ui.main.views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.rameysoft.easypos.ui.main.MainPosUI;
import com.rameysoft.easypos.utils.eventbus.AppEventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
@UIScope
@SpringView(name = Sales.VIEW_NAME,ui=MainPosUI.class)
public class Sales extends Panel implements View{
	public static final String VIEW_NAME = "sales";
	
	@Autowired
	private AppEventBus appEventBus;
	
	@PostConstruct
    void init() {
		appEventBus.register(this);
	}
	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println(event.toString());
	}

}
