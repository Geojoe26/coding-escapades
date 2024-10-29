import java.util.Scanner;

public class StringReverse {

    public static void main(String[] args){

        //Using String Builder Reverse
        String name = "Joel";
        StringBuilder str = new StringBuilder();
        str.append(name);
        System.out.println("Reverse of the String is:"+str.reverse());

        //Without using String Builder Reverse
        String str1 = "Jomon";
        String temp = "";
        for(int i=str1.length()-1; i>=0; i--){
            temp += str1.charAt(i);
 ;       }
        System.out.println("Reverse of the String is:"+temp);

        //Using str from command Line
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a word:");
        String str2 = sc.nextLine();

        char[] c = str2.toCharArray();
        for(int i = c.length-1; i>=0; i--)
        {
            System.out.print(c[i]);
        }
    }
}
