public class BinarySearch {

    public static int getIndex(int[] arr, int element)
    {
        int stIdx = 0;
        int edIdx = arr.length;
        int mid = (stIdx+edIdx)/2;

        if(arr[mid] == element)
        {
            return mid;
        }
        else {

            if(arr[mid]<element)
            {
                for(int i=mid+1; i<arr.length; i++)
                {
                    if(arr[i]==element)
                    {
                        return i;
                    }
                }
            }
            else{

                for(int i=mid-1; i>=0; i--)
                {
                    if(arr[i]==element)
                    {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    public static void main (String args[])
    {
        int[] arr = {14,21,30,44,57,61,75,88,96,102};
        int find = 61;

        System.out.println("Index of "+find+" is:"+getIndex(arr,find));
    }
}
