import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class NineLetterWordsMain {

    public static void main(String[] args) throws IOException {
        List<String> allWords = loadAllWords();

        Instant start = Instant.now();

        printAllNineLetterValidWords(allWords);

        Duration duration = Duration.between(start, Instant.now());
        // Execution time in format - minutes : seconds : milliseconds
        System.out.println(duration.toMinutes() + " : " + duration.toSeconds() + " : " + duration.toMillis());
    }

    private static List<String> loadAllWords() throws IOException {
        URL wordsUrl = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
            return br.lines().skip(2).toList();
        }
    }

    private static void printAllNineLetterValidWords(List<String> allWords) {
        List<String> filteredWords = filterUnnecessaryWords(allWords);
        if(Objects.isNull(filteredWords)) {
            System.out.println("There are no nine letter valid words.");
        }
        List<String> validNineLetterWords = findAllNineLetterValidWords(filteredWords);
        System.out.println(validNineLetterWords.size());
    }

    private static List<String> filterUnnecessaryWords(List<String> allWords) {
        if(allWords.isEmpty()) {
            return null;
        }
        return allWords.stream()
                .filter(word -> word.length() <= 9)
                .filter(word -> word.contains("I") || word.contains("A"))
                .toList();
    }

    private static List<String> findAllNineLetterValidWords(List<String> filteredWords) {
        Dictionary dictionary = Dictionary.create(filteredWords);
        List<String> threeLetterValidWords = getValidWords(dictionary.getThreeLetterWords(), dictionary.getTwoLetterWords());
        List<String> fourLetterValidWords = getValidWords(dictionary.getFourLetterWords(), threeLetterValidWords);
        List<String> fiveLetterValidWords = getValidWords(dictionary.getFiveLetterWords(), fourLetterValidWords);
        List<String> sixLetterValidWords = getValidWords(dictionary.getSixLetterWords(), fiveLetterValidWords);
        List<String> sevenLetterValidWords = getValidWords(dictionary.getSevenLetterWords(), sixLetterValidWords);
        List<String> eightLetterValidWords = getValidWords(dictionary.getEightLetterWords(), sevenLetterValidWords);
        return getValidWords(dictionary.getNineLetterWords(), eightLetterValidWords);
    }

    private static List<String> getValidWords(List<String> words, List<String> oneLetterShortWords) {
        return words.stream()
                .map(word -> WordEntity.create(word, oneLetterShortWords))
                .filter(WordEntity::isValidWord)
                .map(WordEntity::getWord)
                .toList();
    }
}