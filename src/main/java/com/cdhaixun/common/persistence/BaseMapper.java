package com.cdhaixun.common.persistence;

/**
 * 公共mapper,提供基本的方法
 * @author tanggm
 * @param <T> 实体模型
 */
public interface BaseMapper<T> {
	/**
	 * 通过id删除数据
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * 添加数据
	 * @param record
	 * @return
	 */
    int insert(T record);
    /**
     * 通过id获取数据
     * @param id
     * @return
     */
    T selectByPrimaryKey(Integer id);
    /**
     * 更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
}
