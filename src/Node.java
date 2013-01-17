// File: Node.java
// Package: delftalization
//
// Copyright (c) 2011 Michiel Meijer
 
import java.util.*;
 
/**
 * Place holder class describing a graph node.
 *
 * author  Michiel Meijer <m.meijer@cinis.com>
 * @version 2011.0327
 * @since   1.6
 */
public class Node {
    /**
     * Temporary storage of the maximum augment flow for the augment path this
     * node is part of up until this node.
     */
    protected Integer augment;
    /**
     * All the edges which list this node (either as left or right hand side)
     */
    protected ArrayDeque<Edge> edges;
    /**
     * The name of this node
     */
    protected String name;
    /**
     * The edge leading to the previous node in the augment path this node is
     * a part of.
     */
    protected Edge previous;
    /**
     * Indicator flag that this node has already been processed by a breadth
     * first search and should not be processed again.
     */
    protected boolean visited;
 
    /**
     * Constructor for the class node. Sets all class variables to sensible
     * starting values. Most notable the augment value is set to infinite
     * (Integer.Max_VALUE)
     * @param n
     */
    public Node(String n){
        name=n;
        augment=Integer.MAX_VALUE;
        edges=new ArrayDeque<Edge>();
        previous=null;
        visited=false;
    }
 
    /**
     * Read the augment class variable
     * @return the value of augment
     */
    public Integer getAugment() {
        return augment;
    }
 
    /**
     * Set the augment class variable
     * @param a the new value of augment
     */
    public void setAugment(Integer a) {
        augment=a;
    }
 
    /**
     * Add an edge to this node
     * @param e edge to be added
     */
    public void addEdge(Edge e) {
        edges.add(e);
    }
 
    /**
     * Requests an iterator for all edges connected to this node
     * @return  iterator over all edges connected to this node
     */
    public Iterator<Edge> getEdges() {
        return edges.iterator();
    }
 
    /**
     * Produces the name of this node
     * @return the name of this node
     */
    public String getName() {
        return name;
    }
 
    /**
     * Queries the value of the class variable previous
     * @return the value of the class variable previous
     */
    public Edge getPrevious() {
        return previous;
    }
 
    /**
     * Set the value of the class variable previous
     * @param e the value the class variable previous is set to
     */
    public void setPrevious(Edge e) {
        previous=e;
    }
 
    /**
     * Query the flag visited
     * @return the value of the flag visited
     */
    public boolean getVisited() {
        return visited;
    }
 
    /**
     * Set the visited flags to true
     */
    public void visit() {
        visited=true;
    }
 
    /**
     * Set the visited flag to false
     */
    public void reset() {
        visited=false;
    }
}