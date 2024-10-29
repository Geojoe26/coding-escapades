import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringOperations {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string:");

        String str = sc.nextLine().toLowerCase();
        str = str.replaceAll("[^a-zA-Z0-9]","");
        int count=0;
        Map<Character, Integer> charCount = new HashMap<>();
        for(int i=0; i<str.length(); i++)
        {
            if(charCount!=null && charCount.get(str.charAt(i))!= null)
            {
                count = charCount.get(str.charAt(i));
                count++;

                charCount.put(str.charAt(i),count);
            }
            else {
                charCount.put(str.charAt(i),1);
            }
        }
        System.out.println("Characters duplicated in the string are as follows:");
        for(Character c:charCount.keySet())
        {
            if(charCount.get(c) > 1)
            {
                System.out.println(c);
            }
        }
    }
}
