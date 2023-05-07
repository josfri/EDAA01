package textproc;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan1 = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();

		while (scan1.hasNext()) {
			stopwords.add(scan1.next().toLowerCase());
		}
		scan1.close();

		GeneralWordCounter counter = new GeneralWordCounter(stopwords);

		Scanner scan2 = new Scanner(new File("nilsholg.txt"));
		scan2.findWithinHorizon("\uFEFF", 1);
		scan2.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

		while (scan2.hasNext()) {
			counter.process(scan2.next().toLowerCase());
		}
		scan2.close();

		new BookReaderController(counter);

	}

}
