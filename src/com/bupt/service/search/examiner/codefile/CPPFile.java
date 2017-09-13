package com.bupt.service.search.examiner.codefile;

import com.bupt.service.search.deprecated.Pretreatment;
import com.bupt.service.search.deprecated.SpecialCharactersSearch;
import com.bupt.service.search.examiner.lang.CPP;
import com.bupt.service.search.examiner.pre.LineStruct;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CPPFile extends DefaultCodeFile{
	public CPPFile(File f) {
		super(CPP.getInstance());
		super.init(f);
	}

	@Override
	public int[] getKeySequence() throws Exception {
		String code = null;
		code = this.content + "\n";
		String temp = null;//JavaLexical.getKeySequence(code);
		String[] keys = temp.split("[\\s]*\n[\\s]*");
		int len = keys.length;
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = keys[i].hashCode();
		}
		return ans;
	}

	@Override
	public int[] convertCodeToKeySequence(String code) {
		String[] keyWord = this.lang.getKeyWordsArray();
		/**
		 * ȥ��˫�����ַ���
		 */
		code = Pretreatment.removeDoubleQuotation(code);

		/**
		 * ���ú���������ָ���ַ����������� ������
		 */
		String[] codeList = SpecialCharactersSearch.splitCodeBySeparator(code);

		/**
		 * ʹ��LinkedList��ʱ�洢�ؼ�������
		 */
		LinkedList<Integer> kwList = new LinkedList<Integer>();

		for (int i = 0; i < codeList.length; i++) {
			String temp = codeList[i].trim();
			for (int j = 0; j < keyWord.length; j++) {
				if (temp.equals(keyWord[j])) {
					kwList.add(j);
					break;
				}
			}
		}

		/**
		 * ��LinkedList�е����д浽�����ﲢ����
		 */
		int len = kwList.size();
		int[] kwcs = new int[len];
		for (int i = 0; i < len; i++) {
			kwcs[i] = kwList.get(i);
		}
		return kwcs;
	}

	/**
	 * ���ݸ�����һ��java������ݣ��������еĺ��� ��һһ��ȡ����
	 * 
	 * @param classText
	 * @return �ַ������飬�����ÿ��Ԫ��Ϊһ�������Ĵ����ı�
	 */
	public static String[] getFunctions(String classText) {
		/**
		 * �����ҳ��ĺ���ͷ����ʼλ�úͽ���λ�� �Լ�������ȡ���ĺ�����ArrayList
		 */
		ArrayList<Integer> starts = new ArrayList<Integer>();
		ArrayList<Integer> ends = new ArrayList<Integer>();
		ArrayList<String> functions = new ArrayList<String>();

		/**
		 * �ҳ�����ͷ��������ʽ
		 */
		// Pattern
		// pFunctionHead=Pattern.compile("[\\t ]*([\\w_$]+[ \\t]*)*\\(([\\w_$]+[ \\t]+[\\w_$]+)?([ \\t]*,[ \\t]*[\\w_$]+[ \\t]+[\\w_$]+)*\\)[\\s]*\\{");
		Pattern pFunctionHead = Pattern
				.compile("[\\t ]*[\\w$ \\t]*\\(([\\w$]+[ \\t]+[\\w$]+)?([ \\t]*,[ \\t]*[\\w$]+[ \\t]+[\\w$]+)*\\)[\\s]*\\{");
		Matcher mFunctionHead = pFunctionHead.matcher(classText);
		while (mFunctionHead.find()) {
			/**
			 * �ų�catch�������������ƥ�亯���ĸ���
			 */
			if (!mFunctionHead.group().matches("[\\t ]*catch.*")
					&& !mFunctionHead.group().matches("[\\t ]*new.*")) {//
				starts.add(mFunctionHead.start());
				ends.add(mFunctionHead.end());
			}
		}

		/**
		 * �ҳ����гɶԵ�˫���ŵ�������ʽ
		 */
		Pattern p = Pattern.compile("\"([^\\\\\"]|\\\\.)*\"");
		Matcher m = p.matcher(classText);
		char[] charArray = classText.toCharArray();
		int length = charArray.length;
		while (m.find()) {
			int s = m.start();
			int e = m.end();
			for (int i = s; i < e; i++) {
				if (charArray[i] == '{' || charArray[i] == '}')
					charArray[i] = ' ';
			}
		}

		int startsNum = starts.size(), index = 0;
		int s = 0;
		int e = s;
		while (index < startsNum) {
			s = ends.get(index);
			if (s < e) {
				index++;
				continue;
			}
			e = s;
			int flag = 1;
			while (flag > 0 && e < length) {
				if (charArray[e] == '{')
					flag++;
				else if (charArray[e] == '}')
					flag--;
				e++;
			}
			functions.add(classText.substring(starts.get(index), e));
			index++;
		}
		int size = functions.size();
		String[] results = new String[size];
		for (int i = 0; i < size; i++) {
			results[i] = functions.get(i);
		}
		return results;
	}

	/**
	 * �������ļ��к��е�java�� ������Щ��һһ��ȡ����
	 * 
	 * @return �ַ������飬�����ÿһ��Ԫ��Ϊһ��java����ַ���
	 */
	public String[] getClassSequence() throws Exception {
		return null;
		//return CPPParser.runParser(this.content).getClassSequence();
	}

	/**
	 * �������ļ��к��е�java�� ������Щ��һһ��ȡ����
	 * 
	 * @return �ַ������飬�����ÿһ��Ԫ��Ϊһ��java����ַ���
	 */
	@Deprecated
	public String[] getClasses() {

		String fileText = this.content;

		/**
		 * �����ҳ�����ͷ����ʼλ�úͽ���λ�� �Լ�������ȡ�������ArrayList
		 */
		ArrayList<Integer> starts = new ArrayList<Integer>();
		ArrayList<Integer> ends = new ArrayList<Integer>();
		ArrayList<String> classes = new ArrayList<String>();

		/**
		 * �ҳ���ͷ��������ʽ
		 */
		Pattern pClassHead = Pattern
				.compile("(\\s|(public\\s)|(private\\s)|(protected\\s)|(static\\s)|(final\\s)|(abstract\\s))*class\\s+[^\\{]+\\{");
		Matcher mClassHead = pClassHead.matcher(fileText);
		while (mClassHead.find()) {
			starts.add(mClassHead.start());
			ends.add(mClassHead.end());
		}

		/**
		 * �ҳ����гɶԵ�˫���ŵ�������ʽ
		 */
		Pattern p = Pattern.compile("\"([^\\\\\"]|\\\\.)*\"");
		Matcher m = p.matcher(fileText);
		char[] charArray = fileText.toCharArray();
		int length = charArray.length;
		while (m.find()) {
			int s = m.start();
			int e = m.end();
			for (int i = s; i < e; i++) {
				if (charArray[i] == '{' || charArray[i] == '}')
					charArray[i] = ' ';
			}
		}
		int startsNum = starts.size(), index = 0;
		int s = 0;
		int e = s;
		while (index < startsNum) {
			s = ends.get(index);
			if (s < e) {
				index++;
				continue;
			}
			e = s;
			int flag = 1;
			while (flag > 0 && e < length) {
				if (charArray[e] == '{')
					flag++;
				else if (charArray[e] == '}')
					flag--;
				e++;
			}
			classes.add(fileText.substring(starts.get(index), e));
			index++;
		}
		int size = classes.size();
		String[] results = new String[size];
		for (int i = 0; i < size; i++) {
			results[i] = classes.get(i);
		}
		return results;
	}

	protected String[] classArray;
	protected String[] functionArray;

	@Override
	public List<LineStruct> getLineStructHashArrays() {
		// TODO Auto-generated method stub
		return null;
	}

}
