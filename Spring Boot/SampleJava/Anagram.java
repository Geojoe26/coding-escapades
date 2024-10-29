import java.util.Arrays;
import java.util.Scanner;

public class Anagram {

    public static boolean isAnagram(String s1, String s2)
    {
//        Boolean flag = false;
//
//        String tempS1 = s1.toLowerCase().replaceAll("[^a-zA-Z0-9]","");
//        String tempS2 = s2.toLowerCase().replaceAll("[^a-zA-Z0-9]","");
//
//        if(tempS1.length() == tempS2.length())
//        {
//            char[] arrS1 = tempS1.toCharArray();
//            char[] arrS2 = tempS2.toCharArray();
//
//            Arrays.sort(arrS1);
//            Arrays.sort(arrS2);
//
//            flag = Arrays.equals(arrS1,arrS2);
//        }
//        else {
//            return false;
//        }

        Boolean flag = false;

        s1 = s1.toUpperCase().replaceAll("[^a-zA-Z0-9]","");
        s2 = s2.toUpperCase().replaceAll("[^a-zA-Z0-9]","");

        if(s1.length()==s2.length())
        {
            char[] ars1 = s1.toCharArray();
            char[] ars2 = s2.toCharArray();

            Arrays.sort(ars1);
            Arrays.sort(ars2);

            flag = Arrays.equals(ars1,ars2);
        }

        return flag;
    }
    public static void main (String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 1st String");
        String s1 = sc.nextLine();

        System.out.println("Enter 2nd String");
        String s2 = sc.nextLine();

        System.out.println(isAnagram(s1,s2)?"Anagram":"Not an Anagram");
    }
}
