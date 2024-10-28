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



class Result {

    /*
     * Complete the 'isPossible' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER a
     *  2. INTEGER b
     *  3. INTEGER c
     *  4. INTEGER d
     */

    public static String isPossible(int a, int b, int c, int d) {
        
        //The idea is to reduce the final goal to initial values so only one set of operations be performed rather than multiple operations
        String decision = "No";         //Decision variable
        
        //Initiating while loop to keep performing until initial (a,b) and final values (c,d) match
        while(c >= a && d >=b){
            if(c == a && d == b){       //checking if values match, everytime we perform operations
                
                decision = "Yes";       //Reset decision variable 
            }
            
            // If-else loop to check which of the final values is bigger to determine what operation to be performed
            if(c > d){                  
                c = c-d;
            }
            else{
                d = d-c;
            }
            
        }
        
        return decision;             //Returns decision after all operations done

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int a = Integer.parseInt(bufferedReader.readLine().trim());

        int b = Integer.parseInt(bufferedReader.readLine().trim());

        int c = Integer.parseInt(bufferedReader.readLine().trim());

        int d = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.isPossible(a, b, c, d);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
