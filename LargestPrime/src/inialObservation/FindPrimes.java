package inialObservation;

import javax.swing.JOptionPane;

public class FindPrimes {
	
	public static void simplePrimeUpto(int upto) {
		
		//first loop, go through ints
		for(int i = 1; i <= upto; i += 2) {
			
			boolean isPrime = true;
			
			//second loop, see if divisible by numbers less than it
			for(int j = 2; j < i / 2; j++) {
				
				if(i % j == 0) {
					isPrime = false;
					break;
				}
				
			}
			
			if(isPrime) System.out.println(i);
			
		}
	}
	
	public static void main(String[] args) {
		//int upto = Integer.parseInt(JOptionPane.showInputDialog("Enter a number to go upto"));
		int upto = Integer.MAX_VALUE;
		simplePrimeUpto(upto);
	}
}
