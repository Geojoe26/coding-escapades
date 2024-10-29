import java.util.*;

public class SecondHighest {

    public static void main(String[] args){

        List<Integer> numList = Arrays.asList(12,945,4,16,8,74,164,42,974,364);
        int n=2;

        Optional<Integer> second = numList.stream()
                .sorted(Collections.reverseOrder())
                .limit(n)
                .skip(n - 1)
                .findFirst();

        System.out.println(second.isPresent()?"Second Highest:"+second:0.0);
    }
}
