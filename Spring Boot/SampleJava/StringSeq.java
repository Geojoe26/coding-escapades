import java.util.*;

public class StringSeq {

    public static int countWays(String s1, String s2)
    {
        int m = s1.length(), n = s2.length();
        int[] count = new int[m];

        // Initialization to 0 is done because if a is not traversed yet,
        // no subsequence of any type b[0]b[1]...b[i] can exist, i.e. the
        // number is zero.
        for (int i = 0; i < n; i++) { // Traversing the string a
            for (int j = m - 1; j >= 0; j--) { // Reverse traversal of b
                if (s2.charAt(i) == s1.charAt(j)) {

                    // This ternary expression checks weather j == 0,
                    // gives 1 if j == 0.
                    count[j] = count[j]+(j == 0 ? 1 : count[j - 1]);
                }
            }
        }

        return count[m - 1];
    }

    public static void main(String[] args)
    {
        String s1 = "geeks", s2 = "geeksforgeeks";
        System.out.println(countWays(s1, s2));
    }
}
