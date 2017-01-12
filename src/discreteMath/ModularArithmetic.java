package discreteMath;

public class ModularArithmetic {
	
	public static long modFactorial(long start, long stop, long MOD) {
		long res = 1;
		while(start > stop) {
			long s = start % MOD;
			res *= s;
			res %= MOD;
			start--;
		}
		return res;
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
	
	public static long modDivision(long numerator, long denominator, long MOD) {
		
		if (denominator == 0) throw new ArithmeticException();
		
		return ((numerator % MOD) * modExp(denominator, MOD - 2, MOD)) % MOD;
	}
	
	public static long modCombinations(long n, long r, long MOD) {
		
		if(n - r > r) {
			return modDivision(modFactorial(n, n-r, MOD), modFactorial(r, 0, MOD), MOD);
		}
		return modDivision(modFactorial(n, r, MOD), modFactorial(n-r, 0, MOD), MOD);
	}
	
	public static long modPermutations(long n, long r, long MOD) {
		
		return modFactorial(n, n-r, MOD);
	}
}
