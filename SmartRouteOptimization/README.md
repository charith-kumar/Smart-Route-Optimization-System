# Smart Route Optimization System

A Java-based route planning system that computes shortest paths using Dijkstra's algorithm, supports dynamic traffic updates, and exposes an interactive CLI for real-time route operations.

## Overview

Smart Route Optimization System models a road network as a weighted directed graph and finds efficient routes between locations.  
The project demonstrates algorithmic fundamentals plus practical product behavior:

- Fast shortest-path computation with `PriorityQueue`-based Dijkstra
- Live traffic simulation by updating road weights at runtime
- Alternative route generation when the primary route changes
- Menu-driven CLI for interactive usage

This makes it suitable as a resume project for roles requiring data structures, algorithms, and clean software design.

## Key Features

- **Shortest Path Search**: Computes minimum-distance path from source to destination
- **Dynamic Traffic Updates**: Changes edge weights at runtime to simulate congestion
- **Alternative Routes**: Shows up to 2 route options (best + alternative)
- **Interactive CLI**:
  - Add road
  - Update traffic
  - Find shortest path
  - Exit
- **Clean Architecture**: Modular package structure by responsibility

## Project Structure

```text
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
```

## Tech Stack

- **Language**: Java
- **Core DS/Algo**: Graph (adjacency list), Priority Queue (min-heap), Dijkstra algorithm
- **Runtime**: Standard JDK (no external frameworks required)

## How It Works

1. Road network is stored as `Map<Node, List<Edge>>` adjacency list.
2. Dijkstra computes shortest distances using a min-heap (`PriorityQueue`).
3. Parent tracking reconstructs actual route paths.
4. Alternative route logic perturbs primary-path edges temporarily to discover a second viable path.
5. CLI allows users to modify roads/traffic and request routes in real time.

## Complexity

- **Dijkstra**: `O((V + E) log V)` with adjacency list + priority queue
- **Space**: `O(V + E)`

Where:
- `V` = number of nodes
- `E` = number of edges

## Run Locally

From project root (`SmartRouteOptimization`):

```bash
cd src/main/java
javac -d out com/route/model/*.java com/route/graph/*.java com/route/algorithm/*.java com/route/app/*.java
java -cp out com.route.app.Main
```

## CLI Menu

```text
1. Add Road
2. Update Traffic
3. Find Shortest Path
4. Exit
```

### Example Flow

1. Add roads: `A -> B`, `B -> C`, `C -> D`, etc.
2. Find shortest path from `A` to `E`
3. Update traffic (for example, increase `B -> C` weight)
4. Re-run path query to see changed best route and alternative route

## Resume Highlights

- Implemented graph-based route optimization using Dijkstra with path reconstruction
- Designed dynamic traffic simulation through runtime edge-weight updates
- Built alternative route recommendation feature (best + fallback path)
- Structured system with clean modular architecture (`model`, `graph`, `algorithm`, `app`)
- Developed interactive CLI to operate and test system behavior end-to-end

## Future Enhancements

- Undirected road support toggle
- Persist graph data to file/database
- K-shortest paths (formal algorithm such as Yen's)
- Input from CSV/JSON map data
- Unit tests for algorithm and CLI workflows

## Author

Smart Route Optimization System project in Java for algorithm-focused system design and practical routing simulation.
