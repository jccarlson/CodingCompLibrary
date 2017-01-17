package dataStructures;

/**
 * Disjoint Set Forest Implementation for a forest represented by an int array, 
 * where each node in the forest is an index of the array, the value at each
 * index is the parent of the node represented by the index, and negative values
 * indicate the node is a root. Provides two options for union which store 
 * negative values representing the element count or the tree depth at the index
 * of the root of each tree (Do not use interchangeably).
 *
 */
public class DisjointSetForest {
	
	public static void unionCount(int [] DSF, int x, int y) {
        int px = parent(DSF,x);
        int py = parent(DSF,y);
        
        if(px == py) 
            return;
        
        DSF[px] += DSF[py];
        DSF[py] = px;
    }
	
	public static void unionDepth(int [] DSF, int x, int y) {
        int px = parent(DSF,x);
        int py = parent(DSF,y);
        
        if(px == py) 
            return;
        
        if(DSF[px] < DSF[py]) {
        	DSF[py] = px;
        	return;
        }
        if(DSF[py] < DSF[px]) {
        	DSF[px] = py;
        	return;
        }
        DSF[py] = px;
        DSF[px]--;
    }
    
    public static int parent(int [] DSF, int x) {
        if(DSF[x] < 0) return x;
        
        return DSF[x] = parent(DSF,DSF[x]);
    }

}
