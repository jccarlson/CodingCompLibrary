package dataStructures;

public class FenwickTree {
	
	long [] fenwick;
    
	// rangeUpdate() and pointQuery() used together (don't mix with pointUpdate() and rangeQuery())
	
    public void rangeUpdate(int l, int r, long v) {
        pointUpdate(l,v);
        pointUpdate(r+1,-v);
    }
    
    public long pointQuery(int i) {
        return prefixSum(i);
    }
    
    // pointUpdate() and rangeQuery() used together (don't mix with rangeUpdate() and pointQuery())
    
    public void pointUpdate(int i, long v) {
        while(i < fenwick.length) {
            fenwick[i] += v;
            i += (i & -i);
        }
    }
    
    public long rangeQuery(int l, int r) {
        return prefixSum(r) - prefixSum(l-1);
    }
    
    // initializes the tree represented by the array
    
    public void init(int N) {
        fenwick = new long[N+1];
    }
    
    public long prefixSum(int i) {
        long sum = 0;
        while(i > 0) {
            sum += fenwick[i];
            i -= (i & -i);
        }
        return sum;
    }

}
