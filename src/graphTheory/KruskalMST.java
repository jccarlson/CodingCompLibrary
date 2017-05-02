package graphTheory;

import java.util.Arrays;

public class KruskalMST {

	public static int findMST(Edge [] edges, int nVert /* , Edge [] tree */ ) {
		
		/* 
		
		if(tree.length < nVert - 1) throw new IllegalArgumentException();
		Arrays.fill(tree,null);
		
		*/
		
		int [] DSF = new int[nVert];
		
		Arrays.fill(DSF, -1);
	    Arrays.sort(edges);
	
	    int sum = 0;
	    int edgecount = 0;
	    
	    for(int e = 0; e < edges.length; e++) {
	        
	        if(union(DSF, edges[e].v1, edges[e].v2)) {
	    
	        	/*
	        	
	        	tree[edgecount] = edges[e];
	        	 
	        	*/
	        	
	        	edgecount++;
	            sum += edges[e].weight;
            }
	        
	        if(edgecount == nVert - 1) break;
	    }
	    
	    return sum;
	}
	
	
    public static boolean union(int [] DSF, int x, int y) {
        int px = parent(DSF,x);
        int py = parent(DSF,y);
        
        if(px == py) 
            return false;
        
        DSF[px] += DSF[py];
        DSF[py] = px;
        return true;
    }
    
    public static int parent(int [] DSF, int x) {
        if(DSF[x] < 0) return x;
        
        return DSF[x] = parent(DSF,DSF[x]);
    }
	
    
    public static class Edge implements Comparable<Edge>{
        
        public final int v1, v2;
        
        public final int weight;
        
        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = w;
        }
        
        public int compareTo(Edge e) {
            return Integer.compare(weight, e.weight);
        }
        
    }
}
