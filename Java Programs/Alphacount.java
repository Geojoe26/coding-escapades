public class Alphacount {
  static String myMethod(String str) {
    
    String temp="";
    int count=0;
    
    char a = str.charAt(0);
    for(int i = 0; i<str.length(); i++)
    {
       if(a==str.charAt(i))
       {
       	 count++;
         if(count>1)
         {
           temp=temp.substring(0,temp.length()-1)+count;
         }
         else
         {
           temp=temp+str.charAt(i)+count;
         }
         
         a = str.charAt(i);
       }
       else if(str.charAt(i)<'A' || str.charAt(i)>'z')
       {
       		temp=temp+str.charAt(i);
       }
       else
       {
       	 count=1;
         a=str.charAt(i);
         temp=temp+str.charAt(i)+count;
       }
    }
    
    return temp;
  }

  public static void main(String[] args) {
    String str1 = myMethod("AABSJC IAOJDNMDNMK ajhsjjskdoolspp nsbbcjjdikaaal");
    System.out.println(str1);
  }
}
