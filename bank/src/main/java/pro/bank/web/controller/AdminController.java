package pro.bank.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pro.bank.annotation.CurrentUser;
import pro.bank.entity.Admin;
import pro.bank.service.AdminService;
import pro.bank.utils.PasswordHelper;
import pro.bank.web.controller.base.BaseController;
import pro.bank.web.editor.DateEditor;
import pro.bank.web.vo.response.ResponseJsonData;

/**
* 系统用户-登录用户Controller
*
*/
@Controller
@RequestMapping(value="/admin")
public class AdminController extends BaseController{
	@Autowired
	private AdminService adminService;

	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String index(Model model,@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="20") Integer size,@ModelAttribute("searchVal") String searchVal){
		PageHelper.startPage(page, size,"create_time desc");
		List<Admin> adminList = adminService.findAll(searchVal);
		model.addAttribute("adminPage", new PageInfo<Admin>(adminList));
		return "admin/list";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create(Model model){
		return "admin/create";
	}
	
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public @ResponseBody ResponseJsonData create(Admin admin) throws IOException{
	    admin.setCreateTime(new Date());
	    Admin checkAdmin = adminService.findByUserName(admin.getUserName());
		if(checkAdmin != null)
			return new ResponseJsonData(false,"用户名已存在");
		admin.setPassword(PasswordHelper.encryptPassword(admin.getPassword(), admin.getUserName()));
	    adminService.create(admin);
		return new ResponseJsonData(true,"添加成功");
	}
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	public @ResponseBody ResponseJsonData delete(@PathVariable Long id){
		Admin admin = (Admin)SecurityUtils.getSubject().getPrincipal();
		if(id.equals(admin.getId()))
			return new ResponseJsonData(false, "您不能删除自己的账号");	
		if(id.equals(1l))
			return new ResponseJsonData(false, "系统账号无法删除");	
		
		adminService.delete(id);
		return new ResponseJsonData(true, "删除成功");	
	}
	@RequestMapping(value="update/{id}",method=RequestMethod.GET)
	public String update(Model model,@PathVariable Long id){
		Admin admin = adminService.findById(id);
		model.addAttribute("admin", admin);
		return "admin/update";	
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public @ResponseBody ResponseJsonData update(Admin admin){
		Admin curr = adminService.findById(admin.getId());
		if(!curr.getPassword().equals(admin.getPassword()))
			admin.setPassword(PasswordHelper.encryptPassword(admin.getPassword(), admin.getUserName()));
	    Admin checkAdmin = adminService.findByUserName(admin.getUserName());
		if(checkAdmin != null&&!checkAdmin.getId().equals(admin.getId()))
			return new ResponseJsonData(false,"用户名已存在");
		adminService.update(admin);
		return new ResponseJsonData(true, "修改成功");	
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,new DateEditor());
	}
}
