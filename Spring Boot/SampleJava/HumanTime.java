import java.util.Scanner;

public class HumanTime {

    public static String giveHumanTime(int seconds)
    {
        int ss, mm, hh = 0;

        ss = seconds%60;
        mm = seconds/60;

        if(mm>=60)
        {
            hh = mm/60;
            mm = mm%60;
        }

        return String.format("%02d",hh)+":"+String.format("%02d",mm)+":"+String.format("%02d",ss);
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter time in seconds");

        int sec = sc.nextInt();

        System.out.println("Human Time="+giveHumanTime(sec));
    }
}
