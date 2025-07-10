import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int u, int v, int w) {
        src = u;
        dest = v;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class Graph {
    int V;
    List<Edge> edges = new ArrayList<>();

    public Graph(int V) {
        this.V = V;
    }

    public void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
    }

    public List<Edge> primMST() {
        boolean[] visited = new boolean[V];
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        visited[0] = true;
        for (Edge e : edges) {
            if (e.src == 0 || e.dest == 0) pq.add(e);
        }

        while (!pq.isEmpty() && mst.size() < V - 1) {
            Edge e = pq.poll();
            if (visited[e.src] && visited[e.dest]) continue;

            mst.add(e);
            int newNode = visited[e.src] ? e.dest : e.src;
            visited[newNode] = true;

            for (Edge edge : edges) {
                if ((edge.src == newNode && !visited[edge.dest]) ||
                        (edge.dest == newNode && !visited[edge.src])) {
                    pq.add(edge);
                }
            }
        }

        return mst;
    }
