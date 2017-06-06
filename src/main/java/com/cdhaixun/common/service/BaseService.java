package com.cdhaixun.common.service;
/**
* 作者 lingco
* 日期 2016年10月29日 下午4:08:03
* 公共的service，提供增删查改
*/
public interface BaseService<T> {
	/**
	 * 通过id查询数据
	 * @param id
	 * @return
	 */
	T findById(Integer id);
	/**
	 * 添加数据
	 * @param domain
	 * @throws Exception
	 */
	void save(T domain) throws Exception;
	/**
	 * 更新数据
	 * @param domain
	 * @throws Exception
	 */
	void update(T domain) throws Exception;
	/**
	 * 删除数据
	 * @param domain
	 * @throws Exception
	 */
	void delete(T domain) throws Exception;
}
