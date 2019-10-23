
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class WordFrequency {

	public static void main(String[] args) throws FileNotFoundException {
		SCHashTable ht = new SCHashTable(13);
		ArrayList<String> fileText = new ArrayList<String>();
		ArrayList<String> stoplist = new ArrayList<String>();
		ArrayList<String> noDup = new ArrayList<String>();
		ArrayList<Integer> occur = new ArrayList<Integer>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int maxVal = 0;
		int count = 0;
		String finalWords = "";
		

	// FILE1
		try {
			BufferedReader text = new BufferedReader(new FileReader(args[0]));
			Scanner sc = new Scanner(text);
			String str = "";
			while (sc.hasNextLine()) {
				str += sc.nextLine();
			}

			sc.close(); // Stopping file scanner

			String[] split = str.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

			for (int i = 0; i < split.length; i++) { // Moving split array to ArrayList
				fileText.add(split[i]);
			}

		// STOPWORDS
			
			BufferedReader stop = new BufferedReader(new FileReader(args[1]));
			Scanner sc2 = new Scanner(stop);
			while (sc2.hasNextLine()) {
				stoplist.add(sc2.nextLine());
			}

			sc2.close(); // Stopping stoplist scanner

		// REMOVE STOPWORDS

			for (int i = 0; i < stoplist.size(); i++) {
				while (fileText.contains(stoplist.get(i))) {
					fileText.remove(fileText.indexOf(stoplist.get(i)));
				}
			}

		// PUT INTO HASHTABLE
			
			for (int i = 0; i < fileText.size(); i++) {
				String key = fileText.get(i);
				if (ht.get(key) == -1) {
					ht.put(key, 1);
				} else {
					ht.put(key, ht.get(key) + 1);
				}
			}

		// FIND HIGHEST OCCURENCE
			
			// This eliminates duplicates from the fileText and puts them
			// into a no array called noDup.
			for (int i = 0; i < fileText.size(); i++) { 
				String key = fileText.get(i);
				if (!noDup.contains(key)) {
					noDup.add(key);
				}
			}
			
			// This fills the array 'occur' with all the .values of the keys in noDup
			for (int i = 0; i < noDup.size(); i++) {
				String key = noDup.get(i);
				if (ht.containsKey(key)) {
					occur.add(ht.get(key));
				}
			}

			// This detects the highest value in 'occur,'
			
			for (int i = 0; i < occur.size(); i++) {
				int currVal = occur.get(i);
				if (currVal >= maxVal) {
					maxVal = currVal;
				}
			}
			
			// This fills the array 'indices' with the indices that maxVal
			// appears at.
			for(int i = 0; i<occur.size(); i++) {
				int current = occur.get(i);
				if(current == maxVal) {
					indices.add(i);
				}
			}
		
			// This creates a final string to print. I added a count so that if there
			// are multiple words with the same high occurrence, it adds an 'and' before the 
			// last. It's just for appearances.
			for (int i : indices) {
				count++;
				String word = noDup.get(i);
				if (count != indices.size()) {
					finalWords += "\"" + word + ",\"" + " ";
				} else {
					finalWords += "and " + "\"" + word + ",\" ";
				}
			}
			
			System.out.println(ht.toString());
			
			// This prints 'finalWords'. Chooses from 2 different print statements
			// depending on if there is only 1 high occurring word or if there are more
			if (indices.size() > 1) {
				System.out.println("The words with the most occurrences are " 
						+ finalWords + "appearing "
						+ maxVal + " times.");
			} else {
				String finalWords2 = finalWords.replaceAll("and", ""); // Gets rid of 'and'
				System.out.println("The word with the most occurrences is" 
						+ finalWords2 + "appearing "
						+ maxVal + " times.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file. Please try a different argument.");
		}
	}
}
