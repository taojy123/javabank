package pro.bank.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pro.bank.entity.Admin;
import pro.bank.mapper.base.BaseMapper;
/**
* 系统用户-登录用户mapper
* 
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {	
	
	List<Admin> findAll(@Param("searchVal") String searchVal);
	Integer count(@Param("searchVal") String searchVal,@Param("params") Map<String,Object> params);
	Admin findByUserName(String userName);
	
}