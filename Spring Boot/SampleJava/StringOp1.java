import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringOp1 {

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a Sentence");
        String s = sc.nextLine();

        String[] strArr = s.split(" ");
        Map<String, Integer> wordMap = new HashMap<>();

        for(String a:strArr)
        {
            if(wordMap!=null && wordMap.get(a)==null)
            {
                wordMap.put(a,1);
            }
            else {
                int count = wordMap.get(a);
                count++;
                wordMap.put(a,count);
            }
        }

        for(String b: wordMap.keySet())
        {
            if(wordMap.get(b)>=2)
            {
                System.out.println(b+" "+wordMap.get(b));
            }
        }
    }
}
