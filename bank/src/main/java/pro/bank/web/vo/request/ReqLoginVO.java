package pro.bank.web.vo.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ReqLoginVO {

	@NotEmpty(message="请填写账号。")
	@Size(min=1, max=16, message="账号长度有误，规范：1~16位")
	private String userName;
	@NotEmpty(message="请填写密码")
	@Size(min=6, max=18, message="密码长度有误。规范：6~18位")
	private String password;
	@NotEmpty(message="请填写图形验证码")
	@Size(min=4, max=4, message="图形验证码有误。规范：4位")
	private String captcha;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
}
