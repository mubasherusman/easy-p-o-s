package com.rameysoft.easypos.ui.main;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.Subscribe;
import com.rameysoft.easypos.backend.model.User;
import com.rameysoft.easypos.ui.main.views.LoginView;
import com.rameysoft.easypos.ui.main.views.MainView;
import com.rameysoft.easypos.ui.main.views.Sales;
import com.rameysoft.easypos.utils.eventbus.AppEvent.UserAuthenticatedEvent;
import com.rameysoft.easypos.utils.eventbus.AppEvent.UserLoggedOutEvent;
import com.rameysoft.easypos.utils.eventbus.AppEventBus;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("dashboard")
@Title("Easy POS")
//@Widgetset("com.rameysoft.easy_pos.MyAppWidgetset") right now we don't need this.
@SpringUI
public class MainPosUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7665055798622399702L;
	
	// we can use either constructor autowiring or field autowiring

    @Autowired
    private LoginView loginView;
    @Autowired
    private AppEventBus appEventBus;
	@Autowired
	private SpringViewProvider viewProvider;
    
    private MainView mainView;

    @Override
    protected void init(VaadinRequest request) {
    	setLocale(Locale.US);
    	appEventBus.register(this);
    	Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);
        
        updateContent();
    }
	
    /**
     * Updates the correct content for this UI based on the current user status.
     * If the user is logged in with appropriate privileges, main view is shown.
     * Otherwise login view is shown.
     */
    public void updateContent() {
        User user = (User) VaadinSession.getCurrent().getAttribute(
                User.class.getName());
        if (user != null && "admin".equals(user.getRole())) {
        	// Authenticated user
        	if(mainView == null){
        		mainView = new MainView();
        		appEventBus.register(mainView);
        	}
            setContent(mainView);
            removeStyleName("loginview");
            Navigator navigator = new Navigator(this, mainView.getViewContainer());
    	    navigator.addProvider(viewProvider);
    	    navigator.navigateTo(Sales.VIEW_NAME);
    	    
        } else {
           setContent(loginView);
           addStyleName("loginview");
        }
    }
    
    
    @Subscribe
    public void userLoginAuthenticated(final UserAuthenticatedEvent event) {
        User user = event.getUser();
        VaadinSession.getCurrent().setAttribute(User.class.getName(), user);
        updateContent();
    }

    @Subscribe
    public void userLoggedOut(final UserLoggedOutEvent event) {
        // When the user logs out, current VaadinSession gets closed and the
        // page gets reloaded on the login screen. Do notice the this doesn't
        // invalidate the current HttpSession.
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

	public static AppEventBus getEventBus() {
		return ((MainPosUI)UI.getCurrent()).appEventBus;
	}
	
}
