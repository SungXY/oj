package com.bupt.service.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sung on 2017/8/30.
 */
public class Trie {
    private class Node {
        private Node[] childs;
        private boolean isLeaf;

        public Node() {
            isLeaf = false;
            childs = new Node[26];
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void initByFile(String fileName) {
        File in = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(in));
            String tempString = null;
            String ans = "";
            while ((tempString = reader.readLine()) != null) {
                ans += tempString;
            }
            String words[] = ans.split(",");
            for (String item : words) {
                this.insert(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * @param words
     */
    private void insert(String words) {
        insert(this.root, words);
    }

    /**
     * @param root
     * @param word
     */
    private void insert(Node root, String word) {
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (index > 26 || index < 0) {
                System.out.println(word);
            }
            if (root.childs[index] == null) {
                root.childs[index] = new Node();
            }

            //如果找到了字符串结尾，则做标记
            if (i == chars.length - 1) {
                root.childs[index].isLeaf = true;
            }
            //root指向子节点，继续处理
            root = root.childs[index];
        }
    }

    public boolean isExist(String temp) {
        return true;
    }

    /**
     * 暴露给用户的接口
     * root在搜索的时候会不断移动，这个函数将root作为参数传入，
     * 防止了定义在类中的root发生改变
     *
     * @param word
     * @return
     */
    public int search(String word) {
        return this.search(this.root, word);
    }

    /**
     * 从第一个位置开始匹配，返回最长匹配的长度
     *
     * @param word
     * @return
     */
    private int search(Node root, String word) {

        int maxLength = -1;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //如果变量中出现了非字母字符，直接返回false
            if (chars[i] < 'a' || chars[i] > 'z')
                return maxLength;
            int index = chars[i] - 'a';
            if (root.childs[index] != null) {
                if (root.childs[index].isLeaf) {
                    maxLength = i + 1;
                    //System.out.println(word.substring(0, i + 1) + "  find leaf " + maxLength);
                }
                root = root.childs[index];
            } else {
                return maxLength;
            }
        }
        return maxLength;
    }
}
