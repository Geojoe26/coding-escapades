import java.util.Scanner;

public class StringOp2 {

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a sentence");
        String s = sc.nextLine();

        System.out.println("Enter a Character to search");
        String c = sc.nextLine();

        int charLen = s.length() - s.replaceAll(c,"").length();

        System.out.println("Number of Occurrences of "+c+":"+charLen);
    }
}