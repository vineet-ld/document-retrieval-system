/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.util;

import com.neu.psa.irs.datastructure.Graph;
import com.neu.psa.irs.datastructure.Index;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vineet
 */
public class Constants {

    public static final String UPLOAD_PATH = "C:\\Users\\vinee_000\\OneDrive\\Vineet\\Courses\\Program Structure & Algorithms\\Project\\code\\irs\\src\\main\\webapp\\data";
    //public static Index INVERTED_INDEX = new Index();
   // public static Graph<ArrayList<Integer>> GRAPH = new Graph<>();
    
    public static HashMap<String, HashMap<Integer, Integer>> INVERTED_INDEX;
    public static HashMap<String, HashMap<String, ArrayList<Integer>>> CONNECTION_INDEX;

}
