import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class Solution {
	
	public static void main(String [] args) {
		
		//long time = System.currentTimeMillis();
		
		FastInput in = new FastInput(System.in);
		FastOutput out = new FastOutput(System.out);
		
		// SOLUTION GOES HERE
		
		
		out.flush();
		
		//System.err.println("Time: " + (System.currentTimeMillis() - time)/1000.0);
	}
	
	
	// fast IO
	
	public static class FastInput {
		private InputStream is;
		private int bufflen;
		private int buffi;
		private byte [] buffer = new byte[1024];
		private boolean EOF = false;
		private WhiteSpaceFilter wsfilter  = null; 
		
		public FastInput(InputStream i) {
			is = i;
			bufflen = 0;
			buffi = 0;
		}
		
		public FastInput(InputStream i, WhiteSpaceFilter wsfilter) {
			this(i);
			this.wsfilter = wsfilter;
		}
		
		public FastInput(String inputString) {
			is = new ByteArrayInputStream(inputString.getBytes());
			bufflen = 0;
			buffi = 0;
		}
		
		public FastInput(String inputString, WhiteSpaceFilter wsfilter) {
			this(inputString);
			this.wsfilter = wsfilter; 
		}	
		
		public boolean atEOF() {
			return EOF;
		}
		
		public byte nextByte() {
			if(bufflen == -1) {
				throw new InputMismatchException();
			}
			if(buffi >= bufflen) {
				buffi = 0;
				try {
					bufflen = is.read(buffer);	
				}
				catch(IOException e) {
					throw new InputMismatchException();
				}
				if(bufflen <= 0){ 
					EOF = true;
					return -1;
				}
			}
			
			return buffer[buffi++];
		}
		
		public char nextChar() {
			return (char)nextByte();
		}
		
		public int nextInt() {
			byte c = nextByte();
			while(isWhiteSpace(c)) {
				c = nextByte();
			}
			int sign = 1;
			if(c == '-'){ 
				sign = -1;
				c = nextByte();
			}
			
			int result = 0;
			do {
				if(c < '0' || c > '9')
					throw new InputMismatchException();
				result *= 10;
				result += c - '0';
				c = nextByte();
			}while(!isWhiteSpace(c));
			
			return result * sign;
		}
		
		public long nextLong() {
			byte c = nextByte();
			while(isWhiteSpace(c)) {
				c = nextByte();
			}
			int sign = 1;
			if(c == '-'){ 
				sign = -1;
				c = nextByte();
			}
			
			long result = 0;
			do {
				if(c < '0' || c > '9')
					throw new InputMismatchException();
				result *= 10;
				result += c - '0';
				c = nextByte();
			}while(!isWhiteSpace(c));
			
			return result * sign;
		}
		
		public String next() {
            byte c = nextByte();
            while (isWhiteSpace(c))
                c = nextByte();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = nextByte();
            } while (!isWhiteSpace(c));
            return res.toString();
        }
	     
		 public double nextDouble() {
            byte c = nextByte();
            while (isWhiteSpace(c))
                c = nextByte();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = nextByte();
            }
            double res = 0;
            while (!isWhiteSpace(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = nextByte();
            }
            if (c == '.') {
                c = nextByte();
                double m = 1;
                while (!isWhiteSpace(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = nextByte();
                }
            }
            return res * sgn;
        }

		private boolean isWhiteSpace(byte c) {
			
			if(wsfilter != null)
				return wsfilter.isWhiteSpace(c);
			
			return c == ' '||c=='\t'||c=='\n'||c=='\r' || c == -1;
		}
		
		public static interface WhiteSpaceFilter {
			public boolean isWhiteSpace(byte c);
		}
		
	}
	
	public static class FastOutput {
		PrintWriter pw;
		
		public FastOutput(OutputStream o) {
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(o)));
		}
		
		public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++)
            {
                if (i != 0)
                    pw.print(' ');
                pw.print(objects[i]);
            }
        }
		
		public void println(Object... objects) {
			print(objects);
			pw.println();
		}
		
		public void flush() {
			pw.flush();
		}
		
		public void close() {
			pw.close();			
		}
		
	}
}
