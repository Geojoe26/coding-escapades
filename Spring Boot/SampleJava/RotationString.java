import java.util.Scanner;

public class RotationString {

    public static void main (String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a Line:");
        String s = sc.nextLine();

        System.out.println("Enter comparison Line:");
        String s1 = sc.nextLine();
        boolean flag = true;
        String temp="";
        for(int i=0; i<s1.length(); i++)
        {
            if(s1.charAt(i)>=65 && s1.charAt(i)<=90)
            {
                temp += " "+s1.charAt(i);
            }
            else
            {
                temp += s1.charAt(i);
            }
        }

        for(String a:temp.split(" "))
        {
            if(!s.contains(a))
            {
                flag = false;
                break;
            }

        }

        System.out.println(flag?"Rotational String":"Not a Rotational String");
    }
}
