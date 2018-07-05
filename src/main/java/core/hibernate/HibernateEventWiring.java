package core.hibernate;


import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateEventWiring {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private OperListener operListener;
	
	@PostConstruct
	public void registerListeners() {
	    EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
	            EventListenerRegistry.class);
	    registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(operListener);
	    registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(operListener);
	    registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(operListener);
	}

}
