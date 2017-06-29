package objects;

public class HyperAck {
	private int[][] terms;
	public long saves;
	
	public HyperAck(int m, int n){
		terms = new int[m][n];
		saves = 0;
	}
	
	public int getLength(){
		return terms.length;
	}
	
	public static int hyperoperate(int m, int b) {
		if (m == 0)
			return b + 1;
		else {
			int val = 2;
			for (int i = 1; i < b; i++) {
				val = hyperoperate(m - 1, val);
			}
			if (m == 1)
				val++;
			return val;
		}
	}
	
	public int helperUltraAck(int m, int n){
		//System.out.println(terms.length + ", " + terms[0].length);
		if(m < terms.length && n < terms[0].length && terms[m][n] != 0){
			//saves++;
			return terms[m][n];
		}
		
		int t;
		if (m == 0){
			t = n + 1;
			terms[m][n] = t;;
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
	
	public static int getHypAck(int m, int n) {
		return hyperoperate(m, n + 3) - 3;
	}
	
	public int ultraAck(int m, int n){
		return helperUltraAck(m,n + 3) - 3;
	}
	
}
