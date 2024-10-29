import java.util.*;
public class CoinChange {

    public static int coinCombinations(List<Integer> denominations , int idx, int target){
        // Base Case
        if (target == 0) return 1;
        int ways = 0;
        for (int i = idx; i < denominations.size(); i++){
            // Counting ways if i-th denomination cam be included
            if (target - denominations.get(i) >=0 )
                ways += coinCombinations(denominations, i, target-denominations.get(i));
        }
        return ways;
    }

    public static void main(String[] args) throws Exception {

        List<Integer> denominations = Arrays.asList(1, 2);
        int target = 5;
        int ways = coinCombinations(denominations, 0, target);
        System.out.print("Coin change combinations are: ");
        System.out.println(ways);
    }
}
