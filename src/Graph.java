// File: Graph.java
// Package: delftalization
//
// Copyright (c) 2011 Michiel Meijer

import java.util.*;
 
/**
 * This is a basic implementation of a graph with a Ford-Fulkerson algorithm
 * to find the minimal cut and print the result.
 *
 * author  Michiel Meijer <m.meijer@cinis.com>
 * @version 2011.0327
 * @since   1.6
 */
public class Graph {
    /**
     * The graph source (or s) node.
     */
    protected Node source;
    /**
     * The graph sink (or t) node.
     */
    protected Node sink;
    /**
     * A list of all nodes in the graph.
     */
    protected ArrayDeque<Node> nodes;
    /**
     * A list of all edges in the graph.
     */
    protected ArrayDeque<Edge> edges;
    /**
     * A name to node mapping.
     */
    protected HashMap<String,Node> names;
    /**
     * A queue used in breadth first searches. Placed here in the global
     * scope of the class to avoid having to re-initialise it each time
     * a search is performed.
     */
    protected ArrayDeque<Node> queue;
 
    /**
     * Constructor for the Graph class. Initialises all variables defined
     * in the global class scope to a sensible value, with the exception
     * of the queue which is initialised at the last moment so that it
     * has the same number of elements as the node list has (which can
     * grow dynamically).
     */
    public Graph() {
        // Setup nodes
        nodes=new ArrayDeque<Node>();
        source=new Node("source"); // Not on hash to avoid input clashes
        nodes.add(source);
        sink=new Node("sink"); // Not on hash to avoid input clashes
        nodes.add(sink);
 
        // Setup Edges and Names, the queue is not setup untill needed
        edges=new ArrayDeque<Edge>();
        names=new HashMap<String,Node>();
    }
 
    /**
     * Given a name , profit and cost of a technology a new graph node
     * is made with the given names and an edge to the source with a capacity
     * equal to the profit and an edge to the sink with a capacity equal to
     * the cost. If either the cost or the profit is 0 or less then the
     * respective edges are not made. The edges themselves are added to the
     * graph edge list and the edge lists of the respective nodes. Finally
     * a cash mapping node names to nodes is maintained and updated.
     *
     * @param name      Name for the new technology node
     * @param profit    Profit for the new technology
     * @param cost      Cost for the new technology
     * @throws IllegalArgumentException Thrown when trying to add a technology
     *                                  which is already present.
     */
    public void addTechnology(String name, Integer profit, Integer cost)
                                            throws IllegalArgumentException {
        Node n;
        Edge e;
 
        if (names.containsKey(name))
            throw new IllegalArgumentException(
                    "Attempted to redefine existing technology.");
 
        n=new Node(name);
        nodes.add(n);
        names.put(name,n);
 
        if (profit>0) { // connect to source
            e=new Edge(source,n,profit);
            edges.add(e);
            source.addEdge(e);
            n.addEdge(e);
        }
 
        if (cost>0) { // connect to sink
            e=new Edge(n,sink,cost);
            edges.add(e);
            n.addEdge(e);
            sink.addEdge(e);
        }
    }
 
    /**
     * Add a dependency between two previously initialised technologies. This
     * is achieved by adding an edge with (near) infinite capacity
     * (Integer.MAX_VALUE). The edge is added to the edge lists of the graph and
     * the nodes.
     *
     * @param from      Dependant technology
     * @param to        Required technology
     * @throws NullPointerException Thrown when either from or to parameter
     *                              are unknown technologies (not previously
     *                              initialised).
     */
    public void addDependency(String from, String to)
                                                throws NullPointerException {
        Node f,t;
        Edge e;
 
        f=names.get(from);
        t=names.get(to);
        if (t==null || f==null)
            throw new NullPointerException(
                    "Attempted to connect undefined technologies.");
 
        e=new Edge(f,t,Integer.MAX_VALUE); // MAX_VALUE instead of infinity
        edges.add(e);
        f.addEdge(e);
        t.addEdge(e);
    }
 
    /**
     * Reset all nodes of this graph by setting their visited flag to false
     * thus preparing them for a breadth first search. Theoretically this
     * routine should also initialise the source node to appropriate values,
     * but aside from the visited flag this is already done by the node
     * constructor.
     */
    protected void resetNodes() {
        Iterator<Node> nIter;
        Node n;
 
        nIter=nodes.iterator();
        while(nIter.hasNext()) {
            n=nIter.next();
            n.reset();
        }
 
        // Other values of the source node need not be changed as
        // defined in the original algorithm because these values are
        // already correctly set upon node creation.
        source.visit();
    }
 
    /**
     * We have an edge which may allow for additional (forward) flow. Here
     * it is checked out and if it can generate additional forward flow
     * it is added to the queue. If the node on the right hand
     * side of the edge is the sink, we have found a path and return the value
     * for the maximum flow of the path. If the node is not the sink, a
     * value of 0 is returned.
     *
     * @param u     Left hand node of the edge
     * @param e     Edge which may provide extra flow
     * @return      0 Except if there is extra flow and it leads to the sink
     *              node, which means we have found an augmenting path, in
     *              which case the maximum flow of the augmenting path is
     *              returned.
     */
    protected Integer flowForward(Node u, Edge e) {
        Node v; // general purpose node
 
        v=e.rightNode(); // the other side of the edge
 
        // If the other side is not reached yet AND we have capacity to get
        // there, make it so
        if (!v.getVisited() && e.getAvailable()>0) {
            // Set augment to the maximum feasable flow for this path
            v.setAugment(Math.min(u.getAugment(),e.getAvailable()));
            v.visit();
            v.setPrevious(e); // keep track of the path
            if (v==sink) return v.getAugment();
            queue.addLast(v);
        } // if (!v.getVisited() && e.getAvailable()>0) {
 
        // sink not reached yet
        return 0;
    }
 
    /**
     * We have an edge which may allow for flow to be pushed back. Here it is
     * checked if a push back would work. If the node on the left hand side
     * of the edge is the sink (how could this happen ?) a augmentable path has
     * been found and this routine returns the maximum size of the augment
     * flow. 0 is returned otherwise
     *
     * @param u     Right hand side of the edge
     * @param e     Edge which may allow flow to be pushed back
     * @return      0 Except if we can push back flow to the sink
     *              (how?), which would mean we have found an augmenting path.
     *              In this last case the maximum flow of the augmenting path
     *              is returned.
     */
    protected Integer flowBackward(Node u, Edge e) {
        Node v; //general purpose node
 
        v=e.leftNode(); // the other side of the edge
 
        // If the other side is not reached yet AND we have flow to push back
        // make it so
        if (!v.getVisited() && e.getFlow()>0) {
            // Set augment to the maximum feasable flow for this path
            v.setAugment(Math.min(u.getAugment(),e.getFlow()));
            v.visit();
            v.setPrevious(e); // keep track of the path
            if (v==sink) return v.getAugment();
            queue.addLast(v);
        } // if (!v.getVisited() && e.getFlow()>0)
 
        // sink not reached yet
        return 0;
    }
    /**
     * Finds an augmenting path in the graph using a breadth first search.
     * It also records the path into the nodes.
     *
     * @return The maximum flow for the augmenting path
     */
    protected Integer findPath() {
        Iterator<Edge> eIter; // general putpose edge iterator
        Integer augment; // Keep track of possible augmenting paths
        Edge e; // general purpose edeg
        Node u; // genral purpose node 
 
        // Prepare the graph for traversal
        resetNodes();
        queue.clear();
        queue.addLast(source);
 
        // Do a breadth first search
        augment=0; // the loop will return if augment!=0
        while(!queue.isEmpty()) {
            u=queue.removeFirst();
            eIter=u.getEdges();
            while(eIter.hasNext()) {
                e=eIter.next();
                if (e.leftNode()==u) { // outgoing edge of u
                    augment=flowForward(u,e); // augment flow forward
                } else { // incoming edge of u
                    augment=flowBackward(u,e); // augment flow backward
                } // if (e.left==n)
                // If we find an augmentable path to the sink, return here
                if (augment!=0) return augment;
            }
        }
 
        // No augmentable path found.
        return 0;
    }
 
    /**
     * Using the result stored in the nodes by the findPath() procedure, this
     * updates the found path with the augmenting flow.
     *
     * @param augmentation  the size of the augmenting flow.
     */
    protected void augmentPath(Integer augmentation) {
        Node p; // general purpose node iteartor
        Edge e; // general purpose edge
 
        // retrace the Path starting at the sink
        p=sink;
        while(p!=source) {
            e=p.getPrevious();
            if (e.leftNode()==p) { // outgoing edge on p, incoming on previous
                e.augment(-augmentation); // push flow back
                p=e.rightNode();
            } else { // incoming edge on p, outgoing on previous
                e.augment(augmentation); // increase flow
                p=e.leftNode();
            } // if (e.leftNode()==p)
        }
    }
 
    /**
     * Using the results stored in the node by the procedure findPath() this
     * procedure calculates the revenue belonging to the minimal cut.
     */
    protected void printRevenue() {
        Iterator<Edge> eIter; // Edge iterator
        Integer c; // capaicty of minmal cut
        Integer p; // sum of all profits
        Edge e; // general purpose edge
 
        // Calculate the capacity of the minimal cut
        eIter=edges.iterator();
        c=0;
        while(eIter.hasNext()) {
            e=eIter.next();
            if (e.leftNode().getVisited() && !e.rightNode().getVisited()) {
                // This edge is leaving the minmal cut
                c=c+e.getCapacity();
            }
        }
 
        // Calculate the sum of all profits
        eIter=source.getEdges();
        p=0;
        while(eIter.hasNext()) {
            e=eIter.next();
            p=p+e.getCapacity();
        }
 
        System.out.print(p-c); // Print the result
    }
 
    /**
     * Using the results stored into the nodes by the findPath procedure this
     * procedure prints the names of all nodes which are part of the minimal
     * cut with exception of the source node name.
     */
    protected void printChosen() {
        Iterator<Node> nIter; // iterator for nodes
        Node n; // general purpose node
 
        nIter=nodes.iterator();
        while(nIter.hasNext()) {
            n=nIter.next();
            if (n.getVisited() && n!=source) // is node techn. in minimal cut?
                System.out.print(" "+n.getName()); // Print name with lead space
        }
    }
 
    /**
     * Apply a Ford-Fulkerson algorithm to this graph an print the resulting
     * maximum revenue and names of the nodes in the minimal cut.
     */
    public void optimise() {
        Integer augmentation; // value of possible augmentation
 
        // Provide a fifo queue with appropriate size
        queue=new ArrayDeque<Node>(nodes.size());
 
        // Augment path untill no augmentations can be made anymore
        while((augmentation=findPath())!=0){
            augmentPath(augmentation);
        }
 
        // Process the results; only those nodes have been visited in the last
        // findPath() which are still connected to the source. They form per
        // definition the minimal cut.
        printRevenue();
        printChosen();
        System.out.println(""); // end with a newline
    }
}