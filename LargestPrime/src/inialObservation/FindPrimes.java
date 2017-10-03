package inialObservation;

import javax.swing.JOptionPane;

public class FindPrimes {
	
	public static void simplePrime(int upto) {
		
		//first loop, go through ints
		for(int i = 1; i <= upto; i += 2) {
			
			boolean isPrime = true;
			//second loop, see if divisible by numbers less than it
			for(int j = 2; j < i; j++) {
				
				if(i % j == 0) isPrime = false;
			}
			
			if(isPrime) System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		int upto = Integer.MAX_VALUE;
		simplePrime(upto);
	}
}
