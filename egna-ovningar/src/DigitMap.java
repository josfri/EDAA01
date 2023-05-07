import java.util.Map;
import java.util.TreeMap;

public class DigitMap {

	public static void main(String[] args) {
		
		Map<Integer, Integer> map = digitMap(101, 125, 2);
		System.out.println(map);

	}

	public static Map<Integer, Integer> digitMap(int start, int stop, int interval) {

		Map<Integer, Integer> nbrs = new TreeMap <Integer, Integer>();

		for (int i = 0; i < 10; i++) {
		nbrs.put(i,0);
		}

		for (int i = start; i < stop + 1; i+=interval) {
		    
		    Integer h = i / 100;
		    Integer t = (i - i*h)/ 10;
		    Integer e = i % 10;
		    
		    Integer x = nbrs.get(h);
		    nbrs.put(h,x+1);
		    
		     Integer y = nbrs.get(h);
		    nbrs.put(t,y+1);
		    
		     Integer z = nbrs.get(h);
		    nbrs.put(e,z+1);
		}




		return nbrs;
		}
}
