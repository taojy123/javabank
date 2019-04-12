package pro.bank.handler;

import java.io.IOException;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import pro.bank.exception.BusinessException;
import pro.bank.exception.ParameterException;
import pro.bank.exception.SystemError2HtmlException;
import pro.bank.web.vo.response.ResponseJsonData;

/**
 * spring异常拦截器
 * 
 * @author user
 *
 */
@ControllerAdvice
public class WebExceptionHandler {

	/**
	 *  安全身份认证异常拦截
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ AuthenticationException.class})
	public @ResponseBody ResponseJsonData authentication(AuthenticationException e) {
		return new ResponseJsonData(false, "账户/密码错误。");
	}
	
	
	@ExceptionHandler({ SystemError2HtmlException.class})
	public ModelAndView authentication(SystemError2HtmlException e) {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("redirect:/error?msg="+e.getMessage());
		return mnv;
	}

	/**
	 * 业务异常处理
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ ParameterException.class, BusinessException.class })
	public @ResponseBody ResponseJsonData Exception2Json(Exception e) {
		return new ResponseJsonData(false, e.getMessage());
	}

	@ExceptionHandler(FileUploadException.class)
	public ResponseJsonData uploadFile(FileUploadException e) {
		return new ResponseJsonData(false, "文件上传错误,请稍后再试。");
	}

	/**
	 * IO异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IOException.class)
	public @ResponseBody ResponseJsonData io(IOException e) {
		System.err.println(e.getMessage() + e.getLocalizedMessage());
		return new ResponseJsonData(false, "文件上传/下载 失败.");
	}

	/**
	 * 文件上传异常 一般出现未传递file
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MultipartException.class)
	public @ResponseBody ResponseJsonData file(MultipartException e) {
		return new ResponseJsonData(false, "请检查文件上传.");
	}
	

}
