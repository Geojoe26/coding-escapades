import java.util.Scanner;

public class NumberSwap {

    public static void main(String[] args)
    {
        //Swap number using a third variable
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1st number:");
        int a = sc.nextInt();

        System.out.println("Enter 2nd number:");
        int b = sc.nextInt();

        System.out.println("Numbers before swapping are as follows: "+a+" and "+b);

        int temp = 0;
        temp = a;
        a = b;
        b = temp;

        System.out.println("Numbers after swapping are as follows: "+a+" and "+b);

        //without using third variable
        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("Numbers after swapping are as follows: "+a+" and "+b);
    }
}
