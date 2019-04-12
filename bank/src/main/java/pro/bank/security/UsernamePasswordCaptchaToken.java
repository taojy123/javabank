package pro.bank.security;

import org.apache.shiro.authc.UsernamePasswordToken;
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;


	public UsernamePasswordCaptchaToken() {
		super();

	}

	public UsernamePasswordCaptchaToken(String username, String password, boolean rememberMe) {
		super(username, password, rememberMe);
	}

}