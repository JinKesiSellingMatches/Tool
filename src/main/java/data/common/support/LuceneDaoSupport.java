package data.common.support;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class LuceneDaoSupport extends JdbcDaoSupport {
	
	 @Autowired
     @Qualifier("sessionFactory")  
     SessionFactory sessionFactory;  
  
     protected HibernateDaoSupport hbSupport;  
  
     /** 
      * 自动注入数据源并指定为"dataSource" <br> 
      * 子类可重写该方法通过 @Qualifier("dataSource") 注解指定其它数据源 </br> 
      * */  
     @Autowired()  
     public void initJdbc( @Qualifier( "dataSource") DataSource dataSource) {  
            super.setDataSource(dataSource);  
     }  
  
     /** 
      * 自动注入会话工厂并指定为"sessionFactory" <br> 
      * 子类可重写该方法通过 @Qualifier("sessionFactory") 注解指定其它会话工厂 </br> 
      * */  
     @Autowired()  
     public void initHbernate(  
                 @Qualifier( "sessionFactory") SessionFactory sessionFactory) {  
            hbSupport = new HibernateDaoSupport() {  
           };  
            hbSupport.setSessionFactory(sessionFactory);  
     }  
  
     public HibernateTemplate getHbernateTemplate() {  
            return hbSupport.getHibernateTemplate();  
     }  
     
     public Session getCurrentSession() {
         return sessionFactory.getCurrentSession();
     }

}
