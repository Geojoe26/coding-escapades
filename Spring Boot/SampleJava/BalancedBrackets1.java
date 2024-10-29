import java.util.*;

public class BalancedBrackets1 {

    public static String isBalanced(String str)
    {
        List<Character> charList = new ArrayList<>();
        Deque<Character> charDeque = new LinkedList<>();

        charList.add('{');
        charList.add('[');
        charList.add('(');


        if(str == null || str.length()%2 != 0)
        {
            return "Not Balanced";
        }
        else if(str.contains("[a-zA-Z0-9]"))
        {
            return "Not Balanced";
        }
        else {
            char[] c = str.toCharArray();

            for(int i=0; i<c.length; i++)
            {
                if(charList.contains(c[i]))
                {
                    charDeque.add(c[i]);
                }
                else{

                    if(!charDeque.isEmpty() && ((charDeque.getLast().equals('(') && c[i] == ')')
                                                || (charDeque.getLast().equals('{') && c[i] == '}')
                                                || (charDeque.getLast().equals('[') && c[i] == ']')))
                    {
                        charDeque.removeLast();
                    }
                    else {
                         return "Not Balanced";
                    }

                }
            }
        }

        return charDeque.isEmpty()?"Balanced":"Not Balanced";
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a String");
        String str = sc.nextLine();

        System.out.println("Given "+str+" is "+isBalanced(str));
    }
}
