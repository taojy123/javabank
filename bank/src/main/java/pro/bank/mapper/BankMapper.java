package pro.bank.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pro.bank.entity.Bank;
import pro.bank.mapper.base.BaseMapper;
import pro.bank.constant.YesNo;
import java.util.Map;
import java.util.List;
/**
* 银行mapper
* 
*/
@Mapper
public interface BankMapper extends BaseMapper<Bank> {	
	
	List<Bank> findAll(@Param("searchVal") String searchVal,@Param("parentId") Long parentId,@Param("level") Integer level);
	Integer count(@Param("searchVal") String searchVal, @Param("params") Map<String,Object> params);
	@Select("select * from t_sys_bank where name = #{bankName}")
	Bank findByName(String bankName);
	
}