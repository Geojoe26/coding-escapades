import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class WordAutoCorrect {

    /*
     * Complete the 'getSearchResults' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY words
     *  2. STRING_ARRAY queries
     */

    private static String wordSort(String input){
        char[] alpha = input.toCharArray();
        Arrays.sort(alpha);
        return new String(alpha);
    }
    
    public static List<List<String>> getSearchResults(List<String> words, List<String> queries) {
    // Write your code here
    
    Map<String, List<String>> wordMap = new HashMap<>();
    
    for(String a:words)
    {
        String sortedWord = wordSort(a);
        wordMap.computeIfAbsent(sortedWord, x -> new ArrayList<>()).add(a);
    }
    
    List<List<String>> outputList = new ArrayList<>();
    
    for(String b:queries)
    {
        String sortQuery = wordSort(b);
        List<String> anagrams = wordMap.getOrDefault(sortQuery, new ArrayList<>());
        Collections.sort(anagrams);
        outputList.add(anagrams);
    }

    return outputList;
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int wordsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = IntStream.range(0, wordsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<List<String>> result = Result.getSearchResults(words, queries);

        result.stream()
            .map(
                r -> r.stream()
                    .collect(joining(" "))
            )
            .map(r -> r + "\n")
            .collect(toList())
            .forEach(e -> {
                try {
                    bufferedWriter.write(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
