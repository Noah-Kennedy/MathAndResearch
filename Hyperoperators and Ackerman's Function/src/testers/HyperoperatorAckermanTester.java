package testers;

import java.awt.Toolkit;

import utilityClasses.Ackerman;
import utilityClasses.HyperAck;

public class HyperoperatorAckermanTester {
	public static void main(String[] args){
		//HyperAck  hyp = new HyperAck(4,1);
		//Ackerman ack = new Ackerman(4,1);
		for(short i = 0; i <= 3; i++){
			for(short j = 0; j <= 12; j++){
				long t = System.currentTimeMillis();
				//HyperAck  hyp = new HyperAck(4,1);
				//hyp.superAckermann(i, j);
				HyperAck.getSimpleAckermann(i, j);
				t = System.currentTimeMillis() - t;
				int milliHypSec = (int) (t % 1000);
				int hypSec = (int) (t / 1000);
				int hypMin = hypSec / 60;
				hypSec = hypSec % 60;
				int hypHour = hypMin / 60;
				hypMin = hypMin % 60;
				
				long l = System.currentTimeMillis();
				//Ackerman ack = new Ackerman(i,j);
				//ack.ackerman(i, j);
				Ackerman.oldAckerman(i, j);
				l = System.currentTimeMillis() - l;
				int milliHypSecOld = (int) (l % 1000);
				int hypSecOld = (int) (l / 1000);
				int hypMinOld = hypSecOld / 60;
				hypSecOld = hypSecOld % 60;
				int hypHourOld = hypMinOld / 60;
				hypMinOld = hypMinOld % 60;
				
				
				System.out.println("M: " + i + " N: " + j + " HyperAck: " + hypHour + " hours, " + hypMin + " minutes, " + hypSec + " seconds, " + milliHypSec + " milliseconds ___" + " Old Ack: " + hypHourOld + " hours, " + hypMinOld + " minutes, " + hypSecOld + " seconds, " + milliHypSecOld + " milliseconds");
				//Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}
