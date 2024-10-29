import java.util.Scanner;

public class ProportionOfChars {

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a line:");

        String s = sc.nextLine();
        int total_len = s.length();

        int upper = 0, lower = 0, digits = 0, other = 0;

        for(Character c : s.toCharArray())
        {
            if(Character.isUpperCase(c))
            {
                upper++;
            } else if (Character.isLowerCase(c)) {
                lower++;
            } else if (Character.isDigit(c)) {
                digits++;
            }else {
                other++;
            }
        }

        float upperPer = (float) upper /total_len * 100;
        float lowerPer = (float) lower/total_len * 100;
        float digitPer = (float) digits/total_len * 100;
        float otherPer = (float) other/total_len * 100;

        System.out.println("Percentage of Upper Case:"+upperPer);
        System.out.println("Percentage of Lower Case:"+lowerPer);
        System.out.println("Percentage of Digits:"+digitPer);
        System.out.println("Percentage of Others:"+otherPer);
    }
}
