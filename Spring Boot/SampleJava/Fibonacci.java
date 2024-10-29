import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the index until you need a Fibonacci series:");

        int index = sc.nextInt();
        Double temp=0.0;
        Double num1=0.0;
        Double num2=1.0;

        List<Double> fibo = new ArrayList<>();
        for(int i=0; i<=index; i++)
        {
            fibo.add(num1);
            temp = num1+num2;
            num1 = num2;
            num2 = temp;
        }

        System.out.println("Fibonacci Series:"+fibo);
    }
}
