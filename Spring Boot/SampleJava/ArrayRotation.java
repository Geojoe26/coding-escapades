import java.util.Arrays;

public class ArrayRotation {

    public static int[] getUpdatedArr(int[] arr, int d)
    {
        int[] temp = new int[arr.length];
        int j = 0;
        int k = arr.length-d;

        for(int i=d; i<arr.length; i++)
        {
            temp[j] = arr[i];
            j++;
        }

        for(int i=0; i<d; i++)
        {
            temp[k] = arr[i];
            k++;
        }

        return temp;
    }
    public static void main(String args[])
    {
        int[] a = {12,6,1,4,34,29,41,14,96};
        int move = 4;

        System.out.println("Rotated Array:"+ Arrays.toString(getUpdatedArr(a,move)));
    }
}
