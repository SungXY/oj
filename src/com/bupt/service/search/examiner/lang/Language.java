/*
 * �ļ���:		Language.java
 * �������ڣ�	2013-03-27
 * ����޸ģ�	2013-04-24
 * ���ߣ�		���
 */
package com.bupt.service.search.examiner.lang;

import com.bupt.service.search.examiner.codefile.CodeFile;
import com.bupt.service.search.examiner.codefile.DefaultCodeFile;

import java.util.List;


/**
 * (�������)���Խӿ�
 * @author ben
 */
public interface Language {
	/**
	 * @return �����Ե����йؼ���(���ֵ�������������)
	 */
	public String[] getKeyWordsArray();
	
	/**
	 * @return �����Ե�����
	 */
	public String getName();
	
	/**
	 * �Ը�����һ�������ļ����з�������ȡ�����������У�����hashֵ�������ʽ����
	 * @param cf ��Ҫ���з����Ĵ����ļ�
	 * @return ��ʾ�����ļ�token���е�hashֵ����
	 * @throws Exception
	 */
	public int[] getTokenSequence(CodeFile cf) throws Exception;
	public boolean getLineTokenSequence(DefaultCodeFile cf) throws Exception;
}
