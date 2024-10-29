import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayWithArrayList {

    public static void main(String[] args)
    {
        List<String> homies = new ArrayList<>();
        homies.add("Joel");
        homies.add("Jomon");
        homies.add("Rose");
        homies.add("Vishnu");
        homies.add("Akshay");

        //iterate using for loop
        for(int i=0; i<homies.size(); i++)
        {
            System.out.println(homies.get(i));
        }

        //Using while loop
        Iterator itr = homies.listIterator();
        while(itr.hasNext())
        {
            System.out.println(itr.next());
        }

        //Using enhanced for loop
        for(String str:homies)
        {
            System.out.println(str);
        }
    }
}
