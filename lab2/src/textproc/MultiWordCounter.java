package textproc;

import java.util.Map;
import java.util.TreeMap;
//import java.util.HashMap;

public class MultiWordCounter implements TextProcessor {

	private Map<String, Integer> m;

	public MultiWordCounter(String[] list) {

		m = new TreeMap<String, Integer>();

		for (int i = 0; i < list.length; i++) {

			m.put(list[i], 0);
		}
	}

	@Override
	public void process(String w) {

		if (m.containsKey(w)) {
			int n = m.get(w) + 1;
			m.put(w, n);
		}

	}

	@Override
	public void report() {

		for (String key : m.keySet()) {

			System.out.println(key + ": " + m.get(key));

		}

	}

}
