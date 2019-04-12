package pro.bank.web.vo.response;

import java.util.Date;

public class TzData {
	private Integer sorts;
	private String number;
	private Date startTime;
	private Date endTime;
	private Integer endDays;
	private Integer period;
	private String rate;
	private Long buyMoney;
	
	
	public TzData(Integer sorts,String number,Date startTime,Date endTime,Integer endDays,Integer period,String rate,Long buyMoney){
		this.sorts = sorts;
		this.number = number;
		this.startTime = startTime;
		this.endTime = endTime;
		this.endDays = endDays;
		this.period = period;
		this.rate = rate;
		this.buyMoney = buyMoney;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getEndDays() {
		return endDays;
	}

	public void setEndDays(Integer endDays) {
		this.endDays = endDays;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Long getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(Long buyMoney) {
		this.buyMoney = buyMoney;
	}
	
	
	
	
}
