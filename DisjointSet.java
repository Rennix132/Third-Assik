public class DisjointSet {
    int[] parent;
    int[] rank;
    public int operations = 0;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
    }

    public int find(int x) {
        operations++;
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int a, int b) {
        operations++;
        int x = find(a);
        int y = find(b);
        if (x != y) {
            if (rank[x] < rank[y]) parent[x] = y;
            else if (rank[x] > rank[y]) parent[y] = x;
            else { parent[y] = x; rank[x]++; }
        }
    }
}

