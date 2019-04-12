package pro.bank.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMapper<T> {

	/**
	 * R
	 * 
	 * @return
	 */
	List<T> selectAll();

	/**
	 * D
	 * @param id
	 * @return
	 */
	void deleteByPrimaryKey(Long id);

	/**
	 * C
	 * @param record
	 * @return
	 */
	void insert(T record);

	/**
	 * C
	 * @param record
	 * @return
	 */
	void insertSelective(T record);

	/**
	 * R
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(Long id);

	/**
	 * U
	 * @param record
	 * @return
	 */
	void updateByPrimaryKeySelective(T record);
	/**
	 * U
	 * @param record
	 * @return
	 */
	void updateByPrimaryKey(T record);
}
