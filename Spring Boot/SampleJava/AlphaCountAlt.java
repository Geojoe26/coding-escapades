import java.util.*;

public class AlphaCountAlt {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input String:");

        String s = sc.nextLine();
        int count=0;

        Map<Character, Integer>charMap = new LinkedHashMap<>();
        char[] charArr = s.toCharArray();
        for(Character a:charArr)
        {
            if(charMap!=null && charMap.get(a)==null)
            {
                charMap.put(a,1);
            }
            else
            {
                count=charMap.get(a);
                count++;
                charMap.put(a,count);
            }
        }

        //Character[] keys = charMap.keySet().toArray(new Character[0]);
        for(Character c: charMap.keySet())
        {
            System.out.print(c+""+charMap.get(c));
        }
    }
}

