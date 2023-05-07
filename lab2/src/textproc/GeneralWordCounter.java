package textproc;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {

	private Map<String, Integer> m;
	private Set<String> s;
	private List<Map.Entry<String, Integer>> wordList;

	public GeneralWordCounter(Set<String> s) {

		this.s = s;
		m = new HashMap<String, Integer>();

	}

	@Override
	public void process(String w) {
		int k = 0;

		for (String word : s) {

			if (word.equals(w)) {
				k++;
			}
		}

		if (!m.containsKey(w) && k == 0) {
			m.put(w, 1);
		} else if (m.containsKey(w) && k == 0) {
			int n = m.get(w) + 1;
			m.put(w, n);
		}

	}

	@Override
	public void report() {

		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		wordList = new ArrayList<>(wordSet);

		wordList.sort((w1, w2) -> {
			if (w2.getValue() - w1.getValue() == 0) {
				return (w1.getKey().compareTo(w2.getKey()));
			} else {
				return w2.getValue() - w1.getValue();
			}

		});

		for (int i = 0; i < 15; i++) {
			System.out.println(wordList.get(i).getKey() + ": " + wordList.get(i).getValue());
		}


	}

	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<>(m.entrySet()); //

	}

}
