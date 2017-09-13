package com.bupt.service.search;

import com.bupt.service.search.examiner.codefile.CPPFile;
import com.bupt.service.search.node.VariableNode;
import com.bupt.service.search.parser.cpp.CPPParser2;
import com.bupt.service.search.parser.cpp.ParseException;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sung on 2017/8/31.
 */
public class SearchChineseCharAndPinyin {
    private static Trie pingyinTrie = null;
    private static Trie englistWordTrie = null;

    SearchChineseCharAndPinyin() {
        if (pingyinTrie == null) {
            pingyinTrie = new Trie();
        }
        if (englistWordTrie == null) {
            englistWordTrie = new Trie();
        }
    }

    public void initTrie(String fileName1, String fileName2) {
        pingyinTrie.initByFile(fileName1);
        englistWordTrie.initByFile(fileName2);
    }

    public static void main(String[] args) {
        SearchChineseCharAndPinyin searchChineseCharAndPinyin = new SearchChineseCharAndPinyin();
        //searchChineseCharAndPinyin.initTrie("data/pinyin_out.txt", "data/english_word_out.txt");

        String fileName = "e:\\c";
        File root = new File(fileName);
        File[] files = root.listFiles();


        for (int i = 0; i < files.length; i++) {
            List<VariableNode> res = searchChineseCharAndPinyin.getVariable(files[i].getAbsolutePath());
            if (res != null) {
                System.out.println(i+ " " + res);
            }
        }
    }

    /**
     * 查找变量名之前需要预处理，去除文件中的注释
     *
     * @param text
     * @return
     */
    private String clearIllegalCharacter(String text) {
        text += "\n\n";
        char[] solvedText = text.toCharArray();
        String ans = "";
        for (int i = 0; i < solvedText.length; i++) {
            if (solvedText[i] == '\\') {
                if (solvedText[i + 1] == '\\') {
                    ans += ' ';
                    i++;
                } else
                    for (int j = i + 1; j < solvedText.length; j++) {
                        char k = solvedText[j];
                        if (k == '\t' || k == ' ' || k == '	')
                            continue;
                        if (k == '\n') {
                            i = j;
                            ans += '\t';
                            break;
                        } else {
                            ans += ' ';
                            i = j;
                            break;
                        }
                    }
            } else
                ans += solvedText[i];
        }
        return ans;
    }
    /**
     * 查找传入文件变量的的所有变量名和宏定义，返回个包含变量名和位置的链表
     *
     * @param fileName
     * @return
     */
    public List<VariableNode> getVariable(String fileName) {

        CPPFile test2 = new CPPFile(new File(fileName));
        test2.setText(clearIllegalCharacter(test2.getText()));
        /**
         * 从此处开始移植，传入一个CPPFile类型的文件，
         * 其实就是DefaultCodeFile类继承的，和原有代码是相合的，基本不需要改动
         * 通过解析器lex2调用runLexicalFinder()
         * 得到的结果通过lex2.getFinder()返回
         * */
        CPPParser2 lex2 = new CPPParser2(new StringReader(test2.getText()));
        try {
            lex2.runLexicalFinder();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<VariableNode> test3 = lex2.getFinder();
        return test3;
    }

    /**
     * 判断输入的字符串中是否含有拼音或汉字
     *
     * @param word
     * @return
     */
    public boolean isExist(String word) {
        //如果包含中文直接返回True
        if(word.getBytes().length != word.length()){
            return true;
        }
        int ok = 0;

        //将复杂的变量名按特殊字符切片，每个切片里只有英文字符
        List<String> slices = this.getSlice(word);
        System.out.println("切片为： " + slices);
        for (int i = 0; i < slices.size(); i++) {

            String temp = slices.get(i);
            for (int j = 0; j < temp.length(); j++) {
                int english_len = englistWordTrie.search(temp.substring(j));
                int pinyin_len = pingyinTrie.search(temp.substring(j));
                System.out.println(temp.substring(j)+"[ english_len: "+ english_len +"  pinyin_len: " + pinyin_len+"]");
                if(english_len == -1 && pinyin_len == -1)
                    continue;
                if(english_len > pinyin_len){
                    j += english_len-1;
                }
                if(english_len < pinyin_len){
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * 假设有变量asdfas_asdfas_1212_asdf1212_12
     * 将变量中的英文字符片段剪出来，数字和‘_’去掉
     * @param word
     * @return
     */
    private List<String> getSlice(String word) {
        word = word.toLowerCase();
        List<String> slices = new ArrayList<>();
        int pre = 0;
        while (pre < word.length() && (word.charAt(pre) < 'a' || word.charAt(pre) > 'z')) {
            pre++;
        }
        for (int i = pre; i < word.length(); i++) {
            if (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                if (pre != i) {
                    slices.add(word.substring(pre, i));
                    while (i < word.length() && (word.charAt(i) < 'a' || word.charAt(i) > 'z')) {
                        i++;
                    }
                    pre = i;
                    i--;
                }
            }
        }
        if (pre != word.length()) {
            slices.add(word.substring(pre, word.length()));
        }
        return slices;
    }
}
