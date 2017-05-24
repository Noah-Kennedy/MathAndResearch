
public class SingleNumberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 4;
		int k = 2;
		Ackerman a = new Ackerman(4,2);
		System.out.println("(" + i + " , " + k + "): " + a.ackerman(i, k) + " n: " + a.counter + " Real counter: " + a.realCounts);
	}

}
