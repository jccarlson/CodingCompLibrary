package stringSearch;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;



public class AhoCorasickDictionary <T> {
	
	private TrieNode <T> root = new TrieNode<>('\0');
	private int entries = 0;
    
	private static class TrieNode <T>{
	
	    final char value;
	    
	    OutNode<T> output = null;
	    OutNode<T> tail = null;
	    
	    TrieNode<T> fail = null;	    
	    TrieNode<T> next = null;
	    
	    TrieNode<T> children = null;
	    
	    TrieNode(char c) {
	        value = c;
	    }
	}
	
	private static class OutNode <T> {
		final T value;
		OutNode<T> next;
		
		OutNode(T value, OutNode<T> next) {
			this.value = value;
			this.next = next;
		}	
	}
	
	public AhoCorasickDictionary() {
		// do nothing, root is already initialized.
	}
	
	public AhoCorasickDictionary(String v, T out) {
		add(v,out);
	}
	
	
	public LinkedList<T> [] search(String s) {
		@SuppressWarnings("unchecked")
		LinkedList<T> [] ret = new LinkedList[s.length()];
		
		TrieNode<T> curr = root;
		for(int i = 0; i < s.length(); i++) {
			
			TrieNode<T> ch = curr.children;
			while(ch != null && ch.value != s.charAt(i)) {
				ch = ch.next;
			}
			if(ch == null) {
				if(curr.fail == null)
					continue;
				else { 
					curr = curr.fail;
					i--;
					continue;
				}
			}
			
			curr = ch;
			
			if(curr.output != null) {
				OutNode<T> o = curr.output;
				ret[i] = new LinkedList<T>();
				while(o != null) {
					ret[i].add(o.value);
					o = o.next;					
				}
			}
			
		}
		
		return ret;
	}
	

	public void compile() {
		
		Queue<TrieNode<T>> Q = new ArrayDeque<>();
		
		TrieNode<T> c = root.children;
		while(c != null) {
			Q.add(c);
			c = c.next;
		}
		
		while(!Q.isEmpty()) {
			
			TrieNode<T> n = Q.remove();
			
			inner:
			for(TrieNode<T> ch = n.children; ch != null; ch = ch.next) {
				Q.add(ch);
				for(TrieNode<T> to =  n.fail; to != null; to = to.fail){
					for(TrieNode<T> lch = to.children; lch != null; lch = lch.next){
						
						if(lch.value == ch.value){
							ch.fail = lch;
							
							if(ch.tail == null) {
								ch.output = lch.output;
							}
							else {
								ch.tail.next = lch.output;
							}
							
							if(lch.tail != null) {
								ch.tail = lch.tail;
							}
							
							continue inner;
						}
					}
				}	
			}		
		}
	}
	
	public boolean containsAll(Collection <String> c) {
		for(String s : c) {
			if(contains(s))
				continue;
			return false;
		}
		return true;
	}
	
	public boolean containsAll(String [] c) {
		for(String s : c) {
			if(contains(s))
				continue;
			return false;
		}
		return true;
	}
		
	
	public int size() {
		return entries;
	}    
    
    public boolean contains(String v) {
        
        int l = v.length();
                            
        TrieNode<T> curr = root;
                    
        for(int i = 0; i < l; i++) {

            char c = v.charAt(i);

            curr = curr.children;
            
            while(curr != null && curr.value < c) {
                curr = curr.next;
            }
            if(curr == null || curr.value != c)
                return false;              
        }
        
        if(curr.output == null) return false;
        return true;
    }
    
    public void add(String v, T out) {
        
        int l = v.length();
        
        TrieNode<T> curr = root;
        entries++;
        
        for(int i = 0; i < l; i++) {

            char c = v.charAt(i);

            if(curr.children == null) {
               curr.children = new TrieNode<>(c);
               curr.children.fail = root;
            }
            TrieNode<T> parent = curr;
            curr = curr.children;

            TrieNode<T> prev = null;
            while(curr != null && curr.value < c) {
                prev = curr;
                curr = curr.next;
            }

            if(curr == null || curr.value != c) {
                TrieNode<T> t = curr;
                curr = new TrieNode<>(c);
                curr.fail = root;
                curr.next = t;
                if(prev != null)
                    prev.next = curr;
                else
                    parent.children = curr;
            }              
        }
        
        if(curr.output == null) {
        	curr.output = curr.tail = new OutNode<T>(out, null);
        }
        else {
        	curr.output = new OutNode<T>(out,curr.output);
        }
        
    }
    
}
