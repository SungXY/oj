/*
 * 文件名：		LangDeterminable.java
 * 创建日期：	2013-4-4
 * 最近修改：	2013-4-4
 * 作者：		徐犇
 */
package com.bupt.service.search.examiner.lang;

/**
 * 接口，实现此接口的类可以确定所用编程语言并能够返回相应编程语言对象
 * @author ben
 */
public interface LangDeterminable {
	/**
	 * @return 编程语言
	 */
	public Language getLanguage();
}
