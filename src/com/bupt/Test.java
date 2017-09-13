package com.bupt;

import java.io.*;

/**
 * Created by sung on 2017/8/30.
 */


public class Test {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("data/english_word.txt")));
            File out = new File("data/english_word_out.txt");
            if (!out.exists()) {
                out.createNewFile();
            }
            FileWriter writer = new FileWriter(out, false);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() <=2 || line.indexOf(" ") != -1 || line.indexOf("'") != -1 || line.indexOf("-") !=- 1|| (line.charAt(0) >= 'A' && line.charAt(0) <= 'Z')) {
                    System.out.println(line);
                } else {
                    writer.write(line + ",\n");
                }
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
