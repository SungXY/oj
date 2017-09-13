/*
 * �ļ���:		DefaultCodeFile.java
 * ������		DefaultCodeFile
 * �������ڣ�	2009-10-15
 * ����޸ģ�	2013-06-23
 * ���ߣ�		���
 */

package com.bupt.service.search.examiner.codefile;

import com.bupt.service.search.examiner.common.IOAgent;
import com.bupt.service.search.examiner.lang.Language;
import com.bupt.service.search.examiner.pre.LineStruct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * ʵ���˴����ļ��ӿڵ�Ĭ�ϴ����ļ���
 * 
 * @author ben
 */
public abstract class DefaultCodeFile implements CodeFile {
	private List<LineStruct> textLine=new ArrayList<LineStruct>();
	private List<LineStruct> tokenLine=new ArrayList<LineStruct>();
	public List<LineStruct> getTextLine() {
		return textLine;
	}

	public void setTextLine(List<LineStruct> textLine) {
		this.textLine = textLine;
	}

	public List<LineStruct> getTokenLine() {
		return tokenLine;
	}

	public void setTokenLine(List<LineStruct> tokenLine) {
		this.tokenLine = tokenLine;
	}
	/**
	 * �������ļ����õı������
	 */
	protected Language lang = null;

	/**
	 * ��������
	 */
	protected String content = null;

	/**
	 * �ļ�·��
	 */
	protected String filePath = null;

	/**
	 * �ļ�����
	 */
	protected String name = null;
	
	/**
	 * ����������
	 */
	protected long lineNum = 0;
	
	/**
	 * ��������ַ���(unicode�ַ�)
	 */
	protected long charNum = 0;

	/**
	 * ���ַ����д�����ض�
	 */
	private int charrepeat = 1;

	/**
	 * ���������ı���charrepeat����õ���hashֵ����
	 */
	private int[] charHashs = null;

	/**
	 * @param lang
	 */
	public DefaultCodeFile(Language lang) {
		this.lang = lang;
	}

	public final Language getLanguage() {
		return this.lang;
	}

	/**
	 * ʹ��һ��File������ʼ��һ�������ļ�����ע�����File��������ȷ����(�ڴ�����)��������ܳ���
	 * 
	 * @param f
	 */
	protected final void init(File f) {
		//TODO ���Ż�
		IOAgent ioagent = IOAgent.getInstance();
		try {
			filePath = f.getCanonicalPath();
			name = f.getName();
			content = ioagent.getFileText(f);
			char[] ca = content.toCharArray();
			charNum = ca.length;
			lineNum = 1;
			for(int i = 0; i < charNum; i++) {
				if(ca[i] == '\n') {
					lineNum++;
				}
			}
			int index = filePath.lastIndexOf(".") + 1;
			if (index > 0) {
				// suffix = filePath.substring(index);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public final String getFilePath() {
		return this.filePath;
	}

	@Override
	public final String getText() {
		return this.content;
	}
	
	@Override
	public final void setText(String codeText) {
		this.content = codeText;
	}

	@Override
	public String getTypeDesc() {
		return this.lang.getName() + "Դ�ļ�";
	}

	@Override
	public final String getName() {
		return this.name;
	}
	
	@Override
	public final long getLineNum() {
		return this.lineNum;
	}

	@Override
	public final long getCharacterNum() {
		return this.charNum;
	}

	@Override
	public int[] getLineHashArrays() {
		String text = content;
		String[] lines = text.split("\n", -1);//split�����ڶ���������Ϊ�����ܱ�֤��������
		int[] ret = new int[lines.length];
		for (int i = 0; i < lines.length; i++) {
			ret[i] = lines[i].hashCode();
		}
		return ret;
	}
	/**
	 * ���ش��кŵ�hashֵ
	 * �кŴ�1��ʼ
	 */
	public boolean getLineStructHashArrays(DefaultCodeFile cf) {
		if(content==null){
			List<LineStruct> ret = cf.getTextLine();
			ret.clear();
			return true;
		}
		String text = content;
		String[] lines = text.split("\n", -1);//split�����ڶ���������Ϊ�����ܱ�֤��������
		List<LineStruct> ret = cf.getTextLine();
		ret.clear();
		for (int i = 0; i < lines.length; i++) {
			if((!lines[i].equals(""))&&(!lines[i].equals("\t"))&&(!lines[i].equals("\t\t"))){
				LineStruct templine=new LineStruct(i+1,lines[i].hashCode());
				ret.add(templine);			
				}
		}
//		cf.setTextLine(ret);
		return true;
	}

	@Override
	public int[] getCharHashArrays(int repeatability) {
		/*
		 * �����һ�μ���Ľ�����ڣ���ֱ�ӷ���
		 */
		if (this.charHashs != null && repeatability == this.charrepeat) {
			return this.charHashs;
		}

		char[] arrays = content.toCharArray();
		int len = arrays.length - repeatability + 1;
		int i, j;
		int[] ret = new int[len];

		/*
		 * �ο�javaԴ������String�Ĺ�ϣ�㷨ʵ��
		 */
		for (i = 0; i < len; i++) {
			int h = 0, b = i + repeatability;
			for (j = i; j < b; j++) {
				h = 31 * h + arrays[j];
			}
			ret[i] = h;
		}

		this.charHashs = ret;
		this.charrepeat = repeatability;
		return ret;
	}

	@Override
	public final String toString() {
		return this.name;
	}

	@Override
	public abstract int[] getKeySequence() throws Exception;

	@Override
	public abstract int[] convertCodeToKeySequence(String code);
}
