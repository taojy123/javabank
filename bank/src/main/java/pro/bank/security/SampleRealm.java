package pro.bank.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pro.bank.entity.Admin;
import pro.bank.mapper.AdminMapper;

@Component
public class SampleRealm extends AuthorizingRealm {

	@Autowired
	private AdminMapper userMapper;

	public SampleRealm() {
		setName("SampleRealm");
		HashedCredentialsMatcher hashService = new HashedCredentialsMatcher("SHA-256"); // 默认算法SHA-512
		setCredentialsMatcher(hashService);
		setAuthorizationCachingEnabled(false);
	}


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Admin user = userMapper.findByUserName(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user,user.getPassword(),
					ByteSource.Util.bytes(user.getUserName()), getName());
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object user = (Admin)principals.fromRealm(getName()).iterator().next();
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			return info;
		} else {
			return null;
		}
	}

}
