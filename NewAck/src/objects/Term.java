package objects;

public class Term /*implements Comparable<Term>*/{
	
	private int m;
	private int n;
	private int val;
	//private int frequency;
	
	public Term(int m, int n){
		this.setM(m);
		this.setN(n);
		//frequency = 0;
	}
	
	public int getM() {
		return m;
	}
	
	private void setM(int m) {
		this.m = m;
	}
	
	public int getN() {
		return n;
	}
	
	private void setN(int n) {
		this.n = n;
	}
	
	public int getVal() {
		//frequency++;
		return val;
	}
	
	public void setVal(int val) {
		this.val = val;
	}
	
	/*public int getFrequency(){
		return frequency;
	}

	@Override
	public int compareTo(Term o) {
		if(frequency > o.getFrequency()) return -1;
		else if(frequency < o.getFrequency()) return 1;
		else return 0;
	}*/
	
	public boolean equals(Object o){
		return ((Term) o).getM() == getM() && ((Term) o).getN() == getN();
	}
	
	/*public String toString(){
		return "" + frequency;
	}*/
	
}
