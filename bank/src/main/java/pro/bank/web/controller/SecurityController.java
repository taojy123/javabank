package pro.bank.web.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pro.bank.utils.CaptchaUtil;
import pro.bank.web.vo.request.ReqLoginVO;
import pro.bank.web.vo.response.ResponseJsonData;

@Controller
public class SecurityController {


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String detail(Model model) {
		return "redirect:/count";
	}
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model) {
		return "redirect:/admin";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseJsonData login(@Valid ReqLoginVO loginData,Errors errors,HttpServletRequest request) {
		if(errors.hasErrors())//spring valid 取第一个错误提示。
			return new ResponseJsonData(false,errors.getAllErrors().get(0).getDefaultMessage());
		HttpSession session = request.getSession();
		 Object attribute = session.getAttribute("SE_KEY_MM_CODE_LOGIN");
		 if(attribute == null)
			 return new ResponseJsonData(false, "验证码已过期，请点击验证码刷新，再进行登录");
		String code = attribute.toString();
		if (!code.equalsIgnoreCase(loginData.getCaptcha())) {
			return new ResponseJsonData(false,"验证码错误,请重新输入");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(loginData.getUserName(), loginData.getPassword());
		SecurityUtils.getSubject().login(token);
		return new ResponseJsonData(true, "登陆成功");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		return "signup";
	}
	@RequestMapping(value = "captcha_login")
	public void captcha_login(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0L);
		try {
			HttpSession session = request.getSession();
			CaptchaUtil tool = new CaptchaUtil();
			StringBuffer code = new StringBuffer();
			java.awt.image.BufferedImage image = tool.genRandomCodeImage(code);
			session.removeAttribute("SE_KEY_MM_CODE_LOGIN");
			session.setAttribute("SE_KEY_MM_CODE_LOGIN", code.toString());
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
