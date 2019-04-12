package pro.bank.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import pro.bank.constant.YesNo;
import pro.bank.entity.Bank;
import pro.bank.service.BankService;
import pro.bank.web.controller.base.BaseController;
import pro.bank.web.vo.response.*;
import pro.bank.web.vo.request.*;
import pro.bank.web.editor.DateEditor;
import pro.bank.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* 银行Controller
*
*/
@Controller
@RequestMapping(value="/bank")
public class BankController extends BaseController{
	@Autowired
	private BankService bankService;
	@Autowired
	private ImportExcelUtil ImportExcelUtil;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String index(Model model,@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="20") Integer size,@ModelAttribute("searchVal") String searchVal,@RequestParam(required=false) Long parentId){
		PageHelper.startPage(page, size,"id asc");
		List<Bank> bankList = bankService.findAll(searchVal,parentId,null); 
		model.addAttribute("bankPage", new PageInfo<Bank>(bankList));
		model.addAttribute("parentId", parentId);
		return parentId==null?"bank/list":"bank/view";
	}
	
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create(Model model,@RequestParam(required=false) Long parentId){
		model.addAttribute("parentId", parentId);
		return "bank/create";
	}
	
	@RequestMapping(value="import",method=RequestMethod.POST)
	public @ResponseBody ResponseJsonData imports(@RequestParam(value = "uploadFile") MultipartFile file) throws IOException, InvalidFormatException{
		ImportExcelUtil.importExcel_BANK(file);
		return new ResponseJsonData(true,"导入成功");
	}
	@RequestMapping(value="create",method=RequestMethod.POST)
	public @ResponseBody ResponseJsonData create(Bank bank) throws IOException{
	    bank.setCreateTime(new Date());
	    
	    bank.setLevel(bank.getParentId() == null?1:bankService.findById(bank.getParentId()).getLevel()+1);
	    bankService.create(bank);
		return new ResponseJsonData(true,"添加成功");
	}
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	public @ResponseBody ResponseJsonData delete(@PathVariable Long id){
		bankService.delete(id);
		return new ResponseJsonData(true, "删除成功");	
	}
	@RequestMapping(value="update/{id}",method=RequestMethod.GET)
	public String update(Model model,@PathVariable Long id){
		Bank bank = bankService.findById(id);
		model.addAttribute("bank", bank);
		return "bank/update";	
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public @ResponseBody ResponseJsonData update(Bank bank){
		bankService.update(bank);
		return new ResponseJsonData(true, "修改成功");	
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,new DateEditor());
	}
}
