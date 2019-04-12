package pro.bank.web.vo.response;

public class ResponseJsonData {

	private boolean result;
	private String resultDesc;
	private Object data;
	public ResponseJsonData(){
		this.result = true;
		this.resultDesc = "操作成功";
	}
	
	public ResponseJsonData(boolean result,String resultDesc){
		this.result = result;
		this.resultDesc = resultDesc;
	}
	
	public ResponseJsonData(boolean result,Object data){
		this.result = result;
		this.data = data;
	}

	public ResponseJsonData(boolean result,String resultDesc,Object data){
		this.result = result;
		this.data = data;
		this.resultDesc = resultDesc;
	}

	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
