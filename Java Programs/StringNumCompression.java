
import java.util.*;

public class StringNumCompression {
    
  public static String betterCompression(String s) {
        
        //Instantiated a tree map since we need all the characters in alphabetical order
        Map<Character, Integer> charMap = new TreeMap<Character, Integer>();
        int strLength = s.length();
        
        //Checking if the string is null or empty
        if(s!=null && !s.isEmpty())
        {
            //Looping through each character of the string 
            for(int i=0; i<strLength; i++)
        {
            char c = s.charAt(i);
            
            //Created a string to store the integer value of the character, since value would be more than 10 I used the string builder to append until digit occur and stop on encountering a character.
            StringBuilder freq = new StringBuilder();
            while(i+1<strLength && Character.isDigit(s.charAt(i+1)))
            {
                freq.append(s.charAt(i+1));
                i++;
            }
            
            //We get the actual integer associated with the character
            int number = Integer.parseInt(freq.toString());
            
            //Storing them in the map
            if(charMap!=null && charMap.get(c)!=null)
            {
                charMap.put(c, charMap.get(c)+number);
            }
            else{
                charMap.put(c,number);
            }
            
        }
        
        //Generating an updated string using StringBuilder which is our result
        StringBuilder updatedString = new StringBuilder();
        
        //Iterated through the map to get values, since treeMap will sort that for us, we can just simply go on appending
        for(Map.Entry<Character,Integer> mapEnt : charMap.entrySet())
        {
            updatedString.append(mapEnt.getKey()).append(mapEnt.getValue());
        }
        
        //Returning the result
        return updatedString.toString();
        
        }
        else{
        
            return s;
        
        }
        
    }
    
    public static void main(String[] args) {
      System.out.println(betterCompression("b5a1c7a5d9c3b2"));
    }
}