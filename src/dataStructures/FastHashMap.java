package dataStructures;

/**
 * Implements a hash map of fixed size with integer keys and double values which is
 * much faster than the Java SDK HashMap<Integer,Double> because there is no resizing or 
 * autoboxing. Can be modified to suit particular problem needs/constraints with different
 * types for val.
 */
public class FastHashMap {
	private Node [] nodes;
	private int size;
	
	public FastHashMap(int size) {
		this.size = size;
		nodes = new Node[size];
	}
	
	public boolean containsKey(int key) {
		int i = key % size;
		Node n = nodes[i];
		while(n != null) {
			if(n.key == key) {
				return true;
			}
			n = n.next;				
		}
		return false;
	}
	
	public double get(int key) {
		int i = key % size;
		Node n = nodes[i];
		while(n != null) {
			if(n.key == key) {
				return n.val;
			}
			n = n.next;				
		}
		return Double.NaN;			
	}
	
	public void put(int key, double val) {
		int i = key % size;
		Node n = new Node(key, val);
		n.next = nodes[i];
		nodes[i] = n;
	}
	
	
	private static class Node {
		final int key;
		final double val;	
		
		Node next = null;
		
		private Node(int k, double v) {
			key = k;
			val = v;
		}
	}
	
}