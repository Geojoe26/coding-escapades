import java.util.Scanner;

public class SwapString {

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String:");
        String a = sc.nextLine();

        System.out.println("Enter another string:");
        String b = sc.nextLine();

        System.out.println(a+" "+b);

        a = a+b;
        b = a.substring(0,a.length()-b.length());
        a = a.substring(b.length());

        System.out.println(a+" "+b);

    }
}
