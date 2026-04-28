🚀 Smart Route Optimization System

A Java-based route planning system that computes optimal paths using Dijkstra’s Algorithm, supports real-time traffic updates, and provides alternative route suggestions via an interactive CLI.

📌 Overview

This project models a real-world road network as a weighted directed graph and dynamically computes efficient routes between locations.

It goes beyond a basic algorithm implementation by simulating real-time navigation behavior, including traffic-aware rerouting and fallback paths.

✨ Key Features
✅ Shortest Path Computation using optimized Dijkstra (Min-Heap)
🚦 Dynamic Traffic Simulation (update edge weights at runtime)
🔁 Alternative Route Suggestions (best + fallback path)
💻 Interactive CLI System
Add roads
Update traffic
Find routes
🧱 Modular Architecture (clean separation of concerns)
🏗️ Project Structure
src/main/java/com/route
├── model
│   ├── Node.java
│   └── Edge.java
├── graph
│   └── Graph.java
├── algorithm
│   └── Dijkstra.java
└── app
    └── Main.java
⚙️ Tech Stack
Language: Java
Core Concepts:
Graphs (Adjacency List)
Priority Queue (Min-Heap)
Dijkstra’s Algorithm
Runtime: Standard JDK (no external dependencies)
⚡ How It Works

Graph stored as:

Map<Node, List<Edge>>
Dijkstra computes shortest path using a priority queue
Parent tracking reconstructs actual route paths
Alternative route logic finds a second-best path when conditions change
CLI enables real-time interaction with the system
📊 Complexity

Time Complexity:

O((V + E) log V)

Space Complexity:

O(V + E)

Where:

V = number of nodes
E = number of edges
▶️ Run Locally
cd src/main/java
javac -d out com/route/model/*.java com/route/graph/*.java com/route/algorithm/*.java com/route/app/*.java
java -cp out com.route.app.Main
🖥️ CLI Menu
1. Add Road
2. Update Traffic
3. Find Shortest Path
4. Exit
🔍 Example Execution
=== Smart Route Optimization ===

Road added: A -> B (5)
Road added: B -> C (3)
Road added: C -> D (2)
Road added: D -> E (4)

Shortest Path:
Route 1: A → B → C → D → E | Distance: 14
Route 2: A → B → D → E     | Distance: 17

Traffic Update:
B → C weight changed to 20

Recomputed Routes:
Route 1: A → B → D → E     | Distance: 17
Route 2: A → C → D → E     | Distance: 21

👉 This demonstrates real-time rerouting under changing traffic conditions

💼 Resume Highlights
Built a graph-based route optimization system using Dijkstra’s Algorithm
Optimized shortest path computation to O(E log V) using priority queues
Simulated real-time traffic conditions with dynamic edge weight updates
Designed a modular, scalable architecture using clean OOP principles
Implemented alternative routing logic similar to real navigation systems
🚧 Future Enhancements
A* Algorithm for heuristic-based routing
K-shortest paths (Yen’s Algorithm)
Map data import (CSV/JSON)
Persistent storage (DB/file system)
REST API (Spring Boot backend)
🧠 What This Project Demonstrates
Strong grasp of Data Structures & Algorithms
Ability to convert theory into a real-world system
Understanding of performance optimization
Clean software design & modular coding
