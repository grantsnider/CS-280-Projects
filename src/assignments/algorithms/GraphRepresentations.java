package assignments.algorithms;

import java.util.HashSet;
import java.util.Set;
import assignments.graphs.AdjacencyList;
import assignments.graphs.AdjacencyMatrix;
import assignments.graphs.Edge;

public class GraphRepresentations {
    /**
    * Run validation tests.
    * @param args command-line args.
    */
    public static void main(String[] args) {
        Set<Integer> V = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            V.add(i);
        }
        Set<Edge> E = new HashSet<>();
        E.add(new Edge(0, 1)); E.add(new Edge(0, 2)); E.add(new Edge(1, 3)); E.add(new Edge(2, 3)); E.add(new Edge(3, 4));
        AdjacencyList list = new AdjacencyList(V, E);
        AdjacencyMatrix matrix = new AdjacencyMatrix(V, E);

        assert list.hasEdge(new Edge(0, 1));
        assert matrix.hasEdge(new Edge(0, 1));
        assert !list.hasEdge(new Edge(4, 0));
        assert !matrix.hasEdge(new Edge(4, 0));

        AdjacencyMatrix fromList = new AdjacencyMatrix(list);
        assert fromList.hasEdge(new Edge(0, 1));
        assert fromList.hasEdge(new Edge(3, 4));

        AdjacencyList fromMatrix = new AdjacencyList(matrix);
        assert fromMatrix.hasEdge(new Edge(0, 2));
        assert fromMatrix.hasEdge(new Edge(1, 3));

        assert list.getVertices().equals(V);
        assert matrix.getVertices().equals(V);

        assert list.getEdges().equals(E);
        assert matrix.getEdges().equals(E);
        
        System.out.println("Graph Representations passes all tests.");   
    }
}

//java -cp lib/* -ea src/assignments/algorithms/GraphRepresentations.java
