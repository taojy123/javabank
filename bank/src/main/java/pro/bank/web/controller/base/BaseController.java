package pro.bank.web.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {
	
	/**
	 * 去空格
	 * @param searchVal
	 * @return
	 */
	@ModelAttribute("searchVal")
	public String getSearchVal(String searchVal){
		if(StringUtils.isEmpty(searchVal))
			return null;
		return searchVal.trim();
	}
	
}
