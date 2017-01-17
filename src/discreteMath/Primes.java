package discreteMath;

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
	 * Uses trial division to find small factors, then Miller-Rabin to find the (at most) remaining 2 if they are large.
	 * 
	 * @param primeFactors a {@code long[]} which the unique prime factors will be written to, in increasing order, beginning at index 0. Length of at least 15 guarantees every factor of N will fit.
	 * @param powers a {@code byte[]} which the powers of the unique prime factors will be written to. Each value in power corresponds to the equivalently indexed factor in {@code primeFactors}.
	 * @param N a {@code long} value to factor.
	 * @return an {@code int} denoting the number of unique prime factors found.
	 */
	public static int factor(long [] primeFactors, byte [] powers, long N) {
		
		final int [] millerRabinTests = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
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
        
        for(factor = 3; factor * factor * factor <= number || (number <= 1000000 && factor * factor <= number); factor += 2) {
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
        	
        	if(factor * factor > number) {
        		primeFactors[last] = number;
            	powers[last++] = 1;
        	}
        	else {
        		//Miller-Rabin (number is either prime or the product of two primes greater than factor).
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
        		if((int)number < 25326001)
        			testlim = 3;
        		if((int)number < 1373653)
        			testlim = 2;
        		
        		long d = number - 1;
        		
        		int r = 0;
        		while ((d & 1) == 0) {
        			r += 1;
                    d >>>= 1;   
                }    
        		
        		boolean composite = false;
        		long x = 1;
        		
        		outer:
        		for(int i = 0; i < testlim; i++) {
        			long a = millerRabinTests[i];
        			x = modExp(a,d,number);
        			if(x == 1 || x == number-1)
        				continue outer;
        			for(int j = 1; j < r; j++) {
        				x = (x * x) % number;
        				if(x == 1) {
        					composite = true;
        					break outer;
        				}
        				if(x == number -1) {
        					continue outer;
        				}        					
        			}
        			composite = true;
        			break outer;
        		}
        		
        		if(composite) {
        			
        		}
        		else {
        			primeFactors[last] = number;
                	powers[last++] = 1;
        		}
        	}
        }
        return last;
    }
	
	
	public static long modExp(long base, long power, long MOD) {
		base %= MOD;
		
		long res = 1;
		
		while(power > 0) {
			if((power & 1L) == 1L) {
				res *= base;
				res %= MOD;				
			}
			power>>>=1;
			base*=base;
			base %= MOD;
		}
		
		return res;
	}

}
