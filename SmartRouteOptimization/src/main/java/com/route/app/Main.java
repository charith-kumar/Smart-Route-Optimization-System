package com.route.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.route.algorithm.Dijkstra;
import com.route.graph.Graph;
import com.route.model.Node;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Map<String, Node> nodesByName = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int nextNodeId = 1;

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1: {
                    String fromName = readNodeName(scanner, "Enter source node name: ");
                    String toName = readNodeName(scanner, "Enter destination node name: ");
                    int weight = readInt(scanner, "Enter road weight: ");

                    Node fromNode = getOrCreateNode(graph, nodesByName, fromName, nextNodeId);
                    if (fromNode.getId() == nextNodeId) {
                        nextNodeId++;
                    }

                    Node toNode = getOrCreateNode(graph, nodesByName, toName, nextNodeId);
                    if (toNode.getId() == nextNodeId) {
                        nextNodeId++;
                    }

                    graph.addEdge(fromNode, toNode, weight);
                    System.out.println("Road added: " + fromName + " -> " + toName + " (weight " + weight + ")");
                    break;
                }
                case 2: {
                    String fromName = readNodeName(scanner, "Enter source node name: ");
                    String toName = readNodeName(scanner, "Enter destination node name: ");
                    int newWeight = readInt(scanner, "Enter new traffic weight: ");

                    Node fromNode = nodesByName.get(fromName);
                    Node toNode = nodesByName.get(toName);
                    if (fromNode == null || toNode == null) {
                        System.out.println("One or both nodes do not exist. Add roads first.");
                        break;
                    }

                    boolean updated = graph.updateEdgeWeight(fromNode, toNode, newWeight);
                    if (!updated) {
                        System.out.println("Road not found. Add the road first using option 1.");
                        break;
                    }
                    System.out.println("Traffic updated: " + fromName + " -> " + toName + " now has weight " + newWeight);
                    break;
                }
                case 3: {
                    String sourceName = readNodeName(scanner, "Enter source node name: ");
                    String destinationName = readNodeName(scanner, "Enter destination node name: ");

                    Node source = nodesByName.get(sourceName);
                    Node destination = nodesByName.get(destinationName);
                    if (source == null || destination == null) {
                        System.out.println("One or both nodes do not exist. Add roads first.");
                        break;
                    }

                    printRoutes(graph, source, destination, "Shortest path result");
                    break;
                }
                case 4:
                    running = false;
                    System.out.println("Exiting Smart Route Optimization CLI.");
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1, 2, 3, or 4.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Smart Route Optimization ===");
        System.out.println("1. Add Road");
        System.out.println("2. Update Traffic");
        System.out.println("3. Find Shortest Path");
        System.out.println("4. Exit");
    }

    private static Node getOrCreateNode(Graph graph, Map<String, Node> nodesByName, String name, int nodeId) {
        Node existing = nodesByName.get(name);
        if (existing != null) {
            return existing;
        }

        Node created = new Node(nodeId, name);
        nodesByName.put(name, created);
        graph.addNode(created);
        return created;
    }

    private static String readNodeName(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Node name cannot be empty.");
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private static void printRoutes(Graph graph, Node source, Node destination, String title) {
        List<List<Node>> routes = Dijkstra.shortestPathsWithAlternative(graph, source, destination);

        System.out.println("\n" + title);
        if (routes.isEmpty()) {
            System.out.println("No route found from " + source.getName() + " to " + destination.getName());
            return;
        }

        printSingleRoute(graph, routes.get(0), "Route 1 (best)");
        if (routes.size() > 1) {
            printSingleRoute(graph, routes.get(1), "Route 2 (alternative)");
        } else {
            System.out.println("Route 2 (alternative): Not available");
        }
    }

    private static void printSingleRoute(Graph graph, List<Node> route, String label) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < route.size(); i++) {
            if (i > 0) {
                path.append(" -> ");
            }
            path.append(route.get(i).getName());
        }
        int cost = Dijkstra.calculatePathCost(graph, route);
        System.out.println(label + ": " + path + " | Distance: " + cost);
    }
}
