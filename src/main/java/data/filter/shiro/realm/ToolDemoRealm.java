package data.filter.shiro.realm;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


import core.utils.CiperUtil;
import data.Enum.LoginErrorEnum;
import data.sys.entity.User;
import data.sys.manager.UserManager;



public class ToolDemoRealm extends AuthorizingRealm {

	
	@Autowired
	public UserManager userManager;
	
	/**
	 * 授权方法
	 * @param AuthenticationToken token
	 * @return AuthenticationInfo
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return null;
	}

	
	/**
	 * 认证方法
	 * @param AuthenticationToken token
	 * @return AuthenticationInfo
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1:获取用户名
		String userCode = (String) token.getPrincipal();
		
		//2:查询用户是否存在
		User user = this.userManager.getUserByCode(userCode);
		
		//3:判断用户名是否存在，若不存在，则返回空，shiro框架自动抛出UnknownAccountException异常。
		if(user==null){
			return null;
		}
		if (user.getDeleted()==1) {
			throw new UnknownAccountException(LoginErrorEnum.ERRORDELETED.value());  
		}
		
		//4:将user设置到simpleAuthenticationInfo中
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getUserPassword(),this.getName());
		
		//5：将用户信息放入session中
		setSession("user", user);
		
		//获取用户输入密码，加密后放入session中
		char[] userPasswordChar =  (char[]) token.getCredentials();
		String userPassword = new String(userPasswordChar);
		String userPasswordEncrypt = CiperUtil.encrypt(CiperUtil.SECURITY_KEY,userPassword);
		setSession("userPasswordEncrypt", userPasswordEncrypt);
		
		return simpleAuthenticationInfo;
		//return null;
	}

	
	/**
	 * 设置session信息
	 * @param key
	 * @param value
	 */
	protected void setSession(Object key, Object value) {         
		 Subject currentUser = SecurityUtils.getSubject();         
		 if (null != currentUser) {             
			 Session session = currentUser.getSession();             
			 if (null != session) {                 
				 session.setAttribute(key, value);             
			 }         
		}     
	} 
}
