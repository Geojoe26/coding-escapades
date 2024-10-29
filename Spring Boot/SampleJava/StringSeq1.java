import java.util.*;

public class StringSeq1 {

    public static int freqSubstring(String s, int minlen, int maxlen, int maxUnique)
    {
        int maxFrequency =0;

        for (int i = 0; i <= s.length() - minlen; i++) {
            for (int j = minlen; j <= maxlen && i + j <= s.length(); j++) {
                String substring = s.substring(i, i + j);
                if (countUniqueCharacters(substring) <= maxUnique) {
                    int frequency = countFrequency(s, substring);
                    maxFrequency = Math.max(maxFrequency, frequency);
                }
            }
        }

        return maxFrequency;
    }

    public static int countUniqueCharacters(String s) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap.size();
    }

    public static int countFrequency(String input, String substring) {
        int frequency = 0;
        int index = 0;
        while ((index = input.indexOf(substring, index)) != -1) {
            frequency++;
            index += substring.length();
        }
        return frequency;
    }

    public static void main(String args[])
    {
        int ans = freqSubstring("abcde",2,4,26);
        System.out.println(ans);
    }
}
