import java.util.List;


public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1, 3); // A-B
        g.addEdge(0, 2, 5); // A-C
        g.addEdge(1, 3, 4); // B-D
        g.addEdge(2, 3, 2); // C-D

        List<Edge> mst = g.primMST();
        System.out.println("MST edges:");
        for (Edge e : mst) {
            System.out.println(e.src + " - " + e.dest + " = " + e.weight);
        }

        List<Integer> oddVertices = g.getOddDegreeVertices(mst);
        System.out.println("Odd degree vertices: " + oddVertices);

        List<Edge> matching = g.getMinimumMatching(oddVertices);
        System.out.println("Matching edges:");
        for (Edge e : matching) {
            System.out.println(e.src + " - " + e.dest + " = " + e.weight);
        }

        int totalWeight = 0;
        for (Edge e : mst) totalWeight += e.weight;
        for (Edge e : matching) totalWeight += e.weight;
        System.out.println("Total weight (MST + matching): " + totalWeight);
    }

}
