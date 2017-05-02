package discreteMath;

/**
 * @author jcarl
 *
 */
/**
 * @author jcarl
 *
 */
/**
 * @author jcarl
 *
 */
public class Primes {
	

	/**
	 * Calculates the greatest common divisor of {@code a} and {@code b}.
	 * @param a a {@code long} value
	 * @param b a {@code long} value
	 * @return a {@code long} value which is the greatest common divisor of {@code a} and {@code b}.
	 */
	public static long gcd(long a, long b) {
		if(a < b) return gcd(b,a);
		
		long r = a%b;
		while(r > 0) {
			a = b;
			b = r;
			r = a%b;
		}
		return b;
	}
	
	/**
	 * Calculates the least common multiple of {@code a} and {@code b}.
	 * @param a a {@code long} value
	 * @param b a {@code long} value
	 * @return a {@code long} value which is the least common multiple of {@code a} and {@code b}.
	 */
	public static long lcm(long a, long b) {
		if(a == 0 && b == 0)
			return 0;
		return Math.abs((a / gcd(a,b)) * b);		
	}
	
	
	/**
	 * Factors {@code N} into its prime factors and stores them in {@code primeFactors}, and their powers in {@code powers}.
	 * 
	 * @param primeFactors a {@code long[]} which the unique prime factors will be written to, in increasing order, beginning at index 0. Length of at least 15 guarantees every factor of N will fit.
	 * @param powers a {@code byte[]} which the powers of the unique prime factors will be written to. Each value in power corresponds to the equivalently indexed factor in {@code primeFactors}.
	 * @param N a {@code long} value to factor.
	 * @return an {@code int} denoting the number of unique prime factors found.
	 */
	public static int factor(long [] primeFactors, byte [] powers, long N) {
		
		
        int last = 0;
        byte power = 0;
        long number = N;
        
        if((number & 1) == 0) {
            primeFactors[last] = 2;
            while ((number & 1) == 0) {
                power += 1;
                number >>>= 1;   
            }
            powers[last++] = power;
        }
        
        long factor;
        
        for(factor = 3; factor * factor <= number; factor += 2) {
            if ((number % factor) != 0) {
                continue;
            }
            
            power = 0;
            do {
                power += 1;
                number /= factor;   
            } while ((number % factor) == 0);
            
            primeFactors[last] = factor;
            powers[last++] = power;            
        }

        if (number > 1) {
        	
        	primeFactors[last] = number;
            powers[last++] = 1;
        }
        return last;
    }
	
	
	/**
	 * Non-Probabilistic Miller-Rabin primality test for positive numbers up to (2^64) - 1. 
	 * This works because at least one of a = {all primes less than or equal to 37} has been 
	 * verified to return composite for all composite numbers less than 2^64.
	 * 
	 * Extending this test for numbers greater than {@code Long.MAX_VALUE} (for example,
	 * modifying the code to work with {@link BigInteger} instead of {@code long}, comes with 
	 * the caveat that the test will no longer be deterministic for values >= 2^64.   
	 * @param number The number to test for primality.
	 * @return {@code true} if prime, {@code false} if composite
	 */
	public static boolean isPrime(long number) {
        
        if(number <= 1) return false;
        		
		final int [] millerRabinTests = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
            
        for(int i = 0; i < millerRabinTests.length && millerRabinTests[i] < number; i++) {
            if(number % millerRabinTests[i] == 0) return false;
        }
        
        if(number < 37*37) return true;
		
		int testlim = 12;
		if(number < 3825123056546413051L)
			testlim = 9;
		if(number < 341550071728321L)
			testlim = 7;
		if(number < 3474749660383L)
			testlim = 6;
		if(number < 2152302898747L)
			testlim = 5;
		if(number < 3215031751L)
			testlim = 4;
		if(number < 25326001L)
			testlim = 3;
		if(number < 1373653L)
			testlim = 2;	
		
		boolean overflow = number >= 1L<<31;
		
		long d = number - 1;
		
		int r = 0;
		while ((d & 1) == 0) {
			r += 1;
            d >>>= 1;   
        }    
		
		//boolean composite = false;
		long x = 1;
		
		outer:
		for(int i = 0; i < testlim; i++) {
			long a = millerRabinTests[i];
			x = modExp(a,d,number);
			if(x == 1 || x == number-1)
				continue outer;
			for(int j = 1; j < r; j++) {
				x = (overflow ? modMul(x,x,number) : x * x % number);
				if(x == 1) {
					//composite = true;
					//break outer;
					return false;
				}
				if(x == number - 1) {
					continue outer;
				}        					
			}
			//composite = true;
			//break outer;
			
			return false;
		}
		
		return true;
	}
	
	public static long modMul(long a, long b, long MOD) {
	    long r = 0;
	    
	    a %= MOD;
	    b %= MOD;
	    while (b > 0) {
	        if ((b & 1L) == 1)  
	        	r = ((MOD-r) > a) ? r+a : r+a-MOD;    /* r = (r + a) % MOD */
	        
	        b >>= 1;
	        a = ((MOD-a) > a) ? a+a : a+a-MOD;    
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

}
