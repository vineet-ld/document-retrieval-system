/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.controller;

import com.neu.psa.irs.bean.DocumentBean;
import com.neu.psa.irs.dao.AdminDao;
import com.neu.psa.irs.util.Constants;
import com.neu.psa.irs.util.StemmerUtil;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Vineet
 */
@Controller
public class AdminController {

    @Autowired
    DocumentBean documentBean;

    @Autowired
    AdminDao adminDao;

    private String viewName = "";

    @RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
    public String login(Model model) {

        try {

            viewName = "admin";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }

    }

    @RequestMapping(value = "/admin/upload.htm", method = RequestMethod.POST)
    public String upload(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {

        try {

            if (!file.isEmpty()) {

                String name = Constants.UPLOAD_PATH + "/" + file.getOriginalFilename();

                documentBean = new DocumentBean();
                documentBean.setName(file.getOriginalFilename());
                documentBean.setSize(file.getSize());
                documentBean.setFile(new File(name));
                documentBean = adminDao.addDocument(documentBean);

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(documentBean.getFile()));
                stream.write(bytes);
                stream.close();

                String[] content = null;

                BufferedReader bufferedReader = new BufferedReader(new FileReader(documentBean.getFile()));
                StringBuffer contentBuffer = new StringBuffer();
                String line = bufferedReader.readLine();
                while (line != null) {
                    contentBuffer.append(line).append(" ");
                    line = bufferedReader.readLine();
                }

                content = contentBuffer.toString().split("\n");

                HashMap<Integer, Integer> documentMap = null;
                HashMap<String, ArrayList<Integer>> connectionMap = null;
                ArrayList<Integer> documents = null;

                for (int i = 0; i < content.length; i++) {

                    String[] content2 = content[i].split("\r");
                    for (int j = 0; j < content2.length; j++) {

                        String[] content3 = content2[j].split(" ");
                        for (int k = 0; k < content3.length; k++) {

                            if (!content3[k].equals("")) {

                                StemmerUtil stemmerUtil = new StemmerUtil();
                                String word = stemmerUtil.stripAffixes(content3[k]);
                                documentBean.wordCount++;
                                if (Constants.INVERTED_INDEX.containsKey(word)) {

                                    documentMap = Constants.INVERTED_INDEX.get(word);
                                    if (documentMap.containsKey(documentBean.getId())) {
                                        int count = documentMap.get(documentBean.getId());
                                        documentMap.put(documentBean.getId(), ++count);
                                    } else {
                                        documentMap.put(documentBean.getId(), 1);
                                    }

                                } else {

                                    documentMap = new HashMap<>();
                                    documentMap.put(documentBean.getId(), 1);

                                    Constants.INVERTED_INDEX.put(word, documentMap);

                                }
                                if (k > 0) {
                                    if (!content3[k - 1].equals("")) {
                                        String previousWord = stemmerUtil.stripAffixes(content3[k - 1]);

                                        if (Constants.CONNECTION_INDEX.containsKey(previousWord)) {

                                            connectionMap = Constants.CONNECTION_INDEX.get(previousWord);
                                            if (connectionMap.containsKey(word)) {

                                                documents = connectionMap.get(word);
                                                if (!documents.contains(documentBean.getId())) {
                                                    documents.add(documentBean.getId());
                                                }

                                            } else {

                                                documents = new ArrayList<>();
                                                documents.add(documentBean.getId());
                                                connectionMap.put(word, documents);

                                            }

                                        } else {

                                            connectionMap = new HashMap<>();
                                            documents = new ArrayList<>();
                                            documents.add(documentBean.getId());
                                            connectionMap.put(word, documents);
                                            Constants.CONNECTION_INDEX.put(previousWord, connectionMap);

                                        };
                                    }
                                }
                            }
                        }
                    }
                }
                adminDao.setWordCount(documentBean.getId(), documentBean.wordCount);
                documentBean.wordCount = 0;

            }
            viewName = "admin";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }

    }

}
