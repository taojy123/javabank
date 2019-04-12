package pro.bank.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.bank.entity.Admin;
import pro.bank.mapper.AdminMapper;
import pro.bank.service.base.BaseService;
@Service
public class AdminService extends BaseService<Admin>{

	@Autowired
	private AdminMapper mapper;
	
	public List<Admin> findAll(String searchVal){
		return mapper.findAll(searchVal);
	}
	
	public Integer count(String searchVal,Map<String,Object> params){
		return mapper.count(searchVal,params);
	}
	
	
	public Admin findByUserName(String userName){
		return mapper.findByUserName(userName);
	}

}
