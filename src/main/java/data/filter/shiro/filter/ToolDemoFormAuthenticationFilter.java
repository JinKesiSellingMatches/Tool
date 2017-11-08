package data.filter.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import data.Enum.LoginErrorEnum;




public class ToolDemoFormAuthenticationFilter extends FormAuthenticationFilter {
	/**
	 * shiro认证失败后调用该方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {// 不是ajax请求
			setFailureAttribute(httpServletRequest, e);
			return true;
		}
		try {

			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();
			
			//shiro在认证异常后，会将异常信息类的全限定名放在e.getClass().getName()中。
			String message = e.getClass().getName();
			if (IncorrectCredentialsException.class.getName().equals(message)) {
				out.print("{result:false,data:'用户名或密码不正确！'}");
			} else if (UnknownAccountException.class.getName().equals(message)) {
				if (e.getMessage()!=null&&e.getMessage().equals(LoginErrorEnum.ERRORDELETED.value())) {
					out.print("{result:false,data:'"+LoginErrorEnum.ERRORDELETED.description()+"'}");
				}else{
					out.print("{result:false,data:'用户名或密码不正确！'}");
				}
			} else {
				out.print("{result:false,data:'未知错误'}");
			}
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;

	}

	/**
	 * shiro认证成功后调用该方法
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		try {
			if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {// 不是ajax请求
				issueSuccessRedirect(request, response);

			} else {
				httpServletResponse.setCharacterEncoding("UTF-8");
				PrintWriter out = httpServletResponse.getWriter();
				out.print("{result:true,data:'登入成功'}");
				out.flush();
				out.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
}
