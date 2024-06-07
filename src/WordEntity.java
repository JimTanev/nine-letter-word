import java.util.ArrayList;
import java.util.List;

public class WordEntity {
    private String word;
    private boolean isValidWord;

    private WordEntity() {}

    public static WordEntity create(String word, List<String> validWords) {
        WordEntity wordElement = new WordEntity();
        wordElement.word = word;
        wordElement.isValidWord = isThereOneLetterShortWordsIn(word, validWords);
        return wordElement;
    }

    private static boolean isThereOneLetterShortWordsIn(String word, List<String> validWords) {
        List<String> oneLetterShortWords = createOneLetterShortWords(word);
        for(String oneLetterShortWord : oneLetterShortWords) {
            if(validWords.contains(oneLetterShortWord)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> createOneLetterShortWords(String word) {
        List<String> oneLetterShortWords = new ArrayList<>();

        // Remove first character
        oneLetterShortWords.add(word.substring(1));

        // Remove one of middle characters
        int wordLength = word.length();
        for(int i = 1; i < wordLength-1; i++) {
            oneLetterShortWords.add(word.substring(0, i) + word.substring(i+1));
        }

        // Remove last character
        oneLetterShortWords.add(word.substring(0, wordLength-1));

        return oneLetterShortWords;
    }

    public String getWord() {
        return word;
    }


    public boolean isValidWord() {
        return isValidWord;
    }
}
