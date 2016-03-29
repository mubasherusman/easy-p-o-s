package com.rameysoft.easypos.ui.main.views;

import com.google.common.eventbus.Subscribe;
import com.rameysoft.easypos.backend.model.User;
import com.rameysoft.easypos.ui.main.MainPosUI;
import com.rameysoft.easypos.ui.main.views.designs.MainDesign;
import com.rameysoft.easypos.utils.eventbus.AppEvent;
import com.rameysoft.easypos.utils.eventbus.AppEvent.PostViewChangeEvent;
import com.rameysoft.easypos.utils.eventbus.AppEvent.ProfileUpdatedEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;


@SuppressWarnings("serial")
public class MainView extends MainDesign {
	
	 private MenuItem settingsItem;
	    public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	    private static final String STYLE_VISIBLE = "valo-menu-visible";
	public MainView(){
		super();		
		buildUserMenu();
		updateToggleButton();
	}
	
	
	private User getCurrentUser() {
        return (User) VaadinSession.getCurrent().getAttribute(
                User.class.getName());
    }
	
	private Component buildUserMenu() {
        settingsItem = settings.addItem("", new ThemeResource(
                "img/profile-pic-300px.jpg"), null);
        updateUserName(null);
        settingsItem.addItem("Edit Profile", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
               // ProfilePreferencesWindow.open(user, false);
            }
        });
        settingsItem.addItem("Preferences", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                //ProfilePreferencesWindow.open(user, true);
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                MainPosUI.getEventBus().post(new AppEvent.UserLoggedOutEvent());
            }
        });
        return settings;
    }
	
	private void updateToggleButton(){
		valoMenuToggleButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                if (pos_menu.getStyleName().contains(STYLE_VISIBLE)) {
                	pos_menu.removeStyleName(STYLE_VISIBLE);
                } else {
                	pos_menu.addStyleName(STYLE_VISIBLE);
                }
            }
        });
	}
	
	@Subscribe
    public void updateUserName(final ProfileUpdatedEvent event) {
        User user = getCurrentUser();
        settingsItem.setText(user.getFirstName() + " " + user.getLastName());
    }
	
	 @Subscribe
	    public void postViewChange(final PostViewChangeEvent event) {
	        // After a successful view change the menu can be hidden in mobile view.
		 	pos_menu.removeStyleName(STYLE_VISIBLE);
	    }

	 public ComponentContainer getViewContainer(){
		 return viewContainer;
	 }
}
