package drivers;

import objects.HyperAck;
import objects.SimpleAck;

public class SimpleSuperAckTester {
	public static void main(String[] args){
		int x = Integer.MAX_VALUE / 4;
		//x = 1000;
		for(int m = 0; m <= 3; m++){
			//HyperAck h = new HyperAck(m + 1, x);
			SimpleAck a = new SimpleAck(m + 1, x);
			
			for(int n = 0; n <= 1000; n++){
				//h.saves = 0;
				//a.saves = 0;
				//a.strangeSaves = 0;
				System.out.print("M: " + m + ", N: " + n);


				/*long t0 = System.currentTimeMillis();
				int a0 = SimpleAck.getAck(m, n);
				t0 = System.currentTimeMillis() - t0;
				System.out.print(", Old Val: " + a0);
				System.out.print(", Old Time: " + t0);*/

				//try{
				long t1 = /*System.currentTimeMillis()*/System.nanoTime();
				int a1 = a.getSuperAck(m, n);
				t1 = System.nanoTime() - t1;
				System.out.print(", New Val: " + a1);
				System.out.print(", New Time: " + t1);
				//System.out.print(", Super Saves: " + a.saves);
				/*}catch(StackOverflowError e){
					System.out.println(" ERROR ");
					/*long t1 = System.currentTimeMillis();
					int a1 = a.ultraAck(m, n);
					t1 = System.currentTimeMillis() - t1;
					System.out.print(", New Val: " + a1);
					System.out.print(", New Time: " + t1);
					break;
				}*/
				
				
				long t2 = System.currentTimeMillis();
				int a2 = HyperAck.getHypAck(m, n);
				t2 = System.currentTimeMillis() - t2;
				System.out.print(", Hyp Val: " +  a2);
				System.out.print(", Hyp Time: " + t2);
				
				/*long t3 = System.currentTimeMillis();
				int a3 = h.ultraAck(m, n);
				t3 = System.currentTimeMillis() - t3;
				System.out.print(", Ultra Val: " + a3);
				System.out.print(", Ultra Time: " + t3);
				//System.out.print(", Ultra Saves: " + h.saves);*/
				
				/*long t4 = System.currentTimeMillis();
				int a4 = a.ultraAck(m, n);
				t4 = System.currentTimeMillis() - t4;
				System.out.print(", Strange Val: " + a4);
				System.out.print(", Strange Time: " + t4);*/
				//System.out.print(", Strange Saves: " + a.strangeSaves);
				
				System.out.println(
						//+ ", Super Length: " + a.getLength()
						//+ ", Ultra Length: " + h.getLength()
						//+ ", Super Calcs: " + a.getCalcs()
						//+ ", Super Calls: " + a.getCalls()
						//+ ", Ultra Calcs: " + h.getCalcs()
						//+ ", Ultra Calls: " + h.getCalls()
						);
			}
		}
	}
}
