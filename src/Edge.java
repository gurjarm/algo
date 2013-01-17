// File: Edge.java
// Package: delftalization
//
// Copyright (c) 2011 Michiel Meijer


/**
 * This class represents an edge of a Graph.
 * author  Michiel Meijer <m.meijer@cinis.com>
 * @version 2011.0327
 * @since   1.6
 */
public class Edge {
         /* 
     * The maximum flow capacity this edge can take.
     */
protected Integer capacity;
/**
     * The flow currently going through this edge
     */
protected Integer flow;
/**
     * The left or source node for this edge
     */
protected Node left;
/**
     * The right or destination node for this edge
     */
protected Node right;

/**
     * Edge constructor, sets the edge class variables to sensible values.
     *
     * @param l     Left hand or source node for this edge
     * @param r     Right hand or destination node for this edge
     * @param c     Capacity of this edge
     */
    public Edge(Node l, Node r, Integer c) {
        capacity=c;
        left=l;
        right=r;
        flow=0;
    }
 
    /**
     * Queries the capacity of this edge
     * @return the value of the class variable capacity
     */
    public Integer getCapacity() {
        return capacity;
    }
 
    /**
     * Queries the flow of this edge
     * @return the value of the class variable flow
     */
    public Integer getFlow() {
        return flow;
    }
 
    /**
     * Queries the amount by which the flow on this edge can be increased
     * @return the amount by which the flow on this edge can be increased
     */
    public Integer getAvailable() {
        return capacity-flow;
    }
 
    /**
     * Augment the flow on this edge. This can either increase the flow
     * (positive augment) or decrease the flow (negative augment)
     * @param a the amount by which to augment the flow
     */
    public void augment(Integer a) {
        flow=flow+a;
    }
 
    /**
     * Queries the value of the class variable left
     * @return the value of the class variable left
     */
    public Node leftNode() {
        return left;
    }
 
    /**
     * Queries the value of the class variable right
     * @return the value of the class variable right
     */
    public Node rightNode() {
        return right;
    }
}