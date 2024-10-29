import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.OptionalDouble;

public class ArrayAverage {

    public static void main(String args[])
    {
        DecimalFormat df = new DecimalFormat("0.00");
        int[] lala = {1,2,3,4,5,6,7,8,9,10};

        Double average = Arrays.stream(lala).average().getAsDouble();
        System.out.println(df.format(average));
    }
}
