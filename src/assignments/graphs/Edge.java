package assignments.graphs;

public class Edge {
    public final int src;
    public final int tgt;

    /**
     * Construct an immutable edge, indicating a source vertex and a target vertex.
     * @param src the source vertex label
     * @param tgt the target vertex label
     */
    public Edge(int src, int tgt) {
        this.src = src;
        this.tgt = tgt;
    }

    /**
     * Two edges are equal if their source and target match.
     * 
     * @param o - the object to compare to
     * @return true iff o is also an edge, with the same source and target
     */
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge other = (Edge)o;
        return this.src == other.src && this.tgt == other.tgt;
    }

    /**
     * A hashing function that respects the three criteria from java documentation:
     * 1. Consistent within a single program execution.
     * 2. Two equal edges give the same hash code.
     * 3. Two unequal edges give different hash codes as often as possible.
     * 
     * @return a hash
     */
    public int hashCode() {
        return 31 * src + tgt;
    }
}
