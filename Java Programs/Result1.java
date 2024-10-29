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



class Result1 {

    /*
     * Complete the 'pointsBelong' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER x1
     *  2. INTEGER y1
     *  3. INTEGER x2
     *  4. INTEGER y2
     *  5. INTEGER x3
     *  6. INTEGER y3
     *  7. INTEGER xp
     *  8. INTEGER yp
     *  9. INTEGER xq
     *  10. INTEGER yq
     */

    public static int pointsBelong(int x1, int y1, int x2, int y2, int x3, int y3, int xp, int yp, int xq, int yq) {
        
        int decision = 0; //Default set to state that the triangle is non-degenerate triangle
        boolean flag = false;  //To set if point p belongs to triangle set by default to false
        
        //Checking whether the triangle is a non-degenrate triangle or not
        //Refering to the formula mentioned in question for non-degenerate triangle
        double ab = Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2));
        double bc = Math.sqrt(Math.pow(x3 - x2,2) + Math.pow(y3 - y2,2));
        double ac = Math.sqrt(Math.pow(x1 - x3,2) + Math.pow(y1 - y3,2));
        
        //If condition doesn't satisfy return that its not a non-degenerate triangle
        if(!(ab+bc>ac && bc+ac>ab && ab+ac>bc)){
            return decision;
        }
        
        //If condition satisfied then we check whether points belong to the triangle or outside.
        //Calculating Barycentric coordinates
        
        //Firstly for (xp,yp)
        double d1 = (y2-y3)*(x1-x3)+(x3-x2)*(y1-y3);
        double a1 = ((y2-y3)*(xp-x3)+(x3-x2)*(yp-y3))/d1;
        double b1 = ((y3-y1)*(xp-x3)+(x1-x3)*(yp-y3))/d1;
        
        double g1 = 1-a1-b1;
        
        //Secondly for (xq,yq)
        double d2 = (y2-y3)*(x1-x3)+(x3-x2)*(y1-y3);
        double a2 = ((y2-y3)*(xq-x3)+(x3-x2)*(yq-y3))/d2;
        double b2 = ((y3-y1)*(xq-x3)+(x1-x3)*(yq-y3))/d2;
        
        double g2 = 1-a2-b2;
        
        //if the values of a1,a2,b1,b2,g1,g2 are in between or equal to 0 & 1, the points are inside or on the triangle.
        if(a1>=0 && b1>=0 && g1>=0 && a1<=1 && b1<=1 && g1<=1)
        {
            decision=1;      //point p belongs to triangle
            flag=true;       //Sets boolean flag to indicate that point p belongs to triangle
        }
        else if(a2>=0 && b2>=0 && g2>=0 && a2<=1 && b2<=1 && g2<=1){
            
            if(flag){                 //checks if point p belongs to the triangle or not
                decision=3;           //point p and q both belongs to triangle
            }
            else{
                decision=2;           //point q belongs to triangle
            }    
        }
        else{                     //else doesnt belong to the triangle
            decision=4;
        }
        
        return decision;   // Returns decision
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int x1 = Integer.parseInt(bufferedReader.readLine().trim());

        int y1 = Integer.parseInt(bufferedReader.readLine().trim());

        int x2 = Integer.parseInt(bufferedReader.readLine().trim());

        int y2 = Integer.parseInt(bufferedReader.readLine().trim());

        int x3 = Integer.parseInt(bufferedReader.readLine().trim());

        int y3 = Integer.parseInt(bufferedReader.readLine().trim());

        int xp = Integer.parseInt(bufferedReader.readLine().trim());

        int yp = Integer.parseInt(bufferedReader.readLine().trim());

        int xq = Integer.parseInt(bufferedReader.readLine().trim());

        int yq = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.pointsBelong(x1, y1, x2, y2, x3, y3, xp, yp, xq, yq);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
