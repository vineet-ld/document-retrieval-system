/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.datastructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vineet
 */
public class Index {

    private HashMap<String, Graph<ArrayList<Integer>>.Node> table;

    public Index() {
        table = new HashMap<>();
    }

    public HashMap<String, Graph<ArrayList<Integer>>.Node> getTable() {
        return table;
    }

    public void setTable(HashMap<String, Graph<ArrayList<Integer>>.Node> table) {
        this.table = table;
    }

}
