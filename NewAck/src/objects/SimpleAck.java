package objects;

public class SimpleAck {
	private int[][] terms;
	public int saves;
	public long strangeSaves;
	public int getLength(){
		return terms.length;
	}
	
	public SimpleAck(int m, int n){
		 terms = new int[m][n];
		 strangeSaves = 0;
		 saves = 0;
	}
	
	public int getSuperAck(int m, int n){
		return getSuperAckHelper(m,n);
	}
	
	public int helperUltraAck(int m, int n){
		//System.out.println(terms.length + ", " + terms[0].length);
		if(m < terms.length && n < terms[0].length && terms[m][n] != 0){
			strangeSaves++;
			return terms[m][n];
		}
		
		int t;
		if (m == 0){
			t = n + 1;
			terms[m][n] = t;
			return t;
		} else {
			int val = 2;
			for (int i = 1; i < n; i++) {
				val = helperUltraAck(m - 1, val);
			}
			if (m == 1)
				val++;
			t = val;
			terms[m][n] = t;
			return t;
		}
	}
	
	public int ultraAck(int m, int n){
		return helperUltraAck(m,n + 3) - 3;
	}
	
	private int getSuperAckHelper(int m, int n){
		//search list
		if(m < terms.length && n < terms[0].length && terms[m][n] != 0){
			//saves++;
			return terms[m][n];
		}//else if(m >= terms.length || n >= terms[0].length) System.out.println("Warning");
		//otherwise calculate
		if(m == 0) {
			if(m < terms.length && n < terms[0].length)
				terms[m][n] = n + 1;
			return n + 1;
		} else if(n == 0) {
			if(m < terms.length && n < terms[0].length){
				terms[m][n] = getSuperAckHelper(m-1,1);
				return terms[m][n];
			}else return getSuperAckHelper(m-1,1);
		} else {
			if(m < terms.length && n < terms[0].length){
				terms[m][n] = getSuperAckHelper(m-1,getSuperAckHelper(m,n-1));
				return terms[m][n];
			}else return getSuperAckHelper(m-1,getSuperAckHelper(m,n-1));
		}
	}
	public static int getAck(int m, int n){
		if(m == 0) return n + 1;
		else if(n == 0) return getAck(m-1,1);
		else return getAck(m-1,getAck(m,n-1));
	}
}
