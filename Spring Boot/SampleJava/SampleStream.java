import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;
import java.util.*;

public class SampleStream {

    public static void main(String[] args)throws Exception{

        IntStream
                .range(1,15)
                .skip(4)
                .forEach(a -> System.out.println(a));

        System.out.println("Sum:"+
                IntStream.range(2,10)
                        .sum());

        Stream.of("Joel","Jomon","Akshay","Rose","Vishnu")
                .sorted()
                .findFirst()
                .ifPresent(x->System.out.println("First Name:"+x));

        String[] names = {"Joel","Jomon","Akshay","Rose","Vishnu","Nivedha","Hassaan","Anisha","Abins"};
        Arrays.stream(names)
                .sorted()
                .filter(x->x.startsWith("A"))
                .forEach(System.out::println);

        int[] values = new int[] {32,54,29,46,28,34,49};
        Arrays.stream(values)
                .average()
                .ifPresent(System.out::println);

        List<String> mates = Arrays.asList("Joel","Jomon","Akshay","Rose","Vishnu","Nivedha","Hassaan","Anisha","Abins");
        mates.stream()
                .map(String::toUpperCase)
                .filter(s->s.contains("O "))
                .forEach(System.out::println);

        Stream<String> lines = Files.lines(Paths.get("Story.txt"));
        lines.filter(x -> x.length()<15)
                .forEach(System.out::println);
        lines.close();

        List<String> linesNew = Files.lines(Paths.get("Story.txt"))
                .filter(x->x.contains("J"))
                .collect(Collectors.toList());
        linesNew.forEach(x->System.out.println(x));

        Stream<String> row = Files.lines(Paths.get("data.csv"));
        int rowCount = (int) row
                .map(x->x.split(","))
                .filter(x->x.length==3)
                .count();
        System.out.println("No.of.Valid Rows:"+rowCount);
        row.close();

        Stream<String> row1 = Files.lines(Paths.get("data.csv"));
        row1.map(x->x.split(","))
                .filter(x->x.length==3)
                .filter(x->Integer.parseInt(x[1]) > 25)
                .forEach(x -> System.out.println(x[0]+" "+x[1]+" "+x[2]));

        Stream<String> row2 = Files.lines(Paths.get("data.csv"));
        Map<String, Integer> dataMap = new HashMap<>();
        dataMap = row2.map(x->x.split(","))
                .filter(x->x.length==3)
                .filter(x->Integer.parseInt(x[1]) > 25)
                .collect(Collectors.toMap(
                        x-> x[0],
                        x-> Integer.parseInt(x[1])));
        row2.close();

        for(String a:dataMap.keySet())
        {
            System.out.println(a+" "+dataMap.get(a));
        }



    }

}
