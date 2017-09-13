/*
 * 文件名：		CodeFileContainer.java
 * 创建日期：	2013-4-25
 * 最近修改：	2013-4-25
 * 作者：		徐犇
 */
package com.bupt.service.search.examiner.codefile;

/**
 * 
 * 装有代码文件的结构的统一接口
 * @author ben
 *
 */
public interface CodeFileContainer {
	/**
	 * @return 该结构中所有代码文件的数组
	 */
	public CodeFile[] getAllCodeFiles();
	
	/**
	 * @return 该结构中代码文件总数量
	 */
	public int getCodeFileNum();

}
