package pro.bank.web.vo.response;

import java.util.Date;

public class Data {
	private String number;
	private String bankName;
	private Double result;
	
	public Data(String number,String bankName,Double result){
		this.number = number;
		this.bankName = bankName;
		this.result = result;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	
}
