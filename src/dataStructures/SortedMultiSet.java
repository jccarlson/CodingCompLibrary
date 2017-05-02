package dataStructures;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMultiSet <E> implements Collection<E>{
	
	private SortedMap<E, Integer> internal;
	private int size;
	
	public SortedMultiSet() {
		internal = new TreeMap<E,Integer>();
		size = 0;
	}
	
	public SortedMultiSet(Comparator <? super E> c) {
		
		internal = new TreeMap<E,Integer>(c);
		size = 0;
	}
	
	public boolean removeAllEqualTo(Object v) {
		if(!internal.containsKey(v))
			return false;
		size -= internal.get(v);
		internal.remove(v);
        
        return true;
	}
	
	public int count(Object v) {
		if(!internal.containsKey(v))
			return 0;
		return internal.get(v);
	}
	
	public E first() {
		return internal.firstKey();
	}
	public E last() {
		return internal.lastKey();
	}
	
	public Comparator<? super E> comparator() {
		return internal.comparator();
	}
	
	// Remaining methods inherited from Collections Interface
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object v) {
		if(!internal.containsKey(v))
			return false;
		size--;
		internal.put((E)v, internal.get(v) - 1);
        if(internal.get(v) <= 0) {
            internal.remove(v);
        }
        return true;
	}

	@Override
	public boolean add(E v) {
		
		if(!internal.containsKey(v)){
			
            internal.put(v,1);
        }
        else {
        	
            internal.put(v, internal.get(v) + 1);
        }
		size++;
		return true;
		
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		
		for(E e : c) {			
			if(!add(e)) return false;
		}
		return true;
	}

	@Override
	public void clear() {
		size = 0;
		internal = new TreeMap<E,Integer>(internal.comparator());		
	}

	@Override
	public boolean contains(Object o) {
		return internal.containsKey(o); 
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object e : c) {			
			if(!internal.containsKey(e)) return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		
		return internal.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			
			boolean removeOK = false;
			int count = 0;
			E current = null;
			Iterator<E> keys = internal.keySet().iterator();
			
			@Override
			public boolean hasNext() {
				if(keys.hasNext() || count > 0) return true;
				return false;
			}

			@Override
			public E next() {
				removeOK = true;
				if(count > 0) {
					count--;
					
					return current;
				}
				else {
					current = keys.next();
					count = internal.get(current) - 1;
					
					return current;
				}
				
			}
			
			@Override
			public void remove() {
				if(!removeOK) throw new IllegalStateException();
				if(internal.get(current) > 1) {
					SortedMultiSet.this.remove(current);
				}
				else {
					size--;
					keys.remove();
				}
				removeOK = false;
			}
			
		};
	}

	
	@Override
	public boolean removeAll(Collection<?> c) {
		for(Object e : c) {			
			if(!removeAllEqualTo(e)) return false;
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		
		Iterator<E> keys = internal.keySet().iterator();
		
		while(keys.hasNext()) {
			E k = keys.next();
			if(!c.contains(k)) {
				size -= internal.get(k);
				keys.remove();
			}
		}
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return toArray(new Object[0]);
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if(a == null || a.length < size) {
			a = Arrays.copyOf(a, size);
		}
		
		int ai = 0;
		for(E elem : this) {
			a[ai++] = (T) elem;
		}
		if(a.length > size)
			a[size] = null;
		return a;
	}

}
