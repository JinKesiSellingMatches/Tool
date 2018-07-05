package data.filter.shiro.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import core.utils.MD5Utils;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Object tokenCredentials = MD5Utils.getMD5Str((String.valueOf(token.getPassword())));
        Object accountCredentials = getCredentials(info);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
        if("dingding".equals(token.getHost())){
            Object dingding = String.valueOf(token.getPassword());
            return equals(dingding, accountCredentials);
        }
        return equals(tokenCredentials, accountCredentials);
    }

}