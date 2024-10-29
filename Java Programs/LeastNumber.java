import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class LeastNumber {

    /*
     * Complete the 'getSubstring' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING input_str
     *  2. INTEGER k
     */

    public static String getSubstring(String input_str, int k) {
    // Write your code here
    
            int strLen = input_str.length();
            int counter = 0;
            
            int l = 0, leastLen = Integer.MAX_VALUE;
            String output = "";
            
            for(int r = 0; r<strLen; r++)
            {
                if(input_str.charAt(r) == '1')
                {
                    counter++;
                }
                
                while(counter == k)
                {
                    if(r-l+1<leastLen)
                    {
                        leastLen = r-l+1;
                        output = input_str.substring(l,r+1);
                    }
                    else if(r-l+1==leastLen)
                    {
                        String current = input_str.substring(l,r+1);
                        if(current.compareTo(output) < 0)
                        {
                            output = current;
                        }
                    }
                    
                    if(input_str.charAt(l) == '1')
                    {
                        counter--;
                    }
                    
                    l++;
                }
            }
            
            
            return output;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String input_str = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.getSubstring(input_str, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
