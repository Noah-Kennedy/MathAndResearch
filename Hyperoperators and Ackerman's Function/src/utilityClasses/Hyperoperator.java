package utilityClasses;

public class Hyperoperator {
	
	/**
	 * Precondition: a and b are positive integers
	 * @param m
	 * @param a
	 * @param b
	 * @return the mth hyperoperation of a and b
	 */
	public static long hyperoperate(long m, long a, long b){
		if(m == 0) return b + 1;
		else{
			long val = a;
			for(int i = 1; i < b; i++){
				val = hyperoperate(m-1,a,val);
			}
			if(m == 1) val++;
			return val;
		}
	}
	
	/**
	 * Precondition: Positive integers are the input. Zero is fine
	 * @param m
	 * @param n
	 * @return The value of the ackermann function with the input of (m,n) computed with the hyperoperator function
	 */
	public static long getAckermann(long m,long n){
		return hyperoperate(m,2,n+3) - 3;
	}
}
