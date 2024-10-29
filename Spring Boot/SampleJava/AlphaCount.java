import java.util.Scanner;

public class AlphaCount {

    public static String getAlphaCount(String s)
    {

        int count = 0;
        char first = '\0';
        StringBuilder str = new StringBuilder();

        for(int i = 0 ; i<s.length(); i++)
        {
            if(!Character.isLetterOrDigit(s.charAt(i)))
            {
                str.append(s.charAt(i));
                first = '\0';
                continue;
            }

            if(first==s.charAt(i))
            {
                count++;

                if(count>1)
                {
                    str.setLength(str.length()-1);
                    str.append(count);
                }
            }
            else {

                first = s.charAt(i);
                count = 1;
                str.append(s.charAt(i)).append(count);

            }

        }

        return str.toString();
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Input String:");

        String s = sc.nextLine();
        System.out.println("Final Result:"+getAlphaCount(s));
    }
}
