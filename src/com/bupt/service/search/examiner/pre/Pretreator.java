/*
 * 文件名:		Pretreator.java
 * 类名：		Pretreator
 * 创建日期：	2013-03-29
 * 最近修改：	2013-04-25
 * 作者：		徐犇
 */
package com.bupt.service.search.examiner.pre;


import com.bupt.service.search.examiner.codefile.CodeFile;
import com.bupt.service.search.examiner.lang.Language;

/**
 * 预处理抽象类
 * @author ben
 */
public abstract class Pretreator {

	/**
	 * 对代码文件进行预处理
	 * @param cf 需要进行预处理的代码文件
	 * @return 预处理是否成功
	 */
	public boolean pretreat(CodeFile cf) {
		if (cf == null) {
			return false;
		}
		String text = cf.getText();
		if(text == null || text.trim().length() <= 0) {//代码的内容为空
			return false;
		}
		text = excute(text, cf.getLanguage());
		cf.setText(text);
		return true;
	}
	
	/**
	 * (对代码文本)实际执行预处理过程的方法，由派生类具体实现
	 * @return 预处理得到的代码文本内容
	 */
	protected abstract String excute(String codeText, Language lang);
	
}
