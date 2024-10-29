public class SumPairs {

    public static int getPairs(int[]a,int s)
    {
        int pairs = 0;

        for(int i=0;i<a.length;i++)
        {
            for(int j=i+1;j<a.length;j++)
            {
                if(a[i]+a[j]==s)pairs++;
            }
        }

        return pairs;
    }
    public static void main(String args[])
    {
        int[] arr = {1, 5, 7, -1, 5};
        int sum = 8;

        System.out.println("Pairs possible:"+getPairs(arr,sum));
    }
}

