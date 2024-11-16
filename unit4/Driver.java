import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner inFile = new Scanner (new File ("alice.txt"));
		HashMap<String, Word> map = new HashMap<>();
		long startTime = System.currentTimeMillis();

		while (inFile.hasNextLine()) {
			String s = inFile.nextLine();
			StringTokenizer st = new StringTokenizer(s, " !@#$%^&*()=_+:;\"\\`~<,>.?/1234567890");

			while (st.hasMoreTokens()) {
				
				String token = st.nextToken();
				
				if (!token.equals("-") && !token.equals("\'")) {
					token = token.toLowerCase();
					
					while (token.length() >= 1 && token.charAt(0) == '-') {
						token = token.substring(1, token.length());
					}
					
					if (token.length() >= 1 && (token.charAt(token.length()-1) == '-' ||  token.charAt(token.length()-1) == '\'')) {
						token = token.substring(0, token.length()-1);
					}
					
					
					
					if (token.length() > 2) {
						if (token.substring(token.length()-2).equals("\'s")) {
							token = token.substring(0, token.length()-2);
						}
					}
					
					if (token.length() >= 1 && token.charAt(0) == '\'') {
						token = token.substring(1);
					}
					
					Word w = new Word(token);
					
					if (map.containsKey(token)) {
						map.get(token).addWord();
					}
					
					else {
						map.put(token, w);
					}
				}
			}
		}
		
		System.out.println(map.size());
		List<Word> list = new ArrayList<>(map.values());
		Collections.sort(list);
//		Collections.reverse(list);
		System.out.println(list.subList(0, 20));
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime + " ms");

	}
}
