package inialObservation;

public class FindPrimes {
	
	public static void simplePrimeUpto(long upto) {
		
		//first loop, go through longs
		for(long i = 1; i <= upto; i += 2) {
			
			boolean isPrime = true;
			
			//second loop, see if divisible by numbers less than it
			for(long j = 2; j < i / 2; j++) {
				
				if(i % j == 0) {
					isPrime = false;
					break;
				}
				
			}
			
			if(isPrime) System.out.println(i);
			
		}
	}
	
	public static void main(String[] args) {
		//long upto = Long.parseInt(JOptionPane.showInputDialog("Enter a number to go upto"));
		long upto = Long.MAX_VALUE;
		simplePrimeUpto(upto);
	}
}
