import java.io.*;

public class TextFile1TextAnalyzerNoComments
{
	public static void main(String[] args) {
		try {
			analyzeFile();
		}
		catch (IOException e) {
			System.out.println("An error occured while trying to access file: " + e.getMessage());
		}
	}
	
	public static void analyzeFile() throws IOException {
		File file = new File("hiPogi.txt");
		
		String line;
		int wordCount = 0;
		int sentenceCount = 0;
		int longestWordLength = 0;
		String longestWord = null;
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		while ((line = reader.readLine()) != null) {
			String[] wordArray = line.split("\\s+");
			wordCount += wordArray.length;
			
			for (String word : wordArray) {
				String trimmedWord = word.replaceAll("[!?.]*$", "");
				int wordLength = trimmedWord.length();
				
				if (wordLength > longestWordLength) {
				    longestWordLength = wordLength;
				    longestWord = trimmedWord;
				}
				
				char charSaDulo = word.charAt(word.length() - 1);
				if (charSaDulo == '.' || charSaDulo == '?' || charSaDulo == '!') {
					sentenceCount++;
				}
			}
		}
		
		File newFile = new File("hiPogiUppercase.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
		
		reader.close();
		reader = new BufferedReader(new FileReader(file));
		
		while ((line = reader.readLine()) != null) {
			writer.write(line.toUpperCase());
			writer.newLine();
		}
		
		reader.close();
		writer.close();
		
		printResults(wordCount, sentenceCount, longestWord);
	}
	
	public static void printResults(int wordCount, int sentenceCount, String longestWord) {
		System.out.printf("==========================\n");
		System.out.printf("%-15s\t:  %d\n", "Word Count", wordCount);
		System.out.printf("%-15s\t:  %d\n", "Sentence Count", sentenceCount);
		System.out.printf("%-15s\t:  %s\n", "Longest Word", longestWord != null ? longestWord : "N/A");
	}
}
