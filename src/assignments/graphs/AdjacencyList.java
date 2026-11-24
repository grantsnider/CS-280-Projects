package assignments.graphs;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class AdjacencyList {
    private List<List<Integer>> edges;

    /**
     * Construct an AdjacencyList representation of a graph with vertices V and edges E.
     * @param V a set of vertices, labeled with integers
     * @param E a set of edges
     * 
     * NOTE: In this assignment, the vertices will always be labeled from 0 up to n
     * 
     */
    public AdjacencyList(Set<Integer> V, Set<Edge> E) {
        int n = V.size();
        this.edges = new ArrayList<List<Integer>>(n);

        for (int i = 0; i < n; i ++) {
            this.edges.add(i, new ArrayList<Integer>());
        }

        for (Edge e: E) {
            this.edges.get(e.src).add(e.tgt);
        }
    }

    /**
     * Report whether or not this graph has a given edge.
     * @param e the Edge
     */
    public boolean hasEdge(Edge e) {
        return this.edges.get(e.src).contains(e.tgt);
    }

    /**
     * Construct an AdjacencyList representation of the graph G.
     * @param G the graph, as an AdjacencyMatrix
     */
    public AdjacencyList(AdjacencyMatrix G) {
        int n = G.getVertices().size();
        this.edges = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            this.edges.add(new ArrayList<Integer>());
        }
        for (Edge e : G.getEdges()) {
            this.edges.get(e.src).add(e.tgt);
        }
    }

    /**
     * Return a set of vertices in this graph.
     * 
     * Each vertex is represented by an integer.
     * 
     * @return Set<Integer>
     */
    public Set<Integer> getVertices() {
        Set<Integer> vertices = new HashSet<>();
        for (int i = 0; i < edges.size(); i++) {
            vertices.add(i);
        }
        return vertices;
    }

    /**
     * Return a set of Edges in this graph.
     * @return Set<Edge>
     */
    public Set<Edge> getEdges() {
        Set<Edge> result = new HashSet<>();
        for (int src = 0; src < edges.size(); src++) {
            for (Integer tgt : edges.get(src)) {
                result.add(new Edge(src, tgt));
            }
        }
        return result;
    }
}
