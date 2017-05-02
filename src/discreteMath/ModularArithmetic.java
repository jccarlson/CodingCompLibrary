package discreteMath;

public class ModularArithmetic {
	
	public static long modFactorial(long start, long stop, long MOD) {
		
		if(stop == start) return 1;
				
		if(stop > start) throw new ArithmeticException();
		
		if(start >= MOD) return 0;	
		
		long res = 1;
		while(start > stop) {
			res *= start--;
			res %= MOD;			
		}
		
		return res;
	
	}
	
	public static long modMul(long a, long b, long m) {
	    long r = 0;
	    
	    a %= m;
	    b %= m;
	    
	    if(a < 0) a += m;
	    if(b < 0) b += m;
	    
	    if(b > a) {
	    	long t = a;
	    	a = b;
	    	b = t;
	    }
	    
	    while (b > 0) {
	        if ((b & 1L) == 1)  
	        	r = ((m-r) > a) ? r+a : r+a-m;    /* r = (r + a) % m */
	        
	        b >>>= 1;
	        a = ((m-a) > a) ? a+a : a+a-m;    
	    }
	    
	    return r;
	}
	
	
	public static long modExp(long base, long power, long MOD) {
		boolean overflow = false;
		
		if(MOD >= 1L<<31)
			overflow = true;
		
		base %= MOD;
		
		long res = 1;
		
		while(power > 0) {
			
			if((power & 1L) == 1L) {
				res = (overflow ? modMul(res,base, MOD) : (res * base) % MOD);				
			}
			
			power>>>=1;
			base = (overflow ? modMul(base, base, MOD) : (base * base) % MOD);
		}
		
		return res;
	}
	
	public static long primeModDivision(long numerator, long denominator, long MOD) {
		
		if (denominator == 0) throw new ArithmeticException();
		
		return ((numerator % MOD) * modExp(denominator, MOD - 2, MOD)) % MOD;
	}
	
	public static long modDivision(long numerator, long denominator, long MOD) {
		
		if (denominator == 0) throw new ArithmeticException();
		
		numerator %= MOD;
		denominator %= MOD;
		
		return numerator * modInverse(denominator, MOD) % MOD;
	}
	
	
	public static long modInverse(long a, long MOD) {
		long t = 0, newt = 1;
		long r = MOD, newr = a%MOD;
		
		if(newr < 0) newr += MOD;
		
		while(newr != 0) {
			long q = r/newr;
			
			long tmp = newt;
			newt = t - q * newt;
			t = tmp;
			
			tmp = newr;
			newr = r - q * newr;
			r = tmp;			
		}
		
		if(r > 1) throw new ArithmeticException();
		if(t < 0) t+=MOD;
		
		return t;
	}
	
	
	
	public static long modCombinations(long n, long r, long MOD) {
		
		if(n - r > r) {
			return primeModDivision(modFactorial(n, n-r, MOD), modFactorial(r, 0, MOD), MOD);
		}
		return primeModDivision(modFactorial(n, r, MOD), modFactorial(n-r, 0, MOD), MOD);
	}
	
	public static long modPermutations(long n, long r, long MOD) {
		
		return modFactorial(n, n-r, MOD);
	}
	
	
	// for situations where n & r are small (e.g. < ~10000000), it's often faster to 
	// precompute factorials and their inverses for repeated combination/permutation calculations.
	
    public static long [] factorial = new long[500001];
    public static long [] inverse = new long[500001];
    
    public static void makeFactorials(long MOD) {
        factorial[0] = 1;
        inverse[0] = primeModDivision(1,factorial[0],MOD);
        
        for(int i = 1; i < factorial.length; i++) {
            
            factorial[i] = (factorial[i-1] * i) % MOD;
            inverse[i] = primeModDivision(1,factorial[i],MOD);
        }
    }
    
	public static long preModCombinations(int n, int r, long MOD) {
		
		return (factorial[n] * ((inverse[r] * inverse[(n-r)]) % MOD)) % MOD;
	}
	
	public static long preModPermutations(int n, int r, long MOD) {
		
		return (factorial[n] * inverse[(n-r)]) % MOD;
	}
}
