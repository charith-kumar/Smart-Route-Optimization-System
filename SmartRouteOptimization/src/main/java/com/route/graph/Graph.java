package com.route.graph;

import java.util.*;
import com.route.model.Edge;
import com.route.model.Node;

public class Graph {
    private Map<Node, List<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addNode(Node node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to, int weight) {
        adjList.get(from).add(new Edge(to, weight));
    }

    public boolean updateEdgeWeight(Node from, Node to, int newWeight) {
        List<Edge> edges = adjList.getOrDefault(from, Collections.emptyList());
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.getTo().equals(to)) {
                edges.set(i, new Edge(to, newWeight));
                return true;
            }
        }
        return false;
    }

    public int getEdgeWeight(Node from, Node to) {
        List<Edge> edges = adjList.getOrDefault(from, Collections.emptyList());
        for (Edge edge : edges) {
            if (edge.getTo().equals(to)) {
                return edge.getWeight();
            }
        }
        return Integer.MAX_VALUE;
    }

    public List<Edge> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Set<Node> getAllNodes() {
        return adjList.keySet();
    }
}
