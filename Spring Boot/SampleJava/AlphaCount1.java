import java.util.Scanner;

public class AlphaCount1 {

    public static String getCompressedString(String s)
    {
        StringBuilder str = new StringBuilder();
        int count = 1;
        char first ='\0';

        //char[] strArr = s.toCharArray();

        for(int i=0; i<s.length(); i++)
        {
            char currentChar = s.charAt(i);
            if (!Character.isLetterOrDigit(currentChar))
            {
                str.append(currentChar);
                first = '\0';
                continue;
            }

            if(first == currentChar)
            {
                count++;
                if(count>1)
                {
                    str.setLength(str.length()-1);
                    str.append(count);
                }
            }
            else {

                count = 1;
                first = currentChar;
                str.append(currentChar).append(count);
            }

        }

        return str.toString();
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String:");
        String str = sc.nextLine();

        System.out.println("Compressed String is as follows:"+getCompressedString(str));
    }
}


