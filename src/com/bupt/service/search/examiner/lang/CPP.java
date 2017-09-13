/**
 * 
 */
package com.bupt.service.search.examiner.lang;

import com.bupt.service.search.examiner.codefile.CodeFile;
import com.bupt.service.search.examiner.codefile.DefaultCodeFile;

import java.io.StringReader;

/**
 * @author Administrator
 *
 */
public class CPP implements Language{
	//Java��token����Ҫ�ĳ�CPP��
	private final String[] keyWords = { "abstract", "boolean", "break", "byte",
			"case", "catch", "char", "class", "const", "continue", "default",
			"do", "double", "else", "extends", "final", "finally", "float",
			"for", "goto", "if", "implements", "import", "instanceof", "int",
			"interface", "long", "native", "new", "package", "private",
			"protected", "public", "return", "short", "static", "strictfp",
			"super", "switch", "synchronized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while" };

	/**
	 * ȫ��Ψһʵ��
	 */
	private static CPP cpp;

	/**
	 * ����
	 */
	private String name = "CPP����";
	
	/**
	 * @return �����һ��ʵ��
	 */
	public synchronized static CPP getInstance() {
		if (cpp == null) {
			cpp = new CPP();
		}
		return cpp;
	}
	
	/**
	 * ���캯��˽�л�
	 */
	private CPP() {
	}

	@Override
	public String[] getKeyWordsArray() {
		return this.keyWords;
	}

	@Override
	public String getName() {
		return this.name;
	}

	
	//û���ã�ռ�ӵ�
	@Override
	public int[] getTokenSequence(CodeFile cf)  {
		//CPPParser lex = new CPPParser(new StringReader(cf.getText()));
		int[] ret = new int[0];
		return ret;
		/*
		  try {
			lex.runLexical();
			ArrayList<Integer> list = lex.getTokenList();
			int len = list.size();
			int[] ret = new int[len];
			for(int i = 0; i < len; i++) {
				ret[i] = list.get(i);
			}
			return ret;
		} catch (ParseException e) {
			throw e;
		}
		*/
	}
	public boolean getLineTokenSequence(DefaultCodeFile cf) throws Exception {
		//CPPParser lex = new CPPParser(new StringReader(cf.getText()));
		try {
			//lex.runLineLexical(cf);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
