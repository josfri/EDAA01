import java.util.Deque;
import java.util.ArrayDeque;

public class pushpop {

	public static void main(String[] args) {
		
		System.out.println(matches("[]"));
		//System.out.println(matches("( { [(abc){} ()] de} )f"));
		//System.out.println(matches("[ ()"));
		//System.out.println(matches("() }"));

	}

	public static boolean matches(String s) {

		Deque<Character> d = new ArrayDeque<Character>();

		for (char h: s.toCharArray()) {
		    
		    if ((h == '(') || (h == '{') || (h == '[')) {
		        d.push(h);
		        System.out.println(h);
		        
		    } else if (h == ')' || h == '}' || h == ']') {
		        char c = d.pop();
		        System.out.println(h);
		        
		        if ((c != h)) {
		        	System.out.println("inte samma");
		            return false;
		        } 
		    }
		}

		if (d.size() == 0) {
		    return true;
		} else {
		    return false;
		}
		}
}
