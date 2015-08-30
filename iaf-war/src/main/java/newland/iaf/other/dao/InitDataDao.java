/**
 * 
 */
package newland.iaf.other.dao;

import java.util.List;

/**
 * 初始化脚本
 * 
 * @author lindaqun
 * 
 */
public interface InitDataDao {

	void execSql(String sql);

	void execSqls(List<String> sqls);

}
