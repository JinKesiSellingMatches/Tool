package data.sys.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import data.common.manager.impl.BaseDaoImpl;
import data.sys.entity.User;
import data.sys.manager.UserManager;


@Service(value = "userManager")
public class UserManagerImpl extends BaseDaoImpl implements UserManager {
	
    /**
     * 根据用户名获取用户信息
     * 
     * @param userCode
     *            用户名
     * @return 将查询到的用户信息返回
     */
    @Override
    public User getUserByCode(String userCode) {

        Map paramMap = new HashMap();
        paramMap.put("userCode", userCode);
        return (User) this.get("from User where userCode = :userCode", paramMap);
    }

    
}