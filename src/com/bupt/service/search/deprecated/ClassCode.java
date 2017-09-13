/*
 * 文件名:		ClassCode.java
 * 类名：		ClassCode
 * 创建日期：	2011/03/14
 * 最近修改：	2011/03/14
 * 作者：		徐犇
 */

package com.bupt.service.search.deprecated;

/**
 * 
 * @author ben
 *
 */
public interface ClassCode {

	/**
	 * @return 类名
	 */
	public String getClassName();
	
	/**
	 * @return 函数体
	 */
	public String getClassBody();
	
	/**
	 * @return 返回此类中的所有函数
	 */
	public FunctionCode[] getFunctions();
	
}
