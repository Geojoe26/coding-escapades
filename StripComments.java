import java.util.*;
public class StripComments {

	public static String stripComments(String text, String[] commentSymbols) {
    
    String line="";
    String[] textArr = text.split("\n");
    
    for(int i=0; i<textArr.length; i++)
    {
       for(int j=0; j<commentSymbols.length; j++)
       {
          if(textArr[i].contains(commentSymbols[j]))
          {
            textArr[i] = textArr[i].substring(0,textArr[i].indexOf(commentSymbols[j]));
          }
       }
       
       if(i!=textArr.length-1)
       {
         line = line.concat(textArr[i].stripTrailing()+"\n");
       }
       else
       {
         line = line.concat(textArr[i].stripTrailing());
       }
       
    }
		return line;
	}
	
}