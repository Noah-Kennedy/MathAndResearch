package testers;

import utilityClasses.Ackerman;

public class AckermanTester {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Ackerman b = new Ackerman(3,25);
		for(int i = 0; i <= 4; i++){
			for(int k = 0; k <= 10; k++){
				//old ackerman
				//System.out.println("(" + i + " , " + k + "): " + Ackerman.oldAckerman(i, k) + " n: " + Ackerman.c);
				//Ackerman.c = 0;
				
				//new
				
				
				
				Ackerman a = new Ackerman(i, k);
				System.out.println("(" + i + " , " + k + "): " + a.ackerman(i, k) + " Real counter: ");

				//System.out.println("(" + i + " , " + k + "): " + b.ackerman(i, k) + " Real counter: " + b.realCounts);
			}
		}
		//return b.results;
	}
}
