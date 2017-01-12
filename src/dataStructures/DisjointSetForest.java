package dataStructures;

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
