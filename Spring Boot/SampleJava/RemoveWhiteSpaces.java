import java.util.Scanner;

public class RemoveWhiteSpaces {

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a line:");
        String s = sc.nextLine();

        String s1 = s.replaceAll("\\s+","");

        System.out.println("Line Before :"+s);
        System.out.println("Line After :"+s1);

    }
}
