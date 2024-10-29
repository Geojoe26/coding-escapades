import java.util.*;

public class BalancedBracketsWithAlphaNumeric {

    public static String isBalanced(String str)
    {
        boolean flag = false;
        List<Character> charList = new ArrayList<>();
        Deque<Character> deque = new LinkedList<>();

        charList.add('(');
        charList.add('{');
        charList.add('[');

        str = str.replaceAll("[a-zA-Z0-9]","").replaceAll("[+-/*]","");

        if(str == null || str.length()%2 !=0)
        {
            return "Not Balanced";
        }
        else {
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (charList.contains(c[i])) {
                    deque.add(c[i]);
                } else {
                    if (!deque.isEmpty() && ((deque.getLast() == '{' && c[i] == '}')
                            || (deque.getLast() == '[' && c[i] == ']')
                            || (deque.getLast() == '(' && c[i] == ')'))) {
                        deque.removeLast();
                    } else {
                        return "Not Balanced";
                    }
                }
            }

            return deque.isEmpty() ? "Balanced" : "Not Balanced";
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a String");
        String str = sc.nextLine();

        System.out.println("Given "+str+" is "+isBalanced(str));
    }
}
