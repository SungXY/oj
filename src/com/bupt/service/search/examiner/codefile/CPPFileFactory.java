package com.bupt.service.search.examiner.codefile;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CPPFileFactory implements CodeFileFactory{
	/**
	 * 全局唯一实例
	 */
	private static CPPFileFactory cppff;
	
	/**
	 * 本工厂类能处理的文件的过滤器
	 */
	private CPPFileFilter filter = new CPPFileFilter();
	
	/**
	 * 构造方法私有化
	 */
	private CPPFileFactory() {
	}
	
	/**
	 * @return 一个本类的实例
	 */
	public static synchronized CPPFileFactory getInstance() {
		if(cppff == null) {
			cppff = new CPPFileFactory();
		}
		return cppff;
	}
	
	@Override
	public CodeFile createCodeFile(File f) {
		if(f == null || !f.exists() || !f.isFile()) {
			return null;
		}
		if(!this.filter.accept(f)) {
			return null;
		}
		return new CPPFile(f);
	}

	@Override
	public FileFilter getFileFilter() {
		return this.filter;
	}
	/**
	 * C文件名过滤器类
	 * @author ben
	 */
	private class CPPFileFilter extends FileFilter {
		@Override
		public String getDescription() {
			return "CPP源代码文件(*.cpp)";
		}

		@Override
		public boolean accept(File file) {
			if (file.isDirectory()) {
				return true;
			}
			String name = file.getName();
			return name.matches(".*\\.*");
		}
	}
}
