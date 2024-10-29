import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prime {

    public static String isPrime(Integer n)
    {
        boolean flag = true;
        for(int i=2; i<=Math.sqrt(n); i++)
        {
            if(n%i==0)
            {
                flag = false;
            }
        }

        return n == 1 || !flag?"Not Prime":"Prime";
    }

    public static List<Integer> isPrimeUntil(Integer n){

        boolean flag = true;

        List<Integer> primeList = new ArrayList<>();
        for(int i=2; i<=n; i++)
        {
            for(int j=2; j<=Math.sqrt(i); j++)
            {
                if(i%j == 0)
                {
                    flag = false;
                    break;
                }
                else {
                    flag = true;
                }
            }

            if(flag)
            {
                primeList.add(i);
            }
        }

        return primeList;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int num = sc.nextInt();

        System.out.println("The entered number is:"+isPrime(num));
        System.out.println("Prime number until "+num+" :"+isPrimeUntil(num));
    }
}
