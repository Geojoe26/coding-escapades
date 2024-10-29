import java.util.Scanner;

public class ArmstrongNumber {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");

        int num = sc.nextInt();
        int temp = num, sum = 0;
        int digit = 0;
        while(temp!=0)
        {
            digit = temp%10;
            sum = sum+(digit*digit*digit);
            temp = temp/10;
        }

        System.out.println(num==sum?"Armstrong Number":"Not an Armstrong Number");
    }
}
