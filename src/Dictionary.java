import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
    private List<String> nineLetterWords = new ArrayList<>();
    private List<String> eightLetterWords = new ArrayList<>();
    private List<String> sevenLetterWords = new ArrayList<>();
    private List<String> sixLetterWords = new ArrayList<>();
    private List<String> fiveLetterWords = new ArrayList<>();
    private List<String> fourLetterWords = new ArrayList<>();
    private List<String> threeLetterWords = new ArrayList<>();
    private List<String> twoLetterWords = new ArrayList<>();

    private Dictionary() {
    }

    public static Dictionary create(List<String> words) {
        Dictionary dictionary = new Dictionary();
        for (String word : words) {
            switch (word.length()) {
                case 9 -> dictionary.nineLetterWords.add(word);
                case 8 -> dictionary.eightLetterWords.add(word);
                case 7 -> dictionary.sevenLetterWords.add(word);
                case 6 -> dictionary.sixLetterWords.add(word);
                case 5 -> dictionary.fiveLetterWords.add(word);
                case 4 -> dictionary.fourLetterWords.add(word);
                case 3 -> dictionary.threeLetterWords.add(word);
                case 2 -> dictionary.twoLetterWords.add(word);
            }
        }
        createWordLists(dictionary);
        return dictionary;
    }

    private static void createWordLists(Dictionary dictionary) {
        dictionary.nineLetterWords = convertList(dictionary.nineLetterWords);
        dictionary.eightLetterWords = convertList(dictionary.eightLetterWords);
        dictionary.sevenLetterWords = convertList(dictionary.sevenLetterWords);
        dictionary.sixLetterWords = convertList(dictionary.sixLetterWords);
        dictionary.fiveLetterWords = convertList(dictionary.fiveLetterWords);
        dictionary.fourLetterWords = convertList(dictionary.fourLetterWords);
        dictionary.threeLetterWords = convertList(dictionary.threeLetterWords);
        dictionary.twoLetterWords = convertList(dictionary.twoLetterWords);
    }

    private static List<String> convertList(List<String> words) {
        if(words.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableList(words);
    }

    public List<String> getNineLetterWords() {
        return nineLetterWords;
    }

    public List<String> getEightLetterWords() {
        return eightLetterWords;
    }

    public List<String> getSevenLetterWords() {
        return sevenLetterWords;
    }

    public List<String> getSixLetterWords() {
        return sixLetterWords;
    }

    public List<String> getFiveLetterWords() {
        return fiveLetterWords;
    }

    public List<String> getFourLetterWords() {
        return fourLetterWords;
    }

    public List<String> getThreeLetterWords() {
        return threeLetterWords;
    }

    public List<String> getTwoLetterWords() {
        return twoLetterWords;
    }
}
