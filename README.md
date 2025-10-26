# Third-Assik
1. Summary of Input Data and Algorithm Results

Two graphs were tested using Prim’s and Kruskal’s algorithms.
For each graph, we measured total MST cost, execution time, and operation count.
Graph 1
Vertices: 5
Edges: 7

Prim:
Total cost: 16
Execution time: ~1.52 ms
Operations: 42

Kruskal:
Total cost: 16
Execution time: ~1.28 ms
Operations: 37
Graph 2
Vertices: 4
Edges: 5

Prim:
Total cost: 6
Execution time: ~0.87 ms
Operations: 29
Kruskal:

Total cost: 6
Execution time: ~0.92 ms
Operations: 31

Both algorithms produced the same MST cost (correctness confirmed).

2. Comparison of Prim’s and Kruskal’s Algorithms
Theory
Prim’s algorithm grows a tree by expanding from one vertex and selecting the smallest connecting edge.
Kruskal’s algorithm sorts all edges and connects components while avoiding cycles.
Performance
Prim works well when the graph is dense (many edges).
Kruskal works well when the graph is sparse (fewer edges).
Kruskal depends on efficient Union–Find structure.
Results in practice
Both were fast on small graphs.
Operation count was slightly lower for Kruskal on Graph 1.
Execution times were very close and differed only slightly.

3. Conclusions

If the graph is dense (many edges), Prim can be more efficient because it uses a priority queue over adjacency.
If the graph is sparse, Kruskal tends to perform better because it sorts fewer edges.
Kruskal is easier to implement with edge lists.
Prim may require additional data structures (priority queue or heap).
For both algorithms, the total MST cost is always identical.

Overall:
Use Prim for dense graphs and adjacency lists.
Use Kruskal for sparse graphs or when edges already sorted.
