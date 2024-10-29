import java.util.Scanner;

public class PowerOfThree {

    public static String isPower(Integer n)
    {
        while(n%3==0)
        {
            n = n/3;

            if(n==1)
            {
                return "Power of 3";
            } else if (n<=0) {
                return "Not Power of 3";
            }
        }
        return "Not a power of 3";
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number:");
        int num = sc.nextInt();

        System.out.println("Number "+num+" is:"+isPower(num));
    }

}
