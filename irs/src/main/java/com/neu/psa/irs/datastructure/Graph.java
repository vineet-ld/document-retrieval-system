/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.psa.irs.datastructure;

import java.util.ArrayList;

/**
 *
 * @author Vineet
 * @param <E>
 */
public final class Graph<E> {

    private int size;
    private ArrayList<Node> vertices;

    public Graph() {
        size = 0;
        vertices = new ArrayList<Node>();
    }

    public class Node {

        public E value;
        private ArrayList<Node> edges;
        public int count;

        public Node(E object) {
            count = 0;
            value = object;
            edges = new ArrayList<Node>();
        }

    }

    public Node addVertex(E object) {

        Node node = null;
        
        try {

            node = new Node(object);
            vertices.add(node);
            size++;

        } catch (Exception e) {
            throw e;
        }
        return node;

    }

    public void addEdge(Node fromVertex, Node toVertex) throws Exception {

        try {

            if (!vertices.contains(fromVertex) || !vertices.contains(toVertex)) {
                throw new Exception("No such vertex exists in the graph");
            } else {
                fromVertex.edges.add(toVertex);
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public int getSize() {
        return size;
    }

    public ArrayList<Node> getVertices() {
        return vertices;
    }

    public ArrayList<Node> getAdjacentVertices(Node node) throws Exception {
        try {

            if (!vertices.contains(node)) {
                throw new Exception("No such vertex exists in the graph");
            } else {
                return node.edges;
            }

        } catch (Exception e) {
            throw e;
        }
    }
    
    public E getValue(Node node) throws Exception {
        try {

            if (!vertices.contains(node)) {
                throw new Exception("No such vertex exists in the graph");
            } else {
                return node.value;
            }

        } catch (Exception e) {
            throw e;
        }
    }

}
