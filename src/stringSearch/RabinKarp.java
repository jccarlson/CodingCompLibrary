package stringSearch;

import java.util.ArrayList;

public class RabinKarp {

	private static final long PRIME = 1002017;
	private static final long MOD = Integer.MAX_VALUE;
	
	public static int computeHash(String s) {
		
		long hash = 0;
		long base = 1;
		
		
		for(int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			hash += (c * base % MOD);		
			hash %= MOD;
			base *= PRIME;
			base %= MOD;
		}
		
		return (int)hash;
		
	}
	
	public static int recomputeHash(long hash, char out, char in, int strLen) {
		hash -= (out * modExp(PRIME, strLen-1, MOD) % MOD); 
		if(hash < 0) hash += MOD;
		hash *= PRIME;
		hash += in;
		
		hash %= MOD;
		
		return (int)hash;	
	}
	
	public static ArrayList<Integer> findAll(String s, String searched) {
		if(s.length() > searched.length()) return new ArrayList<Integer>();
		
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		int target = computeHash(s);
		int currentHash = computeHash(searched.substring(0, s.length()));
		
		int i = s.length();
		while(true) {
			
			if(target == currentHash && s.equals(searched.substring(i - s.length(), i))) {
				res.add(i - s.length());
			}
			
			if(i >= searched.length()) break;
			
			currentHash = recomputeHash(currentHash, searched.charAt(i - s.length()), searched.charAt(i), s.length());
			i++;
		}
		
		return res;
		
	}
	
	public static int findFirst(String s, String searched) {
	
		if(s.length() > searched.length()) return -1;
		
				
		int target = computeHash(s);
		int currentHash = computeHash(searched.substring(0, s.length()));
		
		int i = s.length();
		while(true) {
			
			if(target == currentHash && s.equals(searched.substring(i - s.length(), i))) {
				return i - s.length();
			}
			
			if(i >= searched.length()) break;
			
			currentHash = recomputeHash(currentHash, searched.charAt(i - s.length()), searched.charAt(i), s.length());
			i++;
		}
		
		return -1;
		
	}
	
	public static long modExp(long base, long power, long MOD) {
				
		base %= MOD;
		
		long res = 1;
		
		while(power > 0) {
			
			if((power & 1L) == 1L) {
				res = ((res * base) % MOD);				
			}
			
			power>>>=1;
			base = ((base * base) % MOD);
		}
		
		return res;
	}
}
