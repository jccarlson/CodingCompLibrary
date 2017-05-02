package SqrtDecomposition;

public class MosAlgorithm {
	
	int [] ans;
    Query [] qs;
    
    
    public void add(int p) {
        
    	// add element at position p to answer
       
    }

    public void remove(int p) {
        
        // remove element at position p from answer
    	
    }
	
    public void solveQs() {
        int currL = 0, currR = 0;
        add(0);
        
        for(Query q:qs) { 
            
            while(currR < q.R) {
                currR++;
                add(currR);
            }
            while(currR > q.R) {
                remove(currR);
                currR--;                
            }
            
            while(currL < q.L) {
                remove(currL);
                currL++;
            }
            while(currL > q.L) {
                currL--;
                add(currL);                                
            }
            
            int a = 0;
            
            //set a = answer for query
            
            ans[q.index] = a;
            
        }
    }
	
	
	public static class Query implements Comparable<Query>{
        
        public static int BLOCK_SIZE = -1;
        
        public int L, R;
        
        public int index;
        
        public Query(int L, int R, int index) {
            this.L = L;
            this.R = R;
            this.index = index;
        }
        
        public int compareTo(Query q) {
            
            int c1 = L/BLOCK_SIZE;
            int c2 = q.L/BLOCK_SIZE;
            
            if(c1 == c2) {
                return R - q.R;
            }
            
            return c1 - c2;            
        }        
    }
}
