import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveArrayDuplicates {

    public static void main(String[] args)
    {
        String[] strArr = {"A","B","C","C","D"};
        List<String> distinctArr = new ArrayList<>();

        for(String s:strArr)
        {
            if(distinctArr!=null && !distinctArr.contains(s))
            {
                distinctArr.add(s);
            }
        }

        System.out.println("Final Array:"+ Arrays.asList(distinctArr));

        List<String> distinctArr1 = Arrays.asList("A","B","C","C","D");

        System.out.println("New Array:"+distinctArr1.stream()
                .distinct()
                .toList());
    }
}
