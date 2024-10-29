import java.util.Scanner;

public class AlphaCountOptimize {

    public static String getAlphaCount(String s) {
        // Using StringBuilder for efficient string manipulation
        StringBuilder result = new StringBuilder();

        // Variables to keep track of current character and its count
        int count = 1;
        char first = '\0';

        // Loop through the string
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // If current character is not a letter or digit, add it to result
            if (!Character.isLetterOrDigit(currentChar)) {
                result.append(currentChar);
                first = '\0';  // Reset first character marker
                continue;
            }

            // Check if current character is same as the last counted character
            if (currentChar == first) {
                count++;  // Increment count for repeating character

                // Update last count in result
                if (count > 1) {
                    result.setLength(result.length() - 1);  // Remove the last count digit
                    result.append(count);  // Append new count
                }
            } else {
                // New character, so reset count and append to result
                first = currentChar;
                count = 1;
                result.append(currentChar).append(count);
            }
        }

        return result.toString();
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Input String:");

        String s = sc.nextLine();
        System.out.println("Final Result:"+getAlphaCount(s));
    }
}
