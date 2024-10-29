import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeOrNot {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int num = sc.nextInt();
        boolean flag = true;
        int temp = 0;
        for(int i=2; i <= num/2; i++)
        {
            temp = num%i;
            if(temp==0)
            {
                flag = false;
                break;
            }
        }

        System.out.println(num==1 || !flag?"Not Prime":"Is Prime");

        boolean flag1 = true;
        List<Integer> prime = new ArrayList<>();
        for(int j=2; j<=num; j++)
        {
            for(int i=2; i<=j/2; i++)
            {
                temp = j%i;
                if(temp==0)
                {
                    flag1 = false;
                    break;
                }
                else {
                    flag1 = true;
                }
            }
            if(flag1)
            {
                prime.add(j);
            }
        }

        System.out.println("All Prime Numbers until "+num+" are as follows:"+prime.toString());
        System.out.println("Number of prime values until "+num+" is:"+prime.size());
    }
}
