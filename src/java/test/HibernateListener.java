package test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import test.hibernate.HibernateUtil;

/**
 *
 * @author ejoseph
 */
public class HibernateListener implements ServletContextListener {  
  
    @Override
    public void contextInitialized(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory(); // Just call the static initializer of that class      
    }  
  
    @Override
    public void contextDestroyed(ServletContextEvent event) {  
        HibernateUtil.getSessionFactory().close(); // Free all resources  
    }  
} 


