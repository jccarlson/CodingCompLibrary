package dataStructures;

import java.util.Collection;

public class StringTrie /*implements Iterable<String>*/{
	
	private TrieNode root = new TrieNode('\0');
    
	private static class TrieNode {
	
	    final char value;
	    int count = 0;
	    
	    TrieNode next = null;
	    TrieNode children = null;
	    
	    TrieNode(char c) {
	        value = c;
	    }
	}
	
	public StringTrie() {
		// do nothing, root is already initialized.
	}
	
	public StringTrie(String v) {
		add(v);
	}
	
	public StringTrie(Collection<String> c) {
		addAll(c);
	}
	
	public StringTrie(String[] c) {
		addAll(c);
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
	
	public void addAll(Collection <String> c) {
		for(String s : c) {
			add(s);
		}
	}
	
	public void addAll(String [] c) {
		for(String s : c) {
			add(s);
		}
	}
	
	public void removeAll(Collection <String> c) {
		for(String s: c) {
			remove(s);
		}
	}
	
	public void removeAll(String [] c) {
		for(String s: c) {
			remove(s);
		}
	}	
	
	public int size() {
		return root.count;
	}
	    
    public int countPrefix(String v) {
        
        int l = v.length();
                          
        TrieNode curr = root;
                    
        for(int i = 0; i < l; i++) {

            char c = v.charAt(i);

            curr = curr.children;

            
            while(curr != null && curr.value < c) {
                curr = curr.next;
            }
            if(curr == null || curr.value != c)
                return 0;              
        }
        return curr.count;
    }
    
    
    public boolean contains(String v) {
        
        int l = v.length();
                            
        TrieNode curr = root;
                    
        for(int i = 0; i < l; i++) {

            char c = v.charAt(i);

            curr = curr.children;
            
            while(curr != null && curr.value < c) {
                curr = curr.next;
            }
            if(curr == null || curr.value != c)
                return false;              
        }
        
        if(curr.children == null) return false;
        return curr.children.value == '\0' ? true : false;
    }
    
    public void add(String v) {
        
        int l = v.length();
        
        TrieNode curr = root;
        curr.count++;
        
        for(int i = 0; i < l; i++) {

            char c = v.charAt(i);

            if(curr.children == null) {
               curr.children = new TrieNode(c);
            }
            TrieNode parent = curr;
            curr = curr.children;

            TrieNode prev = null;
            while(curr != null && curr.value < c) {
                prev = curr;
                curr = curr.next;
            }

            if(curr == null || curr.value != c) {
                TrieNode t = curr;
                curr = new TrieNode(c);
                curr.next = t;
                if(prev != null)
                    prev.next = curr;
                else
                    parent.children = curr;
            }
                       
            curr.count++;               
        }
        
        if(curr.children == null || curr.children.value != '\0') {
            TrieNode t = new TrieNode('\0');
            t.count++;
            t.next = curr.children;
            curr.children = t;
        }
        else {
            curr.children.count++;
        }
        
    }
    
    public boolean remove(String v) {
    	if(!contains(v))
    		return false;
    	
    	int l = v.length();
        
        TrieNode curr = root;
        curr.count--;
        
        for(int i = 0; i < l; i++) {

            char c = v.charAt(i);

            TrieNode parent = curr;
            curr = curr.children;
            
            TrieNode prev = null;
            while(curr.value != c) {
            	prev = curr;
                curr = curr.next;
            }
                       
            curr.count--;
            if(curr.count == 0) {
            	if(prev == null) {
            		parent.children = curr.next;
            	}
            	else {
            		prev.next = curr.next;
            	}
            	return true;
            }
        }
        
        curr.children.count--;
        if(curr.children.count == 0) {
        	curr.children = curr.children.next;
        }
        return true;
    }
    
    /*
	@Override
	public Iterator<String> iterator() {
		
		return new Iterator<String>() {
			
			Deque<TrieNode> stack = new LinkedList<TrieNode>();
			String prev = null;
			StringBuilder s = new StringBuilder();
			
			{
				stack.push(root.children);
			}
			
			@Override
			public boolean hasNext() {
				
				TrieNode t = stack.peek();
				while(t.value != '\0') {
					stack.push(t.children)
				}
				
			}

			@Override
			public String next() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void remove() {
				
			}
			
		};
	}
	*/

	/*
    public void print(FastOutput o) {
        Deque<TrieNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TrieNode curr = q.removeFirst();
            
            TrieNode c = curr.children;
            while(c != null) {
                o.print("{" + c.value + ", " + c.count + "} ");
                q.add(c);      
                c = c.next;
            } 
            o.println();
        }
        
    }
    */
}
