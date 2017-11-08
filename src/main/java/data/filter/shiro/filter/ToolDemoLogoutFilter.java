package data.filter.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ToolDemoLogoutFilter extends LogoutFilter {
	private static final Logger log = LoggerFactory.getLogger(ToolDemoLogoutFilter.class);
	
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		
		return false;
	}
}
