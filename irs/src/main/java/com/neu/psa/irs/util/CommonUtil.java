/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.util;

import com.neu.psa.irs.bean.DocumentBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Vineet
 */
public class CommonUtil {

//    public static StringBuffer getPDFText(File pdfFile) throws IOException {
//
//        StringBuffer sb = new StringBuffer(1024);
//
//        try {
//            PDFTextStream stream = new PDFTextStream(pdfFile);
//            // get OutputTarget configured to pipe text to the provided StringBuffer
//            OutputTarget tgt = OutputTarget.forBuffer(sb);
//            stream.pipe(tgt);
//            stream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return sb;
//    }
//    public static int getMapKey(HashMap<Integer, Integer> map, String value) {
//
//        int key = 0;
//
//        try {
//
//            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
//                if (e.getValue() == value) {
//                    key = e.getKey();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return key;
//        }
//
//    }
    public static ArrayList<DocumentBean> sortList(ArrayList<DocumentBean> arrayList) {

        Collections.sort(arrayList, new Comparator<DocumentBean>() {
            @Override
            public int compare(DocumentBean documentBean1, DocumentBean documentBean2) {
                if (documentBean1.getRank() == 0.0 || documentBean1.getRank() == 0.0) {
                    return 0;
                } else {
                    if (documentBean1.getRank() > documentBean2.getRank()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });

        return arrayList;

    }

}
