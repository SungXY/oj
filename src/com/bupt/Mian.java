package com.bupt;

import com.bupt.service.search.Trie;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sung on 2017/8/30.
 */
public class Mian {

    public static String[] words = {"a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao", "bei", "ben", "beng",
            "bi", "bian", "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai", "can", "cang", "cao",
            "ce", "cen", "ceng", "cha", "chai", "chan", "chang", "chao", "che", "chen", "cheng", "chi",
            "chong", "chou", "chu", "chua", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci",
            "cong", "cou", "cu", "cuan", "cui", "cun", "cuo", "da", "dai", "dan", "dang", "dao", "de",
            "dei", "den", "deng", "di", "dia", "dian", "diao", "die", "ding", "diu", "dong", "dou", "du",
            "duan", "dui", "dun", "duo", "e", "en", "eng", "er", "fa", "fan", "fang", "fei", "fen", "feng",
            "fiao", "fo", "fou", "fu", "ga", "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng", "gong",
            "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun", "guo", "ha", "hai", "han", "hang", "hao",
            "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun",
            "huo", "ji", "jia", "jian", "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan",
            "jue", "ka", "kai", "kan", "kang", "kao", "ke", "ken", "keng", "kong", "kou", "ku", "kua", "kuai",
            "kuan", "kuang", "kui", "kun", "kuo", "la", "lai", "lan", "lang", "lao", "le", "lei", "leng", "li",
            "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "lo", "long", "lou", "lu", "luan", "lun",
            "luo", "lv", "lve", "ma", "mai", "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao",
            "mie", "min", "ming", "miu", "mo", "mou", "mu", "na", "nai", "nan", "nang", "nao", "ne", "nei", "nen",
            "neng", "ni", "nian", "niang", "niao", "nie", "nin", "ning", "niu", "nong", "nou", "nu", "nuan", "nun",
            "nuo", "nv", "nve", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian",
            "piao", "pie", "pin", "ping", "po", "pou", "pu", "qi", "qia", "qian", "qiang", "qiao", "qie", "qin",
            "qing", "qiong", "qiu", "qu", "quan", "que", "qun", "ran", "rang", "rao", "re", "ren", "reng", "ri",
            "rong", "rou", "ru", "rua", "ruan", "rui", "run", "ruo", "sa", "sai", "san", "sang", "sao", "se",
            "sen", "seng", "sha", "shai", "shan", "shang", "shao", "she", "shei", "shen", "sheng", "shi", "shou",
            "shu", "shua", "shuai", "shuan", "shuang", "shui", "shun", "shuo", "si", "song", "sou", "su", "suan",
            "sui", "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te", "tei", "teng", "ti", "tian", "tiao", "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu", "xi", "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya", "yan", "yang", "yao", "ye", "yi", "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang", "zao", "ze", "zei", "zen", "zeng", "zha", "zhai", "zhan", "zhang", "zhao", "zhe", "zhei", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai", "zhuan", "zhuang", "zhui", "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo"};


    public static void main(String[] args) {
        String test = "@#$%^&&1234567";
        Trie trie = new Trie();
        trie.initByFile("data/out.txt");
        System.out.println(trie.isExist(test)?"TRUE":"FALSE");
        //getData();
    }

    /**
     * 验证字典树代码的正确性
     */
    public static void check() {
        Trie trie = new Trie();
        trie.initByFile("data/out.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/out.txt")));
            String tempString = null;
            String ans = "";
            while ((tempString = reader.readLine()) != null) {
                ans += tempString;
            }

            String[] words = ans.split(",");
            for (String item : words) {
                if (!trie.isExist(item)) {
                    System.out.println("ERROR : " + item);
                }
            }
            System.out.println("down");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getData() {
        File file = new File("data/a.txt");
        File out = new File("data/out.txt");
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            String ans = "";
            while ((tempString = reader.readLine()) != null) {
                ans += tempString;
            }
            String[] words = ans.split(",");
            ArrayList<String> res = new ArrayList<>();

            FileWriter writer = new FileWriter(out, false);

            int cnt = 0;
            for (int i = 0; i < words.length; i++) {

                String temp = words[i].substring(words[i].indexOf("\'") + 1, words[i].length() - 1) + ",";
                if (temp.length() == 2)
                    continue;
                writer.write(temp);
                cnt++;
            }
            System.out.println(cnt);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
