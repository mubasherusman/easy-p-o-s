package com.rameysoft.easypos.utils.eventbus;

import com.rameysoft.easypos.backend.model.User;

/*
 * Event bus events used in Dashboard are listed here as inner classes.
 */
public abstract class AppEvent {

    public static final class UserAuthenticatedEvent {
        private final User user;
        
        public UserAuthenticatedEvent(final User user) {
            this.user = user;
        }
        

        public User getUser() {
            return user;
        }
    }

    public static class BrowserResizeEvent {

    }

    public static class UserLoggedOutEvent {

    }

    public static class NotificationsCountUpdatedEvent {
    }

    public static final class ReportsCountUpdatedEvent {
        private final int count;

        public ReportsCountUpdatedEvent(final int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

    }
    
    public static final class PostViewChangeEvent {
    }

    public static class CloseOpenWindowsEvent {
    }

    public static class ProfileUpdatedEvent {
    }

}
