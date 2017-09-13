/*
 * 文件名:		CodeFileFactory.java
 * 接口名：		CodeFileFactory
 * 创建日期：	2013/03/27
 * 最近修改：	2013/03/27
 * 作者：		徐犇
 */
package com.bupt.service.search.examiner.codefile;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * CodeFile的工厂类接口
 * @author ben
 */
public interface CodeFileFactory {
	/**
	 * @param f
	 * @return 构造的CodeFile实例
	 */
	public CodeFile createCodeFile(File f);
	
	/**
	 * @return 返回本工厂类能处理的文件的过滤器
	 */
	public FileFilter getFileFilter();
}
