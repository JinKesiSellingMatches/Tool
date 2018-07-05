package core.invoke;

import java.lang.reflect.Method;

import data.common.manager.BaseDao;
import data.common.manager.BaseGaiaDao;
import data.common.manager.impl.BaseDaoImpl;
import data.common.manager.impl.BaseGaiaDaoImpl;
import net.sf.cglib.proxy.Enhancer;   
import net.sf.cglib.proxy.MethodInterceptor;  
import net.sf.cglib.proxy.MethodProxy;  
  
//直接使用代理类的父类作为目标业务对象。  
public class BusinessCglibProxy1 implements MethodInterceptor {  
  
    private static BusinessCglibProxy1 interceptor = new BusinessCglibProxy1();  
    private BusinessCglibProxy1() {}  
      
    //创建代理对象   
    public static Object getCglibProxy(Class<?> clazz) {  
        //创建一个织入器   
        Enhancer enhancer = new Enhancer();  
        //设置父类   
        enhancer.setSuperclass(clazz);    
        //设置需要织入的逻辑  
        enhancer.setCallback(interceptor);    
        // 创建代理对象  //使用织入器创建子类  
        return enhancer.create();  
    }  
    //由于CGLIB可以不需要实现接口来实现动态代理,其原理是通过字节码生成类的一个子类来完成的,  
    //那就有可能出现需要动态代理对象不存在一个无参构造函数,那么CGLIB在生成子类并实例化时将会产生错误。  
    //创建带参数的代理对象   
   public static Object getCglibProxy(Class<?> clazz, Class<?>[] argClazz, Object[] args) {  
        //创建一个织入器   
        Enhancer enhancer = new Enhancer();  
        //设置父类   
        enhancer.setSuperclass(clazz);    
        // 设置需要织入的逻辑  
        enhancer.setCallback(interceptor);    
        // 创建带参数的代理对象  //使用织入器创建子类  
        return enhancer.create(argClazz, args);  
    }  
      
    /** 
     * obj：cglib动态生成的代理类实例，业务类的子类的实例 
     * method：业务类方法的引用 
     * args：调用参数数组 
     * proxy：代理类对业务类方法的代理引用，是业务类的方法的代理 
     */  
    @Override  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
          
        //obj 是cglib动态生成的代理实例，是BusinessImpl的子类的实例  
        System.out.println("Cglib动态代理类:" + obj.getClass().getName());  
  
        System.out.println("Before " + method.getName() + " ..");  
          
        //因为动态生成的业务类是子类或者是实现类，proxy.invokeSuper(Object obj, Object[] args)  
        //调用的是代理类obj的父类BusinessImpl的service方法。  
        //那么proxy.invoke(Object obj, Object[] args)方法是做什么的，javadoc上说这个方法   
        //可以用于相同类中的其他对象的方法执行，  
        //也就是说这个方法中的obj需要传入相同一个类的另一个对象，否则会进入无限递归循环。  
        //Object result = method.invoke(new BusinessImpl(), args);  
        Object result = proxy.invokeSuper(obj, args);  
          
        System.out.println("End "+ method.getName() + " ..");  
        return result;  
    }  
  
    public static void main(String[] args) {  
        
    	System.out.println(BusinessCglibProxy1.getCglibProxy(BaseDaoImpl.class));
    	
    	test(BaseGaiaDaoImpl.class);
          
//        //带参数的业务类  
//        BaseGaiaDao proxy1 = (BaseGaiaDao) BusinessCglibProxy1.getCglibProxy(BaseGaiaDao.class, new Class[] {String.class}, new Object[] {"jaesonchen"});  
//        proxy1.test();     
    }  
    
    public static <T> T test(Class<T> clazz){
    	return (T)BusinessCglibProxy1.getCglibProxy(clazz.getClass()); 
    }
} 
