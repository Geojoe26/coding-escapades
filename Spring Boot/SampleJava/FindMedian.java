import java.util.*;

public class FindMedian {

    public static int findMedian(List<Integer> a)
    {
        Collections.sort(a);
        int mid = a.size()/2;

        int median = a.stream().mapToInt(x->x).skip(mid).findFirst().orElse(0);

        return median;
    }

    public static void main(String args[])
    {
        List<Integer> arr = new ArrayList<>();
        arr.add(15);
        arr.add(8);
        arr.add(84);
        arr.add(2);
        arr.add(27);
        arr.add(58);
        arr.add(17);

        System.out.println("Median :"+ findMedian(arr));

    }
}
