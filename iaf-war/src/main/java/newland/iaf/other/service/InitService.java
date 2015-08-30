package newland.iaf.other.service;

/**
 * 
 * @author rabbit
 * 
 */
public interface InitService {
	/**
	 * 初始化Demo
	 */
	void execSqlFile(String fileName) throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	void initDemoData() throws Exception;

	/**
	 * 
	 * @throws Exception
	 */
	void initBaseDate() throws Exception;

	/**
	 * 删除初始化的数据
	 * 
	 * @throws Exception
	 */
	void cleanData() throws Exception;
}
