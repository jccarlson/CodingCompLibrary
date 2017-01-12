package matrices;

public class MatrixAlgebra {
	
	public static double [][] transpose(double [][] M) {
		
		double [][] res = new double[M[0].length][M.length];
		
		for(int row = 0; row < M.length; row++) {
			for(int col = 0; col < M[0].length; col++) {
				res[col][row] = M[row][col];
			}
		}
		
		return res;
	}
	
	public static double [][] add(double [][] A, double [][] B) {
		
		double [][] res = new double[A.length][A[0].length];
		
		for(int row = 0; row < A.length; row++) {
			for(int col = 0; col < A[0].length; col++) {
				res[row][col] = A[row][col] + B[row][col];
			}
		}
		
		return res;
	}
	
	public static double [][] subtract(double [][] A, double [][] B) {
		double [][] res = new double[A.length][A[0].length];
		
		for(int row = 0; row < A.length; row++) {
			for(int col = 0; col < A[0].length; col++) {
				res[row][col] = A[row][col] - B[row][col];
			}
		}
		
		return res;
	}
	
	public static double [][] matMult(double [][] A, double [][] B) {
		double [][] res = new double[A.length][B[0].length];
		
		for(int row = 0; row < res.length; row++) {
			for(int col = 0; col < res[0].length; col++) {
				res[row][col] = 0;
				
				for(int i = 0; i < A[0].length; i++) {
					res[row][col] += A[row][i] * B[i][col];
				}
			}
		}
		
		return res;
	}
	
	public static double [][] identity(int rows, int cols) {
		
		double [][] res = new double[rows][cols];
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(row == col)
					res[row][col] = 1.0;
				else
					res[row][col] = 0.0;
			}
		}
		
		return res;
	}
	
	public static double [][] zero(int rows, int cols) {
		
		double [][] res = new double[rows][cols];
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				res[row][col] = 0.0;
			}
		}
		
		return res;
	}
	
	public static double [][] copy(double [][] M) {
		
		double [][] res = new double[M.length][M[0].length];
		
		for(int row = 0; row < M.length; row++) {
			for(int col = 0; col < M[0].length; col++) {
				res[row][col] = M[row][col];
			}
		}
		
		return res;
	}
	
	public static double [][] matExp(double [][] M, int power) {
		
		double [][] res = identity(M.length, M[0].length);		
		
		double [][] base = copy(M);
		
		while(power > 0) {
			if((power & 1) == 1) {
				res = matMult(res, base);
			}
			
			power>>>=1;
			base = matMult(base, base);
		}
		
		return res;
	}
	
	public static double [][] subMatrix(double [][] M, int r0, int c0, int r1, int c1) {
		
		double [][] res = new double[r1 - r0][c1 - c0];
		
		for(int row = r0; row < r1; row++) {
			for(int col = c0; col < c1; col++) {
				res[row - r0][col - c0] = M[row][col];
			}
		}
		
		return res;
	}
	
	public static double [][] rowSwap(double [][] M, int r1, int r2) {
		
		double [][] res = copy(M);
		double [] tmpR1 = M[r1].clone();
		res[r1] = res[r2];
		res[r2] = tmpR1;
		
		return res;
	}
	
	public static double [][] rowMult(double [][] M, int row, int factor) {
		
		double [][] res = copy(M);

		for(int c = 0; c < res[row].length; c++) {
			res[row][c] *= factor;
		}
		
		return res;
	}
	
	public static double [][] rowAdd(double [][] M, int fact1, int r1, int fact2, int r2) {
		
		double [][] res = copy(M);

		double [] tmpR1 = M[r1].clone();
		
		for(int c = 0; c < tmpR1.length; c++) {
			tmpR1[c] *= fact1;
			res[r2][c] *= fact2;
			res[r2][c] += tmpR1[c];
		}
		
		return res;
	}
	
 	public static long [][] copy(long [][] M) {
 	   
        long [][] res = new long[M.length][M[0].length];
		
		for(int row = 0; row < M.length; row++) {
			for(int col = 0; col < M[0].length; col++) {
				res[row][col] = M[row][col];
			}
		}
		
		return res;
	}
    
    public static long [][] modMatMult(long [][] A, long [][] B, final long MOD) {
		long [][] res = new long[A.length][B[0].length];
		
		for(int row = 0; row < res.length; row++) {
			for(int col = 0; col < res[0].length; col++) {
				res[row][col] = 0;
				
				for(int i = 0; i < A[0].length; i++) {
					res[row][col] += ((A[row][i] % MOD) * (B[i][col] % MOD)) % MOD;
                    res[row][col] %= MOD;
				}
			}
		}
		
		return res;
	}
	
	public static long [][] longIdentity(int rows) {
		
		long [][] res = new long[rows][rows];
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < rows; col++) {
				if(row == col)
					res[row][col] = 1;
				else
					res[row][col] = 0;
			}
		}
		
		return res;
	}
	
	public static long [][] longZeroes(int rows, int cols) {
		
		long [][] res = new long[rows][cols];
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				res[row][col] = 0;
			}
		}
		
		return res;
	}
	
		
	
	public static long [][] modMatExp(long [][] M, long power, final long MOD) {
		
		long [][] res = longIdentity(M.length);		
		
		long [][] base = copy(M);
		
		while(power > 0) {
			if((power & 1L) == 1) {
				res = modMatMult(res, base, MOD);
			}
			
			power>>>=1;
			base = modMatMult(base, base, MOD);
		}
		
		return res;
    }

}
