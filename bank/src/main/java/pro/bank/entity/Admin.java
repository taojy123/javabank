package pro.bank.entity;

import pro.bank.constant.YesNo;
import pro.bank.entity.base.BaseEntity;

/**
* 系统用户-登录用户
* 
*/
public class Admin extends BaseEntity{
	
	
	/**用户名*/
	private String userName;
	
	
	/**登录密码*/
	private String password;
	
	
	/**真实姓名*/
	private String realName;
	
	
	/**锁定状态|YES:锁定,NO:正常*/
	private YesNo isBlock;
	
	
	/**用户类型*/
	private String userType;
	
	
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
	
	
	public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
	
	
    public YesNo getIsBlock() {
        return isBlock;
    }
    public void setIsBlock(YesNo isBlock) {
        this.isBlock = isBlock;
    }
	
	
	public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
	
	
}
