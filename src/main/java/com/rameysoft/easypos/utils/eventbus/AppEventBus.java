package com.rameysoft.easypos.utils.eventbus;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.vaadin.spring.annotation.UIScope;

/**
 * A simple wrapper for Guava event bus. Defines static convenience methods for
 * relevant actions.
 */

@UIScope
@Component
public final class AppEventBus  {

    private final EventBus eventBus;

    
    public AppEventBus(){
    	eventBus = new EventBus();
    }
    public   void post(final Object event) {
    	eventBus.post(event);
    }

    public  void register(final Object object) {
    	eventBus.register(object);
    }

    public  void unregister(final Object object) {
    	eventBus.unregister(object);
    }

    
}
