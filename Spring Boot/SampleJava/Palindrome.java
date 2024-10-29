import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Palindrome {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string:");
        String str = sc.nextLine().toLowerCase();
        str = str.replaceAll("[^a-zA-Z0-9]","");
        //Using String Builder
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();

        System.out.println(str.contentEquals(sb) ? "Palindrome" : "Not a Palindrome");

        //Without using String Builder
        String reverse = "";

        for(int i=str.length()-1; i>=0; i--)
        {
            reverse = reverse + str.charAt(i);
        }

        System.out.println(str.equals(reverse) ? "Palindrome" : "Not a Palindrome");
    }
}
