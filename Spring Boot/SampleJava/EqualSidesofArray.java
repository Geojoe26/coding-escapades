import java.util.Arrays;

public class EqualSidesofArray {

    public static int getIndex(int[] a)
    {
        int sum = Arrays.stream(a).sum();
        int left = 0;

        for(int i = 0; i<a.length; i++)
        {
            sum = sum - a[i];
            if(left==sum)
            {
                return i;
            }
            else {
                left = left+a[i];
            }
        }

        return -1;
    }
    public static void main (String args[])
    {
        int[] arr = {20,10,-80,10,10,15,35};
        System.out.println("The index at which the array is equal :"+getIndex(arr));
    }
}
