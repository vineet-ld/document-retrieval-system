/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.servlet;

import com.neu.psa.irs.util.Constants;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author Vineet
 */
public class CustomServlet extends DispatcherServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        try {

            super.init(config);

            Constants.INVERTED_INDEX = new HashMap<>();
            Constants.CONNECTION_INDEX = new HashMap<>();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
