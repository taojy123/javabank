package pro.bank.entity;

import java.util.Date;

import pro.bank.constant.YesNo;

/**
* 银行
* 
*/
public class Bank{
	/**主键ID*/
	private Long id;
	
	
	/**银行名称*/
	private String name;
	
	
	/**上级ID*/
	private Long parentId;
	
	
	/**银行等级*/
	private Integer level;
	
	
	/**创建时间*/
	private Date createTime;
	
	
	
	
	
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
	
	
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	
	
	public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
	
	
	public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
	
	
	public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
	
}
