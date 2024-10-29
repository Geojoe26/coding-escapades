import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountWords {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a line");
        String words = sc.nextLine();

        Map<String, Integer> wordMap = new HashMap();
        int count = 0;
        for(String s:words.split(" "))
        {
            if(wordMap.get(s)!=null)
            {
                count = wordMap.get(s);
                count++;
                wordMap.put(s,count);
            }
            else {
                wordMap.put(s,1);
            }
        }

        System.out.println("Number of words : "+wordMap);
    }
}
