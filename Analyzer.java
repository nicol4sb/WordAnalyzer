import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nick on 01.08.17.
 * Find what word the letters m p r a d e e o n can form
 */
public class Analyzer {

    public static void main(String... args) {

        List<String> permutations = Analyzer.getPermutations("mpradeeon");

        System.out.println(permutations.size() + " permutations found.");
        for (String validWord : getValidWordsfromList(permutations)) {
            System.out.println(validWord);
        }

    }


    private static List<String> getPermutations(String chars) {
        return getPermutations("", chars);
    }

    private static List<String> getPermutations(String prefix, String remainingChars) {
        List<String> results = new ArrayList<>();
        int numberOfremainigChars = remainingChars.length();
        if (numberOfremainigChars == 0) results.add(prefix);
        else {
            for (int i = 0; i < numberOfremainigChars; i++) {
                results.addAll(
                        getPermutations(prefix + remainingChars.charAt(i), remainingChars.substring(0, i) + remainingChars.substring(i + 1, numberOfremainigChars))
                );
            }
        }
        return results;
    }


    public static List<String> getValidWordsfromList(List<String> permutations) {

        List<String> wordsFoundinDictionary = new ArrayList<>();
        Set<String> wordsinDictionary = new HashSet<>();

        // 1 load list in memory
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    "./src/francais.txt"));
            String wordInDictionary;
            while ((wordInDictionary = in.readLine()) != null) {
                wordsinDictionary.add(wordInDictionary);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(wordsinDictionary.size()+" words in dictionary");

        for (String permutation : permutations) {
            if(wordsinDictionary.contains(permutation))
                wordsFoundinDictionary.add(permutation);
        }

        return wordsFoundinDictionary;
    }


}
