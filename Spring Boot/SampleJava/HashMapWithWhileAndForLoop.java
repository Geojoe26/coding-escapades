import java.util.*;
import java.util.Map.Entry;

public class HashMapWithWhileAndForLoop {

    public static void main(String[] args)
    {
        Map<String, Integer> homies = new HashMap<>();
        homies.put("Joel",29);
        homies.put("Jomon",26);
        homies.put("Rose",24);

        //Using While loop
        Iterator i = homies.entrySet().iterator();
        while(i.hasNext())
        {
            Map.Entry mapEnt = (Map.Entry) i.next();
            System.out.println("Key : "+mapEnt.getKey()+",Value: "+mapEnt.getValue());
        }

        /* Using for loop */
        for(String a: homies.keySet())
        {
            System.out.println("Key: "+a+", Value:"+homies.get(a));
        }

        for(Map.Entry<String,Integer> a: homies.entrySet())
        {
            System.out.println("Key: "+a.getKey()+", Value: "+a.getValue());
        }
    }
}
