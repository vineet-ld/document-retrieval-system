/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.controller;

import com.neu.psa.irs.bean.DocumentBean;
import com.neu.psa.irs.dao.AdminDao;
import com.neu.psa.irs.util.CommonUtil;
import com.neu.psa.irs.util.PageRankUtil;
import com.neu.psa.irs.util.SearchUtil;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vineet
 */
@Controller
public class MainController {

    private String viewName = "";

    @RequestMapping(value = "/search.htm", method = RequestMethod.GET)
    public String login(Model model) {

        try {

            viewName = "search";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = "/search.htm", method = RequestMethod.POST)
    public String search(Model model, HttpServletRequest request) {

        try {

            String query = request.getParameter("query");

            if (!query.equals("")) {

                query = query.replaceAll("\n", " ");
                query = query.replaceAll("\r", " ");
                query = query.replaceAll("\t", " ");
                String[] queryParts = query.split(" ");
//                AdminDao adminDao = new AdminDao();
//                ArrayList<DocumentBean> documentList = adminDao.getDocuments();
//                PageRankUtil pageRankUtil = new PageRankUtil();
//                for (DocumentBean documentBean : documentList) {
//                    documentBean.setRank(pageRankUtil.getPageRank(query, documentBean));
//                }
//                documentList = CommonUtil.sortList(documentList);
//
//                model.addAttribute("documentList", documentList);

                AdminDao adminDao = new AdminDao();
                ArrayList<Integer> documents = SearchUtil.fullTextSearch(queryParts);
                ArrayList<Integer> documents2 = SearchUtil.keywordSearch(queryParts);
                ArrayList<DocumentBean> documentList = new ArrayList<>();
                HashSet<Integer> set = new HashSet<>();
                set.addAll(documents);
                set.addAll(documents2);
                for (int d : set) {
                    documentList.add(adminDao.getDocument(d));
                }
                viewName = "result";
                model.addAttribute("documentList", documentList);

            } else {
                viewName = "search";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

}
