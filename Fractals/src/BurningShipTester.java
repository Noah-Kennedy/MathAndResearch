
public class BurningShipTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int iterations = 1000;
		BurningShip m = new BurningShip(6984);
		/*for(int i = 1; i <= iterations; i++){
			m.changeMax(i);
			m.drawSet();
			if (i < iterations){
				m.erase();
			}
			//System.out.println(i);
			m.mc.drawString(Integer.toString(i), 900, 900);
		}*/
		m.drawSet();
	}

}
