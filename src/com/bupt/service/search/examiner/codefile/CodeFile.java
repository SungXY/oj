/*
 * �ļ���:		CodeFile.java
 * �ӿ�����		CodeFile
 * �������ڣ�	2011/02/25
 * ����޸ģ�	2013/03/28
 * ���ߣ�		���
 */

package com.bupt.service.search.examiner.codefile;

import com.bupt.service.search.examiner.lang.LangDeterminable;
import com.bupt.service.search.examiner.lang.Language;
import com.bupt.service.search.examiner.pre.LineStruct;

import java.util.List;


/**
 * �����ļ��ӿ�
 * @author ben
 * 
 */
public interface CodeFile extends LangDeterminable {
	
	/**
	 * @return ���ļ��ı������
	 */
	@Override
	public Language getLanguage();
	
	/**
	 * @return ���ļ����͵�����
	 */
	public String getTypeDesc();

	/**
	 * @return �ļ���·���ַ���
	 */
	public String getFilePath();

	/**
	 * @return �ļ�������
	 */
	public String getName();

	/**
	 * ��ȡ����Ĺؼ�������
	 * 
	 * @param code
	 *            �뱾�ļ���ͬ���͵�һ�δ����ı�
	 * @return ���������ʾ�Ĺؼ�������
	 */
	public int[] convertCodeToKeySequence(String code);
	
	/**
	 * �������ñ��ļ��Ĵ����ı�����
	 * @param codeText �µĴ����ı�����
	 */
	public void setText(String codeText);
	
	/**
	 * 
	 * @return ���������Ĵ����ı��ַ���
	 */
	public String getText();

	/**
	 * ���������ı���ָ�����ضȣ����ַ����д����ضȵľ��庬����������
	 * ��������ı�Ϊabcdefg��repeatability = 2����õ�������Ϊ
	 * ab, bc, cd, de, ef, fg
	 * ���������е�ÿһ��Ԫ��(�ַ���)hash
	 * @param repeatability
	 * @return ����õ���hashֵ���� 
	 */
	public int[] getCharHashArrays(int repeatability);
	
	/**
	 * @return ɢ�к�Ĵ��������У����н�������
	 */
	public int[] getLineHashArrays();
	/**
	 * ���кţ����з���hashֵ
	 */
	public List<LineStruct> getLineStructHashArrays();
	/**
	 * @return �����������
	 */
	public long getLineNum();
	
	/**
	 * @return ��������ַ���
	 */
	public long getCharacterNum();

	/**
	 * 
	 * @return ɢ�к�Ĵ���Ĺؼ�������
	 */
	public int[] getKeySequence()  throws Exception;
}
