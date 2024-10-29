import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringReverseWithSpaces {

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a line:");
        String s = sc.nextLine();

        char[] strArr = s.toCharArray();
        char[] revArr = new char[strArr.length];

        for(int i=0; i<strArr.length; i++)
        {
            if(strArr[i]== ' ')
            {
                revArr[i]=' ';
            }
        }

        int k = revArr.length-1;
        for(int j=0; j<revArr.length; j++)
        {
            if(strArr[j]!=' ')
            {
                if(revArr[k]==' ')
                {
                    k--;
                }
                revArr[k]=strArr[j];
                k--;
            }
        }

        System.out.println("Result:");
        for(Character w:revArr)
        {
            System.out.print(w);
        }
    }
}
