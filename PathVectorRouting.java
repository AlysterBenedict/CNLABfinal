import java.util.*;

class PathVectorRouting {
    static class Node {
        int vertex;
        int distance;
        List<Integer> path;

        Node(int vertex, int distance, List<Integer> path) {
            this.vertex = vertex;
            this.distance = distance;
            this.path = new ArrayList<>(path);
        }
    }

    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static void bellmanFord(List<Edge> edges, int V, int start) {
        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < V; i++) {
            nodes.put(i, new Node(i, Integer.MAX_VALUE, new ArrayList<>()));
        }

        nodes.get(start).distance = 0;
        nodes.get(start).path.add(start);

        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                Node u = nodes.get(edge.source);
                Node v = nodes.get(edge.destination);
                if (u.distance != Integer.MAX_VALUE && u.distance + edge.weight < v.distance) {
                    v.distance = u.distance + edge.weight;
                    v.path = new ArrayList<>(u.path);
                    v.path.add(edge.destination);
                }
            }
        }

        for (Edge edge : edges) {
            Node u = nodes.get(edge.source);
            Node v = nodes.get(edge.destination);
            if (u.distance != Integer.MAX_VALUE && u.distance + edge.weight < v.distance) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        printPaths(nodes, V);
    }

    static void printPaths(Map<Integer, Node> nodes, int V) {
        for (int i = 0; i < V; i++) {
            Node node = nodes.get(i);
            System.out.print("Distance from source to " + i + " is " + node.distance + " via path: ");
            for (int vertex : node.path) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int V = 5; 
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, -1),
            new Edge(0, 2, 4),
            new Edge(1, 2, 3),
            new Edge(1, 3, 2),
            new Edge(1, 4, 2),
            new Edge(3, 2, 5),
            new Edge(3, 1, 1),
            new Edge(4, 3, -3)
        );

        bellmanFord(edges, V, 0);
    }
}
