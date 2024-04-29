import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Word Counter!");

        String inputText = getUserInput(scanner);
        String[] words = extractWords(inputText);

        int wordCount = countWords(words);
        HashMap<String, Integer> wordFrequency = calculateWordFrequency(words);

        System.out.println("Total words: " + wordCount);
        System.out.println("Word frequency:");
        printWordFrequency(wordFrequency);

        scanner.close();
    }

    private static String getUserInput(Scanner scanner) {
        System.out.print("Enter text or specify a file path: ");
        String userInput = scanner.nextLine().trim();

        try {
            File file = new File(userInput);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                StringBuilder sb = new StringBuilder();
                while (fileScanner.hasNextLine()) {
                    sb.append(fileScanner.nextLine()).append(" ");
                }
                fileScanner.close();
                return sb.toString().trim(); // Return content of the file
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Proceeding with user input...");
        }

        return userInput; // If not a valid file path, return user input
    }

    private static String[] extractWords(String inputText) {
        // Split input text into words by whitespace or punctuation (excluding apostrophes)
        return inputText.split("[\\s\\p{Punct}&&[^']]+");
    }

    private static int countWords(String[] words) {
        int count = 0;
        for (String word : words) {
            if (!word.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static HashMap<String, Integer> calculateWordFrequency(String[] words) {
        HashMap<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                String normalizedWord = word.toLowerCase();
                wordFrequency.put(normalizedWord, wordFrequency.getOrDefault(normalizedWord, 0) + 1);
            }
        }
        return wordFrequency;
    }

    private static void printWordFrequency(HashMap<String, Integer> wordFrequency) {
        for (String word : wordFrequency.keySet()) {
            System.out.println(word + ": " + wordFrequency.get(word));
        }
    }
}
