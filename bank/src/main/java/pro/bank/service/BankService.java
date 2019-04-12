package pro.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import pro.bank.entity.Bank;
import pro.bank.mapper.BankMapper;
import pro.bank.service.base.BaseService;
import pro.bank.constant.YesNo;
import java.util.Map;
@Service
public class BankService extends BaseService<Bank>{

	@Autowired
	private BankMapper mapper;
	
	public List<Bank> findAll(String searchVal,Long parentId,Integer level){
		return mapper.findAll(searchVal,parentId,level);
	}
	
	public Integer count(String searchVal,Map<String,Object> params){
		return mapper.count(searchVal,params);
	}

	public Bank findByName(String bankName) {
		return mapper.findByName(bankName);
	}
	
	

}
