package data.filter.shiro.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;

import com.alibaba.fastjson.JSON;

public class OAuthAdviceFilter extends AdviceFilter{
    
    @Override
    protected boolean preHandle(ServletRequest request,ServletResponse response) throws IOException{
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //OAuthRequest oAuthRequest = new OAuthRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("errorMsg", e.getMessage());
            result.put("statusCode", "1002");
            System.out.println(e.getMessage());
            response.setContentType("text;charset=UTF-8");
            response.getWriter().print(JSON.toJSON(result));
            return false;
        }
        return true;
       
    }
}
