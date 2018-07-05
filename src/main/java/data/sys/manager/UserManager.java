package data.sys.manager;

import java.util.List;

import data.common.manager.BaseDao;
import data.sys.entity.User;


/**
 * 
 * @Description: 用户管理服务类
 * @author rqf
 * @date 2016年8月19日 上午10:09:23 @v1.0
 */
public interface UserManager extends BaseDao{

    /**
     * 根据用户名获取用户信息
     * 
     * @param userCode
     *            用户名
     * @return 将查询到的用户信息返回
     */
    public User getUserByCode(String userCode);

}
