/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vineet
 */
public class SearchUtil {

    public static ArrayList<Integer> fullTextSearch(String[] wordList) {

        ArrayList<Integer> documents = new ArrayList<>();

        try {

            HashMap<Integer, Integer> documentMap;
            HashMap<String, ArrayList<Integer>> connectionMap;

            StemmerUtil stemmerUtil = new StemmerUtil();
            String word = stemmerUtil.stripAffixes(wordList[0]);
            if (Constants.INVERTED_INDEX.containsKey(word)) {

                documentMap = Constants.INVERTED_INDEX.get(word);
                for (Map.Entry<Integer, Integer> e : documentMap.entrySet()) {
                    documents.add(e.getKey());
                }

                for (int i = 1; i < wordList.length; i++) {
                    connectionMap = Constants.CONNECTION_INDEX.get(stemmerUtil.stripAffixes(wordList[i - 1]));
                    if (connectionMap != null) {

                        ArrayList<Integer> temp = connectionMap.get(stemmerUtil.stripAffixes(wordList[i]));
                        if (temp == null) {
                            documents = new ArrayList<>();
                            break;
                        } else {

                            for (Integer integer : documents) {
                                if (!temp.contains(integer)) {
                                    documents.remove(integer);
                                }
                            }

                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return documents;
        }

    }

    public static ArrayList<Integer> keywordSearch(String[] wordList) {

        ArrayList<Integer> documents = new ArrayList<>();

        try {

            StemmerUtil stemmerUtil = new StemmerUtil();
            String word;
            HashMap<Integer, Integer> documentMap;

            word = stemmerUtil.stripAffixes(wordList[0]);
            if (Constants.INVERTED_INDEX.containsKey(word)) {

                documentMap = Constants.INVERTED_INDEX.get(word);
                for (Map.Entry<Integer, Integer> e : documentMap.entrySet()) {
                    documents.add(e.getKey());
                }

                for (int i = 1; i < wordList.length; i++) {

                    word = stemmerUtil.stripAffixes(wordList[i]);

                    if (Constants.INVERTED_INDEX.containsKey(word)) {
                        documentMap = Constants.INVERTED_INDEX.get(word);
                        ArrayList<Integer> temp = new ArrayList<>();
                        for (Map.Entry<Integer, Integer> e : documentMap.entrySet()) {
                            temp.add(e.getKey());
                        }
                        for (Integer integer : documents) {
                            if (!temp.contains(integer)) {
                                documents.remove(integer);
                            }
                        }
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return documents;
        }

    }

}
