import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountDuplicates {

    public static int getCount(String s)
    {
        s = s.toLowerCase().replaceAll(" ","");
        Map<Character, Integer> charCount = new HashMap<>();
        char[] charArr = s.toCharArray();
        int count = 0;
        int counter = 0;
        for(char a:charArr)
        {
            if(charCount!=null && charCount.get(a)!=null)
            {
                count = (int) charCount.get(a);
                count++;
                charCount.put(a,count);
            }
            else {
                charCount.put(a,1);
            }
        }

        for(Map.Entry<Character,Integer>b:charCount.entrySet())
        {
            if(b.getValue()>1)
            {
                counter++;
            }
        }

        return counter;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String:");

        String str = sc.nextLine();

        System.out.println("Number of Duplicates in the word:"+getCount(str));
    }
}
