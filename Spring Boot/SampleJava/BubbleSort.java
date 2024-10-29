import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args)
    {
        int[] arr = {12,4,166,17,8,34,19};
        int n = arr.length;
        int temp = 0;
        boolean swap = false;

        for(int i=0; i<n-1 ; i++)
        {
            for(int j=0; j<n-i-1; j++)
            {
                if(arr[j]>arr[j+1])
                {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swap = true;
                }
            }
            if(!swap)
            {
                break;
            }
        }

        System.out.println("Sorted Array:");
        Arrays.stream(arr).forEach(System.out::println);
    }
}
