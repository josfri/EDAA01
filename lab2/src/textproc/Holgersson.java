package textproc;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = {"blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();

   
		ArrayList<TextProcessor> a = new ArrayList<TextProcessor>();
		
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor q = new SingleWordCounter("norge");
		TextProcessor l = new MultiWordCounter(REGIONS);
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new TreeSet<String>();
		
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			
			stopwords.add(word);
		}

		scan.close();
	
		TextProcessor k = new GeneralWordCounter(stopwords);
		a.add(p);
		a.add(q);
		a.add(l);
		a.add(k);
		
		
		Scanner s = new Scanner(new File("nilsholg.txt"));

		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			for (TextProcessor x : a) {
				x.process(word);
			}
		}

		s.close();
		
	
		
		for (TextProcessor x : a) {
			x.report();
		}
		 long t1 = System.nanoTime();
	     System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		
	}
}