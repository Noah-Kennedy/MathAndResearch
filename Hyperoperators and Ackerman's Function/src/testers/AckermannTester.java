package testers;

import utilityClasses.HyperAck;

public class AckermannTester {
	public static void main(String[] args){
		//HyperAck a = new HyperAck(3,15);
		for(short i = 0; i <= 10; i++){
			for(short j = 0; j <= 1000; j++){
				//HyperAck a = new HyperAck(i,j+3);
				System.out.println("M: " + i + " N: " + j + " = " + HyperAck.getSimpleAckermann(i, j));
			}
		}
		
	}
}
