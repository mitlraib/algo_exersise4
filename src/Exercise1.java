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

    public List<Integer> getOddDegreeVertices(List<Edge> mst) {
        int[] degree = new int[V];
        for (Edge e : mst) {
            degree[e.src]++;
            degree[e.dest]++;
        }

        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (degree[i] % 2 == 1) odds.add(i);
        }
        return odds;
    }

    public List<Edge> getMinimumMatching(List<Integer> odds) {
        List<Edge> matching = new ArrayList<>();
        boolean[] used = new boolean[V];

        for (int i = 0; i < odds.size(); i++) {
            if (used[odds.get(i)]) continue;

            int u = odds.get(i), minW = Integer.MAX_VALUE, minV = -1;
            for (int j = i + 1; j < odds.size(); j++) {
                int v = odds.get(j);
                if (!used[v]) {
                    int w = getEdgeWeight(u, v);
                    if (w < minW) {
                        minW = w;
                        minV = v;
                    }
                }
            }

            if (minV != -1) {
                matching.add(new Edge(u, minV, minW));
                used[u] = used[minV] = true;
            }
        }

        return matching;
    }

    private int getEdgeWeight(int u, int v) {
        for (Edge e : edges) {
            if ((e.src == u && e.dest == v) || (e.src == v && e.dest == u))
                return e.weight;
        }
        return Integer.MAX_VALUE;
    }
}



