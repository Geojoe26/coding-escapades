import java.util.*;
import java.util.stream.Collectors;

public class LearnStreams {

    public static void main(String[] args) throws NullPointerException{

        //Q1
        List<Integer> quest1 = Arrays.asList(12,23,72,19,28,34);

        double average = quest1.stream()
                .mapToDouble(x->x)
                .average()
                .orElse(0.0);

        System.out.println("Average is:"+average);

        //Q2
        List<String> quest2 = Arrays.asList("Joel","Jomon","Rose","Vishnu");

        quest2.stream()
                .map(x->x.toLowerCase())
                .forEach(System.out::println);

        quest2.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        quest2 = quest2.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(quest2);

        quest2 = quest2.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(quest2);

        //Q3
        List<Integer> quest3 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        int sumEven = quest3.stream()
                .filter(x->x%2==0)
                .mapToInt(x->x)
                .sum();

        int sumOdd = quest3.stream()
                .filter(x->x%2!=0)
                .mapToInt(x->x)
                .sum();

        System.out.println("Sum of Even numbers:"+sumEven);
        System.out.println("Sum of Odd numbers:"+sumOdd);

        //Q4
        List<String> quest4 = Arrays.asList("Joel","a","b","Jomon","Rose","a","b","Vishnu");

        quest4 = quest4.stream()
                .distinct()
                .toList();

        System.out.println(quest4);

        //Q5
        List<String> quest5 = Arrays.asList("Joel","Jomon","Akshay","Rose","Vishnu","Nivedha","Hassaan","Anisha","Abins");

        int dist_count = (int) quest5.stream()
                .filter(x->x.startsWith("A"))
                .count();

        System.out.println("Distinct Strings with chosen letter is:"+dist_count);

        //Q6
        List<String> quest6 = Arrays.asList("Joel","Jomon","Akshay","Rose","Vishnu","Nivedha","Hassaan","Anisha","Abins");

        quest6 = quest6.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("List in Ascending order:"+quest6);

        quest6 = quest6.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("List in Descending order:"+quest6);

        //Q7
        List<Integer> quest7 = Arrays.asList(12,23,72,19,28,34);

        int min = quest7.stream()
                .mapToInt(x->x)
                .min().getAsInt();

        int max = quest7.stream()
                        .mapToInt(x->x)
                                .max().getAsInt();

        System.out.println("Minimum Value:"+min);
        System.out.println("Maximum Value:"+max);

        //Q8
        int sec_min = quest7.stream()
                .distinct()
                .sorted()
                .skip(1)
                .findFirst()
                .orElse(0);

        int sec_max = quest7.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);

        System.out.println("Minimum Value:"+sec_min);
        System.out.println("Maximum Value:"+sec_max);


    }
}
