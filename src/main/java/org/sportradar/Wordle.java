package org.sportradar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Wordle {
    private static final String WORD_LIST_FILE = "src/main/resources/dictionary.txt";

    private static final int MAX_ATTEMPTS = 5;
    private static final int WORD_LENGTH = 5;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printIntroduction();

        String chosenWord = chooseTheWordFromDictionary();

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.print("Attempt " + attempt + ": Enter your 5 letter guess word: ");
            String guess = scanner.nextLine().trim().toLowerCase();

            if (guess.length() != WORD_LENGTH) {
                System.out.println("Guess must be exactly 5 letters.");
                attempt--;
                continue;
            }

            Map<Integer, Status> result = checkIfGuessedWordMatches(chosenWord, guess);
            displayResult(guess, result);

            if (guess.equals(chosenWord)) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                return;
            }
        }

        System.out.println("Out of attempts! The correct word was: " + chosenWord);
    }

    private static void printIntroduction() {
        System.out.println("Welcome to Wordle!");
        System.out.println("Players have 5 attempts to guess a five-letter word, receiving feedback through colors that indicate correct letters and their placement");
        System.out.println("GREEN: Correct letter in the correct position");
        System.out.println("YELLOW: Letter exists but in the wrong position");
        System.out.println("BLACK: Letter not in the word\n");
    }

    public static String chooseTheWordFromDictionary() {

        try {
            List<String> words = Files.readAllLines(Paths.get(WORD_LIST_FILE));

            if (words.isEmpty()) {
                System.out.println("Dictionary is empty.");
            }

            int randomIndex = new Random().nextInt(words.size());
            return words.get(randomIndex);
        } catch (IOException e) {
            System.out.println("Error reading word list: " + e.getMessage());
            return null;
        }
    }

    public static Map<Integer,Status> checkIfGuessedWordMatches(String chosenWord, String guessWord){
        Map<Integer,Status> result=new HashMap<Integer,Status>();
        Map<Character,Integer> lettersCount=new HashMap<Character,Integer>();

        //First loop to get the count of letters
        for (char c : chosenWord.toCharArray()) {
            lettersCount.put(c, lettersCount.getOrDefault(c, 0) + 1);
        }

        // Second loop to mark GREEN values
        for(int i=0;i<WORD_LENGTH;i++){
            if(guessWord.charAt(i) == chosenWord.charAt(i)){
                result.put(i,Status.GREEN);
                lettersCount.put(guessWord.charAt(i), lettersCount.get(guessWord.charAt(i)) - 1);
            }
        }

        // Third loop to mark Yellow  and Black values
        for (int i = 0; i < guessWord.length(); i++) {
            if (result.containsKey(i)) continue; // already marked green

            char guessChar = guessWord.charAt(i);
            if (lettersCount.getOrDefault(guessChar, 0) > 0) {
                result.put(i, Status.YELLOW);
                lettersCount.put(guessChar, lettersCount.get(guessChar) - 1); // consume it
            } else {
                result.put(i, Status.BLACK);
            }
        }

        return result;
    }

    private static void displayResult(String guess, Map<Integer, Status> result) {
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            System.out.println("Char : " + c + " ; Result :" + result.getOrDefault(i,Status.BLACK).getLabel() );
        }
        System.out.println();
    }
}