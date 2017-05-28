package cn.lingco.common.persistence;

import cn.lingco.domain.UserInfo;

/**
* 作者 lingco
* 日期 2016年10月29日 下午4:02:25
* 公共的mapper
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
    int insert(UserInfo record);
    /**
     * 通过id获取数据
     * @param id
     * @return
     */
    UserInfo selectByPrimaryKey(Integer id);
    /**
     * 更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserInfo record);
}
