package org.ldw.dao;


import org.ldw.entity.Index;

import java.io.*;
import java.util.*;

public class IndexDao {
    public static Map<String, Set<String>> indexList;

    public static void initIndices() {
        indexList = new TreeMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("data/output/index/part-r-00000")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strArr = line.split("[;\\s+]");
                Set<String> bookCodeSet = new TreeSet<>();
                for (int i = 1; i < strArr.length; i++) {
                    String trim = strArr[i].trim();
                    if (trim.length() > 0)
                        bookCodeSet.add(trim);
                }
                indexList.put(strArr[0], bookCodeSet);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Map<String, Set<String>> getIndices() {
        if (indexList == null)
            initIndices();
        return indexList;
    }
}