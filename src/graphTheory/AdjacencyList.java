package graphTheory;

public class AdjacencyList {
	
	public static class EdgeNode {
		public final int weight;
		public int dest;
		
		public EdgeNode next;
		
		public EdgeNode(int w, int d, EdgeNode n) {
			weight = w;
			dest = d;
			next = n;
		}
	}
	
	public EdgeNode [] AdjList;
	int root = -1;
	
	public void makeWeightedTreeFromParentList(int [] parents, int [] weights) {
		AdjList = new EdgeNode[parents.length];
		
		for(int i = 0; i < parents.length; i++) {
			if(parents[i] == -1) {
				root = i;
			}
			else {
				AdjList[parents[i]] = new EdgeNode(weights == null ? 1 : weights[i], i, AdjList[parents[i]]);
			}
		}
	}
	
	public void makeTreeFromParentList(int [] parents) {
		makeWeightedTreeFromParentList(parents, null);
	}
	
	public void addUndirectedEdge(int u, int v, int weight) {
		AdjList[u] = new EdgeNode(weight, v, AdjList[u]);
		AdjList[v] = new EdgeNode(weight, u, AdjList[v]);
	}
	
	public void addEdge(int u, int v, int weight) {
		AdjList[u] = new EdgeNode(weight, v, AdjList[u]);
	}
	
	
	// depth-first search for finding the height of each node (of a tree). 
	// search itself works on graphs too. Can be modified for other purposes. 
	
	public boolean [] visited;
	public int [] height;
	
    public int dfs(int node) {
        
    	visited[node] = true;
         
        height[node] = 1;
        
        EdgeNode e = AdjList[node];
        while(e != null) {
            if(!visited[e.dest]) {
                height[node] = Math.max(dfs(e.dest) + 1, height[node]);
            }
            e = e.next;
        }
        
        return height[node];        
    }
	
	

}
