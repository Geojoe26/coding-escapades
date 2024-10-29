import java.util.Arrays;
import java.util.List;

public class StockMaxProfit {

    public static int getProfit(List<Integer> list)
    {
        int maxProf = 0;
        int buy = list.get(0);
        int sell = buy;

        for(int a:list)
        {
            if(a>sell)
            {
                sell=a;
                int prof = sell-buy;
                if(prof>maxProf)
                {
                    maxProf=prof;
                }
            }else if (a<buy) {
                sell = buy = a;
            }
        }

        return maxProf;
    }
    public static void main(String args[])
    {
        System.out.println("Maximum Profit is:"+getProfit(Arrays.asList(1,4,2,29,8,20,19)));
    }
}
