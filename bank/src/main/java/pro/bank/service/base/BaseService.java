package pro.bank.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.bank.mapper.base.BaseMapper;

@Service
public abstract class BaseService<T> {

		@Autowired
		BaseMapper<T> mapper;
		
		
		public T findById(Long id){
			return mapper.selectByPrimaryKey(id);
		}
		public List<T> findAll(){
			return mapper.selectAll();
		}
		
		public  void create(T t){
			mapper.insertSelective(t);
		}
		
		
		public void update(T t){
			mapper.updateByPrimaryKeySelective(t);
		}
		
		public void delete(Long id){
			mapper.deleteByPrimaryKey(id);
		}
		
		
		
}
