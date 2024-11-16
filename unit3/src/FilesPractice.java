import java.util.*;
import java.io.*;

public class FilesPractice {

	public static void main(String[] args) throws IOException {
		Scanner inFile = new Scanner(new File("input.txt"));

//		while (inFile.hasNextLine()) {
//			String line = inFile.nextLine();
//			System.out.println(line);
//		}
//		inFile.close();
		
		// to write to a file
		PrintWriter outFile = new PrintWriter (new FileWriter ("haha.txt", true));
		outFile.println("hahahahaa");
		outFile.println("not funnnnny");
		outFile.println("hihih");

		outFile.close();
	}

}
