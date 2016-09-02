/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.util;

import com.neu.psa.irs.bean.DocumentBean;
import com.neu.psa.irs.dao.AdminDao;
import java.util.HashMap;

/**
 *
 * @author Vineet
 */
public class PageRankUtil {

    private static final double K1 = 0.5;
    private static final double B = 0.75;

    private double getIDFWeight(String word) {

        double weight = 0.0;

        try {

            int N = getTotalDocuments();
            int n = getTotalDocuments(word);

            weight = Math.log((N - n + 0.5) / (n + 0.5));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return weight;
        }

    }

    private int getTotalDocuments() {

        int documentCount = 0;

        try {

            AdminDao adminDao = new AdminDao();
            documentCount = adminDao.getTotalDocuments();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return documentCount;
        }

    }

    private int getTotalDocuments(String word) {

        int documentCount = 0;

        try {

            documentCount = Constants.INVERTED_INDEX.get(word).size();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return documentCount;
        }

    }

    private int getTermFrequency(String word, int documentId) {

        int termFrequency = 0;

        try {

            HashMap<Integer, Integer> documentMap = Constants.INVERTED_INDEX.get(word);
            termFrequency = documentMap.get(documentId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return termFrequency;
        }

    }

    public double getPageRank(String query, DocumentBean documentBean) {

        double rank = 0.0;

        try {

            query = query.replaceAll("\n", " ");
            query = query.replaceAll("\r", " ");
            query = query.replaceAll("\t", " ");
            String[] queryParts = query.split(" ");

            AdminDao adminDao = new AdminDao();
            StemmerUtil stemmerUtil = new StemmerUtil();

            for (String s : queryParts) {
                s = stemmerUtil.stripAffixes(s);
                double IDF = getIDFWeight(s);
                int f = getTermFrequency(s, documentBean.getId());
                int D = documentBean.wordCount;
                double avgdl = adminDao.getAvgDocumentLength();

                rank += IDF * ((f * (K1 + 1)) / (f + K1 * (1 - B + (B * (D / avgdl)))));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rank;
        }

    }

}
