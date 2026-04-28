package com.route.algorithm;

import java.util.*;
import com.route.graph.Graph;
import com.route.model.Edge;
import com.route.model.Node;

public class Dijkstra {
    private static final int ALTERNATIVE_EDGE_PENALTY = 1_000;

    public static Map<Node, Integer> shortestDistance(Graph graph, Node source) {
        return runDijkstra(graph, source).dist;
    }

    public static List<Node> shortestPath(Graph graph, Node source, Node destination) {
        DijkstraState state = runDijkstra(graph, source);

        if (!state.dist.containsKey(destination) || state.dist.get(destination) == Integer.MAX_VALUE) {
            return Collections.emptyList();
        }

        LinkedList<Node> path = new LinkedList<>();
        Node current = destination;
        while (current != null) {
            path.addFirst(current);
            current = state.parent.get(current);
        }
        return path;
    }

    public static List<List<Node>> shortestPathsWithAlternative(Graph graph, Node source, Node destination) {
        List<List<Node>> routes = new ArrayList<>();
        List<Node> bestPath = shortestPath(graph, source, destination);
        if (bestPath.isEmpty()) {
            return routes;
        }
        routes.add(bestPath);

        List<Node> bestAlternative = Collections.emptyList();
        int bestAlternativeCost = Integer.MAX_VALUE;

        for (int i = 0; i < bestPath.size() - 1; i++) {
            Node from = bestPath.get(i);
            Node to = bestPath.get(i + 1);
            int originalWeight = graph.getEdgeWeight(from, to);
            if (originalWeight == Integer.MAX_VALUE) {
                continue;
            }

            graph.updateEdgeWeight(from, to, originalWeight + ALTERNATIVE_EDGE_PENALTY);
            List<Node> candidate = shortestPath(graph, source, destination);
            graph.updateEdgeWeight(from, to, originalWeight);

            if (candidate.isEmpty() || candidate.equals(bestPath)) {
                continue;
            }

            int candidateCost = calculatePathCost(graph, candidate);
            if (candidateCost < bestAlternativeCost) {
                bestAlternativeCost = candidateCost;
                bestAlternative = candidate;
            }
        }

        if (!bestAlternative.isEmpty()) {
            routes.add(bestAlternative);
        }

        return routes;
    }

    public static int calculatePathCost(Graph graph, List<Node> path) {
        if (path == null || path.size() < 2) {
            return 0;
        }

        int total = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int weight = graph.getEdgeWeight(path.get(i), path.get(i + 1));
            if (weight == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            total += weight;
        }
        return total;
    }

    private static DijkstraState runDijkstra(Graph graph, Node source) {
        Map<Node, Integer> dist = new HashMap<>();
        Map<Node, Node> parent = new HashMap<>();
        PriorityQueue<Map.Entry<Node, Integer>> pq =
                new PriorityQueue<>(Map.Entry.comparingByValue());

        // Initialize distances
        for (Node node : graph.getAllNodes()) {
            dist.put(node, Integer.MAX_VALUE);
            parent.put(node, null);
        }

        dist.put(source, 0);
        parent.put(source, null);
        pq.add(new AbstractMap.SimpleEntry<>(source, 0));

        while (!pq.isEmpty()) {
            Map.Entry<Node, Integer> current = pq.poll();
            Node currentNode = current.getKey();
            int currentDist = current.getValue();

            // Skip outdated entries
            if (currentDist > dist.get(currentNode)) continue;

            for (Edge edge : graph.getNeighbors(currentNode)) {
                Node neighbor = edge.getTo();
                int newDist = currentDist + edge.getWeight();

                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    parent.put(neighbor, currentNode);
                    pq.add(new AbstractMap.SimpleEntry<>(neighbor, newDist));
                }
            }
        }

        return new DijkstraState(dist, parent);
    }

    private static class DijkstraState {
        private final Map<Node, Integer> dist;
        private final Map<Node, Node> parent;

        private DijkstraState(Map<Node, Integer> dist, Map<Node, Node> parent) {
            this.dist = dist;
            this.parent = parent;
        }
    }
}
