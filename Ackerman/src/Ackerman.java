
public class Ackerman {
	//long counter;
	int[][] results;
	//long[][] counts;
	long realCounts;
	static long c;
	//static int open;
	
	public Ackerman(int m, int n){
		int m0 = m + 4;
		int n0 = 0;

		if(m == 0 || m == 1 || m == 2){
			n0 = 1 + n;
		}else if (m == 3){
			n0 = (int) Math.pow(2, n + 3) - 3;
		}else if(m == 4){
			n0 = superPowerBinary(n+3,2) - 7;
		}
		//test case
		//System.out.println(n0);
		
		if(n0 < 5){
			n0 = 5;
		}
		results = new int[m0][n0];
		//counts = new long[m0][n0];
		//counter = 0;
		realCounts = 0;
		results[3][0] = 5;
		results[3][1] = 13;
		results[3][2] = 29;
		results[3][3] = 61;
		results[3][4] = 125;
		//test case
		/*if(m == 4 && n == 1){
			System.out.println("m: " + m0 + " n: " + n0);
		}*/
	}
	
	public int getValueFromArray(int m, int n){
		if(m < results.length && n < results.length){
			return results[m][n];
		}else{
			return ackerman(m,n);
		}
	}
	
	public void setValueFromArray(int m, int n){
		if(m < results.length && n < results.length){
			results[m][n] = ackerman(m,n);
		}
	}
	//this is best
	public static int oldAckerman(int m, int n){
		c++;
		//open++;
		if(m == 0){
			//open--;
			return n + 1;
		}
		if(m > 0 && n == 0){
			//open--;
			return oldAckerman(m-1,1);
		}
		if(m > 0 && n > 0){
			//open--;
			return oldAckerman(m-1,oldAckerman(m,n-1));
		}
		return 90000000;
	}
	
	public static int superPowerBinary(int t, int p){
		int val = 2;
		for(int j = 1; j <= t; j++){
			val = (int) Math.pow(val, p);
		}
		return val;
	}
	
	//new version sucks
	/** Precondition: m and n are not negative
	 * Postcondition: counter = number of times function called
	 * @param m
	 * @param n
	 * @return function value for m,n
	 */
	public int ackerman(int m, int n){
		realCounts++;
		//System.out.println("M: " + m + " N: " + n);
		if(results[m][n] != 0){
			//counter += counts[m][n];
			return results[m][n];
		}else if(m == 0){
			results[m][n] = n + 1;
			return results[m][n];
		}else if(m == 1) {
			results[m][n] = n + 2;
			return results[m][n];
		}else if(m == 2){
			results[m][n] = 2 * n + 3;
			return results[m][n];
		}else if(m > 0 && n == 0){
			int newM = m-1;
			if(results[newM][1] != 0){
				//counter += counts[newM][1];
				return results[newM][1];
			}else{
				results[newM][1] = ackerman(newM,1);
				return results[newM][1];
			}
		}else if(m > 0 && n > 0){
			int newM = m - 1;
			int rn = n - 1;
			int res;
			if(results[m][rn] != 0){
				res = results[m][rn];
				//counter += counts[m][rn];
			}else{
				results[m][rn] = ackerman(m,rn);
				res = results[m][rn];
			}
			if(results[newM][res] != 0){
				//counter += counts[newM][res];
				return results[newM][res];
			}else{
				results[newM][res] = ackerman(newM,res);
				return results[newM][res];
			}
			
		}
		return 90000000;
	}

}
