/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.dao;

import com.neu.psa.irs.bean.DocumentBean;
import com.neu.psa.irs.config.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vineet
 */
@Repository
public class AdminDao {

    DBConfig dbc = new DBConfig();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public DocumentBean addDocument(DocumentBean document) {

        StringBuffer query = new StringBuffer();

        try {
            con = dbc.getConnection();
            query.append("CALL addDocument(?) ");
            pst = con.prepareStatement(query.toString());
            pst.setString(1, document.getName());
            rs = dbc.executePreparedQuery(con, pst);
            while (rs.next()) {
                document.setId(rs.getInt("docId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return document;
        }

    }

    public int getTotalDocuments() {

        StringBuffer query = new StringBuffer();
        int count = 0;

        try {
            con = dbc.getConnection();
            query.append("SELECT COUNT(*) FROM document");
            pst = con.prepareStatement(query.toString());
            rs = dbc.executePreparedQuery(con, pst);
            while (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return count;
        }

    }

    public void setWordCount(int documentId, int count) {

        StringBuffer query = new StringBuffer();

        try {
            con = dbc.getConnection();
            query.append("UPDATE document SET count = ? where id = ?");

            pst = con.prepareStatement(query.toString());
            pst.setInt(1, count);
            pst.setInt(2, documentId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public int getWordCount(int documentId) {

        StringBuffer query = new StringBuffer();
        int count = 0;

        try {
            con = dbc.getConnection();
            query.append("SELECT count FROM document WHERE id = ?");

            pst = con.prepareStatement(query.toString());
            pst.setInt(1, documentId);
            rs = dbc.executePreparedQuery(con, pst);
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return count;
        }

    }

    public double getAvgDocumentLength() {

        StringBuffer query = new StringBuffer();
        double avg = 0.0;

        try {
            con = dbc.getConnection();
            query.append("SELECT AVG(count) FROM document");
            pst = con.prepareStatement(query.toString());
            rs = dbc.executePreparedQuery(con, pst);
            while (rs.next()) {
                avg = rs.getInt("AVG(count)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return avg;
        }

    }

    public ArrayList<DocumentBean> getDocuments() {

        StringBuffer query = new StringBuffer();
        ArrayList<DocumentBean> documentList = new ArrayList<>();
        DocumentBean documentBean = null;

        try {
            con = dbc.getConnection();
            query.append("SELECT * FROM document");
            pst = con.prepareStatement(query.toString());
            rs = dbc.executePreparedQuery(con, pst);
            while (rs.next()) {
                documentBean = new DocumentBean();
                documentBean.setId(rs.getInt("id"));
                documentBean.setName(rs.getString("name"));
                documentBean.wordCount = rs.getInt("count");
                documentList.add(documentBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return documentList;
        }

    }
    
    public DocumentBean getDocument(int documentId) {

        StringBuffer query = new StringBuffer();
        DocumentBean documentBean = new DocumentBean();

        try {
            con = dbc.getConnection();
            query.append("SELECT * FROM document where id = ?");
            pst = con.prepareStatement(query.toString());
            pst.setInt(1, documentId);
            rs = dbc.executePreparedQuery(con, pst);
            while (rs.next()) {
                documentBean = new DocumentBean();
                documentBean.setId(rs.getInt("id"));
                documentBean.setName(rs.getString("name"));
                documentBean.wordCount = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return documentBean;
        }

    }

}
