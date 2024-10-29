import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamsAgain {

    public static void main(String args[])
    {
        String[] one = {"B", "C", "F", "D"};

        System.out.println("First Element:"+ Arrays.stream(one).findFirst().orElse(""));
    }
}
