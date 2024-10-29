import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueFreq {

    public static String isUniqueFreq(int[] arr)
    {
        Map<Integer, Integer> arrMap = new HashMap<>();
        int count = 0;

        for(int i = 0; i<arr.length; i++)
        {
            if(arrMap!=null && arrMap.get(arr[i])!=null)
            {
                count = arrMap.get(arr[i]);
                arrMap.put(arr[i],count+1);
            }
            else {

                arrMap.put(arr[i],1);
            }
        }

        List<Integer> values = new ArrayList<>();
        for(Map.Entry<Integer,Integer>a: arrMap.entrySet())
        {
            values.add(a.getValue());
        }

        int counter = (int) values.stream().distinct().count();

        return counter==values.size()?"YES":"NO";
    }

    public static void main(String args[])
    {
        int[] arr = {1,2,2,3,3,3};
        System.out.println("Has Unique Frequencies:"+isUniqueFreq(arr));
    }
}
